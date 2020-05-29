
package service;

import Entites.Classe;
import Entites.Eleve;
import Interfaces.Ieleve;
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

public class EleveService implements  Ieleve{
 private Connection connexion ; 
  private Statement ste; 
  private PreparedStatement pst  ; 
  private ResultSet rs; 

    public EleveService() {
 connexion=DataSource.getInstance().getCnx();
    }
  
  
  
  
    @Override
    public List<Eleve> displayEleve() {
          String req="select * from utilisateur where roles = ? "; 
                EleveService ele = new EleveService();

      List<Eleve> listEleve = new ArrayList<>(); 
     try {
         pst=connexion.prepareStatement(req);
          pst.setString(1, "a:1:{i:0;s:10:\"ROLE_ELEVE\";}");
              rs = pst.executeQuery(); 
           while (rs.next())
      {
        Eleve e = new Eleve();
        e.setId(rs.getInt("id"));
       e.setClasse(ele.findByClasse(rs.getString("classe")));
        e.setNom(rs.getString("username"));
        e.setEmail(rs.getString("email"));
        listEleve.add(e); 
      }
     } catch (SQLException ex) {
         Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
     }
     
    
     return listEleve; 
    }

    @Override
    public void affecterEleve(Eleve eleve, String nom) {
  String req ="UPDATE eleve Set classe='"+nom+"' where id ="+eleve.getId();
        try {
         pst =connexion.prepareStatement(req);
          pst.executeUpdate(); 
     } catch (SQLException ex) {
         Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
     }
         
    }

    @Override
    public String findByClasse(String classe) {
           String req="select * from utilisateur Where username = ? AND roles = ?";
       Eleve c1 = new Eleve();
       String g = new String();
     try {
         pst = connexion.prepareStatement(req);
           pst.setString(1,classe);
                    pst.setString(2,"a:1:{i:0;s:10:\"ROLE_ELEVE\";}");
           rs = pst.executeQuery(); 
             while (rs.next())
      {
                  c1.setNom(rs.getString("username"));
                  c1.setClasse(rs.getString("classe"));
                  c1.setId(rs.getInt("id"));
          g = rs.getString("classe"); 
      } 
     } catch (SQLException ex) {
         Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
     }
        return  g; 
    }

    @Override
    public Eleve findByNom(String nom) {
         String req="select * from utilisateur where roles = ?  AND username = ?";
       Eleve c1 = new Eleve();
         Eleve e = new Eleve();
     try {
         pst = connexion.prepareStatement(req);
             pst.setString(1, "a:1:{i:0;s:10:\"ROLE_ELEVE\";}");
         pst.setString(2, nom);
           rs = pst.executeQuery(); 
             while (rs.next())
      {
                
        e.setId(rs.getInt("id"));
      
        e.setNom(rs.getString("username"));
        e.setEmail(rs.getString("email"));
      
          return e; 
      } 
     } catch (SQLException ex) {
         Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
     }
     return e ;
    }
   
    @Override
    public List<Eleve> findClasse(String nom) {
            String req="select * from utilisateur where roles = ?  AND classe = ?";
                     List<Eleve> listEleve = new ArrayList<>(); 
                EleveService ele = new EleveService();

      
     try {
         pst = connexion.prepareStatement(req);
         pst.setString(1, "a:1:{i:0;s:10:\"ROLE_ELEVE\";}");
           pst.setString(2, nom);
           rs = pst.executeQuery(); 
             while (rs.next())
      {
        Eleve e = new Eleve();
        e.setId(rs.getInt("id"));
       e.setClasse(rs.getString("classe"));
        e.setNom(rs.getString("username"));
        e.setEmail(rs.getString("email"));
        listEleve.add(e); 
      } 
     } catch (SQLException ex) {
         Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
     }
     return listEleve; 
    }

    @Override
    public List<Eleve> displayElByCLasse() {
        
      String req="select * from utilisateur where roles = ?  AND classe = ?"; 
                EleveService ele = new EleveService();

      List<Eleve> listEleve = new ArrayList<>(); 
     try {
         pst=connexion.prepareStatement(req);
          pst.setString(1, "a:1:{i:0;s:10:\"ROLE_ELEVE\";}");
           pst.setString(2, "");
              rs = pst.executeQuery(); 
           while (rs.next())
      {
        Eleve e = new Eleve();
        e.setId(rs.getInt("id"));
       e.setClasse(ele.findByClasse(rs.getString("classe")));
        e.setNom(rs.getString("username"));
        e.setEmail(rs.getString("email"));
        listEleve.add(e); 
      }
     } catch (SQLException ex) {
         Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
     }
     
    
     return listEleve;   
        
    }

    @Override
    public List<Eleve> displayEleveByClasse(String cla) {
           String req="select * from utilisateur where roles = ? AND classe = ? ";  
       EleveService ele = new EleveService();
      List<Eleve> listEleve = new ArrayList<>(); 
    
     try {
         pst=connexion.prepareStatement(req);
        
         pst.setString(1,"a:1:{i:0;s:10:\"ROLE_ELEVE\";}");
            pst.setString(2,cla);
              rs = pst.executeQuery(); 
           while (rs.next())
      {
        Eleve e = new Eleve();
        e.setId(rs.getInt("id"));
        e.setNom(rs.getString("username"));
        e.setEmail(rs.getString("email"));
        listEleve.add(e); 
      } 
     } catch (SQLException ex) {
         Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
     }
       return listEleve;  
    }

    @Override
    public int calculerEleve() {
           String x = ""; 
        int a =0; 
  String req="select COUNT(*) FROM utilisateur where roles = ? ";
  
     try {
         pst = connexion.prepareStatement(req);
          pst.setString(1, "a:1:{i:0;s:10:\"ROLE_ELEVE\";}");
          rs = pst.executeQuery();
           rs.next(); 
      
     a =  rs.getInt(1);
     } catch (SQLException ex) {
         Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
     }
         return a; 
    }

    @Override
    public int calculerEleveByClasse(String nom) {
         String x = ""; 
        int a =0; 
  String req="select COUNT(*) FROM utilisateur where roles = ? AND classe = ?";
     try {
         pst = connexion.prepareStatement(req);
             pst.setString(1, "a:1:{i:0;s:10:\"ROLE_ELEVE\";}");
                 pst.setString(2,nom);
                  rs = pst.executeQuery();
           rs.next(); 
      
     a =  rs.getInt(1);
     } catch (SQLException ex) {
         Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
     }
      return a; 
    }
    
    
}
