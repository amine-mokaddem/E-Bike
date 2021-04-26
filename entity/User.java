/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author wiemhjiri
 */
public class User {

    private SimpleStringProperty prenom;
    private SimpleStringProperty nom;
    private SimpleStringProperty username;
    private SimpleStringProperty mdp;
    private SimpleStringProperty mail;
    private SimpleStringProperty role;
    SimpleIntegerProperty id;

    public User() {
    }

    public User(String prenom, String nom, String username, String mdp, String mail, String role) {
        this.prenom = new SimpleStringProperty(prenom);
        this.nom = new SimpleStringProperty(nom);
        this.username = new SimpleStringProperty(username);
        this.mdp = new SimpleStringProperty(mdp);
        this.mail = new SimpleStringProperty(mail);
        this.role = new SimpleStringProperty(role);

    }

    public User(String prenom, String nom, String username, String mdp, String mail, String role, int id) {
        this.prenom = new SimpleStringProperty(prenom);
        this.nom = new SimpleStringProperty(nom);
        this.username = new SimpleStringProperty(username);
        this.mdp = new SimpleStringProperty(mdp);
        this.mail = new SimpleStringProperty(mail);
        this.role = new SimpleStringProperty(role);
        this.id = new SimpleIntegerProperty(id);

    }

    public User(String username, String mdp) {
        this.username = new SimpleStringProperty(username);
        this.mdp = new SimpleStringProperty(mdp);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username = new SimpleStringProperty(username);
    }

    public String getNom() {
        return nom.get();
    }

    public void setNom(String nom) {
        this.nom = new SimpleStringProperty(nom);
    }

    public SimpleStringProperty getNomProperty() {
        return nom;
    }

    public String getPrenom() {
        return prenom.get();
    }

    public SimpleStringProperty getPrenomProperty() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = new SimpleStringProperty(prenom);
    }

    public String getMail() {
        return mail.get();
    }

    public SimpleStringProperty getMailProperty() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = new SimpleStringProperty(mail);
    }

    public String getMdp() {
        return mdp.get();
    }

    public SimpleStringProperty getMdpProperty() {
        return mdp;
    }

    public SimpleStringProperty getUsernameProperty() {
        return username;
    }

    public void setMdp(String mdp) {
        this.mdp = new SimpleStringProperty(mdp);
    }

    public String getRole() {
        return role.get();
    }

    public SimpleStringProperty getRoleProperty() {
        return role;
    }

    public void setRole(String role) {
        this.role = new SimpleStringProperty(role);
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty getIdProperty() {
        return id;
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.mdp, other.mdp)) {
            return false;
        }
        return true;
    }

}
