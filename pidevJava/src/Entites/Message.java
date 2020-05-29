/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.Date;
import javafx.scene.control.Button;

public class Message {
  String id;
  String contenu;
  String date;
  String eleve;
String titre; 
    public Message() {
    }

    public Message(String id, String contenu, String date, String eleve, String titre) {
        this.id = id;
        this.contenu = contenu;
        this.date = date;
        this.eleve = eleve;
        this.titre = titre;
    }

    public Message(String contenu, String eleve, String titre) {
        this.contenu = contenu;
        this.eleve = eleve;
        this.titre = titre;
    }

    public Message(String contenu, String date, String eleve, String titre) {
        this.contenu = contenu;
        this.date = date;
        this.eleve = eleve;
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Message{" + "contenu=" + contenu + ", titre=" + titre + '}';
    }

   

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEleve() {
        return eleve;
    }

    public void setEleve(String eleve) {
        this.eleve = eleve;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }
   
}
