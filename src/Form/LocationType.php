<?php

namespace App\Form;

use App\Entity\Location;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class LocationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom')
            ->add('prenom')
            ->add('duree',ChoiceType::class,array(
                'choices'=> array (
                    '30min'=>30,
                    '1h'=>1,
                    '1h30min'=>130,
                    '2h'=>2,
                    '2h30min'=>230,
                    '3h'=>3,
                ),
                'expanded'=>true
            ))
            ->add('datedeb')
            ->add('datefin')
            ->add('email')

        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Location::class,
        ]);
    }
}
