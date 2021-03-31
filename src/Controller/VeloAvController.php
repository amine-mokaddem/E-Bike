<?php

namespace App\Controller;
use App\Entity\Avis;
use App\Entity\Utilisateur;
use App\Entity\Veloav;
use App\Form\AvisType;
use App\Form\CommentType;
use App\Form\Type\RatingType;
use App\Form\VeloavbackType;
use App\Form\VeloavType;
use Doctrine\Persistence\ObjectManager;
use Doctrine\Persistence\ObjectManagerDecorator;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\Routing\Annotation\Route;
use Doctrine\ORM\EntityManagerInterface;

class VeloAvController extends AbstractController
{
    /**
     * @Route("ajoutervav", name="ajoutervav")
     */
    public function ajoutvelo(Request $request){
        $veloav= new Veloav();
        $form= $this->createForm(VeloavbackType::class, $veloav);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
            $file = $form->get('imagev')->getData();

            $newFilename= md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('img_directory') ,
                    $newFilename
                );
            } catch (FileException $e) {
                // ... handle exception if something happens during file upload
            }
            $em= $this->getDoctrine()->getManager();
            $veloav->setImagev($newFilename);
            $em->persist($veloav);
            $qt=$veloav->getQuantite();
            if ($qt> 0){
                $veloav->setDisponibilite('en Stock');
            }
                    else if($qt == 0){
                        $veloav->setDisponibilite('hors stock');
                    }
            $em->flush();

            return $this->redirectToRoute("affichervav");
        }
        return $this->render("velo_av/ajoutervav.html.twig",array("form"=>$form->createView()));

    }


    /**
     * @Route("/affichervav", name="affichervav")
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
            $file = $form->get('imagev')->getData();
            if ($file)
            {
                $picture = VeloavType::ajoutvelo($file, $veloav);
                $veloav->setPicture('pictures/blog/' . $picture);
            }
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



    /**
     * @Route("detail_velo{id}", name="detail_velo")
     */
    public function affiche_detail($id, Request $request ){
        $veloav=$this->getDoctrine()->getRepository(Veloav::class)->find($id);
        $comment= new Avis();
        $form= $this->createForm(CommentType::class,$comment);
        $form->handleRequest($request);
        if($form->isSubmitted() && $form->isValid() ){
            $em=$this->getDoctrine()->getManager();
            $comment->setAuteur($this->getUser());
            $comment->setVelo($veloav);
            $comment->setDateavis(new \DateTime('now'));
            $em->persist($comment);
            $em->flush();
            $this->addFlash(
                'success',
                "votre commentaire a bien été pris en compte ! "
            );
            return $this->redirectToRoute('detail_velo', ['id'=>$veloav->getId()]);
        }
        $listcom=$this->getDoctrine()->getRepository(Avis::class)->findAll();

        return $this->render("velo_av/affichedetail.html.twig", [
            'velo'=>$veloav,
            'form'=>$form->createView(),
            'comment'=>$listcom

        ]);}
    /**
     * @Route("/back", name="back")
     */
    public function back(EntityManagerInterface $manager)
    {
$user=$manager->createQuery('SELECT COUNT(u) FROM App\Entity\Utilisateur u')->getSingleScalarResult();
        $commande=$manager->createQuery('SELECT COUNT(a) FROM App\Entity\Commande a')->getSingleScalarResult();
        $avis=$manager->createQuery('SELECT COUNT(b) FROM App\Entity\Avis b')->getSingleScalarResult();
        $velo=$manager->createQuery('SELECT COUNT(c) FROM App\Entity\Veloav c')->getSingleScalarResult();
        $bestvelo=$manager->createQuery(
            'SELECT AVG(c.note) as note, a.marque,a.id,a.description,a.type,a.prix,a.imagev
             FROM App\Entity\Avis c
             JOIN c.velo a
             GROUP BY a
             ORDER BY note DESC'
        )
            ->setMaxResults(5)
            ->getResult();
        $worstvelo=$manager->createQuery(
            'SELECT AVG(c.note) as note, a.marque,a.id,a.description,a.type,a.prix,a.imagev
             FROM App\Entity\Avis c
             JOIN c.velo a
             GROUP BY a
             ORDER BY note ASC'
        )
            ->setMaxResults(5)
            ->getResult();

        $bestcelocom=$manager->createQuery(
            'SELECT count(c) as qunatite, a.marque,a.id,a.description,a.type,a.prix,a.imagev
             FROM App\Entity\Commande c
             JOIN c.veloAvs a
             GROUP BY a
             ORDER BY qunatite DESC'
        )
            ->setMaxResults(5)
            ->getResult();

        $worstcelocom=$manager->createQuery(
            'SELECT count(c) as qunatite, a.marque,a.id,a.description,a.type,a.prix,a.imagev
             FROM App\Entity\Commande c
             JOIN c.veloAvs a
             GROUP BY a
                 ORDER BY qunatite asc'
        )
            ->setMaxResults(5)
            ->getResult();


        return $this->render('piece_r/piece_r_back.html.twig',array(
            'user'=>$user,
            'commande'=>$commande,
            'avis'=>$avis,
            'velo'=>$velo,
            'bestvelo'=>$bestvelo,
            'worstvelo'=>$worstvelo,
            'bestcom'=>$bestcelocom,
            'worstcom'=>$worstcelocom

        ));
    }



































}
