/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfinal;

import Entites.Absence;
import Entites.Classe;
import Entites.Eleve;
import Entites.EmploiDuTemps;
import Entites.Enseignants;
import Entites.Punition;
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
import com.itextpdf.text.Image;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.AbsenceService;
import service.ClasseService;
import service.EleveService;
import service.EmploiService;
import service.EnseignantsService;
import service.PunitionService;
import util.SendMail;

/**
 * FXML Controller class
 *
 * @author USER
 */
public class DirecteurAdminController implements Initializable {
 ObservableList<String> choice = FXCollections.observableArrayList();
    ObservableList<String> liste_profil = FXCollections.observableArrayList();
    ObservableList<Classe> liste_Classe;
    ObservableList<Eleve> liste_Eleve;
    ObservableList<Enseignants> liste_Enseignant;
    ObservableList<Punition> liste_Punition;
    ObservableList<Absence> liste_Absences;
    public static TableView<Classe> liste_2;
    public static TableView<Absence> listAbsence_2;
    public static TableView<Punition> punition;
        public static TableView<Eleve> list_eleve2;
        public static TableView<Enseignants> list_ens2;
         public static TableView<Classe> list_class;
    @FXML
    private AnchorPane rootPane;
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
    private ImageView cl;
    @FXML
    private Button ajouterCla;
    @FXML
    private Button stat;
    @FXML
    private ImageView pu;
    @FXML
    private Button AjouterPun;
    @FXML
    private Button chercher;
    @FXML
    private TextField nom;
    @FXML
    private TableView<Classe> classes;
    @FXML
    private TableColumn<Classe, String> ic_col;
    @FXML
    private TableColumn<Classe, String> nom_col;
    @FXML
    private TableColumn<Classe, String> niveau_col;
    @FXML
    private TableColumn<Classe, Button> action_col;
    @FXML
    private Button supprimer;
    @FXML
    private Label msg;
    @FXML
    private Label msgg;
    @FXML
    private ComboBox<String> choix;
    @FXML
    private TableView<Eleve> eleves;
    @FXML
    private TableColumn<Eleve, String> id_eleve;
    @FXML
    private TableColumn<Eleve, String> nom_eleve;
    @FXML
    private TableColumn<Eleve, String> email_eleve;
    @FXML
    private TableColumn<Eleve, String> classe_eleve;
    @FXML
    private TableView<Enseignants> enseignants;
    @FXML
    private TableColumn<Enseignants, String> id_ens;
    @FXML
    private TableColumn<Enseignants, String> nom_ens;
    @FXML
    private TableColumn<Enseignants, String> email_ens;
    @FXML
    private TableColumn<Enseignants, String> classe_ens;
    @FXML
    private TableView<Punition> punitions;
    @FXML
    private TableColumn<Punition, String> id_puns;
    @FXML
    private TableColumn<Punition, String> nom_puns;
    @FXML
    private TableColumn<Punition, String> classe_puns;
    @FXML
    private TableColumn<Punition, String> type_puns;
    @FXML
    private TableColumn<Punition, String> date_puns;
    @FXML
    private TableColumn<Punition, String> action_puns;
    @FXML
    private TableView<Absence> absences;
    @FXML
    private TableColumn<Absence, String> id_absence;
    @FXML
    private TableColumn<Absence, String> nomEns_absence;
    @FXML
    private TableColumn<Absence, String> nomele_absence;
    @FXML
    private TableColumn<Absence, String> classe_absence;
    @FXML
    private TableColumn<Absence, String> matiere_absence;
    @FXML
    private TableColumn<Absence, String> heure_absence;
    @FXML
    private TableColumn<Absence, String> date_absence;
    @FXML
    private TableColumn<Absence, String> actionAbsence_col;
    @FXML
    private Pagination pagination;
    @FXML
    private ComboBox<String> deconnexion;
    @FXML
    private Button supprimer1;
    @FXML
    private Button supprimer2;
    @FXML
    private Button trie;
    @FXML
    private Button chercher1;
    @FXML
    private Button chercher2;
    @FXML
    private TextField nom_punition;
    @FXML
    private TextField nom_bsence;
    @FXML
    private Button emplois;
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
    private ImageView ens;
    @FXML
    private Button evenement;
    @FXML
    private ImageView ev;
     @FXML
    private ImageView ms;
    @FXML
    private Button menu;
    @FXML
    private ImageView me;

       int form = 0; 
    int to = 0; 
    int itemPage = 3; 
        ObservableList<Absence> list  = FXCollections.observableArrayList();

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        stat.setVisible(false);
                initEnseignant();
                int count = 0; 
                chercherav();
AbsenceService m = new AbsenceService(); 
count = m.calculerAbsence(); 
int pagecount = (count/itemPage)+1; 
pagination.setPageCount(pagecount);
pagination.setPageFactory(this::createPage);
emplois.setVisible(false);
        
        list_ens2 = enseignants;
        list_eleve2 = eleves;
        listAbsence_2 = absences;
        classes.setVisible(false);
        eleves.setVisible(false);
        enseignants.setVisible(false);
        punitions.setVisible(false);
        absences.setVisible(false);
        supprimer.setVisible(false);
        supprimer1.setVisible(false);
        supprimer2.setVisible(false);
        msg.setVisible(false);
        nom.setVisible(false);
        chercher.setVisible(false);
        chercher1.setVisible(false);
        chercher2.setVisible(false);
        nom_bsence.setVisible(false);
        nom_punition.setVisible(false);
        trie.setVisible(false);
        pagination.setVisible(false);
        loadData();
        initanle();
        initEleve();
        initEnseignant();
        initPunishment();
        initAbsence();
        initProfil();
        liste_2 = classes;
        punition = punitions;

        // if (choix.getSelectionModel().getSelectedItem() == "afficher classes") {
        ClasseService cla = new ClasseService();
        int a = cla.calculer();
        String x = Integer.toString(a);
        msg.setText("Liste des classes : " + x);
        // afficher les images 
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
        cl.setImage(Directeur_dashboardController.img12);
        pu.setImage(Directeur_dashboardController.img13);
    }    

    @FXML
    private void inscriptionButttonAction(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("/pidevfinal/AjoutAbsence.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
        //  AnchorPane pane = FXMLLoader.load(getClass().getResource("AjoutAbsence.fxml"));
        //rootPane.getChildren().setAll(pane); 
    }

    @FXML
    private void AffecterEleveAction(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("AffecterEleve.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
        //    AnchorPane pane = FXMLLoader.load(getClass().getResource("AffecterEleve.fxml"));
        // rootPane.getChildren().setAll(pane); 
    }

    @FXML
    private void AjouterAbsenceAction(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("AffecterEnseignant.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
        //AnchorPane pane = FXMLLoader.load(getClass().getResource("AffecterEnseignant.fxml"));
        //rootPane.getChildren().setAll(pane);   
    }

    @FXML
    private void AjouterClassesAction(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
        // AnchorPane pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        //rootPane.getChildren().setAll(pane); 
    }
@FXML
    private void statAction(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("statistiqueAbsence.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)nom.getScene().getWindow();
              first.close();
    } 
    
    @FXML
    private void AjouterPunitionAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ajouterPunition.fxml"));
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        String css = PidevFinal.class.getResource("styleAjout.css").toExternalForm();
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
        // AnchorPane pane = FXMLLoader.load(getClass().getResource("ajouterPunition.fxml"));
        //rootPane.getChildren().setAll(pane);  
    }

    @FXML
    private void findByNomAction(ActionEvent event) {
          String cla = nom.getText();
        ClasseService c = new ClasseService();
        Classe b = new Classe();

        b = c.findByNom(nom.getText());
        if (b != null) {
            //   msg.setText(b.getNiveaux());
            liste_Classe = FXCollections.observableArrayList(b);
            ic_col.setCellValueFactory(new PropertyValueFactory<>("idClasse"));
            nom_col.setCellValueFactory(new PropertyValueFactory<>("nom"));
            niveau_col.setCellValueFactory(new PropertyValueFactory<>("niveaux"));
            action_col.setCellValueFactory(new PropertyValueFactory<>("modifier"));
            classes.setItems(liste_Classe);
        }
    }

    @FXML
    private void supprimeClasseAction(ActionEvent event) {
        ObservableList<Classe> classe, allclasse;
        ClasseService c = new ClasseService();
        allclasse = classes.getItems();
        classe = classes.getSelectionModel().getSelectedItems();
        for (Classe cl : classe) {
            c.deleteClasse(cl);
            classe.forEach(allclasse::remove);
        }
    }

    @FXML
    private void changer_contenu(ActionEvent event) {
           String m = choix.getValue();
        String c = "afficher classes ";
        String a = "afficher eleve ";
        String d = "afficher enseignants ";
        String e = "afficher punitions ";
        String b = "afficher absences ";
        if (m.equals(c)) {
                    pagination.setVisible(false);
            trie.setVisible(false);
                    stat.setVisible(true);
            eleves.setVisible(false);
            punitions.setVisible(false);
            System.out.println(c);
            classes.setVisible(true);
            enseignants.setVisible(false);
            absences.setVisible(false);
            supprimer.setVisible(true);
            supprimer1.setVisible(false);
            msg.setVisible(true);
            supprimer2.setVisible(false);
            nom.setVisible(true);
            chercher.setVisible(true);
            chercher1.setVisible(false);
            chercher2.setVisible(false);
            nom_bsence.setVisible(false);
            nom_punition.setVisible(false);
            emplois.setVisible(true);

        } else if (m.equals(a)) {
                    stat.setVisible(false);

                    pagination.setVisible(false);
            trie.setVisible(false);
            supprimer1.setVisible(false);
            classes.setVisible(false);
            enseignants.setVisible(false);
            eleves.setVisible(true);
            punitions.setVisible(false);
            absences.setVisible(false);
            supprimer.setVisible(false);
            msg.setVisible(false);
            supprimer2.setVisible(false);
            nom.setVisible(false);
            chercher.setVisible(false);
            chercher1.setVisible(false);
            chercher2.setVisible(false);
            nom_bsence.setVisible(false);
            nom_punition.setVisible(false);
            emplois.setVisible(false);

        } else if (m.equals(d)) {
                    stat.setVisible(false);

            pagination.setVisible(false);
            trie.setVisible(false);
            supprimer1.setVisible(false);
            classes.setVisible(false);
            eleves.setVisible(false);
            enseignants.setVisible(true);
            punitions.setVisible(false);
            absences.setVisible(false);
            supprimer.setVisible(false);
            msg.setVisible(false);
            supprimer2.setVisible(false);
            nom.setVisible(false);
            chercher.setVisible(false);
            chercher1.setVisible(false);
            chercher2.setVisible(false);
            nom_bsence.setVisible(false);
            nom_punition.setVisible(false);
            emplois.setVisible(false);
        } else if (m.equals(e)) {
                    stat.setVisible(false);

                    pagination.setVisible(false);
            trie.setVisible(false);
            supprimer1.setVisible(false);
            classes.setVisible(false);
            eleves.setVisible(false);
            enseignants.setVisible(false);
            punitions.setVisible(true);
            absences.setVisible(false);
            supprimer.setVisible(false);
            msg.setVisible(false);
            supprimer2.setVisible(true);
            nom.setVisible(false);
            chercher.setVisible(false);
            chercher1.setVisible(false);
            chercher2.setVisible(true);
            nom_bsence.setVisible(false);
            nom_punition.setVisible(true);
            emplois.setVisible(false);

        } else if (m.equals(b)) {
                    stat.setVisible(false);

            trie.setVisible(true);
        pagination.setVisible(true);
            supprimer1.setVisible(true);
            classes.setVisible(false);
            eleves.setVisible(false);
            enseignants.setVisible(false);
            punitions.setVisible(false);
            absences.setVisible(true);
            supprimer.setVisible(false);
            msg.setVisible(false);
            supprimer2.setVisible(false);
            nom.setVisible(false);
            chercher.setVisible(false);
            chercher1.setVisible(true);
            chercher2.setVisible(false);
            nom_bsence.setVisible(true);
            nom_punition.setVisible(false);
            emplois.setVisible(false);
        }
    }

    @FXML
    private void deconnexionAction(ActionEvent event) throws IOException {
         String p = ConnecterController.n;
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
              Stage first = (Stage)nom.getScene().getWindow();
              first.close();
        }
    }

    @FXML
    private void supprimerAbsenceAction(ActionEvent event) {
         ObservableList<Absence> absence, allabsence;
        AbsenceService c = new AbsenceService();
        allabsence = absences.getItems();
        absence = absences.getSelectionModel().getSelectedItems();
        for (Absence cl : absence) {
            c.deleteAbsence(cl.getId());
            absence.forEach(allabsence::remove);
        }
    }

    @FXML
    private void supprimerPunitionAction(ActionEvent event) {
         ObservableList<Punition> puni, allpunition;
        PunitionService c = new PunitionService();
        allpunition = punitions.getItems();
        puni = punitions.getSelectionModel().getSelectedItems();
        for (Punition cl : puni) {
            c.deletePunition(cl.getId());
            puni.forEach(allpunition::remove);
        }
    }

    @FXML
    private void trieAction(ActionEvent event) {
          AbsenceService c = new AbsenceService();
        List<Absence> listAbsence = c.displayTrie();
        for (int i = 0; i < listAbsence.size(); i++) {
            liste_Absences = FXCollections.observableArrayList(listAbsence);
        }
        id_absence.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomEns_absence.setCellValueFactory(new PropertyValueFactory<>("ens"));
        nomele_absence.setCellValueFactory(new PropertyValueFactory<>("eleve"));
        classe_absence.setCellValueFactory(new PropertyValueFactory<>("classe"));
        matiere_absence.setCellValueFactory(new PropertyValueFactory<>("matier"));
        heure_absence.setCellValueFactory(new PropertyValueFactory<>("heure"));
        date_absence.setCellValueFactory(new PropertyValueFactory<>("date"));
        actionAbsence_col.setCellValueFactory(new PropertyValueFactory<>("modifier"));
        absences.setItems(liste_Absences);
        editAbsencecol();
    }

    @FXML
    private void findByAbsence(ActionEvent event) {
         String cla = nom_bsence.getText();
        AbsenceService c = new AbsenceService();
        Absence a = new Absence();
        a = c.findByNom(cla);
        if (a != null) {
            liste_Absences = FXCollections.observableArrayList(a);
            id_absence.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomEns_absence.setCellValueFactory(new PropertyValueFactory<>("ens"));
            nomele_absence.setCellValueFactory(new PropertyValueFactory<>("eleve"));
            classe_absence.setCellValueFactory(new PropertyValueFactory<>("classe"));
            matiere_absence.setCellValueFactory(new PropertyValueFactory<>("matier"));
            heure_absence.setCellValueFactory(new PropertyValueFactory<>("heure"));
            date_absence.setCellValueFactory(new PropertyValueFactory<>("date"));
            actionAbsence_col.setCellValueFactory(new PropertyValueFactory<>("modifier"));
            absences.setItems(liste_Absences);
        }
      
    }
        
        public void chercherav() { 
                    // Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Absence> filteredData = new FilteredList<>(list, b -> true);
		
		// 2. Set the filter Predicate whenever the filter changes.
		nom_bsence.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(groupe -> {
				// If filter text is empty, display all persons.
								
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = newValue.toLowerCase();
				
				//if (groupe.getEns().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
				//	return true; // Filter matches first name.
				//} else
                                if (String.valueOf(groupe.getEns()).indexOf(lowerCaseFilter)!=-1 )
				     return true;
				     else  
				    	 return false; // Does not match.
			});
		});
		
		// 3. Wrap the FilteredList in a SortedList. 
		SortedList<Absence> sortedData = new SortedList<>(filteredData);
		
		// 4. Bind the SortedList comparator to the TableView comparator.
		// 	  Otherwise, sorting the TableView would have no effect.
		sortedData.comparatorProperty().bind(absences.comparatorProperty());
		
		// 5. Add sorted (and filtered) data to the table.
		absences.setItems(sortedData);
        }
        
        
    

    @FXML
    private void findbyPunition(ActionEvent event) {
           String cla = nom_punition.getText();
        PunitionService c = new PunitionService();
        Punition b = new Punition();

        b = c.findByNom(cla);
        if (b != null) {
            //   msg.setText(b.getNiveaux());
            liste_Punition = FXCollections.observableArrayList(b);
            id_puns.setCellValueFactory(new PropertyValueFactory<>("id"));
            nom_puns.setCellValueFactory(new PropertyValueFactory<>("eleve"));
            classe_puns.setCellValueFactory(new PropertyValueFactory<>("classe"));
            type_puns.setCellValueFactory(new PropertyValueFactory<>("cause"));
            date_puns.setCellValueFactory(new PropertyValueFactory<>("date"));
            action_puns.setCellValueFactory(new PropertyValueFactory<>("modifier"));
            punitions.setItems(liste_Punition);
        }
    }

    @FXML
    private void detailAction(ActionEvent event) throws DocumentException, IOException {
        ObservableList<Classe> classe, allclasse;
        ClasseService c = new ClasseService();
        allclasse = classes.getItems();
        classe = classes.getSelectionModel().getSelectedItems();
         for (Classe cl : classe) {        
        Document doc = new Document();  
       
        
        try {
            PdfWriter.getInstance(doc ,new FileOutputStream("C:\\Users\\USER\\Desktop\\Test.pdf"));
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
          Paragraph a = new Paragraph("Emploi du temps classe : "+cl.getNom());
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
          if(emp.get(i).getClasse().equals(cl.getNom())) {
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
           //SendMail.sendMail("eyamidou@yahoo.fr", " confirmation Participation Event ","C:\\Users\\USER\\Desktop\\EYA\\logo.png");
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
  
 SendMail demo = new SendMail();
       // demo.sendEmail("C:\\Users\\USER\\Desktop\\Test.pdf");
    }

    @FXML
    private void dashboardButtonAction(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("Directeur_dashboard.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)nom.getScene().getWindow();
              first.close();
    }

    @FXML
    private void enseignantButtonAction(ActionEvent event) throws IOException {
   FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("admin_enseignant.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)nom.getScene().getWindow();
              first.close();
    }

    @FXML
    private void RésultatsButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AjoutEmploi.fxml"));
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
              loader.setLocation(PidevFinal.class.getResource("/ServicesScolarite/TransportTableView.fxml"));
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
         FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/reclamations/ReclamationAdmin.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)nom.getScene().getWindow();
              first.close();
    }

    @FXML
    private void evenementButtonAction(ActionEvent event) throws IOException {
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
              Stage first = (Stage)nom.getScene().getWindow();
              first.close();
    }

    @FXML
    private void menuButtonAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
              loader.setLocation(PidevFinal.class.getResource("/ServicesScolarite/MenuTableView.fxml"));
              Parent root = null; 
              try {
                  root = loader.load(); 
              } catch(IOException ex){} 
              Scene sc = new Scene(root); 
              Stage second = new Stage();
              second.setScene(sc);
              second.show();
              Stage first = (Stage)nom.getScene().getWindow();
              first.close();
    }
      private void loadData() {
        choice.removeAll(choice);
        String a = "afficher eleve ";
        String b = "afficher enseignants ";
        String c = "afficher classes ";
        String d = "afficher punitions ";
        String e = "afficher absences ";
        choice.addAll(a, b, c, d, e);
        choix.setItems(choice);
    }
       private void initanle() {
        ClasseService elService = new ClasseService();
        List<Classe> listclasses = elService.displayClasse();
        for (int i = 0; i < listclasses.size(); i++) {
            liste_Classe = FXCollections.observableArrayList(listclasses);
        }
        ic_col.setCellValueFactory(new PropertyValueFactory<>("idClasse"));
        nom_col.setCellValueFactory(new PropertyValueFactory<>("nom"));
        niveau_col.setCellValueFactory(new PropertyValueFactory<>("niveaux"));
        action_col.setCellValueFactory(new PropertyValueFactory<>("modifier"));
        classes.setItems(liste_Classe);
        edittablecol();

    }
       
    private void initEleve() {
        EleveService elService = new EleveService();
        List<Eleve> listeleves = elService.displayEleve();
        for (int i = 0; i < listeleves.size(); i++) {
            liste_Eleve = FXCollections.observableArrayList(listeleves);
        }
        id_eleve.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_eleve.setCellValueFactory(new PropertyValueFactory<>("nom"));
        email_eleve.setCellValueFactory(new PropertyValueFactory<>("email"));
        classe_eleve.setCellValueFactory(new PropertyValueFactory<>("classe"));
        eleves.setItems(liste_Eleve);

    }

    private void initEnseignant() {
        EnseignantsService elService = new EnseignantsService();
        List<Enseignants> listenseignants = elService.displayEnseignant();
        for (int i = 0; i < listenseignants.size(); i++) {
            liste_Enseignant = FXCollections.observableArrayList(listenseignants);
        }
        id_ens.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_ens.setCellValueFactory(new PropertyValueFactory<>("nom"));
        email_ens.setCellValueFactory(new PropertyValueFactory<>("email"));
        classe_ens.setCellValueFactory(new PropertyValueFactory<>("classe"));
        enseignants.setItems(liste_Enseignant);
    }

    private void initPunishment() {
        PunitionService elService = new PunitionService();
        List<Punition> listPunition = elService.displayPunition();
        for (int i = 0; i < listPunition.size(); i++) {
            liste_Punition = FXCollections.observableArrayList(listPunition);
        }
        id_puns.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_puns.setCellValueFactory(new PropertyValueFactory<>("eleve"));
        classe_puns.setCellValueFactory(new PropertyValueFactory<>("classe"));
        type_puns.setCellValueFactory(new PropertyValueFactory<>("cause"));
        date_puns.setCellValueFactory(new PropertyValueFactory<>("date"));
        action_puns.setCellValueFactory(new PropertyValueFactory<>("modifier"));
        punitions.setItems(liste_Punition);
        editPunitioncol();
    }

    private void initAbsence() {
        AbsenceService elService = new AbsenceService();
        List<Absence> listAbsence = elService.displayAbsence();
        for (int i = 0; i < listAbsence.size(); i++) {
            liste_Absences = FXCollections.observableArrayList(listAbsence);
        }
        id_absence.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomEns_absence.setCellValueFactory(new PropertyValueFactory<>("ens"));
        nomele_absence.setCellValueFactory(new PropertyValueFactory<>("eleve"));
        classe_absence.setCellValueFactory(new PropertyValueFactory<>("classe"));
        matiere_absence.setCellValueFactory(new PropertyValueFactory<>("matier"));
        heure_absence.setCellValueFactory(new PropertyValueFactory<>("heure"));
        date_absence.setCellValueFactory(new PropertyValueFactory<>("date"));
        actionAbsence_col.setCellValueFactory(new PropertyValueFactory<>("modifier"));
        absences.setItems(liste_Absences);
        editAbsencecol();
    }

    private void initProfil() {
        String p = ConnecterController.n;
        String v = "deconnexion";
        String o = "profil";
        liste_profil.addAll(p, o, v);
        deconnexion.setItems(liste_profil);
    }

    private void afficherpunition() {

    }

    private void editAbsencecol() {

        id_absence.setCellFactory(TextFieldTableCell.forTableColumn());
        id_absence.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
        }
        );

        nomEns_absence.setCellFactory(TextFieldTableCell.forTableColumn());
        nomEns_absence.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEns(e.getNewValue());
        }
        );

        nomele_absence.setCellFactory(TextFieldTableCell.forTableColumn());
        nomele_absence.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEleve(e.getNewValue());
        }
        );

        classe_absence.setCellFactory(TextFieldTableCell.forTableColumn());
        classe_absence.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setClasse(e.getNewValue());
        }
        );

        matiere_absence.setCellFactory(TextFieldTableCell.forTableColumn());
        matiere_absence.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setMatier(e.getNewValue());
        }
        );

        heure_absence.setCellFactory(TextFieldTableCell.forTableColumn());
        heure_absence.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setHeure(e.getNewValue());
        }
        );
        absences.setEditable(true);

    }

    private void editPunitioncol() {
        id_puns.setCellFactory(TextFieldTableCell.forTableColumn());
        id_puns.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setId(e.getNewValue());
        }
        );

        nom_puns.setCellFactory(TextFieldTableCell.forTableColumn());
        nom_puns.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setEleve(e.getNewValue());
        }
        );

        classe_puns.setCellFactory(TextFieldTableCell.forTableColumn());
        classe_puns.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setClasse(e.getNewValue());
        }
        );

        type_puns.setCellFactory(TextFieldTableCell.forTableColumn());
        type_puns.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setCause(e.getNewValue());
        }
        );

        punitions.setEditable(true);
    }

    private void edittablecol() {
        ic_col.setCellFactory(TextFieldTableCell.forTableColumn());
        ic_col.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setIdClasse(e.getNewValue());
        }
        );

        nom_col.setCellFactory(TextFieldTableCell.forTableColumn());
        nom_col.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNom(e.getNewValue());
        }
        );

        niveau_col.setCellFactory(TextFieldTableCell.forTableColumn());
        niveau_col.setOnEditCommit(e -> {
            e.getTableView().getItems().get(e.getTablePosition().getRow()).setNiveaux(e.getNewValue());
        }
        );
        classes.setEditable(true);
    }
    public List<Absence> createTablee() 
    {
            AbsenceService elService = new AbsenceService();
        List<Absence> listAbsence = elService.displayAbsencee(form, to); 
        for (int i = 0; i < listAbsence.size(); i++) {
            liste_Absences = FXCollections.observableArrayList(listAbsence);
        }
        id_absence.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomEns_absence.setCellValueFactory(new PropertyValueFactory<>("ens"));
        nomele_absence.setCellValueFactory(new PropertyValueFactory<>("eleve"));
        classe_absence.setCellValueFactory(new PropertyValueFactory<>("classe"));
        matiere_absence.setCellValueFactory(new PropertyValueFactory<>("matier"));
        heure_absence.setCellValueFactory(new PropertyValueFactory<>("heure"));
        date_absence.setCellValueFactory(new PropertyValueFactory<>("date"));
        actionAbsence_col.setCellValueFactory(new PropertyValueFactory<>("modifier"));
        absences.setItems(liste_Absences);
        editAbsencecol();
        return liste_Absences; 
    }
private Node createPage(int pageIndex) {
    form = pageIndex * itemPage; 
    to = itemPage;
    absences.setItems(FXCollections.observableArrayList(createTablee()));
return absences;
} 
} 
