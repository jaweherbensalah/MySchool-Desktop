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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import ServicesScolarite.SceneChanger;
public class LoginController implements Initializable {
    public LoginModel loginModel=new LoginModel();
   /* private static int cust_id;*/
    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField textemail;
    @FXML
    private JFXPasswordField textpass;
    @FXML
    private Label alertLabel;
    public static int cust_id;
    @FXML
    public void exitScreen(ActionEvent event) throws IOException {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "/pidevfinal/Eleve_Accueil.fxml", "All Menues");
    }
      @FXML
     public void MenuScreen(ActionEvent event) throws Exception  {
		Stage primaryStage =new Stage();
		Parent root =FXMLLoader.load(getClass().getResource("/views/EleveInterface.fxml"));
                primaryStage.setTitle("Bienvenue aux services scolarité");
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();
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
            if(loginModel.isLogin(textemail.getText(), textpass.getText())){
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
