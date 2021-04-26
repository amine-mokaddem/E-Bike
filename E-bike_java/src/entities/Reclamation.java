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
public class Reclamation {
    private int id;
    private String email;
    private Date datereclamation;
    private String description;
    private int piece_id;
    private String etat;
    private String nom;

    public Reclamation() {
    }

    public Reclamation(int id, String email, Date datereclamation, String description, int piece_id, String etat, String nom) {
        this.id = id;
        this.email = email;
        this.datereclamation = datereclamation;
        this.description = description;
        this.piece_id = piece_id;
        this.etat = etat;
        this.nom = nom;
    }

    public Reclamation(String email, Date datereclamation, String description, int piece_id, String etat, String nom) {
        this.email = email;
        this.datereclamation = datereclamation;
        this.description = description;
        this.piece_id = piece_id;
        this.etat = etat;
        this.nom = nom;
    }

   

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDatereclamation() {
        return datereclamation;
    }

    public void setDatereclamation(Date datereclamation) {
        this.datereclamation = datereclamation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPiece_id() {
        return piece_id;
    }

    public void setPiece_id(int piece_id) {
        this.piece_id = piece_id;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", email=" + email + ", datereclamation=" + datereclamation + ", description=" + description + ", piece_id=" + piece_id + ", etat=" + etat + ", nom=" + nom + '}';
    }
    
    
    
}
