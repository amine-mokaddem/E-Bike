/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.Piece_r;
import entities.Reclamation;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import services.Piece_r_methode;
import services.reclamation_methode;
import tools.MyConnection;

/**
 * FXML Controller class
 *
 * @author amine
 */
public class Reclamation_backController implements Initializable {
    
    Connection cnx;
    private PreparedStatement ste;
    private ResultSet rs;
    private Statement st;
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
    
    public Reclamation_backController() {
         cnx = MyConnection.getInstance().getConnection();
    }
  
    @FXML
    private AnchorPane reclamation;
    @FXML
    private ListView listrec;
    @FXML
    private Button telecharger;
ObservableList observ_list_data = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     reclamation_methode v= new reclamation_methode();
        try {
            observ_list_data = v.listreclamation();
        } catch (SQLException ex) {
            Logger.getLogger(List_velo_frontController.class.getName()).log(Level.SEVERE, null, ex);
        }
        listrec.setItems(observ_list_data);
        listrec.setCellFactory(new Callback<ListView<Reclamation>, javafx.scene.control.ListCell<Reclamation>>()
        {
            @Override
            public ListCell<Reclamation> call(ListView<Reclamation> listView)
            {
                return new ajout_rec_list_back();
            }
        });
        
        
    }  
    
    
    @FXML
    void telechargerpdf(ActionEvent event) {
        
        Document doc = new Document();
         
        String sql = "Select * from reclamation" ;
        
        try {
            ste = cnx.prepareStatement(sql);
            rs= ste.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Reclamation_backController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            try {
                PdfWriter.getInstance(doc, new FileOutputStream("C:\\Users\\amine\\Desktop\\listeRec.pdf"));
                doc.open();
                
                doc.add(new Paragraph("La liste des reclamation",FontFactory.getFont("Arial",30)));
                doc.add(new Paragraph(" "));
                doc.add(new Paragraph(" "));
                
                PdfPTable table = new PdfPTable(6);
                table.setWidthPercentage(100);
                
                PdfPCell Cell ;
                ////////////////////////////////////////////////////
                 
                Cell = new PdfPCell (new Phrase("Nom",FontFactory.getFont("Comic Sans MS",12)));
                Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                Cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(Cell);
                
                Cell = new PdfPCell (new Phrase("Email",FontFactory.getFont("Comic Sans MS",12)));
                Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                Cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(Cell);
                
                Cell = new PdfPCell (new Phrase("Date",FontFactory.getFont("Comic Sans MS",12)));
                Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                Cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(Cell);
                
                Cell = new PdfPCell (new Phrase("Description",FontFactory.getFont("Comic Sans MS",12)));
                Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                Cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(Cell);
                
                Cell = new PdfPCell (new Phrase("Piece id",FontFactory.getFont("Comic Sans MS",12)));
                Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                Cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(Cell);
                
                Cell = new PdfPCell (new Phrase("Etat",FontFactory.getFont("Comic Sans MS",12)));
                Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                Cell.setBackgroundColor(BaseColor.GRAY);
                table.addCell(Cell);
                
                //////////////////////////////////////////////////////////////////////////
                
                while (rs.next()){
                
                Cell = new PdfPCell (new Phrase(rs.getString("nom").toString(),FontFactory.getFont("Arial",6)));
                Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                table.addCell(Cell);
                
                Cell = new PdfPCell (new Phrase(rs.getString("email").toString(),FontFactory.getFont("Arial",6)));
                Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               
                table.addCell(Cell);
                
                Cell = new PdfPCell (new Phrase(rs.getDate("datereclamation").toString(),FontFactory.getFont("Arial",6)));
                Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                table.addCell(Cell);
                
                Cell = new PdfPCell (new Phrase(rs.getString("description").toString(),FontFactory.getFont("Arial",6)));
                Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                table.addCell(Cell);
                
                Cell = new PdfPCell (new Phrase(rs.getString("piecer_id").toString(),FontFactory.getFont("Arial",6)));
                Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                table.addCell(Cell);
                
                Cell = new PdfPCell (new Phrase(rs.getString("etat").toString(),FontFactory.getFont("Arial",6)));
                Cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                
                table.addCell(Cell);
                
                }
                
                
                
                //////////////////////////////////////////////////////////////////////////
                
                
                
                doc.add(table);
                doc.close();
                try {
                    Desktop.getDesktop().open(new File("C:\\Users\\amine\\Desktop\\listeRec.pdf"));
                } catch (IOException ex) {
                    Logger.getLogger(Reclamation_backController.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
            } catch (DocumentException ex) {
                Logger.getLogger(Reclamation_backController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Reclamation_backController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Reclamation_backController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    

   @FXML
    public void btnHomeOnClick(ActionEvent event) throws IOException{
      
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/addevent.fxml"));
       reclamation.getChildren().setAll(pane);
    }

    @FXML
    private void partbutton(ActionEvent event) throws IOException {
        
    }

    @FXML
    private void velo_en_vente(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/velo.fxml"));
       reclamation.getChildren().setAll(pane);
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
       reclamation.getChildren().setAll(pane);
    }

    @FXML
    private void reclamation(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/reclamation_back.fxml"));
       reclamation.getChildren().setAll(pane);
    }

    @FXML
    private void piece_de_rechange(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/piece_r.fxml"));
       reclamation.getChildren().setAll(pane);
    }

    @FXML
    private void type_de_piece(ActionEvent event) throws IOException {
       AnchorPane pane = FXMLLoader.load(getClass().getResource("../gui/typepr.fxml"));
       reclamation.getChildren().setAll(pane);
    }

    
    
    
    
    
    
    
    
    
    
  
    
}
