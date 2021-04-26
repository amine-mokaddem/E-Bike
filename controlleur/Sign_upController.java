/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import service.UserDao;
import entity.User;
import ilearn.Ilearn;
import static java.awt.SystemColor.window;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    @FXML
    private AnchorPane label;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
    @FXML
    private Label label3;
    @FXML
    private Label label5;
    @FXML
    private Label label4;
    @FXML
    private Label label0;
    @FXML
    private Button b;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        singup.setOnAction(event -> {
            if (!(mail.getText().indexOf("@") != -1)) {
                label0.setText("verifier mail!");
            }

            if (((prenom.getText() == null || prenom.getText().trim().isEmpty()))) {
                label1.setText("il faut remplir le prenom !");
            }
            if (((nom.getText() == null || nom.getText().trim().isEmpty()))) {
                label2.setText("remplir votre nom !");
            }

            if (((username.getText() == null || username.getText().trim().isEmpty()))) {
                label3.setText("remplir user name !");
            }
            if (((mdp.getText() == null || mdp.getText().trim().isEmpty()))) {

                label4.setText("remplir votre mot de passe!");
            }

            if (((role.getText() == null || role.getText().trim().isEmpty()))) {

                label5.setText("remplir votre Role!");
            }

            if ((mail.getText().indexOf("@") != -1) && (!(prenom.getText() == null || prenom.getText().trim().isEmpty())) && (!(nom.getText() == null || nom.getText().trim().isEmpty())) && (!(username.getText() == null || username.getText().trim().isEmpty())) && (!(mdp.getText() == null || mdp.getText().trim().isEmpty())) && (!(role.getText() == null || role.getText().trim().isEmpty()))) {
                UserDao pdao = UserDao.getInstance();
                String mdpcrypter = pdao.encrypt(mdp.getText());
                User p = new User(prenom.getText(), nom.getText(), username.getText(), mdpcrypter, mail.getText(), role.getText());

                pdao.insert(p);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("user insérée avec succés!");
                alert.show();
                nom.setText("");
                prenom.setText("");
                  Ilearn m=new Ilearn();
            try {
                m.changeScene("/view/Login.fxml");
            } catch (IOException ex) {
                Logger.getLogger(Sign_upController.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
          

        });

        b.setOnAction((ActionEvent event) -> {
            FXMLLoader fxmLoader3 = new FXMLLoader(getClass().getResource("/view/Login.fxml"));
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide();

        });

    }

}
