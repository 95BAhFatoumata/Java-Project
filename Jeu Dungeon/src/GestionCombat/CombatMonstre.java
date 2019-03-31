
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionCombat;

import ConnexionBD.*;
import GestionClient.*;
import GestionPersonnage.*;
import java.rmi.RemoteException;
import java.util.*;

/**
 *
 * @author root
 */
public class CombatMonstre implements Runnable{
    
    private ArrayList<Personnage> liste;
    private InterfaceClient client;
    private boolean etatcombat;
    private   Personnage p;
    private String choixclient; 
    private Registre bd;
     private ArrayList<Monstre> lesMonstre;

    public CombatMonstre(ArrayList<Personnage> liste,ArrayList<Monstre> lesMonstres, InterfaceClient client, Registre bd) {
        this.liste = liste;
        this.client = client;
        this.bd = bd;
        this.lesMonstre=lesMonstres;
    }
 
   
     public boolean isEtatcombat() {
        return etatcombat;
    }

    public void setEtatcombat(boolean etatcombat) {
        this.etatcombat = etatcombat;
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
     public void ajouterVieMonstre(int numeroPiece)
    {
        lesMonstre.get(numeroPiece).setVieMonstre(lesMonstre.get(numeroPiece).getVieMonstre()+1);
    
    }
     
    @Override
    public void run() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
  
}
