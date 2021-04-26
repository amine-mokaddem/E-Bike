/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author amine
 */
public class Veloav {
   private int id ;
   private String marque;
   private String type;
   private int prix;
   private int quantite;
   private String imagev;
   private String description;
   private String disponibilite;

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getDescription() {
        return description;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getImagev() {
        return imagev;
    }

    public void setImagev(String imagev) {
        this.imagev = imagev;
    }

    public Veloav(int id, String marque, String type, int prix, int quantite, String imagev, String description, String disponibilite) {
        this.id = id;
        this.marque = marque;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
        this.imagev = imagev;
        this.description = description;
        this.disponibilite = disponibilite;
    }

    public Veloav(String marque, String type, int prix, int quantite, String imagev, String description, String disponibilite) {
        this.marque = marque;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
        this.imagev = imagev;
        this.description = description;
        this.disponibilite = disponibilite;
    }

    public Veloav(String marque, String type, int prix, int quantite, String description, String disponibilite) {
        this.marque = marque;
        this.type = type;
        this.prix = prix;
        this.quantite = quantite;
        this.description = description;
        this.disponibilite = disponibilite;
    }
    


}
