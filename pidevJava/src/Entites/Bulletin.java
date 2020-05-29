/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author hp
 */
public class Bulletin {
    private int id ; 
    private Float moyenne ; 
    private String appreciation ; 
    
    private int eleveid ; 

    public Bulletin() {
    }

    public Bulletin(Float moyenne) {
        this.moyenne = moyenne;
    }

    public Bulletin(Float moyenne, String appreciation) {
        this.moyenne = moyenne;
        this.appreciation = appreciation;
    }

    public int getEleveid() {
        return eleveid;
    }

    public void setEleveid(int eleveid) {
        this.eleveid = eleveid;
    }

    public Bulletin(Float moyenne, String appreciation, int eleveid) {
        this.moyenne = moyenne;
        this.appreciation = appreciation;
        this.eleveid = eleveid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Float getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(Float moyenne) {
        this.moyenne = moyenne;
    }

    public String getAppreciation() {
        return appreciation;
    }

    public void setAppreciation(String appreciation) {
        this.appreciation = appreciation;
    }

    @Override
    public String toString() {
        return "Bulletin{" + "id=" + id + ", moyenne=" + moyenne + ", appreciation=" + appreciation + '}';
    }
    
}
