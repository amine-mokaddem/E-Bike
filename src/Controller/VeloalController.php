<?php

namespace App\Controller;
use App\Entity\VeloAl;
use App\Form\VeloalType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class VeloalController extends AbstractController
{


    /**
     * @Route("ajouterval", name="ajouterval")
     */
    public function ajoutvelo(Request $request){
        $veloal= new VeloAl();
        $form= $this->createForm(VeloalType::class, $veloal);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
            $em= $this->getDoctrine()->getManager();
            $em->persist($veloal);
            $em->flush();
            return $this->redirectToRoute("afficherval");
        }
        return $this->render("veloal/ajouterveloal.html.twig",array("form"=>$form->createView()));

    }


    /**
     * @Route("afficherval", name="afficherval")
     */
    public function affiche_velo(){
        $veloal=$this->getDoctrine()->getRepository(VeloAl::class)->findAll();
        return $this->render("veloal/afficherveloal.html.twig", ['veloal'=>$veloal]);
    }

    /**
     * @Route("supprimerval{id}", name="supprimerval")
     */
    public function supprimer_velo($id){
        $veloal = $this->getDoctrine()->getRepository(VeloAl::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($veloal);
        $em->flush();
        return $this->redirectToRoute("afficherval");
    }
    /**
     * @Route("modifierval{id}", name="modifierval")
     */
    public function updatevelo($id , Request $request){
        $veloal= $this->getDoctrine()->getRepository(VeloAl::class)->find($id);
        $form= $this->createForm(VeloalType::class, $veloal);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
            $em= $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("afficherval");}

        return $this->render("veloal/modifierveloal.html.twig",array("form"=>$form->createView()));
    }


    /**
     * @Route("location", name="location")
     */
    public function affiche_veloal_front(){
        $veloal=$this->getDoctrine()->getRepository(VeloAl::class)->findAll();
        return $this->render("veloal/veloal.html.twig", ['veloal'=>$veloal]);
    }

























}
