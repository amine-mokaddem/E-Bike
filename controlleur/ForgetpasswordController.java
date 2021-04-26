/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import controller.mail;
import service.UserDao;
import entity.User;
import ilearn.Ilearn;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author EMMI
 */
public class ForgetpasswordController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField emailinput;

    @FXML
    private Button confirm;

    @FXML

    void sendmail(ActionEvent event) throws Exception {

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        confirm.setOnAction((ActionEvent event) -> {
            UserDao pdao = UserDao.getInstance();

            String mail_recup = emailinput.getText();
            try {

                User u = pdao.displayUserByEmail(mail_recup);
                               /* System.out.println("ur pwd"+u.getMdp());
                                System.out.println("ur pwd 2"+u.getMdpProperty());

                System.out.println(u.getMailProperty());
                System.out.println(u.getPrenomProperty());

                System.out.println("gggg");*/

               
                String mdpdecrypter = pdao.decrypt(u.getMdp());
                System.out.println("mot de passe crypter "+mdpdecrypter);
                System.out.println(mail_recup);

                mail.sendmail(mail_recup,mdpdecrypter);
            } catch (Exception ex) {
                Logger.getLogger(ForgetpasswordController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Ilearn m=new Ilearn();
            try {
                m.changeScene("/view/Login.fxml");
            } catch (IOException ex) {
                Logger.getLogger(ForgetpasswordController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

}
