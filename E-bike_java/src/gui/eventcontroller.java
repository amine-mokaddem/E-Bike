/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Event;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import services.EventMethode;
import com.jfoenix.controls.JFXListView;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class eventcontroller implements Initializable {

    @FXML
    private TextField description;
    @FXML
    private TextField arivee;
    @FXML
    private TextField depart;
    @FXML
    private DatePicker date_alle;
    @FXML
    private TextField image;
    @FXML
    private Spinner<Integer> nbr_place;
    @FXML
    private JFXListView listv;

    Event e = null;
    int ID;
    Connection cnx;
    private PreparedStatement ste;
    private ResultSet rs;
    private Statement st;
    @FXML
    private TextField nom;
    private DatePicker dateretour;
    @FXML
    private DatePicker dateret;
    @FXML
    private Button event;
    @FXML
    private Button btnOrders;
    @FXML
    private Button btnCustomers;
    @FXML
    private Button btnMenus;
    @FXML
    private Button btnPackages;
    @FXML
    private Button btnSettings;
    @FXML
    private Button btnSignout;
    @FXML
    private Button btnSignout1;
    @FXML
    private Button event1;
    @FXML
    private AnchorPane addevent;
    private List<Event> data;
    ObservableList observ_list_data = FXCollections.observableArrayList();
    @FXML
    private Button AddButton;
    @FXML
    private Button enregistrer;
    @FXML
    private Button editer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setListView();
        } catch (SQLException ex) {
            Logger.getLogger(eventcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        nbr_place.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5000));
    }

    public void setListView() throws SQLException {

        EventMethode evm = new EventMethode();
        data = evm.listEvent();

        observ_list_data.setAll(data);
        listv.setItems(observ_list_data);
        listv.setCellFactory(new Callback<ListView<Event>, javafx.scene.control.ListCell<Event>>() {
            @Override
            public ListCell<Event> call(ListView<Event> listView) {
                return new listevent_item();
            }
        });

    }

    @FXML
    private void AddEvent(ActionEvent event) throws SQLException {
        EventMethode em = new EventMethode();

        em.AddEventA(new Event(nom.getText(), nbr_place.getValue(), depart.getText(), arivee.getText(), java.sql.Date.valueOf(date_alle.getValue()), java.sql.Date.valueOf(date_alle.getValue()), description.getText(), image.getText()));
        setListView();

    }

//    public void event_list() throws SQLException {
//        EventMethode em = new EventMethode();
//        ObservableList<Event> eventlist = em.listEvent();
//        listv.setItems(eventlist);
//        idt.setCellValueFactory(new PropertyValueFactory<Event, Integer>("id"));
//        nomt.setCellValueFactory(new PropertyValueFactory<Event, String>("nom"));
//        nbr_placet.setCellValueFactory(new PropertyValueFactory<Event, Integer>("nbr_place"));
//        departt.setCellValueFactory(new PropertyValueFactory<Event, String>("depart"));
//        ariveet.setCellValueFactory(new PropertyValueFactory<Event, String>("arivee"));
//        date_alleet.setCellValueFactory(new PropertyValueFactory<Event, Date>("date_allee"));
//        date_retourt.setCellValueFactory(new PropertyValueFactory<Event, Date>("date_retour"));
//        descriptiont.setCellValueFactory(new PropertyValueFactory<Event, String>("description"));
//        Callback<TableColumn<Event, String>, TableCell<Event, String>> cellFoctory = (TableColumn<Event, String> param) -> {
//            // make cell containing buttons
//            final TableCell<Event, String> cell = new TableCell<Event, String>() {
//                @Override
//                public void updateItem(String item, boolean empty) {
//                    super.updateItem(item, empty);
//                    //that cell created only on non-empty rows
//                    if (empty) {
//                        setGraphic(null);
//                        setText(null);
//
//                    } else {
//
//                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
//                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);
//
//                        deleteIcon.setStyle(
//                                " -fx-cursor: hand ;"
//                                + "-glyph-size:28px;"
//                                + "-fx-fill:#ff1744;"
//                        );
//
//                        deleteIcon.setOnMouseClicked(event -> {
//
//                            e = (Event) listv.getSelectionModel().getSelectedItem();
//                            em.DeleteEvent(e.getId());
//                            try {
//                                event_list();
//                            } catch (SQLException ex) {
//                                Logger.getLogger(eventcontroller.class.getName()).log(Level.SEVERE, null, ex);
//                            }
//
//                        });
//                        HBox managebtn = new HBox(deleteIcon);
//                        managebtn.setStyle("-fx-alignment:center");
//                        setGraphic(managebtn);
//                        setText(null);
//
//                    }
//                }
//
//            };
//
//            return cell;
//        };
//        editt.setCellFactory(cellFoctory);
//        listv.setItems(eventlist);
//    }
    @FXML
    private void editAccount() { // for updating existing account
		e = (Event) listv.getSelectionModel().getSelectedItem();
		ID = e.getId();
		description.setText(e.getDescription());
                nbr_place.getValueFactory().setValue(e.getNbr_place());
                arivee.setText(e.getArivee());
                depart.setText(e.getDepart());
                date_alle.setValue(localDate(e.getDate_allee().toString()));                              		
    }

    public static final LocalDate localDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(dateString, formatter);
        return localDate;
    }

    @FXML
    private void save() throws SQLException {
        EventMethode EM = new EventMethode();
        e.setNom(nom.getText());
        e.setDescription(description.getText());
        e.setArivee(arivee.getText());
        
        e.setDepart(depart.getText());
e.setImage(image.getText()); 
        Integer nbr = nbr_place.getValue();

            e.setNbr_place(nbr);
            Event ee= (Event) listv.getSelectionModel().getSelectedItems();
            e.setId(ee.getId());
            EM.modifyevent(e);
        

    }

    public void setDescription(String description) {
        this.description.setText(description);
    }

    public void setArivee(String arivee) {
        this.arivee.setText(arivee);
    }

    public void setDepart(String depart) {
        this.depart.setText(depart);
    }

    public void setDate_alle(Date date_alle) {
        this.date_alle.setValue(LocalDate.MAX);
    }

    public void setImage(String image) {
        this.image.setText(image);
    }

    public void setNbr_place(Integer nbr_place) {
        this.nbr_place.getValueFactory().setValue(nbr_place);
    }

    public void setNom(String nom) {
        this.nom.setText(nom);
    }

    public void setDateretour(Date dateretour) {
        this.dateret.setValue(LocalDate.MAX);
    }

    //navigation back
    @FXML
    public void btnHomeOnClick(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/addevent.fxml"));
        addevent.getChildren().setAll(pane);
    }

    @FXML
    private void partbutton(ActionEvent event) throws IOException {

    }

    @FXML
    private void velo_en_vente(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/velo.fxml"));
        addevent.getChildren().setAll(pane);
    }

    @FXML
    private void commande(ActionEvent event) throws IOException {
    }

    @FXML
    private void location(ActionEvent event) throws IOException {
    }

    @FXML
    private void velo_alouer(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/veloal.fxml"));
        addevent.getChildren().setAll(pane);
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/reclamation_back.fxml"));
        addevent.getChildren().setAll(pane);
    }

    @FXML
    private void piece_de_rechange(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/piece_r.fxml"));
        addevent.getChildren().setAll(pane);
    }

    @FXML
    private void type_de_piece(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/typepr.fxml"));
        addevent.getChildren().setAll(pane);
    }

}
