
package ServicesScolarite;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.io.IOException;
import javafx.event.ActionEvent;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AdminController implements Initializable {

    @FXML
    private Tab client;
    @FXML
    private TableView<?> tableclients;
    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private TableColumn<?, ?> prenom;
    @FXML
    private TableColumn<?, ?> date_naissance;
    @FXML
    private TableColumn<?, ?> sexe;
    @FXML
    private TableColumn<?, ?> adresse;
    @FXML
    private TableColumn<?, ?> ville;
    @FXML
    private TableColumn<?, ?> zip;
    @FXML
    private TableColumn<?, ?> nTelephone;
    @FXML
    private TableColumn<?, ?> email;
    @FXML
    private TableColumn<?, ?> password;
    @FXML
    private TableColumn<?, ?> nCarteBancaire;
    @FXML
    private TableColumn<?, ?> date_validation;
    @FXML
    private TableColumn<?, ?> code_s;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private Button loder_D;
    @FXML
    private Tab vendeur;
    @FXML
    private TableView<?> tablevendeur;
    @FXML
    private TableColumn<?, ?> id1;
    @FXML
    private TableColumn<?, ?> nom1;
    @FXML
    private TableColumn<?, ?> prenom1;
    @FXML
    private TableColumn<?, ?> date_naissance1;
    @FXML
    private TableColumn<?, ?> sexe1;
    @FXML
    private TableColumn<?, ?> adresse1;
    @FXML
    private TableColumn<?, ?> ville1;
    @FXML
    private TableColumn<?, ?> zip1;
    @FXML
    private TableColumn<?, ?> nTelephone1;
    @FXML
    private TableColumn<?, ?> email1;
    @FXML
    private TableColumn<?, ?> password1;
    @FXML
    private TableColumn<?, ?> situation_fiscal;
    @FXML
    private TableColumn<?, ?> rib;
    @FXML
    private Button modifier1;
    @FXML
    private Button supprimer1;
    @FXML
    private Button loder_D1;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML private void MenuClickedButton(ActionEvent event) throws IOException {
    
   
        SceneChanger sc = new SceneChanger();
        
        //check if it is an admin user and go to the table view
        //if (SceneChanger.getLoggedInUser().isAdmin())
            sc.changeScenes(event, "MenuTableView.fxml", "Tous les Menus");
      /*  else
        {
           // LogHoursViewController controller = new LogHoursViewController();
           // sc.changeScenes(event, "LogHoursView.fxml", "Log Hours", menu, controller);
        }*/
    
    }

    @FXML private void TransportClickedButton(ActionEvent event) throws IOException {
        
        SceneChanger sc = new SceneChanger();
     
            sc.changeScenes(event, "TransportTableView.fxml", "admin");
    
    
    }
       @FXML private void AbonnementClickedButton(ActionEvent event) throws IOException {
        
        SceneChanger sc = new SceneChanger();
      
            sc.changeScenes(event, "/RegisterValidation/TakeOrder.fxml", "abonnements");
  
            
    
    }
    
}
