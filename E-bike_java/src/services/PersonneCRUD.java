/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import gui.AffichageCoachController;
import edu.db3a4.interfaces.IPersonne;
import entities.Personne;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author House
 */
public class PersonneCRUD implements IPersonne<Personne>{
    public ObservableList<Personne> observableListLocataire = FXCollections.observableArrayList();

    @Override
    public void ajouterPersonne(Personne t) {
        try {
            String requete = "INSERT INTO coach (nom,prenom,numtel,email)"
                    + "VALUES ('"+t.getNom()+"','"+t.getPrenom()+"','"+t.getNumtel()+"','"+t.getEmail()+"')";
            Statement st = MyConnection.getInstance().getConnection()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("coach ajoutée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    
    }
    
   /* public void ajouterPersonne2(Personne t){
        try {
            String requete= "INSERT INTO personne(nom, prenom)"
                    + "VALUES (?,?)";
            PreparedStatement pst = MyConnection.getInstance().getCnx()
                    .prepareStatement(requete);
            pst.setString(1, t.getNom());
            pst.setString(2, t.getPrenom());
            pst.executeUpdate();
            System.out.println("Personne inserée");
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    } */

    @Override
    public void supprimerPersonne(Personne t) {
     
            try {
           String requete = "DELETE FROM coach where id=?";
            PreparedStatement pst = MyConnection.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("Personne supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    public void updateTerrain(Integer id, String nomT, String prenom, String num, String email) {
        try {
            PreparedStatement pst = MyConnection.getInstance().getConnection()
                    .prepareStatement("UPDATE coach SET nom = '"+nomT+"', prenom = '"+prenom+"', numtel = '"+num+"', email = '"+email+"' WHERE id = '"+id+"'");
            pst.executeUpdate();
            System.out.println("coach modifiée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
   public ObservableList<Personne> displayTerrain() {
          try {
            
            String requete = "SELECT * from coach";
            Statement st;
            st = MyConnection.getInstance().getConnection()
                    .createStatement();
             ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                
                observableListLocataire.add( new Personne(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5)));
            }
            }
         catch (SQLException ex) {
            Logger.getLogger(AffichageCoachController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          return observableListLocataire;
        
    }
    
}
