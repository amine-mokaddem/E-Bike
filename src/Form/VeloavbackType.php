<?php

namespace App\Form;

use App\Entity\Veloav;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\FileType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class VeloavbackType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('marque')
            ->add('type')
            ->add('prix')
            ->add('quantite')
            ->add('description')
            ->add('imagev', FileType::class ,array('label' => 'imagev(JPG)'))
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => Veloav::class,
        ]);
    }
}
