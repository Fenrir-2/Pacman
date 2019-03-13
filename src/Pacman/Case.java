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
	
	protected boolean walkable;
	protected BonusEntity bonus;
	protected Pac_Man pacman;
	protected Fantome fantome;
	
	/**
	 * To Do
	 * 
	 * @param walkable
	 * @param bonus
	 * @param pacman
	 * @param fantome
	 * @param x
	 * @param y
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
			// TODO Auto-generated catch block
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
	 * 
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
	 * 
	 */
	public void draw() {
		Canvas canvas = Canvas.getCanvas();
        canvas.draw(this.imageSprite, this.posHor, this.posVer);		
	}
}
