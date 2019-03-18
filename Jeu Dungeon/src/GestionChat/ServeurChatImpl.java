/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionChat;

import ConnexionBD.Registre;
import GestionClient.InterfaceClient;
import GestionPersonnage.Personnage;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author root
 */
public class ServeurChatImpl extends UnicastRemoteObject implements ServeurChat{
    
    private ArrayList<String>registre;
    private Registre base;
    private String nom;
    private ArrayList<Personnage>  listeP;
    
    public ServeurChatImpl(String nom)throws RemoteException{
       registre=new ArrayList<>();
       this.nom=nom;
       
    }
    
    @Override
    public void broadcasterMessage(int numeropiece) throws RemoteException
    {
        String s=new String();
        String requete = null;
        requete="select message from \"MESSAGEPIECE\" where numerop='"+numeropiece+"'" ;
     
         s=base.executerRequete(requete);
       
        
        
    }

   
    public void recupererMessage(String message, InterfaceClient client,int numeroPiece) throws RemoteException {
      String requete = null;
      
       requete="INSERT INTO \"MESSAGEPIECE\" VALUES("+numeroPiece+",'"+client.getNom()+"','"+message+"')";
      // requete+=",CURRENT_TIMESTAMP,'"+message+"')";
        System.out.println("test: "+requete);
       base.insertion(requete);
   
    }

    public void recupererListeClients(ArrayList<Personnage>  liste) throws RemoteException {
         //To change body of generated methods, choose Tools | Templates.
       
        this.listeP=liste;
    }


    
}
