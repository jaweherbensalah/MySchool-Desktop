/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentServices;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import ServicesScolarite.SceneChanger;


public class SignupController implements Initializable {
   public SignupModel signupModel= new SignupModel();
    @FXML
    private JFXTextField textNP;

    @FXML
    private JFXTextField textNT;

    @FXML
    private JFXTextField textE;

    @FXML
    private JFXPasswordField textP;

    @FXML
    private JFXTextField textA;

    @FXML
    private JFXTextField textC;
    
    @FXML
    private Label alertLabel;

    @FXML
    private TextField usernameT;

    @FXML
    private TextField email_canonicalT;

    @FXML
    private TextField saltT;

    @FXML
    private TextField username_canonicalT;

    @FXML
    private TextField confirmation_tokenT;

    @FXML
    private DatePicker password_requested_atT;

    @FXML
    private TextField enabledT;

    @FXML
    private DatePicker last_loginT;

    @FXML
    private TextField matiereT;

    @FXML
    private TextField classe2T;

    @FXML
    private TextField rolesT;

    @FXML
    private DatePicker date_naissanceT;

    @FXML
    private TextField sexeT;

    @FXML
    private TextField descriptionT;

    @FXML
    private TextField url_photo_profilT;

@FXML private DatePicker date_inscriptionT;

 

    @FXML
    private TextField statusT;
    @FXML
    private JFXTextField textpin;
    @FXML
    private JFXPasswordField textpassconf;
    @FXML
    private int pin;
   
    
    @FXML public void cancelButtonPushed(ActionEvent event) throws IOException
    {
        SceneChanger sc = new SceneChanger();
        
           sc.changeScenes(event, "/views/EleveInterface.fxml", "All Menues");
            // sc.changeScenes(event,"FXMLDocument.fxml", "All Menues");
    }
    
   
    public static boolean isValid(String iemail) 
    { 
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+ 
                            "[a-zA-Z0-9_+&*-]+)*@" + 
                            "(?:[a-zA-Z0-9-]+\\.)+[a-z" + 
                            "A-Z]{2,7}$"; 
                              
        Pattern pat = Pattern.compile(emailRegex); 
        if (iemail == null) 
            return false; 
        return pat.matcher(iemail).matches(); 
    } 
   
     @FXML
     public void loginScreen(ActionEvent event) throws Exception  {
		Stage primaryStage =new Stage();
                primaryStage.initStyle(StageStyle.UNDECORATED);
		Parent root =FXMLLoader.load(getClass().getResource("ListServices.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
	}

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(signupModel.isDbConnected()){
            System.out.println("connecté");
        }else{
            System.out.println("non connecté");
        }
    }  
    @FXML
    public void Signup(ActionEvent event){
       
        String nom_prenom=textNP.getText();
        String password=textP.getText();
        String  email=textE.getText().toLowerCase();
        String numero_tel=String.valueOf(textNT.getText());
        String adresse=textA.getText();
        String classe=textC.getText().toLowerCase();
         String username=usernameT.getText(); 
         String username_canonical=this.username_canonicalT.getText();
         String email_canonical=email_canonicalT.getText();
         String enabled=enabledT.getText();
         String salt=saltT.getText().toLowerCase();;
    
         LocalDate last_login =last_loginT.getValue(); 
         String  confirmation_token =confirmation_tokenT.getText();
         LocalDate password_requested_at=password_requested_atT.getValue();
         String roles=rolesT.getText();
         LocalDate date_naissance=date_naissanceT.getValue();
        String sexe="";
         String description=null;
         String   url_photo_profil=null;
         LocalDate date_inscription =date_inscriptionT.getValue();
         String status=null; 
         String matiere=null;
         String  classe2=null;
 
       try {
           if((nom_prenom.isEmpty()|| password.isEmpty() || email.isEmpty()
             || numero_tel.isEmpty() || adresse.isEmpty() 
            || classe.isEmpty()  )){
              alertLabel.setText("Remplir les champs vides");
             
           } else if(!((nom_prenom).matches("[a-zA-Z]+"))){
               alertLabel.setText("Le format du prénom est invalide ");
              
           }else if(!(isValid(email))){
               alertLabel.setText("Le format d'email est invalide ");
           }else if(!(password.length()>=4)){
               alertLabel.setText("Le mot de passe doit avoir plus que 4 caractères !");
           }else if(!(numero_tel.matches("[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]"))){
               alertLabel.setText("Le format du mobile est invalide ");
           
           }else if(!((adresse).matches("[a-zA-Z]+"))){
               alertLabel.setText("Le format de la classe est invalide ");
           }else if(signupModel.isEmail(email)){
               alertLabel.setText("email déjà présent");
               return;
           }
               else{
               alertLabel.setText("");
               signupModel.isSignup(
                       username,
                       username_canonical,
                       email, 
                       email_canonical, 
                enabled,
                salt, 
                password   ,
                last_login ,
                confirmation_token,
                password_requested_at,
                roles,
                nom_prenom,
                date_naissance,
                sexe,
                numero_tel,
                adresse,
                description
                ,url_photo_profil,
                date_inscription,
                status,
                classe 
                ,matiere,
                classe2); 
               alertLabel.setText("Inscription avec succès");
               infoBox("Inscription avec succès :)",null,"Success" );
               Node node = (Node)event.getSource();
               Stage dialogStage = (Stage) node.getScene().getWindow();
               dialogStage.close();
               Scene scene = new Scene(FXMLLoader.load(getClass().getResource("Login.fxml")));
               dialogStage.setScene(scene);
               dialogStage.show();
           }
           
       } catch (SQLException ex) {
           alertLabel.setText("Veuillez remplir tous les champs ");
           Logger.getLogger(SignupController.class.getName()).log(Level.SEVERE, null, ex);
       }catch(IOException ex){
           System.out.println(""+ex);
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
