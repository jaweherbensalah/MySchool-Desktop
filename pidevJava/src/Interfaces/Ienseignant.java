
package Interfaces;

import Entites.Enseignants;
import java.util.List;


public interface Ienseignant {
  List<Enseignants> displayEnseignant();
      List<Enseignants> displayEnsClasse();
            List<Enseignants> displayEnsClasse2();
      List<Enseignants> displayEnsMatiere();
      List<Enseignants> displayByClasse(String nom ); 
      boolean affecterEnseignant(Enseignants ens , String nom );
            boolean affecterEnseignant2(String ens , String nom );
      boolean affecterEnseignantMatiere(Enseignants ens , String matiere ); 
    
     Enseignants findEnsByNom(String nom);
      List<Enseignants> FindByClasse(String nom ); 
      Enseignants findbyMatiere(String nom );
             int  calculerEnseignants();  

}
