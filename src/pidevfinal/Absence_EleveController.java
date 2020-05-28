/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfinal;

import Entites.Absence;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.AbsenceService;
import service.PunitionService;
import ServicesScolarite.SceneChanger;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Absence_EleveController implements Initializable {

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
    private TableView<Absence> absences;
    @FXML
    private TableColumn<Absence,String> id_absence;
    @FXML
    private TableColumn<Absence,String> nomEns_absence;
    @FXML
    private TableColumn<Absence,String> nomele_absence;
    @FXML
    private TableColumn<Absence,String> matiere_absence;
    @FXML
    private TableColumn<Absence,String> heure_absence;
    @FXML
    private TableColumn<Absence,String> date_absence;
     @FXML
    private AnchorPane rootPane;
 ObservableList<Absence> liste_Absences;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img1 = new Image("/images/logo.png");
        logo.setImage(img1);
        user.setText(ConnecterController.n);
        String nom = ConnecterController.n; 
          AbsenceService abservice = new AbsenceService(); 
        int c = abservice.calculerNomAbsence(nom); 
         String  z = Integer.toString(c);
         abs.setText(z);
        PunitionService pu = new PunitionService(); 
         int w = pu.calculerNomPunition(nom); 
      String  p = Integer.toString(w);
         pun.setText(p);
         initAbsence();
    }    

    @FXML
    private void DeconnexionAction(ActionEvent event) throws IOException {
              FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/connecter.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)abs.getScene().getWindow();
              first.close();
    }

    @FXML
    private void AccueilAction(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("Eleve_Accueil.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void AfficherEmploisAction(ActionEvent event) {
    }

    @FXML
    private void afficherAbsenceAction(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("Absence_Eleve.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void afficherPunitionAction(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("Punition_Eleve.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void afficherRseultatAction(ActionEvent event) {
    }

    @FXML
    private void afficherReclamationAction(ActionEvent event) {
    }

    @FXML
    private void afficherTransportAction(ActionEvent event) throws IOException {
        SceneChanger sc = new SceneChanger();
        
        //check if it is an admin user and go to the table view
        //if (SceneChanger.getLoggedInUser().isAdmin())
            sc.changeScenes(event, "/StudentServices/FXMLDocument.fxml", "All Menues");
    }

    @FXML
    private void afficherRestaurantAction(ActionEvent event) throws IOException {
        SceneChanger sc = new SceneChanger();
        
        //check if it is an admin user and go to the table view
        //if (SceneChanger.getLoggedInUser().isAdmin())
            sc.changeScenes(event, "/StudentServices/FXMLDocument.fxml1", "All Menues");
    }
     private void initAbsence() { 
         String nom = ConnecterController.n; 
        AbsenceService elService = new AbsenceService();
        List<Absence> listAbsence = elService.displayAbsenceByNom(nom);
        for (int i = 0; i < listAbsence.size(); i++) {
            liste_Absences = FXCollections.observableArrayList(listAbsence);
        }
        id_absence.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomEns_absence.setCellValueFactory(new PropertyValueFactory<>("ens"));
        nomele_absence.setCellValueFactory(new PropertyValueFactory<>("eleve"));
        matiere_absence.setCellValueFactory(new PropertyValueFactory<>("matier"));
        heure_absence.setCellValueFactory(new PropertyValueFactory<>("heure"));
        date_absence.setCellValueFactory(new PropertyValueFactory<>("date"));
        absences.setItems(liste_Absences);
                }
}
