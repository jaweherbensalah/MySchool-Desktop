
package Entites;

public class Utilisateur {
       private int id;
    private String nom;
    private String prenom;
    private String email;
    private String mdp;
    private String role;
    private int numtel;
    private int age; 

    public Utilisateur() {
         this.id = 0;
        this.nom = "";
        this.prenom = "";
        this.email = "";
        this.mdp = "";
        this.role = "";
        this.numtel = 0; 
    }

    public Utilisateur(String nom) {
        this.nom = nom;
    }

    public Utilisateur(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public Utilisateur(int id, String nom, String email) {
        this.id = id;
        this.nom = nom;
    
        this.email = email; 
    }

    
    public Utilisateur(int id, String nom, String prenom, String email, String mdp, String role , int numtel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mdp = mdp;
        this.role = role;
        this.numtel = numtel; 
    }

    public Utilisateur(int id, String nom, String email, int numtel) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.numtel = numtel;
    }

    @Override
    public String toString() {
       return  nom ; 
            
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    
    
   

}
