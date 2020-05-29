
package Entites;

public class Niveau {
   private int idNiveau;
      private String nom;

    public Niveau() {
    }

    public Niveau(int idNiveau, String nom) {
        this.idNiveau = idNiveau;
        this.nom = nom;
    }

    public Niveau(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return nom ;
    }

    public int getIdNiveau() {
        return idNiveau;
    }

    public void setIdNiveau(int idNiveau) {
        this.idNiveau = idNiveau;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
      
}
