/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Commande;
import entities.Veloav;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class List_panierController implements Initializable {

    @FXML
    private Button btn_back;
    @FXML
    private ImageView img;
    @FXML
    private Label lb_titre;
    @FXML
    private ListView list_panier;
    @FXML
    private Label prix;
 private List<Veloav> data;
  public static int prix_total = 0;

    ObservableList observ_list_data = FXCollections.observableArrayList();
    @FXML
    private AnchorPane anchoe;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       data = Panier.lst_shop;
        for (Veloav f : data) {
            prix_total += f.getPrix();
        }

        prix.setText("Total : " + prix_total + " TND");
        observ_list_data.setAll(Panier.lst_shop);
        list_panier.setItems(observ_list_data);
        list_panier.setCellFactory(new Callback<ListView<Veloav>, javafx.scene.control.ListCell<Veloav>>()
        {
            @Override
            public ListCell<Veloav> call(ListView<Veloav> listView)
            {
                return new ajout_panier_list();
            }
        });
        
       
    }    

    @FXML
    private void retour_shop(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/list_velo_front.fxml"));
       anchoe.getChildren().setAll(pane);
        
        
    }

    @FXML
    private void acheter(ActionEvent event) throws IOException {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/payment_sans_carte.fxml"));
       anchoe.getChildren().setAll(pane);  
    }
    
}
