package Pacman;

/**
 * Short class desciption
 * 
 * @author Nicolas FONNIER, Henri GLEVEAU
 *
 */
public abstract class Entity extends Drawable{
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Entity(int x, int y) {
		super(x, y);
		
	}
	
	/**
	 * V�rifie si l'entit� actuelle est en collision avec l'entit� e
	 * 
	 * @param e L'entit� � v�rifier
	 * 
	 * @return Vrai si les coordonn�es des 2 entit�s sont identiques
	 */
	public boolean checkCollision(Entity e) {
		if(e.posHor == this.posHor && e.posVer == this.posVer)
			return true;

		return false;
	}
	
	/**
	 * 
	 */
	@Override
	public abstract void draw();

}
