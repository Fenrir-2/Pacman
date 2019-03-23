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
	 * 
	 */
	protected int score;
	
	/**
	 * 
	 */
	protected String type;
	
	/**
	 * 
	 * @param type
	 * @param level
	 * @param x
	 * @param y
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
	 * @param x Location x of the bonus
	 * @param y Location y of the bonus
	 * @param level Index of the level
	 */
	public BonusEntity(Bonus bonus, int level, int x, int y) {
		super(x, y);
		
		String filename = "bonus_" + bonus.getType() + ".png";
		if(!bonus.checkLevel(level))
			throw new IllegalArgumentException("BonusEntity Level out of bounds");
		
		this.score = bonus.getScore();
		
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
