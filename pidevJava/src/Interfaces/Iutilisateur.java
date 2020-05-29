
package Interfaces;

import Entites.Utilisateur;

public interface Iutilisateur {
  Utilisateur findBynom (Utilisateur utilisateur );   
    boolean findByRoles (Utilisateur utilisateur );   

}
