/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import javafx.scene.control.Button;

/**
 *
 * @author hp
 */
public class Matiere {
    private String idMat ; 
    private String nomMat ; 
    private float coefficient ; 
    private String description ;
    Button update ; 
    

    public Matiere() {
    }

    public Matiere(String idMat, String nomMat) {
        this.idMat = idMat;
        this.nomMat = nomMat;
    }

    public Matiere(String idMat, String nomMat, float coefficient, String description) {
        this.idMat = idMat;
        this.nomMat = nomMat;
        this.coefficient = coefficient;
        this.description = description;
       
    }

    public Matiere(String idMat, String nomMat, float coefficient, String description, Button update) {
        this.idMat = idMat;
        this.nomMat = nomMat;
        this.coefficient = coefficient;
        this.description = description;
        this.update = update;
    }

    
    

    public String getIdMat() {
        return idMat;
    }

    public void setIdMat(String idMat) {
        this.idMat = idMat;
    }

    public String getNomMat() {
        return nomMat;
    }

    public void setNomMat(String nomMat) {
        this.nomMat = nomMat;
    }

    public float getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(float coefficient) {
        this.coefficient = coefficient;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return nomMat ;
    }

    public Button getUpdate() {
        return update;
    }

    public void setUpdate(Button update) {
        this.update = update;
    }

    
    
    
}
