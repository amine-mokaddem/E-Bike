/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Velo_al;
import gui.List_velo_frontController;
import java.sql.Connection;
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
public class veloal_methode {
    
    
     Connection cnx;
    private PreparedStatement ste;
    private ResultSet rs;
    private Statement st;

    public veloal_methode() {
        cnx = MyConnection.getInstance().getConnection();     
    }
   
    public void ajouter_veloal(Velo_al v){
    
        String req ="INSERT INTO velo_al (marque,type,prix,quantite,image)"+"values (?,?,?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
            ste.setString(1, v.getMarque());
            ste.setString(2, v.getType());
            ste.setInt(3, v.getPrix());
            ste.setInt(4, v.getQuantite());
            ste.setString(5, v.getImage());
            ste.executeUpdate();
            System.out.println("velo ajouté");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }}
    
    
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
        
        
        public void Deleteveloal(int id){
          try {
              String req = "DELETE from velo_al WHERE id =" +id+ " ";
              st = cnx.createStatement();
              st.executeUpdate(req);
              JOptionPane.showMessageDialog(null, "velo supprimé", "", JOptionPane.INFORMATION_MESSAGE);
              System.out.println("velo supprimé");
          } catch (SQLException ex) {
              System.out.println("Probléme");
              Logger.getLogger(EventMethode.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
        
        public void editveloal(int id ,Velo_al v ) throws SQLException{
    String req =  "UPDATE velo_al SET   marque= ? ,type= ?,prix = ?,quantite= ?,image= ? WHERE id = ?";
         try {
             ste = cnx.prepareStatement(req);
         } catch (SQLException ex) {
             Logger.getLogger(typepr_method.class.getName()).log(Level.SEVERE, null, ex);
         }
         ste.setString(1, v.getMarque());
            ste.setString(2, v.getType());
            ste.setInt(3, v.getPrix());
            ste.setInt(4, v.getQuantite());
            ste.setString(5, v.getImage());
            ste.setInt(6, id);
            ste.executeUpdate();
         System.out.println("velo modifié");
         
}
        
   
        
        
        
        
        public ObservableList<Velo_al> Recher(String type) throws SQLException {
     
  
   ObservableList<Velo_al> listvelo = FXCollections.observableArrayList();
         String req = "SELECT * from velo_al where type LIKE '" + type + "%'";        
           st = cnx.createStatement();
           rs= st.executeQuery(req);
           while(rs.next()){
               Velo_al v = new Velo_al(rs.getInt("id"),rs.getString("marque"),rs.getString("type"),rs.getInt("prix"),rs.getInt("quantite"),rs.getString("image"));
               listvelo.add(v);
           }
           return listvelo; 
     }
        
        
        
                public ObservableList<Velo_al> Trier() throws SQLException {
     
  
   ObservableList<Velo_al> listvelo = FXCollections.observableArrayList();
         String req = "SELECT * from velo_al  order by  prix";      
           st = cnx.createStatement();
           rs= st.executeQuery(req);
           while(rs.next()){
               Velo_al v = new Velo_al(rs.getInt("id"),rs.getString("marque"),rs.getString("type"),rs.getInt("prix"),rs.getInt("quantite"),rs.getString("image"));
               listvelo.add(v);
           }
           return listvelo; 
     }
                
                
                
                
                
                public ObservableList<Velo_al> getAllp(float p) throws SQLException{
        ObservableList<Velo_al> listvelo = FXCollections.observableArrayList();
         String req = "SELECT * from velo_al where p.prix '"+p+"' ";   
           st = cnx.createStatement();
           rs= st.executeQuery(req);
           while(rs.next()){
               Velo_al v = new Velo_al(rs.getInt("id"),rs.getString("marque"),rs.getString("type"),rs.getInt("prix"),rs.getInt("quantite"),rs.getString("image"));
               listvelo.add(v);
           }
           return listvelo; 
      }
        
        
         public int getMax() {
        String req = "select max(prix) from velo_al";
        int a=-1;
        try {
           st = cnx.createStatement();
           rs= st.executeQuery(req);
   
           while(rs.next()){
              a= rs.getInt(1);
           }
           return a;
        } catch (SQLException ex) {
            Logger.getLogger(List_velo_frontController.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }
    
}
    
    
    

