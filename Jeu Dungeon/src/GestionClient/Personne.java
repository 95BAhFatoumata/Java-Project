/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionClient;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author fatou
 */
public class Personne implements Serializable {
    
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    private Scanner sc;
    private String nom;
    private int numeropiece;
    private Integer vieJoueur = 10;
    private Integer nbreCombat=0;
    
    private InterfaceClient client;
    public Personne(String nom, int numeropiece) {
        this.nom = nom;
        this.numeropiece = numeropiece;
    }
    public Personne(String nom, int numeropiece,InterfaceClient client) {
        this.nom = nom;
        this.numeropiece = numeropiece;
        this.client=client;
    }
    
    public  Personne()
    {
        
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumeropiece() {
        return numeropiece;
    }

    public void setNumeropiece(int numeropiece) {
        this.numeropiece = numeropiece;
    }
    
    public Integer getVieJoueur() {
        return vieJoueur;
    }

    public void setVie(Integer vie) {
        this.vieJoueur = vie;
        if(vieJoueur <0){
        vieJoueur=0;
        }
    }
    public void retirerVieJoueur(Integer vie) {
        this.vieJoueur -= vie;
       if(vieJoueur <0){
        vieJoueur=0;
        }
       
    }
    public void ajouterVieJoueur(Integer vie) {
        this.vieJoueur += vie;
      
    }
     
    public String toString()
    {
        return "Mr "+nom+" est dans la pièce "+numeropiece;
    }

    public Scanner getSc() {
        return sc;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }
  

    public InterfaceClient getClient() {
        return client;
    }

    public void setClient(InterfaceClient client) {
        this.client = client;
    }

    
     public void afficher(String s) throws RemoteException {
       //To change body of generated methods, choose Tools | Templates.
        System.out.println(s);
    }
     public String choixJoueur() throws RemoteException
    {
       return sc.nextLine();
    }

    public Integer getNbreCombat() {
        return nbreCombat;
    }

    public void setNbreCombat(Integer nbreCombat) {
        this.nbreCombat = nbreCombat;
    }

   
    
}

