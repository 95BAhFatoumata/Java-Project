/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jeu.dungeon;

import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author fatou
 */
public class JeuDungeon {
   private String requete;
    public Connection idConnection;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        
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
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
      
    
}
