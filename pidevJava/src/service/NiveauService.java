
package service;

import Entites.Niveau;
import Interfaces.Iniveau;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;

public class NiveauService implements Iniveau{
 private Connection connexion ; 
  private Statement ste; 
  private PreparedStatement pst  ; 
  private ResultSet rs; 

    public NiveauService() {
         connexion=DataSource.getInstance().getCnx();
    } 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public boolean insertNiveau(Niveau niveau) {
       String req ="insert into niveau (nom) value (?)";
      try { 
          pst = connexion.prepareStatement(req);
           pst.setString(1, niveau.getNom()); // il 1 il le premier nom 

          pst.executeUpdate();
          return true; 
      } catch (SQLException ex) {
          Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
      }
       return false; 
    }

    @Override
    public void deleteNiveau(int id) {
          String req ="DELETE FROM niveau where id = "+id;
      try {
          pst = connexion.prepareStatement(req);
           pst.executeUpdate(); 
      } catch (SQLException ex) {
          Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    @Override
    public void updateNiveau(Niveau niveau) {
           String req ="UPDATE niveau Set nom= '" +niveau.getNom()+ "' Where id = " +niveau.getIdNiveau();
      try {
          pst =connexion.prepareStatement(req);
          pst.executeUpdate(); 
      } catch (SQLException ex) {
          Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
      }
    }

    @Override
    public List<Niveau> displayNiveau() {
         String req="select * from niveau "; 
      List<Niveau> listClasse = new ArrayList<>(); 
      try {
          pst=connexion.prepareStatement(req);
           rs = pst.executeQuery(); 
           while (rs.next())
      {
          Niveau cl = new Niveau(rs.getString("nom"));
          listClasse.add(cl);
      }
      } catch (SQLException ex) {
          Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
      }
       return listClasse; 
    }

    @Override
    public Niveau findByNom(String nom) {
        
      String req="select * from niveau Where nom = ?";
       Niveau c1 = new Niveau();
      try {
          pst = connexion.prepareStatement(req);
           pst.setString(1, nom);
           rs = pst.executeQuery(); 
             while (rs.next())
      {
               //   c1.setIdClasse(rs.getInt("id"));
                  c1.setNom(rs.getString("nom"));
          return c1; 
       
      } 
      } catch (SQLException ex) {
          Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
      }
       c1 = null;
     return c1; 
    }
    
    
    
    
    
    
    
    
    
    
}
