
package Interfaces;

import Entites.Niveau;
import java.util.List;

public interface Iniveau {
      boolean insertNiveau(Niveau niveau);

    void deleteNiveau(int id );
    
    void updateNiveau(Niveau niveau);

    List<Niveau> displayNiveau();

    Niveau findByNom(String nom  ); 
}
