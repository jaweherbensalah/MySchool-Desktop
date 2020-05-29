/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfinal;

import Entites.Absence;
import Entites.Classe;
import Entites.Eleve;
import Entites.EmploiDuTemps;
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
import service.EmploiService;
import service.EnseignantsService;
import service.MatiereService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjoutEmploiController implements Initializable {

   ObservableList<Classe> classe ;  
    ObservableList<Eleve> eleve ; 
    ObservableList<Enseignants> enseignant ; 
     ObservableList<String> heures = FXCollections.observableArrayList();
       ObservableList<String> jour = FXCollections.observableArrayList();
   ObservableList<String> heureF = FXCollections.observableArrayList();

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
    private ChoiceBox<Enseignants> testEns1;
    @FXML
    private ChoiceBox<Enseignants> testEns3;
    @FXML
    private ChoiceBox<Enseignants> testEns4;
    @FXML
    private ChoiceBox<String> heure;
    @FXML
    private ChoiceBox<String> heuref;
     @FXML
    private ChoiceBox<String> heure2;
      @FXML
    private ChoiceBox<String> heuref1;
     @FXML
    private ChoiceBox<String> jours;
   
   
   
    
    
        ObservableList<Absence> liste_Absences; 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
        initNiveau();
      initHeure(); 
      
      initJour(); 
     
    }    

    @FXML
    private void RésultatsAction(ActionEvent event) {
          EmploiService emplS = new EmploiService();
      String cla = niveaux.getSelectionModel().getSelectedItem().getNom(); 
      String ens; 
      String e1;
      String e2; 
      String e3;  
      String heu;
       String h2 ;
       String h ;
        String hf ;
         Enseignants m2 = new Enseignants();
    Enseignants m0 = new Enseignants();
       Enseignants m1 = new Enseignants();
          Enseignants m3 = new Enseignants();
                 EnseignantsService el = new EnseignantsService();

      if (testEns.getSelectionModel().getSelectedItem() == null) {
             ens = " "; 
         } else {
          ens = testEns.getSelectionModel().getSelectedItem().getNom(); 
           m0 = el.findEnsByNom(ens);
      } 
      if(testEns1.getSelectionModel().getSelectedItem() == null) {
             e1 = " "; 
         } else {
        e1 = testEns1.getSelectionModel().getSelectedItem().getNom();
                  m1 = el.findEnsByNom(e1);

      } 
      if (testEns3.getSelectionModel().getSelectedItem()==null) {
             e2 = " "; 
         } else { 
      e2 = testEns3.getSelectionModel().getSelectedItem().getNom();
               m2 = el.findEnsByNom(e2);
      }    
       if (testEns4.getSelectionModel().getSelectedItem()==null) {
             e3 = " "; 
         } else {
      e3 = testEns4.getSelectionModel().getSelectedItem().getNom();
                m3 = el.findEnsByNom(e3);

       } 
         
       if(heure.getSelectionModel().getSelectedItem() ==null) { heu = "null"; } 
          else {
          heu = heure.getSelectionModel().getSelectedItem(); } 
            if (heure2.getSelectionModel().getSelectedItem() ==null) { h2 = "null "; } 
            else { 
          h2 = heure2.getSelectionModel().getSelectedItem(); } 
              if(heuref.getSelectionModel().getSelectedItem() ==null) { h = " null"; } 
              else { 
          h = heuref.getSelectionModel().getSelectedItem(); } 
                       if (heuref1.getSelectionModel().getSelectedItem() == null) { hf = " null"; } 
                       else { 
          hf = heuref1.getSelectionModel().getSelectedItem(); } 
         String j = jours.getSelectionModel().getSelectedItem(); 
       if((testEns.getSelectionModel().getSelectedItem() == null) && (testEns1.getSelectionModel().getSelectedItem() == null) && (testEns3.getSelectionModel().getSelectedItem()==null)
&& (testEns4.getSelectionModel().getSelectedItem()==null) && (heure.getSelectionModel().getSelectedItem() ==null) && (heuref.getSelectionModel().getSelectedItem() ==null) && (heure2.getSelectionModel().getSelectedItem() ==null) 
               && (heuref1.getSelectionModel().getSelectedItem() == null) ) 
       {
          Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error ");
alert.setHeaderText("Ajout Emplois");
alert.setContentText("il faut mois saisir une seance ");
 
alert.showAndWait();   
       }
       
         EmploiDuTemps emp = new EmploiDuTemps(cla,heu,h,h2,hf,ens,e1,e2,e3,m0.getMatiere(),m1.getMatiere(),m2.getMatiere(),m3.getMatiere(),j);
       if(emplS.insertEmplois(emp)) {
           System.out.println("ajout avec succes");
           Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajout Emplois ");
 
        // alert.setHeaderText("Results:");
        alert.setContentText("emplois est  ajouter   ");
 
        alert.showAndWait(); 
       }
       else 
       {
             System.out.println("erreur");
          Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error ");
alert.setHeaderText("Ajout Emplois");
alert.setContentText("probleme");
 
alert.showAndWait();    
       }
     
       
    
    } 
    @FXML
    private void testEleve(MouseEvent event) {
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
       testEns1.setItems(enseignant);
       testEns3.setItems(enseignant);
       testEns4.setItems(enseignant);
      
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
        String a = "8h=>10h"; 
        String c = "10h:5=>12h" ; 
        String d = "14h=>16h"; 
        String e = "16h:15=>18h";
                 
        heures.addAll(a,c,d,e); 
        heure.setItems(heures);  
        heure2.setItems(heures);
        heuref.setItems(heures);
        heuref1.setItems(heures);
    }
   
   
  
    private void initJour() {
      jour.removeAll(jour); 
        String a = "Lundi "; 
        String c = "Mardi " ; 
        String d = "Mercredi"; 
        String e = "Jeudi";
        String F = "Vendredi"; 
        jour.addAll(a,c,d,e,F); 
        jours.setItems(jour);    
    }
    
    private void initEnss() {
        
        String cla = niveaux.getSelectionModel().getSelectedItem().getNom(); 
      
      EnseignantsService clService = new EnseignantsService();
     List<Enseignants> listEns = clService.displayByClasse(cla); 
         for(int i=0;i< listEns.size();i++)
         {
      enseignant= FXCollections.observableArrayList(listEns); 
         } 
       testEns.setItems(enseignant); 
       testEns1.setItems(enseignant);
       testEns3.setItems(enseignant);
       testEns4.setItems(enseignant);
      
    }
}
