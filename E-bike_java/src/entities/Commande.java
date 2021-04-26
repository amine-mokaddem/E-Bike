/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;

/**
 *
 * @author amine
 */
public class Commande {
 private int id;
 private String adresse;
  private int numtel;
 private String nom;
 private String email;
 private String etat;
 private int quantité;
 private Date datecom;

    public Commande(int id, String adresse, int numtel, String nom, String email, String etat, int quantité, Date datecom) {
        this.id = id;
        this.adresse = adresse;
        this.numtel = numtel;
        this.nom = nom;
        this.email = email;
        this.etat = etat;
        this.quantité = quantité;
        this.datecom = datecom;
    }

    public Commande(String adresse, int numtel, String nom, String email, String etat, int quantité, Date datecom) {
        this.adresse = adresse;
        this.numtel = numtel;
        this.nom = nom;
        this.email = email;
        this.etat = etat;
        this.quantité = quantité;
        this.datecom = datecom;
    }

   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getQuantité() {
        return quantité;
    }

    public void setQuantité(int quantité) {
        this.quantité = quantité;
    }

    public Date getDatecom() {
        return datecom;
    }

    public void setDatecom(Date datecom) {
        this.datecom = datecom;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", adresse=" + adresse + ", numtel=" + numtel + ", nom=" + nom + ", email=" + email + ", etat=" + etat + ", quantit\u00e9=" + quantité + ", datecom=" + datecom + '}';
    }

  
 
   







 
}
