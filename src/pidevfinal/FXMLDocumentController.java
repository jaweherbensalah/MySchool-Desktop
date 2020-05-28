/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfinal;

import Entites.Classe;
import Entites.Niveau;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import service.ClasseService;
import service.NiveauService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField nom;
    @FXML
    private Button button;
    @FXML
    private Label msgerreur;
    @FXML
    private ChoiceBox<Niveau> niveau;
   private  List<Niveau> listNiveau = new ArrayList<>();  
    private ObservableList<Niveau> Niveaux; 
        ObservableList<Classe> liste_Eleve;
    char ch1 = '4';
    char ch2 = '1';
    char ch4 = '3';
    char ch5 = '2';
    char ch0 = 'A';
   int t= 28;
    int tt = 1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      afficherNiveau();
    }    

    

    @FXML
    private void handleButtonAction(ActionEvent event) {
         ClasseService ps = new ClasseService(); 
        Classe c3 = new Classe(); 
     String c =  ps.findBy(nom.getText()); 
        System.out.println(nom.getText());
        if (c != null) { 
           Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Warning ");
        alert.setHeaderText("ajout classe");
        alert.setContentText("classe existe deja , changer le nom  ");
 
        alert.showAndWait();
      }
       else if(c == null)
         {
            Classe listeClasses = new Classe();
             ClasseService clService = new ClasseService();
             listeClasses.setNom(nom.getText());
              listeClasses.setNiveaux(niveau.getValue().toString());
             if(clService.insertClasse(listeClasses)) 
             {
              //msgerreur.setText("ajout avec succes   ");
               Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajout classe");
 
        // alert.setHeaderText("Results:");
        alert.setContentText("classe est ajouté ");
 
        alert.showAndWait();
        
     ClasseService elService = new ClasseService();
        List<Classe> listeleves = elService.displayClasse();
        for (int i = 0; i < listeleves.size(); i++) {
            liste_Eleve = FXCollections.observableArrayList(listeleves);
        }
       
         }
             } 
              DirecteurAdminController.list_class.setItems(liste_Eleve);
          }
    
 
    @FXML
    private void afficherNiveau(MouseEvent event) {
    }
    public void afficherNiveau()
 {
     NiveauService NivService = new NiveauService();
      List<Niveau> listNiveau = NivService.displayNiveau(); 
         for(int i=0;i< listNiveau.size();i++)
         {
     Niveaux = FXCollections.observableArrayList(listNiveau); 
         } 
        
       niveau.setItems(Niveaux);
        
 }
}
