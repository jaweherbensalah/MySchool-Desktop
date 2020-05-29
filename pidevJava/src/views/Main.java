package views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author jaret_000
 */
public class Main extends Application{
    
    public static void main(String[] args)
    {   //launch calls the start method
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
       
         
        Parent root;
        root = FXMLLoader.load(getClass().
                getResource("/StudentServices/ListServices"));
        
        Scene scene = new Scene(root);
        
        primaryStage.setTitle("Admin Interface : ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
