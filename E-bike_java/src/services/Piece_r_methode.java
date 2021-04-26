/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Event;
import entities.Piece_r;
import entities.Typepr;
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
public class Piece_r_methode {
private PreparedStatement ste;
    private ResultSet rs;
    private Statement st;
    private Connection cnx;
    public Piece_r_methode() {
         cnx = MyConnection.getInstance().getConnection();
    }
    
      public void ajouterpiece(Piece_r p){
    
        String req ="INSERT INTO Piece_r (nom,prix,type_id,quantite)"+"values (?,?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
            ste.setString(1, p.getNom());
            ste.setInt(2, p.getPrix());
            ste.setInt(3, p.getType_id());
            ste.setInt(4, p.getQuantite());
            ste.executeUpdate();
            System.out.println("type ajouté");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }}

 public int GetIdFromtype(String type) throws SQLException{
            String req = "SELECT id FROM typepr WHERE type = '"+type+"'";
            st = cnx.createStatement();
            rs= st.executeQuery(req);
            int h=0;
            while(rs.next()){
                h = rs.getInt(1);
            }          
            return h;
        
        }
 public ObservableList<Piece_r> listpiece() throws SQLException{
        ObservableList<Piece_r> listpiece = FXCollections.observableArrayList();
         String req = "SELECT * from Piece_r";        
           st = cnx.createStatement();
           rs= st.executeQuery(req);
           while(rs.next()){               
               Piece_r p = new Piece_r(rs.getInt("id"),rs.getString("nom"),rs.getInt("prix"),rs.getInt("type_id"),rs.getInt("quantite"));
               listpiece.add(p);
           }
           return listpiece; 
  }
 
 
 public void Deletepiece(int id){
          try {
              String req = "DELETE from piece_r WHERE id =" +id+ " ";
              st = cnx.createStatement();
              st.executeUpdate(req);
              JOptionPane.showMessageDialog(null, "type supprimé", "", JOptionPane.INFORMATION_MESSAGE);
              System.out.println("type supprimé");
          } catch (SQLException ex) {
              System.out.println("Probléme");
              Logger.getLogger(EventMethode.class.getName()).log(Level.SEVERE, null, ex);
          }
    } 
 
 public ObservableList<String> combcox_nompiece() throws SQLException {
     ObservableList<String> listpiece = FXCollections.observableArrayList();
             String req = "SELECT * from piece_r";        
           st = cnx.createStatement();
           rs= st.executeQuery(req);
     while(rs.next()){
        listpiece.add(rs.getString("nom")); 
     }
     return listpiece;
}
public int GetIdFrompiece(String nom) throws SQLException{
            String req = "SELECT id FROM piece_r WHERE nom = '"+nom+"'";
            st = cnx.createStatement();
            rs= st.executeQuery(req);
            int h=0;
            while(rs.next()){
                h = rs.getInt(1);
            }          
            return h;
        
        } 
 
 
 
 
 
 
}




    
    
    
    
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
    

