package Pacman;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Short class desciption
 * 
 * @author Nicolas FONNIER, Henri GLEVEAU
 *
 */
public class Case extends Drawable{
	
	/**
	 * Attribute to know if a case object is walkable or not
	 */
	protected boolean walkable;
	
	/**
	 * The bonus located on the case object
	 */
	protected BonusEntity bonus;
	
	/**
	 * The pacman located on the case object
	 */
	protected Pac_Man pacman;
	
	/**
	 * the ghost located on the case object
	 */
	protected Fantome fantome;
	
	/**
	 * 
	 * 
	 * @param walkable To put a Case walkable or not
	 * @param bonus To put a bonus on a Case
	 * @param pacman To put a pacman on a Case
	 * @param fantome To put a ghost on a Case
	 * @param x Location x of the Case
	 * @param y Location y of the case
	 */
	public Case(boolean walkable, BonusEntity bonus, Pac_Man pacman, Fantome fantome, int x, int y) {
		super(x, y);
		
		//Init of the attributes according to what has been given.
		this.walkable = walkable;
		this.bonus = bonus;
		this.pacman = pacman;
		this.fantome = fantome;
		
		//Setting the filename according to the fact it's a walkable square or not
		String filename = walkable?"case_walkable.png":"case_wall.png";
		
		try {
			this.imageSprite = ImageIO.read(new File(filename));
		} catch (IOException e) {
			System.out.println("Error while loading image: " + filename);
			e.printStackTrace();
		}		
	}
	
	/**
	 * Returns the walkable boolean
	 * 
	 * @return true if the square has been set as walkable, false else
	 */
	public boolean isWalkable() {
		return this.walkable;
	}
	
	/**
	 * Return the bonusEntity on the square
	 * 
	 * @return The bonusEntity currently on the square.
	 */
	public BonusEntity getBonus() {
		return this.bonus;
	}
	
	/**
	 * Changes the bonus on the square.
	 * 
	 * @param bonus
	 */
	public void setBonus(BonusEntity bonus) {
		this.bonus = bonus;
	}
	
	/**
	 * Return the Pacman on the square, which should be the only Pacman in the game
	 * TODO: ADD INVARIANT CHECK?
	 * @return The Pacman on the square
	 */
	public Pac_Man getPacMan() {
		return this.pacman;
	}
	
	/**
	 * Changes the Pacman of the square
	 * 
	 * @param pacman The new Pacman. Beware only one Pacman is allowed in the game!
	 */
	public void setPacMan(Pac_Man pacman) {
		this.pacman = pacman;
	}
	
	/**
	 * Returns the ghost on the square
	 * 
	 * @return the ghost on the square
	 */
	public Fantome getFantome() {
		return this.fantome;
	}
	
	/**
	 * Changes the ghost on the square
	 * 
	 * @param fantome The new ghost
	 */
	public void setFantome(Fantome fantome) {
		this.fantome = fantome;
	}
	
	/**
	 * Draws the Case on the canvas
	 */
	public void draw() {
		Canvas canvas = Canvas.getCanvas();
        canvas.draw(this.imageSprite, this.posHor, this.posVer);		
	}
}
