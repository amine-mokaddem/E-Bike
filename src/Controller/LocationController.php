<?php

namespace App\Controller;

use App\Entity\Location;
use App\Entity\VeloAl;
use App\Form\LocationType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class LocationController extends AbstractController
{

    /**
     * @Route("/velo_al{id}", name="velo_al")
     */
    public function ajouter_location($id,Request $request )
    {
        $loc =new Location();
        $velo=$this->getDoctrine()->getRepository(VeloAl::class)->find($id);
        $loc->setIdvelol($velo);
        $form = $this->createForm(LocationType::class, $loc);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($loc);
            $em->flush();
            return $this->redirectToRoute("home");
        }
        return $this->render("location/location-front.html.twig",array("form"=>$form->createView()));
    }
}
