/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfinal;

import Entites.EmploiDuTemps;
import Entites.Enseignants;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import service.ClasseService;
import service.EmploiService;
import service.EnseignantsService;
import service.ParticipationService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class TestController implements Initializable {
   ObservableList<String> choice = FXCollections.observableArrayList(); 

    @FXML
    private Label msg;
    @FXML
    private ComboBox<String> choix;
    
     @FXML
    private TableView<String> tableaux; 
      @FXML
    private TableView<String> tableaux2; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         //ParticipationService par = new ParticipationService();
        //int g = par.calculerEvent();
        //String gg = Integer.toString(g);
        //System.out.println("eventtttttttt "+g);
        
        EnseignantsService el = new EnseignantsService();
         Enseignants m1 = el.findEnsByNom("aida");
         System.out.println(m1.getMatiere());
         
         
           EnseignantsService ens = new EnseignantsService(); 
      String nom = "3A1";
  Enseignants en = ens.findbyMatiere(nom);
  if(en!=null) {
        System.out.println(en.getMatiere());
  } else {
       loadData();
     tableaux2.setVisible(false);
      ClasseService cla = new ClasseService(); 
   int a = cla.calculer(); 
   String  x = Integer.toString(a);
      System.out.println("le nommbre de classe est : "+x); 
        EmploiService c = new EmploiService();
        String m = "2A1";
        String j = "lundi";
       /* List<EmploiDuTemps> listEmplois = c.displayByClasse(m,j);
        for (int i=0;i<listEmplois.size();i++) {
            System.out.println(listEmplois.get(i));
        }
      */
    }    
    }
    @FXML
    private void changer_contenu(ActionEvent event) {
        String m = choix.getValue();
         String a = "afficher eleve ";
            //  if (m.equals(a)) {
              //               System.out.println(a);
                //             tableaux2.setVisible(true);
             // }
             // else 
              //{
                //  tableaux2.setVisible(false);
              //}
        
     
    }
     private void loadData() {
        choice.removeAll(choice); 
         String a = "afficher eleve ";
        String b = "afficher enseignants "; 
        String c = "afficher classes "; 
        String d = "afficher punitions "; 
        String e = "afficher absences "; 
        choice.addAll(a,b,c,d,e); 
        choix.setItems(choice);
    }
}
