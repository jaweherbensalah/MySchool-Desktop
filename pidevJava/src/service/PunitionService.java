
package service;

import Entites.Punition;
import Interfaces.Ipunition;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import util.DataSource;

public class PunitionService implements Ipunition{
     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date current = new Date();
  private Connection connexion ; 
  private Statement ste; 
  private PreparedStatement pst  ; 
  private ResultSet rs;    

    public PunitionService() {
            connexion=DataSource.getInstance().getCnx();
    }

    @Override
    public boolean insertPunition(Punition puinition) {
        String req ="insert into punichment (type,classe,eleve,date) value (?,?,?,?)";
      try {
          pst = connexion.prepareStatement(req);
          pst.setString(1,puinition.getCause());
          pst.setString(2,puinition.getClasse());
        pst.setString(3,puinition.getEleve()); 
        pst.setString(4,dateFormat.format(current));
          pst.executeUpdate();
          
          return true; 
      } catch (SQLException ex) {
          Logger.getLogger(PunitionService.class.getName()).log(Level.SEVERE, null, ex);
      }
      return false;
    }

    @Override
    public boolean updateClasse(Punition puinition) {
         String req ="UPDATE punichment Set type= '" +puinition.getCause()+ "' , classe= '" +puinition.getClasse()+ "' ,  eleve= '" +puinition.getEleve() + "' Where id = " +puinition.getId();
      
         try {
             pst =connexion.prepareStatement(req);
             pst.executeUpdate(); 
          return true; 
         } catch (SQLException ex) {
             Logger.getLogger(PunitionService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return false; 
    }

    @Override
    public List<Punition> displayPunition() {
        String req="select * from punichment "; 
      List<Punition> listpunition = new ArrayList<>(); 
      EleveService ele = new EleveService();
ClasseService cl = new ClasseService();
         try {
             pst=connexion.prepareStatement(req);
              rs = pst.executeQuery(); 
           while (rs.next())
      {
        Punition e = new Punition();
        e.setId(rs.getString("id"));
       e.setClasse(rs.getString("classe"));
        e.setEleve(rs.getString("eleve"));
        e.setCause(rs.getString("type"));
        e.setDate(rs.getDate("date"));
        listpunition.add(e); 
      }
         } catch (SQLException ex) {
             Logger.getLogger(PunitionService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return listpunition;
    }

    @Override
    public void deletePunition(String id) {
        String req ="DELETE FROM punichment where id = "+id;
          
         try {
             pst = connexion.prepareStatement(req);
              pst.executeUpdate(); 
         } catch (SQLException ex) {
             Logger.getLogger(PunitionService.class.getName()).log(Level.SEVERE, null, ex);
         }
                 
    }

    @Override
    public int calculerPunition() {
         String x = ""; 
        int a =0; 
  String req="select COUNT(*) FROM punichment ";
           
         try {
             pst = connexion.prepareStatement(req);
              rs = pst.executeQuery();
           rs.next(); 
      
     a =  rs.getInt(1);
         } catch (SQLException ex) {
             Logger.getLogger(PunitionService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return a; 
    }

    @Override
    public Punition findByNom(String nom) {
         String req="select * from punichment Where eleve = ?";
       Punition c1 = new Punition();
         try {
             pst = connexion.prepareStatement(req);
                 pst.setString(1, nom);
           rs = pst.executeQuery(); 
             while (rs.next())
      {
            c1.setId(rs.getString("id"));
                  c1.setEleve(rs.getString("eleve"));
                  c1.setClasse(rs.getString("classe")); 
                  c1.setCause(rs.getString("type"));
        c1.setDate(rs.getDate("date"));
          return c1; 
       
      } 
         } catch (SQLException ex) {
             Logger.getLogger(PunitionService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return c1;
    }

    @Override
    public Punition findByNomEleve(String nom, String classe) {
     String req="select * from punichment Where eleve = ? and classe = ?";
       Punition c1 = new Punition();
         try {   
             pst = connexion.prepareStatement(req);
              pst.setString(1, nom);
           pst.setString(2, classe);
           rs = pst.executeQuery(); 
             while (rs.next())
      {
            c1.setId(rs.getString("id"));
                  c1.setEleve(rs.getString("eleve"));
                  c1.setClasse(rs.getString("classe")); 
                  c1.setCause(rs.getString("type"));
        c1.setDate(rs.getDate("date"));
         }
         }catch (SQLException ex) {
             Logger.getLogger(PunitionService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return c1; 
    }

    @Override
    public int calculerNomPunition(String nom)  {
          String x = ""; 
        int a =0; 
  String req="select COUNT(*) FROM punichment where eleve = ?";
         try {
             pst = connexion.prepareStatement(req);
           pst.setString(1, nom);
             rs = pst.executeQuery();
           rs.next(); 
      
     a =  rs.getInt(1);
       
         } catch (SQLException ex) {
             Logger.getLogger(PunitionService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return a; 
    }

    @Override
    public List<Punition> displayAbsenceByNom(String nom) {
          String req="select * from punichment where eleve = ?  "; 
            List<Punition> listpunition = new ArrayList<>();
         try {
             pst=connexion.prepareStatement(req);
               pst.setString(1, nom);
              rs = pst.executeQuery(); 
           while (rs.next())
      {
        Punition e = new Punition();
        e.setId(rs.getString("id"));
       e.setClasse(rs.getString("classe"));
        e.setEleve(rs.getString("eleve"));
        e.setCause(rs.getString("type"));
        e.setDate(rs.getDate("date"));
        listpunition.add(e); 
      }
         } catch (SQLException ex) {
             Logger.getLogger(PunitionService.class.getName()).log(Level.SEVERE, null, ex);
         }
         return listpunition;
    }
  
  
}
