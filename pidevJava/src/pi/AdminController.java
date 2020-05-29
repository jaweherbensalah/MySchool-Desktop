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
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AdminController implements Initializable {

    @FXML
    private Label msg;

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
    private void AffecterEleve(ActionEvent event) {
    }

    @FXML
    private void AjouterMatiere(ActionEvent event) throws IOException {
        
        FXMLLoader loader=new FXMLLoader();
         loader.setLocation(getClass().getResource("AjouterMatiere.fxml"));
         Parent afficheMatiere=loader.load();
         
         Scene affichage =new Scene(afficheMatiere); 
         
       
         Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        
         window.setScene(affichage);
         window.show();
    }

    @FXML
    private void displayMatieres(ActionEvent event) throws IOException {
         FXMLLoader loader=new FXMLLoader();
         loader.setLocation(getClass().getResource("AfficheMatieres.fxml"));
         Parent afficheMatiere=loader.load();
         
         Scene affichage =new Scene(afficheMatiere); 
         
         AfficheMatieresController controller =loader.getController();
         controller.rechercher();
       
         Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        
         window.setScene(affichage);
         window.show();
       
    }

    @FXML
    private void displayNotes(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader();
         loader.setLocation(getClass().getResource("NotesAdmin.fxml"));
         Parent afficheMatiere=loader.load();
         
         Scene affichage =new Scene(afficheMatiere); 
         
         NotesAdminController controller =loader.getController();
         controller.displayNotes();
       
         Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
        
         window.setScene(affichage);
         window.show();
    }

    
}
