package Pacman;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BonusEntity extends MoveableEntity{
	
	private int Score;
	private String type;
	
	public BonusEntity(String type, int level, int x, int y) {
		super (x, y);
		String filename = "bonus_";
		if(type=="s") {
			filename += "simple.png";
		}else if(type=="S"){
			filename += "super.png";
		}
		else if(type=="") {
			filename = "case_walkable.png";
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
        canvas.draw(this.imageSprite, this.posHor, this.posVer);
	}

}
