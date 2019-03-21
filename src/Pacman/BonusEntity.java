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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * 
	 * @param bonus
	 * @param x
	 * @param y
	 * @param level
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	/**
	 * 
	 * @return
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * 
	 */
	public void draw() {
		Canvas canvas = Canvas.getCanvas();
		if(this.imageSprite != null) {
			canvas.draw(this.imageSprite, this.posHor, this.posVer);
		}

	}

}
