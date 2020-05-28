/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServicesScolarite;

import static StudentServices.ListServicesController.infoBox1;
import java.io.IOException;
import java.net.URL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import pidevfinal.Directeur_dashboardController;
import pidevfinal.PidevFinal;
import util.SendMail2;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class TakeOrderController implements Initializable {

    @FXML
    private TableView<ModelTable> table;
    @FXML
    private TableColumn<ModelTable,String> ordernoCol;
    @FXML
    private TableColumn<ModelTable,String> custCol;
    @FXML
    private TableColumn<ModelTable,String> menuCol;
    @FXML
    private TableColumn<ModelTable,String> QuantityCol;
    @FXML
    private TableColumn<ModelTable,String> deliveryCol;
    @FXML
    private TableColumn<ModelTable,String> addressCol;
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
 TakeOrderModel takeOrderModel =new TakeOrderModel();
     ObservableList<String> liste_profil = FXCollections.observableArrayList();
 Connection con;
    
    ObservableList<ModelTable> obList= FXCollections.observableArrayList();
    
    public TakeOrderController(){
        con=SqlConnection.Connector();
    }
    
  
  
    @FXML
    private void exitScreen(ActionEvent event) throws IOException
    {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "/AdminServices/AdminMain.fxml", "Interface Admin ");
 
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(takeOrderModel.isDbConnected()){
             System.out.println("Db connected");
        }else{
             System.out.println("Db not connected");
        }
        ordernoCol.setCellValueFactory(new PropertyValueFactory<>("orderid"));
        custCol.setCellValueFactory(new PropertyValueFactory<>("custid"));
        menuCol.setCellValueFactory(new PropertyValueFactory<>("menuname"));
         QuantityCol.setCellValueFactory(new PropertyValueFactory<>("qnt"));
        deliveryCol.setCellValueFactory(new PropertyValueFactory<>("deliverytyp"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));
       
        tableConnection();
        table.setItems(obList);
        table.refresh();
        table.getSelectionModel().clearSelection();
        //*****************************************
         
        
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
public void tableConnection(){
        
        try {
             
            String query="{CALL `order_list`()}";
            CallableStatement stmt = con.prepareCall(query);
            
            ResultSet rs =stmt.executeQuery(query);
            while(rs.next()){
                obList.add(new ModelTable(rs.getInt("order_id"),rs.getInt("id"),rs.getString("menu_name"),rs.getString("payment_type"), rs.getString("Address"), rs.getInt("Qnt")) );
            }
        } catch (SQLException ex) {
            Logger.getLogger(TakeOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public void DeliverItem(ActionEvent event){
       ModelTable tableIndex = (ModelTable)table.getSelectionModel().getSelectedItem();
       int tempOrderid = -1;
 
        
       try{
       tempOrderid = tableIndex.getOrderid();
       }catch(Exception e){
           infoBox1("no item selected!", null, "Error");
           
       }
   
    if(tempOrderid >= 0){
        String query = "UPDATE orders SET order_status='DELIVERED' WHERE order_id=?";  
        PreparedStatement pst;
           try {  
                     
        Image img =new  Image("/success.png");
        Notifications notif =Notifications.create()
                .title("Abonnement Validé Avec Succès")
                .text("Vouler vous consulter la facture ?  ")
                .graphic(new ImageView (img))
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_LEFT)
                .onAction(new  EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
  System.out.println("Clicked on :)");
            }
        });
         notif.darkStyle();
        notif.show();  
               pst = con.prepareStatement(query);
               pst.setInt(1, tempOrderid);
               pst.execute();
               infoBox1("Demande Confirméé",null, "Success");
               table.getItems().remove(tableIndex);
               table.refresh();
               table.getSelectionModel().clearSelection();
        
              
           } catch (SQLException ex) {
               Logger.getLogger(TakeOrderController.class.getName()).log(Level.SEVERE, null, ex);
           }catch(Exception e){
               infoBox1("Aucune demande selectionnée!", null, "Error");
           }
               
       
    } else {
        System.out.println("Aucune selction !");
    }
}
      public static boolean infoBox(String infoMessage, String headerText, String title){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.getButtonTypes();
        
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            // ... user chose OK button
         return true;
        } else {
        // ... user chose CANCEL or closed the dialog
        return false;
        }
        
    }
     
     public static void infoBox1(String infoMessage, String headerText, String title){
         Alert alert = new Alert(Alert.AlertType.INFORMATION);
         alert.setTitle(title);
         alert.setHeaderText(headerText);
         alert.setContentText(infoMessage);
         alert.showAndWait();
     }
  @FXML   public  void Logout (ActionEvent event) throws IOException
    {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "/AdminServices/AdminMain.fxml", "Interface Admin ");
 
     }
    
     
    @FXML public void onButtonReport()
    {   int numOrdre = table.getSelectionModel().getSelectedItem().getOrderid();
        int eleveID = table.getSelectionModel().getSelectedItem().getCustid();
        String nomService = table.getSelectionModel().getSelectedItem().getMenuname();
        int nbMois = table.getSelectionModel().getSelectedItem().getQnt();
        String typeDelivery = table.getSelectionModel().getSelectedItem().getDeliverytyp();
        String adresse = table.getSelectionModel().getSelectedItem().getAddress();
       
        
        try {
                          
      
        Image img =new  Image("/success.png");
        Notifications notif =Notifications.create()
                .title("Facture Préparée Avec Succès")
                .text("Vous Pouvez L'imprimer  ")
                .graphic(new ImageView (img))
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_LEFT)
                .onAction(new  EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
  System.out.println("Clicked on :)");
            }
        });
        notif.darkStyle();
        notif.show();  
        
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Report.fxml"));
            
             // Create a controller instance
 
         ReportController controller = new ReportController(numOrdre, eleveID, nomService, nbMois,typeDelivery,adresse);
            
            // Set it in the FXMLLoader
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Facture");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            System.out.println("TRYING!");
        } catch (IOException e) {
            // ignore
            e.printStackTrace();
        }
    }
    
//-----------------------------------------------------------------------------------
  @FXML   public  void Email (ActionEvent event) throws IOException
  {          Image img =new  Image("/success.png");
        Notifications notif =Notifications.create()
                .title("Email Envoyé Avec Succès")
                .text("L'élève recevra votre email  ")
                .graphic(new ImageView (img)) 
                .hideAfter(Duration.seconds(2))
                .position(Pos.TOP_LEFT)
                .onAction(new  EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             System.out.println("Clicked on :");
            }
          });
         SendMail2.sendMail("jaweher.bensalah@esprit.tn", "MySchool "," Votre Inscription a été effectuée avec succés... A bientôt");
         notif.darkStyle();
        notif.show();  
       
     }    
  @FXML   public  void Statistique (ActionEvent event) throws IOException
    {
        SceneChanger sc = new SceneChanger();
        sc.changeScenes(event, "/AdminServices/statistiqueInscriptions.fxml", "Statistique Elève ");
 
     }
    
//**************************************
  


 
    @FXML
    private void onButtonReport(ActionEvent event) {
        int numOrdre = table.getSelectionModel().getSelectedItem().getOrderid();
        int eleveID = table.getSelectionModel().getSelectedItem().getCustid();
        String nomService = table.getSelectionModel().getSelectedItem().getMenuname();
        int nbMois = table.getSelectionModel().getSelectedItem().getQnt();
        String typeDelivery = table.getSelectionModel().getSelectedItem().getDeliverytyp();
        String adresse = table.getSelectionModel().getSelectedItem().getAddress();
       
        
        try {
                          
      
        Image img =new  Image("/success.png");
        Notifications notif =Notifications.create()
                .title("Facture Préparée Avec Succès")
                .text("Vous Pouvez L'imprimer  ")
                .graphic(new ImageView (img))
                .hideAfter(Duration.seconds(3))
                .position(Pos.TOP_LEFT)
                .onAction(new  EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
  System.out.println("Clicked on :)");
            }
        });
        notif.darkStyle();
        notif.show();  
        
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Report.fxml"));
            
             // Create a controller instance
 
         ReportController controller = new ReportController(numOrdre, eleveID, nomService, nbMois,typeDelivery,adresse);
            
            // Set it in the FXMLLoader
            fxmlLoader.setController(controller);
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Facture");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            System.out.println("TRYING!");
        } catch (IOException e) {
            // ignore
            e.printStackTrace();
        }
    }

  

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
    private void inscriptionButttonAction(ActionEvent event) {
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
    private void initProfil() {
        String p = pidevfinal.ConnecterController.n;
        String v = "deconnexion";
        String o = "profil";
        liste_profil.addAll(p, o, v);
        deconnexion.setItems(liste_profil);
    }
}


