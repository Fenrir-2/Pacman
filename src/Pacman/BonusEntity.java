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
	protected int Score;
	
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
		super (x, y);
		String filename = "bonus_";
		if(type=="s") {
			filename += "simple.png";
		}else if(type=="S"){
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
