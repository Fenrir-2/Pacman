package Pacman;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Case extends Drawable{
	
	protected boolean walkable;
	protected BonusEntity bonus;
	protected Pac_Man pacman;
	protected Fantome fantome;
	
	public Case(boolean walkable, BonusEntity bonus, Pac_Man pacman, Fantome fantome, int x, int y) {
		super(x, y);
		this.walkable = walkable;
		this.bonus = bonus;
		this.pacman = pacman;
		this.fantome = fantome;
		String filename = "case_";
		if(walkable) {
			filename += "walkable.png";
		}else {
			filename += "wall.png";
		}
		
		try {
			this.imageSprite = ImageIO.read(new File(filename));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
	public boolean isWalkable() {
		return this.walkable;
	}
	
	public BonusEntity getBonus() {
		return this.bonus;
	}
	
	public void setBonus(BonusEntity bonus) {
		this.bonus = bonus;
	}
	
	public Pac_Man getPacMan() {
		return this.pacman;
	}
	
	public void setPacMan(Pac_Man pacman) {
		this.pacman = pacman;
	}
	
	public Fantome getFantome() {
		return this.fantome;
	}
	
	public void setFantome(Fantome fantome) {
		this.fantome = fantome;
	}
	
	public void draw() {
		
		if(this.pacman != null){
			this.pacman.draw();
		}
		
		if(this.fantome != null) {
			this.fantome.draw();
		}
		
		if(this.bonus != null) {
			this.bonus.draw();
		}
		
		Canvas canvas = Canvas.getCanvas();
        canvas.draw(this.imageSprite, this.posHor, this.posVer);
		
		
	}
}
