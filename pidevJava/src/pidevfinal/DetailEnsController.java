/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfinal;

import Entites.Absence;
import Entites.Classe;
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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.AbsenceService;
import service.ClasseService;
import service.EleveService;
import service.EnseignantsService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DetailEnsController implements Initializable {
      ObservableList<Absence> absences ; 
          ObservableList<String> choice = FXCollections.observableArrayList();

    @FXML
    private ImageView ecole;
    @FXML
    private  ComboBox<String> classess;
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
    private Button retour;
    @FXML
    private Label nom_classe;
    @FXML
    private Label nbr_classe;
    @FXML
    private TableView<Absence> absence;
    @FXML
    private TableColumn<Absence,String> date_col;
    @FXML
    private TableColumn<Absence,String> heure_col;
    @FXML
    private TableColumn<Absence,String> nom_eleveCol;
    @FXML
    private Label nbr_pres;
    @FXML
    private Label nbr_abs;
@FXML
    private AnchorPane rootPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
              intiClasse();
       dash.setImage(Directeur_dashboardController.img1);
        ecole.setImage(Directeur_dashboardController.img2);
        ens.setImage(Directeur_dashboardController.img3);
        res.setImage(Directeur_dashboardController.img4);
        trans.setImage(Directeur_dashboardController.img5);
        mes.setImage(Directeur_dashboardController.img6);
        ev.setImage(Directeur_dashboardController.img7);
        me.setImage(Directeur_dashboardController.img8);
       
       
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
              Stage first = (Stage)nbr_pres.getScene().getWindow();
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
              Stage first = (Stage)nbr_abs.getScene().getWindow();
              first.close();
    }

    @FXML
    private void RésultatsButtonAction(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("AjoutEmploi.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)nbr_abs.getScene().getWindow();
              first.close();
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
              Stage first = (Stage)dash.getScene().getWindow();
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
              Stage first = (Stage)nbr_abs.getScene().getWindow();
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
              Stage first = (Stage)ens.getScene().getWindow();
              first.close();
    }
    @FXML
    private void affihcerAction(ActionEvent event) {
         String n = Admin_enseignantController.list_Ens2.getSelectionModel().getSelectedItem().getNom();
        String c = classess.getValue();
        System.out.println(c);
        nom_classe.setText(c);
        EleveService cl = new EleveService();
        int x = cl.calculerEleveByClasse(c);
      String b = Integer.toString(x);
      nbr_classe.setText(b);
      AbsenceService abs = new AbsenceService();
      int a = abs.calculerAbsenceByClasse(c);
      String z = Integer.toString(a);
      nbr_abs.setText(z);
      int p = x-a;
      String o = Integer.toString(p);
      nbr_pres.setText(o); 
      initEns(n,c);
    }

    @FXML
    private void retourAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("admin_enseignant.fxml"));
  rootPane.getChildren().setAll(pane);
    }
    private void initEns(String nom,String cl) {
        
         AbsenceService clService = new AbsenceService();
     List<Absence> listAbsence = clService.displayByEns(nom,cl);
         for(int i=0;i< listAbsence.size();i++)
         {
      absences= FXCollections.observableArrayList(listAbsence); 
         }
       date_col.setCellValueFactory(new PropertyValueFactory<>("date"));
        nom_eleveCol.setCellValueFactory(new PropertyValueFactory<>("eleve"));
        heure_col.setCellValueFactory(new PropertyValueFactory<>("heure"));
         
       absence.setItems(absences); 
    }
    private void  intiClasse() {
        choice.removeAll(choice);
        String n = Admin_enseignantController.list_Ens2.getSelectionModel().getSelectedItem().getClasse();
        String x = Admin_enseignantController.list_Ens2.getSelectionModel().getSelectedItem().getClasse2();
        choice.addAll(n,x);
       classess.setItems(choice);  
    }
}
