/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.db3a4.interfaces;
import java.sql.Date;
import java.util.List;
/**
 *
 * @author chedly
 */
public interface Icours<T> {
     public void ajoutercours(T t);
     public void supprimercours(T t);
     public void updatecours(Integer t, String a , Date b, String c, String d, String e, String f, String g);
     public List<T> displaycours();
    
}
