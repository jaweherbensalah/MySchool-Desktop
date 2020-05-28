/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Note;
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
 * @author hp
 */
public class NoteService {
    private Connection connection ;
    private Statement ste;
    private PreparedStatement pst ;
    private ResultSet rs ;
    
    public NoteService()
    {  connection=DataSource.getInstance().getCnx();}
    public void insertNote(Note N)
    {
      String req="insert into note( matiere_id,  ds,  cc,  examen,  coeffcc,  coeffDS,  coeffEX,  moyenne,eleve_id) values('"+N.getMatiere_id()+"','"+N.getDs()+"','"+N.getCc()+"','"+N.getExamen()+"','"+N.getCoeffcc()+"','"+N.getCoeffDS()+"','"+N.getCoeffEX()+"','"+calculateMoy(N)+"','"+N.getEleve_id()+"')";
        try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public float calculateMoy(Note N)
    { float s1=N.getCoeffDS()+N.getCoeffEX()+N.getCoeffcc();
       float s2 = N.getCc()*N.getCoeffcc()+N.getDs()*N.getCoeffDS()+N.getExamen()*N.getCoeffEX() ;
         float moy=s2/s1;
         N.setMoyenne(moy);
        return moy;
    }
  
       public List<Note> getAll()
   { String req="select matiere_id,ds,cc,examen,moyenne from note";
      List<Note> list=new ArrayList<>();
      try {
            ste=connection.createStatement();
            rs=ste.executeQuery(req);
            while(rs.next())
            {
             list.add(new Note(rs.getString("matiere_id"),rs.getFloat("ds"),rs.getFloat("cc"),rs.getFloat("examen"),rs.getFloat("moyenne"),rs.getInt("eleve_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
      return list;
   }
      public void updateNote(Note N)
   {
       String req="update note SET ds='" +N.getDs()+ "',cc='" +N.getCc()+ "',examen='" +N.getExamen()+ "' ,moyenne='" +calculateMoy(N)+ "' " + " WHERE matiere_id= '"+N.getMatiere_id()+"' AND eleve_id= '"+N.getEleve_id()+"'" ;
    try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
      
      
     public void DeleteNote(Note N)
    {String req="DELETE  from  note  WHERE eleve_id = '"+N.getEleve_id()+"'" ;
    try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
     public void displayNote(Note N )
     {String req="select *  from  note  WHERE eleve_id = '"+N.getEleve_id()+"'" ;
    try {
            ste=connection.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(NoteService.class.getName()).log(Level.SEVERE, null, ex);
        }
     
     
     }
}
