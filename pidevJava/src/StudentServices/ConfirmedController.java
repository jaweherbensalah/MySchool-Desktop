package StudentServices;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import views.SceneChanger;
/**
 * FXML Controller class
 *
 * @author jaweher
 */
public class ConfirmedController implements Initializable {
    @FXML private Button RetouInterfaceEleve;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    @FXML public void RetourInterfaceEleve(ActionEvent event) throws IOException
    {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "/views/EleveInterface.fxml", "Interface Elève ");
 
    }
    
}

