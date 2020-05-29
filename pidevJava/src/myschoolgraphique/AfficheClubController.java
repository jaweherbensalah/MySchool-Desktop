/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myschoolgraphique;

import entities.club;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import pidevfinal.Directeur_dashboardController;
import pidevfinal.PidevFinal;
import service.ClubService;
import service.EventService;
import service.inscriptionService;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AfficheClubController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Label msg;
    @FXML
    private TextField search;
    @FXML
    private ComboBox<String> sortcombo;
    @FXML
    private Button sort;
    @FXML
    private Button ajoutEvent;
    @FXML
    private Button AjoutClub;
    @FXML
    private Button CalculerNombre;
    @FXML
    private TextField resultat;
    @FXML
    private Button inscriptions;
    @FXML
    private PieChart bookChart;
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
    private Button delete;
    @FXML
    private ImageView clu;
    @FXML
    private ImageView parti;
    @FXML
    private ImageView insc;
    @FXML
    private ImageView even;
    @FXML
    private ComboBox<String> deconnexion;
    @FXML
    private ImageView ecole;
    @FXML
    private ImageView dash;
    @FXML
    private ImageView ms;
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
 ObservableList<club> list  = FXCollections.observableArrayList();
     service.ClubService SCL= new ClubService();
         ObservableList<String> liste_profil = FXCollections.observableArrayList();

    private Statement Ste;
    private PreparedStatement pst;
    private Connection connexion;
    /**
     * Initializes the controller class.
     */
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       // TODO
        initProfil();
        Afficher();
          ClubService SA = new ClubService();
          EventService SAo = new EventService();
          
        ObservableList<String> options = FXCollections.observableArrayList(
                "Nom",
                "Effectif"
                
               
        );
        sortcombo.getItems().addAll(options);
   Afficher(); 
   
     String s;
                  s = String.valueOf(SA.calculerTotal());
                        
                     resultat.setText(s);
                       
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                
                new PieChart.Data("club", SA.calculerTotal()),
               new PieChart.Data("event", SAo.calculerEvents())
              
                );
        bookChart.setData(pieChartData);
        bookChart.setClockwise(false);
               CalculerNombre.setOnAction(event->{SA.calculerTotal();});
               
              club p =new club();
               inscriptionService pss=new inscriptionService();
               
              /*  inscrire.setOnAction(e->
                {
        
          club x= tableView.getSelectionModel().getSelectedItem();
              
               p.setId(x.getId());
                
                  
                      pss.inscrire(p); 
            
               
               
               
                }   */
              
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
    private void RechercheAV(ActionEvent event) {
         FilteredList<club> filteredData = new FilteredList<>(list, b -> true);
		
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(club -> {
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				String lowerCaseFilter = newValue.toLowerCase();
				if (club.getDomaine().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true; 
				} else if (String.valueOf(club.getEffectif()).indexOf(lowerCaseFilter)!=-1) {
					return true; 
				}
				else
				
				if (String.valueOf(club.getNom_club()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
				    	 return false; 
			});
		});
		
		
		SortedList<club> sortedData = new SortedList<>(filteredData);
		
		
		sortedData.comparatorProperty().bind(tableView.comparatorProperty());
		
		
		tableView.setItems(sortedData);
    }

    @FXML
    private void sort(ActionEvent event) throws SQLException {
           if (sortcombo.getValue().equals("Effectif")) {
            
            service.ClubService SA = new ClubService();
            ArrayList<club> a = (ArrayList<club>) SA.getTrier(); 
            ObservableList<club> obs = FXCollections.observableArrayList(a);   
            tableView.setItems(obs);

        }else if (sortcombo.getValue().equals("Nom")) {
            
            service.ClubService SA = new ClubService();
            ArrayList<club> a = (ArrayList<club>) SA.getTrierParNom(); 
            ObservableList<club> obs = FXCollections.observableArrayList(a);
            tableView.setItems(obs);
}        
    }

    @FXML
    private void AttribuerEvent(ActionEvent event) throws IOException {
         Parent tableView = FXMLLoader.load(getClass().getResource("AjoutEvent.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); 
    }

    @FXML
    private void AjoutClub(ActionEvent event) throws IOException { 
          Parent tableView = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); 
    }

    @FXML
    private void listinscriptions(ActionEvent event) throws IOException {
          
         Parent tableView = FXMLLoader.load(getClass().getResource("afficheinscriptions.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); 
    }

    @FXML
    private void update_Name() {
    }

    @FXML
    private void update_Description() {
    }

    @FXML
    private void update_Effectif(TableColumn.CellEditEvent bb) throws SQLException {
         club productselected = tableView.getSelectionModel().getSelectedItem();
     productselected.setNom_club(bb.getNewValue().toString());
  Alert alert=new Alert(AlertType.CONFIRMATION);
             alert.setTitle("confirmation update");
             alert.setHeaderText("this confirmation about update");
             alert.setContentText("are you sure to update??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
             SCL.updateClub(productselected);
             }else{System.out.println("Cancel");}
    }

    @FXML
    private void update_Domaine(TableColumn.CellEditEvent bb) throws SQLException {
        club productselected = tableView.getSelectionModel().getSelectedItem();
     productselected.setDomaine(bb.getNewValue().toString());
  Alert alert=new Alert(AlertType.CONFIRMATION);
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
    private void delete(ActionEvent event) {
         tableView.setItems(list);

             ObservableList<club> allcommand,SingleDemandes ;
             allcommand=tableView.getItems();
             SingleDemandes=tableView.getSelectionModel().getSelectedItems();
             club c = SingleDemandes.get(0);
              ClubService SCL= new ClubService();
     
              Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("confirmation delete");
             alert.setHeaderText("this confirmation about delet");
             alert.setContentText("are you sure to delete??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
                 SCL.DeleteClub(c.getId());
             SingleDemandes.forEach(allcommand::remove);
             
             }else{System.out.println("Cancel");}
    }

    @FXML
    private void AjouterCLUB(MouseEvent event) {
    }

    @FXML
    private void AffecterEleve(ActionEvent event) throws IOException {
        Parent tableView = FXMLLoader.load(getClass().getResource("afficeParticipations.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); 
    }

    @FXML
    private void INSCRIPTIONS(ActionEvent event) throws IOException {
         Parent tableView = FXMLLoader.load(getClass().getResource("afficheinscriptions.fxml"));
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
              Stage first = (Stage)clu.getScene().getWindow();
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
}
