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
	 * Vérifie si l'entité actuelle est en collision avec l'entité e
	 * 
	 * @param e L'entité à vérifier
	 * 
	 * @return Vrai si les coordonnées des 2 entités sont identiques
	 */
	public boolean checkCollision(Entity e) {
		assert(e != null) : "e n'est pas défini, ou est null";
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
