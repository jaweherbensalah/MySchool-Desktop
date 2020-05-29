
package Entites;

public class Enseignants extends Utilisateur{
     private String classe; 
     private String matiere; 
     private String classe2;
    public Enseignants() {
        super();
    }

    public Enseignants(String nom) {
        super(nom);
    }
    
    public Enseignants(String classe, int id, String nom,String matiere) {
        super(id, nom);
        this.classe = classe;
        this.matiere = matiere;
    }

    public Enseignants(String classe, String matiere, String classe2, int id, String nom) {
        super(id, nom);
        this.classe = classe;
        this.matiere = matiere;
        this.classe2 = classe2;
    }

    public Enseignants(int id, String nom) {
        super(id, nom);
    }

    public Enseignants(String classe, int id, String nom, String email,String matiere) {
        super(id, nom, email);
        this.classe = classe;
        this.matiere = matiere;
    }

    public Enseignants(String classe, String matiere, String classe2, int id, String nom, String email, int numtel) {
        super(id, nom, email, numtel);
        this.classe = classe;
        this.matiere = matiere;
        this.classe2 = classe2;
    }

    public Enseignants(String classe, String matiere, String classe2, int id, String nom, String email) {
        super(id, nom, email);
        this.classe = classe;
        this.matiere = matiere;
        this.classe2 = classe2;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getMatiere() {
        return matiere;
    }

    public void setMatiere(String matiere) {
        this.matiere = matiere;
    }

    @Override
    public String toString() {
        return (super.toString());
    }

    public String getClasse2() {
        return classe2;
    }

    public void setClasse2(String classe2) {
        this.classe2 = classe2;
    }
    
     
}
