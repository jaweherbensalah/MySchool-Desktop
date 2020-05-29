/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.logging.Logger;

/**
 *
 * @author hp
 */
public class Note {
    private int id ; 
    private String matiere_id;
    private float ds ;
    private float cc ;
    private float examen ; 
    private float coeffcc;
    private float coeffDS;
    private float coeffEX;
    private float moyenne;
    private int eleve_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMatiere_id() {
        return matiere_id;
    }

    public void setMatiere_id(String matiere_id) {
        this.matiere_id = matiere_id;
    }

    public float getDs() {
        return ds;
    }

    public void setDs(float ds) {
        this.ds = ds;
    }

    public float getCc() {
        return cc;
    }

    public void setCc(float cc) {
        this.cc = cc;
    }

    public float getExamen() {
        return examen;
    }

    public void setExamen(float examen) {
        this.examen = examen;
    }

    public float getCoeffcc() {
        return coeffcc;
    }

    public void setCoeffcc(float coeffcc) {
        this.coeffcc = coeffcc;
    }

    public float getCoeffDS() {
        return coeffDS;
    }

    public void setCoeffDS(float coeffDS) {
        this.coeffDS = coeffDS;
    }

    public float getCoeffEX() {
        return coeffEX;
    }

    public void setCoeffEX(float coeffEX) {
        this.coeffEX = coeffEX;
    }

    public float getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }

    public int getEleve_id() {
        return eleve_id;
    }

    public void setEleve_id(int eleve_id) {
        this.eleve_id = eleve_id;
    }

    public Note(String matiere_id, float ds, float cc, float examen, float moyenne) {
        this.matiere_id = matiere_id;
        this.ds = ds;
        this.cc = cc;
        this.examen = examen;
        this.moyenne = moyenne;
    }

    public Note(String matiere_id, float ds, float cc, float examen, float coeffcc, float coeffDS, float coeffEX, float moyenne, int eleve_id) {
        this.matiere_id = matiere_id;
        this.ds = ds;
        this.cc = cc;
        this.examen = examen;
        this.coeffcc = coeffcc;
        this.coeffDS = coeffDS;
        this.coeffEX = coeffEX;
        this.moyenne = moyenne;
        this.eleve_id = eleve_id;
    }

    public Note(int id, String matiere_id, float ds, float cc, float examen, float coeffcc, float coeffDS, float coeffEX, float moyenne, int eleve_id) {
        this.id = id;
        this.matiere_id = matiere_id;
        this.ds = ds;
        this.cc = cc;
        this.examen = examen;
        this.coeffcc = coeffcc;
        this.coeffDS = coeffDS;
        this.coeffEX = coeffEX;
        this.moyenne = moyenne;
        this.eleve_id = eleve_id;
    }

    public Note() {
      
        
    }

    public Note(String matiere_id, float ds, float cc, float examen, float moyenne, int eleve_id) {
        this.matiere_id = matiere_id;
        this.ds = ds;
        this.cc = cc;
        this.examen = examen;
        this.moyenne = moyenne;
        this.eleve_id = eleve_id;
    }

    public Note(String matiere_id, float ds, float examen, float moyenne) {
        this.matiere_id = matiere_id;
        this.ds = ds;
        this.examen = examen;
        this.moyenne = moyenne;
    }

   
   
    
}
