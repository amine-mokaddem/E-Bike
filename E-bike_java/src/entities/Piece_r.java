/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Connection;
import tools.MyConnection;

/**
 *
 * @author amine
 */
public class Piece_r {
 private int id ;
 private String nom;
 private int prix;
 private int type_id;
 private int quantite;

    public Piece_r() { 
    }

    public Piece_r(int id, String nom, int prix, int type_id, int quantite) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.type_id = type_id;
        this.quantite = quantite;
    }

    public Piece_r(String nom, int prix, int type_id, int quantite) {
        this.nom = nom;
        this.prix = prix;
        this.type_id = type_id;
        this.quantite = quantite;
    }

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

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    
    
    
 
 
 
 
 
 
 
 
 
    
}
