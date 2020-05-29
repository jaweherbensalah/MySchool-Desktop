
package models;


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
import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author jaret_000
 */
public class utlisateur {
    private String firstName, lastName, phoneNumber,password,email;
    private LocalDate birthday;
    private File imageFile;
    private int id;
      private byte[] salt;
      private boolean admin;

    public utlisateur(String firstName, String  lastName,String email, String phoneNumber,
            LocalDate birthday,
            String password,
            boolean admin
            ) throws NoSuchAlgorithmException {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setBirthday(birthday);
        setImageFile(new File("./src/images/defaultUser.png"));
        salt = PasswordGenerator.getSalt();
        this.password = PasswordGenerator.getSHA512Password(password, salt);
        this.admin=admin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
    
          if (email.matches("(.*)@(.*)"))
    this.email = email;
        else
            throw new IllegalArgumentException("Le N° de téléphone doit être de cette forme NXX-XXX-XX");
    }

    public utlisateur(String firstName, String lastName, String email,
            String phoneNumber, LocalDate birthday, File imageFile,String password,boolean admin) throws IOException, NoSuchAlgorithmException {
        this(firstName, lastName,email, phoneNumber, birthday,password, admin);
        setImageFile(imageFile);
        copyImageFile();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
  

   

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * area code    city    house 
     * NXX          -XXX    -XXXX
     * @param phoneNumber 
     */
    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("[2-9]\\d{2}[-.]?\\d{3}[-.]\\d{4}"))

            this.phoneNumber = phoneNumber;
        else
            throw new IllegalArgumentException("Le N° de téléphone doit être de cette forme NXX-XXX-XX");
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    /**
     * This will validate that the volunteer is between the ages of 10 and 100
     * @param birthday 
     */
    public void setBirthday(LocalDate birthday) {
        int age = Period.between(birthday, LocalDate.now()).getYears();
        
        if (age >= 10 && age <= 100)
            this.birthday = birthday;
        else
            throw new IllegalArgumentException("Les utilisateurs doivent être âgés entre 10 et 100.");
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }
    
    /**
     * This method will copy the file specified to the images directory on this server and give it 
     * a unique name
     */
    public void copyImageFile() throws IOException
    {
        //create a new Path to copy the image into a local directory
        Path sourcePath = imageFile.toPath();
        
        String uniqueFileName = getUniqueFileName(imageFile.getName());
        
        Path targetPath = Paths.get("./src/images/"+uniqueFileName);
        
        //copy the file to the new directory
        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        
        //update the imageFile to point to the new File
        imageFile = new File(targetPath.toString());
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
        return String.format("%s %s is %d years old", firstName, lastName, 
                Period.between(birthday, LocalDate.now()).getYears());
    }
    
    /**
     * This method will write the instance of the utlisateur into the database
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
            String sql = "INSERT INTO utilisateur "
                    + "(firstName, lastName,email, phoneNumber, birthday, imageFile,password ,salt,admin)"
                    + "VALUES (?,?,?,?,?,?,?,?,?)";
                    
            //3. prepare the query
            preparedStatement = conn.prepareStatement(sql);
            
            //4. Convert the birthday into a SQL date
            Date db = Date.valueOf(birthday);
                   
            //5. Bind the values to the parameters
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
             preparedStatement.setString(3, email);
            preparedStatement.setString(4, phoneNumber);
            preparedStatement.setDate(5, db);
            preparedStatement.setString(6, imageFile.getName());
           preparedStatement.setString(7, password);
            preparedStatement.setBlob(8, new javax.sql.rowset.serial.SerialBlob(salt));
           preparedStatement.setBoolean(9,admin);
            
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
     * This will update the utlisateur in the database
     */
    public void updateVolunteerInDB() throws SQLException
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        
        try{
            //1.  connect to the DB
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");
            
            //2.  create a String that holds our SQL update command with ? for user inputs
            String sql = "UPDATE utilsateur SET firstName = ?, lastName = ?, phoneNumber=?, birthday = ?, imageFile = ?"
                    + "WHERE volunteerID = ?";
            
            //3. prepare the query against SQL injection
            preparedStatement = conn.prepareStatement(sql);
            
            //4.  convert the birthday into a date object
            Date bd = Date.valueOf(birthday);
            
            //5. bind the parameters
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setDate(4, bd);
            preparedStatement.setString(5, imageFile.getName());
            preparedStatement.setInt(6, id);
            
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
    
    
   
    /**
     * This method will record the hours worked for the volunteer.
     * @param dateWorked - must be in the current year and previous the current date
     * @param hoursWorked - must be less than 18 hours
     */
    public void logHours(LocalDate dateWorked, int hoursWorked) throws SQLException 
    {
        //validate the date is today or earlier
        if (dateWorked.isAfter(LocalDate.now()))
            throw new IllegalArgumentException("Date worked cannot be in the future");
        
        if (dateWorked.isBefore(LocalDate.now().minusYears(1)))
            throw new IllegalArgumentException("Date worked must be within the last 12 months");
        
        //validate the hours worked
        if (hoursWorked < 0 || hoursWorked > 18)
            throw new IllegalArgumentException("Hours worked must be in the range of 0-18");
            
        //ready to store in the database
        Connection conn = null;
        PreparedStatement ps = null;
        
        try{
            //1. connect to the database
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/pidev", "root", "");
            
            //2. create a preparedStatement
            String sql = "INSERT INTO hoursWorked (volunteerID, dateWorked, hoursworked) VALUES (?,?,?);";
            
            //3.  prepare the query
            ps = conn.prepareStatement(sql);
            
            //4.  convert the localdate to sql date
            Date dw = Date.valueOf(dateWorked);
            
            //5.  bind the parameters
            ps.setInt(1, id);
            ps.setDate(2, dw);
            ps.setInt(3, hoursWorked);
            
            //6.  execute the update  
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            if (conn != null)
                conn.close();
            if (ps != null)
                ps.close();
        }
    }
    
     
    
  
}
