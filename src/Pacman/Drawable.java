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

}
