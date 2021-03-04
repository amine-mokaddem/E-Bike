<?php

namespace App\Controller;

use App\Entity\Commande;
use App\Entity\Veloav;
use App\Form\CommandeType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class CommandeController extends AbstractController
{
    /**
     * @Route("/commande{id}", name="commande")
     */
    public function ajouter_commande($id,Request $request )
    {
         $com =new Commande();
         $velo=$this->getDoctrine()->getRepository(Veloav::class)->find($id);
         $com->addIdvelo($velo);
        $com->setEtat('en attente');
        $form = $this->createForm(CommandeType::class, $com);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($com);
            $em->flush();
            return $this->redirectToRoute("home");
        }
        return $this->render("commande/commande.html.twig",array("form"=>$form->createView()));


    }
}
