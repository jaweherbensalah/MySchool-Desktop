/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfinal;

import Entites.Absence;
import Entites.Eleve;
import Entites.Enseignants;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.AbsenceService;
import service.EleveService;
import service.EnseignantsService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterAbsByEnsController implements Initializable {
ObservableList<String> classe  = FXCollections.observableArrayList();
    ObservableList<Eleve> eleve ; 
        ObservableList<Absence> liste_Absences ; 

     ObservableList<String> heures = FXCollections.observableArrayList();
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button button;
    @FXML
    private ChoiceBox<String> niveaux;
    @FXML
    private ChoiceBox<Eleve> test;
    @FXML
    private ChoiceBox<String> heure;
    @FXML
    private Label msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initHeure();
           String p = ConnecterController.n;
         EnseignantsService clService = new EnseignantsService();
         List<Enseignants> listEns = clService.FindByClasse(p);
       for(int i=0;i<listEns.size();i++) {
             String a = listEns.get(i).getClasse();
             String b = listEns.get(i).getClasse2(); 
        classe.addAll(a,b); 
        niveaux.setItems(classe);
    }    
    } 
    @FXML
    private void AjouterAction(ActionEvent event) {
           String p = ConnecterController.n;
            AbsenceService abs = new AbsenceService();
            EnseignantsService ensService = new EnseignantsService();
            
      String cla = niveaux.getSelectionModel().getSelectedItem(); 
       String ele = test.getSelectionModel().getSelectedItem().getNom(); 
         String ens = p; 
         String heu = heure.getSelectionModel().getSelectedItem(); 
         String m = ensService.findEnsByNom(ens).getMatiere();
         Absence w ; 
         Absence a = new Absence(ens,ele,heu,cla,m);
         if(abs.insertAbsence(a))
         {
             System.out.println("avec succes");
   AbsenceService elService = new AbsenceService();
 List<Absence> listAbsence =  elService.displayAbsence();
   for(int i=0;i< listAbsence.size();i++)
         {
      liste_Absences= FXCollections.observableArrayList(listAbsence);
         }
   DetailAccueilenseignantController.list_ab.setItems(liste_Absences);
   DirecteurAdminController.listAbsence_2.setItems(liste_Absences);
    Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajout absence ");
 
        // alert.setHeaderText("Results:");
        alert.setContentText("absence est  ajouter   ");
 
        alert.showAndWait(); 
    }
         else {
               Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error ");
alert.setHeaderText("Ajout Absence");
alert.setContentText("probleme");
 
alert.showAndWait();    
         } 
    }

    @FXML
    private void testEleve(MouseEvent event) {
         String cla = niveaux.getSelectionModel().getSelectedItem(); 
      
      EleveService clService = new EleveService();
     List<Eleve> listEleve = clService.displayEleveByClasse(cla); 
         for(int i=0;i< listEleve.size();i++)
         {
      eleve= FXCollections.observableArrayList(listEleve); 
         } 
       test.setItems(eleve);
    }
     private void initHeure() {
       heures.removeAll(heures); 
        String a = "8h"; 
        String b = "9h"; 
        String c = "10h " ; 
        String d = "11h "; 
        String e = "12h "; 
        heures.addAll(a,b,c,d,e); 
        heure.setItems(heures);  
    }
}
