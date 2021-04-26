/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Piece_r;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import services.Piece_r_methode;

/**
 *
 * @author Hamdi ben zaghden
 */
public class one_item_piece_back {
    @FXML
    public AnchorPane piece_r ;
    @FXML
    private Label nom;
    @FXML
    private Label type_id;
    @FXML
    private Label prix;
    @FXML
    private Label qte;
 
    @FXML
    private Button btnsupprimer; 
     @FXML
    private Button btnmodifier;
     
     Piece_r_methode v= new Piece_r_methode();
     Piece_rController c= new Piece_rController();
     Acceuil_adminController a= new Acceuil_adminController();
      public one_item_piece_back() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/one_item_piece_back.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
      
  public void setinfo(Piece_r data) {

        if (data != null) {
            
            nom.setText(data.getNom());
            type_id.setText(""+data.getType_id());
            prix.setText(""+data.getPrix());
            qte.setText(""+data.getQuantite());
            
            btnsupprimer.setOnAction(e->{
               v.Deletepiece(data.getId());     
               
            });
             btnmodifier.setOnAction(e->{
               
             });
        }
    }    
      
  
    public AnchorPane getBox() {
        return piece_r;
    }
    
}
