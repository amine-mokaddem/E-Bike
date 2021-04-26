/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

/**
 *
 * @author amine
 */
public class top_vente {
  private String marque ;
    private float prix ;
    private int nbr_achats;
   public top_vente() {
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getNbr_achats() {
        return nbr_achats;
    }

    public void setNbr_achats(int nbr_achats) {
        this.nbr_achats = nbr_achats;
    }

    @Override
    public String toString() {
        return "top_vente{" + "marque=" + marque + ", prix=" + prix + ", nbr_achats=" + nbr_achats + '}';
    }
   

}
