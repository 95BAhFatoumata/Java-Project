/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionClient;
import GestionChat.ServeurChat;
import GestionCombat.InterfaceCombat;
import GestionServer.InterfaceLabyrinhe;
import GestionServer.InvalidCoordException;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.Scanner;

/**
 *
 * @author fatou
 */
public class LancerClient {
    
    
     public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException, InterruptedException, InvalidCoordException {
         int res=0;
         String choix = new String();
         String msg = new String();
         String direction ="";
         Scanner sc = new Scanner(System.in);
         System.out.println("Lancement du client");
         
         InterfaceLabyrinhe stub = (InterfaceLabyrinhe) Naming.lookup("rmi://localhost:1099/jeu");
         ClientImpl client = new ClientImpl();
         ServeurChat chat=(ServeurChat)Naming.lookup("rmi://localhost:1098/chat");
         //InterfaceCombat serverCombat = (InterfaceCombat)Naming.lookup("rmi://localhost:1097/combat");
         System.out.println(""+chat.toString());
         System.out.println(""+stub.toString());
         client.saisirPseudo();
         stub.connexion(client);
         System.out.println(stub.AfficherLab());

        
              do
            {
                client.Menu();
                choix=client.choixclient();
                
                if(choix.equals("1"))
            {
                do{
                    System.out.println(stub.TestPossibilite_deplacement(client));
                    System.out.println("Entrer un deplacement possible et 'stop' pour s'arreter");
                    direction = sc.nextLine();
                    stub.seDeplacerVers(direction, client);
                    System.out.println(stub.AfficherLab());
                
               }while(!direction.equals("stop"));
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
