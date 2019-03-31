/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionCombat;

import ConnexionBD.Registre;
import GestionClient.InterfaceClient;
import GestionPersonnage.ListePersonnagePiece;
import GestionPersonnage.Personnage;
import GestionServer.Piece;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author root
 */

public class CombatImpl extends UnicastRemoteObject implements InterfaceCombat{

    private String NomMonstre[]={"SEYE","BOBO","BAH" };
    private ArrayList<Monstre> lesMonstre;
    String porte;
    private ArrayList<Personnage> liste;
    private int numeroPiece;
    private CombatMonstre combatMonstre;
    private Registre base;
    public CombatImpl() throws RemoteException {
		super();
                 lesMonstre=new ArrayList<>();
                 this.base=base;
                

	}

	private Piece piece;

	public void effectuerCombat( Personnage personnage, Monstre  monstre) {
		Scanner read = new Scanner(System.in);
		int choix;

		System.out.println("attaqer?");
		System.out.println("1. Monstre");
		System.out.println("2. Personnage\n");
		choix = read.nextInt();

		if (choix == 1) {
			faire_Attaque(personnage, monstre);

		} else if (choix == 2) {
			faire_Attaque(personnage,monstre);
		} else {
			System.out.println("Veuillez entrer 1 ou 2!");
		}

	}

	public void faire_Attaque(Personnage attaquant, Monstre defenseur) {
		boolean mortA = attaquant.isMort();
		boolean mortD = defenseur.isEtatMonstre();

		defenseur.diminuerVieMonstre(Integer.SIZE);
		System.out.printf(attaquant.getNom(), defenseur.getNomMonstre());

		if (mortA = true)
			defenseur.ajouterVieJoueur(Integer.SIZE);

		if (mortD = true)
			attaquant.setVieJoueur(Integer.MIN_VALUE);
	}

	public boolean isCombat(int numP) {

		return false;
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
         public int verifierEtatJoueur(InterfaceClient client) throws RemoteException
     {
         Personnage pers=chercherPersonnage(client.getNom());
         
         return pers.getNbreCombat();
     }

}