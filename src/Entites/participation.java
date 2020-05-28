/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class participation {
      private int id;
      private int iduser;
      private String idevent;
      private Date createdat;
      private String nom;

    public participation() {
    }

    public participation(int id, int iduser, String idevent, Date createdat,String nom) {
        this.id = id;
        this.iduser = iduser;
        this.idevent = idevent;
        this.createdat = createdat;
        this.nom = nom;
    }

    public participation(int iduser, String idevent, Date createdat,String nom) {
        this.iduser = iduser;
        this.idevent = idevent;
        
        this.createdat = createdat;
         this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public String getIdevent() {
        return idevent;
    }

    public void setIdevent(String idevent) {
        this.idevent = idevent;
    }

    public Date getCreatedat() {
        return createdat;
    }

    public void setCreatedat(Date createdat) {
        this.createdat = createdat;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "participation{" + "id=" + id + ", iduser=" + iduser + ", idevent=" + idevent + ", createdat=" + createdat + '}';
    }
      
}
