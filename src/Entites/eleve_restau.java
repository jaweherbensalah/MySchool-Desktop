
package Entites;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author jaret_000
 */
public class eleve_restau {
    //	id	email	username	solde	password	role (DC2Type:array)	duree_abonnement	
    private String email, username,password;
    private String duree_abonnement;
    private double solde;
    private int id;

    public eleve_restau(String email, String 
            username,double solde,String password,String duree_abonnement
            ) throws NoSuchAlgorithmException {
        setEmail(email);
        setUsername(username);
        setSolde(solde);
        setPassword(password);
        setDuree_abonnement(duree_abonnement);
    }

    
      public eleve_restau(String email, String 
            username,String password,String duree_abonnement
            ) throws NoSuchAlgorithmException {
        setEmail(email);
        setUsername(username);
        setPassword(password);
       setDuree_abonnement(duree_abonnement);
      
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDuree_abonnement() {
        return duree_abonnement;
    }

    public void setDuree_abonnement(String duree_abonnement) {
        this.duree_abonnement = duree_abonnement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
      public double getSolde() {
        return solde;
    }

    public void setSolde(double solde) {
        this.solde = solde;
    }

    public eleve_restau() {
    }
    /**
     * This method will validate if the integer given corresponds to a valid
     * ASCII character that could be used in a file name
     */
    public boolean validCharacterValue(int asciiValue)
    {
        
        //0-9 = ASCII range 48 to 57
        if (asciiValue >= 48 && asciiValue <= 57)
            return true;
        
        //A-Z = ASCII range 65 to 90
        if (asciiValue >= 65 && asciiValue <= 90)
            return true;
        
        //a-z = ASCII range 97 to 122
        if (asciiValue >= 97 && asciiValue <= 122)
            return true;
        
        return false;
    }
    
    

    /**
     * This method will write the instance of the Volunteer into the database
     */
    public void insertIntoDB() throws SQLException
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        
        try
        {
            //1. Connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");
            
            //2. Create a String that holds the query with ? as user inputs
            String sql = "INSERT INTO eleve_restau (email, username, solde,password ,duree_abonnement)"
                    + "VALUES (?,?,?,?,?)";
                    
            //3. prepare the query
            preparedStatement = conn.prepareStatement(sql);
              //5. Bind the values to the parameters
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, username);
            preparedStatement.setDouble(3, 7000);
            preparedStatement.setString(4, password);
            preparedStatement.setString(5, duree_abonnement);
       
            
            preparedStatement.executeUpdate();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            if (preparedStatement != null)
                preparedStatement.close();
            
            if (conn != null)
                conn.close();
        }
    }

}
