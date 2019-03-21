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
	
	public BonusEntity(Bonus bonus, int x, int y) {
		super(x, y);
		String filename = "bonus_";
		if(bonus==Bonus.GOMME) {
			filename += "simple.png";
		}else if(bonus==Bonus.SUPER_GOMME){
			filename += "super.png";
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
	
	public void draw() {
		Canvas canvas = Canvas.getCanvas();
		if(this.imageSprite != null) {
			canvas.draw(this.imageSprite, this.posHor, this.posVer);
		}

	}

}
