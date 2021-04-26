/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Veloav;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author amine
 */
public class veloav_front_methode {
    
    Connection cnx;
    private PreparedStatement ste;
    private ResultSet rs;
    private Statement st;

    public veloav_front_methode() {
        cnx = MyConnection.getInstance().getConnection();     
    }
    
    
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
}
