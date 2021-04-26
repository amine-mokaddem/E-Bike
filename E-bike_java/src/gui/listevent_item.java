/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Event;
import java.awt.Rectangle;
import java.nio.file.Paths;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author amine
 */
//public class listevent_item extends ListCell<Event> {
//
//    private FXMLLoader mLLoader;
//    
//    @FXML
//    private Label label1;
//    
//    @FXML
//    private GridPane gridPane;
//    
//    @FXML
//    private ImageView imgView;
//
//    @Override
//    public void updateItem(Event data, boolean empty) {
//         ImageView imageView = new ImageView();
//     Image img  = new Image(data.getImage());
//
//        super.updateItem(data, empty);
//        if (data != null) {
//            
//            if (mLLoader == null) {
//                mLLoader = new FXMLLoader(getClass().getResource("/gui/list_cell.fxml"));
//                mLLoader.setController(this);
//
//                try {
//                    mLLoader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//            }
//            
//            //setGraphic(this);
//            label1.setText(data.getNom());
//            
//        
//            setGraphic(gridPane);
//        }
//    }
//
//}
public class listevent_item extends ListCell<Event> { 
  
    private final GridPane gridPane = new GridPane(); 
    private final ImageView brandIcon = new ImageView(); 
    private final Label brandLabel = new Label(); 
    private final Label modelLabel = new Label();
    private final Label dateD = new Label(); 
    private final Label dateR = new Label(); 
    private final Rectangle colorRect = new Rectangle(10, 10); 
    private final Label descriptionLabel = new Label(); 
    private final ImageView carIcon = new ImageView();
    
    private final AnchorPane content = new AnchorPane(); 
  
    public listevent_item() { 
        carIcon.setFitWidth(75); 
        carIcon.setPreserveRatio(true); 
        GridPane.setConstraints(carIcon, 0, 0, 1, 3); 
        GridPane.setValignment(carIcon, VPos.TOP); 
        // 
        modelLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 1.5em;"); 
        GridPane.setConstraints(modelLabel, 1, 0); 
        // 
        brandLabel.setStyle("-fx-font-size: 0.9em; -fx-font-style: italic; -fx-opacity: 0.5;"); 
        GridPane.setConstraints(brandLabel, 2, 0); 
        dateD.setStyle("-fx-font-size: 1.2em; -fx-font-style: italic; -fx-opacity: 0.9;"); 
        GridPane.setConstraints(dateD, 10, 0); 
        dateR.setStyle("-fx-font-size: 1.2em; -fx-font-style: italic; -fx-opacity: 0.9;"); 
        GridPane.setConstraints(dateR, 11, 0); 
        // 
        brandIcon.setFitWidth(22); 
        brandIcon.setPreserveRatio(true); 
        GridPane.setConstraints(brandIcon, 3, 0); 
        GridPane.setValignment(brandIcon, VPos.CENTER); 
        // 
        
        descriptionLabel.setStyle("-fx-opacity: 0.75;"); 
        
    
        GridPane.setConstraints(descriptionLabel, 1, 1); 
        GridPane.setColumnSpan(descriptionLabel, Integer.MAX_VALUE);
        GridPane.setConstraints(dateD, 2, 0);
        GridPane.setColumnSpan(dateD, 25); 
        GridPane.setConstraints(dateR, 3, 1);
        GridPane.setColumnSpan(dateR, 45);
        //         
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        
                gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 
        gridPane.getColumnConstraints().add(new ColumnConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, HPos.LEFT, true)); 

                gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 

        
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.NEVER, VPos.CENTER, true)); 
        gridPane.getRowConstraints().add(new RowConstraints(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE, Priority.ALWAYS, VPos.CENTER, true)); 
        gridPane.setHgap(6); 
        gridPane.setVgap(6); 
        gridPane.getChildren().setAll(carIcon, modelLabel, brandLabel,dateD,dateR, brandIcon, descriptionLabel); 
        AnchorPane.setTopAnchor(gridPane, 0d); 
        AnchorPane.setLeftAnchor(gridPane, 0d); 
        AnchorPane.setBottomAnchor(gridPane, 0d); 
        AnchorPane.setRightAnchor(gridPane, 0d); 
        content.getChildren().add(gridPane); 
    } 
  
  
    @Override 
    public void updateItem(Event item, boolean empty) { 
        super.updateItem(item, empty); 
        setGraphic(null); 
        setText(null); 
        setContentDisplay(ContentDisplay.LEFT); 
        if (!empty && item != null) { 
            
            
            modelLabel.setText(item.getNom()); 
            System.out.println(item.getDate_allee().toString());
             dateD.setText(item.getDate_allee().toString()+"  "+":"+" "+"Date ALLER" );
             System.out.println(item.getDate_retour().toString());
             dateR.setText(item.getDate_retour().toString()+"  "+":"+" "+"Date RETOUR");
             

                   carIcon.setImage(new Image(Paths.get("").toAbsolutePath().toUri().toString()+"src/image/"+item.getImage())); 
            descriptionLabel.setText(item.getDescription()); 
            //colorRect.setFill(item.()); 
            setText(null); 
            setGraphic(content); 
            setContentDisplay(ContentDisplay.CENTER); 
        } 
    } 
}