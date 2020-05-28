/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.util.Date;

public class EmploiDuTemps {
    String id ; 
   String classe; 
   String heure ; 
   String heure1 ;
   String heure2; 
   String heure3 ; 

   String enseignant ; 
      String enseignant1 ; 
   String enseignant2 ; 
   String enseignant3 ; 

   String jour;
   Date date ; 
   String matiere;
   String matiere1;
   String matiere2;
   String matiere3;

    public EmploiDuTemps() {
    }

    public EmploiDuTemps(String classe, String heure, String heure1, String heure2, String heure3, String enseignant, String enseignant1, String enseignant2, String enseignant3,String matiere,String matiere1, String matiere2,String matiere3,String jour) {
        this.classe = classe;
        this.heure = heure;
        this.heure1 = heure1;
        this.heure2 = heure2;
        this.heure3 = heure3;
        this.enseignant = enseignant;
        this.enseignant1 = enseignant1;
        this.enseignant2 = enseignant2;
        this.enseignant3 = enseignant3;
        this.matiere1 = matiere1; 
        this.matiere2 = matiere2; 
        this.matiere3 = matiere3; 
        this.jour = jour;
        this.matiere = matiere;
    }

    @Override
    public String toString() {
        return "EmploiDuTemps{" + "id=" + id + ", classe=" + classe + ", heure=" + heure + ", heure1=" + heure1 + ", heure2=" + heure2 + ", heure3=" + heure3 + ", enseignant=" + enseignant + ", enseignant1=" + enseignant1 + ", enseignant2=" + enseignant2 + ", enseignant3=" + enseignant3 + ", jour=" + jour + ", date=" + date + ", matiere=" + matiere + ", matiere1=" + matiere1 + ", matiere2=" + matiere2 + ", matiere3=" + matiere3 + '}';
    }

  


    
    public String getHeure1() {
        return heure1;
    }

    public void setHeure1(String heure1) {
        this.heure1 = heure1;
    }

    public String getHeure2() {
        return heure2;
    }

    public void setHeure2(String heure2) {
        this.heure2 = heure2;
    }

    public String getHeure3() {
        return heure3;
    }

    public void setHeure3(String heure3) {
        this.heure3 = heure3;
    }

    public String getEnseignant1() {
        return enseignant1;
    }

    public void setEnseignant1(String enseignant1) {
        this.enseignant1 = enseignant1;
    }

    public String getEnseignant2() {
        return enseignant2;
    }

    public void setEnseignant2(String enseignant2) {
        this.enseignant2 = enseignant2;
    }

    public String getEnseignant3() {
        return enseignant3;
    }

    public void setEnseignant3(String enseignant3) {
        this.enseignant3 = enseignant3;
    }

    public String getMatiere1() {
        return matiere1;
    }

    public void setMatiere1(String matiere1) {
        this.matiere1 = matiere1;
    }

    public String getMatiere2() {
        return matiere2;
    }

    public void setMatiere2(String matiere2) {
        this.matiere2 = matiere2;
    }

    public String getMatiere3() {
        return matiere3;
    }

    public void setMatiere3(String matiere3) {
        this.matiere3 = matiere3;
    }

  

   


    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

   


   


   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getHeure() {
        return heure;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

   

    public String getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(String enseignant) {
        this.enseignant = enseignant;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
   
   
}
