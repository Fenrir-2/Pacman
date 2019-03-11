package Pacman;

import java.util.ArrayList;

public class Level {
	
	/**
	 * Holds the number of the current level
	 */
	protected int index;
	
	/**
	 * Holds all the squares of the board
	 */
	protected ArrayList<ArrayList<Case>> list; 
	
	/**
	 * Level constructor
	 * 
	 * No param needed
	 */
	public Level() {
		this.list = new ArrayList<ArrayList<Case>>();
		index = 1;
		
		this.changeList();
	}
	
	/**
	 * Runs through the list and draws each square.
	 */
	public void drawList() {
		for(ArrayList<Case> caseList : list) {
			for(Case boardCase : caseList) {
				boardCase.draw();
			}
		}
	}
	
	public void changeList() {
		
	}
	
	public void computeNextFrame() {
		
	}
	
	public void updateGhost() {
		
	}
	
	public void updatePacMan() {
		
	}
	
	public int checkEndGame() {
		return 0; 
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
