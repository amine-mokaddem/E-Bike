/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.User;

import java.security.Key;
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
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import ilearn.Database;

/**
 *
 * @author wiemhjiri
 */
public class UserDao implements Idao<User> {

    private static UserDao instance;
    private Statement st;
    private ResultSet rs;
    Connection myConnex;

    public UserDao() {
        Database cs = Database.getInstance();
        try {
            st = cs.getConnection().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }

    @Override
    public void insert(User o) {
        String req = "insert into user (prenom,nom,username,mdp,mail,role) values ('" + o.getPrenom() + "','" + o.getNom() + "','" + o.getUsername() + "','" + o.getMdp() + "','" + o.getMail() + "','" + o.getRole() + "')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(User o) {
        String req = "delete from user where id=" + o.getId();
        User p = displayById(o.getId());

        if (p != null) {
            try {

                st.executeUpdate(req);

            } catch (SQLException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("n'existe pas");
        }
    }

    @Override
    public ObservableList<User> displayAll() {
        String req = "select * from user";
        ObservableList<User> list = FXCollections.observableArrayList();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                User p = new User();
                p.setPrenom(rs.getString(1));
                p.setNom(rs.getString("nom"));
                p.setUsername(rs.getString(3));
                p.setMdp(rs.getString(4));
                p.setMail(rs.getString(5));
                p.setRole(rs.getString(6));
                p.setId(rs.getInt(7));
                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<User> displayAllList() {
        String req = "select * from user";
        List<User> list = new ArrayList<>();

        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                User p = new User();
                p.setPrenom(rs.getString(1));
                p.setNom(rs.getString("nom"));
                p.setUsername(rs.getString(3));
                p.setMdp(rs.getString(4));
                p.setMail(rs.getString(5));
                p.setRole(rs.getString(6));
                p.setId(rs.getInt(7));

                list.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public User displayById(int id) {

        String req = "select * from user where id =" + id;
        User p = new User();
        try {
            rs = st.executeQuery(req);
            // while(rs.next()){
            rs.next();
            p.setPrenom(rs.getString(1));
            p.setNom(rs.getString("nom"));
            p.setUsername(rs.getString(3));
            p.setMdp(rs.getString(4));
            p.setMail(rs.getString(5));
            p.setRole(rs.getString(6));
            p.setId(rs.getInt(7));

            //}  
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    /* public User displayByEmail(String email) {
        
           String req="select * from user where mail ="+email;
           User p=new User();
        try {
            rs=st.executeQuery(req);
           // while(rs.next()){
            rs.next();
                           p.setPrenom(rs.getString(1));
                p.setNom(rs.getString("nom"));
                  p.setUsername(rs.getString(3));
                    p.setMdp(rs.getString(4));
                      p.setMail(rs.getString(5));
                                   p.setRole(rs.getString(6));
                                       p.setId(rs.getInt(7));

            //}  
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("hahah");
        }
    return p;
    }*/
    public User displayUserByEmail(String mail) throws SQLException {

        String sql = "SELECT * FROM user WHERE mail ='" + mail + "' ";
        User u = new User();
        try {

            rs = st.executeQuery(sql);
            while (rs.next()) {

                u.setPrenom(rs.getString(1));
                System.out.println(rs.getString(1));
                u.setNom(rs.getString(2));
                u.setUsername(rs.getString(3));
                u.setMdp(rs.getString(4));
                System.out.println(rs.getString(4));

                u.setMail(rs.getString(5));
                u.setRole(rs.getString(6));
                u.setId(rs.getInt(7));

            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("hahah");
        }

        return u;
    }

    public Integer countEtud() {
        String etud = "etudiant";
        String sql = "SELECT * FROM user WHERE role = '" + etud + "' ";
        int count = 0;
        try {

            rs = st.executeQuery(sql);
            while (rs.next()) {
                count++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("hahah");
        }

        return count;
    }

    public Integer countprof() {
        String prof = "professor";
        String sql = "SELECT * FROM user WHERE role = '" + prof + "' ";
        int count = 0;
        try {

            rs = st.executeQuery(sql);
            while (rs.next()) {
                count++;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("hahah");
        }

        return count;
    }

    @Override
    public boolean update(User p) {
        String qry = "UPDATE user SET prenom = '" + p.getPrenom() + "', nom = '" + p.getNom() + "', username = '" + p.getUsername() + "', mdp = '" + p.getMdp() + "', mail = '" + p.getMail() + "', role = '" + p.getRole() + "', id = '" + p.getId() + "' WHERE id = " + p.getId();

        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int connex(User p) {
        int l = 7;
        int id=0;
        try {

            String sql = "SELECT * FROM user WHERE username ='" + p.getUsername() + "' AND mdp ='" + p.getMdp() + "'";
            rs = st.executeQuery(sql);

            if (rs.next()) {
               id=Integer.parseInt(rs.getString("id"));
                       
                if ("admin".equals(rs.getString("role"))) {
                    l = 0;
                }
                if ("professor".equals(rs.getString("role"))) {
                    l = 1;
                }
                if ("etudiant".equals(rs.getString("role"))) {
                    l = 2;
                }
                System.out.println("succes");

            } else {
                System.out.println("not connected ");

            }
              String qry = "UPDATE conn SET emm = "+id+" WHERE etat = 'conn'";
                 st.executeUpdate(qry);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return l;

    }

    public String encrypt(String password) {
        String crypte = "";
        for (int i = 0; i < password.length(); i++) {
            int c = password.charAt(i) + 48;
            crypte = crypte + (char) c;
        }
        return crypte;
    }

    public String decrypt(String password) {
        String aCrypter = "";
        for (int i = 0; i < password.length(); i++) {
            int c = password.charAt(i) - 48;
            aCrypter = aCrypter + (char) c;
        }
        return aCrypter;
    }
     public User getconnected() throws SQLException {
        int id=0;
         String req1 = "select * from conn where etat = 'conn' " ;
         rs = st.executeQuery(req1);
          rs.next();
          id=Integer.parseInt(rs.getString("emm"));
        String req = "select * from user where id =" + id;
        User p = new User();
        try {
            rs = st.executeQuery(req);
            // while(rs.next()){
            rs.next();
            p.setPrenom(rs.getString(1));
            p.setNom(rs.getString("nom"));
            p.setUsername(rs.getString(3));
            p.setMdp(rs.getString(4));
            p.setMail(rs.getString(5));
            p.setRole(rs.getString(6));
            p.setId(rs.getInt(7));

            //}  
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

}
