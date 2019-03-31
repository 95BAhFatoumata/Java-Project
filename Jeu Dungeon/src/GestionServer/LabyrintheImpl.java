/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GestionServer;

import ConnexionBD.Registre;
import GestionClient.Personne;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import GestionClient.*;
import GestionPersonnage.ListePersonnagePiece;
import GestionPersonnage.Personnage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fatou
 * </br>
 * Classe principale du jeux de donjons 
 * </ul>
 * Elle permet de mettre en place un certain nombre de pi�ces, 
 * Dans un format 2D (longueur et largeur)
 * Par la suite, c'est par elle que l'utilisateur se d�placera dans chaque pi�ce
 * 
 * @see Piece
 * 
 */
public class LabyrintheImpl extends UnicastRemoteObject implements InterfaceLabyrinhe{
    private String nom;
    private Registre registre;
    private Personnage perso; 
    private ListePersonnagePiece listeClient;
    private ArrayList<Piece>pieces;
    private ArrayList<Personnage> personnages = new ArrayList<>();
     /*----------------------------------------------------------------------------*/
	private static LabyrintheImpl _lab;//on utilise ici le design pattern singleton
	private Piece [][]tab2D;
	private Piece entree, sortie;
        private int  nbMonstres = -1, nbPieges = -1;
	private int mstParPiece, piegParPiece;
	private int nbL = 0;
	private int nbC = 0;
	
	/**
	 * Constructeur de la classe permettant de g�rer les composants du jeu
	 * @param nbLignes Longueur du labyrinthe
	 * @param nbColonnes Largeur du labyrinthe 
	 * @param nb_Mstr Pourcentage de monstres
	 * @param nb_Piege Pourcentage de pi�ges
	 * @param mParPiece Nombre de monstres par pi�ce au maximum
	 * @param pParPiece Nombre de pi�ges par pi�ce au maximum
	 */
    
   /* public LabyrintheImpl(String nom) throws RemoteException{
        this.nom = nom;
       registre =  new Registre();
        listeClient = new ListePersonnagePiece();
        listeClient.initialiser();
        pieces=new ArrayList();
    }*/
   public LabyrintheImpl(String nom, int nbLignes, int nbColonnes, int nb_Mstr, int nb_Piege, int mParPiece, int pParPiece) throws RemoteException
	{
		/* D�finition des dimensions du Labyrinthe */
		this.set_Longeur(nbLignes);
		this.set_Largeur(nbColonnes);
		this.tab2D = new Piece[nbLignes][nbColonnes];
		this.setNom(nom);
		this.registre = new Registre();
                listeClient = new ListePersonnagePiece();
                listeClient.initialiser();
                 pieces=new ArrayList();
                 this.nom = nom;
		
		/* D�finition des bornes maximales du jeu */
		Orientation.set_MAX_ABS( (nbColonnes - 1) );
		Orientation.set_MAX_ORD( (nbLignes - 1) );
		
		/* D�finition du nombre maximum d'�l�ments par pi�ce */
		this.mstParPiece = mParPiece;
		this.piegParPiece = pParPiece;
		
		/* On enl�ve 3 car il y a 3 pi�ces sp�ciales qui n'ont pas besoin 
		 * D'�tre initialis�es (pi�ce d'arriv�e, de sortie, du tr�sor)
		 */
		int ttPieces = (nbLignes * nbColonnes) - 2;
		
		/* Recherche du nombre d'�l�ments en tout dans le donjon */
		this.nbMonstres = nb_Mstr;
		this.nbPieges = nb_Piege;
		
		/* Si le total de tous les �l�ments est inf�rieur au nombre total de pi�ce
		 * On ajoute un au nombre de tr�sor (favoriser la facilit�).
		 */
		
		
		/* On lance la cr�ation des diff�rentes pi�ces du labyrinthe */
		this.generer_labyrinthe();
	}
    
    
     public void CreationDuLabyrinthe() throws RemoteException, SQLException
   {
    
      registre.connexionBD();
      
       for (int i = 1; i < 10; i++) {
           registre.connexionBD();
        //création des pieces du labyrinth
         pieces.add(new Piece(i));
           
       }
   }
    
    public void connexion(InterfaceClient client) throws RemoteException {
        
		 String reponse=new String();
		 String requeteDeVerificaion;
                 String requeteInsertion;
                 int vie = 0;
                 
        try {
            registre.connexionBD();
        } catch (SQLException ex) {
            Logger.getLogger(LabyrintheImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
          requeteDeVerificaion="SELECT pseudo FROM \"JOUEUR\" WHERE pseudo='"+client.getNom()+"'";
          reponse=registre.executerRequete(requeteDeVerificaion);
          // on verifie dans la base de donnée si le pseudo existe
          
           if(reponse.equals(client.getNom())&& client.getNom().length()>=3)
           {
               
                perso = new Personnage (client.getNom(), 0, client);
                //on crée un nouveau personnage
        	perso.setNom(client.getNom());
                perso.setClient(client);
                perso.setPosition(this.get_PieceArrive().get_Coordonnees());
                client.setPersonnage(perso);
                this.personnages.add(perso);
                System.out.println(perso.getNom());
                System.out.println(perso.getPosition().get_coordI() +" , "+perso.getPosition().get_coordJ() );
                
                requeteDeVerificaion="select numeropi from \"SETROUVER\" WHERE pseudopi='"+perso.getNom()+"'";
                perso.setNumeropiece(Integer.parseInt(registre.executerRequete(requeteDeVerificaion)));
               System.out.println("test2:"+perso.getNumeropiece());
                requeteDeVerificaion="select viejoueur from \"JOUEUR\" where pseudo='"+perso.getNom()+"'";
                vie=Integer.parseInt(registre.executerRequete(requeteDeVerificaion));
                
                System.err.println("la vie du joueur" + vie);
                
                    if(vie==0)
                {
                    perso.setVie(10);
                    registre.mettreAjourvieJoueur(perso.getNom(), 10);
                }
                    
                else
                {
                    perso.setVie(vie);
                    registre.mettreAjourvieJoueur(perso.getNom(), vie);
                }
                    listeClient.ajouterClientPiece(perso.getNumeropiece(), perso);
           }
           else
           {
               
                //Première connexion on l'ajoute dans la pièce 1
                
        	   if(client.getNom().length()>=3)
        	   {
                    perso= new Personnage (client.getNom(), 0, client);
                    perso.setPosition(this.get_PieceArrive().get_Coordonnees());
                    client.setPersonnage(perso);
                    this.personnages.add(perso);
        	    requeteDeVerificaion="INSERT INTO \"JOUEUR\" VALUES('"+client.getNom()+"')";
        	    registre.insertion(requeteDeVerificaion);
                    
                   requeteInsertion="INSERT INTO \"SETROUVER\" VALUES ('"+client.getNom()+"','1')";
                   registre.insertion(requeteInsertion);
           
        	    perso.setNom(client.getNom());
                    perso.setNumeropiece(1);
                    perso.setClient(client);
                    client.setNumeropiece(1);
                    System.out.println("test1:"+perso.getNumeropiece());
                   registre.mettreAjourvieJoueur(client.getNom(), 10);
                   listeClient.ajouterClientPiece(1, perso);
             
                     
        	   }
                   else{
                   // si tout se passe mal on informe le client
                   
                   }
        	   
                       
            }
		

   
	}
    // on recupère la liste des clients dans un salon pour l'envoyer au serveur de chat 
   
    public ArrayList<Personnage>  recupererListe() throws RemoteException {
      
       ArrayList<Personnage>res = new ArrayList<>();
       for(int i=1;i<10;i++)
       {
           for(Personnage perso:listeClient.recupererListe(i))
           {
               res.add(perso);
           }
       }
       return res;
       
    }
 // recupère le numero de la pièce pour pouvoir l'envoyer au serveur de chat 
    @Override
    public int recupererNumeroPiece(InterfaceClient client) throws RemoteException {
               
        return listeClient.chercherPersonnage(client.getNom());
    }

    @Override
    public String InfoDest(ClientImpl client) throws RemoteException {
         //To change body of generated methods, choose Tools | Templates.
         
         // A chaque deplacement on l'informe de sa position et les portes pour se deplacer
         String requete="select portepi,numpidest from \"TRAVERSER\" where numerop='"
             +listeClient.chercherPersonnage(client.getNom())+"'";
   requete+="\n TAPEZ N POUR NORD \n TAPEZ S POUR SUD \n TAPEZ O pour OUEST \n TAPEZ E pour EST \n TAPEZ R POUR RESTER";
       
      
   
        return requete;
    }
/*Creation d'un nouveau labyrinthe en utilisant le pattern singleton*/
	public static LabyrintheImpl Nouveau(String nom, int nbLignes, int nbColonnes, int nb_Mstr, int nb_Piege) throws RemoteException{
		if(LabyrintheImpl._lab == null)		
		{
			LabyrintheImpl._lab = new LabyrintheImpl(nom, nbLignes, nbColonnes, nb_Mstr, nb_Piege, 1, 1);
		}
		
		return LabyrintheImpl._lab;
	}
	
	/**
	 * M�thode permettant de g�n�rer l'ensemble des pi�ces 
	 * Pr�sentent dans le tableau 2D
	 */
	private void generer_labyrinthe()
	{
		int nbc, nbl;
		
		nbl = this.get_Longueur();
		nbc = this.get_Largeur();
		/* On parcours l'ensemble des lignes */
		for( int i = 0; i < nbl; i ++ )
		{	
			/* On parcours l'ensemble des colonnes */
			for( int j = 0; j < nbc; j ++ )
			{
				/* On cr�e chaque pi�ce, par d�faut */
				Piece pie = new Piece( new Orientation(i, j) );
				this.set_UneCase(i, j, pie);
			}
		}
		this.attribution_PiecesSpeciales();
		this.attribuer_AutresPieces();
	}
	
	/**
	 * G�re l'attribution des monstres, pi�ges et tr�sors pour toutes les autres pi�ces 
	 */
	protected void attribuer_AutresPieces()
	{
		this.creer_AttribuerMonstres();
		this.creer_AttribuerPieges();
	}
	
	/**
	 * M�thode priv�e qui attribue de mani�re g�n�rique des �l�ments � une pi�ce
	 * @param nbMstrs Nombre de monstres dans la pi�ce
	 * @param nbPieges Nombre de pi�ges dans la pi�ces
	 * @param totalUnElt Maximum pour un des �l�ment
	 */
	protected void attribuer(int nbMstrs, int nbPieges, int totalUnElt)
	{
		/* On va indiquer � Nb pi�ces, ce nombre correspondant au pourcentage
		 * Attribu� � la construction du labyrinthe, qu'elles ont des monstres
		 */
		int compte = 0;
		int i, j, nbC, nbL;
		
		nbL = this.get_Longueur();
		nbC = this.get_Largeur();
		Piece pArrive = this.get_PieceArrive();
		Piece pSortie = this.get_PieceSortie();
		
		while(compte < totalUnElt)
		{
			i = Rand.EntierEntre(0, nbL - 1);
			j = Rand.EntierEntre(0, nbC - 1);
			
			Piece pie = this.get_UneCase(i, j);
			/* La pi�ce est vide donc on ne l'a pas encore g�rer */
			if( pie.get_PasInitialisee() && (pie != pArrive) 
										 && (pie != pSortie) )
			{ /* Ici, on compare la r�f�rence, car cela peut �tre vraiment (r�f�rence) la m�me pi�ce */
				pie.set_Tous(nbMstrs, nbPieges);
				compte ++;
			}
		}
	}
	
	/**
	 * M�thode permettant de cr�er NbMonstres (NbMonstres sp�cifi� au constructeur)
	 */
	protected void creer_AttribuerMonstres()
	{
		/* On va indiquer � Nb pi�ces, ce nombre correspondant au pourcentage
		 * Attribu� � la construction du donjon, qu'elles ont des monstres
		 */
		this.attribuer( this.mstParPiece, 0, this.get_NbMonstresTotal() );
	}
	
	/**
	 * M�thode permettant de cr�er NbPieges (NbPieges sp�cifi� au constructeur)
	 */
	protected void creer_AttribuerPieges()
	{
		/* On va indiquer � Nb pi�ces, ce nombre correspondant au pourcentage
		 * Attribu� � la construction du donjon, qu'elles ont des pi�ges
		 */
		this.attribuer( 0, this.piegParPiece, this.get_NbPiegesTotal() );
	}
	
	/**
	 * M�thode permettant de g�rer l'attribution des deux cellules sp�ciales (entr�e, arriv�e)
	 */
	protected void attribution_PiecesSpeciales()
	{
		int coordI = 0, coordJ = 0;
		int depart = Rand.EntierEntre(0, 1);
		int lg = this.get_Longueur();
		int larg = this.get_Largeur();
		
		/* On tire un nombre au hasard entre 1 et 2 pour savoir 
		 * Sur quelle coordonn�e de d�part on se fixe 
		 * 0 => Ordonn�e, 1 => Abscisse
		 */
		if(depart == 0)
		{	
			/* On cherche � avoir des coordonn�es faisants le contour du donjon */
			coordI = Rand.EntierEntre(0, lg - 1);
			
			if( (coordI == 0) || (coordI == (lg - 1) ) )
			{
				coordJ = Rand.EntierEntre(0, larg - 1);
			}
			else
			{
				boolean bon = false;
				while(! bon)
				{
					coordJ = Rand.EntierEntre(0, larg - 1);
					bon = ( (coordJ == 0) | (coordJ == larg - 1) );
				}
			}
		}
		else
		{
			/* On cherche � avoir des coordonn�es faisants le contour du labyrinthe */
			coordJ = Rand.EntierEntre(0, larg - 1);
			
			if( (coordJ == 0) || (coordJ == (larg - 1) ) )
			{
				coordI = Rand.EntierEntre(0, lg - 1);
			}
			else
			{
				boolean bon = false;
				while(! bon)
				{
					coordI = Rand.EntierEntre(0, lg - 1);
					bon = ( (coordI == 0) | (coordI == lg - 1) );
				}
			}
		}
				
		/* On d�finit la pi�ce o� le joueur devra sortir */
		Piece pce = this.get_UneCase(coordI, coordJ); 
		this.set_PieceSortie( pce );
		this.set_PieceArrive( pce );
				
		//this.gestion_TresorFinal(coordI, coordJ);
	}
	/**
	 * Re-d�finition de la m�thode toString
	 * @return Chaine affichant l'ensemble du labyrinthe (toutes ses pi�ces)
	 */
	public String AfficherLab() throws RemoteException
	{
		StringBuffer bf = new StringBuffer("\n");
		
		for(int i = 0; i < this.nbL; i ++)
		{
			bf.append( this.afficheUneLigne(i) );
		}
		
		bf.append("\n");
		return bf.toString();
	}
	
	/**
	 * M�thode permettant d'afficher une ligne du tableau 
	 * @param lig Ligne du tableau qui va �tre affich�e 
	 * @return Chaine �num�rant l'ensemble des pi�ces avec leur contenu pour une ligne donn�e
	 * <li>TODO G�rer la taille des pi�ces dans la classe de configuration (ici, c'est �gal � 5*5)</li>
	 */
	protected String afficheUneLigne(int lig)
	{
		StringBuffer bf = new StringBuffer("");
		int nbL = this.get_Longueur();
		int nbC = this.get_Largeur();
		bf.append( TravailleChaine.avant_ElementPiece(nbC, 5) );
		/* 2) On affiche le contenu de chaque pi�ce pour une ligne */
		for(int j = 0; j < nbC; j ++)
		{
			Piece pi = this.get_UneCase(lig, j);
			String heros = new String();
			Orientation or = pi.get_Coordonnees();
                        int pos = 1;
			/* Si le h�ros se trouve dans la pi�ce on l'affiche aussi */
			for (Personnage personnage : personnages) {
				if( or.equals(personnage.getPosition()))
				{
					heros = new String("H"+pos);
				}
                                pos++;
			}
			StringBuffer ssSB = new StringBuffer(heros);
			/* On rajoute l'�l�ment qu'il y a dans la pi�ce */
			ssSB.append( pi.attribuer_Lettre() );
			bf.append( TravailleChaine.rajouter_Blanc(5, ssSB.toString(), " ") );
		}
		bf.append("|\n");
		bf.append( TravailleChaine.apres_ElementPiece(nbC, 5) );
		return bf.toString();
	}
	
	/**
	 * M�thode permettant de se d�placer sur la gauche
	 * @return Renvoie la pi�ce o� se trouve maintenant le h�ros
	 * @throws InvalidCoordException L�ve une exception si on se d�plce hors du donjon
	 * @see Heros
	 * @see Piece 
	 */
	public Piece Avance_Gauche(Personnage pers) throws InvalidCoordException
	{
		Piece pie = null;
		
		pers.getPosition().a_Gauche();
		pie = this.get_UneCase(pers.getPosition().get_coordI(), pers.getPosition().get_coordJ());
		
		return pie;
	}	
	
	/**
	 * M�thode permettant de se d�placer sur la droite
	 * @return Renvoie la pi�ce o� se trouve maintenant le h�ros
	 * @throws InvalidCoordException L�ve une exception si on se d�plce hors du donjon
	 * @see Heros
	 * @see Piece 
	 */
	public Piece Avance_Droite(Personnage pers) throws InvalidCoordException
	{
		Piece pie = null;
		
		pers.getPosition().a_Droite();
		pie = this.get_UneCase(pers.getPosition().get_coordI(), pers.getPosition().get_coordJ());
		
		return pie;
	}
	
	/**
	 * M�thode permettant de se d�placer sur la gauche
	 * @return Renvoie la pi�ce o� se trouve maintenant le h�ros
	 * @throws InvalidCoordException L�ve une exception si on se d�plce hors du donjon
	 * @see Heros
	 * @see Piece 
	 */
	public Piece Avance_Bas(Personnage pers) throws InvalidCoordException
	{
		Piece pie = null;
		
		pers.getPosition().en_Bas();
		pie = this.get_UneCase(pers.getPosition().get_coordI(), pers.getPosition().get_coordJ());
		
		return pie;
	}
	
	/**
	 * M�thode permettant de se d�placer sur la gauche
	 * @return Renvoie la pi�ce o� se trouve maintenant le h�ros
	 * @throws InvalidCoordException L�ve une exception si on se d�plce hors du donjon 
	 * @see Heros
	 * @see Piece 
	 */
	public Piece Avance_Haut(Personnage pers) throws InvalidCoordException
	{
		Piece pie = null;
		
		pers.getPosition().en_Haut();
		pie = this.get_UneCase(pers.getPosition().get_coordI(), pers.getPosition().get_coordJ());
		
		return pie;
	}
	
	/**
	 * M�thode permettant de tester toutes les possibilit�s autour de la pi�ce o� se trouve le h�ros
	 * @return </ul>Renvoie une chaine indiquant, suivant une coordonn�e, </ul>
	 *         </ul>Quelles cellules sont diponibles.</ul>
	 * <li>TODO Voir pour une repr�sentation plus agr�able</li>
	 * @throws RemoteException 
	 */
	public String TestPossibilite_deplacement(InterfaceClient client) throws RemoteException, InvalidCoordException
	{
		StringBuffer bf = new StringBuffer();
		Personnage pers = client.getPersonnage();
		
		/* On teste toutes les possibilit�s autour de la pi�ce 
		 * Se trouve le h�ros.
		 */
		/* On r�cup�re une copie r�elle de l'orientation 
		 * (Et on clone de nouveau � chaque test pour la m�me raison) 
		 * Pour �viter de modifier celle du h�ros
		 */
		try
		{
			Orientation or = (Orientation)pers.getPosition().clone();
			or.en_Haut();
			bf.append("H : Pi�ce au-dessus possible \n");
		}
		catch(Exception e) 
		{
			bf.append("Mur au-dessus\n"); 
		}
		
		try
		{
			Orientation or = (Orientation)pers.getPosition().clone();
			or.en_Bas();
			bf.append("B : Pi�ce au-dessous possible \n");
		}
		catch(Exception e) 
		{
			bf.append("Mur au-dessous\n");
		}
		
		try
		{
			Orientation or = (Orientation)pers.getPosition().clone();
			or.a_Gauche();
			bf.append("G : Pi�ce � gauche possible\n");
		}
		catch(Exception e) 
		{
			bf.append("Mur � gauche\n");
		}
		
		try
		{
			Orientation or = (Orientation)pers.getPosition().clone();
			or.a_Droite();
			bf.append("D : Pi�ce � droite possible \n");
		}
		catch(Exception e) 
		{
			bf.append("Mur � droite\n");
		}
		
		return bf.toString();
	}
	public void seDeplacerVers(String direction, InterfaceClient client ) throws RemoteException, InvalidCoordException{
		
		Personnage pers = client.getPersonnage();
		Piece pc;
		switch(direction) {
		
			case "H":
				pc = this.Avance_Haut(pers);
				pers.setPosition(pc.get_Coordonnees());
				break;
			case "B":
				pc = this.Avance_Bas(pers);
				pers.setPosition(pc.get_Coordonnees());
				break;
			case "D":
				pc = this.Avance_Droite(pers);
				pers.setPosition(pc.get_Coordonnees());
				break;
			case "G":
				pc = this.Avance_Gauche(pers);
				pers.setPosition(pc.get_Coordonnees());
				break;
			default:
				System.out.println("Entré invalide!!!");
				break;
		}
		client.setPersonnage(pers);
		this.rafraichirPersonnage(pers);
	}
	
	public void rafraichirPersonnage(Personnage pers) {
		this.personnages.remove(pers);
		this.personnages.add(pers);
	}

	/**
	 * <li>M�thode d�truisant litt�ralement le donjon</li>
	 * <li>Doit �tre utilis� obligatoirement � la fin d'une partie</li>
	 * <li>Sinon on garde la sauvegarde de l'ancien donjon</li>
	 */
	public static void detruire_Labyrinthe()
	{
		LabyrintheImpl._lab = null;
		Orientation.raz_ValsMaxs();
	}
		
	
	/*----------------------------------------------------------*/
	/*----------------  ACCESSEURS & MUTATEURS -----------------*/
	/*----------------------------------------------------------*/
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	
	

	/**
	 * M�thode permettant de connaitre les coordonn�es du h�ros
	 * @return Renvoie une Orientation permettant de savoir o� se trouve le h�ros
	 * @see Orientation
	 */
	
	
	/**
	 * Accesseur du nombre de monstres au total dans toutes les pi�ces
	 * @return Entier repr�sentant le nombre de monstres totalis�s dans le donjon
	 */
	protected final int get_NbMonstresTotal()
	{
		return this.nbMonstres;
	}
	
	/**
	 * Accesseur du nombre de pi�ges au total dans toutes les pi�ces
	 * @return Entier repr�sentant le nombre de pi�ges totalis�s dans le donjon
	 */
	protected final int get_NbPiegesTotal()
	{
		return this.nbPieges;
	}
	
	
	/**
	 * Accesseur de la pi�ce o� l'on trouve le tr�sor
	 * @return Renvoie un objet de type Piece
	 * @see Piece
	
	
	/**
	 * Accesseur de l'entr�e du donjon
	 * @return Retourne un objet de type Piece
	 * @see Piece
	 */
	protected final Piece get_PieceArrive()
	{
		return this.entree;
	}
	
	/** 
	 * Accesseur de la sortie du donjon
	 * @return Renvoie un objet de type Piece
	 * @see Piece 
	 */
	protected final Piece get_PieceSortie()
	{
		return this.sortie;
	}
	
	/**
	 * Accesseur d'une Pi�ce du donjon
	 * @param i Ligne de la Piece a r�cup�rer
	 * @param j Colonne de la Pi�ce � r�cup�rer
	 * @see Piece
	 * @return Renvoie une Piece du donjon
	 */
	protected Piece get_UneCase(int i, int j)
	{
		return this.tab2D[i][j];
	}
	
	/**
	 * Accesseur de la longueur du donjon
	 * @return Renvoie un entier indiquant la longueur du donjon
	 */
	public final int get_Longueur()
	{
		return this.nbL;
	}
	
	/**
	 * Accesseur de la largeur du donjon
	 * @return Renvoie un entier indiquant la largeur du donjon
	 */
	public final int get_Largeur()
	{
		return this.nbC;
	}
	
	/**
	 * Mutateur d'une pi�ce de du donjon
	 * @param i Ligne de la Pi�ce � ajouter
	 * @param j Colonne de la Pi�ce � ajouter
	 * @param pie Nouvelle pi�ce � ajouter
	 * @see Piece
	 */
	protected final void set_UneCase(int i, int j, Piece pie)
	{
		this.tab2D[i][j] = pie;
	}
	
	/**
	 * Mutateur de la longueur du donjon
	 * @param lg Longueur du donjon
	 */
	public final void set_Longeur(int lg)
	{
		/* Si la valeur est rest�e � z�ro, on peut changer le nbre de lignes */
		if( this.nbL == 0 )
		{
			this.nbL = lg;
		}
	}
	
	/**
	 * Mutateur de la largeur du donjon
	 * @param la Largeur du donjon
	 */
	public final void set_Largeur(int la)
	{
		/* Si la valeur est rest�e � z�ro, on peut changer le nbre de colonnes */
		if( this.nbC == 0 )
		{
			this.nbC = la;
		}
	}
	
	/**
	 * Mutateur du pourcentage de monstres dans le jeu (ne peut �tre modifi� 
	 * qu'une seul fois, par la suite sans valeur)
	 * @param val Pourcenatges de monstres dans le donjon
	 */
	public final void set_PourcentageMonstres(float val)
	{
		if( this.nbMonstres == -1 )
		{
			this.nbMonstres = (int)( val * (this.get_Longueur() * this.get_Largeur()) );
		}
	}
	
	
	/**
	 * Mutateur de la pi�ce d'arriv�e du personnage
	 * @param pie Pi�ce o� arrivera le personnage dans le labyrinthe
	 * @see Piece
	 * @see Heros
	 */
	protected final void set_PieceArrive(Piece pie)
	{
		this.entree = pie;
		pie.set_EstLarrivee();
	}
	
	/**
	 * Mutateur de la pi�ce de sortie (en faite l'entr�e du donjon) du donjon
	 * @param pie Pi�ce de sortie (en faite l'entr�e du donjon) du donjon
	 * @see Piece
	 */
	protected final void set_PieceSortie(Piece pie)
	{
		this.sortie = pie;
		pie.set_EstLaSortie();
	}
    
}
