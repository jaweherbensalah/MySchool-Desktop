
package service;

import Entites.Absence;
import Interfaces.Iabsence;
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

public class AbsenceService implements Iabsence{
       DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date current = new Date();
  private Connection connexion ; 
  private Statement ste; 
  private PreparedStatement pst  ; 
  private ResultSet rs; 

    public AbsenceService() {
     connexion=DataSource.getInstance().getCnx(); 
    }

    @Override
    public boolean insertAbsence(Absence abs) {
        String req ="insert into absence (date,nom_enseignant,nom_eleve,classe,heure,matiere) value (?,?,?,?,?,?)";
      try {
          pst = connexion.prepareStatement(req);
          pst.setString(1, dateFormat.format(current) );
          pst.setString(2,abs.getEns());
        pst.setString(3,abs.getEleve()); 
        pst.setString(4,abs.getClasse());
        pst.setString(5,abs.getHeure());
        pst.setString(6,abs.getMatier());
          pst.executeUpdate();
          return true; 
      } catch (SQLException ex) {
          Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
      }
      return false; 
    }

    @Override
    public void deleteAbsence(String id) {
         String req ="DELETE FROM absence where id = "+id;
           try {
               pst = connexion.prepareStatement(req);
                  pst.executeUpdate(); 
           } catch (SQLException ex) {
               Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
           }
    }

    @Override
    public void updateAbsence(Absence abs) {
        String req ="UPDATE absence Set heure= '" +abs.getHeure()+ "' Where id = " +abs.getId();
           try {
               pst =connexion.prepareStatement(req);
                 pst.executeUpdate(); 
           } catch (SQLException ex) {
               Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
           }
    }

    @Override
    public List<Absence> displayAbsencee(int form,int to) {
         String req="select * from absence limit  "+form+ "," +to; 
      List<Absence> listAbsence = new ArrayList<>(); 
         EleveService ele = new EleveService();
         ClasseService cle = new ClasseService(); 
         EnseignantsService enss = new EnseignantsService();
         MatiereService m = new MatiereService(); 
           try {
               pst=connexion.prepareStatement(req);
                rs = pst.executeQuery(); 
           while (rs.next())
      {
                Absence s= new Absence();
               s.setId(rs.getString("id"));
                s.setDate(rs.getDate("date"));
                s.setClasse(ele.findByClasse(rs.getString("classe")));
                s.setEleve(rs.getString("nom_eleve"));
                s.setEns(rs.getString("nom_enseignant"));
                s.setHeure(rs.getString("heure")); 
                s.setMatier(rs.getString("matiere"));
               listAbsence.add(s);
      }
           } catch (SQLException ex) {
               Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
           }
           return listAbsence; 
    }

    @Override
    public int calculerAbsence() {
         String x = ""; 
        int a =0; 
  String req="select COUNT(*) FROM absence ";
           try {
               pst = connexion.prepareStatement(req);
               rs = pst.executeQuery();
           rs.next(); 
      
     a =  rs.getInt(1);
           } catch (SQLException ex) {
               Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
           }
           return a; 
    }

    @Override
    public Absence findByNom(String nom) {
          String req="select * from absence Where nom_eleve = ?";
       Absence c1 = new Absence();
           try { 
               pst = connexion.prepareStatement(req);
               pst.setString(1, nom);
           rs = pst.executeQuery(); 
             while (rs.next())
      {
            c1.setId(rs.getString("id"));
                  c1.setDate(rs.getDate("date"));
                c1.setClasse(rs.getString("classe"));
                c1.setEleve(rs.getString("nom_eleve"));
                c1.setEns(rs.getString("nom_enseignant"));
                c1.setHeure(rs.getString("heure")); 
                c1.setMatier(rs.getString("matiere"));
         
       
      } 
           } catch (SQLException ex) {
               Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
           }
           return c1;
    } 

    @Override
    public List<Absence> displayTrie() {
        String req="select * from absence GROUP BY nom_eleve"; 
      List<Absence> listAbsence = new ArrayList<>(); 
         EleveService ele = new EleveService();
         ClasseService cle = new ClasseService(); 
         EnseignantsService enss = new EnseignantsService();
         MatiereService m = new MatiereService(); 
           try {
               pst=connexion.prepareStatement(req);
                rs = pst.executeQuery(); 
           while (rs.next())
      {
                Absence s= new Absence();
               s.setId(rs.getString("id"));
                s.setDate(rs.getDate("date"));
                s.setClasse(ele.findByClasse(rs.getString("classe")));
                s.setEleve(rs.getString("nom_eleve"));
                s.setEns(rs.getString("nom_enseignant"));
                s.setHeure(rs.getString("heure")); 
                s.setMatier(rs.getString("matiere"));
               listAbsence.add(s);
      }
    }      catch (SQLException ex) {
               Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
           }

    return listAbsence; 
}

    @Override
    public List<Absence> displayAbsence() {
         String req="select * from absence "; 
      List<Absence> listAbsence = new ArrayList<>(); 
         EleveService ele = new EleveService();
         ClasseService cle = new ClasseService(); 
         EnseignantsService enss = new EnseignantsService();
         MatiereService m = new MatiereService(); 
           
           try {
               pst=connexion.prepareStatement(req);
                rs = pst.executeQuery(); 
           while (rs.next())
      {
                Absence s= new Absence();
               s.setId(rs.getString("id"));
                s.setDate(rs.getDate("date"));
                s.setClasse(ele.findByClasse(rs.getString("classe")));
                s.setEleve(rs.getString("nom_eleve"));
                s.setEns(rs.getString("nom_enseignant"));
                s.setHeure(rs.getString("heure")); 
                s.setMatier(rs.getString("matiere"));
               listAbsence.add(s);
      }
           } catch (SQLException ex) {
               Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
           }
      return listAbsence; 
    } 

    @Override
    public Absence findByNomMatiere(String nom, String matiere) {
           String req="select * from absence Where nom_eleve = ? and nom_enseignant = ?";
       Absence c1 = new Absence();
           try {
               pst = connexion.prepareStatement(req);
                pst.setString(1, nom);
                pst.setString(2, matiere);
           rs = pst.executeQuery(); 
             while (rs.next())
      {
            c1.setId(rs.getString("id"));
                  c1.setDate(rs.getDate("date"));
                c1.setClasse(rs.getString("classe"));
                c1.setEleve(rs.getString("nom_eleve"));
                c1.setEns(rs.getString("nom_enseignant"));
                c1.setHeure(rs.getString("heure")); 
                c1.setMatier(rs.getString("matiere"));
         
       
      } 
           } catch (SQLException ex) {
               Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
           }
           return c1; 
    }

    @Override
    public int calculerNomAbsence(String nom ) {
     String x = ""; 
        int a =0; 
  String req="select COUNT(*) FROM absence where nom_eleve = ? ";
           
           try {    
               pst = connexion.prepareStatement(req);
                pst.setString(1, nom);
                 rs = pst.executeQuery();
           rs.next(); 
      
     a =  rs.getInt(1);
           } catch (SQLException ex) {
               Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
           }
           return a; 
    }

    @Override
    public List<Absence> displayAbsenceByNom(String nom )  {
                 String req="select * from absence  where nom_eleve = ? "; 
       List<Absence> listAbsence = new ArrayList<>(); 
       EleveService ele = new EleveService();         
           try {
               pst=connexion.prepareStatement(req);
                               pst.setString(1, nom);
                            rs = pst.executeQuery(); 
           while (rs.next())
      {
                Absence s= new Absence();
               s.setId(rs.getString("id"));
                s.setDate(rs.getDate("date"));
                s.setClasse(ele.findByClasse(rs.getString("classe")));
                s.setEleve(rs.getString("nom_eleve"));
                s.setEns(rs.getString("nom_enseignant"));
                s.setHeure(rs.getString("heure")); 
                s.setMatier(rs.getString("matiere"));
               listAbsence.add(s);
      }
           } catch (SQLException ex) {
               Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
           }
            return listAbsence; 
    }

    @Override
    public int calculerAbsenceByClasse(String classe) {
        String x = ""; 
        int a =0; 
  String req="select COUNT(*) FROM absence where classe = ? ";
           try {
               pst = connexion.prepareStatement(req);
              pst.setString(1, classe);
                 rs = pst.executeQuery();
           rs.next(); 
      
     a =  rs.getInt(1);  
           } catch (SQLException ex) {
               Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
           }
return a; 
    }

    @Override
    public List<Absence> displayByEns(String ens,String cl ) {
      String req="select * from absence  where nom_enseignant = ? and classe = ? "; 
       List<Absence> listAbsence = new ArrayList<>(); 
       EleveService ele = new EleveService();         
           try {       
               pst=connexion.prepareStatement(req);
                pst.setString(1, ens);
                                pst.setString(2, cl);

                            rs = pst.executeQuery(); 
           while (rs.next())
      {
                Absence s= new Absence();
                s.setDate(rs.getDate("date"));
                s.setEleve(rs.getString("nom_eleve"));
                s.setEns(rs.getString("nom_enseignant"));
                s.setHeure(rs.getString("heure")); 
                s.setMatier(rs.getString("matiere"));
               listAbsence.add(s);
      }
           } catch (SQLException ex) {
               Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
           }
           return listAbsence;
    }

    @Override
    public List<Absence> displayByClasse(String nom) {
         String req="select * from absence  where classe = ? "; 
       List<Absence> listAbsence = new ArrayList<>(); 
       EleveService ele = new EleveService();         
           try {
               pst=connexion.prepareStatement(req);
                     pst.setString(1, nom);
                            rs = pst.executeQuery(); 
           while (rs.next())
      {
                Absence s= new Absence();
               s.setId(rs.getString("id"));
                s.setDate(rs.getDate("date"));
                s.setClasse(ele.findByClasse(rs.getString("classe")));
                s.setEleve(rs.getString("nom_eleve"));
                s.setEns(rs.getString("nom_enseignant"));
                s.setHeure(rs.getString("heure")); 
                s.setMatier(rs.getString("matiere"));
               listAbsence.add(s);
      }
           } catch (SQLException ex) {
               Logger.getLogger(AbsenceService.class.getName()).log(Level.SEVERE, null, ex);
           }
           return listAbsence;
    }
    
} 
