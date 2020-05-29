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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pidevfinal.Directeur_dashboardController;
import pidevfinal.PidevFinal;
import service.ClubService;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLController implements Initializable {
    ObservableList<String> liste_profil = FXCollections.observableArrayList();

    @FXML
    private Label msg;
    @FXML
    private ImageView clu;
    @FXML
    private ImageView even;
    @FXML
    private ImageView insc;
    @FXML
    private ImageView parti;
    @FXML
    private Button list;
    @FXML
    private Button button;
    @FXML
    private TextField nom;
    @FXML
    private TextField description;
    @FXML
    private TextField effectif;
    @FXML
    private ComboBox<String> domiane1;
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
    @FXML
    private ComboBox<String> deconnexion;
service.ClubService SC= new ClubService();
      private Connection connexion;
    private Statement Ste;
    private PreparedStatement pst;
    private Statement ste;
    
    private boolean Validchamp(TextField T){
        return !T.getText().isEmpty() && T.getLength() > 3;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ObservableList<String> options = FXCollections.observableArrayList(
                "sportifs","sportifs","entrepreneuriat","social",
                "Culturel"  ,"tics") ;
        domiane1.getItems().addAll(options);
       
        initProfil();
        
        // afficher les images 
         Image img1 = new Image("/images/ajoutEvent.png");
          even.setImage(img1);
         //Image img2 = new Image("/images/logo.png");
          //.setImage(img2);
         Image img3 = new Image("/images/ajoutClub.png");
          clu.setImage(img3);
          Image img4 = new Image("/images/inscription.png");
          insc.setImage(img4);
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
    private void AffecterEleve(ActionEvent event) throws IOException {
         Parent tableView = FXMLLoader.load(getClass().getResource("afficeParticipations.fxml"));
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
              Parent tableView = FXMLLoader.load(getClass().getResource("/myschoolgraphique/afficheinscriptions.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); 
        
    }

    @FXML
    private void listClub(ActionEvent event) throws IOException {
        
  Parent tableView = FXMLLoader.load(getClass().getResource("afficheClub.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show();
    }

    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException, SQLException { 
              
         connexion =   DataSource.getInstance().getCnx();
          Ste=connexion.createStatement();

            ClubService ser =new ClubService();
             club cl=new club();
         
             String vnom = nom.getText ();
             String vdescription = description.getText ();
             String veffectif= effectif.getText ();
             String vdomaine= (String) domiane1.getValue();
      
            //String vdomaine = domiane1.getSelectionModel().getSelectedItem(); 
         //  int i = Integer.parseInt(x); 
           //even.setClub_id(i);
           
               
     
                
                cl.setNom_club(vnom);
                cl.setDescription(vdescription);
                cl.setEffectif(Integer.parseInt(veffectif));
                cl.setDomaine (vdomaine);
             
             
            
            ser.ajoutClub2(new club(nom.getText(),description.getText(),Integer.parseInt(effectif.getText()),(String) domiane1.getValue()));
          
          System.out.println("ok");
                       //SendMail.sendMail("khaoula.soury@esprit.tn", "product", "new product");
 Parent tableView = FXMLLoader.load(getClass().getResource("afficheClub.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); 
                       
                       
                   
     
       /* connexion =   DataSource.getInstance().getCnx();
            ste = connexion.createStatement();
              if(Validchamp(nom)&&Validchamp(description)&&Validchamp(effectif)&&Validchamp(domaine)){   
                  try{
                  int effectif= (int) Integer.valueOf(this.nom.getText());
                  ClubService ser =new ClubService();
                  ser.ajoutClub2(new club(nom.getText(), description.getText(),effectif,domaine.getText()));
                  }
              }
            }
                 catch (SQLException ex) {
                    System.out.println(ex);
                 }
              else{
                 System.out.println("Verifier vos données");
              }
                  
                    
}*/
        
        
        
        
       
        /*connexion =   DataSource.getInstance().getCnx();
            ste = connexion.createStatement(); 
        
        ClubService c1=new ClubService();
        String no = nom.getText() ;
        String desc =description.getText();
        String doma=domaine.getText();
       String eff=effectif.getText();
        
        club c =new club();
        
        
        c.setNom_club(nom.getText());
          c.setDescription(description.getText());
            c.setDomaine(domaine.getText());
             //c.setEffectif(int.parseInt(effectif.getText()));
              c.setEffectif(Integer.parseInt(eff));
              
              c1.ajoutClub2(c);*/
              
        

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
              Stage first = (Stage)nom.getScene().getWindow();
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