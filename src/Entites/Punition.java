
package Entites;

import java.util.Date;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import pidevfinal.DirecteurAdminController;
import service.PunitionService;

public class Punition {
    String eleve ; 
    String classe ; 
    String cause ; 
    String id ; 
    Date date ;
     private Button modifier ; 

    public Button getModifier() {
        return modifier;
    }

    public void setModifier(Button modifier) {
        this.modifier = modifier;
    }

    public Punition() {
         this.modifier = new Button("modifier");
          modifier.setOnAction( e-> {
            ObservableList<Punition> cl = DirecteurAdminController.punition.getSelectionModel().getSelectedItems(); 
    for (Punition punition : cl) {
    if (punition.getModifier() == modifier) {
       // System.out.println("lkgjrtgjtr");
    PunitionService cls = new PunitionService(); 
    cls.updateClasse(punition); 
    }
}
    
});
    }

    public Punition(String eleve, String classe, String cause) {
        this.eleve = eleve;
        this.classe = classe;
        this.cause = cause;
        
       
        
    }

    public Punition(String eleve, String classe, String cause, String id) {
        this.eleve = eleve;
        this.classe = classe;
        this.cause = cause;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Punition{" + "eleve=" + eleve + ", classe=" + classe + ", cause=" + cause + ", id=" + id + ", date=" + date + '}';
    }

    public String getEleve() {
        return eleve;
    }

    public void setEleve(String eleve) {
        this.eleve = eleve;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
