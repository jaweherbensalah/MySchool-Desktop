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
public class inscription_club {
    private int id ;
    private String iduser;
    private String idclub;
    private  String stat;

    public inscription_club() {
    }

    public inscription_club(String iduser, String idclub, String stat) {
        this.iduser = iduser;
        this.idclub = idclub;
        this.stat = stat;
    }
    
            

    public inscription_club(int id, String iduser, String idclub, String stat) {
        this.id = id;
        this.iduser = iduser;
        this.idclub = idclub;
        this.stat = stat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getIdclub() {
        return idclub;
    }

    public void setIdclub(String idclub) {
        this.idclub = idclub;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {
        return "inscription_club{" + "id=" + id + ", iduser=" + iduser + ", idclub=" + idclub + ", stat=" + stat + '}';
    }
    
    
}
