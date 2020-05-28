/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfinal;

import Entites.Classe;
import Entites.Eleve;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javax.swing.JOptionPane;
import service.ClasseService;
import service.EleveService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AffecterEleveController implements Initializable {
 ObservableList<Classe> classe ; 
    ObservableList<Eleve> eleve ; 
        ObservableList<Eleve> liste_Eleve;

    @FXML
    private Button button;
    @FXML
    private ChoiceBox<Classe> classes;
    @FXML
    private ChoiceBox<Eleve> eleves;
    @FXML
    private Label msg;
    @FXML
    private AnchorPane rootPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void afficherEleve(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("DirecteurAdmin.fxml"));
  rootPane.getChildren().setAll(pane); 
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
         ClasseService ps = new ClasseService(); 
         EleveService els = new EleveService();
         String cla = classes.getSelectionModel().getSelectedItem().getNom(); 
         Eleve ele = eleves.getSelectionModel().getSelectedItem(); 
        int q = els.calculerEleveByClasse(cla);
       // System.out.println(q);
       
      //   EleveService el
         if(ps.affecterEleve(ele, cla)) {
           //  msg.setText("affectation c bon");
            EleveService elService = new EleveService();
        List<Eleve> listeleves = elService.displayEleve();
            Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Affectation eleve ");
 
        // alert.setHeaderText("Results:");
        alert.setContentText("eleve est affecté  ");
 
        alert.showAndWait();
        for (int i = 0; i < listeleves.size(); i++) {
            liste_Eleve = FXCollections.observableArrayList(listeleves);
        }
        DirecteurAdminController.list_eleve2.setItems(liste_Eleve);
         }
         else 
         {
             //  msg.setText("erreur ");
              Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error ");
alert.setHeaderText("Affectation eleve");
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
        EleveService clService = new EleveService();
     List<Eleve> listClasse = clService.displayElByCLasse();
         for(int i=0;i< listClasse.size();i++)
         {
      eleve= FXCollections.observableArrayList(listClasse); 
         } 
       eleves.setItems(eleve);   
    }
    @FXML
    private void affecterEnseignant(MouseEvent event) {
         
    }
    
}
