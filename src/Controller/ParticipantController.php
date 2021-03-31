<?php

namespace App\Controller;

use App\Entity\Event;
use App\Entity\Participant;
use App\Form\ParticipantType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class ParticipantController extends AbstractController
{
    /**
     * @Route("/participant{id}", name="participant")
     */
    public function ajout_participant($id, Request $request)
    {
        $part = new Participant();
        $user=$this->getUser();
        $event=$this->getDoctrine()->getRepository(Event::class)->find($id);
        $form = $this->createForm(ParticipantType::class, $part);
        $form->handleRequest($request);
        $part->addIdEvent($event);
        $part->setEtat('non acceptee');
        $part->setEmail($user->getEmail());
        $part->setNumtel($user->getNumtel());
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($part);
            $p =$event->getNbrPlace();
            $event->setNbrPlace($p-1);
            $em->flush();
            return $this->redirectToRoute("evenements");
        }

        return $this->render("participant/participant.html.twig",array("form"=>$form->createView()));

    }

    /**
     * @Route("/affichepart", name="affichepart")
     */
    public function affiche_part(){
        $p=$this->getDoctrine()->getRepository(Participant::class)->findAll();
        return $this->render('participant/afficher_participantback.html.twig', ['part'=>$p]);
    }
    /**
     * @Route("supprimerpart{id}", name="supprimerpart")
     */
    public function supprimer_part($id){
        $part = $this->getDoctrine()->getRepository(Participant::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($part);
        $em->flush();
        return $this->redirectToRoute("affichepart");
    }
    /**
     * @Route("modifierpart{id}", name="modifierpart")
     */

    public function modifier_part($id,\Swift_Mailer $mailer){
        $part = $this->getDoctrine()->getRepository(Participant::class)->find($id);
        $part->setEtat('acceptee');
        $em=$this->getDoctrine()->getManager();
        $em->flush();
        $mail=$part->getEmail();
        if ( $part->getEtat()=='acceptee')
        {
            $message = (new \Swift_Message('Confirmation'))
                ->setFrom('e.bike.contactus@gmail.com')
                ->setTo($mail)
                ->setBody('Participation confirmée')
            ;

            $mailer->send($message);
        }
        return $this->redirectToRoute("affichepart");

    }

}
