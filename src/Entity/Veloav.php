<?php

namespace App\Entity;

use App\Repository\VeloavRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=VeloavRepository::class)
 */
class Veloav
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
    private $marque;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $type;

    /**
     * @ORM\Column(type="float")
     */
    private $prix;

    /**
     * @ORM\Column(type="integer")
     */
    private $quantite;


    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="please upload image")
     * @Assert\File(mimeTypes={"image/jpeg"})
     */
    private $imagev;

    /**
     * @ORM\ManyToMany(targetEntity=Commande::class, inversedBy="veloAvs")
     */
    private $id_commande;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $description;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $disponibilite;

    /**
     * @ORM\OneToMany(targetEntity=Avis::class, mappedBy="velo", orphanRemoval=true)
     */
    private $avis;

    public function __construct()
    {
        $this->id_commande = new ArrayCollection();
        $this->avis = new ArrayCollection();
    }

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getMarque(): ?string
    {
        return $this->marque;
    }

    public function setMarque(string $marque): self
    {
        $this->marque = $marque;

        return $this;
    }

    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getQuantite(): ?int
    {
        return $this->quantite;
    }

    public function setQuantite(int $quantite): self
    {
        $this->quantite = $quantite;

        return $this;
    }

    public function getImagev()
    {
        return $this->imagev;
    }

    public function setImagev(string $imagev)
    {
        $this->imagev = $imagev;

        return $this;
    }

    /**
     * @return Collection|Commande[]
     */
    public function getIdCommande(): Collection
    {
        return $this->id_commande;
    }

    public function addIdCommande(Commande $idCommande): self
    {
        if (!$this->id_commande->contains($idCommande)) {
            $this->id_commande[] = $idCommande;
        }

        return $this;
    }

    public function removeIdCommande(Commande $idCommande): self
    {
        $this->id_commande->removeElement($idCommande);

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    public function getDisponibilite(): ?string
    {
        return $this->disponibilite;
    }

    public function setDisponibilite(string $disponibilite): self
    {
        $this->disponibilite = $disponibilite;

        return $this;
    }

    /**
     * @return Collection|Avis[]
     */
    public function getAvis(): Collection
    {
        return $this->avis;
    }

    public function addAvi(Avis $avi): self
    {
        if (!$this->avis->contains($avi)) {
            $this->avis[] = $avi;
            $avi->setVelo($this);
        }

        return $this;
    }

    public function removeAvi(Avis $avi): self
    {
        if ($this->avis->removeElement($avi)) {
            // set the owning side to null (unless already changed)
            if ($avi->getVelo() === $this) {
                $avi->setVelo(null);
            }
        }

        return $this;
    }
    public function getavgrating(){
       $sum= array_reduce($this->avis->toArray(),function ($total,$avis){
           return $total+$avis->getNote();
       },0);
       if(count($this->avis)>0) return $moyenne=$sum/count($this->avis);
       return 0;
    }
}
