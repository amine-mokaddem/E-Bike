/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Typepr;
import entities.Veloav;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import javafx.util.Callback;
import services.typepr_method;
import services.veloav_methode;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class VeloController implements Initializable {

   
    @FXML
    private TextField type;
    @FXML
    private TextField marque;
    @FXML
    private Spinner<Integer> quantite;
    @FXML
    private Spinner<Integer> prix;
    @FXML
    private TextField disponibilite;
    @FXML
    private TextField description;
    @FXML
    private TableView<Veloav> tablevelo;
    @FXML
    private TableColumn<Veloav, Integer> idv;
    @FXML
    private TableColumn<Veloav, String> marquev;
    @FXML
    private TableColumn<Veloav, String> typev;
    @FXML
    private TableColumn<Veloav, Integer> prixv;
    @FXML
    private TableColumn<Veloav, Integer> quantitev;
    @FXML
    private TableColumn<Veloav, String> imagev;
    @FXML
    private TableColumn<Veloav, String> descriptionv;
    @FXML
    private TableColumn<Veloav, String> disponibilitev;
    @FXML
    private TableColumn<Veloav, String> supprimerv;
int ID;
    @FXML
    private AnchorPane ventevelo;
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
      prix.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5000));
      quantite.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5000));
        try {
            listvelo() ;
        } catch (SQLException ex) {
            Logger.getLogger(VeloController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void editvelo(ActionEvent event) {
       Veloav velo = tablevelo.getSelectionModel().getSelectedItem();
         ID = velo.getId();
         marque.setText(velo.getMarque());
         type.setText(velo.getType());
         
         quantite.getValueFactory().setValue(velo.getQuantite());
         prix.getValueFactory().setValue(velo.getPrix());
         disponibilite.setText(velo.getDisponibilite());
         description.setText(velo.getDescription());
      }

    @FXML
    private void savevelo(ActionEvent event) throws SQLException {
        veloav_methode v= new veloav_methode();
        Veloav velo = tablevelo.getSelectionModel().getSelectedItem();
        
        try {
            v.editvelo(ID, new Veloav(marque.getText(),type.getText(),prix.getValue(),quantite.getValue(),description.getText(),disponibilite.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(VeloController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listvelo();
        
        
        
        
    }

    @FXML
    private void ajoutervelo(ActionEvent event) throws SQLException {
       veloav_methode v= new veloav_methode();
       v.ajouter_velo(new Veloav(marque.getText(),type.getText(),prix.getValue(),quantite.getValue(),description.getText(),disponibilite.getText()));
listvelo();
       
        
    }
    
    public void listvelo() throws SQLException{
       veloav_methode v= new veloav_methode();
        ObservableList<Veloav> listvelo = v.listvelo();
        tablevelo.setItems(listvelo);
 
        idv.setCellValueFactory(new PropertyValueFactory<Veloav, Integer>("id"));
        marquev.setCellValueFactory(new PropertyValueFactory<Veloav, String>("marque"));
        typev.setCellValueFactory(new PropertyValueFactory<Veloav, String>("type"));
         prixv.setCellValueFactory(new PropertyValueFactory<Veloav, Integer>("prix"));
          quantitev.setCellValueFactory(new PropertyValueFactory<Veloav, Integer>("quantite"));
        imagev.setCellValueFactory(new PropertyValueFactory<Veloav, String>("imagev"));
        descriptionv.setCellValueFactory(new PropertyValueFactory<Veloav, String>("description"));
         disponibilitev.setCellValueFactory(new PropertyValueFactory<Veloav, String>("disponibilite"));
        
        Callback<TableColumn<Veloav, String>, TableCell<Veloav, String>> cellFoctory = (TableColumn<Veloav, String> param) -> {
            // make cell containing buttons
            final TableCell<Veloav, String> cell = new TableCell<Veloav, String>() {
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

                            Veloav vl = tablevelo.getSelectionModel().getSelectedItem();
                            v.Deletevelo(vl.getId());
                            try {
                                listvelo();
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
        supprimerv.setCellFactory(cellFoctory);
        tablevelo.setItems(listvelo);
    }
        
        
        
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    public void setType(String type) {
        this.type.setText(type);
    }

    public void setMarque(String marque) {
        this.marque.setText(marque);
    }

    public void setQuantite(Integer quantite) {
        this.quantite.getValueFactory().setValue(quantite);
       
    }

    public void setPrix(Integer prix) {
       this.prix.getValueFactory().setValue(prix);
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite.setText(disponibilite);
    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    @FXML
    private String imagevelo() {
        
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("C:\\Users\\amine\\Desktop\\java pi\\E-bike\\src\\image"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images","*.png","*.jpg","*.gif"));


        File f = fc.showOpenDialog((Window)null);
        if (f != null) {
            System.out.println(f);
        } 
        return f.getName();
        
    }

    
    //navigation back

    @FXML
    public void btnHomeOnClick(ActionEvent event) throws IOException{
      
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/addevent.fxml"));
       ventevelo.getChildren().setAll(pane);
    }

    @FXML
    private void partbutton(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void velo_en_vente(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/velo.fxml"));
       ventevelo.getChildren().setAll(pane);
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
       ventevelo.getChildren().setAll(pane);
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/reclamation_back.fxml"));
       ventevelo.getChildren().setAll(pane);
    }

    @FXML
    private void piece_de_rechange(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/piece_r.fxml"));
       ventevelo.getChildren().setAll(pane);
    }

    @FXML
    private void type_de_piece(ActionEvent event) throws IOException {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/typepr.fxml"));
       ventevelo.getChildren().setAll(pane);
    }

    
    
    
    
    
}
