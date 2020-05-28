package ServicesScolarite;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ReportController implements Initializable {

    private final int order_id;
    private final int costumer_id;
    private final String menu_id;
    private int quantity;
    private String order_status;
    private String adresse;
    @FXML  private Label dateLabel;
    @FXML private Label timeLabel;
    @FXML private AnchorPane root;
    @FXML  private Button btnPrint;
    @FXML  private Label order_idT, costumer_idT, menu_idT, quantityT, order_statusT, 
   adresseT;
    
    public ReportController(int order_id, int costumer_id, String menu_id,int quantity, String order_status,String adresse) {
        this.order_id = order_id;
        this.costumer_id = costumer_id;
        this.menu_id = menu_id;
        this.quantity = quantity;
        this.order_status=order_status;
       this.adresse=adresse;
    }
    
    public void calculateValues() {
      
        
        order_idT.setText(String.valueOf(order_id));
        costumer_idT.setText(String.valueOf(costumer_id)); 
        menu_idT.setText(String.valueOf(menu_id)); 
       quantityT.setText(String.valueOf(quantity) + " mois"); 
       order_statusT.setText(order_status);
       adresseT.setText(adresse);
    }
    
    @FXML
    private void onButtonPrint() {
        btnPrint.setVisible(false);
        print(root);
    }
         
    
    private void print(Node node) {
        PrinterJob job = PrinterJob.createPrinterJob();
        if (job != null && job.showPrintDialog(node.getScene().getWindow())){
            boolean success = job.printPage(node);
            if (success) {
                job.endJob();
            }
        }
        btnPrint.setVisible(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
      calculateValues();
        updateTime();
    }    
      private void updateTime() {
        int hr = LocalTime.now().getHour();
        int min = LocalTime.now().getMinute();
        timeLabel.setText((hr == 0 || hr == 12 ? 12 : (hr % 12 < 10 ? "0"+(hr%12) : hr%12 )) + ":" + (min < 10 ? "0"+min : min) + (hr >= 12 ? " PM" : " AM"));
        dateLabel.setText(LocalDate.now().toString());
    }

    
}
