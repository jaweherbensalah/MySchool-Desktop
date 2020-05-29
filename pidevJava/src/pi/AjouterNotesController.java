/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pi;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import pidevfinal.Directeur_dashboardController;
import pidevfinal.PidevFinal;
import util.DataSource;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class AjouterNotesController implements Initializable {

    @FXML
    private TextField ds;
    @FXML
    private TextField cc;
    @FXML
    private TextField examen;
    @FXML
    private ComboBox<String> matiere_id;
    @FXML
    private ComboBox<Integer> eleve_id;
    @FXML
    private TextField coeffds;
    @FXML
    private TextField coeffcc;
    @FXML
    private TextField coeffex;
    @FXML
    private ImageView ecole1;
    @FXML
    private ImageView dash1;
    @FXML
    private Button nom_ens;
    @FXML
    private Button matiere;
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
     ObservableList<String> liste_profil = FXCollections.observableArrayList();

     public void fillComboBox()
     {
     
     
     }
      public void fillComboBox2()
     {
     
      
     }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
      initProfil();
       Connection   connection = DataSource.getInstance().getCnx();
        try {
            ResultSet rs=connection.createStatement().executeQuery("select idMat from matiere");
            while (rs.next())
            {
             matiere_id.getItems().add(rs.getString("idMat"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterNotesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ResultSet rs=connection.createStatement().executeQuery("select id from eleve");
            while (rs.next())
            {
             eleve_id.getItems().add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AjouterNotesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void addNote(ActionEvent event) {
            try {
            Float ccc=Float.parseFloat(cc.getText());
            Float dss=Float.parseFloat(ds.getText());
            Float ex=Float.parseFloat(examen.getText());
            Float coeffccc=Float.parseFloat(coeffcc.getText());
            Float coeffdss=Float.parseFloat(coeffds.getText());
            Float coeffexx=Float.parseFloat(coeffex.getText());
            Float somme =coeffccc+coeffdss+coeffexx; 
            Float moy=(ccc*coeffccc+coeffdss*dss+coeffexx*ex)/somme ; 
            Connection   connection = DataSource.getInstance().getCnx();
     
            String mat=   matiere_id.getSelectionModel().getSelectedItem();
            int eleve1 =eleve_id.getSelectionModel().getSelectedItem();
            
          /*   ResultSet rs3=connection.createStatement().executeQuery("select avg(moyenne) from note " + " WHERE eleve_id = '"+eleve1+"'");
              if(rs3.next())
              {
                 String chaine = String.valueOf(rs3.getString(1));
                Float moy2=Float.valueOf(rs3.getString(1));
   
          String appreciation; 
                appreciation = "";
           if ((moy2>10)&&(moy2<=12))
           { appreciation="passable"; }
           else if ((moy2>12)&&(moy2<=14))
           {appreciation="assez bien"; }
           else if ((moy2>14)&&(moy2<=16))
           {appreciation="trés bien"; }
           else if ((moy2<10))
           {appreciation="refusé"; }
           else appreciation="excellent";*/
           
           
     //      int req=connection.createStatement().executeUpdate("DELETE * FROM Bulletin WHERE eleve_id = '"+eleve1+"'");
            int rs= connection.createStatement().executeUpdate("insert into note(matiere_id,ds,cc,examen,coeffcc,coeffDS,coeffEX,moyenne,eleve_id) values('"+mat+"','"+dss+"','"+ccc+"','"+ex+"','"+coeffccc+"','"+coeffdss+"','"+coeffexx+"','"+moy+"','"+eleve1+"')");
         //  int rs2=connection.createStatement().executeUpdate("insert into Bulletin(moyenne,appreciation,eleve_id) values('"+moy2+"','"+appreciation+"','"+eleve1+"')");
              
        } catch (SQLException ex) {
            Logger.getLogger(AjouterMatiereController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    
    
    
    
    
     // moy1 = calculerMoy();
            /*  BigDecimal myvalue;
            myvalue = rs3.getBigDecimal("moyenne");
            
            
              float moy2=myvalue.floatValue();*/
    

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
              Stage first = (Stage)cc.getScene().getWindow();
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
              Stage first = (Stage)cc.getScene().getWindow();
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
    private void AjouterNoteAction(ActionEvent event) {
          FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pi/notesEnseignant.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)cc.getScene().getWindow();
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
              Stage first = (Stage)cc.getScene().getWindow();
              first.close();
        }
    }
     private void initProfil() {
        String p = pidevfinal.ConnecterController.n;
        String v = "deconnexion";
        String o = "profil";
        liste_profil.addAll(p, o, v);
        deconnexion.setItems(liste_profil);
    }
}
