/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamations;

import Entites.Message;
//import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static org.joda.time.format.ISODateTimeFormat.date;
import pidevfinal.PidevFinal;
import service.AbsenceService;
import service.reclamationService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AfficherMessagesController implements Initializable {

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
    private TableView<Message> messag;
    @FXML
    private TableColumn<Message, Date> date_col;
    @FXML
    private TableColumn<Message, String> titre_col;
    public static     ObservableList<Message> allpunition;
public static String n ; 
public static String y ; 
public static String p;
 ObservableList<Message> liste_msg;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      reclamationService elService = new reclamationService();
        List<Message> listeleves = elService.displayReclamation();
        for (int j = 0; j < listeleves.size(); j++) {
            liste_msg = FXCollections.observableArrayList(listeleves);
        }
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        titre_col.setCellValueFactory(new PropertyValueFactory<>("titre"));
        messag.setItems(liste_msg);
    }    
@FXML
    private void envoyer(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/reclamations/AjouterMessages.fxml"));
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
    private void details(ActionEvent event) {
        reclamationService c = new reclamationService();
        
        allpunition = messag.getSelectionModel().getSelectedItems();
     if(allpunition != null) {
         for(Message m : allpunition) {
             n = m.getTitre();
             p = m.getContenu();
             y = m.getDate();
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/reclamations/detail.fxml"));
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
     } } 
    }
    
}
