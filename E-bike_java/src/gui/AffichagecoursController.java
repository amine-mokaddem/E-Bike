/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



import entities.cours;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import services.coursCRUD;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AffichagecoursController implements Initializable {
    

 
    @FXML
    private TableView<cours> affichage;
public ObservableList<cours> observableListLocataire = FXCollections.observableArrayList();
   
 
    @FXML
    private TableColumn<cours, String> rCircuit;
    @FXML
    private TableColumn<cours, LocalDate> rDate;
    @FXML
    private TableColumn<cours, String> rDuree;
    @FXML
    private TableColumn<cours, String> rfrais;
    @FXML
    private TableColumn<cours, String> rType;
    @FXML
    private TableColumn<cours, String> rnbmax;
    @FXML
    private TableColumn<cours, String> rnbp;
    @FXML
    private TextField nbmax1;
    @FXML
    private TextField duree1;
    @FXML
    private Button Supp1;
    @FXML
    private Button Rech1;
    @FXML
    private TextField frais1;
    @FXML
    private Button Rech11;
    @FXML
    private ComboBox<String> circuit1;
    @FXML
    private DatePicker date1;
    @FXML
    private ComboBox<String> type1;
    @FXML
    private TextField nb1;
    @FXML
    private TextField id11;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
        coursCRUD pcd = new coursCRUD();
            
            rCircuit.setCellValueFactory(new PropertyValueFactory<>("circuit"));
            rDate.setCellValueFactory(new PropertyValueFactory<>("datecours"));
            rDuree.setCellValueFactory(new PropertyValueFactory<>("dureecours"));
            rfrais.setCellValueFactory(new PropertyValueFactory<>("frais"));
             rType.setCellValueFactory(new PropertyValueFactory<>("typecours"));
            rnbmax.setCellValueFactory(new PropertyValueFactory<>("nombremax"));
            rnbp.setCellValueFactory(new PropertyValueFactory<>("nombreparticip"));
                                                                                                                                              
            affichage.setItems(pcd.displaycours());
                 
    }

    @FXML
    private void RechercherID(ActionEvent event) {
        
        try {
            String requete = "select *FROM cours where id=?";
            PreparedStatement pst = MyConnection.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setString(1,id11.getText() );
             ResultSet rs = pst.executeQuery();
              while(rs.next()){
                
               circuit1.getItems().addAll("Manouba","Borj Louzir","Ariean");
               date1.setValue(rs.getDate(3).toLocalDate());
               duree1.setText(rs.getString(4));
               frais1.setText(rs.getString(5));
               type1.getItems().addAll("Endurance","Defoulement");
               nbmax1.setText(rs.getString(7));
               nb1.setText(rs.getString(8));
    
            }
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
       
        
    }

    @FXML
    private void SupprimerID(ActionEvent event) throws IOException {
        
       
      
           
           
                      JOptionPane jop = new JOptionPane();
    int option = jop.showConfirmDialog(null, "Voulez-vous vraiment supprimer le cours?", "Supression", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
    if(option == JOptionPane.OK_OPTION) {
        
        //la fonction supprimer
        cours r=affichage.getSelectionModel().getSelectedItem();
           coursCRUD pcd = new   coursCRUD();

          pcd.supprimerPersonne(r);
           affichage.setItems(pcd.displaycours());
             
    }       
    }

    private void Modifer(ActionEvent event) {
        String id = id11.getText();
         Integer id1 = Integer.parseInt(id);
         String ciruitcours = circuit1.getValue();
            LocalDate datecours = date1.getValue();
            String dureecours = duree1.getText();
            String frais= frais1.getText();
            String typecours = type1.getValue();
            String nombremax = nbmax1.getText();
            String nombreparticip= nb1.getText();
            coursCRUD pcd = new coursCRUD();
            pcd.updatecours(id1,ciruitcours,datecours,dureecours,frais,typecours,nombremax,nombreparticip);
            affichage.setItems(pcd.displaycours());
    }

  
    @FXML
    private void AjouterT(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("AddCours.fxml"));
            Parent root = loader.load();
            circuit1.getScene().setRoot(root);
    }

    
    
    

    @FXML
    private void tabAffiche(MouseEvent event) {
        cours p = affichage.getSelectionModel().getSelectedItem();
         id11.setText(String.valueOf(p.getId()));
            circuit1.setValue(p.getCircuitcours());
            date1.setValue(p.getDatecours());
            duree1.setText(p.getDureecours());
            frais1.setText(p.getFrais());
            type1.setValue(p.getTypecours());
            nbmax1.setText(p.getNombremax());
            nb1.setText(p.getNombreparticip());
                  
    }

    private void StatT(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("Statistique.fxml"));
            Parent root = loader.load();
            //nom1.getScene().setRoot(root); pour utiliser boutton Retour
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
             stage.setTitle("Statistique");
            stage.show();
    }

    private void PaieT(ActionEvent event) throws IOException {
        
       FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("Calcul.fxml"));
            Parent root = (Parent)loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
             stage.setTitle("Calcul Paiement");
            stage.show();
    }

    @FXML
    private void AfficherT(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("affichagecours.fxml"));
            Parent root = loader.load();
            circuit1.getScene().setRoot(root);
    }

  

    @FXML
    private void CalculeT(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("calcul.fxml"));
            Parent root = loader.load();
            circuit1.getScene().setRoot(root);
    }

    @FXML
    private void ModiferD(ActionEvent event) {
    }

}

  
    



   



    
    








