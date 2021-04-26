/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Piece_r;
import entities.Typepr;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import services.Piece_r_methode;
import services.typepr_method;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class Piece_rController implements Initializable {
      Connection cnx;
    private PreparedStatement ste;
    private ResultSet rs;
    private Statement st;
    @FXML
    private ChoiceBox<String> type;
    @FXML
    private TextField nom;
    @FXML
    private Spinner<Integer> quantite;
    @FXML
    private Spinner<Integer> prix;
    
    
    
    @FXML
    private AnchorPane piece_rr;
    @FXML
    private ListView listepiece;
    ObservableList observ_list_data = FXCollections.observableArrayList();
    

   
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        typepr_method t= new typepr_method();
        ObservableList<String> listtype = FXCollections.observableArrayList();
          try {
              listtype= t.combcox_piece();
          } catch (SQLException ex) {
              Logger.getLogger(Piece_rController.class.getName()).log(Level.SEVERE, null, ex);
          }
         type.setItems(listtype);
          type.getSelectionModel().selectFirst();
          
          
            prix.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5000));
              quantite.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5000));
              Aff();
        }
    
    
    
    
    
    @FXML
    private void ajouterpiece(ActionEvent event) throws SQLException {
       typepr_method t= new typepr_method();
       ObservableList<Typepr> typelist = t.listtype(); 
       Piece_r_methode p = new Piece_r_methode();
       int id_type = p.GetIdFromtype(type.getValue());
       p.ajouterpiece(new Piece_r(nom.getText(),prix.getValue(),id_type,quantite.getValue()));
     
                   

           
    }
    public void Aff(){
          
           Piece_r_methode v= new Piece_r_methode();
        try {
            observ_list_data = v.listpiece();
        } catch (SQLException ex) {
            Logger.getLogger(List_velo_frontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listepiece.setItems(observ_list_data);
        listepiece.setCellFactory(new Callback<ListView<Piece_r>, javafx.scene.control.ListCell<Piece_r>>()
        {
            @Override
            public ListCell<Piece_r> call(ListView<Piece_r> listView)
            {
                return new ajout_piece_list_back() ;
            }
        });
    
    }

    public void setNom(String nom) {
        this.nom.setText(nom);
    }

    public void setQuantite(Integer quantite) {
        this.quantite.getValueFactory().setValue(quantite);
         
    }

    public void setPrix(Integer prix) {
        this.prix.getValueFactory().setValue(prix);
    }

    @FXML
    public void btnHomeOnClick(ActionEvent event) throws IOException{
      
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/addevent.fxml"));
       piece_rr.getChildren().setAll(pane);
    }

    @FXML
    private void partbutton(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void velo_en_vente(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/velo.fxml"));
       piece_rr.getChildren().setAll(pane);
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
       piece_rr.getChildren().setAll(pane);
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/reclamation_back.fxml"));
       piece_rr.getChildren().setAll(pane);
    }

    @FXML
    private void piece_de_rechange(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/piece_r.fxml"));
       piece_rr.getChildren().setAll(pane);
    }

    @FXML
    private void type_de_piece(ActionEvent event) throws IOException {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/typepr.fxml"));
       piece_rr.getChildren().setAll(pane);
    }

    
    
    
    
    
    
    
    
}
