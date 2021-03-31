<?php

namespace App\Controller;
use App\Entity\VeloAl;
use App\Form\VeloalType;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Dompdf\Dompdf;
use Dompdf\Options;

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
            $this->addFlash('success', 'Article Created!');
            return $this->redirectToRoute("afficherval");
        }
        return $this->render("veloal/ajouterveloal.html.twig",array("form"=>$form->createView()));

    }


    /**
     * @Route("afficherval", name="afficherval")
     */
    public function affiche_velo(Request $request,PaginatorInterface $paginator)
    {
        $veloal=$this->getDoctrine()->getRepository(VeloAl::class)->findAll();

        $veloal = $paginator->paginate(
            $veloal, /* query NOT result */
            $request->query->getInt('page', 1)/*page number*/,
            3/*limit per page*/
        );

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


    /**
     * @Route("/searchvelox ", name="searchvelox")
     */
    public function searchvelox(Request $request,NormalizerInterface $Normalizer){
        $repository = $this->getDoctrine()->getRepository(VeloAl::class);
        $requestString=$request->get('searchValue');
        $veloal = $repository->findVeloBymarque($requestString);
        $jsonContent = $Normalizer->normalize($veloal, 'json',['groups'=>'veloal']);
        $retour=json_encode($jsonContent);
        return new Response($retour);
}





    /**
     * @return Response
     * @Route("/afficherpdf", name="afficherpdf")
     */
    public function afficherpdf(): Response
    {

        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');


        $dompdf = new Dompdf($pdfOptions);
        $veloal = $this->getDoctrine()->getRepository(VeloAl::class)->findAll();



        $html = $this->renderView('veloal/list.html.twig', ['veloal' => $veloal,]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'portrait');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (force download)
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => false
        ]);
    }













}



