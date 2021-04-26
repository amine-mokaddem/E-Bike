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
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.AnchorPane;
import services.commande_methode;

import services.stripe_methode;
import services.user_methode;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class Payment_sans_carteController implements Initializable {
 static Commande com;
 static String s= null;
    @FXML
    private Label marquevelo;
    @FXML
    private ImageView imgvelo;
    @FXML
    private Label montant;
    @FXML
    private TextField nom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField email;
    @FXML
    private TextField quantite;
    @FXML
    private Label lb_error;
    @FXML
    private Button btn_ok;
    @FXML
    private Button btn_cancel;
    @FXML
    private AnchorPane anchor;
stripe_methode st= new stripe_methode();
public static List<Commande> comlst = new ArrayList<>();
commande_methode c= new commande_methode();
    @FXML
    private TextField numtel;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         if(Panier.lst_shop.size() != 0){
             montant.setText(""+prix_total+" DT");
              montant.setVisible(false);
               marquevelo.setVisible(false);
         }
         else{
        File file = new File("C:\\Users\\amine\\Desktop\\java pi\\integration\\E-Bike-amine\\public\\upload\\images\\"+v.getImagev());
            imgvelo.setImage(new Image(file.toURI().toString()));
            montant.setText(""+v.getPrix()+" DT");
            marquevelo.setText(v.getMarque());
         } 

    
    }    


    @FXML
    private void valider(ActionEvent event) throws SQLException, IOException, StripeException {
        
          long millis=System.currentTimeMillis();  
        java.sql.Date date=new java.sql.Date(millis);
        int num=Integer.parseInt(numtel.getText());
       int qt=Integer.parseInt(quantite.getText());
        //System.out.println(qt);
        if( Panier.lst_shop.size() != 0){
              List<Veloav> data = Panier.lst_shop;
              for (Veloav f : data) {
                  Commande c = new Commande(adresse.getText(),num,nom.getText(),email.getText(),"non traité",qt,date);
                  comlst.add(c);
              }
        }else{
        com = new Commande(adresse.getText(),num,nom.getText(),email.getText(),"non traité",qt,date);}
        
        user_methode u = new user_methode();
        s=u.Getstripe_from_email(email.getText());
        System.out.println(s);
        if (s==null){
           s= st.makecus( nom.getText(),email.getText());
           u.ajouterstripeid(s, email.getText());
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/paymentcard.fxml"));
       anchor.getChildren().setAll(pane);
       
        }
        else {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/paymentcard.fxml"));
       anchor.getChildren().setAll(pane);
        }
        
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
