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
public class location {
    int id;
    int idvelol_id;
    Date datedeb;
    Date datefin;
    String nom;
    int duree;
    String email;

    public location(int id, int idvelol_id, Date datedeb, Date datefin, String nom, int duree, String email) {
        this.id = id;
        this.idvelol_id = idvelol_id;
        this.datedeb = datedeb;
        this.datefin = datefin;
        this.nom = nom;
        this.duree = duree;
        this.email = email;
    }

    public location(int idvelol_id, Date datedeb, Date datefin, String nom, int duree, String email) {
        this.idvelol_id = idvelol_id;
        this.datedeb = datedeb;
        this.datefin = datefin;
        this.nom = nom;
        this.duree = duree;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdvelol_id() {
        return idvelol_id;
    }

    public void setIdvelol_id(int idvelol_id) {
        this.idvelol_id = idvelol_id;
    }

    public Date getDatedeb() {
        return datedeb;
    }

    public void setDatedeb(Date datedeb) {
        this.datedeb = datedeb;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
