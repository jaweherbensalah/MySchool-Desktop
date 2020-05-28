
package service;

import Entites.Matiere;
import Interfaces.Imatiere;
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

public class MatiereService implements Imatiere{
 private Connection connexion ; 
  private Statement ste; 
  private PreparedStatement pst  ; 
  private ResultSet rs; 

    public MatiereService() {
  connexion=DataSource.getInstance().getCnx();

    }
    
    
    @Override
    public List<Matiere> displayMatiere() {
          String req="select * from matiere "; 
      List<Matiere> listMatiere = new ArrayList<>(); 
     try {
         pst=connexion.prepareStatement(req);
           rs = pst.executeQuery(); 
           while (rs.next())
      {
          Matiere cl = new Matiere(rs.getString("idMat"),rs.getString("nomMat"));
          listMatiere.add(cl);
          
      }
     } catch (SQLException ex) {
         Logger.getLogger(MatiereService.class.getName()).log(Level.SEVERE, null, ex);
     }
     return listMatiere; 
    }

    @Override
    public Matiere findByNom(String nom) {
          String req="select * from matier Where nomMat = ?";
       Matiere c1 = new Matiere();
     try {
         pst = connexion.prepareStatement(req);
            pst.setString(1, nom);
           rs = pst.executeQuery(); 
             while (rs.next())
      {
                  c1.setNomMat(rs.getString("nomMat"));
                  c1.setIdMat(rs.getString("id"));
          return c1; 
      } 
     } catch (SQLException ex) {
         Logger.getLogger(MatiereService.class.getName()).log(Level.SEVERE, null, ex);
     }
      return  c1; 
    }
    
    //////////////////////////////////////////
     public void insertMatiere(Matiere M)
    {String req="insert into matiere (idMat,nomMat,coefficient,description) values(?,?,?,?)";
    try {
          pst = connexion.prepareStatement(req);
        pst.setString(1,M.getIdMat()); 
        pst.setString(1,M.getNomMat()); 
        pst.setFloat(1,M.getCoefficient());
        pst.setString(1,M.getDescription()); 
         pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MatiereService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void insertMatierePst(Matiere M)
    {
    String req="insert into matiere(idMat,nomMat,coefficient,description) values(?,?,?,?)";
    
}
     public List<Matiere> getAll()
    {
      String req="select * from matiere";
      List<Matiere> list=new ArrayList<>();
        try {
            ste=connexion.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next())
            {
             list.add(new Matiere(rs.getString("idMat"),rs.getString(2),rs.getFloat("coefficient"),rs.getString("description")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MatiereService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    } 
    public void updateMatiere(Matiere M)
    {String req="update  matiere SET nomMat ='" +M.getNomMat()+"' " + "WHERE idMat = '"+M.getIdMat()+"'" ;
    try {
            ste=connexion.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(MatiereService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void DeleteMatiere(Matiere M)
    {String req="DELETE  from  matiere  WHERE idMat = '"+M.getIdMat()+"'" ;
    try {
            ste=connexion.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(MatiereService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   
    public List<Matiere> getTrier() throws SQLException {
    List<Matiere> arr=new ArrayList<>();
    ste=connexion.createStatement();
    ResultSet rs=ste.executeQuery("select * from matiere ORDER BY idMat ");
     while (rs.next()) {                
               
              String idMat= rs.getString("idMat");
              String nomMat= rs.getString(2);
              float coefficient= rs.getFloat("coefficient");
              String description= rs.getString("description");
               Matiere p=new Matiere(idMat, nomMat,coefficient,description );
     arr.add(p);
     }
     
    return arr;
    }
}
