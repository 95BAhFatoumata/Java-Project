/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionPersonnage;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import GestionPersonnage.*;
import GestionClient.*;


/**
 *
 * @author root
 */
public class ListePersonnagePiece {
    private HashMap<Integer,ListePersonnage>tableau;
    
    
    public ListePersonnagePiece()
    {
        tableau=new HashMap<>();
    }
    
    public void initialiser()
  {
     
      for(int i=1;i<10;i++)
      {
       ListePersonnage l=new ListePersonnage();
          tableau.put(i, l);
      }
     
  }
    public ArrayList<Personnage> recupererListe(int numeroPiece)
  {
     return  tableau.get(numeroPiece).getListePerso();
  }

    public int chercherPersonnage(String nom)
  {
      int i;
      boolean trouve=false;
      i=1;
      while(i<10 && !trouve)
      {
        trouve= tableau.get(i).chercherPiecePersonnnage(nom);
            if(!trouve) 
                  i++;
      }
      return i;
     
  }
    
    
    public void ajouterClientPiece(int numeroPiece,Personnage personnage) throws RemoteException          
  {
     int nb=0;
       for(Personnage p:tableau.get(numeroPiece).getListePerso())
        {
           if(p.getNom().equals(personnage.getNom()))
           {
             nb=1;
           }
           System.out.println("test nb ajouterclientPiece: " +nb); 
           System.out.println(personnage.getNumeropiece());
          
        }
             if(nb==0)
              tableau.get(numeroPiece).ajouterPersonnage(personnage);
             else
             {
                 deleteClient(personnage.getNom(), numeroPiece);
                 tableau.get(numeroPiece).ajouterPersonnage(personnage);
             }
        System.out.println("test nb2 ajouterclientPiece: " +nb); 
           System.out.println(personnage.getNumeropiece());
        
        
  }
    
    /**
   * 
   * @param nom pseudo du personnage à supprimer 
   * @param numeroPiece numero de la pièce où se trouve le personnage
   */
  public void deleteClient(String nom,int numeroPiece)
  {
       for(Personnage p:tableau.get(numeroPiece).getListePerso())
       {
           if(p.getNom().equals(nom))
           {
             
           tableau.get(numeroPiece).supprimerPersonnage(p);
           return ;
             
           }
       }
              
  }

}
