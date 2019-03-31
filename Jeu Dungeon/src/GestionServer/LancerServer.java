/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionServer;

import ConnexionBD.Registre;
import GestionChat.ServeurChatImpl;
import GestionCombat.CombatImpl;
import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fatou
 */
public class LancerServer {
    
    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException, SQLException {
        
        int Longueur=4;
        int largeur=4;
        System.out.println("on lance le serveur sur le port 1099");
         LocateRegistry.createRegistry(1099);
        // LabyrintheImpl labyrinthe = new LabyrintheImpl("laby");
         InterfaceLabyrinhe labyrinthe = LabyrintheImpl.Nouveau("alpha", Longueur, largeur, 5, 3);
         
       
        System.out.println("# Création du Labyrinthe #");
        labyrinthe.CreationDuLabyrinthe();
       
            System.out.println(""+labyrinthe.toString());
           Naming.rebind("rmi://localhost:1099/jeu", labyrinthe);
        //creation du serveur de chat sur le port 1098
        LocateRegistry.createRegistry(1098);
        ServeurChatImpl serveurChat=new ServeurChatImpl("chat");
        System.out.println(""+serveurChat.toString());
       try {
             Naming.rebind("rmi://localhost:1098/chat",serveurChat);
           }       
       catch (MalformedURLException ex) 
           {
                    Logger.getLogger(ServeurChatImpl.class.getName()).log(Level.SEVERE, null, ex);
           }
       
        //creation du serveur de combat sur le port 1097
       /* LocateRegistry.createRegistry(1097);
        CombatImpl serverCombat = new CombatImpl();
        System.out.println(""+serverCombat.toString());
        Naming.rebind("rmi://localhost:1097/combat", (Remote) serverCombat);*/
        
                
       
       
    }   
}
