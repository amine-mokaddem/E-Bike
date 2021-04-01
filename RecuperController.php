<?php

namespace App\Controller;

use App\Entity\Utilisateur;
use App\Form\UtilisateurType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\Request;
use App\Form\RecuperType;
use App\Entity\Recuper;

class RecuperController extends AbstractController
{   /**
    * @Route("/recuper", name="recuper")
    */

    public function ajoutcompte(Request $request,\Swift_Mailer $mailer){
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
