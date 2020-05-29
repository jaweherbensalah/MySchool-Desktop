/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reclamations;

import Entites.Message;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pidevfinal.Directeur_dashboardController;
import pidevfinal.PidevFinal;
import service.reclamationService;
import util.SendMail1;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ReclamationAdminController implements Initializable {

    @FXML
    private AnchorPane supp;
    @FXML
    private ImageView ecole;
    @FXML
    private ImageView dash;
    @FXML
    private Button dashboard;
    @FXML
    private Button enseignant;
    @FXML
    private ImageView res;
    @FXML
    private Button Résultats;
    @FXML
    private ImageView trans;
    @FXML
    private Button transport;
    @FXML
    private Button message;
    @FXML
    private ImageView ms;
    @FXML
    private ImageView ens;
    @FXML
    private Button evenement;
    @FXML
    private ImageView ev;
    @FXML
    private Button menu;
    @FXML
    private ImageView me;
    @FXML
    private TableView<Message> mes;
    @FXML
    private TableColumn<Message,String> elevecol;
    @FXML
    private TableColumn<Message,Date> datecol;
    @FXML
    private TableColumn<Message,String> titrecol;
    @FXML
    private TableColumn<Message,String> textcol;
   ObservableList<Message> liste_msg;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         dash.setImage(Directeur_dashboardController.img1);
        ecole.setImage(Directeur_dashboardController.img2);
        ens.setImage(Directeur_dashboardController.img3);
        res.setImage(Directeur_dashboardController.img4);
        trans.setImage(Directeur_dashboardController.img5);
        ms.setImage(Directeur_dashboardController.img6);
        ev.setImage(Directeur_dashboardController.img7);
        me.setImage(Directeur_dashboardController.img8);
        reclamationService elService = new reclamationService();
        List<Message> listeleves = elService.displayReclamation();
        for (int j = 0; j < listeleves.size(); j++) {
            liste_msg = FXCollections.observableArrayList(listeleves);
        }
        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
        titrecol.setCellValueFactory(new PropertyValueFactory<>("titre"));
        elevecol.setCellValueFactory(new PropertyValueFactory<>("eleve"));
        textcol.setCellValueFactory(new PropertyValueFactory<>("contenu"));
        mes.setItems(liste_msg);
    }    

    @FXML
    private void dashboardButtonAction(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("admin_enseignant.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)ens.getScene().getWindow();
              first.close();
    }
@FXML
    private void supp(ActionEvent event) {
         ObservableList<Message> absence, allabsence;
        reclamationService c = new reclamationService();
        allabsence = mes.getItems();
        absence = mes.getSelectionModel().getSelectedItems();
        for (Message cl : absence) {
            c.deleteReclamation(cl.getId());
            absence.forEach(allabsence::remove);
        }
    }
    @FXML
    private void enseignantButtonAction(ActionEvent event) {
    }

    @FXML
    private void RésultatsButtonAction(ActionEvent event) {
    }

    @FXML
    private void transportButtonAction(ActionEvent event) {
    }

    @FXML
    private void messageButtonAction(ActionEvent event) {
    }

    @FXML
    private void evenementButtonAction(ActionEvent event) {
    }

    @FXML
    private void menuButtonAction(ActionEvent event) {
    }
    @FXML
    private void traiter(ActionEvent event) {
  SendMail1.sendMail("eya.ksouri@esprit.tn", " réclamation a été    "," réclamation a été");
    }
}
