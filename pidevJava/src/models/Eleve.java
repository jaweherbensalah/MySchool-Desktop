
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


public class Eleve {
    private String nom, prenom, email,mdp,numtel, classe;
    private LocalDate birthday;
    private File imageFile;
    private int id;
    private byte[] salt;
public Eleve(String nom, String prenom, String email,String mdp ,String numtel, String classe ,
               LocalDate birthday
          ) throws NoSuchAlgorithmException {
        setNom(nom);
        setPrenom(prenom);
        setEmail(email);
        setMdp(mdp);
        setImageFile(new File("./src/images/defaultPerson.png"));
        salt = PasswordGenerator.getSalt();
        this.mdp = PasswordGenerator.getSHA512Password(mdp, salt);
}  

    public Eleve(String nom, String prenom,  String email,String mdp,String numtel,String classe, LocalDate birthday, File imageFile) throws IOException, NoSuchAlgorithmException {
        this(nom, prenom, email,mdp,numtel,classe,birthday);
        setImageFile(imageFile);
        copyImageFile();
    }
    
 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id >= 0)
            this.id  =id;
        else
            throw new IllegalArgumentException(" EleveID must be >= 0");
    }
    
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNumtel() {
        return numtel;
    }

    /**
     * area code    city    house 
     * NXX          XXX    XX
     * @param phoneNumber 
     */
    public void setNumtel(String phoneNumber) {
        if (phoneNumber.matches("[2-9]\\d{2}[.]?\\d{3}[.]\\d{4}"))
            this.numtel = phoneNumber;
        else
            throw new IllegalArgumentException("Phone numbers must be in the pattern NXX-XXX-XXXX");
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
        
        if (age >= 10 && age <= 25)
            this.birthday = birthday;
        else
            throw new IllegalArgumentException("Volunteers must be 10-100 years of age.");
    }
      public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
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
        return String.format("%s %s is %d years old", nom , prenom, email,mdp,numtel,
                Period.between(birthday, LocalDate.now()).getYears());
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
            String sql = "INSERT INTO eleve (nom, prenom, email, mdp, numtel,classe ,birthday,imageFile,salt)"
                    + "VALUES (?,?,?,?,?,?,?,?)";
                    
            //3. prepare the query
            preparedStatement = conn.prepareStatement(sql);
            
            //4. Convert the birthday into a SQL date
            Date db = Date.valueOf(birthday);
                   
            //5. Bind the values to the parameters
            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, mdp);
            preparedStatement.setString(5, numtel);
            preparedStatement.setString(6, classe);
           preparedStatement.setDate(7, db);
            preparedStatement.setString(8, imageFile.getName());
            preparedStatement.setBlob(9, new javax.sql.rowset.serial.SerialBlob(salt));
            
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
    
//------------------------------------------------------------------------------
   
    
  
}
