/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;
import services.commande_methode;
import services.top_vente;
import services.veloav_methode;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class Acceuil_adminController implements Initializable {

    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
   private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Button event;
    @FXML
    private Button btnSignout1;
    @FXML
    private AnchorPane acceuil;
    @FXML
    private Button event1;
    @FXML
    private BarChart<String,Integer> chart;
     private List<top_vente> data;
     commande_methode c=new commande_methode();
     veloav_methode v =new veloav_methode();
    private Label nbrvente;
    @FXML
    private Label nbrvelo;
    @FXML
    private Label nbrachat;
     

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
try {
            data = v.getTopVente();
        } catch (SQLException ex) {
            Logger.getLogger(Acceuil_adminController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (data != null) {
            System.out.println(data.toString());
        
            // Create a XYChart.Data object for each month. Add it to the series.
            
            for (top_vente t : data) {
                 chart.setTitle("statistique des ventes des velo");
                XYChart.Series<String, Integer> series = new XYChart.Series<>();
                series.setName(t.getMarque());
                 final XYChart.Data<String, Integer> s= new XYChart.Data<>(t.getMarque(), (int)(t.getPrix() * t.getNbr_achats()));
                series.getData().add(s);
                chart.getData().add(series);
            }

        }
        try {
            //nbrvente.setText(v.listvelo().size()+" vélo en vente");
            nbrvelo.setText(c.listcommande().size()+" vélo en vente");
        } catch (SQLException ex) {
            Logger.getLogger(Acceuil_adminController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }    


    @FXML
    public void btnHomeOnClick(ActionEvent event) throws IOException{
      
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/addevent.fxml"));
       acceuil.getChildren().setAll(pane);
    }

    @FXML
    private void partbutton(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void velo_en_vente(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/velo.fxml"));
       acceuil.getChildren().setAll(pane);
    }

    @FXML
    private void commande(ActionEvent event) throws IOException {
    }

    @FXML
    private void location(ActionEvent event) throws IOException {
    }

    @FXML
    private void velo_alouer(ActionEvent event) throws IOException {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/veloal.fxml"));
       acceuil.getChildren().setAll(pane);
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/reclamation_back.fxml"));
       acceuil.getChildren().setAll(pane);
    }

    @FXML
    private void piece_de_rechange(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/piece_r.fxml"));
       acceuil.getChildren().setAll(pane);
    }

    @FXML
    private void type_de_piece(ActionEvent event) throws IOException {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/typepr.fxml"));
       acceuil.getChildren().setAll(pane);
    }

  
    public void go_velo_alouer() throws IOException {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/veloal.fxml"));
       acceuil.getChildren().setAll(pane);
    }
   
}
