/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import entities.Event;
import entities.Participant;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import map.Map;
import maps.java.MapsJava;
import services.EventMethode;
import services.participant_methode;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class FrontController implements Initializable {

    Participant e = new Participant();
    EventMethode EM = new EventMethode();
    participant_methode pm = new participant_methode();
    int placeRest = 0;
    @FXML
    private JFXTextField nom;

    @FXML
    private JFXTextField prenom;

    @FXML
    private JFXTextField email;

    @FXML
    private JFXTextField tel;
    @FXML
    private WebView webView;
@FXML
    private AnchorPane front;
    @FXML
    private Button participer;

    @FXML
    private Button loadBtn;
    private String msg = "";
    private int id = 0;

    @FXML
    void loadData(ActionEvent event) throws SQLException {
        //int a =EM.listEventplace();
        placeRest = EM.getNbPlaceRest(47);
        WebEngine engine = this.webView.getEngine();

        engine.reload();
        Platform.runLater(() -> {
            engine.getLoadWorker().stateProperty().addListener((ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State t1) -> {
                if (t1 == Worker.State.SUCCEEDED) {

                    msg = "Nombre de place restant: # <br>".replace("#", placeRest + "");
                    engine.executeScript("addMarkerPopup(10.17972, 36.80278, '#')".replace("#", msg));
                    engine.executeScript("addMarkerPopup(10.15000, 36.80278, '#')".replace("#", msg));
                    engine.executeScript("addMarkerPopup(10.20000, 36.80278, '#')".replace("#", msg));
                }
            });
        });
    }

    @FXML
    void participer(ActionEvent event) throws SQLException {
        if (this.placeRest > 0) {
            e.setEmail(email.getText());
            e.setNom(nom.getText());
            e.setPrenom(prenom.getText());
            e.setNumtel(Integer.parseInt(tel.getText()));
            pm.ajpar(e);
            loadData(event);
        } else {
            System.err.println("pas de place");
        }

    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //static event
        e.getEvent().setId(47);
        try {
            placeRest = EM.getNbPlaceRest(47);
        } catch (SQLException ex) {
            Logger.getLogger(FrontController.class.getName()).log(Level.SEVERE, null, ex);
        }

        MapsJava.setKey("AIzaSyBSCxawsr0v4eyvrVGKFgkiRsL7xT3sIGs");
        Platform.runLater(() -> {
            WebEngine engine = webView.getEngine();
            engine.setJavaScriptEnabled(true);
            URL urlMap = Map.class.getResource("/map/index.html");
            engine.load(urlMap.toString());
            engine.getLoadWorker().stateProperty().addListener((ObservableValue<? extends Worker.State> ov, Worker.State t, Worker.State t1) -> {
                if (t1 == Worker.State.SUCCEEDED) {
                    // this will be run as soon as WebView is initialized.
                    //engine.executeScript("addMarker(10.17972, 36.60278)");
                }
            });
        });
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

}
