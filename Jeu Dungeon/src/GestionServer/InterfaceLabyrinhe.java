/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionServer;

import GestionClient.ClientImpl;
import GestionClient.InterfaceClient;
import GestionPersonnage.Personnage;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author fatou
 */
public interface InterfaceLabyrinhe extends Remote{
   public void connexion(InterfaceClient client) throws RemoteException;
   public void CreationDuLabyrinthe() throws RemoteException, SQLException;
   public ArrayList<Personnage> recupererListe()throws RemoteException;
   public int  recupererNumeroPiece(InterfaceClient client)throws RemoteException;
   public void seDeplacerVers(String direction, InterfaceClient client ) throws RemoteException, InvalidCoordException;

    public String InfoDest(ClientImpl client) throws RemoteException;
    public String TestPossibilite_deplacement(InterfaceClient client) throws RemoteException, InvalidCoordException;
    public String AfficherLab() throws RemoteException;
    
}
