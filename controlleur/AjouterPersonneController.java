/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import service.UserDao;
import entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    private TextField username;
    @FXML
    private TextField mail;
    @FXML
    private PasswordField password;
    @FXML
    private Button b;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuClose;
    @FXML
    private AnchorPane slider;

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
            if ((mail.getText().indexOf("@") != -1) && (!(prenom.getText() == null || prenom.getText().trim().isEmpty())) && (!(nom.getText() == null || nom.getText().trim().isEmpty())) && (!(username.getText() == null || username.getText().trim().isEmpty())) && (!(password.getText() == null || password.getText().trim().isEmpty()))) {
                UserDao pdao = UserDao.getInstance();
                String mdpcrypter = pdao.encrypt(password.getText());
                User p = new User(prenom.getText(), nom.getText(), username.getText(), mdpcrypter, mail.getText(), "admin");

                pdao.insert(p);

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("user insérée avec succés!");
                alert.show();
                // nom.setText("");
                // prenom.setText("");
            }
        });

        b.setOnAction((ActionEvent event) -> {
            FXMLLoader fxmLoader3 = new FXMLLoader(getClass().getResource("/view/Accueil.fxml"));
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide();

        });
    }

    @FXML
    private void form(MouseEvent event) {
    }

    @FXML
    private void abon(MouseEvent event) {
    }

    @FXML
    private void stat(MouseEvent event) {
    }

}
