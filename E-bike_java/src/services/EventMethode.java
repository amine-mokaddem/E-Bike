/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.sql.Date;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import entities.Event;
import entities.Reclamation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tools.MyConnection;

/**
 *
 * @author amine
 */
public class EventMethode {

    Connection cnx;
    private PreparedStatement ste;
    private ResultSet rs;
    private Statement st;

    public EventMethode() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public void AddEventA(Event e) {

        String req = "INSERT INTO event ( nom, nbr_place, depart, arivee,date_allee,date_retour,description, image)" + "values (?,?,?,?,?,?,?,?)";
        try {
            ste = cnx.prepareStatement(req);
            ste.setString(1, e.getNom());
            ste.setInt(2, e.getNbr_place());
            ste.setString(3, e.getDepart());
            ste.setString(4, e.getArivee());
            ste.setDate(5, (Date) e.getDate_allee());
            ste.setDate(6, (Date) e.getDate_retour());
            //ste.setDate(6, (Date) e.getDate_retour());
            //ste.setInt(5, e.getNb_participant());
            ste.setString(7, e.getDescription());
            ste.setString(8, e.getImage());
            //ste.setInt(10, e.getCode_artiste());
            ste.executeUpdate();
            System.out.println("Event ajouté");

        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());

        }

    }

    public ObservableList<Event> listEvent() throws SQLException {
        ObservableList<Event> eventlist = FXCollections.observableArrayList();
        String req = "SELECT * from event";
        st = cnx.createStatement();
        rs = st.executeQuery(req);
        while (rs.next()) {
            Event e = new Event(rs.getInt("id"), rs.getString("nom"), rs.getInt("nbr_place"), rs.getString("depart"), rs.getString("arivee"), rs.getDate("Date_allee"), rs.getDate("Date_retour"), rs.getString("description"), rs.getString("image"));
            eventlist.add(e);
        }
        return eventlist;
    }

    public List<Event> Liste() throws SQLException {
        List<Event> eventlist = new ArrayList<>();

        String req = "SELECT * from event";
        st = cnx.createStatement();
        rs = st.executeQuery(req);
        while (rs.next()) {
            Event e = new Event(rs.getInt("id"), rs.getString("nom"), rs.getInt("nbr_place"), rs.getString("depart"), rs.getString("arivee"), rs.getDate("Date_allee"), rs.getDate("Date_retour"), rs.getString("description"), rs.getString("image"));
            eventlist.add(e);
        }
        return eventlist;
    }

    public int listEventplace() throws SQLException {
        ObservableList<Event> eventlist = FXCollections.observableArrayList();
        String req = "SELECT * from event";
        st = cnx.createStatement();
        rs = st.executeQuery(req);
        while (rs.first()) {
            Event e = new Event(rs.getInt("id"), rs.getString("nom"), rs.getInt("nbr_place"), rs.getString("depart"), rs.getString("arivee"), rs.getDate("Date_allee"), rs.getDate("Date_retour"), rs.getString("description"), rs.getString("image"));
            eventlist.add(e);
        }
        return rs.getInt("nbr_place");
    }

    public int getNbPlaceRest(int eventId) throws SQLException {
        String req = "SELECT nbr_place - (select count(*) from participant_event where event_id = # ) FROM event WHERE id = #".replaceAll("#", eventId + "");
        st = cnx.createStatement();
        rs = st.executeQuery(req);
        int h = 0;
        while (rs.next()) {
            h = rs.getInt(1);
        }
        return h;
    }

    public String listEventname() throws SQLException {
        ObservableList<Event> eventlist = FXCollections.observableArrayList();
        String req = "SELECT * from event";
        st = cnx.createStatement();
        rs = st.executeQuery(req);
        while (rs.first()) {
            Event e = new Event(rs.getInt("id"), rs.getString("nom"), rs.getInt("nbr_place"), rs.getString("depart"), rs.getString("arivee"), rs.getDate("Date_allee"), rs.getDate("Date_retour"), rs.getString("description"), rs.getString("image"));
            eventlist.add(e);
        }
        return rs.getString("nom");
    }

    public void DeleteEvent(int id) {
        try {
            String req = "DELETE from event WHERE id =" + id + " ";
            st = cnx.createStatement();
            st.executeUpdate(req);
            JOptionPane.showMessageDialog(null, "Evènement supprimé", "", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("Event supprimé");
        } catch (SQLException ex) {
            System.out.println("Probléme");
            Logger.getLogger(EventMethode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifyevent(Event e) throws SQLException {

        try {
            String req = "UPDATE event SET nom= ? , nbr_place= ?,depart= ?,arivee= ?,description= ?,image= ? WHERE id =?";
            ste = cnx.prepareStatement(req);
            ste.setString(1, e.getNom());
            ste.setString(3, e.getDepart());
            ste.setString(4, e.getArivee());
            ste.setInt(2, e.getNbr_place());
            ste.setString(5, e.getDescription());
            ste.setString(6, e.getImage());

            ste.setInt(7, e.getId());
            ste.executeUpdate();
            JOptionPane.showMessageDialog(null, "reclamation approuvée", "", JOptionPane.INFORMATION_MESSAGE);
            System.out.println("reclamation approuvée");
        } catch (SQLException ex) {
            System.out.println("Probléme");
            Logger.getLogger(EventMethode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
