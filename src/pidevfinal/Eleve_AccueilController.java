/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfinal;
import com.itextpdf.text.Image;
import Entites.EmploiDuTemps;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pi.AfficheNotesController;
import pi.BulletinController;
import service.AbsenceService;
import service.EleveService;
import service.EmploiService;
import service.PunitionService;
import ServicesScolarite.SceneChanger;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class Eleve_AccueilController implements Initializable {

    @FXML
    private AnchorPane rootPane;
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
    private Button restuarant1;
    @FXML
    private Button restuarant2;
    @FXML
    private MenuButton resultat;
    @FXML
    private Label num;

    
    /**
     * Initializes the controller class.
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
    }    

    @FXML
    private void DeconnexionAction(ActionEvent event) throws IOException {
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
              Stage first = (Stage)user.getScene().getWindow();
              first.close();     
    }

    @FXML
    private void AccueilAction(ActionEvent event) {
    }

   

    @FXML
    private void afficherAbsenceAction(ActionEvent event) throws IOException {
         AnchorPane pane = FXMLLoader.load(getClass().getResource("Absence_Eleve.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void afficherPunitionAction(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("Punition_Eleve.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @FXML
    private void afficherReclamationAction(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/reclamations/AfficherMessages.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)user.getScene().getWindow();
              first.close();
    }

    @FXML
    private void afficherTransportAction(ActionEvent event) throws IOException {
        SceneChanger sc = new SceneChanger();
        
        //check if it is an admin user and go to the table view
        //if (SceneChanger.getLoggedInUser().isAdmin())
            sc.changeScenes(event,"/StudentServices/ListServices.fxml", "All Menues");
    }

    @FXML
    private void afficherRestaurantAction(ActionEvent event) throws IOException {
        SceneChanger sc = new SceneChanger();
        
        //check if it is an admin user and go to the table view
        //if (SceneChanger.getLoggedInUser().isAdmin())
            sc.changeScenes(event, "/StudentServices/FXMLDocument.fxml", "All Menues");
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
              Stage first = (Stage)user.getScene().getWindow();
              first.close();
    }

    @FXML
    private void AfficherEmploisAction(ActionEvent event) throws DocumentException  {
          String nom = ConnecterController.n; 
          EleveService elservice = new EleveService();
   String listeleve = elservice.findByClasse(nom);
            System.out.println(nom);
            num.setText(listeleve);
      Document doc = new Document();  
     
           
        try {
            PdfWriter.getInstance(doc ,new FileOutputStream("C:\\Users\\USER\\Desktop\\Test2.pdf"));
            doc.open();
           
            try {
                
                Image img1 = Image.getInstance("C:\\Users\\USER\\Desktop\\EYA\\logo.png");
                img1.scaleAbsoluteWidth(50);
                img1.scaleAbsoluteHeight(50);
                img1.setAlignment(Image.ALIGN_TOP);
               img1.setAlignment(Image.ALIGN_LEFT);
                doc.add(img1);
                 Paragraph p = new Paragraph("Année universitaire : 2019/2020");
          // doc.add(new Paragraph("Emploid su temps ")); 
          p.setAlignment(Paragraph.ALIGN_RIGHT);
          Paragraph a = new Paragraph("Emploi du temps classe : "+listeleve);
         a.setAlignment(Paragraph.ALIGN_CENTER);          
           doc.add(p);
             doc.add(new Paragraph("  "));
                        doc.add(a);
                           doc.add(new Paragraph("  "));
                PdfPTable table = new PdfPTable(6);
                table.setWidthPercentage(100);
                
                PdfPCell cell; 
                
                cell = new PdfPCell (new Phrase(" ",FontFactory.getFont("Comic Sans MS ",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.PINK);
                table.addCell(cell);
                
                cell = new PdfPCell (new Phrase("8h=>10h  ",FontFactory.getFont("Comic Sans MS ",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.PINK);
                table.addCell(cell);
              
                
                cell = new PdfPCell (new Phrase("10h=>12h  ",FontFactory.getFont("Comic Sans MS ",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.PINK);
                table.addCell(cell);
                
                cell = new PdfPCell (new Phrase("12h=>14h  ",FontFactory.getFont("Comic Sans MS ",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.PINK);
                table.addCell(cell);
                
                    
                cell = new PdfPCell (new Phrase("14h=>16h  ",FontFactory.getFont("Comic Sans MS ",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.PINK);
                table.addCell(cell);
                
                 cell = new PdfPCell (new Phrase("16h=>18h  ",FontFactory.getFont("Comic Sans MS ",12)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.PINK);
                table.addCell(cell);
          

                        
                //**************************
            
          
               EmploiService ccc = new EmploiService();
        String aa = "Lundi "; 
        String cc = "Mardi " ; 
        String d = "Mercredi"; 
        String e = "Jeudi";
        String F = "Vendredi"; 
      List<EmploiDuTemps> emp = (ArrayList<EmploiDuTemps>) ccc.displayEmploi();
      for (int i=0;i<emp.size();i++) {
          if(emp.get(i).getClasse().equals(listeleve)) {
                   if(emp.get(i).getJour().equals(aa)) {
                                          table.addCell(aa);
                     table.addCell(emp.get(i).getMatiere());
           
                table.addCell(emp.get(i).getMatiere1());
                  table.addCell("pause dej ");             
                
                  // if (emp.get(i).getHeure2().equals("14h=>16h")) {
                       table.addCell(emp.get(i).getMatiere2());
                     
                        table.addCell(emp.get(i).getMatiere3());  
                          

                   } 
                   
                   
                   else  if(emp.get(i).getJour().equals(cc)) {
                                          table.addCell(cc);
                     table.addCell(emp.get(i).getMatiere());
           
                table.addCell(emp.get(i).getMatiere1());
                  table.addCell("pause dej ");             
                
                  // if (emp.get(i).getHeure2().equals("14h=>16h")) {
                       table.addCell(emp.get(i).getMatiere2());
                     
                        table.addCell(emp.get(i).getMatiere3());  
                          

                        
                   } else  if(emp.get(i).getJour().equals(d)) {
                                          table.addCell(d);
                     table.addCell(emp.get(i).getMatiere());
           
                table.addCell(emp.get(i).getMatiere1());
                  table.addCell("pause dej ");             
                
                  // if (emp.get(i).getHeure2().equals("14h=>16h")) {
                       table.addCell(emp.get(i).getMatiere2());
                     
                        table.addCell(emp.get(i).getMatiere3());  
                          

                   } 
                   
                   else  if(emp.get(i).getJour().equals(e)) {
                                          table.addCell(e);
                     table.addCell(emp.get(i).getMatiere());
           
                table.addCell(emp.get(i).getMatiere1());
                  table.addCell("pause dej ");             
                
                  // if (emp.get(i).getHeure2().equals("14h=>16h")) {
                       table.addCell(emp.get(i).getMatiere2());
                     
                        table.addCell(emp.get(i).getMatiere3());  
                          

                   } else  if(emp.get(i).getJour().equals(F)) {
                                          table.addCell(F);
                     table.addCell(emp.get(i).getMatiere());
           
                table.addCell(emp.get(i).getMatiere1());
                  table.addCell("pause dej ");             
                
                  // if (emp.get(i).getHeure2().equals("14h=>16h")) {
                       table.addCell(emp.get(i).getMatiere2());
                     
                        table.addCell(emp.get(i).getMatiere3());  
                          

                   } 
                        }   
              }
             
                  
            
           
      
        
            
              
           doc.add(table);
           doc.close();
               Desktop.getDesktop().open(new File ("C:\\Users\\USER\\Desktop\\Test.pdf"));

            } catch (BadElementException ex) {
                Logger.getLogger(DirecteurAdminController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(DirecteurAdminController.class.getName()).log(Level.SEVERE, null, ex);
            }
               
           
           
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DirecteurAdminController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    @FXML
    private void afficherNotes(ActionEvent event) {
        
    }

    @FXML
    private void displayNote(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/pi/afficheNotes.fxml"));
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
    private void displayBulletin(ActionEvent event) throws IOException {
            FXMLLoader loader=new FXMLLoader();
        loader.setLocation(getClass().getResource("/pi/Bulletin.fxml"));
        Parent afficheNotes =loader.load();
        Scene affichage=new Scene(afficheNotes);
        
        BulletinController controller = loader.getController();
        controller.afficheResultatAction();;
        
         Stage window =(Stage)((Node)event.getSource()).getScene().getWindow();
         window.setScene(affichage);
         window.show();
    }
    
}
