/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myschoolgraphique;

import entities.inscription_club;
import entities.participation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import pidevfinal.Directeur_dashboardController;
import pidevfinal.PidevFinal;
import service.ParticipationService;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AfficeParticipationsController implements Initializable {

    @FXML
    private Label msg;
    @FXML
    private TextField search;
    @FXML
    private TableView<participation> tableView;
    @FXML
    private TableColumn<participation, Integer> id;
    @FXML
    private TableColumn<participation, String> iduser;
    @FXML
    private TableColumn<participation, String> idclub;
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
    private ImageView clu1;
    @FXML
    private ImageView even1;
    @FXML
    private ImageView insc1;
    @FXML
    private ImageView parti1;
     @FXML
    private ImageView ms;
    @FXML
    private ComboBox<String> deconnexion;
    ObservableList<String> liste_profil = FXCollections.observableArrayList();

    ObservableList<participation> list  = FXCollections.observableArrayList();
     service.ParticipationService SCL= new ParticipationService();
    /**
     * Initializes the controller class.
     */
     @FXML
    private TableColumn<participation, Date> stat;
    
    public void Afficher(){
                        try {
                            Connection connexion = DataSource.getInstance().getCnx();
                            Statement Ste = connexion.createStatement();

           
                        list.clear();
        for(participation cl: SCL.afficheInscriptions())
             list.add(cl);
      } catch (SQLException ex) {
     }
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
         iduser.setCellValueFactory(new PropertyValueFactory<>("nom"));
         idclub.setCellValueFactory(new PropertyValueFactory<>("idevent"));
        // stat.setCellValueFactory(new PropertyValueFactory<>("placeDispo"));
         stat.setCellValueFactory(new PropertyValueFactory<>("createdat"));
         
       
        tableView.setItems(list);
     tableView.setEditable(true);
     
      id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
     // iduser.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      idclub.setCellFactory(TextFieldTableCell.forTableColumn());
     //.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      // dateEvent.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      iduser.setCellFactory(TextFieldTableCell.forTableColumn());
    } 
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img1 = new Image("/images/ajoutEvent.png");
          even1.setImage(img1);
         Image img2 = new Image("/images/part.png");
          parti1.setImage(img2);
         Image img3 = new Image("/images/ajoutClub.png");
          clu1.setImage(img3);
          Image img4 = new Image("/images/inscription.png");
          insc1.setImage(img4);
           dash.setImage(Directeur_dashboardController.img1);
        ecole.setImage(Directeur_dashboardController.img2);
        ens.setImage(Directeur_dashboardController.img3);
        res.setImage(Directeur_dashboardController.img4);
        trans.setImage(Directeur_dashboardController.img5);
        ms.setImage(Directeur_dashboardController.img6);
        ev.setImage(Directeur_dashboardController.img7);
        me.setImage(Directeur_dashboardController.img8);  
         Afficher();
         initProfil();
    }    

    
    @FXML
    private void RechercheAV(ActionEvent event) {
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
              Stage first = (Stage)me.getScene().getWindow();
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
              Stage first = (Stage)me.getScene().getWindow();
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
              Stage first = (Stage)clu1.getScene().getWindow();
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
              Stage first = (Stage)me.getScene().getWindow();
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
              Stage first = (Stage)clu1.getScene().getWindow();
              first.close();
    }

    @FXML
    private void AffecterEleve(ActionEvent event) throws IOException {
         Parent tableView = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); 
    }

    @FXML
    private void AjouterEVENT(ActionEvent event) throws IOException {
         Parent tableView = FXMLLoader.load(getClass().getResource("AjoutEvent.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void INSCRIPTIONS(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/myschoolgraphique/afficheinscriptions.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)ecole.getScene().getWindow();
              first.close();
    }

    @FXML
    private void deconnexionAction(ActionEvent event) {
           String p = pidevfinal.ConnecterController.n;
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
              Stage first = (Stage)ecole.getScene().getWindow();
              first.close();
    }
    } 
    @FXML
    private void AjouterCLUB(MouseEvent event) throws IOException {
        Parent tableView = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); 
    }
    private void initProfil() {
        String p = pidevfinal.ConnecterController.n;
        String v = "deconnexion";
        String o = "profil";
        liste_profil.addAll(p, o, v);
        deconnexion.setItems(liste_profil);
    }
}
