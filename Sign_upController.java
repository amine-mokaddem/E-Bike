/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.UserDao;
import com.esprit.entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LEGION
 */
public class Sign_upController implements Initializable {

    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField username;
    @FXML
    private TextField mdp;
    @FXML
    private TextField mail;
    @FXML
    private Button singup;
    @FXML
    private TextField role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                singup.setOnAction(event -> {
            if(( mail.getText().indexOf("@")!=-1)&&(!(prenom.getText() == null || prenom.getText().trim().isEmpty()))&&(!(nom.getText() == null || nom.getText().trim().isEmpty()))&&(!(username.getText() == null || username.getText().trim().isEmpty()))&&(!(mdp.getText() == null || mdp.getText().trim().isEmpty()))&&(!(role.getText() == null || role.getText().trim().isEmpty())))
            {
            User p = new User(prenom.getText(), nom.getText(), username.getText(), mdp.getText(), mail.getText(), role.getText());
            UserDao pdao = UserDao.getInstance();
            pdao.insert(p);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("user insérée avec succés!");
        alert.show();
        nom.setText("");
        prenom.setText("");
            }
   
        });
                          Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("verifier vos paramettres!");
        alert.show();
        nom.setText("");
        prenom.setText("");
    }    
    
}
