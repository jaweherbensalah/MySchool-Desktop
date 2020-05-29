/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

import entities.event;
import entities.participation;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import util.DataSource;


public class EventService {

     

    private Connection connexion;
    private Statement Ste;
    private PreparedStatement pst;
    private Statement ste;

    public EventService() {
        connexion = DataSource.getInstance().getCnx();
    }
   

    
     public void ajoutEvent(event t)  {
        
        String requeteInsert = "INSERT INTO event (`id`, `nom_event`,`description` ,`placeDispo`, `DateEvent`, `HeureEvent`,`image`,`club_id`) VALUES (NULL, '" + t.getNom_event() + "', '" + t.getDescription() + "', '" + t.getPlaceDispo() + "', '" + t.getDateEvent() +"', '" + t.getHeureEvent() + "', '" + t.getImage() + "', '" + t.getClub_id() + "');";
        
        try { 
            pst=connexion.prepareStatement(requeteInsert);
             pst.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
           
            
      
     }
    
     public void supprimeEvent(int id) {
        try {
            String req = "DELETE FROM event WHERE id=?";
            pst = connexion.prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();//exucute query select kahaw
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        
    }
     public void modifierEvent(event a) throws SQLException { 
         
        
            PreparedStatement pst;
            String requete="Update event set  id=?, nom_event=?,description=?,placeDispo=?,DateEvent=?,HeureEvent=?,image=?,club_id=? where id='" + a. getId()+ "'" ;
           
            pst=connexion.prepareStatement(requete);
            pst.setInt(1,a.getId());
            pst.setString(2,a.getNom_event());
            pst.setString(3,a.getDescription());
            pst.setInt(4,a.getPlaceDispo());
            pst.setDate (5,a.getDateEvent());
            pst.setString(6,a.getHeureEvent());
            pst.setString(7,a.getImage());
            pst.setInt(8,a.getClub_id());
            pst.executeUpdate();
            System.err.println("event modifiée");
       
    }
   
    public List<event> afficheEvents() throws SQLException {
    List<event> arr=new ArrayList<>();
    ste=connexion.createStatement();
    ResultSet rs=ste.executeQuery("select * from event");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom_event=rs.getString("nom_event");
               String description=rs.getString("description");
               int placeDispo=rs.getInt("placeDispo");
               Date DateEvent=rs.getDate("DateEvent");
               String HeureEvent=rs.getString("HeureEvent");
               String image=rs.getString("image");
               int club_id=rs.getInt("club_id");
               ImageView img = new ImageView(image);
               event p=new event (img, id,  nom_event, description,  placeDispo, DateEvent,  HeureEvent, club_id);
                arr.add(p);
             }
     return arr;
                }
    
    public List<event> RechercheParNom(String search) {
        String req = "select * from event where nom_event like '%" + search + "%'";
        List<event> list = new ArrayList<>();
        try {
            Statement st =connexion.createStatement();
           ResultSet rs=st.executeQuery(req);
            while (rs.next()) {
                while (rs.next()) {
                    list.add(new event(rs.getInt("id"),rs.getString("nom_event") ,rs.getString("description"),rs.getInt("placeDispo"),rs.getDate("DateEvent"),rs.getString("HeureEvent"),rs.getString("image"),rs.getInt("club_id")));
                }
                System.out.println("la liste des évenements trouvé ");
            }

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<event> rechercheParHeuure(String search ) throws SQLException {
    List<event> arr=new ArrayList<>();
    ste=connexion.createStatement();
    ResultSet rs=ste.executeQuery("select * from event where HeureEvent like '%" + search + "%' order by DateEvent DESC");
     while (rs.next()) {                
               int id=rs.getInt(1);
               String nom_event=rs.getString("nom_event");
               String description=rs.getString("description");
               int placeDispo=rs.getInt("placeDispo");
               Date DateEvent=rs.getDate("DateEvent");
               String HeureEvent=rs.getString("HeureEvent");
               String image=rs.getString("image");
               int club_id=rs.getInt("club_id");
               
               event p=new event ( id,  nom_event, description,  placeDispo, DateEvent,  HeureEvent, image, club_id);
     arr.add(p);
     }
    return arr;
    }
      
    
     public void Participer(participation p){
        
        
        String req="insert into participation (iduser,idevent,createdat) values ('"+p.getIduser()+"','"+p.getIdevent()+"','"+p.getCreatedat()+"')";
       String req1="update event set placeDispo=placeDispo-1 where id='"+p.getIdevent()+"'";
       
        try {
            ste= connexion.createStatement();
            ste.executeUpdate(req);
            ste.executeUpdate(req1);
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
     
      
    public static int totaleJ = 0;
    public static int totaleF = 0;
    public static int totaleM = 0;
    public static int totaleA = 0;
    public static int totaleMai = 0;
    public static int totaleJuin = 0;
    public static int totaleJuillet = 0;
    public static int totaleAout = 0;
    public static int totaleSeptembre = 0;
    public static int totaleOctobre = 0;
    public static int totaleNovembre = 0;
    public static int totaleDécembre = 0;
     
    public void calculernbreEventparmois() {
 
        try {
            System.out.println("testttttt");

            String req = "SELECT * FROM event ";
   ste = connexion.createStatement();
        ResultSet rs = ste.executeQuery(req);
            System.out.println(rs);
            while (rs.next()) {
                System.out.println(rs.getDate("DateEvent").getMonth() + 1 == 7);
                if (rs.getDate("DateEvent").getMonth() + 1 == 1) {
                    totaleJ += 1;
                    System.out.println("janvier " + totaleJ + " date " + rs.getDate("DateEvent"));

                } else if (rs.getDate("DateEvent").getMonth() + 1 == 2) {
                    totaleF += 1;
                    System.out.println("fevrier " + totaleF + " date " + rs.getDate("DateEvent"));

                } else if (rs.getDate("DateEvent").getMonth() + 1 == 3) {
                    totaleM += 1;
                    System.out.println("mars " + totaleM + " date " + rs.getDate("DateEvent"));

                } else if (rs.getDate("DateEvent").getMonth() + 1 == 4) {
                    totaleA += 1;
                    System.out.println("avril " + totaleA + " date " + rs.getDate("DateEvent"));

                } else if (rs.getDate("DateEvent").getMonth() + 1 == 5) {
                    totaleMai += 1;
                    System.out.println("mai " + totaleMai + " date " + rs.getDate("DateEvent"));

                } else if (rs.getDate("DateEvent").getMonth() + 1 == 6) {
                    totaleJuin += 1;
                    System.out.println("juin " + totaleJuin + " date " + rs.getDate("DateEvent"));

                } else if (rs.getDate("DateEvent").getMonth() + 1 == 7) {
                    totaleJuillet += 1;
                    System.out.println("juillet " + totaleJuillet + " date" + rs.getDate("DateEvent"));

                } else if (rs.getDate("DateEvent").getMonth() + 1 == 8) {
                    totaleAout += 1;
                    System.out.println("aout " + totaleAout + " date " + rs.getDate("DateEvent"));
                }
                
              else if (rs.getDate("DateEvent").getMonth() + 1 == 9) {
                    totaleSeptembre += 1;
                    System.out.println("septembre " + totaleSeptembre + " date " + rs.getDate("DateEvent"));
                }
                else if (rs.getDate("DateEvent").getMonth() + 1 == 10) {
                    totaleOctobre += 1;
                    System.out.println("octobre " + totaleOctobre + " date " + rs.getDate("DateEvent"));
                }
                else if (rs.getDate("DateEvent").getMonth() + 1 == 11) {
                    totaleNovembre += 1;
                    System.out.println("novembre " + totaleNovembre + " date " + rs.getDate("DateEvent"));
                }
                else if (rs.getDate("DateEvent").getMonth() + 1 == 12) {
                    totaleDécembre += 1;
                    System.out.println("décembre " + totaleDécembre + " date " + rs.getDate("DateEvent"));
                }

            }

        } catch (SQLException ex) {

        }

     }
    
     /*public int calculerParticipantsEvent( int c) {
          int l = 0 ;
         String requete = "SELECT COUNT(id) FROM participation WHERE idevent='"+c+"' " ;
        try {
           
           Statement st =connexion.createStatement(); 
           ResultSet rs=st.executeQuery(requete);
           if (rs.next()){
          String chaine = String.valueOf(rs.getString(1));
           l=Integer.parseInt(chaine);
            System.out.println("le nombre des participants  à cet  évenement est :" +l);
           }
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      return l ;
    }   */
         
   /*public boolean EventComplet (participation p) {
        boolean x = false;
        try {
            Statement stmt = connexion.createStatement();
            String select = "SELECT * FROM event WHERE placeDispo=0 AND id= '" + p.getIdevent()+ "'";
            ResultSet result = stmt.executeQuery(select);
            if (result.next()) {
                System.out.println("vous ne pouvez pas participer !!Event complet!!");
                x = false;
            } else {
                System.out.println("vous pouvez participer");
                x = true;
            }

        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return x;

    }*/

    public String[] retourDesignationMateriel() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public event getById(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   public int calculerEvents() {
          
         String requete = "SELECT COUNT(*) FROM event" ;
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

    public List<participation> getParticipatedEvent(participation p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void Participer(event c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     
  
  public ImageView afficheImage() throws SQLException {
    
    ste=connexion.createStatement();
    ResultSet rs=ste.executeQuery("select * from event");
     ImageView img = new ImageView();
     while (rs.next()) {                
               int id=rs.getInt(1);
               String image=rs.getString("image");
             
              img = new ImageView(image);
             }
     return img;
                }
   
}