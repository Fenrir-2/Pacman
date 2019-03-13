package Pacman;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Fantome extends MoveableEntity{
	
	private boolean spooked;
	private boolean dead;
	
	public Fantome(int x, int y) {
		super(x, y);
		try {
			this.imageSprite = ImageIO.read(new File("Fantome.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.spooked = false;
		this.dead = false;
	}
	
	public void setSpooked(boolean state) {
		this.spooked = state;
	}
	
	public void setDead(boolean state){
		this.dead = state;
	}
	
	public boolean getSpooked() {
		return this.spooked;
	}
	
	public boolean getDead() {
		return this.dead;
	}
	
	public void draw() {
		Canvas canvas = Canvas.getCanvas();
        canvas.draw(this.imageSprite, this.posHor, this.posVer);
	}
}
