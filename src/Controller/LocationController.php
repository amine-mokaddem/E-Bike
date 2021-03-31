<?php

namespace App\Controller;

use App\Entity\Location;
use App\Entity\VeloAl;
use App\Entity\Veloav;
use App\Form\LocationType;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class LocationController extends Controller
{

    /**
     * @Route("/velo_al{id}", name="velo_al")
     */
    public function ajouter_location($id,Request $request ,\Swift_Mailer $mailer)
    {
        $loc =new Location();
        $velo=$this->getDoctrine()->getRepository(VeloAl::class)->find($id);
        $loc->setIdvelol($velo);
        $form = $this->createForm(LocationType::class, $loc);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();
            $em->persist($loc);
            $l = $velo->getQuantite();
            $velo->setQuantite($l-1);
            $em->flush();
            $mail= $loc->getEmail();
            $message = (new \Swift_Message('Location'))
                ->setFrom('e.bike.contactus@gmail.com')
                ->setTo($mail)
                ->setBody('Votre location a ete bien effectue.')
            ;

            $mailer->send($message);
            return $this->redirectToRoute("home");
        }
        return $this->render("location/location-front.html.twig",array("form"=>$form->createView()));
    }
    /**
     * @Route("afficherloc", name="afficherloc")
     */
    public function affiche_velo(){
        $loc=$this->getDoctrine()->getRepository(Location::class)->findAll();
        return $this->render("veloal/afficherloc.html.twig", ['loc'=>$loc]);

    }

    /**
     * @Route("/veloall", name="veloall")
     */
    public function pagevelo(Request $request, PaginatorInterface $paginator)
    {

        $donnees = $this->getDoctrine()->getRepository(Location::class)->findBy([],['created_at' => 'desc']);
        $loc = $paginator->paginate(
            $donnees,
            $request->query->getInt('page', 1),
            4
        );

        return $this->render("veloal/afficherloc.html.twig", ['loc'=>$loc]);
    }









}
