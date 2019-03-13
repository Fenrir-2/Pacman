package Pacman;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Short class description
 * 
 * @author Nicolas FONNIER, Henri GLEVEAU
 *
 */
public class Fantome extends MoveableEntity{
	
	/**
	 * 
	 */
	protected boolean spooked;
	
	/**
	 * 
	 */
	protected boolean dead;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Fantome(int x, int y) {
		super(x, y);
		try {
			this.imageSprite = ImageIO.read(new File("Fantome.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.spooked = false;
		this.dead = false;
	}
	
	/**
	 * 
	 * @param state
	 */
	public void setSpooked(boolean state) {
		this.spooked = state;
	}
	
	/**
	 * 
	 * @param state
	 */
	public void setDead(boolean state){
		this.dead = state;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getSpooked() {
		return this.spooked;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getDead() {
		return this.dead;
	}
	
	/**
	 * 
	 */
	public void draw() {
		Canvas canvas = Canvas.getCanvas();
        canvas.draw(this.imageSprite, this.posHor, this.posVer);
	}
}
