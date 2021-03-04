<?php

namespace App\Controller;
use App\Entity\Cours;
use App\Form\AjoutcoursType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CoursController extends AbstractController
{


    /**
     * @Route("ajoutcours", name="ajoutcours")
     */
    public function ajout_cours(Request $request){
        $cours= new Cours();
        $form= $this->createForm(AjoutcoursType::class, $cours);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
            $em= $this->getDoctrine()->getManager();
            $em->persist($cours);
            $em->flush();
            return $this->redirectToRoute("affichercours");
        }
        return $this->render("cours/ajoutcours.html.twig",array("form"=>$form->createView()));

    }


    /**
     * @Route("affichercours", name="affichercours")
     */
    public function affiche_cours(){
        $cours=$this->getDoctrine()->getRepository(Cours::class)->findAll();
        return $this->render("cours/affichercours.html.twig", ['cours'=>$cours]);
    }

    /**
     * @Route("supprimercours{id}", name="supprimercours")
     */
    public function supprimer_cours($id){
        $cours = $this->getDoctrine()->getRepository(Cours::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($cours);
        $em->flush();
        return $this->redirectToRoute("affichercours");
    }
    /**
     * @Route("modifiercours{id}", name="modifiercours")
     */
    public function update_cours($id , Request $request){
        $cours= $this->getDoctrine()->getRepository(Cours::class)->find($id);
        $form= $this->createForm(AjoutcoursType::class, $cours);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
            $em= $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("affichercours");}

        return $this->render("cours/modifiercours.html.twig",array("form"=>$form->createView()));
    }
    /**
     * @Route("cours", name="cours")
     */
    public function affiche_c(){
        $cours=$this->getDoctrine()->getRepository(Cours::class)->findAll();
        return $this->render("cours/cours.html.twig", ['cours'=>$cours]);
    }

}








