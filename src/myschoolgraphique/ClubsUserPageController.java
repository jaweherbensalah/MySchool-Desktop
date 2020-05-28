/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myschoolgraphique;

import entities.club;
import entities.inscription_club;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import pidevfinal.PidevFinal;
import service.ClubService;
import service.inscriptionService;
import util.DataSource;
import ServicesScolarite.SceneChanger;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class ClubsUserPageController implements Initializable {

    @FXML
    private BorderPane locationPane;
    @FXML
    private TextField search;
    @FXML
    private Button participer;
    @FXML
    private ImageView imgv;
    @FXML
    private TableView<club> tableView;
    @FXML
    private TableColumn<club,String> id;
    @FXML
    private TableColumn<club,String> nom;
    @FXML
    private TableColumn<club,String> description;
    @FXML
    private TableColumn<club,Integer> effectif;
    @FXML
    private TableColumn<club,String> domaine;
    @FXML
    private Button Home2;
    @FXML
    private Button Accueil;
    @FXML
    private Button emplois;
    @FXML
    private Button restuarant;
    @FXML
    private Button transport;
    @FXML
    private Button absence;
    @FXML
    private Button punition;
    @FXML
    private Button absence1;
    @FXML
    private Button transport1;
    @FXML
    private Button restuarant1;
    @FXML
    private ImageView log1;
    @FXML
    private Label user;
 private Connection connexion;
    private Statement Ste;
      private club club1;
       ObservableList<club> list  = FXCollections.observableArrayList();
     service.ClubService SCL= new ClubService();
    public ClubsUserPageController(){
        connexion = DataSource.getInstance().getCnx();
    }
     public void Afficher(){
                        try {
             connexion =   DataSource.getInstance().getCnx();
          Ste=connexion.createStatement();

           
                        list.clear();
        for(club cl: SCL.afficheClubs())
             list.add(cl);
      } catch (SQLException ex) {
     }
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
         nom.setCellValueFactory(new PropertyValueFactory<>("nom_club"));
         description.setCellValueFactory(new PropertyValueFactory<>("description"));
         effectif.setCellValueFactory(new PropertyValueFactory<>("effectif"));
         domaine.setCellValueFactory(new PropertyValueFactory<>("domaine"));
       
        tableView.setItems(list);
     tableView.setEditable(true);
     
      id.setCellFactory(TextFieldTableCell.forTableColumn());
      nom.setCellFactory(TextFieldTableCell.forTableColumn());
      description.setCellFactory(TextFieldTableCell.forTableColumn());
      effectif.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      domaine.setCellFactory(TextFieldTableCell.forTableColumn());
      
   
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Afficher();   
       participer.setDisable(false);
        user.setText(pidevfinal.ConnecterController.n);
    }    

    @FXML
    private void participer(ActionEvent event) throws SQLException {
         tableView.setItems(list);
             ObservableList<club> allcommand,SingleDemandes ;
             allcommand=tableView.getItems();
             SingleDemandes=tableView.getSelectionModel().getSelectedItems();
             club c = SingleDemandes.get(0);
           inscriptionService ps = new inscriptionService();
        //java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

      String x = c.getNom_club();
         //  int i = Integer.parseInt(x); 
         
      inscription_club  p;
        p = new inscription_club(pidevfinal.ConnecterController.n,x,"null");
        ps.ajoutpart(p);
        participer.setDisable(true);
                Afficher();
                //RechercheAV();
    }
    

    @FXML
    private void update_Name(TableColumn.CellEditEvent bb) throws SQLException {
         club productselected = tableView.getSelectionModel().getSelectedItem();
     productselected.setNom_club(bb.getNewValue().toString());
  Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SCL.updateClub(productselected);
             }else{System.out.println("Cancel");}
    }

    @FXML
    private void update_Description(TableColumn.CellEditEvent bb) throws SQLException {
        club productselected = tableView.getSelectionModel().getSelectedItem();
     productselected.setDescription(bb.getNewValue().toString());
  Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SCL.updateClub(productselected);
             }else{System.out.println("Cancel");
             
             }
    }

    @FXML
    private void update_Effectif(TableColumn.CellEditEvent bb) throws SQLException {
        club productselected = tableView.getSelectionModel().getSelectedItem();
     productselected.setEffectif(Integer.parseInt(bb.getNewValue().toString()));
  Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SCL.updateClub(productselected);
             }else{System.out.println("Cancel");
             
             }
    }
    

    @FXML
    private void update_Domaine(TableColumn.CellEditEvent bb) throws SQLException {
         club productselected = tableView.getSelectionModel().getSelectedItem();
     productselected.setDomaine(bb.getNewValue().toString());
  Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SCL.updateClub(productselected);
             }else{System.out.println("Cancel");
             
             }
    }

    @FXML
    private void HomeBack2(ActionEvent event) {
    }

    @FXML
    private void AccueilAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/Eleve_Accueil.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)Home2.getScene().getWindow();
              first.close();
    }

    @FXML
    private void AfficherEmploisAction(ActionEvent event) {
    }

    @FXML
    private void afficherEvent(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/myschoolgraphique/afficheEvent.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)Home2.getScene().getWindow();
              first.close();
    }
     @FXML
    private void aficherclub(ActionEvent event) throws IOException {
          Parent tableView = FXMLLoader.load(getClass().getResource("/myschoolgraphique/clubsUserPage.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }
    @FXML
    private void afficherTransportAction(ActionEvent event) throws IOException {
        SceneChanger sc = new SceneChanger();
        
        //check if it is an admin user and go to the table view
        //if (SceneChanger.getLoggedInUser().isAdmin())
            sc.changeScenes(event, "/StudentServices/FXMLDocument.fxml", "All Menues");
    }
        @FXML
    private void afficherRestauAction(ActionEvent event) throws IOException {
     SceneChanger sc = new SceneChanger();
        
        //check if it is an admin user and go to the table view
        //if (SceneChanger.getLoggedInUser().isAdmin())
            sc.changeScenes(event, "/StudentServices/FXMLDocument.fxml", "All Menues");     
    }
    @FXML
    private void afficherPunitionAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/Punition_Eleve.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)Home2.getScene().getWindow();
              first.close();
    }

    @FXML
    private void afficherAbsenceAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/Absence_Eleve.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)Home2.getScene().getWindow();
              first.close();
    }

    @FXML
    private void displayNote(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pi/afficheNotes.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)Home2.getScene().getWindow();
              first.close();
    }

    @FXML
    private void displayBu(ActionEvent event) {
    }
    
}
