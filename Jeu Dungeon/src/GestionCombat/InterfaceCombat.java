/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionCombat;
import GestionClient.InterfaceClient;
import GestionPersonnage.Personnage;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author root
 */
public interface InterfaceCombat {
    public void effectuerCombat(Personnage personnage, Monstre monstre) throws RemoteException;
	public void faire_Attaque(Personnage attaquant, Monstre defenseur) throws RemoteException;
	public boolean isCombat(int numP) throws RemoteException; //Si ya un combat dans ce piece
        
 
      public Personnage chercherPersonnage(String pseudo)throws RemoteException;
    
}
