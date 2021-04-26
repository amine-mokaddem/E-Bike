/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Event;
import java.io.IOException;
import static java.nio.file.Files.list;
import java.sql.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;



/**
 *
 * @author amine
 */
public class main extends Application {
    Event event;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
       //FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/addevent.fxml"));
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/login.fxml"));

      //e.setInfo(new Event(4,  "amine", 20, "test", "test",new Date(10/02/2020),new Date(10/02/2020), "abcd", "abcd"));
      

            Parent root = loader.load();
            Scene scene = new Scene(root);
             
            primaryStage.setTitle("E-bike");
            primaryStage.setScene(scene);
            JMetro jMetro = new JMetro(Style.LIGHT);
            jMetro.setScene(scene);
            
            primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
