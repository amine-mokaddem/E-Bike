/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import service.UserDao;
import entity.User;
import ilearn.Ilearn;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author LEGION
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tfuser;
    @FXML
    private TextField tfpass;
    @FXML
    private Button login;
    @FXML
    private Button singup;
    @FXML
    private Label label;
    @FXML
    private Label label1;
    @FXML
    private CheckBox check;
    @FXML
    private Text p;
    @FXML
    private Button fp;

    /**
     * Initializes the controller class.
     */
    
  
   
    
    
    
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        
    
   
         login.setOnAction(event -> {
                         UserDao pdao = UserDao.getInstance();

           String mdpcrypter = pdao.encrypt(tfpass.getText());
            User p = new User(tfuser.getText(), mdpcrypter);
            
            
          int x=  pdao.connex(p);
            
            
          if (x==0)
          {
         Ilearn m =new Ilearn();
       
                             try {
                                 m.changeScene("/view/AfficherPersonne.fxml");
                                 
                             } catch (IOException ex) {
                                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                             }
                  label1.setText("mot de passe confirmé");
                 
          }
          else if (x==1)
          {
                try {
//****************************************************
//                    FXMLLoader loader =new FXMLLoader(getClass().getResource("/view/EtudiantHome.fxml"));
//        Parent root = (Parent) loader.load();
//        
//        Stage window= (Stage) label1.getScene().getWindow();
//        window.setScene(new Scene(root)); 
//        EtudiantHomeController a= loader.getController();
//        a.setState("i");
//        
                    //*****************************************************
                 FXMLLoader fxmLoader = new FXMLLoader(getClass().getResource("/view/interfaceens.fxml"));
                 Parent root1 = (Parent) fxmLoader.load();
                 Stage stage = new Stage();
                 stage.setScene(new Scene(root1));
                 stage.show();} catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }  
                label1.setText("mot de passe confirmé");
          }
          else if (x==2)
          {
              Ilearn m =new Ilearn();
       
                             try {
                                 m.changeScene("/view/EtudiantHome.fxml");
                                 
                             } catch (IOException ex) {
                                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                             }
                  label1.setText("Your password has been confirmed");
          }
                        

          
          else{
              label.setText("Your password is not  correct!");
          }
            
            
        });
         
                  singup.setOnAction(event -> {
            
             try {
                 Ilearn m =new Ilearn();
       
                      
                                 m.changeScene("/view/sign_up.fxml");
             
             } catch (IOException ex) {
                 Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
             }

        });
                  
               check.setOnAction((ActionEvent event) -> {  
                   if( check.isSelected()){
                       p.setText(tfpass.getText());
                       p.setVisible(true);
                       tfpass.setVisible(false);
                       return;
                  
                   }
                       tfpass.setText(p.getText());
                          tfpass.setVisible(true);
                          p.setVisible(false);
               
               });
              fp.setOnAction(event -> {
              try {
                
                Parent page2 = FXMLLoader.load(getClass().getResource("/view/forgetpassword.fxml"));
   
                Scene scene = new Scene(page2);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
                  
        
}
}
