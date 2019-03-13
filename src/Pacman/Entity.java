package Pacman;

/**
 * Short class desciption
 * 
 * @author Nicolas FONNIER, Henri GLEVEAU
 *
 */
public class Entity extends Drawable{
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Entity(int x, int y) {
		super(x, y);
		
	}
	
	/**
	 * 
	 * @param e
	 * @return
	 */
	public boolean checkCollision(Entity e) {
		return false;
	}
	
	/**
	 * 
	 */
	@Override
	public void draw() {
		
		
	}	

}
