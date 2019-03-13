package Pacman;

public class MoveableEntity extends Entity{
	
	/**
	 * Constructor for the MoveableEntity 
	 * 
	 * @param x the x location of the entity
	 * @param y the y location of the entity
	 */
	public MoveableEntity(int x, int y) {
		super(x, y);
	}
	
	/**
	 * A FAIRE
	 * @param gauche
	 */
	public void deplacementHorizontal(Boolean gauche) {
		this.posHor += gauche?-1:1;
	}
	
	/**
	 * A FAIRE
	 * 
	 * @param haut
	 */
	public void deplacementVertical(Boolean haut) {
		this.posVer += haut?-1:1;
	}
	
	


}
