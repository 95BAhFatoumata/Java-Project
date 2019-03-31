package GestionServer;

/**
 * @author noff noff
 *
 * <li>Classe statique permettant d'effectuer un travail sur les chaines</li>
 * <lil>Elle permet par exemple de compl�ter une chaine par des d'autres caract�res, ....</li>  
 * 
 */
public class TravailleChaine
{
	/**
	 * M�thode permettant de r�p�ter plusieurs fois un m�me caract�re
	 * @param car Caract�re (ou cha�ne)  � r�p�ter nbFois
	 * @param nbFois Nombre de r�p�titions du caract�re
	 * @return Chaine avec nbFois le caract�re pass� en param�tre
	 */
	public static String repeter_Caractere(String car, int nbFois)
	{
		StringBuffer bf = new StringBuffer();
		
		for(int i = 0; i < nbFois; i ++)
		{
			bf.append(car);
		}
		
		return bf.toString();
	}
	
	/**
	 * M�thode permettant de compl�ter une chaine suivant une taille donn�e
	 * @param larg Taille totale de la cha�ne
	 * @param rajout Si la taille de 'ch' est inf�rieur � 'larg', on rajoute ce caract�re
	 * @param ch Chaine o� l'on va rajouter apr�s 'rajout'  
	 * @return Retourne une cha�ne qui sera compl�t�e par 'rajout'
	 */
	public static String rajouter_Blanc(int larg, String ch, String rajout )
	{
		StringBuffer buf = new StringBuffer();		
		
		int lg = ch.length();
		int md = (int)( (larg - lg) / 2 ) ;
		
		buf.append( "|" );
		/* Si la taille de 'ch' est inf�rieure � larg, on rajoute des 'rajout' */
		if( lg < larg )
		{
			buf.append( TravailleChaine.repeter_Caractere(rajout, md) );
			buf.append( ch );
			int total = md + lg;
			/* Il faut que la chaine soit de la taille pr�cis�e en param�tre */
			md = (larg - total);
			buf.append( TravailleChaine.repeter_Caractere(rajout, md) );
		}
		else if( lg > larg )
		{
			int lgCh = lg;
			buf = buf.delete( (lgCh - larg - 2), larg);
		}
		
		return buf.toString();
	}
	
	/**
	 * M�thode renvoyant le d�but de la repr�sentation de la pi�ce
	 * @param nbC Nombre de colonnes � repr�senter
	 * @param taille Taille de la Piece (pour le dessin)
	 * @return Chaine format�e pour la repr�sentation de la pi�ce 
	 */
	public static String avant_ElementPiece(int nbC, int taille)
	{
		StringBuffer bf = new StringBuffer();
		
		/* 1) On affiche avant chaque indication du contenu , pour chaque ligne du tableau */
		for(int i = 0; i < nbC; i ++)
		{
			bf.append("o");
			bf.append( TravailleChaine.repeter_Caractere("-", taille) );
		}
		bf.append("o\n");
		
		int mid = (int)(taille / 2);
		for(int i = 0; i < mid - 1; i ++)
		{
			for(int j = 0; j < nbC; j ++)
			{
				bf.append( TravailleChaine.rajouter_Blanc(taille, " ", " ") );
			}
			bf.append("|\n");
		}
		
		return bf.toString();
	}
	
	/**
	 * M�thode renvoyant la fin de la repr�sentation de la pi�ce
	 * @param nbC Nombre de colonnes � repr�senter
	 * @param taille Taille de la Piece (pour le dessin)
	 * @return Chaine format�e pour la repr�sentation de la pi�ce 
	 */
	public static String apres_ElementPiece(int nbC, int taille)
	{
		StringBuffer bf = new StringBuffer();
		
		int mid = (int)(taille / 2);
		for(int i = 0; i < mid - 1; i ++)
		{
			for(int j = 0; j < nbC; j ++)
			{
				bf.append( TravailleChaine.rajouter_Blanc(taille, " ", " ") );
			}
			bf.append("|\n");
		}
		
		/* 1) On affiche avant chaque indication du contenu , pour chaque ligne du tableau */
		for(int i = 0; i < nbC; i ++)
		{
			bf.append("o");
			bf.append( TravailleChaine.repeter_Caractere("_", taille) );
		}
		bf.append("o\n");
		
		return bf.toString();
	}
}