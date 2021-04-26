package entities;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.Objects;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author user
 */
public class performance {
    
    private SimpleStringProperty depart;
    private SimpleStringProperty arivee;
    private SimpleStringProperty distance;
        private SimpleStringProperty temps;
            private SimpleStringProperty mail;
               private SimpleStringProperty role;
               SimpleIntegerProperty id;

    public performance() {
    }

    public performance(String depart, String arivee, String distance, String temps) {
        this.depart = new SimpleStringProperty(depart)  ;
        this.arivee = new SimpleStringProperty(arivee);
        this.distance = new SimpleStringProperty(distance);
        
        this.temps =new SimpleStringProperty(temps) ;
      
        
    }
   public performance(String depart, String arivee, String distance, String temps,int id) {
       this.depart = new SimpleStringProperty(depart)  ;
        this.arivee = new SimpleStringProperty(arivee);
        this.distance = new SimpleStringProperty(distance);
        
        this.temps =new SimpleStringProperty(temps) ;
                 this.id =new SimpleIntegerProperty(id) ;
        
    }
    
    

    public String getDepart() {
        return depart.get();
    }

    public void setDepart(String depart) {
        this.depart = new SimpleStringProperty(depart);
    }

    public String getArivee() {
        return arivee.get();
    }

    public void setArivee(String arivee) {
        this.arivee = new SimpleStringProperty(arivee);
    }
 public SimpleStringProperty getDepartProperty(){
        return depart;
    }
    public String getDistance() {
        return distance.get();
    }
   
    public SimpleStringProperty getDistanceProperty(){
        return distance;
    }
    public void setDistance(String distance) {
        this.distance = new SimpleStringProperty(distance);
    }
    public String getTemps() {
        return temps.get();
    }
   
    public SimpleStringProperty getTempsProperty(){
        return temps;
    }
    public void setTemps(String temps) {
        this.temps = new SimpleStringProperty(temps);
    }
   
   
    public SimpleStringProperty getAriveeProperty(){
        return arivee;
    }
     
          public int getId() {
        return id.get();
    }
   
    public SimpleIntegerProperty getIdProperty(){
        return id;
    }
    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }
    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

 

  
}
