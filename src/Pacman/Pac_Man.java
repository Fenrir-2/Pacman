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
	protected int score = 0;
	
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
			System.out.println("Error while loading image: Pacman.png");
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 */
	public void perdVie() {
		this.nbVies = this.nbVies-1;
		assert(this.nbVies > 0) : "Number of lives below zero";
	}
	
	/**
	 * 
	 * @param state
	 * @return
	 */
	//C'est quoi ce return de mes couilles? Wllh j'étais dead
	public boolean setSuperMode(boolean state) {
		this.SuperMode = state;
		return this.SuperMode;
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean getSuperMode() {
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
	public void addScore(int score) {
		this.score = this.score+score;
	}
	
	/**
	 * 
	 */
	public void draw() {
		Canvas canvas = Canvas.getCanvas();
        canvas.draw(this.imageSprite, this.posHor, this.posVer);
	}

	public int getScore() {
		return this.score;
	}

}
