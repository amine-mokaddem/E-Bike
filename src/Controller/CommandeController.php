<?php

namespace App\Controller;

use App\Entity\Commande;
use App\Entity\Utilisateur;
use App\Entity\Veloav;
use App\Form\CommandeType;
use Doctrine\DBAL\Types\IntegerType;
use phpDocumentor\Reflection\Types\Integer;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\CheckboxType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\CountryType;
use Symfony\Component\Form\Extension\Core\Type\CurrencyType;
use Symfony\Component\Form\Extension\Core\Type\RangeType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Dompdf\Dompdf;
use Dompdf\Options;
class CommandeController extends AbstractController
{





    /**
     * @Route("/commande{id}", name="commande")
     */
    public function ajouter_commande($id, Request $request )
    {   $user=$this->getUser();
        $com = new Commande();
        $velo = $this->getDoctrine()->getRepository(Veloav::class)->find($id);
       dd($velo);
        $com->addVeloAv($velo);
        $com->setEtat('en attente');
        $com->setDatecom(new \DateTime('now'));
        $com->setNom($user->getUsername());
        $com->setNumtel($user->getNumtel());
        $com->setEmail($user->getEmail());
$com->setPrenom('mokaddem');
        $form = $this->createForm(CommandeType::class, $com);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($com);
            $v = $velo->getQuantite();
            $q= $com->getQunatite();
            $velo->setQuantite($v - $q);
            if (($velo->getQuantite())<0){
                $velo->setQuantite(0);
                $velo->setDisponibilite('hors stock');

            }
            $em->flush();

            return $this->redirectToRoute("home");

        }
        return $this->render("commande/commande.html.twig", array("form" => $form->createView()));

    }


    /**
     * @Route("/affichecom", name="affichecom")
     */
    public function affiche_comande()
    {
        $com = $this->getDoctrine()->getRepository(Commande::class)->findAll();
        return $this->render('commande/affichercom.html.twig', array('com' => $com));
    }


    /**
     * @Route("supprimercom{id}", name="supprimercom")
     */
    public function supprimer_com($id)
    {
        $com = $this->getDoctrine()->getRepository(Commande::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($com);
        $em->flush();

        return $this->redirectToRoute("affichecom");
    }

    /**
     * @Route("modifiercom{id}", name="modifiercom")
     */

    public function approuver_com($id,\Swift_Mailer $mailer)
    {
        $com = $this->getDoctrine()->getRepository(Commande::class)->find($id);
        $com->setEtat('acceptee');
        $em = $this->getDoctrine()->getManager();
        $em->flush();
        $mail=$com->getEmail();
        $message = (new \Swift_Message('Commande '))
            ->setFrom('e.bike.contactus@gmail.com')
            ->setTo($mail)
            ->setBody('votre commande a bien été approuvée ')
        ;

        $mailer->send($message);
        return $this->redirectToRoute("affichecom");

    }

    /**
     * @Route("ajouterpanier{id}", name="ajouter_panier")
     */
    public function ajouter_panier($id , SessionInterface $session){
     $panier = $session->get('panier', []);
         $panier[$id]=1;
     $session->set('panier', $panier);

        return $this->redirectToRoute("velo_av");
    }

    /**
     * @Route("/panier", name="panier")
     */
    public function panier(SessionInterface $session ){
        $panier = $session->get('panier', []);
        $veloalrepository= $this->getDoctrine()->getRepository(Veloav::class);
        $panierwithdata=[];
        foreach ($panier as $id => $val  ){
                $panierwithdata[] = [
                    'product'=> $veloalrepository->find($id)  ,
                ];
        }

        foreach ($panier as $id => $val  ){
            $paniercom[] = [
                 $veloalrepository->find($id),
            ];
        }
        $session->set('paniercom', $paniercom);

        return $this->render("commande/ajouteraupanier.html.twig", array('items'=>$panierwithdata ));

    }

    /**
     * @Route("/testcom", name="testcom")
     */

    public function passer_commande_panier(SessionInterface $session, Request $request){


    $paniercom=$session->get('paniercom',[]);
     $em=$this->getDoctrine()->getManager();
        $user=$this->getUser();
$i=0;
        foreach ($paniercom as $id => $val ){
            $com= new Commande();

            $com->addVeloAv($val[0]);

        }



        $com->setEtat('en attente');
        $com->setDatecom(new \DateTime('now'));
        $com->setNom($user->getUsername());
        $com->setNumtel($user->getNumtel());
        $com->setEmail($user->getEmail());
        $com->setPrenom('mokaddem');
        $com->setAdresse('test');
        $com->setQunatite(52);
        $em->persist($com);
        $em->flush();
        /*$form= $this->createFormBuilder( $com)
            ->add('qunatite' )
            ->getForm();
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em->persist($com);
           $em->flush();
        }*/
        return $this->render("commande/index.html.twig");


    }

    /**
     * @Route("/listcom", name="listcom")
     */
    public function listcom()
    {   $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
        $com = $this->getDoctrine()->getRepository(Commande::class)->findAll();
        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('commande/imprimercom.html.twig', array('com' => $com));

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => true
        ]);
    }
}