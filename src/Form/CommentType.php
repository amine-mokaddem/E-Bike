<?php

namespace App\Form;

use App\Entity\Avis;
use Doctrine\DBAL\Types\IntegerType;
use phpDocumentor\Reflection\Types\This;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class CommentType extends ApplicationType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('note', \Symfony\Component\Form\Extension\Core\Type\IntegerType::class, $this->getConfiguration("Note sur 5 ",
            "veuillez indiquer votre note de 0 à 5 ",[
                'attr'=>[
                    'min'=>0,
                    'max'=>5,
                    'step'=>1
                ]
                ]) )
            ->add('comment',TextareaType::class, $this->getConfiguration("votre avis","N'hesitez pas a etre tres precit "))

        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Avis::class,
        ]);
    }
}
