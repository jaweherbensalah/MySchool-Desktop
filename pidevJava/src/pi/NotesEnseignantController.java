/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import util.DataSource;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import pidevfinal.ConnecterController;
import pidevfinal.Directeur_dashboardController;
import pidevfinal.PidevFinal;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class NotesEnseignantController implements Initializable {

    @FXML
    private TableView<Note> notes;
    @FXML
    private TableColumn<Note,String> eleve;
    @FXML
    private TableColumn<Note, Float> matiere;
    @FXML
    private TableColumn<Note, Float> ds;
    @FXML
    private TableColumn<Note, Float> cc;
    @FXML
    private TableColumn<Note, Float> examen;
    @FXML
    private TableColumn<Note, Float> moyenne;
    @FXML
    private ImageView ecole1;
    @FXML
    private ImageView dash1;
    @FXML
    private Button nom_ens;
    @FXML
    private Button matiere1;
    @FXML
    private ImageView mati;
    @FXML
    private ImageView emp;
    @FXML
    private Button emplois;
    @FXML
    private Button rdv;
    @FXML
    private Button contact;
    @FXML
    private ImageView cont;
    @FXML
    private Button evenement1;
    @FXML
    private ImageView ev1;
    @FXML
    private Button menu1;
    @FXML
    private ImageView me1;
    @FXML
    private ImageView mes;
    @FXML
    private Button afficherclass;
    @FXML
    private ImageView clas;
    @FXML
    private ImageView etu;
    @FXML
    private Button AjouterAbs;
    @FXML
    private ImageView abs;
    @FXML
    private Button AjouterNote;
    @FXML
    private ComboBox<String> deconnexion;

    ObservableList<Note>  list = FXCollections.observableArrayList() ;
             ObservableList<String> liste_profil = FXCollections.observableArrayList();

private Statement ste;
private PreparedStatement pst ;
private ResultSet rs ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initProfil();
          ecole1.setImage(Directeur_dashboardController.img2);
         Image img1 = new Image("/images/cool-profile-icons-64.png");
         dash1.setImage(img1);
         mati.setImage(Directeur_dashboardController.img15);
         Image img2 = new Image("/images/icon.png");
         emp.setImage(img2);
        mes.setImage(Directeur_dashboardController.img6);
        Image img3 = new Image("/images/mo_354424.png");
        cont.setImage(img3);
        ev1.setImage(Directeur_dashboardController.img7);
        me1.setImage(Directeur_dashboardController.img8);
        clas.setImage(Directeur_dashboardController.img14);
        etu.setImage(Directeur_dashboardController.img10);
        
         try {
            Connection   connection = DataSource.getInstance().getCnx();
            list.clear();
            ResultSet rs=connection.createStatement().executeQuery("select * from note");
            while (rs.next())

            {
            list.add(new Note(rs.getString("matiere_id"),rs.getFloat("ds"),rs.getFloat("cc"),rs.getFloat("examen"),rs.getFloat("moyenne"),rs.getInt("eleve_id")));
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(AfficheNotesController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
   
            matiere.setCellValueFactory(new PropertyValueFactory<>("matiere_id"));
           eleve.setCellValueFactory(new PropertyValueFactory<>("eleve_id"));
            ds.setCellValueFactory(new PropertyValueFactory<>("ds"));
            cc.setCellValueFactory(new PropertyValueFactory<>("cc"));
            examen.setCellValueFactory(new PropertyValueFactory<>("examen"));
            moyenne.setCellValueFactory(new PropertyValueFactory<>("moyenne"));
             
             notes.setItems(list) ;
         
    }    

    @FXML
    public void displayNote() {
         try {
            Connection   connection = DataSource.getInstance().getCnx();
            list.clear();
            ResultSet rs=connection.createStatement().executeQuery("select * from note");
            while (rs.next())

            {
            list.add(new Note(rs.getString("matiere_id"),rs.getFloat("ds"),rs.getFloat("cc"),rs.getFloat("examen"),rs.getFloat("moyenne"),rs.getInt("eleve_id")));
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(AfficheNotesController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
   
            matiere.setCellValueFactory(new PropertyValueFactory<>("matiere_id"));
           eleve.setCellValueFactory(new PropertyValueFactory<>("eleve_id"));
            ds.setCellValueFactory(new PropertyValueFactory<>("ds"));
            cc.setCellValueFactory(new PropertyValueFactory<>("cc"));
            examen.setCellValueFactory(new PropertyValueFactory<>("examen"));
            moyenne.setCellValueFactory(new PropertyValueFactory<>("moyenne"));
             
             notes.setItems(list) ;
         
        
        
        
        
    }
    
    @FXML
    private void deleteNote(ActionEvent event) {
           Note selectedNote ;
        ObservableList<Note>  allNote ; 
      allNote=notes.getItems();
     selectedNote=notes.getSelectionModel().getSelectedItem();
   
       try {
            
            
            Connection   connection = DataSource.getInstance().getCnx();
          list.clear();
            int rs=connection.createStatement().executeUpdate("DELETE  from  note  WHERE id = '"+selectedNote.getId()+"'");
           
           
        } catch (SQLException ex) {
            Logger.getLogger(AfficheMatieresController.class.getName()).log(Level.SEVERE, null, ex);
        }
         displayNote();
    }

    @FXML
    private void ProfilAction(ActionEvent event) {
    }

    @FXML
    private void MatiereAction(ActionEvent event) {
           FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/EnseignantAccueil.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)menu1.getScene().getWindow();
              first.close();
    }

    @FXML
    private void EmploisAction(ActionEvent event) {
    }

    @FXML
    private void rdvAction(ActionEvent event) {
    }

    @FXML
    private void ContactAction(ActionEvent event) {
    }

    @FXML
    private void evenementButtonAction(ActionEvent event) {
    }

    @FXML
    private void menuButtonAction(ActionEvent event) {
    }

    @FXML
    private void afficherClassesAction(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/EnseignantAccueil.fxml"));
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
    private void AjouterAbsenceAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/pidevfinal/AjouterAbsByEns.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void AjouterNoteAction(ActionEvent event) throws IOException {
         FXMLLoader loader=new FXMLLoader();
         loader.setLocation(getClass().getResource("/pi/AjouterNotes.fxml"));
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
    private void deconnexionAction(ActionEvent event) throws IOException {
         String p = ConnecterController.n;
        String v = "deconnexion";
        String o = "profil";
        String m = deconnexion.getValue();
        if (m.equals(v)) {
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
    }
      private void initProfil() {
        String p = ConnecterController.n;
        String v = "deconnexion";
        String o = "profil";
        liste_profil.addAll(p, o, v);
        deconnexion.setItems(liste_profil);
    }
}
