package Pacman;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Fantome class represents the entity "Fantome" on the board
 * It's a movable entity with the possibility to evolve in a spooked mode 
 * to be eaten by Pacman for a short amount of time. It then turns to the dead state
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
	 *  Main constructor for Fantome objects.
	 * 
	 * @param x The horizontal position
	 * @param y The vertical position
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
	 * When the state is set to true, the ghost sprite changes to "Fantome_spooked.png"
	 * Otherwise, it falls back to the default "Fantome.png"
	 * 
	 * @param state the new spooked state
	 */
	public void setSpooked(boolean state) {
		this.spooked = state;
		assert(this.spooked == state) : "L'état spooked des fantomes n'est pas respecté";
		
		if(this.dead == true) {
			try {
				this.imageSprite = ImageIO.read(new File("Fantome_spooked.png"));
			} catch (IOException e) {
				System.out.println("Error while loading image: Fantome_spooked.png");
				e.printStackTrace();
			}
		}else {
			try {
				this.imageSprite = ImageIO.read(new File("Fantome.png"));
			} catch (IOException e) {
				System.out.println("Error while loading image: Fantome.png");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method manages the Dead state of ghosts, and sets this state according to the entry parameter
	 * When the state is set to true, the ghost sprite changes to "Fantome_dead.png"
	 * Otherwise, it falls back to the default "Fantome.png"
	 * 
	 * @param state the new dead state
	 */
	public void setDead(boolean state){
		this.dead = state;
		assert(this.dead == state) : "L'état dead des fantomes n'est pas respecté";
		
		if(this.dead == true) {
			try {
				this.imageSprite = ImageIO.read(new File("Fantome_dead.png"));
			} catch (IOException e) {
				System.out.println("Error while loading image: Fantome_dead.png");
				e.printStackTrace();
			}
		}else {
			try {
				this.imageSprite = ImageIO.read(new File("Fantome.png"));
			} catch (IOException e) {
				System.out.println("Error while loading image: Fantome.png");
				e.printStackTrace();
			}
		}
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
