
package Interfaces;

import Entites.Matiere;
import java.util.List;

public interface Imatiere {
      List<Matiere> displayMatiere();
      public Matiere findByNom(String nom); 
      
}
