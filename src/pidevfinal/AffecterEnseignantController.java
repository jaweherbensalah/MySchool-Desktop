
package pidevfinal;

import Entites.Classe;
import Entites.Enseignants;
import Entites.Matiere;
import com.teknikindustries.bulksms.SMS;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.Notifications;
import service.ClasseService;
import service.EnseignantsService;
import service.MatiereService;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AffecterEnseignantController implements Initializable {
      ObservableList<Classe> classe; 
      ObservableList<Enseignants> enseignant ; 
          ObservableList<Matiere> matiere ; 
    ObservableList<Enseignants> liste_Enseignant;

    
    @FXML
    private Button button;
    @FXML
    private ChoiceBox<Classe> classes;
    @FXML
    private ChoiceBox<Enseignants> enseignants;
    @FXML
    private Label msg;
    @FXML
    private AnchorPane rootPane;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        EnseignantsService ps = new EnseignantsService(); 
         String cla = classes.getSelectionModel().getSelectedItem().getNom(); 
         Enseignants ele = enseignants.getSelectionModel().getSelectedItem(); 
       //  String ma = matieres.getSelectionModel().getSelectedItem().getNomMat();
         if(ps.affecterEnseignant(ele, cla))
         {
               List<Enseignants> listenseignants = ps.displayEnseignant();
        for (int i = 0; i < listenseignants.size(); i++) {
            liste_Enseignant = FXCollections.observableArrayList(listenseignants);
        }
        DirecteurAdminController.list_ens2.setItems(liste_Enseignant);
            /* Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Affectation");
 
        // alert.setHeaderText("Results:");
        alert.setContentText("affecter enseignant avec succes  ");
 
        alert.showAndWait();
 
        alert.showAndWait();*/
             System.out.println("affecter avec succes");
            Notifications.create()
                     .title("affectation")
                     .text("affectation enseignant avec succes  ").showWarning(); 
        sms("cyrine10","16111998Eya","21656656110", "affectation enseignants");
         }
         else 
         {
       Alert alert = new Alert(AlertType.ERROR);
alert.setTitle("Error ");
alert.setHeaderText("Affectation enseignants");
alert.setContentText("probleme");
 
alert.showAndWait();  
         }
    }

    @FXML
    private void afficherClasses(MouseEvent event) {
       ClasseService clService = new ClasseService();
     List<Classe> listClasse = clService.displayClasse(); 
         for(int i=0;i< listClasse.size();i++)
         {
      classe= FXCollections.observableArrayList(listClasse); 
         } 
       classes.setItems(classe);      
    }

    @FXML
    private void afficherEnseignant(MouseEvent event) {
     EnseignantsService clService = new EnseignantsService();  
     List<Enseignants> listenseignant = clService.displayEnsClasse();
         for(int i=0;i< listenseignant.size();i++)
         {
      enseignant= FXCollections.observableArrayList(listenseignant); 
         } 
       enseignants.setItems(enseignant);   
    }
    @FXML
    private void afficherEleve(ActionEvent event) throws IOException {
            AnchorPane pane = FXMLLoader.load(getClass().getResource("DirecteurAdmin.fxml"));
            rootPane.getChildren().setAll(pane); 
    }
     
 public void sms(String username , String password , String to , String messagee) {
        try {
             String myURI = "https://api.bulksms.com/v1/messages";

    // change these values to match your own account
    String myUsername = ""+username+"";
    String myPassword = ""+password+"";

    // the details of the message we want to send
    String myData = "{to: \""+to+"\", encoding: \"UNICODE\", body: \""+messagee+"\"}";

    // if your message does not contain unicode, the "encoding" is not required:
    // String myData = "{to: \"1111111\", body: \"Hello Mr. Smith!\"}";

    // build the request based on the supplied settings
    URL url = new URL(myURI);
    HttpURLConnection request = (HttpURLConnection) url.openConnection();
    request.setDoOutput(true);

    // supply the credentials
    String authStr = myUsername + ":" + myPassword;
    String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
    request.setRequestProperty("Authorization", "Basic " + authEncoded);

    // we want to use HTTP POST
    request.setRequestMethod("POST");
    request.setRequestProperty( "Content-Type", "application/json");

    // write the data to the request
    OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
    out.write(myData);
    out.close();

    // try ... catch to handle errors nicely
    try {
      // make the call to the API
      InputStream response = request.getInputStream();
      BufferedReader in = new BufferedReader(new InputStreamReader(response));
      String replyText;
      while ((replyText = in.readLine()) != null) {
        System.out.println(replyText);
      }
      in.close();
    } catch (IOException ex) {
      System.out.println("An error occurred:" + ex.getMessage());
      BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
      // print the detail that comes with the error
      String replyText;
      while ((replyText = in.readLine()) != null) {
        System.out.println(replyText);
      }
      in.close();
    }
    request.disconnect();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
  
}
