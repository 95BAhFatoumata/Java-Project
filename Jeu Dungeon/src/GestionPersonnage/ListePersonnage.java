/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionPersonnage;

import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author root
 */
class ListePersonnage {
     private ArrayList<Personnage>listePerso;

    public ListePersonnage() {
        listePerso=new ArrayList<>();
    }
     
     

    ArrayList<Personnage> getListePerso() {
        return listePerso;
    }
    
    
    public boolean chercherPiecePersonnnage(String pseudo)
    {
        boolean trouve=false;
        System.out.println("pseudo chercherPerso: " +pseudo);
        for(Personnage p:listePerso)
        {
            if(p.getNom().equals(pseudo))
             {
                return  trouve=true;
             
             }
        }
        System.out.println("trouve:" +trouve);
        return trouve;
        
    }
    
    public void ajouterPersonnage(Personnage personnage)
    {
       
        listePerso.add(personnage);
        
    }
    
    /**
     * supprimer le personnage de la liste de personnage
     * @param personnage 
     */
    public void supprimerPersonnage(Personnage personnage)
    {
        listePerso.remove(personnage);
    }
}
