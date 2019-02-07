/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionServer;

import ConnexionBD.Registre;
import GestionClient.Personne;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import GestionClient.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fatou
 */
public class LabyrintheImpl extends UnicastRemoteObject implements InterfaceLabyrinhe{
    private String nom;
    private Registre registre;
    private Personne perso; 

    public LabyrintheImpl(String nom) throws RemoteException{
        this.nom = nom;
        registre =  new Registre();
    }
    
     public void CreationDuLabyrinthe() throws RemoteException, SQLException
   {
    
      registre.connexionBD();
      
       for (int i = 1; i < 10; i++) {
           
        //création des pieces du labyrinthe

           
       }
   }
    
    public void connexion(InterfaceClient client) throws RemoteException {
        
		 String reponse=new String();
		 String requeteDeVerificaion;
                 
        try {
            registre.connexionBD();
        } catch (SQLException ex) {
            Logger.getLogger(LabyrintheImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
          requeteDeVerificaion="SELECT pseudo FROM \"JOUEUR\" WHERE pseudo='"+client.getNom()+"'";
          reponse=registre.executerRequete(requeteDeVerificaion);
          // on verifie dans la base de donnée si le pseudo existe
           if(reponse.equals(client.getNom())&& client.getNom().length()>3)
           {
                perso = new Personne();
                //on crée un nouveau personnage
        	perso.setNom(client.getNom());
                perso.setClient(client);
           }
           else
           {
               
                //Première connexion on l'ajoute dans la pièce 1
                
        	   if(client.getNom().length()>3)
        	   {
                    perso=new Personne();
        	    requeteDeVerificaion="INSERT INTO \"JOUEUR\" VALUES('"+client.getNom()+"')";
        	    registre.insertion(requeteDeVerificaion);
                  
           
        	    perso.setNom(client.getNom());
                    perso.setNumeropiece(1);
                    perso.setClient(client);
                    registre.mettreAjourvieJoueur(client.getNom(), 10);
                     
        	   }
                   else{
                   // si tout se passe mal on informe le client
                   //client.afficherEtatConnexion(0);
                   }
        	   
                       
            }
		

   
	}
    
}
