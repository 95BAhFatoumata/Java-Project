/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionClient;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author fatou
 */
public interface InterfaceClient extends Remote{

    public String getNom() throws RemoteException;
    
}
