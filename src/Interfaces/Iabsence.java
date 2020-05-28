
package Interfaces;

import Entites.Absence;
import java.util.List;


public interface Iabsence {
     boolean insertAbsence(Absence abs);
      
      void deleteAbsence(String id );
    
      void updateAbsence(Absence abs);

      List<Absence> displayAbsence();
      List<Absence> displayByEns(String ens,String cl);
        List<Absence> displayAbsencee(int form,int to);
        List<Absence> displayAbsenceByNom(String nom); 
                List<Absence> displayByClasse(String nom);

          int  calculerAbsence(); 
          int calculerNomAbsence(String nom); 
          int calculerAbsenceByClasse(String classe);
          
             Absence findByNom(String nom  ); 
             Absence findByNomMatiere(String nom , String matiere ); 
 List<Absence> displayTrie(); 
}
