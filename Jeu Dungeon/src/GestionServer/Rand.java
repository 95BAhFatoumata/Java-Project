package GestionServer;

import java.util.Random;
/** Classe permettant de cr�er des nombres al�atoires
 * @author DIALLO
 *
 * TODO rien de sp�cial pour l'instant
 */
public class Rand 
{

	static private Random R = new Random();
	
	/** Fonction g�n�rant un entier al�atoire entre   
	 * min et max
	 * @param min La borne inf�rieure du nombre al�atoire 
	 * @param max La borne sup�rieure du nombre al�atoire
	 * @return un nombre al�atoire entre min et max
	 */
	static public int EntierEntre( int min, int max )
	{
		int val = R.nextInt(max-min+1) + min;
		
		return val;
	}
}
