/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import entities.Veloav;
import javafx.scene.control.ListCell;

/**
 *
 * @author amine
 */
public class ajout_velo_list_front extends ListCell<Veloav> {
     ajout_velo_list_front() {
        super();
    }
      @Override
    public void updateItem(Veloav data, boolean empty)
    {
       
        super.updateItem(data,empty);
        if(data != null)
        {
            one_item_velo_front data_controller = new one_item_velo_front();
            data_controller.setinfo(data);
            setGraphic(data_controller.getBox());
            
           
        }
    }
    
}
