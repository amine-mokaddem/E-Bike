<?php

namespace App\Repository;

use App\Entity\VeloAl;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method VeloAl|null find($id, $lockMode = null, $lockVersion = null)
 * @method VeloAl|null findOneBy(array $criteria, array $orderBy = null)
 * @method VeloAl[]    findAll()
 * @method VeloAl[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class VeloAlRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, VeloAl::class);
    }

    // /**
    //  * @return VeloAl[] Returns an array of VeloAl objects
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
    public function findOneBySomeField($value): ?VeloAl
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
