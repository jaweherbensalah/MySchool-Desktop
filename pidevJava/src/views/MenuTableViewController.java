/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import StudentServices.ListServicesController;
import static StudentServices.ListServicesController.infoBox1;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Menu;
import pidevfinal.ConnecterController;
import pidevfinal.Directeur_dashboardController;
import pidevfinal.PidevFinal;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class MenuTableViewController implements Initializable {

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
    private ImageView ne;
    @FXML
    private Button inscription;
    @FXML
    private ImageView etu;
    @FXML
    private Button affecterEe;
    @FXML
    private ImageView abs;
    @FXML
    private Button AjouterAbs;
    @FXML
    private ImageView st;
    @FXML
    private Button editMenuButton1;
    @FXML
    private Button editMenuButton;
    @FXML
    private TableView<Menu> menuTable;
    @FXML
    private TableColumn<Menu, Integer> menuIDColumn;
    @FXML
    private TableColumn<Menu, String> itemsColumn;
    @FXML
    private TableColumn<Menu, String> prixColumn;
    ObservableList<String> liste_profil = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editMenuButton.setDisable(true);    
             // confgure the table columns
         menuIDColumn.setCellValueFactory(new PropertyValueFactory<Menu, Integer>("id"));
         itemsColumn.setCellValueFactory(new PropertyValueFactory<Menu, String>("items"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<Menu, String>("prix"));
        try{
            loadMenues();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        initProfil();
        
          dash.setImage(Directeur_dashboardController.img1);
        ecole.setImage(Directeur_dashboardController.img2);
        ens.setImage(Directeur_dashboardController.img3);
        res.setImage(Directeur_dashboardController.img4);
        trans.setImage(Directeur_dashboardController.img5);
        ms.setImage(Directeur_dashboardController.img6);
        ev.setImage(Directeur_dashboardController.img7);
        me.setImage(Directeur_dashboardController.img8);
         ne.setImage(Directeur_dashboardController.img9);
          etu.setImage(Directeur_dashboardController.img10);
        abs.setImage(Directeur_dashboardController.img11);
        st.setImage(Directeur_dashboardController.img16);
    }    
public void loadMenues() throws SQLException {
     ObservableList<Menu> menues = FXCollections.observableArrayList();
        
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev","root","");
            //2.  create a statement object
            statement = conn.createStatement();
            
            //3.  create the SQL query
            resultSet = statement.executeQuery("SELECT * FROM menu order by items");
            
            //4.  create menu objects from each record
            while (resultSet.next())
            {
                Menu newMenu = new Menu(resultSet.getString("items"),
                                             resultSet.getString("prix"));
                newMenu.setId(resultSet.getInt("id"));
                newMenu.setPhoto(new File(resultSet.getString("photo")));
                
                menues.add(newMenu);
            }
            
            menuTable.getItems().addAll(menues);
            
        } catch (Exception e)
        {
            System.err.println(e);
        }
        finally
        {
            if (conn != null)
                conn.close();
            if(statement != null)
                statement.close();
            if(resultSet != null)
                resultSet.close();
        }
}

 /**
     * If a user has been selected in the table, enable the edit button
     */
    
    @FXML
    private void dashboardButtonAction(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pidevfinal/Directeur_dashboard.fxml"));
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
              Stage first = (Stage)abs.getScene().getWindow();
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
              Stage first = (Stage)abs.getScene().getWindow();
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
              Stage first = (Stage)abs.getScene().getWindow();
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
              Stage first = (Stage)abs.getScene().getWindow();
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

    @FXML
    private void inscriptionButttonAction(ActionEvent event) throws IOException {
        SceneChanger sc = new SceneChanger();
      
            sc.changeScenes(event, "/RegisterValidation/TakeOrder.fxml", "abonnements");
    }

    @FXML
    private void AffecterEleveAction(ActionEvent event) {
    }

    @FXML
    private void AjouterAbsenceAction(ActionEvent event) {
    }

    @FXML
    private void afficherstat(ActionEvent event) throws IOException {
          SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "/RegisterValidation/statistiqueInscriptions.fxml", "Statistique Elève ");
    }
    

    @FXML
    private void deleteButtonPushed(ActionEvent event) {
         Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;
         Menu tableIndex = this.menuTable.getSelectionModel().getSelectedItem();
    
       int tempMenuid = -1;
       try{
       tempMenuid = tableIndex.getId();
       }catch(Exception e){
           infoBox1("no item selected!", null, "Error");
           
       }
   
    if(tempMenuid >= 0){
        String query = "DELETE FROM menu WHERE id = ? ";  
        java.sql.PreparedStatement pst;
           try {
                  //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev","root","");
            pst = conn.prepareStatement(query);
               pst.setInt(1, tempMenuid);
              
               pst.execute();
               menuTable.getItems().remove(tableIndex);
               menuTable.refresh();
               menuTable.getSelectionModel().clearSelection();
               
              // loadMenues();
              
           } catch (SQLException ex) {
               Logger.getLogger(ListServicesController.class.getName()).log(Level.SEVERE, null, ex);
           }catch(Exception e){
               infoBox1("no item selected!", null, "Error");
           }
               
       
    } else {
        System.out.println("no selction made");
    }
    }

    @FXML
    private void newMenuButtonPushed(ActionEvent event) throws IOException {
         SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "NewMenuView.fxml", "Create New Menu");
    }

    @FXML
    private void editButtonPushed(ActionEvent event) throws IOException {
        SceneChanger sc = new SceneChanger();

        Menu menu = this.menuTable.getSelectionModel().getSelectedItem();
        NewMenuViewController npvc = new NewMenuViewController();
                System.out.println(menu.getPhoto().getCanonicalPath());
      sc.changeScenes(event, "NewMenuView.fxml", "Edit menu", menu,  npvc);
     
    }

    @FXML
    private void menuSelected(MouseEvent event) {
         editMenuButton.setDisable(false);
    }
    private void initProfil() {
        String p = pidevfinal.ConnecterController.n;
        String v = "deconnexion";
        String o = "profil";
        liste_profil.addAll(p, o, v);
        deconnexion.setItems(liste_profil);
    }
}
