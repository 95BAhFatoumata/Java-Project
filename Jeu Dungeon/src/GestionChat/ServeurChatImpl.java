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
    
    public ServeurChatImpl(Registre base)throws RemoteException{
       registre=new ArrayList<>();
       this.base=base;
       
    }
    
    @Override
    public void broadcasterMessage(int numeropiece) throws RemoteException
    {
        String s=new String();
         String requete;
         requete="select message from \"MESSAGEPIECE\" where numerop='"+numeropiece+"'" ;
     
         s=base.executerRequete(requete);
       
        
        
    }

   
    public void recupererMessage(String message, InterfaceClient client,int numeroP) throws RemoteException {
      String requete;
       requete="INSERT INTO \"MESSAGEPIECE\" VALUES('"+numeroP+"','"+client.getNom()+"'";
       requete+=",CURRENT_TIMESTAMP,'"+message+"')";
       base.insertion(requete);
   
    }

    public void recupererListeClients(ArrayList<Personnage>  liste) throws RemoteException {
         //To change body of generated methods, choose Tools | Templates.
       
        //this.listeP=liste;
    }


    
}
