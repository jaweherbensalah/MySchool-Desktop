/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import Entites.Matiere;
import entity.Note;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.converter.FloatStringConverter;
import pidevfinal.ConnecterController;
import pidevfinal.PidevFinal;
import service.AbsenceService;
import service.NoteService;
import service.PunitionService;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class AfficheNotesController implements Initializable {

    @FXML
    private ImageView logo;
    @FXML
    private Label user;
    @FXML
    private Button deconnexion;
    @FXML
    private Button Accueil;
    @FXML
    private Button emplois;
    @FXML
    private Button absence;
    @FXML
    private Button punition;
    @FXML
    private Button reclamation;
    @FXML
    private Button transport;
    @FXML
    private Button restuarant;
    @FXML
    private Label pun;
    @FXML
    private Label abs;
    @FXML
    private TableColumn<Note, String> matiere;
    @FXML
    private TableColumn<Note, Float> cc;
    @FXML
    private TableColumn<Note, Float> ds;
    @FXML
    private TableColumn<Note, Float> examen;
    @FXML
    private TableColumn<Note, Float> moyenne;
    @FXML
    private TableView<Note> notes;
    
     ObservableList<Note>  list = FXCollections.observableArrayList() ;
     
private Statement ste;
private PreparedStatement pst ;
private ResultSet rs ;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Image img1 = new Image("/images/logo.png");
        //logo.setImage(img1);
        user.setText(ConnecterController.n);
        String nom = ConnecterController.n; 
          AbsenceService abservice = new AbsenceService(); 
        int c = abservice.calculerNomAbsence(nom); 
         String  z = Integer.toString(c);
         abs.setText(z);
        PunitionService pu = new PunitionService(); 
         int w = pu.calculerNomPunition(nom); 
      String  p = Integer.toString(w);
         pun.setText(p);
             try {
            
            
            Connection   connection = DataSource.getInstance().getCnx();
            list.clear();
            ResultSet rs=connection.createStatement().executeQuery("select * from note");
            while (rs.next())

            {
            list.add(new Note(rs.getString("matiere_id"),rs.getFloat("ds"),rs.getFloat("cc"),rs.getFloat("examen"),rs.getFloat("moyenne")));
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(AfficheNotesController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
   
            matiere.setCellValueFactory(new PropertyValueFactory<>("matiere_id"));
            ds.setCellValueFactory(new PropertyValueFactory<>("ds"));
            cc.setCellValueFactory(new PropertyValueFactory<>("cc"));
            examen.setCellValueFactory(new PropertyValueFactory<>("examen"));
            moyenne.setCellValueFactory(new PropertyValueFactory<>("moyenne"));
             notes.setItems(list) ;
         /*   notes.setEditable(true);
            
            matiere.setCellFactory(TextFieldTableCell.forTableColumn());
            ds.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter() ));
            cc.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
            examen.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
            moyenne.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        */
    }    
    @FXML
    private void DeconnexionAction(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/connecter.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)abs.getScene().getWindow();
              first.close();
    }

    @FXML
    private void AccueilAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/Eleve_Accueil.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)abs.getScene().getWindow();
              first.close();
    }

    @FXML
    private void AfficherEmploisAction(ActionEvent event) {
    }

    @FXML
    private void afficherAbsenceAction(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/Absence_Eleve.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)abs.getScene().getWindow();
              first.close();
       
    }

    @FXML
    private void afficherPunitionAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/Punition_Eleve.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)abs.getScene().getWindow();
              first.close();
      
    }

     @FXML
    private void inscriClub(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/myschoolgraphique/clubsUserPage.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)abs.getScene().getWindow();
              first.close();
    }
    
    @FXML
    private void afficherReclamationAction(ActionEvent event) {
    }

    @FXML
    private void afficherTransportAction(ActionEvent event) {
        
    }

    @FXML
    private void afficherRestaurantAction(ActionEvent event) {
    }

    @FXML
    public void displayNote()  {
         
        try {
            
            
            Connection   connection = DataSource.getInstance().getCnx();
            list.clear();
            ResultSet rs=connection.createStatement().executeQuery("select * from note");
            while (rs.next())

            {
            list.add(new Note(rs.getString("matiere_id"),rs.getFloat("ds"),rs.getFloat("cc"),rs.getFloat("examen"),rs.getFloat("moyenne")));
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(AfficheNotesController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
   
            matiere.setCellValueFactory(new PropertyValueFactory<>("matiere_id"));
            ds.setCellValueFactory(new PropertyValueFactory<>("ds"));
            cc.setCellValueFactory(new PropertyValueFactory<>("cc"));
            examen.setCellValueFactory(new PropertyValueFactory<>("examen"));
            moyenne.setCellValueFactory(new PropertyValueFactory<>("moyenne"));
             notes.setItems(list) ;
         /*   notes.setEditable(true);
            
            matiere.setCellFactory(TextFieldTableCell.forTableColumn());
            ds.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter() ));
            cc.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
            examen.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
            moyenne.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));
        */
    }

    @FXML
    private void displayBulletin(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pi/Bulletin.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)abs.getScene().getWindow();
              first.close();
    }
     
   
      
      
    
}
