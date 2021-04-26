/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.db3a4.interfaces;

import java.util.List;

/**
 *
 * @author House
 */
public interface IPersonne<T> {
     public void ajouterPersonne(T t);
     public void supprimerPersonne(T t);
     public void updateTerrain(Integer t, String a , String b, String c, String v);
     public List<T> displayTerrain();
}
