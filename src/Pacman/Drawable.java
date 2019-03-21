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
	 * 
	 */
	protected BufferedImage imageSprite;
	
	/**
	 * 
	 */
	protected int posHor;
	
	/**
	 * 
	 */
	protected int posVer;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Drawable(int x, int y) {
		this.posHor = x;
		this.posVer = y;
		
	}
	
	/**
	 * 
	 */
	public abstract void draw();
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void moveTo(int x, int y) {
		this.posHor = x;
		this.posVer = y;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getX() {
		return this.posHor;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getY() {
		return this.posVer;
	}

}
