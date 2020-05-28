/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfinal;

import Entites.Absence;
import Entites.Classe;
import Entites.Eleve;
import Entites.Enseignants;
import Entites.Matiere;
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
import service.ClasseService;
import service.EleveService;
import service.EnseignantsService;
import service.MatiereService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjoutAbsenceController implements Initializable {
  ObservableList<Classe> classe ;  
    ObservableList<Eleve> eleve ; 
    ObservableList<Enseignants> enseignant ; 
     ObservableList<String> heures = FXCollections.observableArrayList();
      ObservableList<Matiere> matieres ; 
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button button;
    @FXML
    private Label msg;
    @FXML
    private ChoiceBox<Classe> niveaux;
    @FXML
    private ChoiceBox<Eleve> test;
    @FXML
    private ChoiceBox<Enseignants> testEns;
    @FXML
    private ChoiceBox<String> heure;
    @FXML
    private ChoiceBox<Matiere> matiere;
    
    
        ObservableList<Absence> liste_Absences; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      initNiveau();
      initHeure(); 
      initMatiere(); 
    }    

    @FXML
    private void RésultatsAction(ActionEvent event) {
          AbsenceService abs = new AbsenceService();
      String cla = niveaux.getSelectionModel().getSelectedItem().getNom(); 
       String ele = test.getSelectionModel().getSelectedItem().getNom(); 
         String ens = testEns.getSelectionModel().getSelectedItem().getNom(); 
         String heu = heure.getSelectionModel().getSelectedItem(); 
         String m = matiere.getSelectionModel().getSelectedItem().getNomMat(); 
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
   DirecteurAdminController.listAbsence_2.setItems(liste_Absences);
     Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajout Absence ");
 
        // alert.setHeaderText("Results:");
        alert.setContentText("Absence est  ajouté   ");
 
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
       String cla = niveaux.getSelectionModel().getSelectedItem().getNom(); 
      
      EleveService clService = new EleveService();
     List<Eleve> listEleve = clService.displayEleveByClasse(cla); 
         for(int i=0;i< listEleve.size();i++)
         {
      eleve= FXCollections.observableArrayList(listEleve); 
         } 
       test.setItems(eleve);
   
    }
    @FXML
    private void testEns(MouseEvent event) {
     String cla = niveaux.getSelectionModel().getSelectedItem().getNom(); 
      
      EnseignantsService clService = new EnseignantsService();
     List<Enseignants> listEns = clService.displayByClasse(cla); 
         for(int i=0;i< listEns.size();i++)
         {
      enseignant= FXCollections.observableArrayList(listEns); 
         } 
       testEns.setItems(enseignant);   
    }
    private void initNiveau() {
       
   ClasseService clService = new ClasseService();
     List<Classe> listClasse = clService.displayClasse(); 
         for(int i=0;i< listClasse.size();i++)
         {
      classe= FXCollections.observableArrayList(listClasse); 
         } 
       niveaux.setItems(classe); 
      
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
    private void initMatiere() {
        MatiereService cleService = new MatiereService();
     List<Matiere> listmatiere = cleService.getAll();
         for(int i=0;i< listmatiere.size();i++)
         {
     matieres = FXCollections.observableArrayList(listmatiere); 
         } 
       matiere.setItems(matieres);
    }
}
