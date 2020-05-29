/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.mail.Transport;
import models.Menu;
import pidevfinal.Directeur_dashboardController;
import pidevfinal.PidevFinal;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class NewMenuViewController implements Initializable , ControllerClass {

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
    private ImageView me;
    @FXML
    private Button men;
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
    private ComboBox<String> deconnexion;
    @FXML
    private TextField prixTextField;
    @FXML
    private TextField itemsTextField;
    @FXML
    private ImageView imageView;
private File imageFile;
private boolean imageFileChanged;
    ObservableList<String> liste_profil = FXCollections.observableArrayList();

private Menu menu;
     
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         imageFileChanged = false; //initially the image has not changed, use the default
        
       // errMsgLabel.setText("");    //set the error message to be empty to start
        
        //load the defautl image for the avatar
        try{
            imageFile = new File("./src/images/defaultPerson.png");
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            Image image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(image);
            
        }
        catch (IOException e)
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
    private void saveMenuButtonPushed(ActionEvent event) {
        try
            {
                if (menu != null) //we need to edit/update an existing menu
                {
                    updateMenu();
                    menu.updateMenuInDB();
                }
                else    //we need to create a new menu
                {
                    if (imageFileChanged) //create a Menu with a custom image
                    {
                        menu = new Menu(itemsTextField.getText(),prixTextField.getText(), imageFile);
                                              
                    }
                    else  //create a Menu with a default image
                    {
                        menu = new Menu(itemsTextField.getText(),prixTextField.getText());
                    }
                  //  errMsgLabel.setText("");    //do not show errors if creating Menu was successful
                    menu.insertIntoDB();    
                }

                SceneChanger sc = new SceneChanger();
                sc.changeScenes(event, "MenuTableView.fxml", "Tous les menus :");
            }
            catch (Exception e)
            {
              //  errMsgLabel.setText(e.getMessage());
            }
        
    }

    @FXML
    private void chooseImageButtonPushed(ActionEvent event) {
      //get the Stage to open a new window (or Stage in JavaFX)
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        //Instantiate a FileChooser object
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Image");
        
        //filter for .jpg and .png
       // FileChooser.ExtensionFilter jpgFilter = new FileChooser.ExtensionFilter("Image File (*.jpg)", "*.jpg");
       // FileChooser.ExtensionFilter pngFilter = new FileChooser.ExtensionFilter("Image File (*.png)", "*.png");
       // fileChooser.getExtensionFilters().addAll(jpgFilter, pngFilter);
        
        //Set to the user's picture directory or user directory if not available
        String userDirectoryString = System.getProperty("user.home")+"\\Pictures";
        File userDirectory = new File(userDirectoryString);
        
        //if you cannot navigate to the pictures directory, go to the user home
        if (!userDirectory.canRead())
            userDirectory = new File(System.getProperty("user.home"));
        
        fileChooser.setInitialDirectory(userDirectory);
        
        //open the file dialog window
        File tmpImageFile = fileChooser.showOpenDialog(stage);
        
        if (tmpImageFile != null)
        {
            imageFile = tmpImageFile;
        
            //update the ImageView with the new image
            if (imageFile.isFile())
            {
                try
                {
                    BufferedImage bufferedImage = ImageIO.read(imageFile);
                    Image img = SwingFXUtils.toFXImage(bufferedImage, null);
                    imageView.setImage(img);
                    imageFileChanged = true;
                }
                catch (IOException e)
                {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
    /**
     * This method will update the view with a Menu object preloaded for an edit
     * @param menu 
     */
    @Override
    public void preloadData(Menu menu) {
        this.menu = menu;
        this.itemsTextField.setText(menu.getItems());
        this.prixTextField.setText(menu.getPrix());
      //  this.birthday.setValue(menu.getBirthday());
      //  this.phoneTextField.setText(menu.getPhoneNumber());
       // this.headerLabel.setText("Edit Menu");
    
        //load the image : nwarriw l'mage le9dima
        //w baaed nbadlouha
        try{
            String imgLocation = ".\\src\\images\\" + menu.getPhoto().getName();
            imageFile = new File(imgLocation);
            BufferedImage bufferedImage = ImageIO.read(imageFile);
            Image img = SwingFXUtils.toFXImage(bufferedImage, null);
            imageView.setImage(img);
        }
        catch (IOException e)   
        {
            System.err.println(e.getMessage()); 
        }
    }
//------------------------------------------------------------------------------
    /**
     * This method will read from the GUI fields and update the menu object
     */
    
    public void updateMenu() throws IOException
    {
        menu.setItems(itemsTextField.getText());
        menu.setPrix(prixTextField.getText());
      //  menu.setPhoneNumber(phoneTextField.getText());
       // menu.setBirthday(birthday.getValue());
        menu.setPhoto(imageFile);
        menu.copyImageFile();
    }
   
 private void initProfil() {
        String p = pidevfinal.ConnecterController.n;
        String v = "deconnexion";
        String o = "profil";
        liste_profil.addAll(p, o, v);
        deconnexion.setItems(liste_profil);
    }

    @Override
    public void preloadData(models.Transport transport) {
        
    }
}
