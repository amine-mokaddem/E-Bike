<?php

namespace App\Controller;

use App\Entity\Event;
use App\Form\AjoutEventType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class EvenementsController extends AbstractController
{

    /**
     * @Route("ajoute", name="ajoute")
     */
    public function ajout_event(Request $request){
        $event= new Event();
        $form= $this->createForm(AjoutEventType::class, $event);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
            $em= $this->getDoctrine()->getManager();
            $em->persist($event);
            $em->flush();
            return $this->redirectToRoute("affichee");
        }
           return $this->render("evenements/ajoute.html.twig",array("form"=>$form->createView()));

}


    /**
     * @Route("/affichee", name="affichee")
     */
    public function affiche_event(){
        $event=$this->getDoctrine()->getRepository(Event::class)->findAll();
        return $this->render('evenements/affichee.html.twig', ['event'=>$event]);
    }

    /**
     * @Route("supprimere{id}", name="supprimere")
     */
    public function supprimer_event($id){
        $event = $this->getDoctrine()->getRepository(Event::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($event);
        $em->flush();
        return $this->redirectToRoute("affichee");
    }
    /**
     * @Route("updatee{id}", name="updatee")
     */
   public function update($id , Request $request){
        $event= $this->getDoctrine()->getRepository(Event::class)->find($id);
       $form= $this->createForm(AjoutEventType::class, $event);
       $form->handleRequest($request);
       if ($form->isSubmitted() && $form->isValid()){
           $em= $this->getDoctrine()->getManager();
           $em->flush();
           return $this->redirectToRoute("affichee");}

           return $this->render("evenements/modifiere.html.twig",array("form"=>$form->createView()));
   }

    /**
     * @Route("/evenements", name="evenements")
     */
    public function affiche_event_front(){
        $event=$this->getDoctrine()->getRepository(Event::class)->findAll();
        return $this->render('evenements/evenements.html.twig', ['event'=>$event]);
    }



}