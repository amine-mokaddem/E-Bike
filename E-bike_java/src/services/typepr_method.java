/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Event;
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
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import tools.MyConnection;

/**
 *
 * @author amine
 */
public class typepr_method {
    Connection cnx;
    private PreparedStatement ste;
    private ResultSet rs;
    private Statement st;
    

    public typepr_method() {
        cnx = MyConnection.getInstance().getConnection();
    }
    
    public void ajoutertype(Typepr t){
    
        String req ="INSERT INTO typepr (type,fournisseur)"+"values (?,?)";
        try {
            ste = cnx.prepareStatement(req);
            ste.setString(1, t.getType());
            ste.setString(2, t.getFournisseur());
            ste.executeUpdate();
            System.out.println("type ajouté");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }
    
}

public ObservableList<Typepr> listtype() throws SQLException{
        ObservableList<Typepr> listtype = FXCollections.observableArrayList();
         String req = "SELECT * from typepr";        
           st = cnx.createStatement();
           rs= st.executeQuery(req);
           while(rs.next()){
               Typepr t = new Typepr(rs.getInt("id"),rs.getString("type"),rs.getString("fournisseur"));
               listtype.add(t);
           }
           return listtype; 
  }

public ObservableList<String> combcox_piece() throws SQLException {
     ObservableList<String> listtype = FXCollections.observableArrayList();
             String req = "SELECT * from typepr";        
           st = cnx.createStatement();
           rs= st.executeQuery(req);
     while(rs.next()){
        listtype.add(rs.getString("type")); 
     }
     return listtype;
}





public void Deletetype(int id){
          try {
              String req = "DELETE from typepr WHERE id =" +id+ " ";
              st = cnx.createStatement();
              st.executeUpdate(req);
              JOptionPane.showMessageDialog(null, "type supprimé", "", JOptionPane.INFORMATION_MESSAGE);
              System.out.println("type supprimé");
          } catch (SQLException ex) {
              System.out.println("Probléme");
              Logger.getLogger(EventMethode.class.getName()).log(Level.SEVERE, null, ex);
          }
    }



public void edittype(int id ,Typepr t ) throws SQLException{
    String req =  "UPDATE typepr SET type = ?, fournisseur = ? WHERE id = ?";
         try {
             ste = cnx.prepareStatement(req);
         } catch (SQLException ex) {
             Logger.getLogger(typepr_method.class.getName()).log(Level.SEVERE, null, ex);
         }
         ste.setString(1,t.getType());
         ste.setString(2,t.getFournisseur()); 
         ste.setInt(3, id);
         ste.executeUpdate();
         System.out.println("type modifié");
         
}

public ObservableList<String> setpiece_type(int ID) throws SQLException {
     ObservableList<String> listpiece = FXCollections.observableArrayList();
             String req = "SELECT * from piece_r where type_id =" +ID+ "  ";        
           st = cnx.createStatement();
           rs= st.executeQuery(req);
     while(rs.next()){
        listpiece.add(rs.getString("nom")); 
     }
     return listpiece;
}

public int getIDfromTYPE(String nom) throws SQLException{
            String req = "SELECT id FROM typepr WHERE type = '"+nom+"'";
            st = cnx.createStatement();
            rs= st.executeQuery(req);
            int h=0;
            while(rs.next()){
                h = rs.getInt(1);
            }          
            return h;
        
        } 








}
