
package Interfaces;
import Entites.Classe;
import Entites.Eleve;
import Entites.Enseignants;
import Entites.Matiere;
import java.util.List;

public interface Iclasse {
   //  void affecterEleve(Eleve eleve, String nom);

    boolean insertClasse(Classe classe);

    boolean deleteClasse(Classe classe );
    
    boolean updateClasse(Classe classe);

    List<Classe> displayClasse();

    Classe findByNom(String nom  ); 
    
    String findBy(String nom); 
    
    List<Eleve> displayEleve(); 
    
  boolean affecterEleve(Eleve eleve, String nom);
  
    List<Enseignants> dislayEnseignant(); 
    
    void affecterEnseignant(Enseignants ens , String nom); 
    
    List<Matiere> displayMatiere(); 
    
    List<Eleve> findelevesClasse(String nom);
    
    int  calculer(); 
    
       

}
