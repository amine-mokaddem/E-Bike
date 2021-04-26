/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.stripe.exception.StripeException;
import entities.Commande;
import entities.Veloav;
import static gui.AfficherdetController.v;
import static gui.List_panierController.prix_total;
import java.net.URL;
import static gui.Payment_sans_carteController.com;
import static gui.Payment_sans_carteController.comlst;
import static gui.Payment_sans_carteController.s;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import services.commande_methode;
import services.stripe_methode;
import services.veloav_methode;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class PaymentcardController implements Initializable {

    @FXML
    private ImageView img_visa;
    @FXML
    private ImageView img_master;
    @FXML
    private TextField txt_carte;
    @FXML
    private TextField année;
    @FXML
    private TextField mois;
    @FXML
    private PasswordField txt_code;
    @FXML
    private Label lb_error;
    @FXML
    private Button btn_ok;
    @FXML
    private Button btn_cancel;
    stripe_methode st = new stripe_methode();
    commande_methode cc = new commande_methode();
    veloav_methode vv =new veloav_methode();
    int prix_final;
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void valider(ActionEvent event) throws StripeException, IOException, SQLException {
        st.makecard(s, txt_carte.getText(), mois.getText(), année.getText(), txt_code.getText());
        if(comlst.size() != 0){
            prix_final=prix_total;
            List<Commande> data = comlst;
              for (Commande c : data) {
         cc.ajoutercommande(c);
        }
         st.payer(prix_final, s);  
                 System.out.println("commande ajouté");
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("payment");
        alert.setContentText("le paiement a été effectué avec succès!");
        alert.showAndWait();
        }else{
        int prix_final = com.getQuantité() * v.getPrix()*100;
        st.payer(prix_final, s);
        System.out.println(com.toString());
        System.out.println("payment succccees ");
        cc.ajoutercommande(com);
        cc.commandetable(vv.getidvelobymarque(v.getMarque()), cc.getidcommandebymail(com.getEmail()));
        System.out.println("commande ajouté");
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("payment");
        alert.setContentText("le paiement a été effectué avec succès!");
        alert.showAndWait();
         AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/list_velo_front.fxml"));
       anchor.getChildren().setAll(pane);
        } //cc.commandetable(v.getId(), com.getId());
    }
    @FXML
    private void abondonner(ActionEvent event) throws IOException{
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/list_velo_front.fxml"));
       anchor.getChildren().setAll(pane);
    }

}
