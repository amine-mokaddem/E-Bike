/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import service.UserDao;

import entity.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import entity.User;
import ilearn.Ilearn;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableSet;
import javafx.event.EventHandler;
import javafx.print.JobSettings;
import javafx.print.PageLayout;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
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
    @FXML
    private TableColumn<User, String> passColonne;
    @FXML
    private TableColumn<User, String> roleColonne;
    @FXML
    private TextField nom;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
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
    @FXML
    private TextField mail;
    @FXML
    private TextField filterField;
    private final ObservableList<User> dataList = FXCollections.observableArrayList();
    @FXML
    private Button b;
    private ObservableList<User> masterData = FXCollections.observableArrayList();
    private ObservableList<User> filteredData = FXCollections.observableArrayList();
    @FXML
    private Button imprimer;
    @FXML
    private Label Menu;
    @FXML
    private Label MenuClose;
    @FXML
    private AnchorPane slider;
    @FXML
    private Label admin;
   @FXML
    private Button smsbut;
    @FXML
    private HBox ooooh;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
     User u=new User();
        UserDao us=new UserDao();
        try {
            u=us.getconnected();
        } catch (SQLException ex) {
            Logger.getLogger(AfficherPanierController.class.getName()).log(Level.SEVERE, null, ex);
        }
        admin.setText(u.getUsername());
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

                if (user.getPrenom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches first name.
                } else if (user.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches last name.
                } else if (user.getUsername().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (user.getMail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(user.getRole()).indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else {
                    return false; // Does not match.
                }
            });
        });

        // 3. Wrap the FilteredList in a SortedList. 
        SortedList<User> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        // 	  Otherwise, sorting the TableView would have no effect.
        sortedData.comparatorProperty().bind(personsTable.comparatorProperty());

        // 5. Add sorted (and filtered) data to the table.
        personsTable.setItems(sortedData);

        ref.setOnAction(event -> {

            ObservableList<User> recla = FXCollections.observableArrayList();
            UserDao pdao = UserDao.getInstance();
            recla = pdao.displayAll();

            //888888888888888888 
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

        personsTable.setOnMouseClicked(event -> {
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

            int x = Integer.parseInt(id.getText());
            UserDao pdao = UserDao.getInstance();
            User p2 = pdao.displayById(x);
            pdao.delete(p2);

        });
        mod.setOnAction(event -> {
            int x = Integer.parseInt(id.getText());
            User p1 = new User(prenom.getText(), nom.getText(), username.getText(), password.getText(), mail.getText(), role.getText(), Integer.parseInt(id.getText()));
            UserDao pdao = UserDao.getInstance();
            pdao.update(p1);
        });

        b.setOnAction((ActionEvent event) -> {
            FXMLLoader fxmLoader3 = new FXMLLoader(getClass().getResource("/view/Accueil.fxml"));
            Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            app_stage.hide();

        });
        imprimer.setOnAction(event -> {

            // Création du job d'impression.
            final PrinterJob printerJob = PrinterJob.createPrinterJob();
            // Affichage de la boite de dialog de configation de l'impression.    
            if (printerJob.showPrintDialog(personsTable.getScene().getWindow())) {
                final JobSettings settings = printerJob.getJobSettings();
                final PageLayout pageLayout = settings.getPageLayout();
                final double pageWidth = pageLayout.getPrintableWidth();
                final double pageHeight = pageLayout.getPrintableHeight();
                System.out.println(Printer.getAllPrinters());
                // Mise en page, si nécessaire.
                // Lancement de l'impression.
                if (printerJob.printPage(personsTable)) {
                    // Fin de l'impression.
                    printerJob.endJob();
                }
            }

        });
     
           
		
	

    }

    @FXML
    private void form(MouseEvent event) throws IOException {
        Ilearn m=new Ilearn();
        m.changeScene("/view/AfficherPersonne.fxml");
    }

    @FXML
    private void abon(MouseEvent event) throws IOException {
        Ilearn m=new Ilearn();
        m.changeScene("/view/ajouterperf.fxml");
    }

    @FXML
    private void stat(MouseEvent event) throws IOException {
        Ilearn m=new Ilearn();
        m.changeScene("/view/stat.fxml");
    }

    @FXML
    private void promo(MouseEvent event) throws IOException {
        Ilearn m=new Ilearn();
        m.changeScene("/view/Promotion.fxml");
    }

    @FXML
    private void deco(MouseEvent event) throws IOException {
          Ilearn m=new Ilearn();
        m.changeScene("/view/Login.fxml");
    }

    @FXML
    private void sendd(ActionEvent event) throws IOException {
        Ilearn m=new Ilearn();
        m.changeScene("/view/sendsms.fxml");
    }

    @FXML
    private void oooohOut(MouseEvent event) {
     ooooh.setStyle("-fx-background-color: transparent");
    }

    @FXML
    private void oooohIn(MouseEvent event) {
        
     ooooh.setStyle("-fx-background-color: #146886");
    }

      @FXML
    private void reclam(MouseEvent event) throws IOException {
       Ilearn m=new Ilearn();
        m.changeScene("/view/GererReclamationBK.fxml");  
    }

    @FXML
    private void demande(MouseEvent event) throws IOException {
         Ilearn m=new Ilearn();
        m.changeScene("/view/demandes.fxml");
    }

}
