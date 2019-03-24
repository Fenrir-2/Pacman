package Pacman;

/**
 * The Entity class represents all the differents entities on the game, 
 * such as PacMan, bonuses, Ghosts
 * It can also manage the collision between two entities.
 * 
 * @author Nicolas FONNIER, Henri GLEVEAU
 *
 */
public abstract class Entity extends Drawable{
	
	/**
	 *  Main constructor for Entity objects.
	 * 
	 * @param x The horizontal position
	 * @param y The vertical position
	 */
	public Entity(int x, int y) {
		super(x, y);
		
	}
	
	/**
	 * Vérifie si l'entité actuelle est en collision avec l'entitée
	 * 
	 * @param e L'entité à  vérifier
	 * 
	 * @return Vrai si les coordonnées des 2 entités sont identiques
	 */
	public boolean checkCollision(Entity e) {
		assert(e != null) : "e n'est pas dÃ©fini, ou est null";
		if(e.posHor == this.posHor && e.posVer == this.posVer)
			return true;

		return false;
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract void draw();

}
