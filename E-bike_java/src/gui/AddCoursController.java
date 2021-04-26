/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;



import entities.cours;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import services.coursCRUD;

/**
 * FXML Controller class
 *
 * @author chedly
 */
public class AddCoursController implements Initializable {

    @FXML
    private TextField tffrais;
    @FXML
    private TextField tfnbmax;
    @FXML
    private Button btnAdd;
    @FXML
    private TextField tfduree;
    @FXML
    private ComboBox<String> cbcircuit;
    @FXML
    private DatePicker dpdate;
    @FXML
    private ComboBox<String> cbtype;
    @FXML
    private TextField tfnbp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cbcircuit.getItems().addAll("Manouba","Borj Louzir","Ariana");
        cbtype.getItems().addAll("Endurance","Defoulement");
        // TODO
    }    

    @FXML
    private void ajoutercours(ActionEvent event) {
       
            /// SAUVEGARDE DANS LA BD
            String resCircuit = cbcircuit.getValue();
            LocalDate resDate = dpdate.getValue();
            String resDuree = tfduree.getText();
            String resFrais = tffrais.getText();
            String resType = cbtype.getValue();
            String resNBmax = tfnbmax.getText();
            String resNBp = tfnbp.getText();
            cours p = new cours(14, resCircuit, resDate, resDuree, resFrais, resType, resNBmax, resNBp);
            coursCRUD pcd = new coursCRUD();
            //SmsSender S=new  SmsSender();
            //S.send("FootTunisie : votre terrain et ajouté avec succés ! Nom du terrain ,"+resNom +"Lieu : "+resLieu,"b");
            pcd.ajoutercours(p);
            JOptionPane.showMessageDialog(null, "cours ajouté");
            
                    
            
     
                   
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
                    .getResource("../gui/Affichagecours.fxml"));
            Parent root = loader.load();
            tffrais.getScene().setRoot(root);
    }


    private void pc(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../gui/Statistique.fxml"));
            Parent root = loader.load();
            tffrais.getScene().setRoot(root);
    }



    @FXML
    private void CalculT(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("../gui/Calcul.fxml"));
            Parent root = loader.load();
            tffrais.getScene().setRoot(root);
    }

    }




