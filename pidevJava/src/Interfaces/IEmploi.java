
package Interfaces;

import Entites.EmploiDuTemps;
import java.util.List;

public interface IEmploi {
      boolean insertEmplois(EmploiDuTemps empl);
       List<EmploiDuTemps> displayEmploi();
       List<EmploiDuTemps> displayByClasse(String nom );
}
