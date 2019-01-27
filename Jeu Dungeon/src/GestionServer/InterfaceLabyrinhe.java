/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionServer;

import GestionClient.InterfaceClient;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author fatou
 */
public interface InterfaceLabyrinhe extends Remote{
   public void connexion(InterfaceClient client) throws RemoteException;
    
}
