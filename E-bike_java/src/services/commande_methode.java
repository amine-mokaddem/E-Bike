/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commande;
import entities.Veloav;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import tools.MyConnection;

/**
 *
 * @author amine
 */
public class commande_methode {
    Connection cnx;
    private PreparedStatement ste;
    private ResultSet rs;
    private Statement st;

    public commande_methode() {
         cnx = MyConnection.getInstance().getConnection();           
    }
    
    public void ajoutercommande(Commande c){
        
        String req ="INSERT INTO Commande (adresse,numtel,nom,email,etat,qunatite,datecom)"+"values (?,?,?,?,?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
            ste.setString(1, c.getAdresse());
            ste.setInt(2, c.getNumtel());
            ste.setString(3, c.getNom());
            ste.setString(4, c.getEmail());
            ste.setString(5, c.getEtat());
            ste.setInt(6, c.getQuantité());  
            ste.setDate(7, c.getDatecom());   
            
            ste.executeUpdate();
                
        }   catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage()); 
        }     
    }
    public int getidcommandebymail(String nom) throws SQLException{
            String req = "SELECT id FROM commande WHERE email = '"+nom+"'";
            st = cnx.createStatement();
            rs= st.executeQuery(req);
            int h=0;
            while(rs.next()){
                h = rs.getInt(1);
            }          
            return h;
        
        } 

    
    
    public void commandetable(int idv,int idc){
        
        String req ="INSERT INTO veloav_commande (veloav_id,commande_id)"+"values (?,?)";
        try {
            ste = cnx.prepareStatement(req);
            ste.setInt(1,idv);
            ste.setInt(2, idc);
            
            
            ste.executeUpdate();
                
        }   catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage()); 
        }     
    }  
    
     public ObservableList<Commande> listcommande() throws SQLException{
        ObservableList<Commande> listcom = FXCollections.observableArrayList();
         String req = "SELECT * from commande";        
           st = cnx.createStatement();
           rs= st.executeQuery(req);
           while(rs.next()){
               Commande c = new Commande(rs.getInt("id"),rs.getString("adresse"),rs.getInt("numtel"),rs.getString("nom"),rs.getString("email"),rs.getString("etat"),rs.getInt("qunatite"),rs.getDate("datecom"));
               listcom.add(c);
           }
           return listcom; 
}
}
