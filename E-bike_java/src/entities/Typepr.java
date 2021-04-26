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
public class Typepr {
    private int id;
    private String type; 
    private String fournisseur ;

    public Typepr(int id, String type, String fournisseur) {
        this.id = id;
        this.type = type;
        this.fournisseur = fournisseur;
    }

    public Typepr(String type, String fournisseur) {
        this.type = type;
        this.fournisseur = fournisseur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }
    
    
    
    
}
