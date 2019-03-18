/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionClient;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

/**
 *
 * @author fatou
 */
public class ClientImpl extends UnicastRemoteObject implements InterfaceClient,Serializable {
    private int vie;
    private String nom;
    private Scanner sc;
     int numeropiece = 0;

    public String getNom() {
        return nom;
    }

    public ClientImpl() throws RemoteException {
        
        sc=new Scanner(System.in);
        this.vie = 10;
    }
    
     public int getNumeropiece()throws RemoteException{
       
        return numeropiece;
    }
  
    public void setNumeropiece(int numeropiece)throws RemoteException{
        this.numeropiece = numeropiece;
    }
  
    
    public void saisirPseudo()
    {
       do
       {
        System.out.println("Entrer votre pseudo");
        nom=sc.nextLine();
        if(nom.length()<3)
               System.out.println("Votre pseudo est trop court");
       }while(nom.length()<3);   
       }
    
 

    
    public void Menu() 
    {
        System.out.println("##############################################");
        System.out.println("# 1.Se deplacer ou Rester dans la même piece #");
        System.out.println("# 2. Chatter                                 #");
        System.out.println("# 3.Quitter                                  #");
        System.out.println("##############################################");
        
    }
    
    public String choixclient() throws RemoteException
    {
       return sc.nextLine();
    }
    
    public String envoyerMessage() throws RemoteException {
        
        
        System.out.println("1: saisir  votre message");
        System.out.println("2:saisir q pour quittez");
        
        Scanner sc=new Scanner(System.in);
        String Message=new String();
        String test=new String();
        Message+="["+nom+"] ";
        
        test=sc.nextLine();
           if(!test.equals("q"))
           {
                return      Message+=test;
           }
           else
           {
              return test;
           }
        
    }
}
