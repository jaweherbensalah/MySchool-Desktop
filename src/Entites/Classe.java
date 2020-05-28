
package Entites;

import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import pidevfinal.DirecteurAdminController;
import service.ClasseService;

public class Classe {
    private String idClasse;
      private String nom; 
      private String niveaux; 
      private Button modifier ; 
    public Classe(String niveaux) {
        this.niveaux = niveaux;
    }

  

    public Classe() {
    }

    public Classe(String idClasse, String nom, String niveaux) {
        this.idClasse = idClasse;
        this.nom = nom;
        this.niveaux = niveaux;
        this.modifier = new Button("modifier");
        
        modifier.setOnAction( e-> {
            ObservableList<Classe> cl = DirecteurAdminController.liste_2.getSelectionModel().getSelectedItems(); 
    for (Classe classe : cl) {
    if (classe.getModifier() == modifier) {
        System.out.println("id "+classe.getIdClasse());
        System.out.println("nom "+classe.getNom());
        System.out.println("niveau  "+classe.getNiveaux());
    ClasseService cls = new ClasseService(); 
    cls.updateClasse(classe); 
    }
}
    
}); 
    }


    

    public Classe(String nom, String niveaux) {
        this.nom = nom;
        this.niveaux = niveaux;
    }

    @Override
    public String toString() {
        return  nom ;
    }

    public String getIdClasse() {
        return idClasse;
    }

    public void setIdClasse(String idClasse) {
        this.idClasse = idClasse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNiveaux() {
        return niveaux;
    }

    public void setNiveaux(String niveaux) {
        this.niveaux = niveaux;
    }

    public Button getModifier() {
        return modifier;
    }

    public void setModifier(Button modifier) {
        this.modifier = modifier;
    }

   
}
