/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Event;
import entities.Typepr;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import services.EventMethode;
import services.typepr_method;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class TypeprController implements Initializable {

    @FXML
    private TextField type;
    @FXML
    private TextField fournisseur;
    @FXML
    private TableView<Typepr> tabletype;
    @FXML
    private TableColumn<Typepr, String> typet;
    @FXML
    private TableColumn<Typepr, String> fournisseurt;
    @FXML
    private TableColumn<Typepr, String> supprimer;
    int ID;
     Connection cnx;
    private PreparedStatement ste;
    private ResultSet rs;
    private Statement st;
    @FXML
    private AnchorPane typeprr;
    @FXML
    private Button event;
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
    private Button btnSignout1;
    @FXML
    private Button event1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            type_list();
        } catch (SQLException ex) {
            Logger.getLogger(TypeprController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void editertype(ActionEvent event) {
       Typepr typep = tabletype.getSelectionModel().getSelectedItem();
         ID = typep.getId();
         type.setText(typep.getType());        
         fournisseur.setText(typep.getFournisseur());
        System.out.println(ID); 
        
       }

    @FXML
    private void savetype(ActionEvent event) throws SQLException {
        typepr_method t= new typepr_method();
        Typepr typep = tabletype.getSelectionModel().getSelectedItem();
        t.edittype(ID, new Typepr(type.getText(),fournisseur.getText()));
        type_list();
        
        
          
        
        
        
        
    }

    @FXML
    private void ajoutertype(ActionEvent event) throws SQLException {
         typepr_method t= new typepr_method();
         t.ajoutertype(new Typepr(type.getText(),fournisseur.getText() ) );
         type_list();
    }
    
    
     public void type_list() throws SQLException {
        typepr_method t= new typepr_method();
        ObservableList<Typepr> typelist = t.listtype();
        tabletype.setItems(typelist);
        
        typet.setCellValueFactory(new PropertyValueFactory<Typepr, String>("type"));
        
        fournisseurt.setCellValueFactory(new PropertyValueFactory<Typepr, String>("fournisseur"));
        
        
        Callback<TableColumn<Typepr, String>, TableCell<Typepr, String>> cellFoctory = (TableColumn<Typepr, String> param) -> {
            // make cell containing buttons
            final TableCell<Typepr, String> cell = new TableCell<Typepr, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );

                        deleteIcon.setOnMouseClicked(event -> {

                            Typepr ty = tabletype.getSelectionModel().getSelectedItem();
                            t.Deletetype(ty.getId());
                            try {
                                type_list();
                            } catch (SQLException ex) {
                                Logger.getLogger(eventcontroller.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        HBox managebtn = new HBox(deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        setGraphic(managebtn);
                        setText(null);

                    }
                }

            };

            return cell;
        };
        supprimer.setCellFactory(cellFoctory);
        tabletype.setItems(typelist);
    }

   @FXML
    public void btnHomeOnClick(ActionEvent event) throws IOException{
      
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/addevent.fxml"));
       typeprr.getChildren().setAll(pane);
    }

    @FXML
    private void partbutton(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void velo_en_vente(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/velo.fxml"));
       typeprr.getChildren().setAll(pane);
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
       typeprr.getChildren().setAll(pane);
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/reclamation_back.fxml"));
       typeprr.getChildren().setAll(pane);
    }

    @FXML
    private void piece_de_rechange(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/piece_r.fxml"));
       typeprr.getChildren().setAll(pane);
    }

    @FXML
    private void type_de_piece(ActionEvent event) throws IOException {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/typepr.fxml"));
       typeprr.getChildren().setAll(pane);
    }

     
     
     
     
    
}
