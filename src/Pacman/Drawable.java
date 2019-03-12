package Pacman;

import java.awt.image.BufferedImage;

public abstract class Drawable {
	
	protected BufferedImage imageSprite;
	protected int posHor;
	protected int posVer;
	
	public Drawable(int x, int y) {
		this.posHor = x;
		this.posVer = y;
		
	}
	
	public abstract void draw();

}
