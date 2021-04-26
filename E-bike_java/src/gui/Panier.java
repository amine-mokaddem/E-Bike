/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Veloav;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amine
 */
public class Panier {
   
    public static List<Veloav> lst_shop = new ArrayList<>();

    public static void addItem(Veloav f) {
        if (lst_shop == null) {
            lst_shop = new ArrayList<>();
        }
        lst_shop.add(f);
    }

    public static boolean checkItemPanier(int id) {

        for (Veloav f : lst_shop) {
            if(f.getId() == id)
                return true ;
        }

        return false;
    }
    
    public static void deleteItemFromPanier(int id) {

        for (int i = 0 ; i < lst_shop.size() ; i++) {
            if(lst_shop.get(i).getId() == id){
                lst_shop.remove(i);
                
            }

        }
        System.out.println("after del "+lst_shop.size());

    }
    public static void afficher(ArrayList<Veloav> l ){
           for (int i = 0 ; i < lst_shop.size() ; i++) {
               System.out.println(l.get(i).toString());
        
        
    }}
 
}
