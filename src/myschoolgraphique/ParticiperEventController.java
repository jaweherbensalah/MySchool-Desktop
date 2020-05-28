/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package myschoolgraphique;

import entities.participation;
import util.SendMail1;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.ClubService;
import service.ParticipationService;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ParticiperEventController implements Initializable {

    @FXML
    private AnchorPane HeureEvent;
    @FXML
    private TextField iduser;
    @FXML
    private TextField idevent;
    @FXML
    private DatePicker createDate;
    @FXML
    private Button add;
    @FXML
    private Button listEvent;
    service.ParticipationService SC= new ParticipationService();
      private Connection connexion;
    private Statement Ste;
    private PreparedStatement pst;
    private Statement ste;
    @FXML
    private Button Home2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajoutEvent(ActionEvent event) throws SQLException {
        
           connexion =   DataSource.getInstance().getCnx();
          Ste=connexion.createStatement();

            ParticipationService ser =new ParticipationService();
             participation part=new participation();
         
        
        part.setIduser(Integer.parseInt(iduser.getText()));
    //    part.setIdevent(Integer.parseInt(idevent.getText()));
        part.setCreatedat(java.sql.Date.valueOf(createDate.getValue().toString()));
        
     
            
            
          ser.EventComplet(part);
          //  ser.Participer(part);
             
             SendMail1.sendMail("sirine.charrad@esprit.tn", " confirmation Participation Event "," soyez à l'heure et à bientôt");
          
              
            
          
          System.out.println("ok");
        
    }

    @FXML
    private void listEvent(ActionEvent event) {
    }

    @FXML
    private void HomeBack2(ActionEvent event) throws IOException {
         Parent tableView = FXMLLoader.load(getClass().getResource("FXML.fxml"));
        Scene sceneview = new Scene(tableView);
          Stage window = (  Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(sceneview);
        window.show(); 
    }
    
}
