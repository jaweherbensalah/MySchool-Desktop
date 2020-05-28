package ServicesScolarite;
import javafx.geometry.Insets;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.print.PrinterJob;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;


public  final class Print extends Application {
private TableView<ModelTable> table;
private ObservableList<ModelTable> list;

public void createTable() {

    table = new TableView<>();
    table.setEditable(true);
    list = FXCollections.observableArrayList(
            new ModelTable(1,1,"Restau", "cash", "tunis",1),
            new ModelTable(1,2,"Restau", "cash", "tunis",4),
            new ModelTable(11,4,"Transport", "Online", "tunis",6),
            new ModelTable(22,5,"Transport", "cash", "manouba",33),
            new ModelTable(2,2,"Transport", "Online", "menzel temime",55));

//associating data with the table columns
    TableColumn firstNameCol = new TableColumn("Num Commande");
    firstNameCol.setMinWidth(100);
    firstNameCol.setCellValueFactory(
            new PropertyValueFactory<ModelTable, String>("orderid"));

    TableColumn SurnameCol = new TableColumn("Num Elève");
    SurnameCol.setMinWidth(100);
    SurnameCol.setCellValueFactory(
            new PropertyValueFactory<ModelTable, String>("custid"));
    
    TableColumn emailCol = new TableColumn("Nom Service");
    emailCol.setMinWidth(100);
    emailCol.setCellValueFactory(
     new PropertyValueFactory<ModelTable, String>("menuname"));
   
    TableColumn qnt = new TableColumn("Type De Paiement");
    qnt.setMinWidth(100);
    qnt.setCellValueFactory(
    new PropertyValueFactory<ModelTable, String>("deliverytyp"));
   
    TableColumn add= new TableColumn("ADresse Elève");
    add.setMinWidth(100);
    add.setCellValueFactory(
    new PropertyValueFactory<ModelTable, String>("address"));
     
    TableColumn j = new TableColumn("Nombre des mois ");
    j.setMinWidth(100);
    j.setCellValueFactory(
            new PropertyValueFactory<ModelTable, String>("qnt"));

    table.setItems(list);
    table.getColumns().addAll(firstNameCol, SurnameCol, emailCol,qnt,add,j);

}

@Override
public void start(Stage primaryStage) {
    StackPane root = new StackPane();
    Scene scene = new Scene(root, 600, 600);
    this.createTable();
    Label label = new Label("My Address Book");
    Button button = new Button("Imprimer");
    //printing
    button.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent event) {
            System.out.println(" can I print?");
            PrinterJob printerJob = PrinterJob.createPrinterJob();
            if (printerJob.showPrintDialog(primaryStage) && printerJob.printPage(table))
            {
                printerJob.endJob();
                System.out.println("printed");
            }
        }
    });

    final VBox vbox = new VBox();
    vbox.setSpacing(5);
    vbox.setPadding(new Insets(10, 0, 0, 10));
    vbox.getChildren().addAll(label, table, button);
    root.getChildren().add(vbox);

    primaryStage.setTitle("Test sur pdf ");
    primaryStage.setScene(scene);
    primaryStage.show();

}

public static void main(String[] args) {
    launch(args);
}
}
    

