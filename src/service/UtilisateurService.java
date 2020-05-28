
package service;

import Entites.Utilisateur;
import Interfaces.Iutilisateur;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;

public class UtilisateurService implements Iutilisateur{

     private Connection connexion ; 
  private Statement ste; 
  private PreparedStatement pst  ; 
  private ResultSet rs; 

    public UtilisateurService() {
         connexion=DataSource.getInstance().getCnx();
    }
    @Override
    public Utilisateur findBynom(Utilisateur utilisateur) {
      String req="select * from utilisateur Where username = ?";
       Utilisateur c1 = new Utilisateur();
         try {
             pst = connexion.prepareStatement(req);
             pst.setString(1, utilisateur.getNom());
           rs = pst.executeQuery(); 
             while (rs.next())
      {
                  c1.setNom(rs.getString("username"));
                  c1.setRole(rs.getString("roles"));
    }
             return c1;
         } catch (SQLException ex) {
             Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
         }
           
    return c1; 
}

    @Override
    public boolean findByRoles(Utilisateur utilisateur) {
     String req="select * from utilisateur Where roles = ?  where id = "+utilisateur.getId();
       Utilisateur c1 = new Utilisateur();
         
         try {    
             pst = connexion.prepareStatement(req);
              pst.setString(1, "ROLE_ADMIN");
                rs = pst.executeQuery(); 
             while (rs.next())
      {
                  c1.setNom(rs.getString("username"));
    }
             return true; 
         } catch (SQLException ex) {
             Logger.getLogger(UtilisateurService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return  false; 
    }
} 