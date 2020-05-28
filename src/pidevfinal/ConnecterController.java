/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfinal;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ServicesScolarite.SceneChanger;
import Entites.Utilisateur;
import StudentServices.LoginController;
import StudentServices.LoginModel;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import service.EleveService;
import service.EnseignantsService;
import service.UtilisateurService;
import ServicesScolarite.SceneChanger;
/**
 * FXML Controller class
 *
 * @author USER
 */
public class ConnecterController implements Initializable {

      public LoginModel loginModel=new LoginModel();
    public static int cust_id;
    
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ImageView user;
    @FXML
    private ImageView logo;
    @FXML
    private TextField nom;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button connecter;
 
    public static String n; 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Image img1 = new Image("/images/logo.png");
        logo.setImage(img1);
        Image img2 = new Image("/images/ad.png");
        user.setImage(img2);  
    }    

    @FXML
    private void seconnecterAction(ActionEvent event) {
       Utilisateur u ; 
        Utilisateur x; 
        EleveService el = new EleveService(); 
        EnseignantsService ens = new EnseignantsService();
        UtilisateurService  uti = new UtilisateurService(); 
        n = nom.getText(); 
        String m = mdp.getText(); 
         u = new Utilisateur(n); 
        x = uti.findBynom(u); 
        if (x != null)
        {
         if((x.getRole().equalsIgnoreCase("a:1:{i:0;s:10:\"ROLE_ADMIN\";}")) ) {
         System.out.println("il s'agit de role admin ");
           // System.out.println("adminn");
      
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
    } else if(x.getRole().equalsIgnoreCase("a:1:{i:0;s:10:\"ROLE_ELEVE\";}")) {
              FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("Eleve_Accueil.fxml"));
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
    else if(x.getRole().equalsIgnoreCase("a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}")) {
   
  FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/EnseignantAccueil.fxml"));
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
    }
//****************************************************************************
    
      public static void CustomerId(int cusst){
        cust_id=LoginModel.customer_id;
        
    }
    public void Login(ActionEvent event) throws SQLException{
     try {
            if(loginModel.isLogin(nom.getText(), mdp.getText())){
                infoBox("Bienvenues aux services de la scolarité ! "+cust_id,null,"Success" );
                
                Node node = (Node)event.getSource();
                Stage primaryStage =new Stage();
               Stage dialogStage = (Stage) node.getScene().getWindow();
               dialogStage.close();
               Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/pidevfinal/Eleve_Accueil.fxml")));
		primaryStage.setScene(scene);
                
               dialogStage.setScene(scene);
               dialogStage.show();
       }else{
                infoBox("Entrer correctement l'email et le mot de passe",null,"Failed" );
            }
        } catch (IOException ex) {
        }
        
    }
     public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }
}
