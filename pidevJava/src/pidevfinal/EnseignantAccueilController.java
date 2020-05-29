/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfinal;

import Entites.Eleve;
import Entites.Enseignants;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pi.NotesEnseignantController;
import service.ClasseService;
import service.EleveService;
import service.EnseignantsService;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class EnseignantAccueilController implements Initializable {
    ObservableList<String> choix = FXCollections.observableArrayList();
    ObservableList<Eleve> liste_Eleve;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button afficherclass;
    @FXML
    private ImageView clas;
    @FXML
    private ImageView etu;
    @FXML
    private Button AjouterAbs;
    @FXML
    private ImageView abs;
    @FXML
    private Button AjouterNote;
    @FXML
    private Button detail;
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
    private ComboBox<String> classes;
    @FXML
    private TableView<Eleve> eleves;
    @FXML
    private TableColumn<Eleve,String> ic_col;
    @FXML
    private TableColumn<Eleve,String> nom_col;
    @FXML
    private TableColumn<Eleve,String> mail_cool;
    @FXML
    private Label num_classe;
    @FXML
    private Label total_cl;

    public static  String x;
        ObservableList<String> liste_profil = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initProfil();
         String p = ConnecterController.n;
         nom_ens.setText(p); 
          choix.removeAll(choix);
         EnseignantsService clService = new EnseignantsService();
         List<Enseignants> listEns = clService.FindByClasse(p);
       for(int i=0;i<listEns.size();i++) {
             String a = listEns.get(i).getClasse();
             String b = listEns.get(i).getClasse2(); 
        choix.addAll(a,b); 
        classes.setItems(choix); 
    }    
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
        me1.setImage(Directeur_dashboardController.img8);
        clas.setImage(Directeur_dashboardController.img14);
        etu.setImage(Directeur_dashboardController.img10);
    } 
    @FXML
    private void afficherClassesAction(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/EnseignantAccueil.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)total_cl.getScene().getWindow();
              first.close();
    }

    @FXML
    private void AjouterAbsenceAction(ActionEvent event) throws IOException  {
      Parent root = FXMLLoader.load(getClass().getResource("AjouterAbsByEns.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
            
    }

    @FXML
    private void AjouterNoteAction(ActionEvent event) throws IOException {
       
          FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pi/notesEnseignant.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)total_cl.getScene().getWindow();
              first.close();
    }

    @FXML
    private void Afficherabsences(ActionEvent event) throws IOException {
           AnchorPane pane = FXMLLoader.load(getClass().getResource("DetailAccueilenseignant.fxml"));
  rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void ProfilAction(ActionEvent event) {
    }

    @FXML
    private void MatiereAction(ActionEvent event) {
    }

    @FXML
    private void EmploisAction(ActionEvent event) {
           FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/EnseignantAccueil.fxml"));
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
              Stage first = (Stage)abs.getScene().getWindow();
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
              Stage first = (Stage)abs.getScene().getWindow();
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
    private void initClasse(ActionEvent event) {
        
            x = classes.getSelectionModel().getSelectedItem();
            num_classe.setText(x);
           EleveService cl = new EleveService();
        int zz = cl.calculerEleveByClasse(x);
      String pp = Integer.toString(zz);
      total_cl.setText(pp);
        EleveService elService = new EleveService();
        List<Eleve> listeleves = elService.displayEleveByClasse(x);
        for (int j = 0; j < listeleves.size(); j++) {
            liste_Eleve = FXCollections.observableArrayList(listeleves);
        }
        ic_col.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nom"));
        mail_cool.setCellValueFactory(new PropertyValueFactory<>("email"));
        eleves.setItems(liste_Eleve);
        } 
         private void initProfil() {
        String p = ConnecterController.n;
        String v = "deconnexion";
        String o = "profil";
        liste_profil.addAll(p, o, v);
        deconnexion.setItems(liste_profil);
    }
    }
    

