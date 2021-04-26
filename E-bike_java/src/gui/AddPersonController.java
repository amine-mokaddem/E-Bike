/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Personne;
import java.awt.Dialog;
import java.awt.Image;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import services.PersonneCRUD;
//import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author House
 */
public class AddPersonController implements Initializable {

    @FXML
    private TextField tfNom;
    private ComboBox <String> tfType;
    private TextField tfTaille;
    private TextField tfLieu;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField tfNum;
    @FXML
    private TextField tfEmail;
    @FXML
    private TextField tfPrenom;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
      }    

    @FXML
    private void ajouterPersonne(ActionEvent event) {
       
            /// SAUVEGARDE DANS LA BD
            String resNom = tfNom.getText();
            String resPrenom = tfPrenom.getText();
            String resNum = tfNum.getText();
            String resEmail = tfEmail.getText();
            Personne p = new Personne(14, resNom, resPrenom, resNum, resEmail);
            PersonneCRUD pcd = new PersonneCRUD();
            //SmsSender S=new  SmsSender();
            //S.send("FootTunisie : votre terrain et ajouté avec succés ! Nom du terrain ,"+resNom +"Lieu : "+resLieu,"b");
            pcd.ajouterPersonne(p);
            JOptionPane.showMessageDialog(null, "Coach ajouté");
            
                    
            
     
                   
            //notif
         /*   Notifications notificationBuilder = Notifications.create().title("Terrain ajouté avec succés !")
                     .text("").graphic(null)
                     .hideAfter(javafx.util.Duration.seconds(5))
                    .position(Pos.CENTER).darkStyle()
                    .onAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println("Clicked on notification");
    
                }
                    });
            notificationBuilder.showInformation();  */
                  
                   
            
            //REDIRECTIO
    }

   
    
    
    
    
    @FXML
    private void AfficherT(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../gui/AffichageCoach.fxml"));
            Parent root = loader.load();
            tfNom.getScene().setRoot(root);
    }


    private void pc(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../gui/Statistique.fxml"));
            Parent root = loader.load();
            tfNom.getScene().setRoot(root);
    }

    private void PaiAjout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../gui/Calcul.fxml"));
            Parent root = loader.load();
            tfNom.getScene().setRoot(root);
    }

}