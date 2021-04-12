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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class AjouterPersonneController implements Initializable {

    @FXML
    private Button btn;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private Pane BoutonValider;
    @FXML
    private TextField username;
    @FXML
    private TextField mail;
    @FXML
    private PasswordField password;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO     
        btn.setOnAction(event -> {
                        if(( mail.getText().indexOf("@")!=-1)&&(!(prenom.getText() == null || prenom.getText().trim().isEmpty()))&&(!(nom.getText() == null || nom.getText().trim().isEmpty()))&&(!(username.getText() == null || username.getText().trim().isEmpty()))&&(!(password.getText() == null || password.getText().trim().isEmpty())))
                        {
            User p = new User(prenom.getText(), nom.getText(), username.getText(), mail.getText(), password.getText(),"admin");
            UserDao pdao = UserDao.getInstance();
            pdao.insert(p);
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("user insérée avec succés!");
        alert.show();
        nom.setText("");
        prenom.setText("");
                        }
        });
        
        
    }

}
