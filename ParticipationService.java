/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entities.club;
import entities.event;
import entities.participation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import util.DataSource;

/**
 *
 * @author ASUS
 */
public class ParticipationService {
     private Connection cnx;
    private Statement ste;
    private ResultSet rs;
      private PreparedStatement pst  ; 

    
    public ParticipationService() {
        cnx=DataSource.getInstance().getCnx();
        
    }
    
    
     public void ajoutpart (participation t) throws SQLException {
        ste = cnx.createStatement();
        
       String requeteInsert = "INSERT INTO  `participation`(`id`, `iduser`, `idevent`, `createdat`,`nom`)  VALUES (NULL, '" + t.getIduser() + "', '" + t.getIdevent() + "', '" + t.getCreatedat() +"','"+t.getNom()+"');";
        String req1="update event set placeDispo=placeDispo-1 where id='"+t.getIdevent()+"'";
       
        
       try {
            ste=cnx.createStatement();
            ste.executeUpdate(requeteInsert);
            ste.executeUpdate(req1);
            
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
    
 

    
    
  /* public void Participer(participation p){
        
        
        String req="insert into participation (iduser,idevent,createdat) values ('"+p.getIduser()+"','"+p.getIdevent()+"','"+p.getCreatedat()+"')";
       String req1="update event set placeDispo=placeDispo-1 where id='"+p.getIdevent()+"'";
       
       
        try {
            ste= cnx.createStatement();
            ste.executeUpdate(req);
            ste.executeUpdate(req1);
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
         }*/
     
      public List<participation> afficheParticipations() throws SQLException {
    List<participation> arr=new ArrayList<>();
    ste=cnx.createStatement();
    ResultSet rs=ste.executeQuery("select * from participation");
     while (rs.next()) {         
           int id=rs.getInt(1);
               int iduser=rs.getInt("iduser");
                 String idevent=rs.getString("idevent");
              Date createdat =rs.getDate("createdat");
              String nom=rs.getString("nom");
           participation p=new participation (id, iduser, idevent,  createdat,nom);
                arr.add(p);
        }
        return arr;
    }

    /* public List<event> getParticipatedEvent(int iduser) {
        try {
            List<event> listeEvents = new ArrayList<>();
            List<participation> listePArticipations = new ArrayList<>();
            String req = "select * from participation where iduser=" + iduser;
            ste = cnx.createStatement();
        ResultSet rs=ste.executeQuery(req);
            while (rs.next()) {
                listePArticipations.add(new participation(rs.getInt("id"), rs.getInt("iduser"), rs.getString("idevent"),rs.getDate("createdat"),rs.getString("nom")));

            }

            for (participation participation : listePArticipations) {
                String req2 = "select * from event where id=" + participation.getId();
                ste = cnx.createStatement();
                rs = ste.executeQuery(req2);
                while (rs.next()) {
                    event event = new event(rs.getInt("id"),rs.getString("nom_event") ,rs.getString("description"),rs.getInt("placeDispo"),rs.getDate("DateEvent"),rs.getString("HeureEvent"),rs.getString("image"),rs.getInt("club_id"));
                    listeEvents.add(event);

                }
            }
            List<event> listDistinct = listeEvents.stream().distinct().collect(Collectors.toList());

            System.out.println("Size ==> " + listDistinct.size());
            System.out.println("listeEvents ==> " + listDistinct);

            return listDistinct;
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
     }*/
     public boolean Participate(participation p) {
        boolean x = false; //result of function
        try {
            Statement stmt = cnx.createStatement();
            String select = "SELECT * FROM event WHERE placeDispo=0 AND id= '" + p.getIdevent()+ "'";
            ResultSet result = stmt.executeQuery(select);
                if (result.next()) {
                System.out.println("vous ne pouvez pas participer !!Event complet!!");
             
           
                x = false;
            } else {
                System.out.println("participation enregistré avec succès");
               
                x = true;
                  String req="insert into participation (iduser,idevent,createdat) values ('"+p.getNom()+"','"+p.getIdevent()+"','"+p.getCreatedat()+"')";
                  String req1="update event set placeDispo=placeDispo-1 where id='"+p.getIdevent()+"'";
       
       
        try {
            ste= cnx.createStatement();
            ste.executeUpdate(req);
            ste.executeUpdate(req1);
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
            }

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return x;//return x selon l'event cherché 

    }
    private ResultSet res ;

  public participation verif(String iduser,String idevent) {
          participation a = null;
            String req = "select * from participation where nom= ?  and idevent= ? ";
        try {
           pst = cnx.prepareStatement(req);
              pst.setString(1,iduser);
                pst.setString(2,idevent);
            res=pst.executeQuery();
            if (res.next())                
            {a=new participation(res.getInt(1),res.getInt(2),res.getString(3),res.getDate(4),res.getString(5));}
        } catch (SQLException ex) {
            Logger.getLogger(ParticipationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a ;
        
    }
 
}
