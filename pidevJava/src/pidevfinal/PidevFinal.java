/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfinal;

import Entites.Enseignants;
import com.teknikindustries.bulksms.SMS;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import service.EnseignantsService;

/**
 *
 * @author Asus
 */
public class PidevFinal extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
//Parent root = FXMLLoader.load(getClass().getResource("DirecteurAdmin.fxml"));
  // Parent root = FXMLLoader.load(getClass().getResource("AjoutEmploi.fxml"));
     Parent root = FXMLLoader.load(getClass().getResource("connecter.fxml"));

 //Parent root = FXMLLoader.load(getClass().getResource("test.fxml"));
//Parent root = FXMLLoader.load(getClass().getResource("statistiqueAbsence.fxml"));

        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm(); 
        scene.getStylesheets().add(css); 
        stage.setScene(scene);
       //stage.setResizable(false);
        stage.show();
 
           
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      launch(args);
  
    }
    }
    

