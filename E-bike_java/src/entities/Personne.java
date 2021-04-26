/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author House
 */
public class Personne {
    
    private int id;
    private String nom;
    private String prenom;
    private String numtel;
    private String email;

    public Personne() {
    }

    public Personne(int id, String nom, String prenom, String numtel ,String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.email = email;
    }
     public Personne( String nom, String prenom, String numtel ,String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.numtel = numtel;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }
    
    public String getNumtel() {
        return numtel;
    }
    
    public String getEmail() {
        return email;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    public void setNumtel(String numtel) {
        this.numtel = numtel;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    

    @Override
    public String toString() {
        return "Personne{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", numtel=" + numtel + ", email=" + email + '}';
    }
    
    
}
