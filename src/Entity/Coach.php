<?php

namespace App\Entity;

use App\Repository\CoachRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CoachRepository::class)
 */
class Coach
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $nom;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $prenom;

    /**
     * @ORM\ManyToMany(targetEntity=Cours::class, inversedBy="coaches")
     */
    private $idcours;

    public function __construct()
    {
        $this->idcours = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

        return $this;
    }

    /**
     * @return Collection|Cours[]
     */
    public function getIdcours(): Collection
    {
        return $this->idcours;
    }

    public function addIdcour(Cours $idcour): self
    {
        if (!$this->idcours->contains($idcour)) {
            $this->idcours[] = $idcour;
        }

        return $this;
    }

    public function removeIdcour(Cours $idcour): self
    {
        $this->idcours->removeElement($idcour);

        return $this;
    }
}
