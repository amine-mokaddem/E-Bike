/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Reclamation;
import javafx.scene.control.ListCell;

/**
 *
 * @author amine
 */
public class ajout_rec_list_back  extends ListCell<Reclamation> {
    ajout_rec_list_back(){
         super();
    }
   @Override
    public void updateItem(Reclamation data, boolean empty)
    {
       
        super.updateItem(data,empty);
        if(data != null)
        {
            one_item_rec_back data_controller = new one_item_rec_back();
            data_controller.setinfo(data);
            setGraphic(data_controller.getBox());
            
           
        }
    }  
    
    
}
