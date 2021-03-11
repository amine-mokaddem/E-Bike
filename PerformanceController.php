<?php

namespace App\Controller;

use App\Entity\Performance;

use App\Form\PerformanceType;

use App\Repository\PerformanceRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Dompdf\Dompdf;
use Dompdf\Options;

class PerformanceController extends AbstractController
{
    /**
     * @Route("ajouterpp", name="ajouterpp")
     */
    public function ajout_cours(Request $request){
        $per= new Performance();
        $form= $this->createForm(PerformanceType::class, $per);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
            $em= $this->getDoctrine()->getManager();
            $em->persist($per);
            $em->flush();
            return $this->redirectToRoute("afficherp");
        }
        return $this->render("performance/ajouterpp.html.twig",array("form"=>$form->createView()));

    }

    /**
     * @Route("/afficherpp", name="pdf")
     */
    public function affiche_veloo()
    {


// Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);
        $performance = $this->getDoctrine()->getRepository(Performance::class)->findAll();


        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('performance/mypdf1.html.twig', [
            'performance' => $performance
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => true
        ]);




    }

   /**
    * @Route("/afficherp", name="afficherp")
     */
   public function affiche_velo(){
       $performance=$this->getDoctrine()->getRepository(Performance::class)->findAll();
       return $this->render("performance/afficherp.html.twig", ['performance'=>$performance]);
   }

    /**
     * @Route("/supprimerp{id}", name="supprimerp")
     */
    public function supprimer_velo($id){
        $performance = $this->getDoctrine()->getRepository(Performance::class)->find($id);
        $em=$this->getDoctrine()->getManager();
        $em->remove($performance);
        $em->flush();
        return $this->redirectToRoute("afficherp");
    }
    /**
     * @Route("/modifierp{id}", name="modifierp")
     */
    public function updatevelo($id , Request $request){
        $per= $this->getDoctrine()->getRepository(Performance::class)->find($id);
        $form= $this->createForm(PerformanceType::class, $per);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
            $em= $this->getDoctrine()->getManager();
            $em->flush();
            return $this->redirectToRoute("afficherp");}

        return $this->render("performance/modifierp.html.twig",array("form"=>$form->createView()));
    }
    /**
     * @Route("performance1", name="performance1")
     */
    public function ajoutcompte(Request $request){
        $performance= new Performance();
        $form= $this->createForm(PerformanceType::class, $performance);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()){
           # $util->setType('client');
            $em= $this->getDoctrine()->getManager();
            $em->persist($performance);
            $em->flush();
            return $this->redirectToRoute("performance1");
        }
        return $this->render("performance/performance1.html.twig",array("form"=>$form->createView()));

    }
        /**
         * @Route("/afficherppp",name="trie")
         */
        function OrderByPrix(PerformanceRepository $repository){
            $performance=$repository->orderPrix();
            return $this->render('performance/afficherp.html.twig',['performance'=>$performance]);
        }




   # /**
    # * @Route("performance", name="performance")
    # */
   # public function affiche_c(){
   #     $performance=$this->getDoctrine()->getRepository(Performance::class)->findAll();
    #    return $this->render("performance/performance.html.twig", ['performance'=>$performance]);
   # }


}
