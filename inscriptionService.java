/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.inscription_club;
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

/**
 *
 * @author ASUS
 * 
 */


public class inscriptionService {
   private Connection connexion;
    private Statement Ste;
    private PreparedStatement pst;
    private Statement ste;
     public inscriptionService () {
        connexion = DataSource.getInstance().getCnx();
    }
   
  
    
       public List<inscription_club> afficheInscriptions() throws SQLException {
    List<inscription_club> arr=new ArrayList<>();
    ste=connexion.createStatement();
    ResultSet rs=ste.executeQuery("select * from inscription_club");
     while (rs.next()) {         
           int id=rs.getInt(1);
               String iduser=rs.getString("iduser");
                 String idclub=rs.getString("idclub");
               String stat=rs.getString("stat");
           inscription_club p=new inscription_club (id, iduser, idclub,  stat);
                arr.add(p);
        }
        return arr;
    }
       
        public int calculerTotalInscrits() {
          
         String requete = "SELECT COUNT(*) FROM inscription_club" ;
        int a = 0;
         try {
           
           Statement st =connexion.createStatement();
           ResultSet rs=st.executeQuery(requete);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
          a=Integer.parseInt(chaine);
            System.out.println(a);
           return a ;
           }
        } catch (SQLException ex) {
            Logger.getLogger(inscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return a;
    }
       
     
       public void AccepterInscriptionClub(int Id) throws SQLException{
     String mot ="Accepter";
     String req1 = "UPDATE `inscription_club` SET `stat`=? WHERE id="+Id;
         try {
           PreparedStatement ste = connexion.prepareStatement(req1);
            ste.setString(1,mot);
             ste.executeUpdate();
     
     } catch(SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     }  
        public void RefuserInscriptionClub(int Id) throws SQLException{
     String mot ="refuser";
     String req1 = "UPDATE `inscription_club` SET `stat`=? WHERE id="+Id;
         try {
           PreparedStatement ste = connexion.prepareStatement(req1);
            ste.setString(1,mot);
             ste.executeUpdate();
     
     } catch(SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     }
         public void updateStat( inscription_club a) throws SQLException {  
      String req="  UPDATE `inscription_club` SET `stat`=?  WHERE `id`='" +a.getId()+"'";   
   
            pst=connexion.prepareStatement(req);
            //pst.setInt(1,a.getId());
            pst.setString(2,a.getStat());
            //pst.setString(3,a.getDescription());
            //pst.setInt(4,a.getEffectif());
            //pst.setString (5,a.getDomaine());
            pst.executeUpdate();
            System.out.println("statut modifié");
        
    }
         
       public void InscriptionClub (inscription_club t) throws SQLException {
        ste = connexion.createStatement();
        String requeteInsert = "INSERT INTO  `inscription_club`(`id`, `iduser`, `idclub`, `stat`)  VALUES (NULL, '" + t.getIduser() + "', '" + t.getIdclub() + "', '" + t.getStat() +"');";
        try {
            ste=connexion.createStatement();
            ste.executeUpdate(requeteInsert);
            
        } catch (SQLException ex) {
            Logger.getLogger(inscriptionService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
 
}

