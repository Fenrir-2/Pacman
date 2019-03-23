package Pacman;

import java.util.ArrayList;
import javax.swing.SwingUtilities;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * Short class description
 * 
 * @author Nicolas FONNIER, Henri GLEVEAU
 *
 */
public class Level {
	
	/**
	 * Number of levels
	 */
	protected static final int MAX_INDEX = 3;
	
	/**
	 * The name of the score file
	 */
	protected static final String SCORE_FILE = "score.dat"; 
	
	/**
	 * Holds the number of the current level
	 */
	protected int index;
	
	/**
	 * Holds all the squares of the board
	 */
	protected ArrayList<ArrayList<Case>> list; 
	
	/**
	 * Holds a reference to the pacman object
	 */
	protected Pac_Man pacman;
	
	/**
	 * Holds a reference to all the ghosts on the board
	 */
	protected ArrayList<Fantome> ghostList;
	
	/**
	 * Holds a reference to all the bonus entities on the board
	 * Allows for fast checking of end game state
	 */
	protected ArrayList<BonusEntity> bonusList;
	
	/**
	 * Holds the reference to all the objects that have been modified between two
	 * calls of computeNextFrame main loop
	 */
	protected ArrayList<Drawable> modifiedObjectList;
	
	/**
	 * Level constructor
	 * 
	 * No param needed
	 */
	public Level() {
		this.list = new ArrayList<ArrayList<Case>>();
		this.pacman = null;
		this.ghostList = new ArrayList<Fantome>();
		this.bonusList = new ArrayList<BonusEntity>();
		this.modifiedObjectList = new ArrayList<Drawable>();
		index = 1;
		
		this.changeList();
		this.drawList();
	}
	
	/**
	 * Runs through the list and draws each square.
	 * Draws, in order: Squares, bonus, ghost, Pacman
	 */
	public void drawList() {
		//Clearing the board
		Canvas.getCanvas().redraw();
		
		//Waiting for the clear to be finished
		Canvas.getCanvas().wait(100);
		
		//Drawing all the board squares		
		for(ArrayList<Case> caseList : list) {
			for(Case boardCase : caseList) {
				boardCase.draw();
			}
		}
		
		//Drawing every bonus entity
		for(BonusEntity bonus : bonusList) {
			bonus.draw();
		}
		
		//Drawing every ghost
		for(Fantome fantome : ghostList) {
			fantome.draw();
		}
		
		//Drawing PacMan
		pacman.draw();
	}
	
	/**
	 * 
	 */
	public void drawModifiedList() {
		//Usually, the list consists of the board squares first, then the other objects.
		for(Drawable object : this.modifiedObjectList) {
			object.draw();
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
		int i = 0;
		int j = 0;
		int caseNbOnLine = 0;
		for(String line : lines) {
			//Checking if line definition
			if(line.toCharArray()[0] == 'f' || line.toCharArray()[0] == 't') {
				
				ArrayList<Case> caseLine = new ArrayList<Case>();
				//Allows to work on each character separately
				for(char caseDef : line.toCharArray()) {
					
					//Checking in case garbage was slipped inside a line def
					if(caseDef == 'f' || caseDef == 't') {
						
						caseLine.add(new Case(caseDef == 't', null, null, null,  i, j));
						if(caseLine.get(caseNbOnLine).isWalkable()) {
							//If the square is walkable, set a simple bonus on the square
							BonusEntity newBonus = new BonusEntity(Bonus.GOMME, this.index, i-11, j-9);
							this.bonusList.add(newBonus);
							caseLine.get(caseNbOnLine).setBonus(newBonus);
						}
					}
					caseNbOnLine++;
					i+=10;
				}
				
				caseNbOnLine=0;
				list.add(caseLine);
				System.out.println(caseLine.size());
				i=0;
				j=j+10;
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
				if(this.list.get(x).get(y).getBonus()!=null) {
					this.bonusList.remove(this.list.get(x).get(y).getBonus());
					this.list.get(x).get(y).setBonus(null);
				}
				
				//Creating the Pacman we'll keep a reference to for later
				this.pacman = new Pac_Man(y*10,x*10);
				
				//Adding the Pacman to the square
				this.list.get(x).get(y).setPacMan(this.pacman);
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
				BonusEntity newBonus = new BonusEntity(Bonus.SUPER_GOMME, this.index, y*10,x*10);
				this.bonusList.remove(this.list.get(x).get(y).getBonus());
				this.bonusList.add(newBonus);
				this.list.get(x).get(y).setBonus(newBonus);
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
				if(this.list.get(x).get(y).getBonus()!=null)
					this.bonusList.remove(this.list.get(x).get(y).getBonus());
					this.list.get(x).get(y).setBonus(null);
				
				//Keeping a reference to the new ghost
				Fantome newGhost = new Fantome(y*10,x*10);
				this.ghostList.add(newGhost);
				
				//Adding it to the board
				this.list.get(x).get(y).setFantome(newGhost);
			}
		}
				
		System.out.println(list.size());
	}
	
	/**
	 * 
	 */
	//TODO : A Reorganiser en découpant dans les bonnes fonctions et en utilisant les bonnes méthodes 
	public void computeNextFrame() {
		int endState = this.checkEndGame();
		
		if(endState == 0) {
						
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Error while sleeping");
				e.printStackTrace();
			}

			//Moving the player and the ghosts
			SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	            	Level.this.updateGhost();
	            }
	        });
			
			//Allows the ghost movement to be done 
			//We still need to wait for all modification to the modified list to be done to draw.
			try {
				SwingUtilities.invokeAndWait(new Runnable() {
					@Override
					public void run() {
						Level.this.updatePacMan();
					}
				});
			} catch (InvocationTargetException e) {
				System.out.println("Error calling updatePacman");
				e.printStackTrace();
			} catch (InterruptedException e) {
				System.out.println("Error calling update Pacman");
				e.printStackTrace();
			}
			
			
			//Setting the label to the correct score
			Canvas.getCanvas().getScoreLabel().setText("Score : " + Integer.toString(this.pacman.getScore()));
			this.drawModifiedList();
			
			//Clearing the list so no objects are marked are modified
			this.modifiedObjectList.clear();
			
			//As long as everything is fine, keep computing new states
			computeNextFrame();
		}else {
			this.endGame(endState);
		}
	}
	
	/**
	 * 
	 */
	public void updateGhost() {
		
		//TODO: REFACTOR
		for (Fantome ghost : ghostList) {
			int deplacement = (int) (1 + Math.random() * ( 5 - 1 ));
			int x = ghost.posHor/10;
			int y = ghost.posVer/10;
			if(this.list.get(y).get(x).isWalkable())
				this.modifiedObjectList.add(0, this.list.get(y).get(x));
			if(this.list.get(y).get(x).getBonus() != null)
				this.modifiedObjectList.add(this.list.get(y).get(x).getBonus());
			
			switch(deplacement) {
				case 1: x += this.list.get(y).get(x-1).isWalkable()?-1:0;
				break;
				
				case 2: y += this.list.get(y-1).get(x).isWalkable()?-1:0;
				break;
				
				case 3: x += this.list.get(y).get(x+1).isWalkable()?1:0;
				break;
				
				case 4: y += this.list.get(y+1).get(x).isWalkable()?1:0;
				break;
			}
			
			ghost.moveTo(x*10, y*10);
			this.modifiedObjectList.add(0, this.list.get(y).get(x));
			this.modifiedObjectList.add(ghost);
		}
		
		//Resetting the ghost to their original place.
		/*
		int i = 0;
		int z = 0;
		for(Fantome ghost : ghostList) {
			int x = ghost.posHor;
			int y = ghost.posVer;
			this.list.get(14).get(11+i).setFantome(ghost);
			ghost.posHor = 110+z;
			ghost.posVer = 140;
			ghost.draw();
			this.list.get(x/10).get(y/10).setFantome(null);
		}
		i=i+1;
		z=z+10;*/
	}
	
	/**
	 * 
	 */
	public void updatePacMan() {
		//Retrieving the input.
		boolean gauche = Canvas.getCanvas().isLeftPressed();
		boolean droite = Canvas.getCanvas().isRightPressed();
		boolean haut = Canvas.getCanvas().isUpPressed();
		boolean bas = Canvas.getCanvas().isDownPressed();
		
		//New coordinates for Pac
		int x = pacman.getX()/10;
		int y = pacman.getY()/10;
		
		//Current coordinates
		/*
		int currX = pacman.getX()/10;
		int currY = pacman.getY()/10;
		*/
		Case currSquare = this.list.get(y).get(x);
		
		for(Fantome ghost : ghostList) {
			if(ghost.checkCollision(pacman)) {
				//Removing the pacman from the current square
				currSquare.setPacMan(null);
				
				//Moving it to the beginning position
				this.list.get(23).get(13).setPacMan(this.pacman);
				this.pacman.moveTo(130, 230);
				this.modifiedObjectList.add(this.pacman);
				
				//Removing 1 life
				pacman.perdVie();
				
				//No need to check for input for the time being.
				return;		
			}
		}
		
		//Whole version:
	 	x += gauche?-1:0;
	 	x += droite?1:0;
	 	y += haut?-1:0;
	 	y += bas?1:0;
	 	
	 	if(gauche || droite || haut || bas) {
		 	if(this.list.get(y).get(x).isWalkable()) {
				//Removing the Pacman from the current square
				currSquare.setPacMan(null);
				
				//Moving the Pac
				pacman.moveTo(x*10, y*10);
				
				Case nextSquare = this.list.get(y).get(x);
				nextSquare.setPacMan(this.pacman);
				//Bonus of the next board square
				BonusEntity bonus = nextSquare.getBonus();
				
				if(bonus != null){
					pacman.addScore(bonus.getScore());
					nextSquare.setBonus(null);
					this.bonusList.remove(bonus);
				}
				
				this.modifiedObjectList.add(0, nextSquare);
				//Adding the square at the top so it is drawn first
				this.modifiedObjectList.add(0, currSquare);
				//Adding the square at the top so it is drawn first
				this.modifiedObjectList.add(pacman);
			}
	 	}
	}
	
	/**
	 * 
	 * @return
	 */
	public int checkEndGame() {
		//In case Pacman died
		if(this.pacman.getPacManLives() <= 0)
			return 1;
		
		//Checking if there are any bonuses left. If there are none, change to next level
		//and return 2
		if(this.bonusList.size() == 0)
			return 2;
		
		return 0; 
	}
	
	/**
	 * 
	 * @param endState
	 */
	public void endGame(int endState) {
		this.writeScore();
	}
	
	/**
	 * Fetches the score from pacman, and writes it to scores.dat
	 */
	public void writeScore() {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(Level.SCORE_FILE, true));
		} catch (IOException e) {
			System.out.println("Error while creating score Writer");
			e.printStackTrace();
		}
		
		//If the writer was correctly setup
		if(bw != null) {
			try {
				System.out.println(this.pacman.getScore());
				bw.write(Integer.toString(this.pacman.getScore()) + "\n");
				bw.flush();
			} catch (IOException e) {
				System.out.println("Error while writing score. Closing...");
				e.printStackTrace();
			}finally {
				//Whatever happened, try to close the stream and release the file
				try {
					bw.close();
				} catch (IOException e) {
					System.out.println("Error while closing score stream");
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Point of entry
	 * 
	 * @param args Automatically sent by the system when the program is called
	 */
	public static void main(String[] args) {
		
		Level board = new Level();
		board.computeNextFrame();
		Canvas.getCanvas().close();
		System.out.println("Game ended");
	}

}
