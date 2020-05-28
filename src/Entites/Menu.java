
package Entites;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Menu {
    private String items;
    private String prix ;
    private File photo;
    private int id;

    public Menu(String items, String prix  ) throws NoSuchAlgorithmException {
        setItems(items);
        setPrix(prix);
        //setPhoneNumber(phoneNumber);
       // setBirthday(birthday);
        setPhoto(new File("./src/images/defaultPerson.png"));
      
    }

    public Menu(String items, String prix,File photo) throws IOException, NoSuchAlgorithmException {
        this(items, prix);
        setPhoto(photo);
        copyImageFile();
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
    
    
    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }






    public File getPhoto() {
        return photo;
    }

    public void setPhoto(File photo) {
        this.photo = photo;
    }
    
    /**
     * This method will copy the file specified to the images directory on this server and give it 
     * a unique name
     */
    public void copyImageFile() throws IOException
    {
        //create a new Path to copy the image into a local directory
        Path sourcePath = photo.toPath();
        
        String uniqueFileName = getUniqueFileName(photo.getName());
        
        Path targetPath = Paths.get("./src/images/"+uniqueFileName);
        
        //copy the file to the new directory
        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        
        //update the imageFile to point to the new File
        photo = new File(targetPath.toString());
    }
    
    
    /**
     * This method will receive a String that represents a file name and return a
     * String with a random, unique set of letters prefixed to it
     */
    private String getUniqueFileName(String oldFileName)
    {
        String newName;
        
        //create a Random Number Generator
        SecureRandom rng = new SecureRandom();
        
        //loop until we have a unique file name
        do
        {
            newName = "";
            
            //generate 32 random characters
            for (int count=1; count <=32; count++)
            {
                int nextChar;
                
                do
                {
                    nextChar = rng.nextInt(123);
                } while(!validCharacterValue(nextChar));
                
                newName = String.format("%s%c", newName, nextChar);
            }
            newName += oldFileName;
            
        } while (!uniqueFileInDirectory(newName));
        
        return newName;
    }
    
    
    /**
     * This method will search the images directory and ensure that the file name
     * is unique
     */
    public boolean uniqueFileInDirectory(String fileName)
    {
        File directory = new File("./src/images/");
        
        File[] dir_contents = directory.listFiles();
                
        for (File file: dir_contents)
        {
            if (file.getName().equals(fileName))
                return false;
        }
        return true;
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
        return String.format("%s %s Le menu :", items, prix
                );
    }
    
    /**
     * This method will write the instance of the Menu into the database
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
            String sql = "INSERT INTO menu (items, prix, photo)"
                    + "VALUES (?,?,?)";
                    
            //3. prepare the query
            preparedStatement = conn.prepareStatement(sql);
            
            //4. Convert the birthday into a SQL date
         //   Date db = Date.valueOf(birthday);
                   
            //5. Bind the values to the parameters
            preparedStatement.setString(1, items);
            preparedStatement.setString(2, prix);
          //  preparedStatement.setString(3, phoneNumber);
            //preparedStatement.setDate(4, db);
            preparedStatement.setString(3, photo.getName());
         ;
            
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
     * This will update the Menu in the database
     */
    public void updateMenuInDB() throws SQLException
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        
        try{
            //1.  connect to the DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");
            
            //2.  create a String that holds our SQL update command with ? for user inputs
            String sql = "UPDATE menu SET items = ?, prix = ?,photo = ?"
                    + "WHERE id = ?";
            
            //3. prepare the query against SQL injection
            preparedStatement = conn.prepareStatement(sql);
            
            //4.  convert the birthday into a date object
            //Date bd = Date.valueOf(birthday);
            
            //5. bind the parameters
            preparedStatement.setString(1, items);
            preparedStatement.setString(2, prix);
           // preparedStatement.setString(3, phoneNumber);
          //  preparedStatement.setDate(4, bd);
            preparedStatement.setString(3, photo.getName());
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
