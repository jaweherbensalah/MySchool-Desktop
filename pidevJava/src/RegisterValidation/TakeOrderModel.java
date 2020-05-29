package RegisterValidation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TakeOrderModel {
    Connection connection;
    public TakeOrderModel(){
        connection =SqlConnection.Connector();
        if(connection==null){
            System.exit(0);
            System.out.println("notconnected");
        }
    }
    public boolean isDbConnected(){
        try {
            return !connection.isClosed();
        } catch (SQLException ex) {
            System.out.println("error");
            Logger.getLogger(TakeOrderModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error");
            return false;
            
        }
        
    }
    
    
    
}
