/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entites.EmploiDuTemps;
import Interfaces.IEmploi;
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

public class EmploiService implements IEmploi{
private Connection connexion ; 
  private Statement ste; 
  private PreparedStatement pst  ; 
  private ResultSet rs; 
 DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date current = new Date();
    public EmploiService() {
         connexion=DataSource.getInstance().getCnx(); 
    }
  
  
    @Override
    public boolean insertEmplois(EmploiDuTemps empl) {
        EnseignantsService ens = new EnseignantsService(); 
        String m = ens.findbyMatiere(empl.getClasse()).getMatiere();
         String req ="insert into emploi (classe,heure,matiere,enseignant,heure1,enseignant1,matiere1,heure2,enseignant2,matiere2,heure3,enseignant3,matiere3,jour,date) value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    try {
        pst = connexion.prepareStatement(req);
          pst.setString(1,empl.getClasse());
        pst.setString(2,empl.getHeure()); 
        pst.setString(3,empl.getMatiere()); 
        pst.setString(4,empl.getEnseignant());
       pst.setString(5,empl.getHeure1());
       pst.setString(6,empl.getEnseignant1());
      pst.setString(7,empl.getMatiere1());
       pst.setString(8,empl.getHeure2());
       pst.setString(9,empl.getEnseignant2());
       pst.setString(10,empl.getMatiere2());
       pst.setString(11,empl.getHeure3());
       pst.setString(12,empl.getEnseignant3());
       pst.setString(13,empl.getMatiere3());
       pst.setString(14,empl.getJour());
       pst.setString(15,dateFormat.format(current));

          pst.executeUpdate();
        return true; 
    } catch (SQLException ex) {
        Logger.getLogger(EmploiService.class.getName()).log(Level.SEVERE, null, ex);
    }
    return false; 
    }

    @Override
    public List<EmploiDuTemps> displayEmploi() {
        String req="select * from emploi ";
                EnseignantsService ens = new EnseignantsService(); 
     List<EmploiDuTemps> listEmploi = new ArrayList<>(); 
             

    try {
        pst = connexion.prepareStatement(req);
         rs = pst.executeQuery(); 
           while (rs.next())
      {
           EmploiDuTemps s= new EmploiDuTemps();
               s.setId(rs.getString("id"));
                s.setClasse(rs.getString("classe"));
                s.setHeure(rs.getString("heure"));
                s.setMatiere(rs.getString("matiere"));
                s.setEnseignant(rs.getString("enseignant"));
                s.setHeure1(rs.getString("heure1"));
           s.setEnseignant1(rs.getString("enseignant1"));
                s.setMatiere1(rs.getString("matiere1"));
                s.setHeure2(rs.getString("heure2"));
                s.setEnseignant2(rs.getString("enseignant2"));
                s.setMatiere2(rs.getString("matiere2"));
                s.setEnseignant3(rs.getString("enseignant3"));
                s.setHeure3(rs.getString("heure3"));
                s.setMatiere3(rs.getString("matiere3"));
                s.setJour(rs.getString("jour"));
                s.setDate(rs.getDate("date"));
               listEmploi.add(s);
      }
    } catch (SQLException ex) {
        Logger.getLogger(EmploiService.class.getName()).log(Level.SEVERE, null, ex);
    }
return listEmploi; 
    }

    @Override
    public List<EmploiDuTemps> displayByClasse(String nom ) {
    String req="select * from emploi where classe = ? "; 
     EmploiDuTemps s= new EmploiDuTemps();
 List<EmploiDuTemps> listEmploi = new ArrayList<>(); 
        try {
            pst = connexion.prepareStatement(req);
               pst.setString(1, nom);
           rs = pst.executeQuery(); 
             while (rs.next())
             {
                s.setId(rs.getString("id"));
                s.setClasse(rs.getString("classe"));
                s.setHeure(rs.getString("heure"));
                s.setMatiere(rs.getString("matiere"));
                s.setEnseignant(rs.getString("enseignant"));
                s.setHeure1(rs.getString("heure1"));
           s.setEnseignant1(rs.getString("enseignant1"));
                s.setMatiere1(rs.getString("matiere1"));
                s.setHeure2(rs.getString("heure2"));
                s.setEnseignant2(rs.getString("enseignant2"));
                s.setMatiere2(rs.getString("matiere2"));
                s.setEnseignant3(rs.getString("enseignant3"));
                s.setHeure3(rs.getString("heure3"));
                s.setMatiere3(rs.getString("matiere3"));
                s.setJour(rs.getString("jour"));
                s.setDate(rs.getDate("date"));
                listEmploi.add(s); 
             }
        } catch (SQLException ex) {
            Logger.getLogger(EmploiService.class.getName()).log(Level.SEVERE, null, ex);
        }
return listEmploi; 

    }
    
}
