/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Personne;
import services.PersonneCRUD;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AffichageCoachController implements Initializable {
    

    @FXML
    private TableColumn<Personne, String> rNom;
    @FXML
    private TableColumn<Personne, String> rPrenom;
    @FXML
    private TableColumn<Personne, String> rNum;
    @FXML
    private TableColumn<Personne, String> rEmail;
    @FXML
    private TableView<Personne> affichage;
public ObservableList<Personne> observableListLocataire = FXCollections.observableArrayList();
   
    @FXML
    private TextField id1;
    private TextField nom1;
    @FXML
    private TextField Prenom1;
    private TextField taille1;
    private TextField lieu1;
    @FXML
    private Button Rech1;
    @FXML
    private Button Supp1;
    @FXML
    private Button Rech11;
    private TextField Sur1;
    @FXML
    private TextField Num1;
    @FXML
    private TextField Email1;
    @FXML
    private TextField Nom1;
    @FXML
    private Button export;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
       
        PersonneCRUD pcd = new PersonneCRUD();
            
            rNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            rPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            rNum.setCellValueFactory(new PropertyValueFactory<>("numtel"));
            rEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
                                                                                                                                              
            affichage.setItems(pcd.displayTerrain());
                 
    }

    @FXML
    private void RechercherID(ActionEvent event) {
        
        try {
            String requete = "select *FROM coach where id=?";
            PreparedStatement pst = MyConnection.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setString(1,id1.getText() );
             ResultSet rs = pst.executeQuery();
              while(rs.next()){
                
               Nom1.setText(rs.getString(2));
               Prenom1.setText(rs.getString(3));
               Num1.setText(rs.getString(4));
               Email1.setText(rs.getString(5));
    
            }
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
       
        
    }

    @FXML
    private void SupprimerID(ActionEvent event) throws IOException {
        
       
      
           
           
                      JOptionPane jop = new JOptionPane();
    int option = jop.showConfirmDialog(null, "Voulez-vous vraiment supprimer le coach?", "Supression", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
    if(option == JOptionPane.OK_OPTION) {
        
        //la fonction supprimer
        Personne r=affichage.getSelectionModel().getSelectedItem();
           PersonneCRUD pcd = new   PersonneCRUD();

          pcd.supprimerPersonne(r);
           affichage.setItems(pcd.displayTerrain());
             
    }       
    }

    @FXML
    private void Modifer(ActionEvent event) {
        String id = id1.getText();
         Integer id1 = Integer.parseInt(id);
         String nom = Nom1.getText();
            String prenom = Prenom1.getText();
            String numtel = Num1.getText();
            String email= Email1.getText();
            PersonneCRUD pcd = new PersonneCRUD();
            pcd.updateTerrain(id1,nom,prenom,numtel,email);
            affichage.setItems(pcd.displayTerrain());
    }

    private void calculS(ActionEvent event) {
        
        try {
            String requete = "select *FROM tcoach where id=?";
            PreparedStatement pst = MyConnection.getInstance().getConnection()
                    .prepareStatement(requete);
            pst.setString(1,id1.getText() );
             ResultSet rs = pst.executeQuery();
              while(rs.next()){
                  
                
               if (taille1.getText().equals("10x20"))
               {
                   Sur1.setText("200 m2");
               } 
               
               else if (taille1.getText().equals("15x25"))
               {
                   Sur1.setText("375 m2");
               }
               
               else if (taille1.getText().equals("25x35"))
               {
                   Sur1.setText("875 m2");
               }
               
               else if (taille1.getText().equals("30x50"))
               {
                   Sur1.setText("1500 m2");
               }
                
    
            }
          
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void AjouterT(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("AjouterCoach.fxml"));
            Parent root = loader.load();
            Nom1.getScene().setRoot(root);
    }

    
    
    

    @FXML
    private void tabAffiche(MouseEvent event) {
        Personne p = affichage.getSelectionModel().getSelectedItem();
            id1.setText(String.valueOf(p.getId()));
            Nom1.setText(p.getNom());
            Prenom1.setText(p.getPrenom());
            Num1.setText(p.getNumtel());
            Email1.setText(p.getEmail());
                  
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
    private void AfficherT(ActionEvent event) {
    }

    @FXML
    private void exporter(ActionEvent event) {
        try{
        String query= "Select * From coach";
        PreparedStatement pst =MyConnection.getInstance().getConnection().prepareStatement(query);
        ResultSet rs = pst.executeQuery();
       
        XSSFWorkbook  wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Coach details");
        XSSFRow header = sheet.createRow(0);
        header.createCell(0).setCellValue("id");
        header.createCell(1).setCellValue("nom");
        header.createCell(2).setCellValue("Prenom");
        header.createCell(3).setCellValue("Numero telephone");
        header.createCell(4).setCellValue("Email");
        
          int index =1;
          while(rs.next()) {
             XSSFRow row = sheet.createRow(index);
             row.createCell(0).setCellValue(rs.getInt("id"));
             row.createCell(1).setCellValue(rs.getString("nom"));
             row.createCell(2).setCellValue(rs.getString("prenom"));
             row.createCell(3).setCellValue(rs.getString("numtel"));
             row.createCell(4).setCellValue(rs.getString("Email"));
             
             
             index++;
          }
          FileOutputStream fileOut = new FileOutputStream("Coach.xlsx");
          wb.write(fileOut);
          fileOut.close();
         
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Votre document Excel a ete créé !");
        alert.showAndWait();
       
        pst.close();
        rs.close();
        }
        catch (SQLException | FileNotFoundException ex){
            Logger.getLogger(AffichageCoachController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AffichageCoachController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

  
    



   



    
    








