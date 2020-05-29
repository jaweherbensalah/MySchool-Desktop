
package models;

import java.security.NoSuchAlgorithmException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author jaweher
 */
public class Transport {
    private String montant;
    private LocalDate date_inscription;
   private LocalDate fin_inscription;
    private int id;

    public Transport(LocalDate date_inscription, LocalDate fin_inscription, String montant
            ) throws NoSuchAlgorithmException {
        setDate_inscription(date_inscription);
        setFin_inscription(fin_inscription);
        setMontant(montant);
    }

 
    
 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id >= 0)
            this.id = id;
        else
            throw new IllegalArgumentException("id must be >= 0");
    }
    
    
    public LocalDate getDate_inscription() {
        return date_inscription;
    }

    public void setDate_inscription(LocalDate date_inscription) {
        this.date_inscription = date_inscription;
    }

    public LocalDate getFin_inscription() {
        return fin_inscription;
    }

     public void setFin_inscription(LocalDate fin_inscription) {
        this.fin_inscription = fin_inscription;
    }
    
    public void setMontant(String montant) {
        this.montant = montant;
    }
    public String getMontant() {
        return montant;
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
     * This method will return a formatted String with the persons' first name, last name and age
     */
    public String toString()
    {
        return String.format("%s %s is %d %d %s transport", 
                Period.between(date_inscription, LocalDate.now()).getYears(),
                 Period.between(fin_inscription, LocalDate.now()).getYears(),montant);
    }
    
    /**
     * This method will write the instance of the Transport into the database
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
            String sql = "INSERT INTO transport (date_inscription, fin_inscription, montant)"
                    + "VALUES (?,?,?)";
                    
            //3. prepare the query
            preparedStatement = conn.prepareStatement(sql);
            
            //4. Convert the date_inscription into a SQL date
            Date dDebut = Date.valueOf(date_inscription);
            Date dFin = Date.valueOf(fin_inscription);
                   
            //5. Bind the values to the parameters
           
            preparedStatement.setDate(1, dDebut);
            preparedStatement.setDate(2, dFin);
            preparedStatement.setString(3, montant);
         
            
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
    
//------------------------------------------------------------------------------
      /**
     * This will update the Transport in the database
     */
    public void updateTransportInDB() throws SQLException
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        
        try{
            //1.  connect to the DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");
            
            //2.  create a String that holds our SQL update command with ? for user inputs
            String sql = "UPDATE transport SET date_inscription = ?, fin_inscription = ?, montant=? "
                    + "WHERE id = ?";
            
            //3. prepare the query against SQL injection
            preparedStatement = conn.prepareStatement(sql);
            
              //4. Convert the date_inscription into a SQL date
            Date dDebut = Date.valueOf(date_inscription);
            Date dFin = Date.valueOf(fin_inscription);
                   
            //5. Bind the values to the parameters
           
            preparedStatement.setDate(1, dDebut);
            preparedStatement.setDate(2, dFin);
            preparedStatement.setString(3, montant);
            preparedStatement.setInt(4, id);
            
            //6. run the command on the SQL server
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            if (conn != null)
                conn.close();
            if (preparedStatement != null)
                preparedStatement.close();
        }
        
    }
    
    
    
    
  
}
