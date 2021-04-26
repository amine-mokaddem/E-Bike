/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Event;
import entities.Veloav;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import services.EventMethode;
import services.veloav_methode;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class List_velo_frontController implements Initializable {

    @FXML
    private ListView listvelo;
    @FXML
    private  AnchorPane listanchor;
     ObservableList observ_list_data = FXCollections.observableArrayList();
    @FXML
    private Button consulterpanier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       veloav_methode v= new veloav_methode();
        try {
            observ_list_data = v.listvelo();
        } catch (SQLException ex) {
            Logger.getLogger(List_velo_frontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listvelo.setItems(observ_list_data);
        listvelo.setCellFactory(new Callback<ListView<Veloav>, javafx.scene.control.ListCell<Veloav>>()
        {
            @Override
            public ListCell<Veloav> call(ListView<Veloav> listView)
            {
                return new ajout_velo_list_front();
            }
        });
    }    

    @FXML
    private void consulterpanier(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/list_panier.fxml"));
      listanchor.getChildren().setAll(pane);
       
        
    }
    @FXML
    private void goshopfront(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/list_velo_front.fxml"));
       listanchor.getChildren().setAll(pane);
    }

    @FXML
    private void golocationfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/location.fxml"));
       listanchor.getChildren().setAll(pane);
    }

    @FXML
    private void goeventfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/front.fxml"));
       listanchor.getChildren().setAll(pane);
    }

    @FXML
    private void gocoursfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/.fxml"));
       listanchor.getChildren().setAll(pane);
    }

    @FXML
    private void goperffront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/ajouterperf.fxml"));
       listanchor.getChildren().setAll(pane);
    }

    @FXML
    private void gorecfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/reclamation_front.fxml"));
       listanchor.getChildren().setAll(pane);
    }
    
}
