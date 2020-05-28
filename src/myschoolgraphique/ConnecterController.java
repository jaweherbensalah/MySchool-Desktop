/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myschoolgraphique;

import Entites.Utilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.sound.midi.SysexMessage;
import service.UtilisateurService;




/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ConnecterController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button connecter;
    @FXML
    private ImageView admin;
    @FXML
    private ImageView logcon;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Image img11 = new Image("/images/logo.png");
          logcon.setImage(img11);
          Image img12 = new Image("/images/ad.png");
          admin.setImage(img12);
    }    

    @FXML
    private void seconnecterAction(ActionEvent event) throws IOException {
              Utilisateur u ; 
        Utilisateur x; 
       // EleveService el = new EleveService(); 
        //EnseignantsService ens = new EnseignantsService();
        UtilisateurService  uti = new UtilisateurService(); 
        String n = nom.getText(); 
        String m = mdp.getText(); 
         u = new Utilisateur(n); 
        x = uti.findBynom(u); 
        if (x != null)
        {
         if(x.getRole().equalsIgnoreCase("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) {
         System.out.println("il s'agit de role admin ");
        
            Parent tableView = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); 
    }
        }
        
    }
}
