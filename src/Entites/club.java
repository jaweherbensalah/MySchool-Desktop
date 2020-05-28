/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

/**
 *
 * @author ASUS
 */
public class club {

  
     
    private String id;
    private String nom_club;
    private String description;
    private int effectif;
    private String domaine;
    
    public club() {
    } 

    public club(String nom_club, String description, int effectif, String domaine) {
        this.nom_club = nom_club;
        this.description = description;
        this.effectif = effectif;
        this.domaine = domaine;
    }

    public club(String id, String nom_club, String description, int effectif, String domaine) {
        this.id = id;
        this.nom_club = nom_club;
        this.description = description;
        this.effectif = effectif;
        this.domaine = domaine;
    }

    public club(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    

    public String getNom_club() {
        return nom_club;
    }

    public void setNom_club(String nom_club) {
        this.nom_club = nom_club;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEffectif() {
        return effectif;
    }

    public void setEffectif(int effectif) {
        this.effectif = effectif;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    } 
    @Override
    public String toString() {
        return  nom_club;
    }

   
    
    
    
}
