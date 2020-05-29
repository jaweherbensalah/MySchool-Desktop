/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentServices;

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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pidevfinal.ConnecterController;
import pidevfinal.PidevFinal;
import service.AbsenceService;
import service.PunitionService;
import views.SceneChanger;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Button Accueil;
    @FXML
    private Button emplois;
    @FXML
    private Button absence;
    @FXML
    private Button punition;
    @FXML
    private Button resultat;
    @FXML
    private Button reclamation;
    @FXML
    private Button transport;
    @FXML
    private Button restuarant;
    @FXML
    private Label pun;
    @FXML
    private Label abs;
    @FXML
    private ImageView logo;
    @FXML
    private Label user;
    @FXML
    private Button deconnexion;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      Image img1 = new Image("/images/logo.png");
        logo.setImage(img1);
        user.setText(pidevfinal.ConnecterController.n);
        String nom = pidevfinal.ConnecterController.n; 
          AbsenceService abservice = new AbsenceService(); 
        int c = abservice.calculerNomAbsence(nom); 
         String  z = Integer.toString(c);
         abs.setText(z);
        PunitionService pu = new PunitionService(); 
         int w = pu.calculerNomPunition(nom); 
      String  p = Integer.toString(w);
         pun.setText(p);
    }    

    @FXML
    private void signupScreen(ActionEvent event) throws IOException {
        Stage primaryStage =new Stage();
                primaryStage.initStyle(StageStyle.UNDECORATED);
		Parent root =FXMLLoader.load(getClass().getResource("/StudentServices/Signup.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void loginScreen(ActionEvent event) throws IOException {
        Stage primaryStage =new Stage();
                primaryStage.initStyle(StageStyle.UNDECORATED);
		Parent root =FXMLLoader.load(getClass().getResource("login.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    private void AccueilAction(ActionEvent event) throws IOException {
       Parent root = FXMLLoader.load(getClass().getResource("/pidevfinal/Eleve_Accueil.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
        Stage first = (Stage)abs.getScene().getWindow();
              first.close();
        //AnchorPane pane = FXMLLoader.load(getClass().getResource("AffecterEnseignant.fxml"));
        //rootPane.getChildren().setAll(pane);   
    }

    @FXML
    private void AfficherEmploisAction(ActionEvent event) {
    }

    @FXML
    private void afficherAbsenceAction(ActionEvent event) throws IOException {
       
             Parent root = FXMLLoader.load(getClass().getResource("/pidevfinal/Absence_Eleve.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
	 Stage first = (Stage)abs.getScene().getWindow();
              first.close();	
		
      
    }

    @FXML
    private void afficherPunitionAction(ActionEvent event) throws IOException {
      
Parent root =FXMLLoader.load(getClass().getResource("/pidevfinal/Punition_Eleve.fxml"));
       Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
         Stage first = (Stage)abs.getScene().getWindow();
              first.close();
    }

    @FXML
    private void afficherRseultatAction(ActionEvent event) {
    }

    @FXML
    private void afficherReclamationAction(ActionEvent event) {
    }

    @FXML
    private void afficherTransportAction(ActionEvent event) throws IOException {
        Parent root =FXMLLoader.load(getClass().getResource("/StudentServices/FXMLDocument.fxml"));

        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
         Stage first = (Stage)abs.getScene().getWindow();
              first.close();
        //check if it is an admin user and go to the table view
        //if (SceneChanger.getLoggedInUser().isAdmin())
           
    }

    @FXML
    private void afficherRestaurantAction(ActionEvent event) throws IOException {
       Parent root =FXMLLoader.load(getClass().getResource("/StudentServices/FXMLDocument1.fxml"));
 Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
         Stage first = (Stage)abs.getScene().getWindow();
              first.close();
    }

    @FXML
    private void DeconnexionAction(ActionEvent event) throws IOException {
        Parent root =FXMLLoader.load(getClass().getResource("/pidevfinal/connecter.fxml"));
 Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
         Stage first = (Stage)abs.getScene().getWindow();
              first.close();
    }
    
}
