/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myschoolgraphique;

import entities.inscription_club;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import service.inscriptionService;
import util.DataSource;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.util.converter.IntegerStringConverter;
import pidevfinal.Directeur_dashboardController;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;
import pidevfinal.PidevFinal;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AfficheinscriptionsController implements Initializable {

    @FXML
    private Label msg;
    @FXML
    private TextField search;
    @FXML
    private Button accepter;
    @FXML
    private Button refuser;
    @FXML
    private TableView<inscription_club> tableView;
    @FXML
    private TableColumn<inscription_club, Integer> id;
    @FXML
    private TableColumn<inscription_club, String> iduser;
    @FXML
    private TableColumn<inscription_club,String> idclub;
    @FXML
    private TableColumn<inscription_club,String> stat;
    @FXML
    private Button export;
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
    private ImageView clu;
    @FXML
    private ImageView parti;
    @FXML
    private ImageView insc;
    @FXML
    private ImageView even;
    @FXML
    private ImageView ms;
    @FXML
    private ComboBox<String> deconnexion;
         ObservableList<String> liste_profil = FXCollections.observableArrayList();
 ObservableList<inscription_club> list  = FXCollections.observableArrayList();
     service.inscriptionService SCL= new inscriptionService();
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
        for(inscription_club cl: SCL.afficheInscriptions())
             list.add(cl);
      } catch (SQLException ex) {
     }
       id.setCellValueFactory(new PropertyValueFactory<>("id"));
         iduser.setCellValueFactory(new PropertyValueFactory<>("iduser"));
         idclub.setCellValueFactory(new PropertyValueFactory<>("idclub"));
         stat.setCellValueFactory(new PropertyValueFactory<>("stat"));
        
       
        tableView.setItems(list);
     tableView.setEditable(true);
     
      id.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
          iduser.setCellFactory(TextFieldTableCell.forTableColumn());
             idclub.setCellFactory(TextFieldTableCell.forTableColumn());
      stat.setCellFactory(TextFieldTableCell.forTableColumn());
    
   
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initProfil();
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
        
          Afficher();
            export.setOnAction(event ->{pdf();});
    }    
 void pdf() {
 System.out.println("To Printer!");
         PrinterJob job = PrinterJob.createPrinterJob();
           if(job != null){
    Window primaryStage = null;
           job.showPrintDialog(primaryStage); 
            
    Node root = this.tableView ;
   
           job.printPage(root);
     
           job.endJob();
            
       

  }}    
    @FXML
    private void RechercheAV(ActionEvent event) {
    }

    @FXML
    private void accepter(ActionEvent event) throws SQLException {
        tableView.setItems(list);

             ObservableList<inscription_club> allcommand,SingleDemandes ;
             allcommand=tableView.getItems();
             SingleDemandes=tableView.getSelectionModel().getSelectedItems();
             inscription_club c = SingleDemandes.get(0);
              inscriptionService SCL= new inscriptionService();
     
              Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("confirmation  Acceptation");
             alert.setHeaderText("cette confirmation a props acceptation inscription");
             alert.setContentText("sure d'accepter??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
                 SCL.AccepterInscriptionClub(c.getId());
              // SingleDemandes.forEach(allcommand::add);
              
             TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle("Inscription Club Comfirmé ");
                tray.setMessage("Notre club vous informe que votre demande d'inscription est accepté  avec succés");
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
                        //    playSomeSound("add_notification");
             
             }else{System.out.println("Cancel");}
           
    }

    @FXML
    private void refuser(ActionEvent event) throws SQLException {
        tableView.setItems(list);

             ObservableList<inscription_club> allcommand,SingleDemandes ;
             allcommand=tableView.getItems();
             SingleDemandes =tableView.getSelectionModel().getSelectedItems();
             inscription_club c = SingleDemandes.get(0);
              inscriptionService SCL= new inscriptionService();
     
              Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("confirmation  Acceptation");
             alert.setHeaderText("cette confirmation a props acceptation inscription");
             alert.setContentText("sure d'accepter??");
             Optional<ButtonType> result=alert.showAndWait();
             if(result.get()==ButtonType.OK){
                 
                 SCL.RefuserInscriptionClub(c.getId());
                 
                 
                 
              //SingleDemandes.forEach(allcommand::updateStat());
             // Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               // stage.hide();
                
            SCL.updateStat(c);
             
             }else{System.out.println("Cancel");}
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
        //String css = pidevfinal.class.getResource("styleAjout.css").toExternalForm();
       // scene.getStylesheets().add(css);
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

    @FXML
    private void AffecterClub(ActionEvent event) throws IOException {
         Parent tableView = FXMLLoader.load(getClass().getResource("afficheEventAdmin.fxml"));
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
    private void AjouterCLUB(MouseEvent event) {
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
      private void initProfil() {
        String p = pidevfinal.ConnecterController.n;
        String v = "deconnexion";
        String o = "profil";
        liste_profil.addAll(p, o, v);
        deconnexion.setItems(liste_profil);
    }

    @FXML
    private void AffecterPart(ActionEvent event) {
    }
}
