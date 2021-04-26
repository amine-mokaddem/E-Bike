/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import services.user_methode;


/**
 * FXML Controller class
 *
 * @author amine
 */
public class LoginController implements Initializable {

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    user_methode u = new user_methode();
   public static int id = 0;
    @FXML
    private AnchorPane login;
    @FXML
    private Label erreur;
String type =null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void se_connect(ActionEvent event) throws SQLException, IOException {
        
    type = u.verif_user(password.getText(), username.getText());
  
     if(type==null ){
           AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/login.fxml"));
           login.getChildren().setAll(pane);
           }
    
           else  {
         if(type.equals("client") ){
               AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/acceuil_front.fxml"));
           login.getChildren().setAll(pane);
       
         
       }
         else {
           AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/acceuil_admin.fxml"));
           login.getChildren().setAll(pane);}   
         }
         
         
     }
          
         
    }
    
    

