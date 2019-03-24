package Pacman;

/**
 * The MoveableEntity class is a class which represents 
 * all the movable entities on the board game, such as Pacman or ghosts
 * It manages the movement of all the entities
 * 
 * @author Nicolas FONNIER, Henri GLEVEAU
 *
 */
public abstract class MoveableEntity extends Entity{
	
	/**
	 * Main constructor for the MoveableEntity objects.
	 * 
	 * @param x the x location of the entity
	 * @param y the y location of the entity
	 */
	public MoveableEntity(int x, int y) {
		super(x, y);
	}
	
	/**
	 * This Method manages the Horizontal moves of PacMan using a ternary condition
	 * @param gauche boolean for the move conditions
	 */
	public void deplacementHorizontal(Boolean gauche) {
		//The Horizontal position gets -1 if gauche is true, else it gets +1 
		this.posHor += gauche?-1:1;
	}
	
	/**
	 * This Method manages the Vertical moves of PacMan using a ternary condition
	 * 
	 * @param haut boolean for the move conditions
	 */
	public void deplacementVertical(Boolean haut) {
		//The vertical position gets -1 if haut is true, else it gets +1 
		this.posVer += haut?-1:1;
	}
	
	


}
