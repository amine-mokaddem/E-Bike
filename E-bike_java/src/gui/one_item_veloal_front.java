/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Velo_al;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author hp
 */
public class one_item_veloal_front {
    @FXML
    public AnchorPane location;
    @FXML
    private Label marque;
    @FXML
    private Label type;
    @FXML
    private Label prix;
    @FXML
    private Label qte;
 
    @FXML
    private Button btnlouer; 
    
    Acceuil_adminController a= new Acceuil_adminController();
          public one_item_veloal_front() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/one_item_veloal_front.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            
        }
    }
  
        public void setinfo(Velo_al data) {

        if (data != null) {
            
            marque.setText(data.getMarque());
            type.setText(data.getType());
            prix.setText(""+data.getPrix());
            qte.setText(""+data.getQuantite());
            
       
             
        }
    }      
                
          
           public AnchorPane getBox() {
        return location;
    }
}
