/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Event;
import entities.Participant;
import java.sql.Connection;
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
public class participant_methode {

    Connection cnx;
    private PreparedStatement ste;
    private ResultSet rs;
    private Statement st;

    public participant_methode() {
        cnx = MyConnection.getInstance().getConnection();
    }

    public ObservableList<Participant> listpart() throws SQLException {
        ObservableList<Participant> listpart = FXCollections.observableArrayList();
        String req = "SELECT * from participant";
        st = cnx.createStatement();
        rs = st.executeQuery(req);
        while (rs.next()) {
            Participant e = new Participant(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getString("email"), rs.getInt("numtel"));
            listpart.add(e);
        }
        return listpart;
    }

    public void ajpar(Participant r) {
        String req = "INSERT INTO participant (nom,prenom,email,numtel,etat)" + "values (?,?,?,?,?)";
        try {

            ste = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
            ste.setString(1, r.getNom());
            ste.setString(2, r.getPrenom());
            ste.setString(3, r.getEmail());
            ste.setInt(4, r.getNumtel());
            ste.setString(5, r.getEtat());

            ste.executeUpdate();

            try (ResultSet generatedKeys = ste.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    r.setId(generatedKeys.getInt(1));
                }
            }

            req = "INSERT INTO participant_event values (?,?)";
            ste = cnx.prepareStatement(req);
            ste.setInt(1, r.getId());
            ste.setInt(2, r.getEvent().getId());
            ste.executeUpdate();

            System.out.println("participant ajouté");
            JOptionPane.showMessageDialog(null, "participant ajoutee", "", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            System.out.println("Probléme");
            System.out.println(ex.getMessage());
        }

    }

}
