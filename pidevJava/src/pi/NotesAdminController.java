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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pidevfinal.Directeur_dashboardController;
import pidevfinal.PidevFinal;
import util.DataSource;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class NotesAdminController implements Initializable {

    @FXML
    private Label msg;
    @FXML
    private TableView<Note> notes;
    @FXML
    private TableColumn<Note, Float> eleve;
    @FXML
    private TableColumn<Note, String> matiere;
    @FXML
    private TableColumn<Note, Float> ds;
    @FXML
    private TableColumn<Note, Float> cc;
    @FXML
    private TableColumn<Note, Float> examen;
    @FXML
    private TableColumn<?, ?> moyenne;
    @FXML
    private ImageView ecole;
    @FXML
    private ImageView dash;
    @FXML
    private Button dashboard;
    @FXML
    private Button enseignant;
    @FXML
    private ImageView res;
    @FXML
    private Button Résultats;
    @FXML
    private ImageView trans;
    @FXML
    private Button transport;
    @FXML
    private Button message;
    @FXML
    private ImageView ms;
    @FXML
    private ImageView ens;
    @FXML
    private Button evenement;
    @FXML
    private ImageView ev;
    @FXML
    private Button menu;
    @FXML
    private ImageView me;
    @FXML
    private ComboBox<String> deconnexion;
    @FXML
    private ImageView cl;
@FXML
    private ImageView ma;
@FXML
    private ImageView st;
@FXML
    private ImageView no;
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
        dash.setImage(Directeur_dashboardController.img1);
        ecole.setImage(Directeur_dashboardController.img2);
        ens.setImage(Directeur_dashboardController.img3);
        res.setImage(Directeur_dashboardController.img4);
        trans.setImage(Directeur_dashboardController.img5);
       // ms.setImage(Directeur_dashboardController.img6);
        ev.setImage(Directeur_dashboardController.img7);
        me.setImage(Directeur_dashboardController.img8);
      //  ne.setImage(Directeur_dashboardController.img9);
      cl.setImage(Directeur_dashboardController.img14);
      ma.setImage(Directeur_dashboardController.img15);
      st.setImage(Directeur_dashboardController.img16);
      no.setImage(Directeur_dashboardController.img4);
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
    private void AjouterMatiere(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pi/AjouterMatiere.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)ms.getScene().getWindow();
              first.close();
    }

    @FXML
    private void AffecterEleve(ActionEvent event) {
    }

    @FXML
    private void sendEmail(ActionEvent event) {
    }

    @FXML
    private void DeleteNote(ActionEvent event) {
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
         displayNotes();
    }
    

    @FXML
    private void dashboardButtonAction(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/DirecteurAdmin.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)res.getScene().getWindow();
              first.close();
    }

    @FXML
    private void enseignantButtonAction(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/admin_enseignant.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)res.getScene().getWindow();
              first.close();
    }

    @FXML
    private void RésultatsButtonAction(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("/pidevfinal/AjoutEmploi.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void transportButtonAction(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/views/TransportTableView.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)ecole.getScene().getWindow();
              first.close();
    }

    @FXML
    private void messageButtonAction(ActionEvent event) {
    }

    @FXML
    private void evenementButtonAction(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/myschoolgraphique/FXML.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)res.getScene().getWindow();
              first.close();
    }

    @FXML
    private void menuButtonAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/views/MenuTableView.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)cl.getScene().getWindow();
              first.close();
    }

    @FXML
    private void deconnexionAction(ActionEvent event) {
         String p = pidevfinal.ConnecterController.n;
        String v = "deconnexion";
        String o = "profil";
        String m = deconnexion.getValue();
        if (m.equals(v)) {
        
             FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("connecter.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)msg.getScene().getWindow();
              first.close();
    }
    }
     @FXML
    public void displayNotes() {
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
    private void afficherstat(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/statistiqueAbsence.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)ms.getScene().getWindow();
              first.close();
    }
    @FXML
    private void affichercl(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/DirecteurAdmin.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)ms.getScene().getWindow();
              first.close();
    }
     private void initProfil() {
        String p = pidevfinal.ConnecterController.n;
        String v = "deconnexion";
        String o = "profil";
        liste_profil.addAll(p, o, v);
        deconnexion.setItems(liste_profil);
    }
}
