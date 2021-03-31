<?php

namespace App\Repository;

use App\Entity\PieceR;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method PieceR|null find($id, $lockMode = null, $lockVersion = null)
 * @method PieceR|null findOneBy(array $criteria, array $orderBy = null)
 * @method PieceR[]    findAll()
 * @method PieceR[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class PieceRRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, PieceR::class);
    }

    // /**
    //  * @return PieceR[] Returns an array of PieceR objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?PieceR
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
