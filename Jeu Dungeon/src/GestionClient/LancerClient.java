/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionClient;
import GestionChat.ServeurChat;
import GestionCombat.InterfaceCombat;
import GestionServer.InterfaceLabyrinhe;
import java.net.MalformedURLException;
import java.rmi.*;

/**
 *
 * @author fatou
 */
public class LancerClient {
    
    
     public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException, InterruptedException {
         int res=0;
         String choix = new String();
         String choix2= new String();//choix de rester 
         String choix3 = new String();//choix de fuir
         String msg = new String();
         System.out.println("Lancement du client");
         
         InterfaceLabyrinhe stub = (InterfaceLabyrinhe) Naming.lookup("rmi://localhost:1099/jeu");
         ClientImpl client = new ClientImpl();
         ServeurChat chat=(ServeurChat)Naming.lookup("rmi://localhost:1098/chat");
         //InterfaceCombat serverCombat = (InterfaceCombat)Naming.lookup("rmi://localhost:1097/combat");
         System.out.println(""+chat.toString());
         System.out.println(""+stub.toString());
         client.saisirPseudo();
         stub.connexion(client);
        
              do
            {
                client.Menu();
                choix=client.choixclient();
                
                if(choix.equals("1"))
            {
                do
                {
                 do
               {
               client.affichage(stub.InfoDest(client));
               choix2=client.choixclient();
                
               }while(!choix2.equals("R") && !choix2.equals("N")&& !choix2.equals("S") && !choix2.equals("O")&& 
                       !choix2.equals("E"));
                  if(!choix2.equals("R"))
                      //res=stub.deplacerJoueur(choix2,client);
                    // else
                      res=1;
                 
                }while(res!=1);
             
               
                      
   
           }
               
                
                else  if(choix.equals("2"))
                {
                                     
                                
                     chat.recupererListeClients(stub.recupererListe());
                     System.out.println("client dans la piece" +stub.recupererListe());
                     System.out.println("test dans envoie de message");
                
                    do
                      {
                        if(!msg.equals("q"))
                            {
                               msg=client.envoyerMessage();
                               System.out.println(msg);
                               chat.recupererMessage(msg, client,stub.recupererNumeroPiece(client));
                               chat.broadcasterMessage(stub.recupererNumeroPiece(client));
                            }                   
                      }while(!msg.equals("q"));
                                      

                }
            }while(res!=3);
              
     }
     
       
    
}
