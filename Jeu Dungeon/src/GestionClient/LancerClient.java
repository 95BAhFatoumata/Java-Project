/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionClient;
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
         System.out.println("Lancement du client");
         
         InterfaceLabyrinhe stub = (InterfaceLabyrinhe) Naming.lookup("rmi://localhost:1099/by");
         ClientImpl client = new ClientImpl();
         System.out.println(""+stub.toString());
         client.saisirPseudo();
         stub.connexion(client);
        
              do
            {
                client.Menu();
                choix=client.choixclient();
            }while(res!=1);
     }
       
    
}
