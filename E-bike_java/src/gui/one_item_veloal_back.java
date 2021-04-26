/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Velo_al;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import services.veloal_methode;

/**
 *
 * @author amine
 */
public class one_item_veloal_back {
    @FXML
    public AnchorPane veloal;
    @FXML
    private Label marque;
    @FXML
    private Label type;
    @FXML
    private Label prix;
    @FXML
    private Label qte;
 
    @FXML
    private Button btnsupprimer; 
     @FXML
    private Button btnmodifier; 
     veloal_methode v= new veloal_methode();
     VeloalController c= new VeloalController();
     Acceuil_adminController a= new Acceuil_adminController();
      public one_item_veloal_back() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/one_item_veloal_back.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
      
  public void setinfo(Velo_al data) {

        if (data != null) {
            
            marque.setText(data.getMarque());
            type.setText(data.getType());
            prix.setText(""+data.getPrix());
            qte.setText(""+data.getQuantite());
            
            btnsupprimer.setOnAction(e->{
               v.Deleteveloal(data.getId());  
               
               
            });
             btnmodifier.setOnAction(e->{
               
             });
        }
    }    
      
  
    public AnchorPane getBox() {
        return veloal;
    }
   
     
}
