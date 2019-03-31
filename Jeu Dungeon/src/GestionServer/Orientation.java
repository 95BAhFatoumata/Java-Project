package GestionServer;


/**
 * @author DIALLO
 *
 * <h4>Classe permettant de gerer les deplacements</h4>
 * Cette classe permet d'orienter le personnage suivant des coordonnees x et y
 * <br>
 * Elle leve une exception de mauvaises coordonnees si on depasse les bornes limites.
 * 
 * @see InvalidCoordException
 */

public class Orientation implements Cloneable, java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int i, j;

	private static int MAX_I = -1;
	private static int MAX_J = -1;
	
	/**
	 * Valeur minimale de l'ordonnee
	 */
	public final static int MIN_ORD = 0;
	/**
	 * Valeur minimale de l'abscisse
	 */
	public final static int MIN_ABS = 0;
	
	
	/**
	 * Constructeur par defaut de la classe
	 * @param i Coordonnee initiale ordonnee 
	 * @param j Coordonnee intiale abscisse
	 */
	public Orientation(int i, int j)
	{
		this.i = i;
		this.j = j;
	}
	
	/**
	 * Red�finition de la classe clone
	 * @return Renvoie un object � caster en Tresor
	 * @see Object
	 */
	public Object clone()
	{
		Orientation or = null;
		
		try
		{
			or = (Orientation)super.clone();
		}
		catch(Exception ex)
		{/**/}
		
		return or;
	}
	
	/**
	 * Permet de tester l'�galit� entre les 2 instances
	 * @param o Objet � comparer avec l'intance en cours
	 * @return Bool�en indiquant si les deux instances sont �gales
	 */
	public boolean equals(Object o)
	{
		boolean bon = false;
		
		if( o instanceof Orientation)
		{
			Orientation or = (Orientation)o;
			bon = ( (or.i == this.i) && (or.j == this.j) );
		}
		
		return bon;
	}
	
	/**
	 * Permet de d�placer les coordonn�es vers la gauche (abscisse - 1)
	 * @throws InvalidCoordException une exception indiquant qu'on est au-dehors des valeurs limites.
	 * @see InvalidCoordException
	 */
	public void a_Gauche() throws InvalidCoordException
	{
		/* On enl�ve 1 � l'abscisse */
		int j = this.get_coordJ() - 1;
		this.set_J(j);
	}
	
	/**
	 * Permet de d�placer les coordonn�es vers la droite (abscisse + 1)
	 * @throws InvalidCoordException une exception indiquant qu'on est au-dehors des valeurs limites.
	 * @see InvalidCoordException
	 */
	public void a_Droite() throws InvalidCoordException
	{
		/* On ajoute 1 � l'abscisse */
		int j = this.get_coordJ() + 1;
		this.set_J(j);
	}
	
	/**
	 * Permet de d�placer les coordonn�es vers le bas (ordonn�e + 1)
	 * @throws InvalidCoordException une exception indiquant qu'on est au-dehors des valeurs limites.
	 * @see InvalidCoordException
	 */
	public void en_Bas() throws InvalidCoordException
	{
		/* On ajoute 1 � l'ordonn�e */
		int i = this.get_coordI() + 1;
		this.set_I(i);
	}
	
	/**
	 * Permet de d�placer les coorodnn�es vers le haut (ordonn�e - 1)
	 * @throws InvalidCoordException une exception indiquant qu'on est au-dehors des valeurs limites.
	 * @see InvalidCoordException
	 */
	public void en_Haut() throws InvalidCoordException
	{
		/* On enl�ve 1 � l'ordonn�e */
		int i = this.get_coordI() - 1;
		this.set_I(i);
	}
	
	/*----------------------------------------------------------*/
	/*----------------  ACCESSEURS & MUTATEURS -----------------*/
	/*----------------------------------------------------------*/
		
	/**
	 * Accesseur de l'ordonn�e
	 * @return Valeur (enti�re) de l'ordonn�e
	 */
	public int get_coordI()
	{
		return this.i;
	}
	
	/**
	 * Accesseur de l'abscisse
	 * @return Valeur (enti�re) de l'abscisse
	 */
	public int get_coordJ()
	{
		return this.j;
	}
	
	/**
	 * Accesseur statique de la borne maximale de l'ordonn�e
	 * @return Renvoie un entier repr�sentant la valuer maximale des ordonn�es
	 */
	public static int MAX_ORD()
	{
		return (int)Orientation.MAX_I;
	}
	
	/**
	 * Accesseur statique de la borne maximale de l'abscisse
	 * @return Renvoie un entier repr�sentant la valuer maximale des abscisses
	 */
	public static int MAX_ABS()
	{
		return (int)Orientation.MAX_J;
	}
	
	/**
	 * Mutateur de l'ordonn�e
	 * @param i Nouvelle valeur de l'ordonn�e
	 * @throws InvalidCoordException Exception repr�sentant une coordonn�e au-dehors des bornes
	 */
	public void set_I(int i) throws InvalidCoordException
	{
		if( (i >= Orientation.MIN_ORD) && (i <= Orientation.MAX_ORD()) )
		{
			this.i = i;
		}
		else if( i < Orientation.MIN_ORD)
		{
			throw new InvalidCoordException("En dehors de la zone (trop en haut)");
		}
		else
		{
			throw new InvalidCoordException("En dehors de la zone (trop en bas)");
		}
	}
	
	/**
	 * Mutateur de l'abscisse
	 * @param j Nouvelle valeur de l'abscisse
	 * @throws InvalidCoordException Exception repr�sentant une coordonn�e au-dehors des bornes
	 */
	public void set_J(int j) throws InvalidCoordException
	{
		if( (j >= Orientation.MIN_ABS) && (j <= Orientation.MAX_ABS()) )
		{
			this.j = j;
		}
		else if( j < Orientation.MIN_ABS)
		{
			throw new InvalidCoordException("En dehors de la zone (trop � gauche)");
		}
		else
		{
			throw new InvalidCoordException("En dehors de la zone (trop � droite)");
		}
	}
	
	/**
	 * <h4>Premi�re �tape � effectuer pour definir les bornes � ne pas d�passer</h4>
	 * <h5>Si ce n'est pas d�fini, les bornes sont � -1</h5>
	 * @param maxI Ordonn�e maximale qu'on peut atteindre 
	 * @serialField public final static void set_MAX_ABS(int maxJ)
	 */
	public final static void set_MAX_ORD(int maxI)
	{
		if( Orientation.MAX_I == -1 )
		{
			Orientation.MAX_I = maxI;
		}
	}
	
	/**
	 * <h4>Premi�re �tape � effectuer pour definir les bornes � ne pas d�passer</h4>
	 * <h5>Si ce n'est pas d�fini, les bornes sont � -1</h5>
	 * @param maxJ Abscisse maximale qu'on peut atteindre 
	 * @serialField public final static void set_MAX_ABS(int maxJ)
	 */
	public final static void set_MAX_ABS(int maxJ)
	{
		if( Orientation.MAX_J == -1 )
		{
			Orientation.MAX_J = maxJ;
		}
	}
	
	/**
	 * <li>Cette m�thode est utilis�e pour remettre � z�ro</li>
	 * <li>Des valeurs maximales des Orientations</li>
	 * <li>Doit �tre utilis�e lors de la destruction du donjon</li>
	 * @see Donjon
	 * @see plateau.DonjonAmeliore
	 */
	public static void raz_ValsMaxs()
	{
		Orientation.MAX_I = -1;
		Orientation.MAX_J = -1;
	}
	
	/**
	 * Re-d�finition de la m�thode toString
	 * @return Retourne une cha�ne repr�sentant les coordonn�es abscisse et ordonn�e
	 */
	public String toString()
	{
		StringBuffer buf = new StringBuffer("\nPosition : ").append("\nAbscisse : ")
														 .append(this.get_coordJ())
															 .append("\nOrdonn�e : ")
															 .append(this.get_coordI() );
		return buf.toString();
	}
    
	
	

}
