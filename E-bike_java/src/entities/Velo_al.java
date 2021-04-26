/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.image.ImageView;

/**
 *
 * @author amine
 */
public class Velo_al {
    
    
    private int id;
    private String marque;
    private String Type;
    private int prix;
    private int quantite;
    private String image;
    private ImageView img;

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    

    public Velo_al(int id, String marque, String Type, int prix, int quantite, String image) {
        this.id = id;
        this.marque = marque;
        this.Type = Type;
        this.prix = prix;
        this.quantite = quantite;
        this.image = image;
    }

    public Velo_al() {
    }

    public Velo_al(String marque, String Type, int prix, int quantite, String image) {
        this.marque = marque;
        this.Type = Type;
        this.prix = prix;
        this.quantite = quantite;
        this.image = image;
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
        return Type;
    }

    public void setType(String Type) {
        this.Type = Type;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    
}
