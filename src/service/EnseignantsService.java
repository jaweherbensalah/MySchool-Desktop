
package service;

import Entites.Enseignants;
import Interfaces.Ienseignant;
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


public class EnseignantsService implements Ienseignant{
  private Connection connexion ; 
  private Statement ste; 
  private PreparedStatement pst  ; 
  private ResultSet rs;   

    public EnseignantsService() {
     connexion=DataSource.getInstance().getCnx();
    
    }

    @Override
    public List<Enseignants> displayEnseignant() {
           String req="select * from utilisateur where roles = ? "; 
      List<Enseignants> listEnseignant = new ArrayList<>(); 
                      EleveService ele = new EleveService();

      try {
          pst=connexion.prepareStatement(req);
            pst.setString(1, "a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}");
           rs = pst.executeQuery(); 
           while (rs.next())
      {
        Enseignants e = new Enseignants();
         e.setId(rs.getInt("id"));
     e.setClasse(rs.getString("classe"));
        e.setNom(rs.getString("username"));
        e.setEmail(rs.getString("email"));
          e.setMatiere(rs.getString("matiere"));
          e.setClasse2(rs.getString("classe2"));
          listEnseignant.add(e); 
      }
      } catch (SQLException ex) {
          Logger.getLogger(EnseignantsService.class.getName()).log(Level.SEVERE, null, ex);
      }
      return listEnseignant; 
    }

    @Override
    public List<Enseignants> displayEnsClasse() {
          String req="select * from utilisateur where roles = ?  AND classe = ?"; 
                EleveService ele = new EleveService();

      List<Enseignants> listEns = new ArrayList<>(); 
     
      try {
          pst=connexion.prepareStatement(req);
           pst.setString(1, "a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}");
           pst.setString(2, " ");
              rs = pst.executeQuery(); 
           while (rs.next())
      {
        Enseignants e = new Enseignants();
        e.setId(rs.getInt("id"));
       e.setClasse(rs.getString("classe"));
        e.setNom(rs.getString("username"));
        e.setEmail(rs.getString("email"));
        listEns.add(e); 
      }
      } catch (SQLException ex) {
          Logger.getLogger(EnseignantsService.class.getName()).log(Level.SEVERE, null, ex);
      }
      return listEns;
    }

    @Override
    public List<Enseignants> displayByClasse(String nom ) {
        String req="select * from utilisateur where roles = ?  AND classe = ?"; 
                EleveService ele = new EleveService();
      List<Enseignants> listEns = new ArrayList<>(); 
    
      try { 
          pst=connexion.prepareStatement(req);
           pst.setString(1, "a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}");
           pst.setString(2, nom);
              rs = pst.executeQuery(); 
           while (rs.next())
      {
        Enseignants e = new Enseignants();
        e.setId(rs.getInt("id"));
       e.setClasse(rs.getString("classe"));
        e.setNom(rs.getString("username"));
        e.setEmail(rs.getString("email"));
        listEns.add(e); 
      }
      } catch (SQLException ex) {
          Logger.getLogger(EnseignantsService.class.getName()).log(Level.SEVERE, null, ex);
      }
     return  listEns; 
    }

    /**
     *
     * @param ens
     * @param nom
     * @return
     */
    @Override
    public boolean affecterEnseignant(Enseignants ens, String nom) {
         String req ="UPDATE utilisateur Set classe='"+nom+"' where id ="+ens.getId();
    
      try {
          pst = connexion.prepareStatement(req);
           pst.executeUpdate();
           return true; 
      } catch (SQLException ex) {
          Logger.getLogger(EnseignantsService.class.getName()).log(Level.SEVERE, null, ex);
      }
          return false; 
    }

    @Override
    public int calculerEnseignants() {
          String x = ""; 
        int a =0; 
  String req="select COUNT(*) FROM utilisateur where roles = ? ";
      try {
          pst = connexion.prepareStatement(req);
     pst.setString(1, "a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}");
 rs = pst.executeQuery();
           rs.next(); 
      
     a =  rs.getInt(1);
      } catch (SQLException ex) {
          Logger.getLogger(EnseignantsService.class.getName()).log(Level.SEVERE, null, ex);
      }
      return a; 
    }

    @Override
    public boolean affecterEnseignantMatiere(Enseignants ens, String matiere) {
         String req ="UPDATE utilisateur Set matiere='"+matiere+"' where id ="+ens.getId();
      
      try {
          pst = connexion.prepareStatement(req);
            pst.executeUpdate();
           return true; 
      } catch (SQLException ex) {
          Logger.getLogger(EnseignantsService.class.getName()).log(Level.SEVERE, null, ex);
      }
      return false;
    }

    @Override
    public List<Enseignants> displayEnsMatiere() {
         String req="select * from utilisateur where roles = ?  AND matiere = ?"; 
                EleveService ele = new EleveService();

      List<Enseignants> listEns = new ArrayList<>(); 
      try {
          pst=connexion.prepareStatement(req);
           pst.setString(1, "a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}");
           pst.setString(2, "");
              rs = pst.executeQuery(); 
           while (rs.next())
      {
        Enseignants e = new Enseignants();
        e.setId(rs.getInt("id"));
       e.setClasse(rs.getString("classe"));
        e.setNom(rs.getString("username"));
        e.setEmail(rs.getString("email"));
        listEns.add(e); 
      }
      } catch (SQLException ex) {
          Logger.getLogger(EnseignantsService.class.getName()).log(Level.SEVERE, null, ex);
      }
      return listEns;
    }

    @Override
    public Enseignants findbyMatiere(String nom) {
      String req="select matiere from utilisateur where roles = ? and classe = ? "; 
         Enseignants e = new Enseignants();
      try {
          pst=connexion.prepareStatement(req);
             pst.setString(1, "a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}");
           pst.setString(2, nom);
         rs = pst.executeQuery(); 
         rs.next();
     
      
       e.setMatiere(rs.getString("matiere"));
      
       
      } catch (SQLException ex) {
          Logger.getLogger(EnseignantsService.class.getName()).log(Level.SEVERE, null, ex);
      }
      return e; 
    }

    @Override
    public Enseignants findEnsByNom(String nom) {
       String req="select matiere from utilisateur where roles = ? and username = ? "; 
         Enseignants e = new Enseignants();
      try {
          pst=connexion.prepareStatement(req);
            pst.setString(1, "a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}");
           pst.setString(2, nom);
         rs = pst.executeQuery(); 
         rs.next();
               e.setMatiere(rs.getString("matiere"));
      } catch (SQLException ex) {
          Logger.getLogger(EnseignantsService.class.getName()).log(Level.SEVERE, null, ex);
      }
return e; 
    }

    @Override
    public List<Enseignants> displayEnsClasse2() {
           String req="select * from utilisateur where roles = ?  AND classe2 = ?"; 
                EleveService ele = new EleveService();
      List<Enseignants> listEns = new ArrayList<>(); 
      try {
          pst=connexion.prepareStatement(req);
            pst.setString(1, "a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}");
           pst.setString(2, " ");
              rs = pst.executeQuery(); 
           while (rs.next())
      {
        Enseignants e = new Enseignants();
        e.setId(rs.getInt("id"));
       e.setClasse(rs.getString("classe"));
        e.setNom(rs.getString("username"));
        e.setEmail(rs.getString("email"));
        listEns.add(e); 
      }
      } catch (SQLException ex) {
          Logger.getLogger(EnseignantsService.class.getName()).log(Level.SEVERE, null, ex);
      }
      return listEns;
    }

    @Override
    public boolean affecterEnseignant2(String ens, String nom) {
        String req ="UPDATE utilisateur Set classe2 = ? where username = ? AND roles = ?";
      try {
          pst = connexion.prepareStatement(req);
                pst.setString(1,nom);
                 pst.setString(2,ens);
             pst.setString(3, "a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}");
            pst.executeUpdate();
           return true; 
      } catch (SQLException ex) {
          Logger.getLogger(EnseignantsService.class.getName()).log(Level.SEVERE, null, ex);
      }
      return false;
    }

    @Override
    public List<Enseignants> FindByClasse(String nom) {
        String req="select * from utilisateur where roles = ? AND username = ? "; 
              List<Enseignants> listEns = new ArrayList<>(); 
      try {
          pst=connexion.prepareStatement(req);
             pst.setString(1, "a:1:{i:0;s:15:\"ROLE_ENSEIGNANT\";}");
              pst.setString(2,nom);
                 rs = pst.executeQuery(); 
           while (rs.next())
      {
        Enseignants e = new Enseignants();
//        e.setId(rs.getInt("id"));
       e.setClasse(rs.getString("classe"));
        e.setNom(rs.getString("username"));
        e.setEmail(rs.getString("email"));
        e.setClasse2(rs.getString("classe2"));
        listEns.add(e); 
      }
      } catch (SQLException ex) {
          Logger.getLogger(EnseignantsService.class.getName()).log(Level.SEVERE, null, ex);
      }
      return listEns;
    }
}
