/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.ImageView;
//import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author hp
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
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
    private Button resultat;
    
    private void handleButtonAction(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void DeconnexionAction(ActionEvent event) {
    }

    @FXML
    private void AccueilAction(ActionEvent event) {
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
    private void afficherReclamationAction(ActionEvent event) {
    }

    @FXML
    private void afficherTransportAction(ActionEvent event) {
    }

    @FXML
    private void afficherRestaurantAction(ActionEvent event) {
    }


    @FXML
    private void displayNotes(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("afficheNotes.fxml"));
        Parent afficheNotes =loader.load();
        Scene affichage=new Scene(afficheNotes);
        
        AfficheNotesController controller = loader.getController();
        controller.displayNote();
        
         Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
         window.setScene(affichage);
         window.show();
    
      
    }
    

    @FXML
    private void afficherRseultatAction(ActionEvent event) throws IOException {
        
    FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("Bulletin.fxml"));
        Parent afficheNotes =loader.load();
        Scene affichage=new Scene(afficheNotes);
        
        BulletinController controller = loader.getController();
        controller.afficheResultatAction();;
        
         Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
         window.setScene(affichage);
         window.show();


     
    }
}
