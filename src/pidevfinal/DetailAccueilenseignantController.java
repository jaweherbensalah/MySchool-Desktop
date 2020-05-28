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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import service.AbsenceService;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DetailAccueilenseignantController implements Initializable {
    ObservableList<Absence> liste_abs;
public static TableView<Absence> list_ab;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label msg;
    @FXML
    private ImageView ecole1;
    @FXML
    private ImageView dash1;
    @FXML
    private Button nom_ens;
    @FXML
    private Button matiere;
    @FXML
    private ImageView mati;
    @FXML
    private ImageView emp;
    @FXML
    private Button emplois;
    @FXML
    private Button rdv;
    @FXML
    private Button contact;
    @FXML
    private ImageView cont;
    @FXML
    private Button evenement1;
    @FXML
    private ImageView ev1;
    @FXML
    private Button menu1;
    @FXML
    private ImageView me1;
    @FXML
    private ImageView mes;
    @FXML
    private ComboBox<String> deconnexion;
    @FXML
    private Button retour;
    @FXML
    private TableView<Absence> absences;
    @FXML
    private TableColumn<Absence, String> ic_col;
    @FXML
    private TableColumn<Absence, String> nom_col;
    @FXML
    private TableColumn<Absence, String> heure_col;
    @FXML
    private TableColumn<Absence, String> date_col;
    @FXML
    private TableColumn<Absence, String> action_col;
    @FXML
    private Label num_classe;
    @FXML
    private Label total_cl;
    ObservableList<String> liste_profil = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initProfil();
          ecole1.setImage(Directeur_dashboardController.img2);
         Image img1 = new Image("/images/cool-profile-icons-64.png");
         dash1.setImage(img1);
         mati.setImage(Directeur_dashboardController.img15);
         Image img2 = new Image("/images/icon.png");
         emp.setImage(img2);
        mes.setImage(Directeur_dashboardController.img6);
        Image img3 = new Image("/images/mo_354424.png");
        cont.setImage(img3);
        ev1.setImage(Directeur_dashboardController.img7);
        
       String n = EnseignantAccueilController.x;
       num_classe.setText(n);
       AbsenceService absService = new AbsenceService();
        int zz = absService.calculerAbsenceByClasse(n);
      String pp = Integer.toString(zz);
      total_cl.setText(pp);
        List<Absence> listabs = absService.displayByClasse(n);
        for (int j = 0; j < listabs.size(); j++) {
            liste_abs = FXCollections.observableArrayList(listabs);
        }
        ic_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("eleve"));
        heure_col.setCellValueFactory(new PropertyValueFactory<>("heure"));
        date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
       absences.setItems(liste_abs);
       editAbsencecol();
       list_ab = absences;
    }    

    @FXML
    private void ProfilAction(ActionEvent event) {
    }

    @FXML
    private void MatiereAction(ActionEvent event) {
    }

    @FXML
    private void EmploisAction(ActionEvent event) {
    }

    @FXML
    private void rdvAction(ActionEvent event) {
    }

    @FXML
    private void ContactAction(ActionEvent event) {
    }

    @FXML
    private void evenementButtonAction(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/myschoolgraphique/FXML.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)cont.getScene().getWindow();
              first.close();
    }

    @FXML
    private void menuButtonAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/views/MenuTableView.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)emp.getScene().getWindow();
              first.close();
    }

    @FXML
    private void deconnexionAction(ActionEvent event) throws IOException {
         String p = ConnecterController.n;
        String v = "deconnexion";
        String o = "profil";
        String m = deconnexion.getValue();
        if (m.equals(v)) {
             FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("connecter.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)msg.getScene().getWindow();
              first.close();
        }
    }

     @FXML
    private void supprimerAbs(ActionEvent event) {
      ObservableList<Absence> absence, allabsence;
        AbsenceService c = new AbsenceService();
        allabsence = absences.getItems();
        absence = absences.getSelectionModel().getSelectedItems();
        for (Absence cl : absence) {
            c.deleteAbsence(cl.getId());
            absence.forEach(allabsence::remove);
        }  
    }
    @FXML
    private void retourAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("EnseignantAccueil.fxml"));
  rootPane.getChildren().setAll(pane);
    }
    private void editAbsencecol() {

        ic_col.setCellFactory(TextFieldTableCell.forTableColumn());
        ic_col.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
        }
        );

        nom_col.setCellFactory(TextFieldTableCell.forTableColumn());
        nom_col.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEleve(e.getNewValue());
        }
        );

        heure_col.setCellFactory(TextFieldTableCell.forTableColumn());
        heure_col.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setHeure(e.getNewValue());
        }
        );
        absences.setEditable(true);

    }
     private void initProfil() {
        String p = ConnecterController.n;
        String v = "deconnexion";
        String o = "profil";
        liste_profil.addAll(p, o, v);
        deconnexion.setItems(liste_profil);
    }
}
