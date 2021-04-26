/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Participant;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author HP
 */
public class one_part_item {
 
@FXML
private Label nom;
@FXML
private Label email;
@FXML
private Label prenom;
@FXML
private Label numtel;
@FXML
private Label etat;
@FXML
private Button btnsupprimer;
@FXML
private AnchorPane anchor;
public one_part_item(){
 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/one_part_item.fxml"));
fxmlLoader.setController(this);
    try {
        fxmlLoader.load();
    } catch (IOException ex) {
        Logger.getLogger(one_part_item.class.getName()).log(Level.SEVERE, null, ex);
    }
    
}
 public void setinfo(Participant data) {
     if (data!=null){
       nom.setText(data.getNom());
       email.setText(data.getEmail());
       numtel.setText(data.getNumtel()+"");
       etat.setText(data.getPrenom());
     }
     
     
     
     
     
 }  
   public AnchorPane getBox() {
    
        return anchor;
    } 
    
    
}
