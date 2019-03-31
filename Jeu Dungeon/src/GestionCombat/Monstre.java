/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionCombat;

import GestionPersonnage.Personnage;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author root
 */
public class Monstre  implements Serializable{
    
    private String nomMonstre;
    private Integer vieMonstre = 5;
    private boolean etatMonstre;
    private int nbreAdversaire;
    
    public Monstre(String pnomMonstre) {
        this.nomMonstre = pnomMonstre;
        vieMonstre=5;
        etatMonstre=false;
        nbreAdversaire=0;
    }
    
    public String Menu()
   {
       Scanner sc=new Scanner(System.in);
       System.out.println("****1. attaquer*****************************");
       System.out.println("****2. Se deplacez ou Fuir le combat ******");
       System.out.println("****3. Envoyer un message************************");
       System.out.println("****4. quitter*****************************");
       return sc.nextLine();
   }

    public String getNomMonstre() {
        return nomMonstre;
    }

    public Integer getVieMonstre() {
        return vieMonstre;
    }

    public int getNbreAdversaire() {
        return nbreAdversaire;
    }

    public void setNomMonstre(String nomMonstre) {
        this.nomMonstre = nomMonstre;
    }

    public void setVieMonstre(Integer vieMonstre) {
        this.vieMonstre = vieMonstre;
    }

    public void setNbreAdversaire(int nbreAdversaire) {
        this.nbreAdversaire = nbreAdversaire;
    }
    public void diminuerVieMonstre(Integer vie) {
        this.vieMonstre -= vie;
        if(vieMonstre<0){
        vieMonstre=0;
        }
    }
    public void ajouterVieJoueur(Integer vie) {
        this.vieMonstre += vie;
    }

   
 
    
     public void attaquerPersonnage(Personnage p) throws RemoteException{
        p.setVie(p.getVieJoueur()-1);
}

    public boolean isEtatMonstre() {
        etatMonstre=false;
        if(vieMonstre==0)
        return etatMonstre;
        else 
            return true;
    }

    public void setEtatMonstre(boolean etatMonstre) {
        this.etatMonstre = etatMonstre;
    }

   
    
    
}
