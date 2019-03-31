/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionClient;

import GestionPersonnage.Personnage;
import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author fatou
 */
public interface InterfaceClient extends Remote{

    public String getNom() throws RemoteException;
    public int getNumeropiece()throws RemoteException;
    public void setNumeropiece(int numeropiece)throws RemoteException;

   // public void affichage(String s);

    public void affichage(String s) throws RemoteException;

    public void setPersonnage(Personnage pers) throws RemoteException;

    public Personnage getPersonnage() throws RemoteException;
    
}
