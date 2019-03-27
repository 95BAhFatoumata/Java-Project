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
import GestionPersonnage.ListePersonnagePiece;
import GestionPersonnage.Personnage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fatou
 */
public class LabyrintheImpl extends UnicastRemoteObject implements InterfaceLabyrinhe{
    private String nom;
    private Registre registre;
    private Personnage perso; 
    private ListePersonnagePiece listeClient;
    private ArrayList<Piece>pieces;

    public LabyrintheImpl(String nom) throws RemoteException{
        this.nom = nom;
        registre =  new Registre();
        listeClient = new ListePersonnagePiece();
        listeClient.initialiser();
        pieces=new ArrayList();
    }
    
     public void CreationDuLabyrinthe() throws RemoteException, SQLException
   {
    
      registre.connexionBD();
      
       for (int i = 1; i < 10; i++) {
           registre.connexionBD();
        //création des pieces du labyrinth
         pieces.add(new Piece(i));
           
       }
   }
    
    public void connexion(InterfaceClient client) throws RemoteException {
        
		 String reponse=new String();
		 String requeteDeVerificaion;
                 String requeteInsertion;
                 int vie = 0;
                 
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
               
                perso = new Personnage (client.getNom(), 0, client);
                //on crée un nouveau personnage
        	perso.setNom(client.getNom());
                perso.setClient(client);
                
                requeteDeVerificaion="select numeropi from \"SETROUVER\" WHERE pseudopi='"+perso.getNom()+"'";
//                perso.setNumeropiece(Integer.parseInt(registre.executerRequete(requeteDeVerificaion)));
               System.out.println("test2:"+perso.getNumeropiece());
                requeteDeVerificaion="select viejoueur from \"JOUEUR\" where pseudo='"+perso.getNom()+"'";
                vie=Integer.parseInt(registre.executerRequete(requeteDeVerificaion));
                
                System.err.println("la vie du joueur" + vie);
                
                    if(vie==0)
                {
                    perso.setVie(10);
                    registre.mettreAjourvieJoueur(perso.getNom(), 10);
                }
                    
                else
                {
                    perso.setVie(vie);
                    registre.mettreAjourvieJoueur(perso.getNom(), vie);
                }
                    listeClient.ajouterClientPiece(perso.getNumeropiece(), perso);
           }
           else
           {
               
                //Première connexion on l'ajoute dans la pièce 1
                
        	   if(client.getNom().length()>3)
        	   {
                    perso= new Personnage (client.getNom(), 0, client);
        	    requeteDeVerificaion="INSERT INTO \"JOUEUR\" VALUES('"+client.getNom()+"')";
        	    registre.insertion(requeteDeVerificaion);
                    
                   requeteInsertion="INSERT INTO \"SETROUVER\" VALUES ('"+client.getNom()+"','1')";
                   registre.insertion(requeteInsertion);
           
        	    perso.setNom(client.getNom());
                    perso.setNumeropiece(1);
                    perso.setClient(client);
                    client.setNumeropiece(1);
                    System.out.println("test1:"+perso.getNumeropiece());
                   registre.mettreAjourvieJoueur(client.getNom(), 10);
                   listeClient.ajouterClientPiece(1, perso);
             
                     
        	   }
                   else{
                   // si tout se passe mal on informe le client
                   
                   }
        	   
                       
            }
		

   
	}
    // on recupère la liste des clients dans un salon pour l'envoyer au serveur de chat 
   
    public ArrayList<Personnage>  recupererListe() throws RemoteException {
      
       ArrayList<Personnage>res = new ArrayList<>();
       for(int i=1;i<10;i++)
       {
           for(Personnage perso:listeClient.recupererListe(i))
           {
               res.add(perso);
           }
       }
       return res;
       
    }
 // recupère le numero de la pièce pour pouvoir l'envoyer au serveur de chat 
    @Override
    public int recupererNumeroPiece(InterfaceClient client) throws RemoteException {
               
        return listeClient.chercherPersonnage(client.getNom());
    }

    @Override
    public String InfoDest(ClientImpl client) throws RemoteException {
         //To change body of generated methods, choose Tools | Templates.
         
         // A chaque deplacement on l'informe de sa position et les portes pour se deplacer
         String requete="select portepi,numpidest from \"TRAVERSER\" where numerop='"
             +listeClient.chercherPersonnage(client.getNom())+"'";
   requete+="\n TAPEZ N POUR NORD \n TAPEZ S POUR SUD \n TAPEZ O pour OUEST \n TAPEZ E pour EST \n TAPEZ R POUR RESTER";
       
      
   
        return requete;
    }

    
}
