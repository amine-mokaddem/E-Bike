/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Reclamation;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import services.reclamation_methode;

/**
 *
 * @author amine
 */

public class one_item_rec_back {
    
    @FXML
    public AnchorPane recback;
    @FXML
    private Label nom;
    @FXML
    private Label email;
    @FXML
    private Label date;
    @FXML
    private Label pieceid;
    @FXML
    private Label desc;
     @FXML
    private Label etat;
    @FXML
    private Button btnapprouver;
   
reclamation_methode r= new reclamation_methode();
   public one_item_rec_back() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gui/one_item_rec_back.fxml"));
        fxmlLoader.setController(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


public void setinfo(Reclamation data) {

        if (data != null) {
            
            nom.setText(data.getNom());
            email.setText(data.getEmail());
            date.setText(""+data.getDatereclamation());
            pieceid.setText(""+data.getPiece_id());
            desc.setText(data.getDescription());
            etat.setText(data.getEtat());
            btnapprouver.setOnAction(e->{
               
                try {
                    r.approuverreclamation(data);  
                    etat.setText("acceptée");
                } catch (SQLException ex) {
                    Logger.getLogger(one_item_rec_back.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            });
        }
    }

    public AnchorPane getBox() {
        return recback;
    }














}
