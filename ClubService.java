/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entities.club;
import entities.inscription_club;
import java.sql.*;
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


public class ClubService {

   
     

    private Connection connexion;
   // private Statement Ste;//l'interface ta3tina methode d'excution de requete bd 
    private PreparedStatement pst;//sous interface men statement pour les requete parametré 'insert update ..)rapide //
    private Statement ste;

    public ClubService() {
        connexion = DataSource.getInstance().getCnx(); //3ayetna l sigleton mta3na 
    }

    public ClubService(String text, String text0, String text1, String text2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
   
      public void ajoutClub(club t) throws SQLException {
        ste = connexion.createStatement();
        String requeteInsert = "INSERT INTO club (`id`, `nom_club`,`description` ,`effectif`, `domaine`) VALUES (NULL, '" + t.getNom_club() + "', '" + t.getDescription() + "', '" + t.getEffectif() + "', '" + t.getDomaine() +"');";
        try {
            ste=connexion.createStatement();
            ste.executeUpdate(requeteInsert);
            
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void ajoutClub2(club a){//paramétré
        String req = "INSERT INTO `club`( `nom_club`, `description`, `effectif`, `domaine`) VALUES(?,?,?,?)";
        
        try {
            pst=connexion.prepareStatement(req);
       
            pst.setString(1,a.getNom_club());
            pst.setString(2,a.getDescription());
            pst.setInt(3,a.getEffectif());
            pst.setString (4,a.getDomaine());
            pst.executeUpdate();
        } catch (SQLException ex) {
            
            Logger.getLogger(club.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateClub( club a) throws SQLException {  
      String req="  UPDATE `club` SET `id`=?,`nom_club`=?,`description`=?,`effectif`=?,`domaine`=?  WHERE `id`='" +a.getId()+"'";   
   
            pst=connexion.prepareStatement(req);
            pst.setString(1,a.getId());
            pst.setString(2,a.getNom_club());
            pst.setString(3,a.getDescription());
            pst.setInt(4,a.getEffectif());
            pst.setString (5,a.getDomaine());
            pst.executeUpdate();
            System.out.println("done");
        
    }
    public void updateNom( club a) throws SQLException {  
      String req="  UPDATE `club` SET `nom_club`=?  WHERE `id`='" +a.getId()+"'";   
   
            pst=connexion.prepareStatement(req);
            //pst.setInt(1,a.getId());
            pst.setString(2,a.getNom_club());
            //pst.setString(3,a.getDescription());
            //pst.setInt(4,a.getEffectif());
            //pst.setString (5,a.getDomaine());
            pst.executeUpdate();
            System.out.println("done");
        
    }
 
    public void DeleteClub(String id) {
        try {
            String req = "DELETE FROM club WHERE id=?";
            pst = connexion.prepareStatement(req);
            pst.setString(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        
    }
     public List<club> afficheClubs()   {
        Statement stm;
          List<club> clubs = new ArrayList<>();
        try {
            stm = connexion.createStatement();
             String query = "select * from `club`";
            ResultSet rst = stm.executeQuery(query);//executer requete de type select 
      
        while (rst.next()) {
            club p2 = new club();
            p2.setId(rst.getString("id"));
            p2.setNom_club(rst.getString(2));
            p2.setDescription(rst.getString("description"));
            p2.setEffectif(rst.getInt("effectif"));
            p2.setDomaine(rst.getString("domaine"));
            clubs.add(p2);
        }
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return clubs;
    }
      public List<club> getTrier() throws SQLException {
    List<club> arr=new ArrayList<>();
    ste=connexion.createStatement();
    ResultSet rs=ste.executeQuery("select * from club ORDER BY effectif DESC");
     while (rs.next()) {                
                String id=rs.getString(1);
               String nom_club=rs.getString("nom_club");
               String description=rs.getString("description");
               int effectif=rs.getInt("effectif");
               String domaine=rs.getString("domaine");
               club p=new club(id, nom_club, description, effectif,domaine);
     arr.add(p);
     }
     
    System.out.println("trié avec succés ");
    return arr;
    }
      public List<club> getTrierParNom() throws SQLException {
    List<club> arr=new ArrayList<>();
    ste=connexion.createStatement();
    ResultSet rs=ste.executeQuery("select * from club ORDER BY nom_club ASC");
     while (rs.next()) {                
               String id=rs.getString(1);
               String nom_club=rs.getString("nom_club");
               String description=rs.getString("description");
               int effectif=rs.getInt("effectif");
               String domaine=rs.getString("domaine");
               club p=new club(id, nom_club, description, effectif,domaine);
     arr.add(p);
     }
     
    System.out.println("trié avec succés ");
    return arr;
    }
      
       public List<club> rechercheParNom(String nom) throws SQLException {
    List<club> arr=new ArrayList<>();
    ste=connexion.createStatement();
    ResultSet rs=ste.executeQuery(" SELECT * FROM `club` WHERE (nom_club like '%"+nom+"%')");
     while (rs.next()) {                
               String id=rs.getString(1);
               String nom_club=rs.getString("nom_club");
               String description=rs.getString("description");
               int effectif=rs.getInt("effectif");
               String domaine=rs.getString("domaine");
               club p=new club(id, nom_club, description, effectif,domaine);
     arr.add(p);
     }
     
    System.out.println(" votre liste de recherche done");
    return arr;
    }
      public club getById(int id) {
          club a = null;
         String requete = " select* from club  where id='"+id+"'" ;
        try {
           
            ste = connexion.createStatement();
            ResultSet res =ste.executeQuery(requete);
            if (res.next())
            {a=new club(res.getString(1),res.getString(2),res.getString(3),res.getInt(4),res.getString(5));}
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
           public club getByEffectif(String domaine) throws SQLException {
      club p = null;
      Statement stm = connexion.createStatement();
         String requete = " SELECT * FROM `club` WHERE (domaine like '%"+domaine+"%')" ;
        try {
           
            stm = connexion.createStatement();
            ResultSet rst = stm.executeQuery(requete);
            if (rst.next())
            p=new club(rst.getString("id"),rst.getString("nom_club"),rst.getString("description"),rst.getInt("effectif"),rst.getString("domaine"));
            
                } catch (SQLException ex) {
        }
                          System.out.println("succés recherche par nom");

        return p ;
    
}
   public int calculerTotal() {
          
         String requete = "SELECT COUNT(*) FROM club" ;
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
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return a;
    }
 
    /*public void InscriptionClub(int id){
    

        try {
             
                   Statement ste = connexion.createStatement();
               String req1 = "INSERT INTO `inscription_club` (  `iduser`, `idclub`,`stat`) VALUES ('"+2+"',(select id  from `club` where `id`='"+id+"'),'"+""+"');";
        
               ste.executeUpdate(req1);
              System.out.println("participer !!");
               }
       catch (SQLException ex) {
            
             System.out.println("\nerror lors de participation \n"+ex);
        }

}  */
     /*  public void AccepterInscriptionClub(int Id) throws SQLException{
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
     
     }*/

    public void AccepterInscriptionClub(inscription_club p1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void AccepterInscriptionClub(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public inscription_club getId() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void RefuserInscriptionClub(inscription_club p1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}