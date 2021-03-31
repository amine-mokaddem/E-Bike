<?php

namespace App\Controller;

use App\Form\TrieNbrDeplacesType;
use App\Repository\EventRepository;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Component\HttpFoundation\File\UploadedFile;
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
    public function ajout_event(Request $request)
    {
        $event = new Event();
        $form = $this->createForm(AjoutEventType::class, $event);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('image')->getData();
            $newFilename = md5(uniqid()) . '.' . $file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('img_directory'),
                    $newFilename
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $em = $this->getDoctrine()->getManager();
            $event->setImage($newFilename);
            $em->persist($event);
            $em->flush();
            return $this->redirectToRoute("affichee");
        }
        return $this->render("evenements/ajoute.html.twig", array("form" => $form->createView()));

    }


    /**
     * @Route("/affichee", name="affichee")
     */
    public function affiche_event()
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->findAll();
        return $this->render('evenements/affichee.html.twig', ['event' => $event]);
    }

    /**
     * @Route("/afficheee", name="afficheee")
     */
    public function affichee_event()
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->orderbynb();
        return $this->render('evenements/evenements.html.twig', ['event' => $event]);
    }
    /**
     * @Route("/afficheeee", name="afficheeee")
     */
    public function afficheee_event()
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->orderbynbb();
        return $this->render('evenements/evenements.html.twig', ['event' => $event]);
    }
    /**
     * @Route("/afficheeeb", name="afficheeeb")
     */
    public function afficheeb_event()
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->orderbynb();
        return $this->render('evenements/affichee.html.twig', ['event' => $event]);
    }
    /**
     * @Route("/afficheeeeb", name="afficheeeeb")
     */
    public function afficheeeb_event()
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->orderbynbb();
        return $this->render('evenements/affichee.html.twig', ['event' => $event]);
    }
    /**
     * @Route("supprimere{id}", name="supprimere")
     */
    public function supprimer_event($id)
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->find($id);
        $em = $this->getDoctrine()->getManager();
        $em->remove($event);
        $em->flush();
        return $this->redirectToRoute("affichee");
    }

    /**
     * @Route("updatee{id}", name="updatee")
     */
    public function update($id, Request $request)
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->find($id);
        $form = $this->createForm(AjoutEventType::class, $event)->add('image', FileType::class, array('label' => 'image', 'data_class' => null, 'required' => false));
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $em = $this->getDoctrine()->getManager();

            /** @var UploadedFile $file */
            $file = $event->getImage();
            $fileName = md5(uniqid()) . '.' . $file->guessExtension();
            $file->move($this->getParameter('upload_directory'), $fileName);
            $event->setImage($fileName);

            $em->flush();
            return $this->redirectToRoute("affichee");
        }

        return $this->render("evenements/modifiere.html.twig", array("form" => $form->createView()));
    }

    /**
     * @Route("/evenements", name="evenements")
     */
    public function affiche_event_front()
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->findAll();
        return $this->render('evenements/evenements.html.twig', ['event' => $event]);
    }

    /**
     * @Route("/Quickview{id}", name="Quickview")
     */
    public function affiche_Quickview($id)
    {
        $event = $this->getDoctrine()->getRepository(Event::class)->find($id);
        return $this->render('evenements/Quickview.html.twig', ['i' => $event]);
    }
}