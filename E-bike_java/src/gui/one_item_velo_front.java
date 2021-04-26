/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Veloav;
import static gui.AfficherdetController.v;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 *
 * @author amine
 */
public class one_item_velo_front {

    @FXML
    public AnchorPane container;
    @FXML
    private ImageView imgv;
    @FXML
    private Label nomv;
    @FXML
    private Label dispov;
    @FXML
    private Button afficherdet;
    @FXML
    private Button btnpanier;
    @FXML
    private AnchorPane affdeta;
    Stage primaryStage;
     Parent root;
    public one_item_velo_front() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/one_item_velo_front.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void setinfo(Veloav data) {

        if (data != null) {
            System.out.println(data.toString());
            nomv.setText(data.getMarque());
            dispov.setText(data.getDisponibilite());
            nomv.setText(data.getMarque());
            System.out.println(data.getImagev());
            File file = new File("C:\\Users\\amine\\Desktop\\java pi\\integration\\E-Bike-amine\\public\\upload\\images\\"+data.getImagev());
            imgv.setImage(new Image(file.toURI().toString()));
              afficherdet.setOnAction(e->{
              
                    AfficherdetController.v=data;
                    System.out.println(v);
                     FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/afficherdet.fxml"));
                try {
                    Parent root = loader.load();
                    nomv.getScene().setRoot(root);
                    // Panier.addItem(data);
                } catch (IOException ex) {
                    Logger.getLogger(one_item_velo_front.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            
            });
            btnpanier.setOnAction(e->{ 
                Panier.addItem(data);
               
            });  
 
        }
    }

    public AnchorPane getBox() {
        return container;
    }

}
