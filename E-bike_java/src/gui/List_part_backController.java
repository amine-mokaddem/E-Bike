/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Event;
import entities.Participant;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import services.EventMethode;
import services.participant_methode;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class List_part_backController implements Initializable {

    @FXML
    private AnchorPane participant;
    @FXML
    private Button event;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnSignout1;
    @FXML
    private Button event1;
    @FXML
    private ListView listpart;
ObservableList observ_list_data= FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        
 participant_methode evm = new participant_methode();
        

        try {
            observ_list_data = evm.listpart();
        } catch (SQLException ex) {
            Logger.getLogger(List_part_backController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listpart.setItems(observ_list_data);
        listpart.setCellFactory(new Callback<ListView<Participant>, javafx.scene.control.ListCell<Participant>>() {
            @Override
            public ListCell<Participant> call(ListView<Participant> listView) {
                return new ajout_part_back() ;
            }
        });

    }








// TODO

    @FXML
    private void btnHomeOnClick(ActionEvent event) {
    }

    @FXML
    private void partbutton(ActionEvent event) {
    }

    @FXML
    private void velo_en_vente(ActionEvent event) {
    }

    @FXML
    private void commande(ActionEvent event) {
    }

    @FXML
    private void location(ActionEvent event) {
    }

    @FXML
    private void velo_alouer(ActionEvent event) {
    }

    @FXML
    private void reclamation(ActionEvent event) {
    }

    @FXML
    private void piece_de_rechange(ActionEvent event) {
    }

    @FXML
    private void type_de_piece(ActionEvent event) {
    }
    }    


