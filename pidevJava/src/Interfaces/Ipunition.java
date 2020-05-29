
package Interfaces;

import Entites.Punition;
import java.util.List;

public interface Ipunition {
   boolean insertPunition(Punition puinition);
   boolean updateClasse(Punition puinition);
 List<Punition> displayAbsenceByNom(String nom);
    List<Punition> displayPunition();
    void deletePunition(String id );
       int  calculerPunition(); 
          Punition findByNom(String nom  );
          Punition findByNomEleve(String nom ,String classe); 
                    int calculerNomPunition(String nom); 

}
