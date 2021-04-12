/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.controller;

import com.esprit.dao.UserDao;
import com.esprit.entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class AfficherPersonneController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TableView<User> personsTable;
    private TableColumn<User, String> NomColonne;
    private TableColumn<User, String> PrenomColonne;

    @FXML
    private Label idLabel;
    @FXML
    private Label nomLabel;
    @FXML
    private Label prenomLabel;

    private ListData listdata = new ListData();

    //@FXML
    //private Button btn_pie;
    @FXML
    private TableColumn<User, String> prenomColonne;
    @FXML
    private TableColumn<User, String> nomColonne;
    @FXML
    private TableColumn<User, String> usernameColonne1;
    @FXML
    private TableColumn<User, String> emailColonne;
    private TableColumn<User, String> passColonne;
    private TableColumn<User, String> roleColonne;
    @FXML
    private TextField nom;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    private TextField role;
    @FXML
    private TextField prenom;
    @FXML
    private Button mod;
    @FXML
    private Button sup;
    @FXML
    private Button ref;
    @FXML
    private TableColumn<User, Integer> icColonne;
    @FXML
    private TextField id;
    private TextField mail;
    private TextField filterField;
        private final ObservableList<User> dataList = FXCollections.observableArrayList();
    @FXML
    private TextField filtredfield;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
ref.setOnAction(event->{
        ObservableList<User>       recla=FXCollections.observableArrayList();
                  UserDao pdao = UserDao.getInstance();
                    recla= pdao.displayAll();
        
     // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<User> filteredData = new FilteredList<>(dataList, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		filterField.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (user.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (user.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}else if (user.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}else if (user.getMdp().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}else if (user.getMail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(user.getRole()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<User> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(personsTable.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		personsTable.setItems(sortedData);
    
        personsTable.setItems(recla);
        
        
        
        
        
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
