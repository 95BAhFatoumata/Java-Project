/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionServer;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 *
 * @author fatou
 */
public class Server {
    
    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {
        
         // on lance le serveur sur le port 1099;
         LocateRegistry.createRegistry(1099);
        LabyrintheImpl labyrinthe = new LabyrintheImpl("lab");
       // laburinthe.CreationDuLabyrinthe();
        System.out.println(""+labyrinthe.toString());
        Naming.rebind("rmi://localhost:1099/by", labyrinthe);
    
    }   
}
