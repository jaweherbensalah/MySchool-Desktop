
package StudentServices;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SignupModel {
  Connection connection;
    public SignupModel(){
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
            Logger.getLogger(SignupModel.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error");
            return false;
            
        }
        
    } 
    public void isSignup(String username,
            String username_canonical, 
            String email,
            String email_canonical, 
           String   enabled, 
           String salt,
        String   password ,
        LocalDate last_login ,
     String confirmation_token,
     LocalDate password_requested_at,
     String roles,
      String nom_prenom,
     LocalDate date_naissance,
     String sexe, 
     String numero_tel,
     String adresse,
     String description,
     String url_photo_profil,
     LocalDate date_inscription,
     String status,
     String classe ,
     String matiere,
     String classe2) throws SQLException{
        PreparedStatement preparedStatement =null;
        String query="insert into utilisateur (username,username_canonical, email, email_canonical, "
                + "enabled,salt, password   ,last_login ,  confirmation_token,"
                + "password_requested_at,roles,nom_prenom,date_naissance, sexe,numero_tel,adresse,description"
                + ",url_photo_profil,date_inscription,status,classe ,matiere,classe2)"+
                "value(?,?,?,?,?,?,?,?,?,?"
          +          ",?,?,?,?,?,?,?,?,?,?,"
          + "?,?,?)";
      try {
       
            //---------------------------------------------
          preparedStatement=connection.prepareStatement(query);
           Date lastlogin = Date.valueOf(last_login);
          Date passReq = Date.valueOf(password_requested_at);
          Date dateins = Date.valueOf(date_inscription);
          Date datenaiss = Date.valueOf(date_naissance);
          //---------------------------------------------------------
          preparedStatement.setString(1,username);
          preparedStatement.setString(2,username_canonical);
          preparedStatement.setString(3,email);
          preparedStatement.setString(4,email_canonical);
          preparedStatement.setString(5,enabled);
          
          preparedStatement.setString(6,salt);
          preparedStatement.setString(7,password);
          preparedStatement.setDate(8,lastlogin);
          preparedStatement.setString(9,confirmation_token);
        
          
          preparedStatement.setDate(10,passReq);
           preparedStatement.setString(11,roles);
          preparedStatement.setString(12,nom_prenom);
           
          preparedStatement.setDate(13,datenaiss);
          preparedStatement.setString(14,sexe);
          preparedStatement.setString(15,numero_tel);
           
          preparedStatement.setString(16,adresse);
          preparedStatement.setString(17,description);
          preparedStatement.setString(18,url_photo_profil);
          preparedStatement.setDate(19,dateins);
          preparedStatement.setString(20,status);
          preparedStatement.setString(21,classe);
          preparedStatement.setString(22,matiere);
          preparedStatement.setString(23,classe2);
      
      } catch (SQLException ex) {
          Logger.getLogger(SignupModel.class.getName()).log(Level.SEVERE, null, ex);
      }finally{
          connection.close();
      }
        

        
    }
    public boolean isEmail(String email) throws SQLException{
        PreparedStatement preparedStatement = null ;
        ResultSet resultSet =null;
        String query="select * from utilisateur where email=?";
        try{
            preparedStatement =connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch(SQLException e){
            System.out.println(" no!"+e);
            return false;
        }finally{
            preparedStatement.close();
            resultSet.close();
        }
    }
}
