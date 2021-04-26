/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Reclamation;
import entities.Velo_al;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import services.veloal_methode;
import services.veloav_methode;
import tools.MyConnection;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import services.reclamation_methode;


/**
 * FXML Controller class
 *
 * @author amine
 */
public class VeloalController implements Initializable {

    @FXML
    private TextField marque;
    @FXML
    private Spinner<Integer> quantite;
    @FXML
    private TextField type;
    @FXML
    private Spinner<Integer> prix;
int ID;
    @FXML
    private AnchorPane velo_a_louer;
  
        ObservableList<Velo_al> observ_list_data = FXCollections.observableArrayList();
        private Statement ste;
        private Connection con;
    @FXML
    private TextField affiche;
    @FXML
    private Button uploadbutton;
    @FXML
    private ListView listvelo;
static Velo_al velo;
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
    ObservableList<PieChart.Data> piechartdata;
    @FXML
    private PieChart piechart;
    @FXML
    private TextField rechAvan;
    @FXML
    private Button btnTrier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        prix.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5000));
      quantite.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 5000));
        Aff();
        
        Stat();
       piechart.setData(piechartdata);
      
    }    

    @FXML
    public void saveveloal() throws SQLException {
        veloal_methode v= new veloal_methode();
       
        
        try {
            v.editveloal(ID, new Velo_al(marque.getText(),type.getText(),prix.getValue(),quantite.getValue(),affiche.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(VeloController.class.getName()).log(Level.SEVERE, null, ex);
        }
     Aff();
        
        
        
    }

    
    public void editveloal( Velo_al velo  ) {
        
         ID = velo.getId();
         marque.setText(velo.getMarque());
         type.setText(velo.getType());
         affiche.setText(velo.getImage());
         quantite.getValueFactory().setValue(velo.getQuantite());
         prix.getValueFactory().setValue(velo.getPrix());
         
        
        
    }

    @FXML
    private void ajouterveloal(ActionEvent event) throws IOException {
        
        //Send notification
                              Notifications notificationBuild = Notifications.create()
                                      .title("Traitement D'ajout")
                                      .text("ajout de velo est avec succes")
                                      .graphic(null)
                                      .hideAfter(Duration.seconds(5))
                                      .position(Pos.CENTER)
                                      .onAction(new EventHandler<ActionEvent>() {
                                  @Override
                                  public void handle(ActionEvent event) {
                                      System.out.println("click here");
                                  }
                                  
                              });
                              notificationBuild.show();
        
        
        
        
                        File f = new File(affiche.getText());

        Velo_al vel = new Velo_al(marque.getText(), type.getText(), prix.getValue(), quantite.getValue(), f.getName());
        veloal_methode v= new veloal_methode();
        v.ajouter_veloal(vel);
                
        Files.copy(Paths.get(affiche.getText()),Paths.get("C:\\Users\\amine\\Desktop\\integration\\E-Bike-amine\\public\\upload\\images\\"+f.getName()),REPLACE_EXISTING);
        
        
        
        
    }
       public void Aff(){
          
           veloal_methode v= new veloal_methode();
        try {
            observ_list_data = v.listvelo();
        } catch (SQLException ex) {
            Logger.getLogger(List_velo_frontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listvelo.setItems(observ_list_data);
        listvelo.setCellFactory(new Callback<ListView<Velo_al>, javafx.scene.control.ListCell<Velo_al>>()
        {
            @Override
            public ListCell<Velo_al> call(ListView<Velo_al> listView)
            {
                return new ajout_veloal_list_back() ;
            }
        });
           
          
        
} 
       
       
     
    private Connection cnx;
    private ResultSet rs=null;
    private ResultSet rs1=null;
    private PreparedStatement pst,pst1;
    
    private void Stat(){
        
           ArrayList<Integer> np=new ArrayList<Integer>();
          ArrayList<String> cat=new ArrayList<String>();
    Connection cnx=MyConnection.getInstance().getConnection();

        piechartdata=FXCollections.observableArrayList();
    try {
       
        pst=cnx.prepareStatement("select * from velo_al");
        rs=pst.executeQuery();
       
        while(rs.next() )
        {
           
             
            pst1=cnx.prepareStatement("select count(*) as countab FROM velo_al WHERE prix="+rs.getInt("prix"));
           rs1=pst1.executeQuery(); 
        while(rs1.next())
        {       
            int i=Integer.valueOf(rs1.getString("countab"));
            piechartdata.add(new PieChart.Data(rs.getString("prix"),i));
            
            np.add(i);
            cat.add(rs.getString("prix"));
        }
      
    }
    } catch (SQLException ex) {
        System.out.println("stat velos");
    } 
     piechart.setData(piechartdata);
     
      }

    
    
    
    
    
    @FXML
     public void RechercheOnAction(){
          
           veloal_methode v= new veloal_methode();
           
        try {
            observ_list_data = v.Recher(rechAvan.getText()); 
        } catch (SQLException ex) {
            Logger.getLogger(List_velo_frontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listvelo.setItems(observ_list_data);
        listvelo.setCellFactory(new Callback<ListView<Velo_al>, javafx.scene.control.ListCell<Velo_al>>()
        {
            @Override
            public ListCell<Velo_al> call(ListView<Velo_al> listView)
            {
                return new ajout_veloal_list_back() ;
            }
        });
        
       
     }
    
   
     
     
     
     
     
     
     
       
       
       
       
       
       
       
       
       
       
   public void setType(String type) {
        this.type.setText(type);
    }

    public void setMarque(String marque) {
        this.marque.setText(marque);
    }

    public void setQuantite(Integer quantite) {
        this.quantite.getValueFactory().setValue(quantite);
       
    }

    public void setPrix(Integer prix) {
       this.prix.getValueFactory().setValue(prix);
    }

   @FXML
    public void btnHomeOnClick(ActionEvent event) throws IOException{
      
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/addevent.fxml"));
       velo_a_louer.getChildren().setAll(pane);
    }

    @FXML
    private void partbutton(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void velo_en_vente(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/velo.fxml"));
       velo_a_louer.getChildren().setAll(pane);
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
       velo_a_louer.getChildren().setAll(pane);
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/reclamation_back.fxml"));
       velo_a_louer.getChildren().setAll(pane);
    }

    @FXML
    private void piece_de_rechange(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/piece_r.fxml"));
       velo_a_louer.getChildren().setAll(pane);
    }

    @FXML
    private void type_de_piece(ActionEvent event) throws IOException {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/typepr.fxml"));
       velo_a_louer.getChildren().setAll(pane);
    }

    @FXML
    private void Uploadfile(ActionEvent event) {
                       
        FileChooser fc = new FileChooser();
        String path = fc.showOpenDialog(uploadbutton.getScene().getWindow()).getPath();
        affiche.setText(path);
 
    }

    @FXML
    private void btnTrierOnAction(ActionEvent event) {
            veloal_methode v= new veloal_methode();
           
        try {
            observ_list_data = v.Trier();
        } catch (SQLException ex) {
            Logger.getLogger(List_velo_frontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listvelo.setItems(observ_list_data);
        listvelo.setCellFactory(new Callback<ListView<Velo_al>, javafx.scene.control.ListCell<Velo_al>>()
        {
            @Override
            public ListCell<Velo_al> call(ListView<Velo_al> listView)
            {
                return new ajout_veloal_list_back() ;
            }
        });
    }


       
    
    
    
        
    }
    

