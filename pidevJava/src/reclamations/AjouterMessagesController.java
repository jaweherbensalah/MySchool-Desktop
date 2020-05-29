/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamations;

import Entites.Message;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pidevfinal.PidevFinal;
import service.reclamationService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterMessagesController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView logo;
    @FXML
    private Label user;
    @FXML
    private Button deconnexion;
    @FXML
    private Button Accueil;
    @FXML
    private Button emplois;
    @FXML
    private Button absence;
    @FXML
    private Button punition;
    @FXML
    private Button resultat;
    @FXML
    private Button reclamation;
    @FXML
    private Button transport;
    @FXML
    private Button restuarant;
    @FXML
    private Label pun;
    @FXML
    private Label abs;
    @FXML
    private TextField titre;
    @FXML
    private TextArea contenu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void DeconnexionAction(ActionEvent event) {
    }

    @FXML
    private void AccueilAction(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/eleveacceuil"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)logo.getScene().getWindow();
              first.close();
     }
    

    @FXML
    private void AfficherEmploisAction(ActionEvent event) {
    }

    @FXML
    private void afficherAbsenceAction(ActionEvent event) {
    }

    @FXML
    private void afficherPunitionAction(ActionEvent event) {
    }

    @FXML
    private void afficherRseultatAction(ActionEvent event) {
    }

    @FXML
    private void afficherReclamationAction(ActionEvent event) {
    }

    @FXML
    private void afficherTransportAction(ActionEvent event) {
    }

    @FXML
    private void afficherRestaurantAction(ActionEvent event) {
    }

    @FXML
    private void AddMatiere(ActionEvent event) {
         reclamationService ps = new reclamationService(); 
         String cla = titre.getText(); 
         String ele = contenu.getText(); 
       //  String ma = matieres.getSelectionModel().getSelectedItem().getNomMat();
       String useer = pidevfinal.ConnecterController.n;
       Message msg ; 
       msg = new Message(ele,useer,cla);
         if(ps.insertReclamation(msg))
         {
         Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Reclamation ");
 
        // alert.setHeaderText("Results:");
        alert.setContentText("Votre message est envoyer   ");
 
        alert.showAndWait(); 
         }
         else 
         {
       System.out.println("erreur ");
        Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error ");
alert.setHeaderText("Reclamation");
alert.setContentText("probleme");
 
alert.showAndWait();  
         } 
    }
    
}
