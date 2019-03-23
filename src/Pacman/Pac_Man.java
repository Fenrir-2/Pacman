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
	 * Nombre de vie de Pacman, initialisÃ© Ã  3
	 */
	protected int nbVies = 3;
	
	/**
	 * Boolean for Pacman SuperMode, 
	 */
	protected boolean SuperMode = false;
	
	/**
	 * Pacman's Score
	 */
	protected int score = 0;
	
	/**
	 * Main constructor for Pac_Man objects.
	 * 
	 * @param x The horizontal position
	 * @param y The vertical position
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
	 * Method for Pacman to lose a life
	 */
	public void perdVie() {
		this.nbVies = this.nbVies-1;
		assert(this.nbVies >= 0) : "Number of lives below zero";
		assert(this.nbVies == this.nbVies-1) : "Le nombre de vie de pacman n'a pas été mis à  jour correctement";
	}
	
	/**
	 * Changes the Pac_Man super mode state.
	 * 
	 * @param state the new {@link #SuperMode} state
	 */

	public void setSuperMode(boolean state) {
		this.SuperMode = state;
		assert(this.SuperMode == state) : "L'état du SuperMode n'est pas respecté";
		
	}
	
	/**
	 * Getter of Pacman's Supermode
	 * @return The SuperMode of Pacman
	 */
	public boolean getSuperMode() {
		return this.SuperMode;
	}
	
	/**
	 * Getter of Pacman's lives
	 * @return Pacman's lives number
	 */
	public int getPacManLives() {
		return this.nbVies;
	}
	
	/**
	 * Adds an integer to Pac_Man's score
	 * 
	 * @param score the score of the bonus pacman eats. Must be positive
	 */
	public void addScore(int score) {
		assert(score > 0) : "Negative score received";
		this.score = this.score+score;
		assert(this.score > 0) : "Negative score after addScore";
	}
	
	/**
	 * Draw the Entity PacMan on the canvas
	 */
	public void draw() {
		Canvas canvas = Canvas.getCanvas();
        canvas.draw(this.imageSprite, this.posHor, this.posVer);
	}
	/**
	 * Score getter.
	 * 
	 * @return Pacman's score
	 */
	public int getScore() {
		return this.score;
	}

}
