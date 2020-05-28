
package ServicesScolarite;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import Entites.Menu;
import Entites.Transport;
import ServicesScolarite.SceneChanger;


public class EleveInterfaceController implements Initializable,ControllerClass{

    @FXML
    private ImageView logo;
    @FXML
    private Label user;
    @FXML
    private Button deconnexion;
    @FXML
    private Button Accueil;
    @FXML private Button emplois;
    @FXML private Button absence;
    @FXML private Button punition;
    @FXML private Button resultat;
    @FXML private Button reclamation;
    @FXML private Button transport;
    @FXML private Button restuarant;
    @FXML private Label pun;
    @FXML private Label abs;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       /* Image img1 = new Image("/images/logo.png");
        logo.setImage(img1);*/
       
    }    

    @FXML
    private void DeconnexionAction(ActionEvent event) {
    }

    @FXML
    private void AccueilAction(ActionEvent event) {
    }

    @FXML
    private void AfficherEmploisAction(ActionEvent event) {
    }

    @FXML   private void afficherAbsenceAction(ActionEvent event) {
    }

    @FXML private void afficherPunitionAction(ActionEvent event) {
    }

    @FXML  private void afficherRseultatAction(ActionEvent event) {
    }

    @FXML   private void afficherReclamationAction(ActionEvent event) {
    }

    @FXML   private void afficherTransportAction(ActionEvent event) {
    }

    @FXML 
    private void ServiceRestauButton(ActionEvent event) throws IOException {
        SceneChanger sc = new SceneChanger();
            sc.changeScenes(event, "/StudentServices/ListServices.fxml", "services");
     
    }

    @Override
    public void preloadData(Menu menu) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void preloadData(Transport transport) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
