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
	 * Attribute to know if ghosts are spooked or not
	 */
	protected boolean spooked;
	
	/**
	 *  Attribute to know if ghosts are dead or not
	 */
	protected boolean dead;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Fantome(int x, int y) {
		super(x, y);
		//Loads the ghosts image file
		try {
			this.imageSprite = ImageIO.read(new File("Fantome.png"));
		} catch (IOException e) {
			System.out.println("Error while loading image: Fantome.png");
			e.printStackTrace();
		}
		this.spooked = false;
		this.dead = false;
	}
	
	/**
	 * This method manages the Spooked state of ghosts, and sets this state according to the entry parameter
	 * @param state
	 */
	public void setSpooked(boolean state) {
		this.spooked = state;
		assert(this.spooked == state) : "L'état spooked des fantomes n'est pas respecté";
	}
	
	/**
	 * This method manages the Dead state of ghosts, and sets this state according to the entry parameter
	 * @param state
	 */
	public void setDead(boolean state){
		this.dead = state;
		assert(this.dead == state) : "L'état dead des fantomes n'est pas respecté";
	}
	
	/**
	 * Getter to Spooked state
	 * @return the current state of spooked
	 */
	public boolean getSpooked() {
		return this.spooked;
	}
	
	/**
	 * Getter to Dead state
	 * @return the current state of dead
	 */
	public boolean getDead() {
		return this.dead;
	}
	
	/**
	 * Draws a Ghost entity on the canvas
	 */
	public void draw() {
		Canvas canvas = Canvas.getCanvas();
		if(this.imageSprite != null)
			canvas.draw(this.imageSprite, this.posHor, this.posVer);
	}
}
