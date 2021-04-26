/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Velo_al;
import entities.location;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author amine
 */
public class location_methode {
    
     Connection cnx;
    private PreparedStatement ste;
    private ResultSet rs;
    private Statement st;
    
      public ObservableList<Velo_al> listvelo() throws SQLException{
        ObservableList<Velo_al> listvelo = FXCollections.observableArrayList();
         String req = "SELECT * from velo_al";        
           st = cnx.createStatement();
           rs= st.executeQuery(req);
           while(rs.next()){
               Velo_al v = new Velo_al(rs.getInt("id"),rs.getString("marque"),rs.getString("type"),rs.getInt("prix"),rs.getInt("quantite"),rs.getString("image"));
               listvelo.add(v);
           }
           return listvelo; 
      }
      
      
    public void ajouterlocation(location c){
        
        String req ="INSERT INTO location (datedeb,datefin,nom,duree,email)"+"values (?,?,?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
            ste.setDate(1, c.getDatedeb());
            ste.setDate(2, c.getDatefin());
            ste.setString(3, c.getNom());
            ste.setInt(4, c.getDuree());
            ste.setString(5, c.getEmail());
              
            ste.executeUpdate();
                
        }   catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage()); 
        }     
    }
    

    
    

}
