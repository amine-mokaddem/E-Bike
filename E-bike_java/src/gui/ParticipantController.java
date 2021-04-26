/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Event;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.EventMethode;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class ParticipantController implements Initializable {

    @FXML
    private TableColumn<Event, String> nom;
    private TableColumn<Event, Integer> nbrplace;
      @FXML
    private TableColumn<Event, String> depart;
    private TableColumn<Event, String> arivee;
    @FXML
    private TableColumn<Event, Date> date_allee;
    @FXML
    private TableColumn<Event, Date> dateretour;
    @FXML
    private TableColumn<Event, String> description;
    @FXML
    private TableColumn<Event, String> image;
    @FXML
    private TextField emailp;
    @FXML
    private TextField prenomp;
    @FXML
    private TextField nomp;
    @FXML
    private Spinner<Integer> numtel;
    @FXML
    private TableView<Event> tableevent;
    @FXML
    private TableColumn<Event, Integer> nbr;
    @FXML
    private TableColumn<Event, String> ariveee;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            event_list();
        } catch (SQLException ex) {
            Logger.getLogger(ParticipantController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
     public void event_list() throws SQLException {
        EventMethode em = new EventMethode();
        ObservableList<Event> eventlist = em.listEvent();
        tableevent.setItems(eventlist);
        nom.setCellValueFactory(new PropertyValueFactory<Event, String>("nom"));
        nbr.setCellValueFactory(new PropertyValueFactory<Event, Integer>("nbr_place"));
        ariveee.setCellValueFactory(new PropertyValueFactory<Event, String>("arivee"));
        depart.setCellValueFactory(new PropertyValueFactory<Event, String>("depart"));
        date_allee.setCellValueFactory(new PropertyValueFactory<Event, Date>("date_allee"));
        dateretour.setCellValueFactory(new PropertyValueFactory<Event, Date>("date_retour"));
        description.setCellValueFactory(new PropertyValueFactory<Event, String>("description"));
     }
    
  
    
    
    
    
    
    
    
    
    
}
