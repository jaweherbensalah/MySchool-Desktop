/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myschoolgraphique;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pidevfinal.Directeur_dashboardController;
import pidevfinal.PidevFinal;
import service.EventService;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class SuiviEventController implements Initializable {

    @FXML
    private Label msg;
    @FXML
    private LineChart<?, ?> LineChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
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
    private ImageView ms;
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
    private ComboBox<String> deconnexion;
         ObservableList<String> liste_profil = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
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
        
          EventService apr = new EventService();
       //*****************LineChaart************************
        EventService es =new EventService();
     
        es.calculernbreEventparmois();
        
       
          XYChart.Series series = new XYChart.Series(); 
       

        series.setName("Nombre Total de  evenement par mois");
        series.getData()
                .add(new XYChart.Data("Janvier", EventService.totaleJ));
        series.getData()
                .add(new XYChart.Data("Fevrier", EventService.totaleF));
        series.getData()
                .add(new XYChart.Data("Mars", EventService.totaleM));
        series.getData()
                .add(new XYChart.Data("Avril", EventService.totaleA));
        series.getData()
                .add(new XYChart.Data("Mai", EventService.totaleMai));
        series.getData()
                .add(new XYChart.Data("Juin", EventService.totaleJuin));
        series.getData()
                .add(new XYChart.Data("Juillet", EventService.totaleJuillet));
        series.getData()
                .add(new XYChart.Data("Aout", EventService.totaleAout));
         series.getData()
                .add(new XYChart.Data("Septembre", EventService.totaleSeptembre));
          series.getData()
                .add(new XYChart.Data("Octobre", EventService.totaleOctobre));
           series.getData()
                .add(new XYChart.Data("Novembre", EventService.totaleNovembre));
            series.getData()
                .add(new XYChart.Data("Décembre", EventService.totaleDécembre));
        
        LineChart.getData() .add(series);
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
              Stage first = (Stage)ev.getScene().getWindow();
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
              Stage first = (Stage)ev.getScene().getWindow();
              first.close();
    }

    @FXML
    private void AffecterEleve(ActionEvent event) {
    }

    @FXML
    private void INSCRIPTIONS(ActionEvent event) {
    }

    @FXML
    private void AjouterEVENT(ActionEvent event) {
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
}
