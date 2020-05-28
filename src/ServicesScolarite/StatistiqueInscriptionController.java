/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesScolarite;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pidevfinal.Directeur_dashboardController;
import pidevfinal.PidevFinal;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class StatistiqueInscriptionController implements Initializable {

    @FXML
    private Pane paneView;
    @FXML
    private PieChart pieChart;
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
    private ComboBox<String> deconnexion;
    @FXML
    private ImageView ne;
    @FXML
    private Button inscription;
    @FXML
    private ImageView etu;
    @FXML
    private Button affecterEe;
    @FXML
    private ImageView abs;
    @FXML
    private Button AjouterAbs;
    @FXML
    private ImageView st;
Connection cnx; 
    Statement ste ; 
    ObservableList<PieChart.Data> piechartdata; 
    ArrayList <String> p = new ArrayList<> (); 
    ArrayList<Integer> n = new ArrayList<> (); 
        ObservableList<String> liste_profil = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         loadData();
       pieChart.setData(piechartdata); 
    }    
    public void loadData() {
        String req = "Select orders.customer_id , COUNT(customer.customer_id) as nb   from orders,    customer group by  orders.customer_id "; 
        
        piechartdata = FXCollections.observableArrayList(); 
        
        cnx = DataSource.getInstance().getCnx(); 
        
        try { 
            ResultSet rs = cnx.createStatement().executeQuery(req);
            while(rs.next()) {
                piechartdata.add(new PieChart.Data(rs.getInt("customer_id")+"("+Integer.toString(rs.getInt("nb"))+")",
                        rs.getInt("nb")));
                String nombre = Integer.toString(rs.getInt("nb")); 
                p.add(rs.getInt("customer_id")+nombre); 
                n.add(rs.getInt("nb")); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(StatistiqueInscriptionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        initProfil();
        
          dash.setImage(Directeur_dashboardController.img1);
        ecole.setImage(Directeur_dashboardController.img2);
        ens.setImage(Directeur_dashboardController.img3);
        res.setImage(Directeur_dashboardController.img4);
        trans.setImage(Directeur_dashboardController.img5);
        ms.setImage(Directeur_dashboardController.img6);
        ev.setImage(Directeur_dashboardController.img7);
        me.setImage(Directeur_dashboardController.img8);
         ne.setImage(Directeur_dashboardController.img9);
          etu.setImage(Directeur_dashboardController.img10);
        abs.setImage(Directeur_dashboardController.img11);
        st.setImage(Directeur_dashboardController.img16);    
    }    

    @FXML
    private void dashboardButtonAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/Directeur_dashboard.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)abs.getScene().getWindow();
              first.close();
    }

    @FXML
    private void enseignantButtonAction(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/admin_enseignant.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)abs.getScene().getWindow();
              first.close();
    }

    @FXML
    private void RésultatsButtonAction(ActionEvent event) { FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/admin_enseignant.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)abs.getScene().getWindow();
              first.close();
    }

    @FXML
    private void transportButtonAction(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/admin_enseignant.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)abs.getScene().getWindow();
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
              Stage first = (Stage)abs.getScene().getWindow();
              first.close();
    }

    @FXML
    private void menuButtonAction(ActionEvent event) { FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/views/MenuTableView.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)abs.getScene().getWindow();
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
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/connecter.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)abs.getScene().getWindow();
              first.close();
        }
    }

    @FXML
    private void inscriptionButttonAction(ActionEvent event) {
    }

    @FXML
    private void AffecterEleveAction(ActionEvent event) {
    }

    @FXML
    private void AjouterAbsenceAction(ActionEvent event) {
    }

    @FXML
    private void afficherstat(ActionEvent event) throws IOException {
          SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "/RegisterValidation/statistiqueInscriptions.fxml", "Statistique Elève ");
    }
    private void initProfil() {
        String p = pidevfinal.ConnecterController.n;
        String v = "deconnexion";
        String o = "profil";
        liste_profil.addAll(p, o, v);
        deconnexion.setItems(liste_profil);
    }
}
