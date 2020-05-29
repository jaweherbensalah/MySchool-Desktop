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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import pidevfinal.PidevFinal;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Login1Controller implements Initializable {
    public LoginModel loginModel=new LoginModel();

    @FXML
    private JFXTextField textemail;
    @FXML
    private JFXPasswordField textpass;
    @FXML
    private Label alertLabel;
    @FXML
    private JFXTextField textemail1;
    @FXML
    private JFXPasswordField textpass1;
    @FXML
    private Label alertLabel1;
 public static int cust_id;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(loginModel.isDbConnected()){
             System.out.println("Db connected");
        }else{
             System.out.println("Db not connected");
        }
    }    

    @FXML
    private void Login(ActionEvent event) {
     try {
            if(loginModel.isLogin(textemail.getText(), textpass.getText())){
                infoBox("Biienvenues aux services de la scolarité ! "+cust_id,null,"Success" );
                 Parent root = FXMLLoader.load(getClass().getResource("/StudentServices/tes.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
        Stage first = (Stage)alertLabel.getScene().getWindow();
              first.close();
              
                
                
            }else{
                infoBox("Entrer correctement l'email et le mot de passe",null,"Failed" );
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            alertLabel.setText("Entrer correctement l'email et le mot de passe");
        } catch (IOException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     public static void infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    @FXML
    private void exitScreen(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/StudentServices/FXMLDocument.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
        Stage first = (Stage)alertLabel.getScene().getWindow();
              first.close();
    }
     public static void CustomerId(int cusst){
        cust_id=LoginModel.customer_id;
        
    }
}
