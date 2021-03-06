/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionPersonnage;

import GestionClient.InterfaceClient;
import GestionServer.Orientation;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Scanner;

/**
 *
 * @author root
 */
public class Personnage implements Serializable{
    private Scanner sc;
    private String nom;
    private int numeropiece;
    private Integer vieJoueur = 10;
    private Integer nbreCombat=0;
     private InterfaceClient client;
     private Orientation position;

    public Personnage( String nom, int numeropiece, InterfaceClient client) {
        
        this.nom = nom;
        this.numeropiece = numeropiece;
        this.client=client;
        this.position = new Orientation(0, 0);

    }
    public Personnage (InterfaceClient client) throws RemoteException{
        this.client= client;
        this.nom=client.getNom();
        this.position=new Orientation(0, 0);
    }

   

    public String getNom() {
        return nom;
    }

    public int getNumeropiece() {
        return numeropiece;
    }

    public Integer getVieJoueur() {
        return vieJoueur;
    }

    public Integer getNbreCombat() {
        return nbreCombat;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setNumeropiece(int numeropiece) {
        this.numeropiece = numeropiece;
    }

       public void setClient(InterfaceClient client) {
        this.client = client;
    }
    public void setVieJoueur(Integer vieJoueur) {
        this.vieJoueur = vieJoueur;
    }

    public void setNbreCombat(Integer nbreCombat) {
        this.nbreCombat = nbreCombat;
    }

    public Scanner getSc() {
        return sc;
    }

    public InterfaceClient getClient() {
        return client;
    }

    public void setSc(Scanner sc) {
        this.sc = sc;
    }

 
    


    public void setVie(Integer vie) {
        this.vieJoueur = vie;
        if(vieJoueur <0){
        vieJoueur=0;
        }
    }
     public void affichage(String s) throws RemoteException {
       
        System.out.println(s);
    }

    public Orientation getPosition() {
        return position;
    }

    public void setPosition(Orientation position) {
        this.position = position;
    }
    

    
}
