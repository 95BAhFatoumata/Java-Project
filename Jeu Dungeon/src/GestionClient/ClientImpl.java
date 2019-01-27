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

    public String getNom() {
        return nom;
    }
    
    public ClientImpl() throws RemoteException
    {
      super();
      sc=new Scanner(System.in);
      vie=10;
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
        System.out.println("1.pour se deplacer ou Rester dans la meme piece");
         System.out.println("2.pour chatter ");
         System.out.println("3.pour quitter ");
        
        
    }
}
