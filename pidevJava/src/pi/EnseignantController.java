/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class EnseignantController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void initialize(MouseEvent event) {
    }

    @FXML
    private void AfficherMatiere(ActionEvent event) {
    }

    @FXML
    private void AffecterEleve(ActionEvent event) {
    }

    @FXML
    private void AjouterNotes(ActionEvent event) throws IOException {
        
          FXMLLoader loader=new FXMLLoader();
         loader.setLocation(getClass().getResource("AjouterNotes.fxml"));
         Parent afficheNotes=loader.load();
         
         Scene affichage =new Scene(afficheNotes); 
         
         AjouterNotesController controller =loader.getController();
         controller.fillComboBox();
       controller.fillComboBox2();
         Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        
         window.setScene(affichage);
         window.show();
        
    }
    @FXML
    public void displayNotes(ActionEvent event) throws IOException
    {
    
    
    }

}
