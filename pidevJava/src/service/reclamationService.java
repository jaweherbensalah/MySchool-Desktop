
package service;

import Entites.Message;
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

public class reclamationService {
  DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date current = new Date();
  private Connection connexion ; 
  private Statement ste; 
  private PreparedStatement pst  ; 
  private ResultSet rs; 

    public reclamationService() {
        connexion=DataSource.getInstance().getCnx(); 
    }
 public boolean insertReclamation(Message abs) {
        String req ="insert into reclamations (contenuReclamation,dateEnvoiReclamation,nom,titre) value (?,?,?,?)";
      try {
          pst = connexion.prepareStatement(req);
          pst.setString(2, dateFormat.format(current) );
          pst.setString(1,abs.getContenu());
        pst.setString(3,abs.getEleve()); 
         pst.setString(4,abs.getTitre());
          pst.executeUpdate();
          return true; 
      } catch (SQLException ex) {
          Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
      }
      return false; 
    }

    
    public void deleteReclamation(String id) {
         String req ="DELETE FROM reclamations where id = "+id;
           try {
               pst = connexion.prepareStatement(req);
                  pst.executeUpdate(); 
           } catch (SQLException ex) {
               Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
           }
    }

   
    public void updateReclamation(Message abs) {
       
    }

  
    public List<Message> displayReclamation() {
         String req="select * from reclamations" ; 
      List<Message> listAbsence = new ArrayList<>(); 
         EleveService ele = new EleveService();
           try {
               pst=connexion.prepareStatement(req);
                rs = pst.executeQuery(); 
           while (rs.next())
      {
                Message s= new Message();
             //  s.setId(rs.getString("idReclamation"));
                s.setDate(rs.getString("dateEnvoiReclamation"));
                s.setEleve(rs.getString("nom"));
                s.setContenu(rs.getString("contenuReclamation"));
                s.setTitre(rs.getString("titre")); 
                
               listAbsence.add(s);
      }
           } catch (SQLException ex) {
               Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
           }
           return listAbsence; 
    }

    
   
}
