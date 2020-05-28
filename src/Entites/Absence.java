
package Entites;

import java.util.Date;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import pidevfinal.DetailAccueilenseignantController;
import pidevfinal.DirecteurAdminController;
import service.AbsenceService;

public class Absence {
     String ens;  
    String eleve ; 
    private Date date; 
    private String id; 
    private String heure ; 
   private  String classe; 
   String matier ; 
   private Button modifier ; 
    public Absence() {
    this.modifier = new Button("modifier");  modifier.setOnAction( e-> {
    ObservableList<Absence> cl = DirecteurAdminController.listAbsence_2.getSelectionModel().getSelectedItems();
    for (Absence clas : cl) {
    if (clas.getModifier() == modifier) {
       
    AbsenceService cls = new AbsenceService(); 
    cls.updateAbsence(clas); 
   
    } 
}
   
}); 
         }
    public Absence(String ens, String eleve, String id) {
        this.ens = ens;
        this.eleve = eleve;
        this.id = id;
    }

    public Absence(Button modifier) {
        this.modifier = modifier;
    }

    public Absence(String ens, String eleve,  String heure, String classe,String matiere , String id , Date date  ) {
        this.ens = ens;
        this.eleve = eleve;
        this.heure = heure;
        this.classe = classe;
        this.matier = matiere; 
        this.id = id; 
        this.date = date ; 
             this.modifier = new Button("modifier");

      
        
       

    }

    public Absence(String ens, String eleve, String heure, String classe, String matier) {
        this.ens = ens;
        this.eleve = eleve;
        this.heure = heure;
        this.classe = classe;
        this.matier = matier;
    }

    @Override
    public String toString() {
        return "Absence{" + "ens=" + ens + ", eleve=" + eleve + ", date=" + date + ", heure=" + heure + '}';
    }

    public String getMatier() {
        return matier;
    }

    public void setMatier(String matier) {
        this.matier = matier;
    }

    public Button getModifier() {
        return modifier;
    }

    public void setModifier(Button modifier) {
        this.modifier = modifier;
    }
 
    public String getEns() {
        return ens;
    }

    public void setEns(String ens) {
        this.ens = ens;
    }

    public String getEleve() {
        return eleve;
    }

    public void setEleve(String eleve) {
        this.eleve = eleve;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }
   
   
}
