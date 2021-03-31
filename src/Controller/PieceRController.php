<?php

namespace App\Controller;
use App\Entity\PieceR;
use App\Form\AjoutPieceRType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class PieceRController extends AbstractController
{

    /**
     * @Route("/ajouterp", name="ajouterp")
     */
    public function ajout_piece(Request $request){
        $piece= new PieceR();
        $form= $this->createForm(AjoutPieceRType::class, $piece);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
            $em= $this->getDoctrine()->getManager();
            $em->persist($piece);
            $em->flush();
            return $this->redirectToRoute("afficherpr");
        }
        return $this->render("piece_r/ajoutp.html.twig",array("form"=>$form->createView()));

    }


    /**
     * @Route("/afficherpr", name="afficherpr")
     */
    public function affiche_piece(){
        $piece=$this->getDoctrine()->getRepository(PieceR::class)->findAll();
        return $this->render('piece_r/affichep.html.twig', ['piece'=>$piece]);
    }

    /**
     * @Route("/supprimerp{id}", name="supprimerp")
     */
    public function supprimer_piece($id){
        $piece = $this->getDoctrine()->getRepository(PieceR::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($piece);
        $em->flush();
        return $this->redirectToRoute("afficherpr");
    }
    /**
     * @Route("/updatep{id}", name="updatep")
     */
    public function update_piece($id , Request $request){
        $piece= $this->getDoctrine()->getRepository(PieceR::class)->find($id);
        $form= $this->createForm(AjoutPieceRType::class, $piece);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
            $em= $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("afficherpr");}

        return $this->render("piece_r/modifierp.html.twig",array("form"=>$form->createView()));
    }











}
