/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionClient;
import GestionChat.ServeurChat;
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
         String choix;
         choix = new String();
         String msg = new String();
         System.out.println("Lancement du client");
         
         InterfaceLabyrinhe stub = (InterfaceLabyrinhe) Naming.lookup("rmi://localhost:1099/jeu");
         ClientImpl client = new ClientImpl();
         ServeurChat chat=(ServeurChat)Naming.lookup("rmi://localhost:1098/chat");
         System.out.println(""+chat.toString());
         System.out.println(""+stub.toString());
         client.saisirPseudo();
         stub.connexion(client);
        
              do
            {
                client.Menu();
                choix=client.choixclient();
               
                
              if(choix.equals("2"))
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
