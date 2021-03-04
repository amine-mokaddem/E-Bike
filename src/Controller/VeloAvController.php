<?php

namespace App\Controller;
use App\Entity\Veloav;
use App\Form\VeloavType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class VeloAvController extends AbstractController
{



    /**
     * @Route("ajoutervav", name="ajoutervav")
     */
    public function ajoutvelo(Request $request){
        $veloav= new Veloav();
        $form= $this->createForm(VeloavType::class, $veloav);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
            $em= $this->getDoctrine()->getManager();
            $em->persist($veloav);
            $em->flush();
            return $this->redirectToRoute("affichervav");
        }
        return $this->render("velo_av/ajoutervav.html.twig",array("form"=>$form->createView()));

    }


    /**
     * @Route("affichervav", name="affichervav")
     */
    public function affiche_velo(){
        $veloav=$this->getDoctrine()->getRepository(Veloav::class)->findAll();
        return $this->render("velo_av/affichervav.html.twig", ['veloav'=>$veloav]);
    }

    /**
     * @Route("supprimervav{id}", name="supprimervav")
     */
    public function supprimer_velo($id){
        $veloav = $this->getDoctrine()->getRepository(Veloav::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($veloav);
        $em->flush();
        return $this->redirectToRoute("affichervav");
    }
    /**
     * @Route("modifiervav{id}", name="modifiervav")
     */
    public function updatevelo($id , Request $request){
        $veloav= $this->getDoctrine()->getRepository(Veloav::class)->find($id);
        $form= $this->createForm(VeloavType::class, $veloav);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
            $em= $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("affichervav");}

        return $this->render("velo_av/modifiervav.html.twig",array("form"=>$form->createView()));
    }

    /**
     * @Route("velo_av", name="velo_av")
     */
    public function affiche_veloal_front(){
        $veloav=$this->getDoctrine()->getRepository(Veloav::class)->findAll();
        return $this->render("velo_av/veloav.html.twig", ['veloav'=>$veloav]);
    }


























}
