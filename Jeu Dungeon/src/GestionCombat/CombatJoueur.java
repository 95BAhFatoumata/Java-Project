/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionCombat;

import ConnexionBD.*;
import GestionClient.*;
import GestionPersonnage.*;
import java.io.IOException;
import java.nio.CharBuffer;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author root
 */
public class CombatJoueur implements Readable{
      private String pseudo;
    private InterfaceClient client;
     private ArrayList<Personnage> liste;
     private Registre bd;

    public CombatJoueur(String pseudo, InterfaceClient client, ArrayList<Personnage> liste,Registre base) throws RemoteException {
        this.pseudo = pseudo;
        this.client = client;
        this.liste = liste;
        this.bd=bd;
    }
    public Personnage chercherPersonnage(String pseudo)
    {
          for(Personnage p:liste)
        {
            if(p.getNom().equals(pseudo))
            {
               return p;
                
            }
        }
        return null;
 
      }

    @Override
    public int read(CharBuffer cb) throws IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
    

    
    
    
}
