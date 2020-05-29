package StudentServices;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import static StudentServices.SignupController.infoBox;
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
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pidevfinal.PidevFinal;
import views.SceneChanger;

/**
 * FXML Controller class
 *
 * @author jaweher
 */
public class LoginController implements Initializable {
    public LoginModel loginModel=new LoginModel();
   /* private static int cust_id;*/
    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField textemail1;

    @FXML
    private JFXPasswordField textpass1;
    
    @FXML
    private Label alertLabel;
    public static int cust_id;
    
    
  @FXML
    public void exitScreen(ActionEvent event) throws IOException {
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
      @FXML
     public void MenuScreen(ActionEvent event) throws Exception  {
		Stage primaryStage =new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("Menu.fxml"));
                primaryStage.setTitle("Bienvenue aux services scolarité");
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
                
            // Hide this current window (if this is what you want)
            ((Node)(event.getSource())).getScene().getWindow().hide();
	}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         if(loginModel.isDbConnected()){
             System.out.println("Db connected");
        }else{
             System.out.println("Db not connected");
        }
    } 
    
     public static void CustomerId(int cusst){
        cust_id=LoginModel.customer_id;
        
    }
    public void Login(ActionEvent event){
     try {
            if(loginModel.isLogin(textemail1.getText(), textpass1.getText())){
                infoBox("Biienvenues aux services de la scolarité ! "+cust_id,null,"Success" );
                 Parent root = FXMLLoader.load(getClass().getResource("/StudentServices/ListServices"));
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
   
    
}
