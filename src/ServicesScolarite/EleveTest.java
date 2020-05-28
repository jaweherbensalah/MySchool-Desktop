package ServicesScolarite;

import java.io.IOException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Entites.Menu;
import Entites.Transport;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
/**
 *
 * @author jaret_000
 */
public class EleveTest extends Application{
    
    
    public static void main(String[] args)
    {   //launch calls the start method
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       
         
        Parent root;
        root = FXMLLoader.load(getClass().
                getResource("/StudentServices/Login.fxml"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Espace Elève");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
