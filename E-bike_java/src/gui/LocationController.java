/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Velo_al;
import entities.Veloav;
import entities.location;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import services.location_methode;
import services.veloal_methode;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class LocationController implements Initializable {

    @FXML
    private ListView listloc;
    @FXML
    private  AnchorPane listanchor;
     ObservableList observ_list_data = FXCollections.observableArrayList();
    @FXML
    private Slider slider;
    @FXML
    private TextField pmax;
    @FXML
    private TextField pmin;
    private Float p=0f;
private veloal_methode ds = new veloal_methode();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       veloal_methode v= new veloal_methode();
        try {
            observ_list_data = v.listvelo();
        } catch (SQLException ex) {
            Logger.getLogger(List_velo_frontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listloc.setItems(observ_list_data);
        listloc.setCellFactory(new Callback<ListView<Velo_al>, javafx.scene.control.ListCell<Velo_al>>()
        {
            @Override
            public ListCell<Velo_al> call(ListView<Velo_al> listView)
            {
                return new ajout_veloal_list_front();
            }
        });
        
        pmax.setText(String.valueOf(ds.getMax())+" DNT");
        pmax.setDisable(true);
        pmin.setDisable(true);
         slider.valueProperty().addListener(new ChangeListener<Number>(){
           @Override
           public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
              p=(float) slider.getValue();
              pmin.setText(String.valueOf(p)+" DNT");
          Aff1();
           }

          
       });
   
       slider.setMax(ds.getMax());
       
        
         
        
    }  

  
      
    
    
    
    public void Aff1(){
          
          veloal_methode v= new veloal_methode();
        try {
            observ_list_data = v.getAllp(p);
        } catch (SQLException ex) {
            Logger.getLogger(List_velo_frontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listloc.setItems(observ_list_data);
        listloc.setCellFactory(new Callback<ListView<Velo_al>, javafx.scene.control.ListCell<Velo_al>>()
        {
            @Override
            public ListCell<Velo_al> call(ListView<Velo_al> listView)
            {
                return new ajout_veloal_list_front();
            }
        });
           
          
        
} 
 @FXML
    private void goshopfront(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/list_velo_front.fxml"));
       listanchor.getChildren().setAll(pane);
    }

    @FXML
    private void golocationfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/location.fxml"));
       listanchor.getChildren().setAll(pane);
    }

    @FXML
    private void goeventfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/front.fxml"));
       listanchor.getChildren().setAll(pane);
    }

    @FXML
    private void gocoursfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/.fxml"));
       listanchor.getChildren().setAll(pane);
    }

    @FXML
    private void goperffront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/ajouterperf.fxml"));
       listanchor.getChildren().setAll(pane);
    }

    @FXML
    private void gorecfront(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/reclamation_front.fxml"));
       listanchor.getChildren().setAll(pane);
    }
    
      

}
