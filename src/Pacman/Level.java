package Pacman;

import java.util.ArrayList;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

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
	
	/**
	 * Loads the file that contains the level corresponding to the index
	 * 
	 */
	public void changeList() {
		InputStreamReader fileInput = null;
		try {
			fileInput = new InputStreamReader(new FileInputStream("Level" + index));
		} catch (FileNotFoundException e) {
			System.out.println("Could not find the level: " + this.index);
			e.printStackTrace();
		}
		
		String fileText = "";
		char[] buffer = new char[1024];
		
		if(fileInput != null) {
			int len = 0;
			try {
				len = fileInput.read(buffer);
			} catch (IOException e) {
				System.out.println("Error while reading first kB of file");
				e.printStackTrace();
			}
			
			while(len != -1) {
				try {
					len = fileInput.read(buffer);
				} catch (IOException e) {
					e.printStackTrace();
				}
				fileText += String.valueOf(buffer);
			}
		}
		
		System.out.println(fileText);
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
		Level board = new Level();
	}

}
