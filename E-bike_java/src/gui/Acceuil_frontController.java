/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class Acceuil_frontController implements Initializable {

    @FXML
    private AnchorPane front;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void goshopfront(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/list_velo_front.fxml"));
       front.getChildren().setAll(pane);
    }

    @FXML
    private void golocationfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/location.fxml"));
       front.getChildren().setAll(pane);
    }

    @FXML
    private void goeventfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/front.fxml"));
       front.getChildren().setAll(pane);
    }

    @FXML
    private void gocoursfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/.fxml"));
       front.getChildren().setAll(pane);
    }

    @FXML
    private void goperffront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/ajouterperf.fxml"));
       front.getChildren().setAll(pane);
    }

    @FXML
    private void gorecfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/reclamation_front.fxml"));
       front.getChildren().setAll(pane);
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/login.fxml"));
       front.getChildren().setAll(pane);
    }
    }
    

