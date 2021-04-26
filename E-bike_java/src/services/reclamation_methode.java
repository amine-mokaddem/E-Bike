/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Reclamation;
import entities.Veloav;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import tools.MyConnection;

/**
 *
 * @author amine
 */
public class reclamation_methode {
    Connection cnx;
    private PreparedStatement ste;
    private ResultSet rs;
    private Statement st;
    
    
    public reclamation_methode() {
         cnx = MyConnection.getInstance().getConnection();
    }
    
    
    public void ajouter_reclamation(Reclamation r){
    
        String req ="INSERT INTO reclamation (email,datereclamation,description,piecer_id,etat,nom)"+"values (?,?,?,?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
            ste.setString(1, r.getEmail());
            ste.setDate(2, r.getDatereclamation());
            ste.setString(3, r.getDescription());
            ste.setInt(4, r.getPiece_id());
            ste.setString(5, r.getEtat());
            ste.setString(6, r.getNom());   
            ste.executeUpdate();
            System.out.println("reclamation ajouté");
            JOptionPane.showMessageDialog(null, "reclamation ajoutee", "", JOptionPane.INFORMATION_MESSAGE);      
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage()); 
        }
    }
     public void approuverreclamation( Reclamation r) throws SQLException
             
        {
           
          try {
              String req = "UPDATE reclamation SET etat= ? WHERE id =?";
               ste = cnx.prepareStatement(req);
                  ste.setString(1, "acceptée");
                  ste.setInt(2, r.getId());
                  ste.executeUpdate();
              JOptionPane.showMessageDialog(null, "reclamation approuvée", "", JOptionPane.INFORMATION_MESSAGE);
              System.out.println("reclamation approuvée");
          } catch (SQLException ex) {
              System.out.println("Probléme");
              Logger.getLogger(EventMethode.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
     
      public ObservableList<Reclamation> listreclamation() throws SQLException{
        ObservableList<Reclamation> listreclamation = FXCollections.observableArrayList();
         String req = "SELECT * from reclamation";        
           st = cnx.createStatement();
           rs= st.executeQuery(req);
           while(rs.next()){
               Reclamation r = new Reclamation( rs.getInt("id"),rs.getString("email"), rs.getDate("datereclamation") ,  rs.getString("description"),  rs.getInt("piecer_id"),  rs.getString("etat"),  rs.getString("nom"));
               listreclamation.add(r);
           }
           return listreclamation;   
}
}
