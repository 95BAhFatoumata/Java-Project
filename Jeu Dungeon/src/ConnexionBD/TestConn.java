/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ConnexionBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author root
 */
public class TestConn {
    private String requete;
    public static Connection idConnection;
    
    
    public static void main(String[] args) throws ClassNotFoundException {
         
      
    try
		{
                 
		 Class.forName("org.postgresql.Driver");
	     // System.out.println("Driver O.K.");

	      String url = "jdbc:postgresql://localhost:5432/postgres";
	      String user = "postgres";
	      String passwd = "bah";

	      
		
			idConnection = DriverManager.getConnection(url, user, passwd);
			
		      System.out.println("Connexion BD okkkk !!!");         

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    }    
		
}
