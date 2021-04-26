/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Event;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class Item_eventController implements Initializable {

    @FXML
    public AnchorPane container;
    @FXML
    private ImageView img;
    @FXML
    private Label eventn;
    @FXML
    private Label desc;
    @FXML
    private Label debdate;
    @FXML
    private Label findate;
    @FXML
    private Label nbr_place;
    @FXML
    private Button participer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Event e = new Event(4, "amine", 20, "test", "test", new Date(10 / 02 / 2020), new Date(10 / 02 / 2020), "abcd", "abcd");
        eventn.setText("amine");
    }

    public void aziz(Event e) {
        if (e == null) {
            System.out.println("null");
        }
    }

    public AnchorPane getBox() {
        return container;
    }

}
