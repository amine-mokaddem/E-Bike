/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Piece_r;
import entities.Reclamation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import services.Piece_r_methode;
import services.reclamation_methode;
import services.typepr_method;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class Reclamation_frontController implements Initializable {

    @FXML
    private ComboBox<String> piece;
    @FXML
    private TextField email;
    @FXML
    private TextArea description;
    @FXML
    private TextField nom;
    @FXML
    private ComboBox<String> type;
     typepr_method t= new typepr_method();
     ObservableList<String> listpiece = FXCollections.observableArrayList();
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /*Piece_r_methode piecee= new Piece_r_methode();
       ObservableList<String> listpiece = FXCollections.observableArrayList();
        try {
            listpiece= piecee.combcox_nompiece();
        } catch (SQLException ex) {
            Logger.getLogger(Reclamation_frontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        piece.setItems(listpiece);
        piece.getSelectionModel().selectFirst();*/
        
        typepr_method t= new typepr_method();
        ObservableList<String> listtype = FXCollections.observableArrayList();
          try {
              listtype= t.combcox_piece();
          } catch (SQLException ex) {
              Logger.getLogger(Piece_rController.class.getName()).log(Level.SEVERE, null, ex);
          }
         type.setItems(listtype);
          type.getSelectionModel().selectFirst();
    
    }

    @FXML
    private void ajouterreclamation(ActionEvent event) throws SQLException {
      Piece_r_methode piecee= new Piece_r_methode(); 
      reclamation_methode r = new reclamation_methode();
       int id_piece = piecee.GetIdFrompiece(piece.getValue());
       LocalDate now = LocalDate.now();
       r.ajouter_reclamation(new Reclamation(email.getText(),  java.sql.Date.valueOf(now) ,  description.getText(),  id_piece,  "en attente",  nom.getText()));  
        
    }
    
    

    @FXML
    private void changerpiece(ActionEvent event) throws SQLException {
        
        int id;
        id = t.getIDfromTYPE(type.getValue());

            listpiece = t.setpiece_type(id);
            piece.setItems(listpiece);
            piece.getSelectionModel().selectFirst();
            
        
        
    

}
    @FXML
    private void goshopfront(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/list_velo_front.fxml"));
       anchor.getChildren().setAll(pane);
    }

    @FXML
    private void golocationfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/location.fxml"));
       anchor.getChildren().setAll(pane);
    }

    @FXML
    private void goeventfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/front.fxml"));
       anchor.getChildren().setAll(pane);
    }

    @FXML
    private void gocoursfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/.fxml"));
       anchor.getChildren().setAll(pane);
    }

    @FXML
    private void goperffront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/ajouterperf.fxml"));
       anchor.getChildren().setAll(pane);
    }

    @FXML
    private void gorecfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/reclamation_front.fxml"));
       anchor.getChildren().setAll(pane);
    }
    
}
