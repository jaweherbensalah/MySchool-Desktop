
package service;

import Entites.Classe;
import Entites.Eleve;
import Entites.Enseignants;
import Entites.Matiere;
import Interfaces.Iclasse;
import java.util.List;
import util.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ClasseService implements Iclasse{
  private Connection connexion ; 
  private Statement ste; 
  private PreparedStatement pst  ; 
  private ResultSet rs; 
  
  public ClasseService()
  {
       connexion=DataSource.getInstance().getCnx();
  }
  
  
    @Override
    public boolean insertClasse(Classe classe) {
          String req ="insert into classe (nom,niveau) value (?,?)";
      try { 
          pst = connexion.prepareStatement(req);
           pst.setString(1, classe.getNom()); // il 1 il le premier nom 
          pst.setString(2, classe.getNiveaux()); // il 1 il le premier nom 

          pst.executeUpdate();
          return true; 
      } catch (SQLException ex) {
          Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
      }
       return false; 
    }

    @Override
    public boolean deleteClasse( Classe classe ) {
         String req ="DELETE FROM classe where id = "+classe.getIdClasse();
      try {
          pst = connexion.prepareStatement(req);
           pst.executeUpdate(); 
           return true; 
      } catch (SQLException ex) {
          Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
      }
      return false; 
    }

    @Override
    public boolean updateClasse(Classe classe) {
          String req ="UPDATE classe Set nom= '" +classe.getNom()+ "' Where id = " +classe.getIdClasse();
      try {
          pst =connexion.prepareStatement(req);
          pst.executeUpdate(); 
          return true; 
      } catch (SQLException ex) {
          Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
      }
      return false; 
    }

    @Override
    public List<Classe> displayClasse() {
        String req="select * from classe "; 
      List<Classe> listClasse = new ArrayList<>(); 
      try {
          pst=connexion.prepareStatement(req);
           rs = pst.executeQuery(); 
           while (rs.next())
      {
          Classe cl = new Classe(rs.getString("id"),rs.getString("nom"),rs.getString("niveau"));
          listClasse.add(cl);
      }
      } catch (SQLException ex) {
          Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
      }
       return listClasse;   
    }

    @Override
    public Classe findByNom(String nom) {
         String req="select * from classe Where nom = ?";
       Classe c1 = new Classe();
      
      try {
          pst = connexion.prepareStatement(req);
           pst.setString(1, nom);
           rs = pst.executeQuery(); 
             while (rs.next())
      {
            c1.setIdClasse(rs.getString("id"));
                  c1.setNom(rs.getString("nom"));
                  c1.setNiveaux(rs.getString("niveau"));
          return c1; 
       
      } 
      } catch (SQLException ex) {
          Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
      }
      
     return c1; 
    }

    @Override
    public List<Eleve> displayEleve() {
            String req="select * from eleve "; 
      List<Eleve> listEleve = new ArrayList<>(); 
     try {
         pst=connexion.prepareStatement(req);
          rs = pst.executeQuery(); 
           while (rs.next())
      {
          Eleve el = new Eleve(rs.getInt("id"),rs.getString("nom"));
          listEleve.add(el);
      }
     } catch (SQLException ex) {
         Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
     }
     return listEleve; 
    }

    @Override
    public boolean affecterEleve(Eleve eleve, String nom) {
          String req ="UPDATE utilisateur Set classe='"+nom+"' where id ="+eleve.getId();
      try {
          pst = connexion.prepareStatement(req);
            pst.executeUpdate(); 
            return true; 
      } catch (SQLException ex) {
          Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
      }
        return false;
    }

    @Override
    public List<Enseignants> dislayEnseignant() {
         String req="select * from teacher "; 
      List<Enseignants> listEnseignant = new ArrayList<>(); 
     
      try {
          pst=connexion.prepareStatement(req);
           rs = pst.executeQuery(); 
           while (rs.next())
      {
        Enseignants el = new Enseignants(rs.getInt("id"),rs.getString("nom"));
          listEnseignant.add(el); 
      }
      } catch (SQLException ex) {
          Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
      }
        return listEnseignant;   
    }

    @Override
    public void affecterEnseignant(Enseignants ens, String nom) {
         String req ="UPDATE utilisateur Set classe='"+nom+"' where id ="+ens.getId();
      try {
          pst = connexion.prepareStatement(req);
           pst.executeUpdate();
      } catch (SQLException ex) {
          Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
      }
         
    }

    @Override
  public String findBy(String nom)  { 
  String req="select * from classe Where nom = ?";
       Classe c1 = new Classe();
      try {
          pst = connexion.prepareStatement(req);
           pst.setString(1, nom);
           rs = pst.executeQuery(); 
             while (rs.next())
      {
             c1.setIdClasse(rs.getString("id"));
                  c1.setNom(rs.getString("nom"));
                  c1.setNiveaux(rs.getString("niveau"));
          return c1.getIdClasse(); 
       
      } 
      } catch (SQLException ex) {
          Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
      }
      return c1.getIdClasse();
    }

    @Override
    public List<Matiere> displayMatiere() {
          String req="select * from matier "; 
      List<Matiere> listMatiere = new ArrayList<>(); 
      try {
          pst=connexion.prepareStatement(req);
           rs = pst.executeQuery(); 
           while (rs.next())
      {
          Matiere cl = new Matiere(rs.getString("id"),rs.getString("nomMat"));
          listMatiere.add(cl);
      }
      } catch (SQLException ex) {
          Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
      }
      return listMatiere; 
    }

    @Override
    public List<Eleve> findelevesClasse(String nom) {
         String req="select * from eleve Where classe = ?";
                     List<Eleve> listEleve = new ArrayList<>(); 
       Eleve c1 = new Eleve();
      try {
          pst = connexion.prepareStatement(req);
           pst.setString(1, nom);
           rs = pst.executeQuery(); 
             while (rs.next())
      {
                  c1.setNom(rs.getString("nom"));
                  
          listEleve.add(c1); 
      } 
      } catch (SQLException ex) {
          Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
      }
       return listEleve; 
    }

    @Override
    public int  calculer() {
        String x = ""; 
        int a =0; 
  String req="select COUNT(*) FROM classe";
  
      try { 
           pst = connexion.prepareStatement(req);
          rs = pst.executeQuery();
           rs.next(); 
      
     a =  rs.getInt(1);
      } catch (SQLException ex) {
          Logger.getLogger(ClasseService.class.getName()).log(Level.SEVERE, null, ex);
      }
           
         // x = Integer.toString(a);
      
      return  a; 
    }


}
