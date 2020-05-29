/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfinal;

import Entites.Enseignants;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.EnseignantsService;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Admin_enseignantController implements Initializable {
   public static TableView<Enseignants> list_Ens2;
        public static TableView<Enseignants> list_Ens3;
       ObservableList<Enseignants> liste_Enseignants ; 
    @FXML
    private AnchorPane rootPane;
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
    private ImageView mes;
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
    private ComboBox<String> deconnexion;
    @FXML
    private TableView<Enseignants> enseignants;
    @FXML
    private TableColumn<Enseignants,String> id_ens;
    @FXML
    private TableColumn<Enseignants,String> nom_ens;
    @FXML
    private TableColumn<Enseignants,String> email_ens;
    @FXML
    private TableColumn<Enseignants,String> classe_ens;
    @FXML
    private TableColumn<Enseignants,String> classe_ens1;
    @FXML
    private TableColumn<Enseignants,String> matiere_ens;
    @FXML
    private Button supprimer2;
    @FXML
    private Button affclasse2;
    @FXML
    private ImageView ne;
    @FXML
    private Button inscription;
    @FXML
    private ImageView affM;
    @FXML
    private ImageView abs;
    @FXML
    private Button AjouterAbs;
    @FXML
    private ImageView cl;
    @FXML
    private Button ajouterCla;
    @FXML
    private ImageView pu;
    @FXML
    private Button AjouterPun;
    ObservableList<String> liste_profil = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      list_Ens2 = enseignants;
        initEns();
        initProfil();
         // afficher les images 
        
        dash.setImage(Directeur_dashboardController.img1);
        ecole.setImage(Directeur_dashboardController.img2);
        ens.setImage(Directeur_dashboardController.img3);
        res.setImage(Directeur_dashboardController.img4);
        trans.setImage(Directeur_dashboardController.img5);
       // ms.setImage(Directeur_dashboardController.img6);
        ev.setImage(Directeur_dashboardController.img7);
        me.setImage(Directeur_dashboardController.img8);
        ne.setImage(Directeur_dashboardController.img9);
       // etu.setImage(Directeur_dashboardController.img10);
        abs.setImage(Directeur_dashboardController.img11);
        cl.setImage(Directeur_dashboardController.img12);
        pu.setImage(Directeur_dashboardController.img13);
        Image  img6 = new Image("/images/icono-maestro-png-6.png");
        affM.setImage(img6);
        /*
        enseignants.setRowFactory(tv -> new TableRow<Enseignants>() {
    public void updateItem(Enseignants item, boolean empty) {
        super.updateItem(item, empty) ;
        
            setStyle("-fx-background-color: #F7F7F0;");
       
    }
}); */ 
    }    
                
    @FXML
    private void dashboardButtonAction(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("Directeur_dashboard.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)pu.getScene().getWindow();
              first.close();
    }

    @FXML
    private void enseignantButtonAction(ActionEvent event) {
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
              Stage first = (Stage)pu.getScene().getWindow();
              first.close();
    }

    @FXML
    private void RésultatsButtonAction(ActionEvent event) throws IOException {
           Parent root = FXMLLoader.load(getClass().getResource("AjoutEmploi.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void transportButtonAction(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/views/TransportTableView.fxml"));
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
    private void messageButtonAction(ActionEvent event) {
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
              Stage first = (Stage)pu.getScene().getWindow();
              first.close();
    }

    @FXML
    private void menuButtonAction(ActionEvent event) {FXMLLoader loader = new FXMLLoader();
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
    private void deconnexionAction(ActionEvent event) {
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
              Stage first = (Stage)ne.getScene().getWindow();
              first.close();
    }
    } 
    @FXML
    private void supprimerPunitionAction(ActionEvent event) throws IOException {
          AnchorPane pane = FXMLLoader.load(getClass().getResource("detailEns.fxml"));
  rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void AffecterClasse2Action(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Affecter_enseignant2.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();    
    }

    @FXML
    private void inscriptionButttonAction(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pi/AfficheMatieres.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)ne.getScene().getWindow();
              first.close();
    }

    @FXML
    private void AffecterMatiereAction(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AffecterMatiere.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void AjouterAbsenceAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AffecterEnseignant.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void AjouterClassesAction(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void AjouterPunitionAction(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("ajouterPunition.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }
     private void initEns() {
         EnseignantsService elService = new EnseignantsService();
         List<Enseignants> listens = elService.displayEnseignant();
        for (int i = 0; i < listens.size(); i++) {
            liste_Enseignants = FXCollections.observableArrayList(listens);
        }
        id_ens.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_ens.setCellValueFactory(new PropertyValueFactory<>("nom"));
        email_ens.setCellValueFactory(new PropertyValueFactory<>("email"));
        classe_ens.setCellValueFactory(new PropertyValueFactory<>("classe"));
        classe_ens1.setCellValueFactory(new PropertyValueFactory<>("classe2"));
        matiere_ens.setCellValueFactory(new PropertyValueFactory<>("matiere"));
       
        enseignants.setItems(liste_Enseignants);
    }
      private void initProfil() {
        String p = ConnecterController.n;
        String v = "deconnexion";
        String o = "profil";
        liste_profil.addAll(p, o, v);
        deconnexion.setItems(liste_profil);
    }
}
