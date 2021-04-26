/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import gui.AffichageCoachController;
import edu.db3a4.interfaces.Icours;
import entities.cours;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
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
public class coursCRUD implements Icours<cours>{
    public ObservableList<cours> observableListLocataire = FXCollections.observableArrayList();

    public void ajoutercours(cours t) {
        try {
            String requete = "INSERT INTO cours (circuitcours,datecours,dureecours,frais,typecours,nombremax,nombreparticip)"
                    + "VALUES ('"+t.getCircuitcours()+"','"+t.getDatecours()+"','"+t.getDureecours()+"','"+t.getFrais()+"','"+t.getTypecours()+"','"+t.getNombremax()+"','"+t.getNombreparticip()+"')";
            Statement st = MyConnection.getInstance().getConnection()
                    .createStatement();
            st.executeUpdate(requete);
            System.out.println("cour ajouté");
            
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

    public void supprimerPersonne(cours t) {
     
            try {
           String requete = "DELETE FROM cours where id=?";
            PreparedStatement pst = MyConnection.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setInt(1, t.getId());
            pst.executeUpdate();
            System.out.println("cours supprimée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }

    public void updatecours(int id, String circuitcours, Date datecours , String dureecours  ,Integer frais, String typecours, Integer nombremax, Integer nombreparticip) {
        try {
            PreparedStatement pst = MyConnection.getInstance().getConnection()
                    .prepareStatement("UPDATE cours SET circuitcours = '"+circuitcours+"', datecours = '"+datecours+"', dureecours = '"+dureecours+"', frais = '"+frais+"', typecours = '"+typecours+"', nombremax = '"+nombremax+"', nombreparticip = '"+nombreparticip+"' +' WHERE id = '"+id+"'");
            pst.executeUpdate();
            System.out.println("cours modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

   public ObservableList<cours> displaycours() {
          try {
            
            String requete = "SELECT * from cours";
            Statement st;
            st = MyConnection.getInstance().getConnection()
                    .createStatement();
             ResultSet rs =  st.executeQuery(requete);
            while(rs.next()){
                
                observableListLocataire.add( new cours(rs.getInt(1),rs.getString(2),rs.getDate(3).toLocalDate(),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8)));
            }
            }
         catch (SQLException ex) {
            Logger.getLogger(AffichageCoachController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          return observableListLocataire;
        
    }

    @Override
    public void supprimercours(cours t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void updatecours(Integer t, String a, LocalDate b, String c, String d, String e, String f, String g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updatecours(Integer t, String a, Date b, String c, String d, String e, String f, String g) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
