/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnexionBD;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.rmi.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author fatou
 */
public class Registre {
     private String requete;
    public Connection idConnection;
   
    public Registre ()
    {
        
    }
    
    public void connexionBD() throws RemoteException, SQLException {
		// TODO Auto-generated method stub
		try
		{
		 Class.forName("org.postgresql.Driver");
	      //System.out.println("Driver O.K.");

	      String url = "jdbc:postgresql://localhost:5432/postgres";
	      String user = "postgres";
	      String passwd = "bah";

	      
		
		      idConnection = DriverManager.getConnection(url, user, passwd);
			
		      System.out.println("Connexion BD okkkk !!!");         

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     	     
		
	}
    
    public String executerRequete(String requete)
	{
		String resultat=new String();
		java.sql.Statement state;
		try {
			state = idConnection.createStatement();
			 
                       ResultSet res = state.executeQuery(requete);
               while(res.next())
             {
                  resultat= res.getString(1);
             }
		   
       	 return resultat;
			   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return "Erreur Traitement";
		
	
		
	}
    
   public void insertion(String requete)
	{
		String res=new String();
		java.sql.Statement state;
		 try {
			state = idConnection.createStatement();
			 state.execute(requete);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 		
	} 
   
   public void mettreAjourvieJoueur(String pseudo,int viejoueur)
    {
     String requete;
     requete="UPDATE \"JOUEUR\" SET viejoueur='"+viejoueur+"' where pseudo='"+pseudo+"'";
        System.err.println(""+requete);
     insertion(requete);
    }
}
