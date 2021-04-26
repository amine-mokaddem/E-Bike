/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author chedly
 */
public class cours {
        private int id;
    private String circuitcours;
    private LocalDate datecours ;
    private String dureecours;
    private String frais;
    private String typecours;
    private String nombremax;
    private String nombreparticip;

    public cours() {
    }

    public cours(int id, String circuitcours, LocalDate datecours , String dureecours  ,String frais, String typecours, String nombremax, String nombreparticip) {
        this.id = id;
        this.circuitcours = circuitcours;
        this.datecours = datecours ;
        this.dureecours = dureecours;
        this.frais = frais;
        this.typecours = typecours;
         this.nombremax = nombremax;
        this.nombreparticip = nombreparticip;
    }
     public cours( String circuitcours, LocalDate datecours , String dureecours ,String frais, String typecours, String nombremax, String nombreparticip) {
        this.circuitcours = circuitcours;
        this.datecours= datecours;
        this.dureecours = dureecours;
        this.frais = frais;
        this.typecours = typecours;
    }

    public int getId() {
        return id;
    }

   

    public String getCircuitcours() {
        return circuitcours;
    }
    
     public String getFrais() {
        return frais;
    }

    public String getDureecours() {
        return dureecours;
    }

    public void setFrais(String frais) {
        this.frais = frais;
    }

    public void setDureecours(String dureecours) {
        this.dureecours = dureecours;
    }

    public void setCircuitcours(String circuitcours) {
        this.circuitcours = circuitcours;
    }

    
  
    

    public void setId(int id) {
        this.id = id;
    }

   

    public LocalDate getDatecours() {
        return datecours;
    }

    public void setDatecours(LocalDate datecours) {
        this.datecours = datecours;
    }

    
    
   
 
    public String getTypecours() {
        return typecours;
    }

    public void setTypecours(String typecours) {
        this.typecours = typecours;
    }

    public String getNombremax() {
        return nombremax;
    }

    public void setNombremax(String nombremax) {
        this.nombremax = nombremax;
    }

    public String getNombreparticip() {
        return nombreparticip;
    }

    public void setNombreparticip(String nombreparticip) {
        this.nombreparticip = nombreparticip;
    }
    
    

    @Override
    public String toString() {
        return "cours{" + "id=" + id + ", circuitcours=" + circuitcours + ", datecours=" + datecours + ", dureecours=" + dureecours + ", frais=" + frais +", typecours=" + typecours + ", nombremax=" + nombremax +", nombreparticip=" + nombreparticip +'}';
    }
    
}
