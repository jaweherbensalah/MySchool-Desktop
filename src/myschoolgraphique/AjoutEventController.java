/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myschoolgraphique;

import entities.club;
import entities.event;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pidevfinal.Directeur_dashboardController;
import pidevfinal.PidevFinal;
import service.ClubService;
import service.EventService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjoutEventController implements Initializable {
    ObservableList<String> liste_profil = FXCollections.observableArrayList();

    @FXML
    private AnchorPane HeureEvent;
    @FXML
    private Label msg;
    @FXML
    private ImageView even;
    @FXML
    private ImageView insc;
    @FXML
    private ImageView parti;
    @FXML
    private Button listEvent;
    @FXML
    private Button add;
    @FXML
    private TextField nom;
    @FXML
    private Button importE;
    @FXML
    private ChoiceBox<club> clubs;
    @FXML
    private TextField image;
    @FXML
    private TextField heureEvent;
    @FXML
    private DatePicker DateEvent;
    @FXML
    private TextField description;
    @FXML
    private TextField place;
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
    private ImageView ms;
    @FXML
    private ImageView me;
    @FXML
    private Label user;
private FileChooser fileChooser;
    private File posterFileInput;
      ObservableList<club> club;

    /**
     * Initializes the controller class.
     */
      
     public void  listclub()    {
       ClubService  clService = new ClubService();
        EventService  clService1 = new EventService();
     List<club> c = clService.afficheClubs(); 
         for(int i=0;i< c.size();i++) 
         {
      club= FXCollections.observableArrayList(c); 
         } 
   
       clubs.setItems(club);
     }
      
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      //  user.setText(pidevfinal.ConnecterController.n);
         listclub();
       
         importE.setOnMouseClicked((MouseEvent e)->{
            
        final FileChooser fileChooser = new FileChooser();
        final Stage stage = null;

        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
           image.setText(file.toURI().toString());
        }
        
        });
        
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
     initProfil();
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
          Parent tableView = FXMLLoader.load(getClass().getResource("afficheinscriptions.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); 
    }

    @FXML
    private void AffecterEleve(ActionEvent event) {
    }

    @FXML
    private void listEvent(ActionEvent event) throws IOException {
         Parent tableView = FXMLLoader.load(getClass().getResource("afficheEventAdmin.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); 
    }

    @FXML
    private void ajoutEvent(ActionEvent event) {
               
            EventService ser =new EventService();
             club cl=new club();
    
        event even = new event();
        even.setNom_event(nom.getText());
        even.setDescription(description.getText());
        even.setPlaceDispo(Integer.parseInt(place.getText()));
        even.setDateEvent(java.sql.Date.valueOf(DateEvent.getValue().toString()));
        //DatePicker Date_Commandd=(DatePicker)DateEvent;
        //String date=(String)Date_Commandd.getValue().toString();
        //date=date.substring(0,4)+'/'+date.substring(5,7)+'/'+date.substring(8);
        //java.util.Date myDate=new java.util.Date(date);
        //java.sql.Date sqldate=new java.sql.Date(myDate.getTime());
        even.setHeureEvent(heureEvent.getText());
        //even.setImage(StorageService.getInstance().upload(posterFileInput)); 
        
        even.setImage(image.getText());
           // even.setClub_id(Integer.parseInt(club_id.getText()));
           String x = clubs.getSelectionModel().getSelectedItem().getId(); 
           int i = Integer.parseInt(x); 
           even.setClub_id(i);
        //String catVal = (String) category.getValue();
       // activity.setType_ac(catVal);
        //ServiceActivity.getInstance().insert(activity);
        
        //String club_id = (String) club.getValue();
        //even. setClub_id(club_id);
        //ComboBox club1 = (ComboBox) club;

         // String club_id = (String) club1.getValue();
          if (DateEvent.getValue().isAfter(LocalDate.now())){
          //System.out.println(i);
        ser.ajoutEvent(even);
        /* try {
                        Notification.sendNotification("add succeed", TrayIcon.MessageType.ERROR);
                    } catch (AWTException ex) {
                        Logger.getLogger(AjoutEventController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(AjoutEventController.class.getName()).log(Level.SEVERE, null, ex);
                    }*/
        
        
        
        
             /*   Notifications notificationBuilder = Notifications.create().title("hahahah traitée").hideAfter(Duration.seconds(3)).position(Pos.BOTTOM_RIGHT).onAction(new EventHandler<ActionEvent>()
       {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("clicked");
            }
       
       
       });*/
               
               
     /*          
       notificationBuilder.darkStyle();
       notificationBuilder.show();     
                    */
          


       // browser.close();
        // engine.close();
        
       // FXMLLoader AddacLoader = new FXMLLoader(getClass().getResource("Addac.fxml"));
       //Parent AddacView = AddacLoader.load();
        //((AnchorPane) t1.getParent()).getChildren().setAll(AddacView);
        
   System.out.println("ok"); 
    }
          else{
                         Alert alert = new Alert(Alert.AlertType.ERROR);

                    alert.setTitle("Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Date non valide!");
                    alert.show();
                        }
    }
    

    @FXML
    private void onClickPosterPicker(ActionEvent event) {
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
              Stage first = (Stage)nom.getScene().getWindow();
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
              Stage first = (Stage)nom.getScene().getWindow();
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
              Stage first = (Stage)nom.getScene().getWindow();
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
              Stage first = (Stage)nom.getScene().getWindow();
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
