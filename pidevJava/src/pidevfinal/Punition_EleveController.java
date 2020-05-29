/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfinal;

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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import service.AbsenceService;
import service.PunitionService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Punition_EleveController implements Initializable {
    ObservableList<Punition> liste_Punition;

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
    private TableView<Punition> punitions;
    @FXML
    private TableColumn<Punition, String> id_puns;
    @FXML
    private TableColumn<Punition, String> nom_puns;
    @FXML
    private TableColumn<Punition, String> type_puns;
    @FXML
    private TableColumn<Punition, String> date_puns;

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
         initPunition(); 
    }    

    @FXML
    private void DeconnexionAction(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("connecter.fxml"));
            rootPane.getChildren().setAll(pane);
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
    private void afficherTransportAction(ActionEvent event) {
    }

    @FXML
    private void afficherRestaurantAction(ActionEvent event) {
    }
    private void initPunition() {
         String nom = ConnecterController.n; 
        PunitionService elService = new PunitionService();
        List<Punition> listPunition = elService.displayAbsenceByNom(nom); 
        for (int i = 0; i < listPunition.size(); i++) {
            liste_Punition = FXCollections.observableArrayList(listPunition);
        }
        id_puns.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_puns.setCellValueFactory(new PropertyValueFactory<>("eleve"));
        type_puns.setCellValueFactory(new PropertyValueFactory<>("cause"));
        date_puns.setCellValueFactory(new PropertyValueFactory<>("date"));
        punitions.setItems(liste_Punition);

    }
    
}
