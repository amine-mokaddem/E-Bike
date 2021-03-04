<?php

namespace App\Controller;

use App\Entity\Performance;
use App\Entity\Utilisateur;
use App\Form\UtilisateurType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class PerformanceController extends AbstractController
{

    /**
     * @Route("/performance{id}", name="performance")
     */
    public function ajout_performance(Request $request , $id) {
        $per=new Performance();
        $user=$this->getDoctrine()->getRepository(Utilisateur::class)->find($id);
        $per->getIdUtil($user);
        $form = $this->createForm(UtilisateurType::class, $per);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($per);
            $em->flush();
            return $this->redirectToRoute("home");

        }



        return $this->render("performance/performance.html.twig",array("form"=>$form->createView()));
    }
}
