/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.UserDao;
import com.esprit.entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LEGION
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tfuser;
    @FXML
    private TextField tfpass;
    @FXML
    private Button login;
    @FXML
    private Button singup;
    @FXML
    private Button buttonbensalem;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         login.setOnAction(event -> {
            
            User p = new User(tfuser.getText(), tfpass.getText());
            UserDao pdao = UserDao.getInstance();
          int x=  pdao.connex(p);

          if (x==0)
          {
                 try {
       FXMLLoader fxmLoader = new FXMLLoader(getClass().getResource("/com/esprit/view/Accueil.fxml"));
                 Parent root1 = (Parent) fxmLoader.load();
                 Stage stage = new Stage();
                 stage.setScene(new Scene(root1));
                 stage.show();} catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }  
          }
          else if (x==1)
          {
                try {
                 FXMLLoader fxmLoader = new FXMLLoader(getClass().getResource("/com/esprit/view/interfaceens.fxml"));
                 Parent root1 = (Parent) fxmLoader.load();
                 Stage stage = new Stage();
                 stage.setScene(new Scene(root1));
                 stage.show();} catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }   
          }
          else if (x==2)
          {
                 try {
                 FXMLLoader fxmLoader2 = new FXMLLoader(getClass().getResource("/com/esprit/view/interfaceetud.fxml"));
                 Parent root2 = (Parent) fxmLoader2.load();
                 Stage stage2 = new Stage();
                 stage2.setScene(new Scene(root2));
                 stage2.show();} catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }  
          }
        
    
      
        });
                  singup.setOnAction(event -> {
            
             try {
                 FXMLLoader fxmLoader3 = new FXMLLoader(getClass().getResource("/com/esprit/view/sign_up.fxml"));
                 Parent root3 = (Parent) fxmLoader3.load();
                 Stage stage3= new Stage();
                 stage3.setScene(new Scene(root3));
                 stage3.show();} catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }
      
        });                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
        // TODO
    }    

    @FXML
    private void aze(ActionEvent event) {
    }
    
}
