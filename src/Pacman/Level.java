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
					//Checking in case garbage was slipped inside a line def
					if(caseDef == 'f' || caseDef == 't') {
						caseLine.add(new Case(caseDef == 't', null, null, null));
					}
				}
				list.add(caseLine);
				System.out.println(caseLine.size());
			}
			
			//Checking if Pacman location
			if(line.toCharArray()[0] == 'P') {
				//Getting the X and Y location of Pacman
				String[] pacLocLine = line.split(" ");
				int x = Integer.parseInt(pacLocLine[1].trim());
				int y = Integer.parseInt(pacLocLine[2].trim());
				
				assert(x > 0) : "X location of Pacman negative";
				assert(y > 0) : "Y location of Pacman negative";
				assert(x < this.list.size()) : "X location of Pacman greater than board maximum";
				assert(y >  this.list.get(0).size()) : "Y location of Pacman greater than board maximum";
				
				System.out.println("Pacman detected @ " + x + "," + y);
				
				//Setting the Pacman on the right board square
				this.list.get(x).get(y).setPacMan(new Pac_Man());
			}
			
			//Checking if bonus location
			if(line.toCharArray()[0] == 'S') {
				//Getting the X and Y location of the ghost
				String[] bonusLocLine = line.split(" ");
				int x = Integer.parseInt(bonusLocLine[1].trim());
				int y = Integer.parseInt(bonusLocLine[2].trim());
				
				assert(x > 0) : "X location of bonus negative";
				assert(y > 0) : "Y location of bonus negative";
				assert(x < this.list.size()) : "X location of bonus greater than board maximum";
				assert(y >  this.list.get(0).size()) : "Y location of bonus greater than board maximum";
				
				System.out.println("Bonus detected @ " + x + "," + y);
				
				//Setting the ghost on the right board square
				this.list.get(x).get(y).setBonus(new BonusEntity(this.index));;
			}
			
			//Checking if ghost location
			if(line.toCharArray()[0] == 'F') {
				//Getting the X and Y location of the ghost
				String[] ghostLocLine = line.split(" ");
				int x = Integer.parseInt(ghostLocLine[1].trim());
				int y = Integer.parseInt(ghostLocLine[2].trim());
				
				assert(x > 0) : "X location of ghost negative";
				assert(y > 0) : "Y location of ghost negative";
				assert(x < this.list.size()) : "X location of ghost greater than board maximum";
				assert(y >  this.list.get(0).size()) : "Y location of ghost greater than board maximum";
				
				System.out.println("Ghost detected @ " + x + "," + y);
				
				//Setting the ghost on the right board square
				this.list.get(x).get(y).setFantome(new Fantome());;
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
