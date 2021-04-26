/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Participant;
import javafx.scene.control.ListCell;

/**
 *
 * @author HP
 */
public class ajout_part_back extends ListCell <Participant> {
    ajout_part_back(){
        super();
    }
   @Override
    public void updateItem(Participant data, boolean empty)
    {
       
        super.updateItem(data,empty);
        if(data != null)
        {
            one_part_item data_controller = new one_part_item();
            data_controller.setinfo(data);
            setGraphic(data_controller.getBox());
            
           
        }
    }
}
