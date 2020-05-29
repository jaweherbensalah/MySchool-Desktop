/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfinal;

import Entites.Classe;
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
import service.EnseignantsService;
import service.MatiereService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AffecterMatiereController implements Initializable {
  ObservableList<Matiere> liste_Matiere ; 
   ObservableList<Enseignants> liste_Enseignants ; 
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button button;
    @FXML
    private ChoiceBox<Matiere> matieres;
    @FXML
    private ChoiceBox<Enseignants> enseignants;
    @FXML
    private Label msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
         String m = matieres.getSelectionModel().getSelectedItem().getNomMat();
        Enseignants ens = enseignants.getSelectionModel().getSelectedItem(); 
                EnseignantsService elService = new EnseignantsService();
         if(elService.affecterEnseignantMatiere(ens, m))
         {
          System.out.println("avec succes"); 
       Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Affectation matiere ");
 
        // alert.setHeaderText("Results:");
        alert.setContentText("matiere est  affecté  ");
 
        alert.showAndWait();
        List<Enseignants> listens = elService.displayEnseignant();
        for (int i = 0; i < listens.size(); i++) {
            liste_Enseignants = FXCollections.observableArrayList(listens);
        }
          Admin_enseignantController.list_Ens2.setItems(liste_Enseignants);
         }
         else {
   System.out.println("erreur");  
    Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error ");
alert.setHeaderText("Affectation matiere");
alert.setContentText("probleme");
 
alert.showAndWait();  
         }
    }

    @FXML
    private void afficherClasses(MouseEvent event) {
           MatiereService elService = new MatiereService();
        List<Matiere> listmatiere = elService.displayMatiere();
        for (int i = 0; i < listmatiere.size(); i++) {
            liste_Matiere = FXCollections.observableArrayList(listmatiere);
        }
        matieres.setItems(liste_Matiere);
    }

    @FXML
    private void afficherMatieres(MouseEvent event) {
         MatiereService elService = new MatiereService();
        List<Matiere> listmatiere = elService.displayMatiere();
        for (int i = 0; i < listmatiere.size(); i++) {
            liste_Matiere = FXCollections.observableArrayList(listmatiere);
        }
        matieres.setItems(liste_Matiere);
    }

    @FXML
    private void afficherEnseignant(MouseEvent event) {
        EnseignantsService elService = new EnseignantsService();
        List<Enseignants> listens = elService.displayEnsMatiere();
        for (int i = 0; i < listens.size(); i++) {
            liste_Enseignants = FXCollections.observableArrayList(listens);
        }
        enseignants.setItems(liste_Enseignants);  
    }
    
}
