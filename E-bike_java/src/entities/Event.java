/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.util.Date;
/**
 *
 * @author amine
 */
public class Event {
    private int id;
    private String nom;
    private int nbr_place;
    private String depart;
    private String arivee;
    private  Date  date_allee;
    private Date date_retour;
    private String description;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbr_place() {
        return nbr_place;
    }

    public void setNbr_place(int nbr_place) {
        this.nbr_place = nbr_place;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArivee() {
        return arivee;
    }

    public void setArivee(String arivee) {
        this.arivee = arivee;
    }

    public Date getDate_allee() {
        return date_allee;
    }

    public void setDate_allee(Date date_allee) {
        this.date_allee = date_allee;
    }

    public Date getDate_retour() {
        return date_retour;
    }

    public void setDate_retour(Date date_retour) {
        this.date_retour = date_retour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Event(int id, String nom, int nbr_place, String depart, String arivee, Date date_allee, Date date_retour, String description, String image) {
        this.id = id;
        this.nom = nom;
        this.nbr_place = nbr_place;
        this.depart = depart;
        this.arivee = arivee;
        this.date_allee = date_allee;
        this.date_retour = date_retour;
        this.description = description;
        this.image = image;
    }

    

    public Event(String nom, int nbr_place, String depart, String arivee, Date date_allee, Date date_retour, String description, String image) {
        this.nom = nom;
        this.nbr_place = nbr_place;
        this.depart = depart;
        this.arivee = arivee;
        this.date_allee = date_allee;
        this.date_retour = date_retour;
        this.description = description;
        this.image = image;
    }

    public Event(int nbr_place, String depart, String arivee, Date date_allee, String description, String image) {
        this.nbr_place = nbr_place;
        this.depart = depart;
        this.arivee = arivee;
        this.date_allee = date_allee;
        this.description = description;
        this.image = image;
    }
    
    

    public Event() {
    }

    @Override
    public String toString() {
        return "Event{ nom=" + nom + ", nbr_place=" + nbr_place + ", depart=" + depart + ", arivee=" + arivee + ", date_allee=" + date_allee + ", date_retour=" + date_retour + ", description=" + description + ", image=" + image + '}';
    }

    public Event(int id, String nom, int nbr_place, String depart, String arivee, Date date_allee, Date date_retour, String description) {
        this.id = id;
        this.nom = nom;
        this.nbr_place = nbr_place;
        this.depart = depart;
        this.arivee = arivee;
        this.date_allee = date_allee;
        this.date_retour = date_retour;
        this.description = description;
    }

  
    
    
    
    
  
    
    
    
    
}
