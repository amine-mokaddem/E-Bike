<?php

namespace App\Controller;

use App\Entity\Utilisateur;
use App\Form\LoginType;
use App\Form\UtilisateurType;
use App\Form\RecuperType;
use App\Entity\Recuper;
use phpDocumentor\Reflection\Types\String_;
use Symfony\Bundle\SwiftmailerBundle;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

class UtilisateurController extends AbstractController
{
    /**
     * @Route("/user/api/login", name="user_login")
     */
    public function login(Request $request): Response
    {
        $email = $request->query->get('email');
        $password = $request->query->get('password');

        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository(Utilisateur::class)->findOneBy(['email' => $email]);
        if ($user) {

            if (password_verify($password, $user->getPassword())) {


                $serializer = new Serializer([new ObjectNormalizer()]);
                $formatted = $serializer->normalize($user);
                return new JsonResponse($formatted);
            } else {
                //password not found
                return new Response("passord error");
            }
        } else {
            //email not found
            return new Response("failed");
        }
    }

    /**
     * @Route("/user/api/signup", name="user_signup")
     */
    public function signUp(Request $request, UserPasswordEncoderInterface $encoder, \Swift_Mailer $mailer): Response
    {
        $email = $request->query->get('email');
        $password = $request->query->get('password');
        $nom = $request->query->get('nom');
        $phone = $request->query->get('phone');
        $type = $request->query->get('type');

        //test addresse lazm bl @
        if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
            return new Response("email invalide");
        }
        $user = new Utilisateur();
        $user->setEmail($email);
        $user->setPassword($encoder->encodePassword($user, $password));
        $user->setNom($nom);
        $user->setNumtel($phone);
        $user->setType($type);

        $message= (new \Swift_Message('e-bike bienvenue'))
            ->setFrom('yassine.bensalem@esprit.tn')
            ->setTo($email)
            ->setBody('bienvenue a e-bike votre compte est valider');

        $mailer->send($message);
        try {
            $em = $this->getDoctrine()->getManager();
            $em->persist($user);
            $em->flush();

            return new JsonResponse("compte cree", 200);
        } catch (\Exception $ex) {
            return new Response("exception" . $ex->getMessage());
        }
    }

    /**
     * @Route("/user/api/findemail", name="user_find")
     */
    public function findByEmail(Request $request, NormalizerInterface $Normalizer)
    {

        $email = $request->query->get('email');

        $user = $this->getDoctrine()->getRepository(Utilisateur::class)->findOneBy(array('email' => $email));
        $jsonContent = $Normalizer->normalize($user, 'json');

        return new Response(json_encode($jsonContent));
    }
    /**
     * @Route("", name="utilisateur")
     */
    public function affiche(): Response
    {
        return $this->render('utilisateur/accueil.html.twig', [
            'controller_name' => 'UtilisateurController',
        ]);
    }

    /**
     * @Route("/login", name="login")
     */
    public function verif(Request $request)
    {
        $User = new Utilisateur();
        $form = $this->createForm(LoginType::class, $User);
        $form->handleRequest($request);
        if ($form->isSubmitted()) {
            $nom = $User->getNom();
            $password = $User->getPassword();
            $user1 = $this->getDoctrine()->getRepository(Utilisateur::class)->findOneBy(array('nom' => $nom, 'password' => $password));

            if (!$user1) {
                return $this->redirectToRoute("login");
            } else {
                $type = $user1->getType();
                if ($type == 'admin') {
                    return $this->redirectToRoute("back");
                } else {
                    if ($type == 'client') {
                        return $this->redirectToRoute("home");
                    }
                }
            }
        }
        return $this->render("utilisateur/login.html.twig", array("form" => $form->createView()));
    }


    /**
     * @Route("creer", name="creer")
     */
    public function ajoutcompte(Request $request,\Swift_Mailer $mailer){
        $util= new Utilisateur();
        $form= $this->createForm(UtilisateurType::class, $util);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
            $util->setType('client');

            $m= $util->getEmail();
            $message= (new \Swift_Message('e-bike bienvenue'))
                ->setFrom('yassine.bensalem@esprit.tn')
                ->setTo($m)
                ->setBody('bienvenue a e-bike votre compte est valider');

            $mailer->send($message);



            $em= $this->getDoctrine()->getManager();
            $em->persist($util);

            $em->flush();

            return $this->redirectToRoute("login");

        }
        return $this->render("utilisateur/creer.html.twig",array("form"=>$form->createView()));

    }








    /**
     * @Route("ajouterU", name="ajouterU")
     */
    public function ajoutvelo(Request $request){
        $util= new Utilisateur();
        $form= $this->createForm(UtilisateurType::class, $util);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
            $em= $this->getDoctrine()->getManager();
            $em->persist($util);
            $em->flush();
            return $this->redirectToRoute("afficherU");
        }
        return $this->render("utilisateur/ajouterU.html.twig",array("form"=>$form->createView()));

    }


    /**
     * @Route("afficherU", name="afficherU")
     */
    public function affiche_velo(){
        $util=$this->getDoctrine()->getRepository(Utilisateur::class)->findAll();
        return $this->render("utilisateur/afficherU.html.twig", ['util'=>$util]);
    }

    /**
     * @Route("supprimerU{id}", name="supprimerU")
     */
    public function supprimer_velo($id){
        $util = $this->getDoctrine()->getRepository(Utilisateur::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($util);
        $em->flush();
        return $this->redirectToRoute("afficherU");
    }
    /**
     * @Route("modifierU{id}", name="modifierU")
     */
    public function updatevelo($id , Request $request){
        $util= $this->getDoctrine()->getRepository(Utilisateur::class)->find($id);
        $form= $this->createForm(UtilisateurType::class, $util);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
            $em= $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("afficherU");}

        return $this->render("utilisateur/modifierU.html.twig",array("form"=>$form->createView()));
    }

    /**
     * @Route("/recuper", name="recuper")
     */

    public function recup (Request $request,\Swift_Mailer $mailer){
        $util= new Recuper();
        $form= $this->createForm(RecuperType::class, $util);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){


            $m= $util->getEmail();
            $user1 = $this->getDoctrine()->getRepository(Utilisateur::class)->findOneBy(['email' => $m]);
            if (!$user1) {
                return $this->redirectToRoute("creer");
            }
            else {
                $mot = $user1->getPassword();

                $message = (new \Swift_Message('e-bike recuper mot de passe '))
                    ->setFrom('yassine.bensalem@esprit.tn')
                    ->setTo($m)
                    ->setBody($mot);

                $mailer->send($message);





            }
            return $this->redirectToRoute("login");

        }
        return $this->render("recuper/recuper.html.twig",array("recuperForm"=>$form->createView()));

    }















}
