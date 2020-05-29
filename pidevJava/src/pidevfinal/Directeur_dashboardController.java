/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfinal;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import service.AbsenceService;
import service.EleveService;
import service.EnseignantsService;
import service.EventService;
import service.PunitionService;
/**
 * FXML Controller class
 *
 * @author Asus
 */
public class Directeur_dashboardController implements Initializable {

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
    private ImageView punT;
    @FXML
    private Label msg_pun;
    @FXML
    private ImageView eleT;
    @FXML
    private Label msg_ele;
    @FXML
    private ImageView ensT;
    @FXML
    private Label msg_ense;
    @FXML
    private ImageView absT;
    @FXML
    private Label msg_abs;
    @FXML
    private ImageView evT;
    @FXML
    private Label msg_event;
    @FXML
    private ImageView im;

    
      public static    Image  img1 = new Image("/images/maison-png-8.png"); 
    public static  Image    img2 = new Image("/images/high-school.png"); 
      public static Image   img3 = new Image("/images/90477.png"); 
       public static   Image  img4 = new Image("/images/results-icon-png-12.png"); 
        public static  Image img5 = new Image("/images/e06899eb675d084004067ef882f706d3.png"); 
        public static  Image  img6 = new Image("/images/secured-letter.png"); 
     public static  Image img7 = new Image("/images/datum-png-2.png");   
       public static  Image img8 = new Image("/images/3tvJykCGap5Qed228mt1KXL1.png"); 
       public static Image img9= new Image("/images/5a5a6d3714d8c4188e0b0890.png");
       public static Image img10 = new Image("/images/14146.png");
       public static Image img11 = new Image("/images/50042.png");
       public static  Image img12 = new Image("/images/50001.png");
       public static  Image img13 = new Image("/images/icone-supprimer-png.png");
     public static Image  img14 = new Image("/images/mommy-and-me-classes.png"); 
    public static Image    img15 = new Image("/images/309-3099328_study-flat-icon.png"); 
   public static  Image   img16 = new Image("/images/272.png"); 
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         EleveService elservice = new EleveService(); 
        int a = elservice.calculerEleve(); 
         String  x = Integer.toString(a);
         msg_ele.setText(x); 
         
         EnseignantsService enservice = new EnseignantsService(); 
        int b = enservice.calculerEnseignants(); 
         String  y = Integer.toString(b);
         msg_ense.setText(y); 
         
          AbsenceService abservice = new AbsenceService(); 
        int c = abservice.calculerAbsence(); 
         String  z = Integer.toString(c);
         msg_abs.setText(z); 
         
          PunitionService puservice = new PunitionService(); 
        int d = puservice.calculerPunition(); 
         String  w = Integer.toString(d);
         msg_pun.setText(w); 
         
       EventService par = new EventService();
        int g = par.calculerEvents();
        String gg = Integer.toString(g);
       msg_event.setText(gg);
       
       
       
       
       
         // afficher les images 
    img1 = new Image("/images/maison-png-8.png"); 
        dash.setImage(img1);
        img2 = new Image("/images/high-school.png"); 
        ecole.setImage(img2);
        img3 = new Image("/images/90477.png"); 
        ens.setImage(img3);
        //img4 = new Image("/images/results-icon-png-12\n"); 
        res.setImage(img4);
     img5 = new Image("/images/e06899eb675d084004067ef882f706d3.png"); 
        trans.setImage(img5);
        img6 = new Image("/images/secured-letter.png"); 
        mes.setImage(img6);
         img7 = new Image("/images/datum-png-2.png"); 
        ev.setImage(img7);
        img8 = new Image("/images/3tvJykCGap5Qed228mt1KXL1.png"); 
        me.setImage(img8);
         Image img9 = new Image("/images/unnamed (1).png");
         absT.setImage(img9);
          Image img10 = new Image("/images/delete_delete_deleteusers_delete_male_user_maleclient_2348.png");
         punT.setImage(img10);
  Image img11 = new Image("/images/59495.png");
  eleT.setImage(img11);
    Image img12 = new Image("/images/Teacher-male.png");
    ensT.setImage(img12);
      evT.setImage(img7);
    }    

    @FXML
    private void dashboardButtonAction(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("DirecteurAdmin.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)msg_abs.getScene().getWindow();
              first.close();
    }

    @FXML
    private void enseignantButtonAction(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("admin_enseignant.fxml"));
  rootPane.getChildren().setAll(pane); 
    }

    @FXML
    private void RésultatsButtonAction(ActionEvent event) {
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
              Stage first = (Stage)absT.getScene().getWindow();
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
              Stage first = (Stage)msg_abs.getScene().getWindow();
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
              Stage first = (Stage)me.getScene().getWindow();
              first.close(); 
    }
    
}
