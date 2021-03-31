<?php

namespace App\Entity;

use App\Repository\CommandeRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;

/**
 * @ORM\Entity(repositoryClass=CommandeRepository::class)
 */
class Commande
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
    private $adresse;

    /**
     * @ORM\Column(type="integer")
     */
    private $numtel;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $nom;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $prenom;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $email;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $etat;

    /**
     * @ORM\ManyToMany(targetEntity=Veloav::class, mappedBy="id_commande")
     */
    private $veloAvs;

    /**
     * @ORM\Column(type="integer")
     */
    private $qunatite;

    /**
     * @ORM\Column(type="datetime")
     */
    private $datecom;


    public function __construct()
    {
        $this->veloAvs = new ArrayCollection();
    }


    public function getId(): ?int
    {
        return $this->id;
    }


    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getNumtel(): ?int
    {
        return $this->numtel;
    }

    public function setNumtel(int $numtel): self
    {
        $this->numtel = $numtel;

        return $this;
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

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function getEtat(): ?string
    {
        return $this->etat;
    }

    public function setEtat(string $etat): self
    {
        $this->etat = $etat;

        return $this;
    }

    /**
     * @return Collection|VeloAv[]
     */
    public function getVeloAvs(): Collection
    {
        return $this->veloAvs;
    }

    public function addVeloAv(VeloAv $veloAv): self
    {
        if (!$this->veloAvs->contains($veloAv)) {
            $this->veloAvs[] = $veloAv;
            $veloAv->addIdCommande($this);
        }

        return $this;
    }

    public function removeVeloAv(VeloAv $veloAv): self
    {
        if ($this->veloAvs->removeElement($veloAv)) {
            $veloAv->removeIdCommande($this);
        }

        return $this;
    }

    public function getQunatite(): ?int
    {
        return $this->qunatite;
    }

    public function setQunatite(int $qunatite): self
    {
        $this->qunatite = $qunatite;

        return $this;
    }

    public function getDatecom(): ?\DateTimeInterface
    {
        return $this->datecom;
    }

    public function setDatecom(\DateTimeInterface $datecom): self
    {
        $this->datecom = $datecom;

        return $this;
    }

}
