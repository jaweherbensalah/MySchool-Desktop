/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myschoolgraphique;

import entities.event;
import entities.participation;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pidevfinal.PidevFinal;
import service.EventService;
import service.ParticipationService;
import util.DataSource;
import util.SendMail1;
import ServicesScolarite.SceneChanger;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AfficheEventController implements Initializable {

    @FXML
    private TableView<event> tableView;
    @FXML
    private TableColumn<event,Integer> id;
    @FXML
    private TableColumn<event, String> nom;
    @FXML
    private TableColumn<event, String> description;
    @FXML
    private TableColumn<event, Integer> placeDispo;
    @FXML
    private TableColumn<event, Date> dateEvent;
    @FXML
    private TableColumn<event, String> HeureEvent;
    @FXML
    private TableColumn<event, ImageView> Image;
    @FXML
    private TableColumn<event, Integer> club_id;
    @FXML
    private TextField search;
    @FXML
    private Button participer;
    @FXML
    private ImageView imgv;
    @FXML
    private Button Home2;
    @FXML
    private Button Accueil;
    @FXML
    private Button emplois;
    @FXML
    private Button absence;
    @FXML
    private Button punition;
    @FXML
    private Button restuarant;
    @FXML
    private Button transport;
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
ObservableList<event> list  = FXCollections.observableArrayList();
     service.EventService SCL= new EventService();
      private Statement Ste;
    private PreparedStatement pst;
    private Connection connexion;
    
    
    public void Afficher(){
                        try {
             connexion =   DataSource.getInstance().getCnx();
          Ste=connexion.createStatement();
          
           
                        list.clear();
        for(event cl: SCL.afficheEvents())
             list.add(cl);
      } catch (SQLException ex) {
     }
                        
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
         nom.setCellValueFactory(new PropertyValueFactory<>("nom_event"));
         description.setCellValueFactory(new PropertyValueFactory<>("description"));
         placeDispo.setCellValueFactory(new PropertyValueFactory<>("placeDispo"));
         dateEvent.setCellValueFactory(new PropertyValueFactory<>("DateEvent"));
         HeureEvent.setCellValueFactory(new PropertyValueFactory<>("HeureEvent"));
         Image.setPrefWidth(80);
         Image.setCellValueFactory(new PropertyValueFactory<>("photo"));
         club_id.setCellValueFactory(new PropertyValueFactory<>("club_id"));
       
        tableView.setItems(list);   
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        user.setText(pidevfinal.ConnecterController.n);
        Afficher();
        RechercheAV();
        participer.setDisable(true);
        //javafx.scene.image.Image img2 = new javafx.scene.image.Image("/images/logo.png");
         // log1.setImage(img2);
    }    
 public void RechercheAV(){
                // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<event> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(groupe -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				if (groupe.getNom_event().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; // Filter matches first name.
				} else if (String.valueOf(groupe.getId()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<event> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		tableView.setItems(sortedData);
    }
    @FXML
    private void verifparti(MouseEvent event) {
         tableView.setItems(list);
                ParticipationService ps = new ParticipationService();
             ObservableList<event> allcommand,SingleDemandes ;
             allcommand=tableView.getItems();
             SingleDemandes=tableView.getSelectionModel().getSelectedItems();
             event c = SingleDemandes.get(0);
             System.out.println(c.getNom_event());
             participation p = null;
             p=ps.verif(pidevfinal.ConnecterController.n, c.getNom_event());
             System.out.println(p);
             if(c.getPlaceDispo()==0)
             {
                 participer.setDisable(true);
             }
             else if(p!=null)
             {
                 participer.setDisable(true);
                  SendMail1.sendMail("sirine.charrad@esprit.tn", " confirmation Participation Event "," soyez à l'heure et à bientôt");
                   System.out.println("ok bb");
              
             } 
             else
             {
                 participer.setDisable(false);
             }
                     RechercheAV();
    }

    @FXML
    private void participer(ActionEvent event) throws SQLException {
         tableView.setItems(list);
             ObservableList<event> allcommand,SingleDemandes ;
             allcommand=tableView.getItems();
             SingleDemandes=tableView.getSelectionModel().getSelectedItems();
             event c = SingleDemandes.get(0);
        ParticipationService ps = new ParticipationService();
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        participation p = new participation(2, c.getNom_event(),date,pidevfinal.ConnecterController.n);
        ps.ajoutpart(p);
        participer.setDisable(true);
                Afficher();
                RechercheAV();
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
    private void afficherEvent(ActionEvent event) throws IOException {
         Parent tableView = FXMLLoader.load(getClass().getResource("/myschoolgraphique/afficheEvent.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); 
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
              first.close();} 
    
    @FXML
    private void displayBu(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pi/Bulletin.fxml"));
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
            sc.changeScenes(event, "/StudentServices/FXMLDocument.fxml", "All Menues");
    }
    
}
