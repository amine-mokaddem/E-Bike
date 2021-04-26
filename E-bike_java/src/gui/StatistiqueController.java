/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class StatistiqueController implements Initializable {

    @FXML
    private BarChart<?, ?> dt;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    private TextField pms;
    @FXML
    private TextField p1;
    @FXML
    private TextField p3;
    @FXML
    private TextField p4;
    @FXML
    private TextField p2;
    @FXML
    private TextField anneé;
    @FXML
    private Label label;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label.setWrapText(true); //ecrire un label sur plusieurs ligne
                     
    }    

    @FXML
    
    private void stat(ActionEvent event) {
        
        
        
        
        int dimS = 10*20;
        int dimFS = 15*25;
        int dimT = 25*35;
        int dimG = 30*50;
        
        
        
            
            int a = Integer.parseInt(p1.getText());
            int b = Integer.parseInt(p2.getText());
            int c = Integer.parseInt(p3.getText());
            int d = Integer.parseInt(p4.getText());
            
           
            
                
            
            XYChart.Series set1 = new XYChart.Series<>();
            for(int i=0;i<5;i++){ 
            set1.setName(anneé.getText());
            }
            set1.getData().add(new XYChart.Data("Sable",a*dimS));
            set1.getData().add(new XYChart.Data("Foot Salle",b*dimFS));
            set1.getData().add(new XYChart.Data("Tarton",c*dimT));
            set1.getData().add(new XYChart.Data("Gazon",d*dimG));
            dt.getData().addAll(set1);
                      
    }

    @FXML
    private void retourstat(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass()
                    .getResource("AffichageTerrains.fxml"));
            Parent root = loader.load();
            p1.getScene().setRoot(root);
    }      
}
