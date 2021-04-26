/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import tools.MyConnection;

/**
 *
 * @author amine
 */
public class user_methode {
    Connection cnx;
    private PreparedStatement ste;
    private ResultSet rs;
    private Statement st;

    public user_methode() {
        cnx = MyConnection.getInstance().getConnection();
    }
    public String Getstripe_from_email(String nom) throws SQLException{
            String req = "SELECT stripeid FROM utilisateur WHERE email = '"+nom+"'";
            st = cnx.createStatement();
            rs= st.executeQuery(req);
            String h=null;
            while(rs.next()){
                h = rs.getString(1);
            }          
            return h;
        
        }
          public void ajouterstripeid( String stripeid, String email) throws SQLException
             
        {
           
          try {
              String req = "UPDATE utilisateur SET stripeid= ? WHERE email =?";
               ste = cnx.prepareStatement(req);
                  ste.setString(1, stripeid);
                  ste.setString(2, email);
                  ste.executeUpdate();
             
          } catch (SQLException ex) {
              System.out.println("Probléme");
              
          }}
          public String verif_user(String password,String username ) throws SQLException{
              String req = "SELECT type FROM utilisateur WHERE username = '"+username+"' and password ='"+password+"'";
            st = cnx.createStatement();
            rs= st.executeQuery(req);
            String h=null;
            while(rs.next()){
                h = rs.getString(1);
            }          
            return h;
          }

          
          public int verif_user_client(String password,String username ) throws SQLException{
              String req = "SELECT type FROM utilisateur WHERE username = '"+username+"' and password ='"+password+"' and type='client'";
            st = cnx.createStatement();
            rs= st.executeQuery(req);
            int h=0;
            while(rs.next()){
                h = rs.getInt(1);
            }          
            return h;
          }
          public int verif_user_admin(String password,String username ) throws SQLException{
              String req = "SELECT id FROM utilisateur WHERE username = '"+username+"' and password ='"+password+"' and type='admin'";
            st = cnx.createStatement();
            rs= st.executeQuery(req);
            int h=0;
            while(rs.next()){
                h = rs.getInt(1);
            }          
            return h;
          }        
             
              
              
              
              
  
          }
                  
          
          
          
          
          
          
         
       

