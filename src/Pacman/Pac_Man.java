package Pacman;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Short class description
 * 
 * @author Nicolas FONNIER, Henri GLEVEAU
 *
 */
public class Pac_Man extends MoveableEntity{
	
	/**
	 * 
	 */
	protected int nbVies = 3;
	
	/**
	 * 
	 */
	protected boolean SuperMode = false;
	
	/**
	 * 
	 */
	protected int Score = 0;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public Pac_Man(int x, int y) {
		super(x, y);
		try {
			this.imageSprite = ImageIO.read(new File("Pacman.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public void perdVie() {
		this.nbVies = this.nbVies-1;
	}
	
	/**
	 * 
	 * @param state
	 * @return
	 */
	public boolean setSuperMode(boolean state) {
		this.SuperMode = state;
		return this.SuperMode;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getPacManLives() {
		return this.nbVies;
	}
	
	/**
	 * 
	 * @param score
	 */
	public void addscore(int score) {
		this.Score = this.Score+score;
	}
	
	/**
	 * 
	 */
	public void draw() {
		Canvas canvas = Canvas.getCanvas();
        canvas.draw(this.imageSprite, this.posHor, this.posVer);
	}

}
