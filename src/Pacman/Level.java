package Pacman;

import java.util.ArrayList;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

public class Level {
	
	/**
	 * Number of levels
	 */
	public static final int MAX_INDEX = 3;
	
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
		assert(index > 0) : "Invalid index : Negative.";
		assert(index < MAX_INDEX) : "Invalid index : Greater than MAX_INDEX";
		//Loads the file, then puts it in the fileText string
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
		
		//File parsing
		String[] lines = fileText.split("\n");
		
		for(String line : lines) {
			//Checking if line definition
			if(line.toCharArray()[0] == 'f' || line.toCharArray()[0] == 't') {
				ArrayList<Case> caseLine = new ArrayList<Case>();
				//Allows to work on each character separately
				for(char caseDef : line.toCharArray()) {
					caseLine.add(new Case(caseDef == 't', null, null, null));
				}
				list.add(caseLine);
				System.out.println(caseLine.size());
			}
		}
		
		System.out.println(list.size());
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
