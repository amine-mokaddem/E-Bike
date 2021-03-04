<?php

namespace App\Entity;

use App\Repository\EventRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\File\UploadedFile;
use Symfony\Component\String\Slugger\SluggerInterface;

/**
 * @ORM\Entity(repositoryClass=EventRepository::class)
 */
class Event
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
     * @ORM\Column(type="integer")
     */
    private $nbr_place;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $depart;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $arivee;

    /**
     * @ORM\Column(type="datetime")
     */
    private $date_allee;

    /**
     * @ORM\Column(type="datetime")
     */
    private $date_retour;

    /**
     * @ORM\Column(type="string", length=255)
     */
    private $description;

    /**
     * @ORM\ManyToMany(targetEntity=Participant::class, mappedBy="id_event")
     */
    private $inscription;

    public function __construct()
    {
        $this->inscription = new ArrayCollection();
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

    public function getNbrPlace(): ?int
    {
        return $this->nbr_place;
    }

    public function setNbrPlace(int $nbr_place): self
    {
        $this->nbr_place = $nbr_place;

        return $this;
    }

    public function getDepart(): ?string
    {
        return $this->depart;
    }

    public function setDepart(string $depart): self
    {
        $this->depart = $depart;

        return $this;
    }

    public function getArivee(): ?string
    {
        return $this->arivee;
    }

    public function setArivee(string $arivee): self
    {
        $this->arivee = $arivee;

        return $this;
    }

    public function getDateAllee(): ?\DateTimeInterface
    {
        return $this->date_allee;
    }

    public function setDateAllee(\DateTimeInterface $date_allee): self
    {
        $this->date_allee = $date_allee;

        return $this;
    }

    public function getDateRetour(): ?\DateTimeInterface
    {
        return $this->date_retour;
    }

    public function setDateRetour(\DateTimeInterface $date_retour): self
    {
        $this->date_retour = $date_retour;

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

    /**
     * @return Collection|Participant[]
     */
    public function getInscription(): Collection
    {
        return $this->inscription;
    }

    public function addInscription(Participant $inscription): self
    {
        if (!$this->inscription->contains($inscription)) {
            $this->inscription[] = $inscription;
            $inscription->addIdEvent($this);
        }

        return $this;
    }

    public function removeInscription(Participant $inscription): self
    {
        if ($this->inscription->removeElement($inscription)) {
            $inscription->removeIdEvent($this);
        }

        return $this;
    }


}
