/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Piece_r;
import javafx.scene.control.ListCell;

/**
 *
 * @author Hamdi ben zaghden
 */
public class ajout_piece_list_back extends ListCell<Piece_r>{
    ajout_piece_list_back(){
         super();
    }
   @Override
    public void updateItem(Piece_r data, boolean empty)
    {
       
        super.updateItem(data,empty);
        if(data != null)
        {
            one_item_piece_back data_controller = new one_item_piece_back();
            data_controller.setinfo(data);
            setGraphic(data_controller.getBox());
            
           
        }
    }
    
}
