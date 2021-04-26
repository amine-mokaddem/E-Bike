

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import ilearn.Database;

import entity.Promotion;
import entity.event;
import static com.sun.deploy.security.BlockedDialog.show;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import entity.performance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author user
 */
public  class PerformanceDao {
    
   private static PerformanceDao instance;
    private Statement st;
    private ResultSet rs;
    Connection myConnex;
    //cnxBD myc = cnxBD.myc.getIstance();
    Connection cnx = Database.getInstance().getConnection();

    public PerformanceDao() {
        Database cs = Database.getInstance();
        try {
            st = cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(PerformanceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static PerformanceDao getInstance() {
        if (instance == null) {
            instance = new PerformanceDao();
        }
        return instance;
    }
  
  
    public void insert(performance o) {
        String req="insert into performance (depart,arivee,distance,temps) values ('"+o.getDepart()+"','"+o.getArivee()+"','"+o.getDistance()+"','"+o.getTemps()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(PerformanceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    
    public void delete(performance o) {
        String req="delete from performance where id="+o.getId();
        performance p=displayById(o.getId());
        
          if(p!=null)
              try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(PerformanceDao.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    
    public ObservableList<performance> displayAll() {
        String req="select * from performance";
        ObservableList<performance> list=FXCollections.observableArrayList();       
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                performance p=new performance();
                         p.setDepart(rs.getString(1));
                p.setArivee(rs.getString(2));
                  p.setDistance(rs.getString(3));
                    p.setTemps(rs.getString(4));
                     
                                       p.setId(rs.getInt(7));
                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PerformanceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<performance> displayAllList() {
        String req="select * from performance";
        List<performance> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                performance p=new performance();
                        p.setDepart(rs.getString(1));
                p.setArivee(rs.getString(2));
                  p.setDistance(rs.getString(3));
                    p.setTemps(rs.getString(4));
                     
                                       p.setId(rs.getInt(7));

                list.add(p);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PerformanceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
   
    public performance displayById(int id) {
        
           String req="select * from performance where id ="+id;
           performance p=new performance();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                          p.setDepart(rs.getString(1));
                p.setArivee(rs.getString(2));
                  p.setDistance(rs.getString(3));
                    p.setTemps(rs.getString(4));
                     
                                       p.setId(rs.getInt(7));

            //}  
        } catch (SQLException ex) {
            Logger.getLogger(PerformanceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    return p;
    }

    
    public boolean update(performance p) {
        String qry = "UPDATE performance SET depart = '"+p.getDepart()+"', arivee = '"+p.getArivee()+"', distance = '"+p.getDistance()+"', temps = '"+p.getTemps()+"', id = '"+p.getId()+"' WHERE id = "+p.getId();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(PerformanceDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private static class Performance {

        public Performance() {
        }
    }
     

    
    
}


