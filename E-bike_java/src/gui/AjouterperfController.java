package gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import entities.performance;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import services.PerformanceDao;


/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterperfController implements Initializable {

    @FXML
    private AnchorPane show;
    @FXML
    private TextField nom;
    @FXML
    private TextField numero;
    @FXML
    private TextField objectif;
    @FXML
    private TextField location;
    @FXML
    private Button ajouter;
    @FXML
    private Button consulter;
    @FXML
    private AnchorPane PERFANCH;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuClose;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) { 
        ajouter.setOnAction(event -> {
            if  ( (!(nom.getText() == null || nom.getText().trim().isEmpty())) && (!(numero.getText() == null || numero.getText().trim().isEmpty())) && (!(location.getText() == null || location.getText().trim().isEmpty()))) {
                PerformanceDao pdao = PerformanceDao.getInstance();
                
                performance p = new performance(nom.getText(), numero.getText(), location.getText(),  objectif.getText());

                pdao.insert(p);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("performance insérée avec succés!");
                alert.show();
                // nom.setText("");
                // prenom.setText("");
            }
        });
        // TODO
    }    

    @FXML
    private void ajouter_event(ActionEvent event) {
    }

    @FXML
    private void consulter_event(ActionEvent event) {
    }

    @FXML
    private void form(MouseEvent event) {
    }

    @FXML
    private void promo(MouseEvent event) {
    }

    @FXML
    private void abon(MouseEvent event) {
    }

    @FXML
    private void stat(MouseEvent event) {
    }
    @FXML
    private void goshopfront(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/list_velo_front.fxml"));
       PERFANCH.getChildren().setAll(pane);
    }

    @FXML
    private void golocationfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/location.fxml"));
       PERFANCH.getChildren().setAll(pane);
    }

    @FXML
    private void goeventfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/front.fxml"));
       PERFANCH.getChildren().setAll(pane);
    }

    @FXML
    private void gocoursfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/.fxml"));
       PERFANCH.getChildren().setAll(pane);
    }

    @FXML
    private void goperffront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/ajouterperf.fxml"));
       PERFANCH.getChildren().setAll(pane);
    }

    @FXML
    private void gorecfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/reclamation_front.fxml"));
       PERFANCH.getChildren().setAll(pane);
    }
    
    
}