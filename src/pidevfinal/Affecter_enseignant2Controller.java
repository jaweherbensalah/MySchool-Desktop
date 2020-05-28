
package pidevfinal;

import Entites.Classe;
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
import service.ClasseService;
import service.EnseignantsService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Affecter_enseignant2Controller implements Initializable {
    ObservableList<Classe> classe; 
      ObservableList<Enseignants> enseignant ; 
             ObservableList<Enseignants> liste_Enseignants ; 
    ObservableList<String> choice = FXCollections.observableArrayList();

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button button;
    @FXML
    private ChoiceBox<Classe> classes;
    @FXML
    private ChoiceBox<String> enseignants;
    @FXML
    private Label msg;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        EnseignantsService ps = new EnseignantsService(); 
         String cla = classes.getSelectionModel().getSelectedItem().getNom(); 
         String ele = enseignants.getSelectionModel().getSelectedItem(); 
       //  String ma = matieres.getSelectionModel().getSelectedItem().getNomMat();
         if(ps.affecterEnseignant2(ele, cla))
         {
       System.out.println("affecter avec succes");       
   EnseignantsService elService = new EnseignantsService();
         List<Enseignants> listens = elService.displayEnseignant();
        for (int i = 0; i < listens.size(); i++) {
            liste_Enseignants = FXCollections.observableArrayList(listens);
        }
      Admin_enseignantController.list_Ens2.setItems(liste_Enseignants);
        
         Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Affectation enseignants ");
 
        // alert.setHeaderText("Results:");
        alert.setContentText("enseignants est  affecté  ");
 
        alert.showAndWait(); 
         }
         else 
         {
       System.out.println("erreur ");
        Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error ");
alert.setHeaderText("Affectation enseignants");
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
    private void afficherEnseignant(MouseEvent event) {
        choice.removeAll(choice);
        String n = Admin_enseignantController.list_Ens2.getSelectionModel().getSelectedItem().getNom();
      choice.addAll(n);
       enseignants.setItems(choice);  
     
    }
}

