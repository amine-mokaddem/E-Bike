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
        $event=$this->getDoctrine()->getRepository(Event::class)->find($id);
        $form = $this->createForm(ParticipantType::class, $part);
        $form->handleRequest($request);
        $part->addIdEvent($event);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($part);
            $em->flush();
            return $this->redirectToRoute("evenements");
        }

        return $this->render("participant/participant.html.twig",array("form"=>$form->createView()));

    }
}
