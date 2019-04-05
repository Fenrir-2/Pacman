package Pacman;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class represents all the different bonus entities, according to the {@link Pacman.Bonus}.
 * Every bonus possesses a type and a score
 * 
 * @author Nicolas FONNIER, Henri GLEVEAU
 *
 */
public class BonusEntity extends MoveableEntity{
	
	/**
	 * The bonus' score
	 */
	protected int score;
	
	/**
	 * The link to the BonusEntity
	 */
	protected Bonus bonus;
	
	/**
	 * Main constructor for BonusEntity objects.
	 * 
	 * @param bonus Bonus type 
	 * @param level Index of the level
	 * @param x Location x of the bonus
	 * @param y Location y of the bonus
	 */
	public BonusEntity(Bonus bonus, int level, int x, int y) {
		super(x, y);
		
		//Checking if the bonus is valid
		if(!bonus.checkLevel(level))
			throw new IllegalArgumentException("BonusEntity Level out of bounds");
		
		//Retrieving the score
		this.score = bonus.getScore();
		
		//Image loading
		String filename = "bonus_" + bonus.getType() + ".png";
		try {
			this.imageSprite = ImageIO.read(new File(filename));
		} catch (IOException e) {
			System.out.println("Error while loading image: " + filename);
			e.printStackTrace();
		}
		
		this.bonus = bonus;
	}
	
	/**
	 * Getter for the bonus' score
	 * @return the score of the bonus
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Draw a bonus object on the Canvas
	 */
	public void draw() {
		Canvas canvas = Canvas.getCanvas();
		if(this.imageSprite != null) {
			canvas.draw(this.imageSprite, this.posHor, this.posVer);
		}

	}
	
	/**
	 * Returns the {@link Pacman.Bonus} object that was used to create this {@link Pacman.BonusEntity}
	 * @return the {@link Pacman.Bonus} object that was used to create this  {@link Pacman.BonusEntity}
	 */
	public Bonus getBonusType() {
		return this.bonus;
	}

}
