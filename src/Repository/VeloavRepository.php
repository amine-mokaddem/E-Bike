<?php

namespace App\Repository;

use App\Entity\Veloav;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Veloav|null find($id, $lockMode = null, $lockVersion = null)
 * @method Veloav|null findOneBy(array $criteria, array $orderBy = null)
 * @method Veloav[]    findAll()
 * @method Veloav[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class VeloavRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Veloav::class);
    }

    // /**
    //  * @return Veloav[] Returns an array of Veloav objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('v')
            ->andWhere('v.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('v.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Veloav
    {
        return $this->createQueryBuilder('v')
            ->andWhere('v.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
