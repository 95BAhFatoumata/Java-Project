package GestionServer;

/**
 * @author DIALLO
 *
 * <h4>Classe representant une exception de coordonnees.</h4>
 * <ul>Si on se deplace en dehors du labyrinthe, une exception sera levee.</ul>
 * <ul>Indiquant que les coordonnees de deplacements sont impossibles.</ul>
 *	
 * 
 */
public class InvalidCoordException extends Exception
{
	
	
	/**
	 * Constructeur grace a un message d'information 
	 * @param message Message a afficher lors de la levee de l'exception
	 */
	public InvalidCoordException(String message)
	{
		super(message);
	}
}