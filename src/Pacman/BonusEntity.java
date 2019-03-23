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
public class BonusEntity extends MoveableEntity{
	
	/**
	 * The bonus' score
	 */
	protected int score;
	
	/**
	 * 
	 * @param type The type of the bonus. Used for image loading
	 * @param level Index of the level
	 * @param x The horizontal position
	 * @param y The vertical position
	 */
	public BonusEntity(String type, int level, int x, int y) {
		super(x, y);
		String filename = "bonus_";
		if(type=="s") {
			filename += "simple.png";
			this.score = 10;
		}else if(type=="S"){
			filename += "super.png";
			this.score = 100;
		}
		else {
			throw new IllegalArgumentException("Unrecognized type: " + type);
		}
		
		try {
			this.imageSprite = ImageIO.read(new File(filename));
		} catch (IOException e) {
			System.out.println("Error while loading image: " + filename);
			e.printStackTrace();
		}		
	}
	
	/**
	 * 
	 * @param bonus Bonus type 
	 * @param level Index of the leve
	 * @param x Location x of the bonus
	 * @param y Location y of the bonusl
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

}
