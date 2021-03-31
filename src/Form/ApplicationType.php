<?php


namespace App\Form;


use Symfony\Component\Form\AbstractType;

class ApplicationType extends AbstractType
{
    /**
     * @param string $label
     * @param string $placeholder
     * @param array $options
     */
    protected function getConfiguration(string $label, string $placeholder, array $options = [])
    {
        return array_merge_recursive(
            [
                'label' => $label,
                'attr' => [
                    'placeholder' => $placeholder
                ]
            ], $options);


    }

}





