/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesScolarite;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import Entites.Menu;
import Entites.eleve_restau;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class RestaurantRegisterController implements Initializable {

    @FXML private Label headerLabel;
    @FXML private ComboBox<String> duree;
    @FXML private TextField emailTextField;
    @FXML private TextField usernameTextField;
    @FXML private PasswordField password;
    @FXML private Label errMsgLabel;
    @FXML private Button confirmer;
    private eleve_restau eleve_restau;
ObservableList<String>list=FXCollections.observableArrayList("trimestre","semestre","année");
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      duree.setItems(list);
    }    

    @FXML private void RestaurantRegisterButtonPushed(ActionEvent event) {
        
        try
            {
                  //do not show errors if creating Menu was successful
                    eleve_restau = new eleve_restau(emailTextField.getText(),
                            usernameTextField.getText(),
                    password.getText(),duree.getValue());
                    eleve_restau.insertIntoDB();
                    errMsgLabel.setText("Vous êtes inscri au Restaurant !");
             /*   SceneChanger sc = new SceneChanger();
                sc.changeScenes(event, "MenuTableView.fxml", "Tous les menus :"); */
            }
            catch (Exception e)
            {
                errMsgLabel.setText(e.getMessage());
            }
    }

    @FXML public void cancelButtonPushed(ActionEvent event) throws IOException
    {
        SceneChanger sc = new SceneChanger();
        
        //check if it is an admin user and go to the table view
        //if (SceneChanger.getLoggedInUser().isAdmin())
            sc.changeScenes(event, "MenuTableView.fxml", "All Menues");
      /*  else
        {
           // LogHoursViewController controller = new LogHoursViewController();
           // sc.changeScenes(event, "LogHoursView.fxml", "Log Hours", menu, controller);
        }*/
    
    }
    
}
