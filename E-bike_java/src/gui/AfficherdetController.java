/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Veloav;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.user_methode;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class AfficherdetController implements Initializable {

    @FXML
    private ImageView imgd;
    @FXML
    private Label price;
    public static Veloav v;
    @FXML
    private AnchorPane affdeta;
    @FXML
    private Label marque;
    @FXML
    private Label desc;
    @FXML
    private Label disponiblilite;
    @FXML
    private Button btnacheter;
    
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       price.setText(""+v.getPrix()+"DT");
       marque.setText(v.getMarque());
       desc.setText(v.getDescription());
       disponiblilite.setText(v.getDisponibilite());
       File file = new File("C:\\Users\\amine\\Desktop\\java pi\\integration\\E-Bike-amine\\public\\upload\\images\\"+v.getImagev());
            imgd.setImage(new Image(file.toURI().toString()));
   }    

    @FXML
    private void acheter(ActionEvent event) throws IOException {
      AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/payment_sans_carte.fxml"));
       affdeta.getChildren().setAll(pane);    
    }

    @FXML
    private void add_cart(ActionEvent event) {
    }

    @FXML
    private void go_shop(MouseEvent event) {
    }

  
    
}
