/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Velo_al;
import javafx.scene.control.ListCell;

/**
 *
 * @author amine
 */
public class ajout_veloal_list_back  extends ListCell<Velo_al> {
    ajout_veloal_list_back(){
         super();
    }
    @Override
    public void updateItem(Velo_al data, boolean empty)
    {
       
        super.updateItem(data,empty);
        if(data != null)
        {
            one_item_veloal_back data_controller = new one_item_veloal_back();
            data_controller.setinfo(data);
            setGraphic(data_controller.getBox());
            
           
        }
    }  
}
