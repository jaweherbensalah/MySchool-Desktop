
package Entites;


public class Eleve extends Utilisateur{
    private String classe; 

    public Eleve(String nom) {
        super(nom);
    }

    public Eleve(int id, String nom) {
        super(id, nom);
    }
    
    
    public Eleve() {
        super();
    }

    public Eleve(String classe, int id, String nom, String email) {
        super(id, nom,email);
        this.classe = classe;
    }

    public Eleve(String classe, String nom) {
        super(nom);
        this.classe = classe;
    }

  

    public Eleve(String classe, int id, String nom, String prenom, String email, String mdp, String role, int numtel) {
        super(id, nom, prenom, email, mdp, role, numtel);
        this.classe = classe;
    }

    @Override
    public String toString() {
        return (super.toString());
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

  
   
   
    
    
}
