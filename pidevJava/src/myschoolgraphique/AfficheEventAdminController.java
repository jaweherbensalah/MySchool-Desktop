/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myschoolgraphique;

import entities.event;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.Date;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pidevfinal.PidevFinal;
import util.DataSource;
import java.util.List;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.util.converter.IntegerStringConverter;
import pidevfinal.Directeur_dashboardController;
import service.EventService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import static util.SendWhatsAppMessage.ACCOUNT_SID;
import static util.SendWhatsAppMessage.AUTH_TOKEN;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AfficheEventAdminController implements Initializable {

    @FXML
    private Label msg;
    @FXML
    private TableView<event> tableView;
    @FXML
    private TableColumn<event,Integer> id;
    @FXML
    private TableColumn<event, String> nom;
    @FXML
    private TableColumn<event, String> description;
    @FXML
    private TableColumn<event,Integer> placeDispo;
    @FXML
    private TableColumn<event, Date> dateEvent;
    @FXML
    private TableColumn<event, String> HeureEvent;
    @FXML
    private TableColumn<event, ImageView> Image;
    @FXML
    private TableColumn<event,Integer> club_id;
    @FXML
    private Button delete;
    @FXML
    private TextField search;
    @FXML
    private ImageView insc;
    @FXML
    private ImageView parti;
    @FXML
    private ImageView even;
    @FXML
    private ImageView clu;
    @FXML
    private ComboBox<String> deconnexion;
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
    private ImageView ms;
         ObservableList<String> liste_profil = FXCollections.observableArrayList();
  private Statement Ste;
    private PreparedStatement pst;
    private Connection connexion;
     
    ObservableList<event> list  = FXCollections.observableArrayList();
     service.EventService SCL= new EventService();
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
          //Image.setHeight(200);
          //Image.setPreHeight(0x50);
         Image.setCellValueFactory(new PropertyValueFactory<>("photo"));
         club_id.setCellValueFactory(new PropertyValueFactory<>("club_id"));
       
        tableView.setItems(list);
     tableView.setEditable(true);
     
      id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      nom.setCellFactory(TextFieldTableCell.forTableColumn());
      description.setCellFactory(TextFieldTableCell.forTableColumn());
      placeDispo.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      // dateEvent.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
      HeureEvent.setCellFactory(TextFieldTableCell.forTableColumn());
       club_id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
   
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      initProfil();
       Afficher();
        RechercheAV();
        
        
                Image img1 = new Image("/images/ajoutEvent.png");
          even.setImage(img1);
         
         Image img3 = new Image("/images/ajoutClub.png");
          clu.setImage(img3);
          Image img4 = new Image("/images/inscription.png");
          insc.setImage(img4);
          Image img5 = new Image("/images/part.png");
          parti.setImage(img5);
          dash.setImage(Directeur_dashboardController.img1);
        ecole.setImage(Directeur_dashboardController.img2);
        ens.setImage(Directeur_dashboardController.img3);
        res.setImage(Directeur_dashboardController.img4);
        trans.setImage(Directeur_dashboardController.img5);
        ms.setImage(Directeur_dashboardController.img6);
        ev.setImage(Directeur_dashboardController.img7);
        me.setImage(Directeur_dashboardController.img8); 
    }    

    @FXML
    private void update_nom(TableColumn.CellEditEvent bb) throws SQLException {
        event productselected = tableView.getSelectionModel().getSelectedItem();
     productselected.setNom_event(bb.getNewValue().toString());
  Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SCL.modifierEvent(productselected);
             }else{System.out.println("Cancel");
             
             }
                     RechercheAV();

    }

    @FXML
    private void update_description(TableColumn.CellEditEvent bb) throws SQLException {
        event productselected = tableView.getSelectionModel().getSelectedItem();
     productselected.setDescription(bb.getNewValue().toString());
  Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SCL.modifierEvent(productselected);
             }else{System.out.println("Cancel");
             
             }
                     RechercheAV();

    }

    @FXML
    private void update_placeDispo(TableColumn.CellEditEvent bb) throws SQLException {
          event productselected = tableView.getSelectionModel().getSelectedItem();
     productselected.setPlaceDispo(Integer.parseInt(bb.getNewValue().toString()));
  Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SCL.modifierEvent(productselected);
             }else{System.out.println("Cancel");
             
             }
                     RechercheAV();

    }

    @FXML
    private void update_Heure(TableColumn.CellEditEvent bb) throws SQLException {
          event productselected = tableView.getSelectionModel().getSelectedItem();
     productselected.setHeureEvent(bb.getNewValue().toString());;
  Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SCL.modifierEvent(productselected);
             }else{System.out.println("Cancel");
             
             }
                     RechercheAV();

    }

    @FXML
    private void update_Image(TableColumn.CellEditEvent bb) throws SQLException {
         event productselected = tableView.getSelectionModel().getSelectedItem();
     productselected.setImage(bb.getNewValue().toString());;
  Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SCL.modifierEvent(productselected);
             }else{System.out.println("Cancel");
             
             }
                     RechercheAV();
    }

    @FXML
    private void updateClub_id(TableColumn.CellEditEvent bb) throws SQLException {
         event productselected = tableView.getSelectionModel().getSelectedItem();
     productselected.setClub_id(Integer.parseInt(bb.getNewValue().toString()));
  Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SCL.modifierEvent(productselected);
             }else{System.out.println("Cancel");
             
             }
                     RechercheAV();
    }

    @FXML
    private void delete(ActionEvent event) {
         tableView.setItems(list);

             ObservableList<event> allcommand,SingleDemandes ;
             allcommand=tableView.getItems();
             SingleDemandes=tableView.getSelectionModel().getSelectedItems();
             event c = SingleDemandes.get(0);
              EventService SCL= new EventService();
     
              Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("confirmation delete");
             alert.setHeaderText("this confirmation about delet");
             alert.setContentText("are you sure to delete??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
                 SCL.supprimeEvent(c.getId());
             SingleDemandes.forEach(allcommand::remove);
               Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+21629851315"),
                new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                //" lkhir" +nom.getText()+"wl barka  💖 !")
                 " Désolé ! Myschool vous informe que notre événement a était annulé pour certains raisons !Stay tuned pour nos prochains événements ")
           .create();
        System.out.println(message.getSid()); 
              
        
            
           
           
             
             }else{System.out.println("Cancel");}
                   RechercheAV();
    }

    @FXML
    private void suiviEvent(ActionEvent event) throws IOException {
        Parent tableView = FXMLLoader.load(getClass().getResource("suiviEvent.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); 
    }

    @FXML
    private void INSCRIPTIONS(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/pidevfinal/afficheinscriptions.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void AffecterEleve(ActionEvent event) {
    }

    @FXML
    private void AjouterEVENT(ActionEvent event) throws IOException {
         Parent tableView = FXMLLoader.load(getClass().getResource("/myschoolgraphique/AjoutEvent.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
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
    private void AjouterCLUB(MouseEvent event) {
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
              Stage first = (Stage)ecole.getScene().getWindow();
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
              Stage first = (Stage)ecole.getScene().getWindow();
              first.close();
    }

    @FXML
    private void RésultatsButtonAction(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("AjoutEmploi.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
       
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
              Stage first = (Stage)dash.getScene().getWindow();
              first.close();
    }

    @FXML
    private void messageButtonAction(ActionEvent event) {
    }

    @FXML
    private void evenementButtonAction(ActionEvent event) throws IOException {
         Parent tableView = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
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
              Stage first = (Stage)clu.getScene().getWindow();
              first.close();
    }
     private void initProfil() {
        String p = pidevfinal.ConnecterController.n;
        String v = "deconnexion";
        String o = "profil";
        liste_profil.addAll(p, o, v);
        deconnexion.setItems(liste_profil);
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
}
