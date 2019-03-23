package Pacman;

import java.awt.image.BufferedImage;

/**
 * Short class description
 * 
 * @author Nicolas FONNIER, Henri GLEVEAU
 *
 */
public abstract class Drawable {
	
	/**
	 * Image File of the Drawable object
	 */
	protected BufferedImage imageSprite;
	
	/**
	 * Horizontal Position of the drawable object
	 */
	protected int posHor;
	
	/**
	 *  Vertical Position of the drawable object
	 */
	protected int posVer;
	
	/**
	 * 
	 * @param x The horizontal position
	 * @param y The vertical position
	 */
	public Drawable(int x, int y) {
		this.posHor = x;
		this.posVer = y;
		assert(x>0) : "x est négatif";
		assert(y>0) : "y est négatif";
	}
	
	/**
	 * Draws the object.
	 */
	public abstract void draw();
	
	/**
	 * 
	 * @param x The horizontal position
	 * @param y The vertical position
	 */
	public void moveTo(int x, int y) {
		this.posHor = x;
		this.posVer = y;
	}
	
	/**
	 * Getter for the horizontal position
	 * @return the horizontal position
	 */
	public int getX() {
		return this.posHor;
	}
	
	/**
	 * Getter for the Vertical position
	 * @return the vertical position
	 */
	public int getY() {
		return this.posVer;
	}

}
