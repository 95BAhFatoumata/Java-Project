/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionClient;

import GestionPersonnage.Personnage;
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
     private Personnage personnage;

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
        System.out.println("# 3. combat                                  #");
        System.out.println("# 4.Quitter                                  #");
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
    public void recupererMessage(String message) throws RemoteException {
       //To change body of generated methods, choose Tools | Templates.
       System.out.println(message);
       
    }

    @Override
    public void affichage(String s) throws RemoteException {
        //To change body of generated methods, choose Tools | Templates.
      System.out.println(s);
    }

    @Override
    public void setPersonnage(Personnage pers) throws RemoteException{
         //To change body of generated methods, choose Tools | Templates.
         this.personnage=pers;
    }

    @Override
    public Personnage getPersonnage()  throws RemoteException{
        //To change body of generated methods, choose Tools | Templates.
        return personnage;
        
    }
   
      
}
