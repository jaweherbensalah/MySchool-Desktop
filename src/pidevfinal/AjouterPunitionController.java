/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfinal;

import Entites.Classe;
import Entites.Eleve;
import Entites.Punition;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.ClasseService;
import service.EleveService;
import service.PunitionService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterPunitionController implements Initializable {
ObservableList<Classe> classe ; 
    ObservableList<Eleve> eleve ; 
    @FXML
    private Button button;
    @FXML
    private ChoiceBox<Classe> classes;
    @FXML
    private ChoiceBox<Eleve> eleves;
    @FXML
    private Label msg;
    @FXML
    private TextField cause;
@FXML
    private AnchorPane rootPane;

ObservableList<Punition> liste_Punition; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void retourAction(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("DirecteurAdmin.fxml"));
  rootPane.getChildren().setAll(pane); 
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        PunitionService ps = new PunitionService(); 
        String cla = classes.getSelectionModel().getSelectedItem().getNom(); 
         String ele = eleves.getSelectionModel().getSelectedItem().getNom(); 
         String c = cause.getText();
         Punition w = ps.findByNomEleve(ele, cla); 
        
         Punition p = new Punition(ele,cla,c);
         if(ps.insertPunition(p)) {

             System.out.println("avec succes");
   PunitionService elService = new PunitionService();
 List<Punition> listPunition =  elService.displayPunition();
   for(int i=0;i< listPunition.size();i++)
         {
      liste_Punition= FXCollections.observableArrayList(listPunition);
         }
   DirecteurAdminController.punition.setItems(liste_Punition);  
    Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajout Punition ");
 
        // alert.setHeaderText("Results:");
        alert.setContentText("punition est  ajouter   ");
 
        alert.showAndWait(); 
         }
         else 
         {
    Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error ");
alert.setHeaderText("Ajout Punition");
alert.setContentText("probleme");
 
alert.showAndWait();    
         }
         } 
       
    

    @FXML
    private void afficherClasses(MouseEvent event) {
        ClasseService clService = new ClasseService();
     List<Classe> listClasse = clService.displayClasse(); 
         for(int i=0;i< listClasse.size();i++)
         {
      classe= FXCollections.observableArrayList(listClasse); 
         } 
       classes.setItems(classe);  
    }

    @FXML
    private void afficherEleves(MouseEvent event) {
     String cla = classes.getSelectionModel().getSelectedItem().getNom(); 
        EleveService clService = new EleveService();
     List<Eleve> listClasse = clService.findClasse(cla);
         for(int i=0;i< listClasse.size();i++)
         {
      eleve= FXCollections.observableArrayList(listClasse); 
         } 
       eleves.setItems(eleve);   
    }
    
}
