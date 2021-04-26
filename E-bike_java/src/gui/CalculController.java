/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class CalculController implements Initializable {
    

    @FXML
    private TextField mpm;
    @FXML
    private TextField pmt;
    @FXML
    private TextField nbmr;
    @FXML
    private TextField dt;
    @FXML
    private ComboBox<String> cbc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        cbc.getItems().addAll("Defoulement","Endurance");
    }    

    @FXML
    private void calculernbr(ActionEvent event) {
        
       int a = Integer.parseInt(pmt.getText());
       int b = Integer.parseInt(dt.getText());
       int c = Integer.parseInt(mpm.getText());
       
       int t = ((a*c)-(b*c));
       nbmr.setText(String.valueOf(t));

       
    }

    @FXML
    private void DimensionsUPD(ActionEvent event) {
        if(cbc.getValue().equals("Endurance")){
            dt.setText("5");
        }
        
        else if (cbc.getValue().equals("Defoulement")){
            dt.setText("3");
        }
       
    }

    @FXML
    private void retourCalcul(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("Affichagecours.fxml"));
            Parent root = loader.load();
            mpm.getScene().setRoot(root);
     
    }
    
}
