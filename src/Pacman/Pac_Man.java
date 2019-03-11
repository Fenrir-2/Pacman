package Pacman;

public class Pac_Man extends MoveableEntity{
	
	
	private int nbVies = 3;
	private boolean SuperMode = false;
	private int Score = 0;
	
	public Pac_Man() {
		
	}
	
	public void perdVie() {
		this.nbVies = this.nbVies-1;
	}
	
	public boolean setSuperMode(boolean state) {
		this.SuperMode = state;
		return this.SuperMode;
	}
	
	public int getPacManLives() {
		return this.nbVies;
	}
	
	public void addscore(int score) {
		this.Score = this.Score+score;
	}
/*
 * Gros shlag
 */
}
