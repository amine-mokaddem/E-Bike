/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Commande;
import entities.Veloav;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import services.commande_methode;
import services.veloav_front_methode;
import services.veloav_methode;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class Veloav_frontController implements Initializable {

    @FXML
    private TableView<Veloav> tablevelo;
    @FXML
    private TableColumn<Veloav, String> marquev;
    @FXML
    private TableColumn<Veloav, String> typev;
    @FXML
    private TableColumn<Veloav, Integer> prixv;
    @FXML
    private TableColumn<Veloav, Integer> quantitev;
    @FXML
    private TableColumn<Veloav, String> descriptionv;
    @FXML
    private TableColumn<Veloav, String> disponibilitév;
    private TableColumn<Veloav, String> commander;
    @FXML
    private TextField email;
    @FXML
    private TextField nom;
    @FXML
    private TextField adresse;
    @FXML
    private Spinner<Integer> numtel;
    @FXML
    private Spinner<Integer> quantitec;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            listedesvelo();
        } catch (SQLException ex) {
            Logger.getLogger(Veloav_frontController.class.getName()).log(Level.SEVERE, null, ex);
        }
            numtel.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5000));
            quantitec.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5000));


}
public void listedesvelo() throws SQLException{
        veloav_front_methode v= new veloav_front_methode();
        ObservableList<Veloav> listvelo = v.listvelo();
        tablevelo.setItems(listvelo);
        marquev.setCellValueFactory(new PropertyValueFactory<Veloav, String>("marque"));
        typev.setCellValueFactory(new PropertyValueFactory<Veloav, String>("type"));
        prixv.setCellValueFactory(new PropertyValueFactory<Veloav, Integer>("prix"));
        quantitev.setCellValueFactory(new PropertyValueFactory<Veloav, Integer>("quantite"));
        descriptionv.setCellValueFactory(new PropertyValueFactory<Veloav, String>("description"));
 
}

    @FXML
    private void passercommande(ActionEvent event) {
        commande_methode com= new commande_methode();
         LocalDate now = LocalDate.now();
        com.ajoutercommande(new Commande("zzz", 516248, "nom", "aiaiai", "en attente",3,java.sql.Date.valueOf(now))); 
     
        
        
        
        
    }
    
   

    public void setNumtel(Integer numtel) {
        this.numtel.getValueFactory().setValue(numtel);
    }

    public void setQuantitec(Integer quantitec) {
         this.quantitec.getValueFactory().setValue(quantitec);
    }

    public void setEmail(String email) {
        this.email.setText(email);
    }

    public void setNom(String nom) {
        this.nom.setText(nom);
    }

    public void setAdresse(String adresse) {
        this.adresse.setText(adresse);
    }
     
    

}
