/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*<br>Classe repr�sentant une pi�ce dans un labyrinthe</br>
 * <br>On peut y trouver des monstres </br> 
 * 
 * TODO Finir la cr�ation de l'affichage des pi�ces (en mode textuel)
 */

package GestionServer;

import GestionPersonnage.Personnage;
import java.util.ArrayList;


import java.util.Arrays;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedLis;
import java.util.LinkedList;
import static javax.swing.text.StyleConstants.Orientation;
/**
 *
 * @author root
 */
public class Piece {
     private int numeroPiece;
     private ArrayList<String>nomPorte;
     private ArrayList<Personnage>personneDansLapiece;
     
	private Orientation coord;
	private Hashtable users;

	
	/* Indique si on est d�j� venu dans cette pi�ce */
	private boolean estDejaVenu = false;
	private boolean aTresor     = false;
	private boolean estSortie   = false;
	private boolean estEntree   = false;

	/*Nombre de monstres */
	private int nbMstres  = -1;
	private int nbPieges  = -1;

	private LinkedList  monstres = null;
	private LinkedList  pieges   = null;  
     
    /*
    *nom de la porte qui peut être NORD ou SUD ou ESTou OUEST.
    */
    private String porte[]={"NORD","SUD","EST","OUEST"};
    
    public Piece(Integer numeroPiece) {
        
        this.numeroPiece = numeroPiece;
        this.nomPorte=new ArrayList<>();
        personneDansLapiece=new ArrayList<>();
        creerUnePiece(numeroPiece);
               
    }
    public Piece(Orientation coord)
	{
		this.set_Coordonnees(coord);
	    users = new Hashtable();
	}
    
    
       /**
    * 
    * @param numeroPiece 
    * On initialise les portes de chaque pièce en fonction du numero de pièce
    * le labyrinthe est composé de 9 pièces 3*3
    */
    public void creerUnePiece(Integer numeroPiece)
    {
        
        if(numeroPiece==1 || numeroPiece==2)
        {
          for(int i=1;i<porte.length;i++)
          {
              
              nomPorte.add(porte[i]);
          
          }
             
        }
        
        else if(numeroPiece==4 || numeroPiece==7)
        {
              for(int i=0;i<porte.length-1;i++)
              nomPorte.add(porte[i]);
            
        }
          else if(numeroPiece==5 || numeroPiece==8)
        {
            for(int i=0;i<porte.length;i++)
              nomPorte.add(porte[i]);
        }
        else 
        {
          
              
              nomPorte.add(porte[3]);
              nomPorte.add(porte[1]);
           
        }
    }





/**
 * @author DIALLO
 *	
 * 
 

	/**
	 * Constructeur par d�faut de la classe
	 * @param coord Place de la pi�ce dans le labyrinthe
	 * @see Labyrinthe
	 */
	
	
	/**
	 * Constructeur complet de la classe (mettre 0 s'il n'y a pas un des composants)
	 * @param coord Coordonn�es de la place dans le donjon
	 * @param nbMonstres Nombre de monstres dans la pi�ce
	 * @param nbPieges Nombre de pi�ges dans la pi�ce
	 * @param nbTresors Nombre de tr�sors dans la pi�ce
	 */
	public Piece(Orientation coord, int nbMonstres, int nbPieges)
	{
		this.set_Coordonnees(coord);
	    users = new Hashtable();

		
		this.nbMstres  = nbMonstres;
		this.nbPieges = nbPieges;
		
	}
	
	/**
	 * Re-d�finition de la m�thode equals (test d'�galit�)
	 * @param o Objet � tester
	 * @return Bol�en indiquant si l'instance pas� en param�tre est �gale � celle en cours
	 */
	public boolean equals(Object o)
	{
		boolean bon = false;
		
		/* Il faut qeu ce soient d�j� 2 instances de la m�me classe
		 * Pour pouvoir les comparer.
		 */
		if( o instanceof Piece )
		{
			Piece pp = (Piece)o;
			
			bon = this.coord.equals( pp.coord );
			bon &= (this.nbMstres == pp.nbMstres);
			bon &= (this.nbPieges == pp.nbPieges);
			bon &= (this.users.size() == pp.users.size());
		}
		
		return bon;
	}
	
	/**
	 * M�thode permettant de savoir rapidement si la pi�ce contient des �l�ments 
	 * @return bol�en indiquant si la pi�ce est vide 
	 */
	public boolean est_Vide()
	{
		boolean bon = true;
		
		/* On regarde pour chaque liste d'�l�ments si 
		 * Elle est non nulle ET
		 * Si elle est � vide.
		 */ 
		
		bon =  (! this.A_Monstres() );
		bon &= (! this.A_Pieges() );
		bon &= (this.users.isEmpty());
		
		return bon;
	}
	
	
	/**
	 * Red�finition de la m�thode tostring
	 * @return Chaine repr�sentant l'objet en texte
	 */
	public String toString ()
	{
		StringBuffer sb = new StringBuffer();
		
		/* On affiche d'abord les coordonn�es */
		sb.append(this.get_Coordonnees());
		
		sb.append("\nPr�sence d'un monstre : ");
		sb.append("\n").append( this.A_Monstres() );
		
		sb.append("\nPr�sence d'un pi�ge : ");
		sb.append("\n").append( this.A_Pieges() );
		
		sb.append("\nUtilisateurs present : ");
		sb.append("\n").append(this.users.toString());

		return sb.toString();		
	}
	
	/**
	 * M�thode permettant d'afficher une pi�ce sous une forme carr�e
	 * @param lg Longueur du carr� (ou rectangle) repr�sentant la pi�ce
	 * @param larg Largeur du carr� (ou rectangle) repr�sentant la pi�ce
	 * @return Retourne une chaine qui repr�sente une pi�ce (dessin�e en texte)
	 */
	public String representation_Texte(int lg, int larg)
	{
		/* Dessin de  : (exemple)
		 * ------------
		 * |          |
		 * |    M     |
		 * |          |
		 * ------------
		 */
		
		StringBuffer bf = new StringBuffer("o");
		
		bf.append( TravailleChaine.repeter_Caractere("_", larg) );
		bf.append("o\n");
		
		int milI = (int)(lg / 2) + 1;
		
		for(int i = 1; i < milI + 1; i ++)
		{
			bf.append( TravailleChaine.rajouter_Blanc(larg, " ", " ") ).append("|\n");
		}
		
		bf.append( this.attribuer_Lettre(larg) ).append("|\n");
		
		for(int i = 1; i < milI + 1; i ++)
		{
			bf.append( TravailleChaine.rajouter_Blanc(larg, " ", " ") ).append("|\n");
		}
		bf.append("users: ");
		bf.append("o");
		bf.append( TravailleChaine.repeter_Caractere("-", larg) );
		bf.append("o");
		
		return bf.toString();
	}
	
	/**
	 * Permet de renvoyer une chaine qui indique si la pi�ce est ou non vide
	 * @return Chaine indiquant si la pi�ce a un �l�ment (monstre, pi�ge, ...)
	 */
	public String attribuer_Lettre()
	{
		StringBuffer bf = new StringBuffer("");
		
		/* On va afficher soit un M pour monstre
		 *                soit un T pour tresor
		 *                soit un P pour piege
		 *                soit un R pour une pi�ce vide
		 */
		
		if( this.nbMstres > 0 )
		{
			bf.append("M");
		}
		if( this.nbPieges > 0 )
		{
			bf.append("P");
		}
		if( this.est_Vide() )
		{
			bf.append("?");
		}
		
		return bf.toString();
	}
	
	/**
	 * Permet de renvoyer une chaine qui indique si la pi�ce est ou non vide
	 * @param lgTotal Longueur total de la chaine � renvoyer
	 * @return Chaine indiquant si la pi�ce a un �l�ment (monstre, pi�ge, ...)
	 */
	public String attribuer_Lettre(int lgTotal)
	{
		StringBuffer bf = new StringBuffer("");
		
		/* On va afficher soit un M pour monstre
		 *                soit un P pour piege
		 *                soit un R pour une pi�ce vide
		 */
		
		if( this.nbMstres > 0 )
		{
			bf.append("M");
		}
		if( this.nbPieges > 0 )
		{
			bf.append("P");
		}
		if( this.est_Vide() )
		{
			bf.append("?");
		}
		bf = new StringBuffer().append( TravailleChaine.rajouter_Blanc(lgTotal, bf.toString(), " ") );
		
		return bf.toString();
	}
	
	
	/**
	 * M�thode permettant de savoir si la pi�ce a �t� initialis�e
	 * (Accessible que pour la package car elle ne sert � rien ailleurs,
	 *  Mais est utilis� par des classes de son package)
	 * @return Bool�en indiquant si la pi�ce a �t� initialis�e
	 */
	boolean get_PasInitialisee()
	{
		boolean bon;
		
		bon = ( (this.nbMstres   == -1) && 
				(this.nbPieges   == -1) );		
		return bon;
	}
	
	//enregistrement d'un nouvel joueur
	public synchronized  boolean enregistrementUtilisateur(String nom, Object user)
	{
	    users.put(nom, user);
	    return true;
	}
	
	//Supression de l'utilisateur ayant le nom passe en parametre
	public synchronized void effacerUtilisateur(String nom)
	{
	    users.remove(nom);
	}
	/*----------------------------------------------------------*/
	/*----------------  ACCESSEURS & MUTATEURS  ----------------*/
	/*----------------------------------------------------------*/
	
	/**
	 * Accesseur de la liste des monstres sous une cha�ne
	 * @return Chaine listant l'ensemble des monstres
	 */
	public String get_ListeMonstres()
	{
		StringBuffer sb = new StringBuffer("");
		
		/* Si la pi�ce a des monstres, on les affiche, un par un */
		if( this.A_Monstres() )
		{
			Iterator i;
			i = this.monstres.iterator();
			while( i.hasNext() )
			{
				Monstre mst = (Monstre)i;
				sb.append(mst);
			}
		}
		
		return sb.toString();
	}
	
	/**
	 * Accesseur de la liste des pi�ges sous une cha�ne
	 * @return Chaine listant l'ensemble des pi�ges
	 */
	public String get_ListePieges()
	{
		StringBuffer sb = new StringBuffer("");
		
		/* Si la pi�ce a des pi�ges, on les affiche, un par un */
		if( this.A_Pieges() )
		{
			Iterator i;
			i = this.monstres.iterator();
			while( i.hasNext() )
			{
				Piege p = (Piege)i;
				sb.append(p);
			}
		}
		
		return sb.toString();
	}
	

	/**
	 * Liste des monstres pr�sents dans la pi�ce
	 * @return Retourne l'ensemble des monstres pr�sents dans la pi�ce
	 * @see Monstre
	 */
	public final Object[] get_Monstres()
	{	
		/* Si on n'est pas d�j� pass� dans cette pi�ce, on cr�e l'ensemble des monstres */
		if(! this.est_DejaPasseIci())
		{
			/* this.estDejaVenu = true; */
			/* S'il doit y avoir des monstres */
			if(this.nbMstres > 0)
			{
				this.monstres = new LinkedList();
				/* On g�re la cr�ation des monstres 
				 * Suivant le pourcentage de la super super classe m�re (Jeu)
				 */
				int nbM = this.nbMstres;
				for(int i = 0; i < nbM; i ++)
				{
					Monstre mst = new Monstre();
					this.monstres.add(mst);
				}
			}
		}
		
		return this.monstres.toArray();
	}
	
	/**
	 * Liste des pi�ges pr�sents dans la pi�ce
	 * @return Retourne l'ensemble des pi�ges pr�sents dans la pi�ce
	 * @see Piege
	 */
	public final Object[] get_Pieges()
	{
		/* Si on n'est pas d�j� pass� dans cette pi�ce, on cr�e l'ensemble des monstres */
		if(! this.est_DejaPasseIci() )
		{
			this.estDejaVenu = true;
			/* S'il doit y avoir des pi�ges */
			if(this.nbPieges > 0)
			{
				this.pieges = new LinkedList();
				/* On g�re la cr�ation des pi�ges 
				 * Suivant le pourcentage de la super super classe m�re (Jeu)
				 */
				for(int i = 0; i < this.nbPieges; i ++)
				{
					Piege pg = new Piege();
					this.pieges.add(pg);
				}
			}
		}
		
		return this.pieges.toArray();
	}
	/**
	 * Permet de savoir si on ets d�j� venu dans la pi�ce auparavant
	 * @return Bool�en indiquant si on est d�j� pass� dans cette pi�ce
	 */
	public final boolean est_DejaPasseIci()
	{
		return this.estDejaVenu;
	}
	
	/**
	 * Accesseur permettant de savoir si c'est la pi�ce de sortie
	 * @return Bool�en indiquant si oui ou non la pi�ce est la pi�ce de sortie
	 */
	public final boolean est_LaPieceSortie()
	{
		return this.estSortie;
	}
	
	/**
	 * Accesseur indiquant si la pi�ce a des pi�ges
	 * @return Retourne un bool�en de contr�le
	 */
	public final boolean A_Pieges()
	{
		boolean bon = false;
		/* On a un pi�ge si il y a des pi�ges dans la pi�ce 
		 * Et le pi�ge n'est pas d�sactiv�
		 */
		bon = ( this.nbPieges > 0 );	
		
		return bon;
	}
	
	/**
	 * Accesseur indiquant si la pi�ce a des monstres
	 * @return Retourne un bool�en de contr�le
	 */
	public final boolean A_Monstres()
	{
		boolean bon = false;
		
		/* Commentaires, idem que pour : A_Pi�ges */ 
		bon = ( this.nbMstres > 0 );
		
		return bon;
	}

	/**
	 * Accesseur des coordonn�es de la pi�ce
	 * @return Renvoie l'orientation (les coordonn�es) de la pi�ce
	 * @see Orientation
	 */
	public final Orientation get_Coordonnees()
	{
		return this.coord;
	}
	
	/**
	 * Accesseur du nombre de monstres dans la pi�ce
	 * @return Entier rep�sentant le nombre de monstres dans la pi�ce  
	 */
	public final int get_NBmonstres()
	{
		return (this.monstres != null ? this.monstres.size() : 0);
	}
	
	/**
	 * Accesseur du nombre de pi�ges dans la pi�ce
	 * @return Entier rep�sentant le nombre de pi�ges dans la pi�ce  
	 */
	public final int get_NBpieges()
	{
		return (this.pieges != null ? this.pieges.size() : 0);
	}
	
	/**
	 * Mutateur du nombre de monstres (ne fonctionne qu'une seule fois)
	 * @param nb <li>Nombre de monstres dans la pi�ce (de 0 � l'infini)</li>
	 *           <li>Ne peut �tre mis qu'une seule fois</li>
	 */
	private final void set_NbMstres(int nb)
	{
		if( this.nbMstres == -1 )
		{
			this.nbMstres = nb;
		}
	}
	
	/**
	 * Mutateur du nombre de pi�ges (ne fonctionne qu'une seule fois)
	 * @param nb <li>Nombre de pi�ges dans la pi�ce (de 0 � l'infini)</li>
	 *           <li>Ne peut �tre mis qu'une seule fois</li>
	 */
	private final void set_NbPieges(int nb)
	{
		if( this.nbPieges == -1 )
		{
			this.nbPieges = nb;
		}
	}
	
	/**
	 * <li>M�thode permettant de tout pr�ciser d'un seul coup</li> 
	 * <li>(nombre de monstres, de tr�sors et de pi�ges)</li>
	 * @param nbM Nombre de monstres dans la pi�ce
	 * @param nbT Nombre de tr�sors dans la pi�ce
	 * @param nbPi Nombre de pi�ges dans la pi�ce
	 * @see Piege
	 * @see Tresor
	 * @see Monstre
	 */
	public final void set_Tous(int nbM, int nbPi)
	{
		this.set_NbPieges(nbPi);
		this.set_NbMstres(nbM);
	}
	
	/**
	 * Mutateur de l'orientation de la pi�ce (les coorodnn�es)
	 * @param or Nouvelle orientation de la pi�ce
	 * @see Orientation
	 */
	public final void set_Coordonnees(Orientation or)
	{
		/* Si on n'a pas d�j� d�finit l'orientation */
		if(this.coord == null)
		{
			this.coord = (Orientation)or.clone();
		}
	}
	
	/**
	 * <li>Ne s'utilise qu'une seule fois</li>
	 * <li>Indique que cette pi�ce est la pi�ce de sortie</li>
	 */
	public final void set_EstLaSortie()
	{
		if(! this.estSortie )
		{
			this.estSortie = true;
		}
	}
	
	/**
	 * M�thode permettant d'indiquer � cette pi�ce qu'elle est la pi�ce d'arriv�e du h�ros
	 */
	public final void set_EstLarrivee()
	{
		if(! this.estEntree)
		{
			this.estEntree = true;
		}
	}

	public Hashtable getUsers() {
		return users;
	}

	public void setUsers(Hashtable users) {
		this.users = users;
	}
	
	
	
	

}    

