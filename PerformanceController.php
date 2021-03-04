<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class PerformanceController extends AbstractController
{
    /**
     * @Route("/performance", name="performance")
     */
    public function index(): Response
    {
        return $this->render('performance/performance.html.twig', [
            'controller_name' => 'PerformanceController',
        ]);
    }
}
