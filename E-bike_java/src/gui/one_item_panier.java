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

/**
 *
 * @author amine
 */
public class one_item_panier {
    
    @FXML
    public AnchorPane container;
    @FXML
    private ImageView img;
    @FXML
    private Label marque;
    @FXML
    private Label prix;
    @FXML
    private Button btn_annuler;
    
    public one_item_panier(){
     FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/one_item_panier.fxml"));
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
            marque.setText(data.getMarque());
            prix.setText(data.getPrix()+" DT");
            File file = new File("C:\\Users\\amine\\Desktop\\java pi\\integration\\E-Bike-amine\\public\\upload\\images\\"+data.getImagev());
            img.setImage(new Image(file.toURI().toString()));
             btn_annuler.setOnAction(e->{
                try {
                    Panier.deleteItemFromPanier(data.getId());
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/list_panier.fxml"));
                    
                    Parent root = loader.load();
                    marque.getScene().setRoot(root);
                    // Panier.addItem(data);
                } catch (IOException ex) {
                    Logger.getLogger(one_item_panier.class.getName()).log(Level.SEVERE, null, ex);
                }
                            
                
                
            });
        }
        
    }

      public AnchorPane getBox() {
        return container;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
