<?php

namespace App\Entity;

use App\Repository\CoursRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CoursRepository::class)
 */
class Cours
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
    private $circuitcours;

    /**
     * @ORM\Column(type="datetime")
     */
    private $datecours;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $dureecours;

    /**
     * @ORM\Column(type="float")
     */
    private $frais;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $typecours;

    /**
     * @ORM\ManyToMany(targetEntity=Coach::class, mappedBy="idcours")
     */
    private $coaches;

    public function __construct()
    {
        $this->coaches = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getCircuitcours(): ?string
    {
        return $this->circuitcours;
    }

    public function setCircuitcours(string $circuitcours): self
    {
        $this->circuitcours = $circuitcours;

        return $this;
    }

    public function getDatecours(): ?\DateTimeInterface
    {
        return $this->datecours;
    }

    public function setDatecours(\DateTimeInterface $datecours): self
    {
        $this->datecours = $datecours;

        return $this;
    }

    public function getDureecours(): ?string
    {
        return $this->dureecours;
    }

    public function setDureecours(string $dureecours): self
    {
        $this->dureecours = $dureecours;

        return $this;
    }

    public function getFrais(): ?float
    {
        return $this->frais;
    }

    public function setFrais(float $frais): self
    {
        $this->frais = $frais;

        return $this;
    }

    public function getTypecours(): ?string
    {
        return $this->typecours;
    }

    public function setTypecours(string $typecours): self
    {
        $this->typecours = $typecours;

        return $this;
    }

    /**
     * @return Collection|Coach[]
     */
    public function getCoaches(): Collection
    {
        return $this->coaches;
    }

    public function addCoach(Coach $coach): self
    {
        if (!$this->coaches->contains($coach)) {
            $this->coaches[] = $coach;
            $coach->addIdcour($this);
        }

        return $this;
    }

    public function removeCoach(Coach $coach): self
    {
        if ($this->coaches->removeElement($coach)) {
            $coach->removeIdcour($this);
        }

        return $this;
    }
}
