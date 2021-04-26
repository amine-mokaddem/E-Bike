/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Typepr;
import entities.Veloav;
import java.sql.Connection;
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
import javax.swing.JOptionPane;
import tools.MyConnection;

/**
 *
 * @author amine
 */
public class veloav_methode {
  Connection cnx;
    private PreparedStatement ste;
    private ResultSet rs;
    private Statement st;

    public veloav_methode() {
        cnx = MyConnection.getInstance().getConnection();     
    }
   
    public void ajouter_velo(Veloav v){
    
        String req ="INSERT INTO veloav (marque,type,prix,quantite,imagev,description,disponibilite)"+"values (?,?,?,?,?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
            ste.setString(1, v.getMarque());
            ste.setString(2, v.getType());
            ste.setInt(3, v.getPrix());
            ste.setInt(4, v.getQuantite());
            ste.setString(5, v.getImagev());
            ste.setString(6, v.getDescription());
            ste.setString(7, v.getDisponibilite());
            ste.executeUpdate();
            System.out.println("type ajouté");
            
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
            
        }}
        public ObservableList<Veloav> listvelo() throws SQLException{
        ObservableList<Veloav> listvelo = FXCollections.observableArrayList();
         String req = "SELECT * from veloav";        
           st = cnx.createStatement();
           rs= st.executeQuery(req);
           while(rs.next()){
               Veloav v = new Veloav(rs.getInt("id"),rs.getString("marque"),rs.getString("type"),rs.getInt("prix"),rs.getInt("quantite"),rs.getString("imagev"),rs.getString("description"),rs.getString("disponibilite"));
               listvelo.add(v);
           }
           return listvelo; 
  }
        public void Deletevelo(int id){
          try {
              String req = "DELETE from veloav WHERE id =" +id+ " ";
              st = cnx.createStatement();
              st.executeUpdate(req);
              JOptionPane.showMessageDialog(null, "velo supprimé", "", JOptionPane.INFORMATION_MESSAGE);
              System.out.println("velo supprimé");
          } catch (SQLException ex) {
              System.out.println("Probléme");
              Logger.getLogger(EventMethode.class.getName()).log(Level.SEVERE, null, ex);
          }
    }
    
        
        public void editvelo(int id ,Veloav v ) throws SQLException{
    String req =  "UPDATE veloav SET   marque= ? ,type= ?,prix = ?,quantite= ?,imagev= ?,description= ?,disponibilite= ? WHERE id = ?";
         try {
             ste = cnx.prepareStatement(req);
         } catch (SQLException ex) {
             Logger.getLogger(typepr_method.class.getName()).log(Level.SEVERE, null, ex);
         }
         ste.setString(1, v.getMarque());
            ste.setString(2, v.getType());
            ste.setInt(3, v.getPrix());
            ste.setInt(4, v.getQuantite());
            ste.setString(5, v.getImagev());
            ste.setString(6, v.getDescription());
            ste.setString(7, v.getDisponibilite());
            ste.setInt(8, id);
            ste.executeUpdate();
         System.out.println("velo modifié");
         
}
        public int getidvelobymarque(String nom) throws SQLException{
            String req = "SELECT id FROM veloav WHERE marque = '"+nom+"'";
            st = cnx.createStatement();
            rs= st.executeQuery(req);
            int h=0;
            while(rs.next()){
                h = rs.getInt(1);
            }          
            return h;
        
        } 
        
        
         public List<top_vente> getTopVente() throws SQLException {
        String req = "SELECT v.marque as `velo` ,v.prix, COUNT(a.veloav_id) AS `nbr` FROM veloav_commande a LEFT JOIN veloav v ON v.id=a.veloav_id GROUP BY a.veloav_id ORDER BY `nbr` DESC LIMIT 5" ;
        List<top_vente> list=new ArrayList<>();
        st = cnx.createStatement();
            rs=st.executeQuery(req); 
            while(rs.next()){
              top_vente t = new top_vente();
             
                t.setMarque(rs.getString("velo"));
                t.setNbr_achats(rs.getInt("nbr"));
                t.setPrix(rs.getFloat("prix"));
                list.add(t);
                
            }
        return list ;
    }
        
   
        
        
        
        
    
}
    
    
    
    
    
    
    
    

