
package Interfaces;

import Entites.Classe;
import Entites.Eleve;
import java.util.List;

public interface Ieleve {
   //boolean insertEleve(Eleve eleve);

   // void deleteEleve(int id );
    
  //  void updateEleve(Eleve eleve);

    List<Eleve> displayEleve();
    List<Eleve> displayElByCLasse();
    List<Eleve> displayEleveByClasse(String classe); 
    void affecterEleve(Eleve eleve, String nom);
    
        public   String findByClasse(String classe  );
         public Eleve findByNom(String nom);
       public   List<Eleve> findClasse(String nom ); 
           int  calculerEleve(); 
           int calculerEleveByClasse(String nom);


  //  Eleve findByNom(String nom  ); 
}
