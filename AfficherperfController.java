/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.PerformanceDao;
import com.esprit.dao.UserDao;
import com.esprit.entity.User;
import com.esprit.entity.performance;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AfficherperfController implements Initializable {

    @FXML
    private TableView<?> personsTable;
    @FXML
    private TableColumn<?, ?> prenomColonne;
    @FXML
    private TableColumn<?, ?> nomColonne;
    @FXML
    private TableColumn<?, ?> usernameColonne1;
    @FXML
    private TableColumn<?, ?> emailColonne;
    @FXML
    private TableColumn<?, ?> passColonne;
    @FXML
    private TableColumn<?, ?> roleColonne;
    @FXML
    private TableColumn<?, ?> icColonne;
    @FXML
    private Button ref;
    @FXML
    private TextField filterField;
    private final ObservableList<performance> dataList = FXCollections.observableArrayList();
    @FXML
    private Label idLabel;
    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;
    @FXML
    private TextField prenom;
    @FXML
    private TextField nom;
    @FXML
    private TextField username;
    @FXML
    private TextField role;
    @FXML
    private TextField id;
    @FXML
    private TextField mail;
    @FXML
    private PasswordField password;
    @FXML
    private Button mod;
    @FXML
    private Button sup;
    

    private ListData listdata = new ListData();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        {
ref.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        ObservableList<performance>       recla=FXCollections.observableArrayList();
        PerformanceDao pdao = PerformanceDao.getInstance();
        recla= pdao.displayAll();
        
        // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<performance> filteredData = new FilteredList<>(dataList, b -> true);
        
        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(performance -> {
                // If filter text is empty, display all persons.
                
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                
                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();
                
                if (performance.getDepart().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches first name.
                } else if (performance.getArivee().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }else if (performance.getDistance().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }else if (performance.getTemps().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                }
                else if (String.valueOf(performance.getTemps()).indexOf(lowerCaseFilter)!=-1)
                    return true;
                else
                    return false; // Does not match.
            });
        });
        
        // 3. Wrap the FilteredList in a SortedList.
        SortedList<performance> sortedData = new SortedList<>(filteredData);
        
        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(personsTable.comparatorProperty());
        
        // 5. Add sorted (and filtered) data to the table.
        personsTable.setItems(sortedData);
        
        personsTable.setItems(recla);
        
        
        
        
        
        prenomColonne.setCellValueFactory(cell -> cell.
                getValue().getDepartProperty());
        nomColonne.setCellValueFactory(cell -> cell.
                getValue().getAriveeProperty());
        usernameColonne1.setCellValueFactory(cell -> cell.
                getValue().getDisatanceProperty());
        emailColonne.setCellValueFactory(cell -> cell.
                getValue().getTempsProperty());
        
        icColonne.setCellValueFactory(cell -> cell.
                getValue().getIdProperty().asObject());
    }
});
      
        personsTable.setItems(listdata.getPersons());
        
        
        prenomColonne.setCellValueFactory(cell -> cell.
                getValue().getPrenomProperty());
        nomColonne.setCellValueFactory(cell -> cell.
                getValue().getNomProperty());
        usernameColonne1.setCellValueFactory(cell -> cell.
                getValue().getUsernameProperty());
        emailColonne.setCellValueFactory(cell -> cell.
                getValue().getMailProperty());
        passColonne.setCellValueFactory(cell -> cell.
                getValue().getMdpProperty());
        roleColonne.setCellValueFactory(cell -> cell.
                getValue().getRoleProperty()); 
          icColonne.setCellValueFactory(cell -> cell.
                getValue().getIdProperty().asObject()); 
          
          
          
        personsTable.setOnMouseClicked(event->{
        prenom.setText(String.valueOf(listdata.getPersons()
                .get(personsTable.getSelectionModel().getSelectedIndex())
                .getPrenom()));
        nom.setText(listdata.getPersons()
                .get(personsTable.getSelectionModel().getSelectedIndex())
                .getNom());
        username.setText(listdata.getPersons()
                .get(personsTable.getSelectionModel().getSelectedIndex())
                .getUsername());
           password.setText(listdata.getPersons()
                .get(personsTable.getSelectionModel().getSelectedIndex())
                .getMdp());
              mail.setText(listdata.getPersons()
                .get(personsTable.getSelectionModel().getSelectedIndex())
                .getMail());
                 role.setText(listdata.getPersons()
                .get(personsTable.getSelectionModel().getSelectedIndex())
                .getRole());
          id.setText(String.valueOf(listdata.getPersons()
                .get(personsTable.getSelectionModel().getSelectedIndex())
                .getId()));
    });
        
                sup.setOnAction(event -> {
                    
             int x=    Integer.parseInt(id.getText());
                UserDao pdao = UserDao.getInstance();
                  User p =          pdao.displayById(x);
                  pdao.delete(p);
          
                });
                       mod.setOnAction(event->{
                      int  x=    Integer.parseInt(id.getText());
            User p1 = new User (prenom.getText(), nom.getText(), username.getText(), password.getText(), mail.getText(), role.getText(), Integer.parseInt(id.getText()));
                     UserDao pdao = UserDao.getInstance();
                     pdao.update(p1);
                       });
        //Redirection vers l'interface PieChart
        //btn_pie.setOnAction(event->{
          //  try {
               // System.out.println("testttttttttttttt");
                //Parent pagePieChart=FXMLLoader.load(getClass().getResource("/com/esprit/view/PieChartView.fxml"));
               // Scene scene=new Scene(pagePieChart);
               // Stage stage=(Stage) ((Node) event.getSource())
                        //.getScene()
                        //.getWindow();
                //stage.setScene(scene);
                //stage.show();
            //} catch (IOException ex) {
               // Logger.getLogger(AfficherPersonneController.class.getName()).log(Level.SEVERE, null, ex);
            //}
       // });
    }

}

    }    
    

