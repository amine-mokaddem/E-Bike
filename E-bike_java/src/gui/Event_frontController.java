/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Event;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import services.EventMethode;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class Event_frontController implements Initializable {

    @FXML
    private ListView list_evenement;
    private List<Event> data ;
   
     ObservableList observ_list_data = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       EventMethode e= new EventMethode();
        
         data = new ArrayList<>();
        
        try {
            data= e.Liste();
        } catch (SQLException ex) {
            Logger.getLogger(Event_frontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        /*
        data.add(new Formation("titre 01", "description 01", "01/03/2021", "16/03/2021", "nom_form 01", "prenom_form 01", "type", 0))    ;
        data.add(new Formation("titre 02", "description 01", "01/03/2021", "16/03/2021", "nom_form 01", "prenom_form 01", "type", 0))    ;
        data.add(new Formation("titre 03", "description 01", "01/03/2021", "16/03/2021", "nom_form 01", "prenom_form 01", "type", 0))    ;
        data.add(new Formation("titre 04", "description 01", "01/03/2021", "16/03/2021", "nom_form 01", "prenom_form 01", "type", 0))    ;
*/
        
        observ_list_data.setAll(data);
        list_evenement.setItems(observ_list_data);
        list_evenement.setCellFactory(new Callback<ListView<Event>, javafx.scene.control.ListCell<Event>>()
        {
            @Override
            public ListCell<Event> call(ListView<Event> listView)
            {
                return new listevent_item();
            }
        });
    }  
    
    
    
   
}
