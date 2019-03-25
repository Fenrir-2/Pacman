package Pacman;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.SwingUtilities;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * The class Level represents the board game with all the entities such as Pacman, ghosts, Cases and bonuses
 * This class holds the "main" method, to allow the player to play the game
 * It computes each frame, to move the entities according to the keyboard inputs
 * 
 * @author Nicolas FONNIER, Henri GLEVEAU
 */
public class Level {
	
	/**
	 * Number of levels
	 */
	protected static final int MAX_INDEX = 3;
	
	/**
	 * Size of the image of a square
	 */
	protected static final int SQUARE_SIZE = 10;
	
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
	 * Holds all the defaults/starting positions for the differents ghosts
	 * Referenced in squares, you need to multply by {@link #SQUARE_SIZE} to get the pixel location.
	 */
	protected ArrayList<int[]> ghostDefaultPos;
	
	/**
	 * Holds the default/starting position for Pacman.
	 * Referenced in squares, you need to multply by {@link #SQUARE_SIZE} to get the pixel location.
	 */
	protected int[] pacDefaultPos;
	
	/**
	 * Level constructor, 
	 * no params needed
	 */
	public Level() {
		this.list = new ArrayList<ArrayList<Case>>();
		this.pacman = null;
		this.ghostList = new ArrayList<Fantome>();
		this.bonusList = new ArrayList<BonusEntity>();
		this.modifiedObjectList = new ArrayList<Drawable>();
		this.ghostDefaultPos = new ArrayList<int[]>();
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
	 * Draws the list of all the {@link Pacman.Drawable} objects that have been modified
	 * between two calls of {@link #computeNextFrame()}.
	 */
	public void drawModifiedList() {
		//Usually, the list consists of the board squares first, then the other objects.
		for(Drawable object : this.modifiedObjectList) {
			object.draw();
		}
		
		//Score footer
		Drawable footer = new Drawable(0, Canvas.HEIGHT - 15) {
			
			@Override
			public void draw() {
				if(this.imageSprite == null) {
					try {
						this.imageSprite = ImageIO.read(new File("Footer.png"));
					} catch (IOException e) {
						System.out.println("Error while loading image: Footer.png");
						e.printStackTrace();
					}
				}
				
				Canvas canvas = Canvas.getCanvas();
				if(this.imageSprite != null)
					canvas.draw(this.imageSprite, this.posHor, this.posVer);
				
			}
			
		};
		
		footer.draw();
		
		//Clearing the list so no objects are marked are modified
		this.modifiedObjectList.clear();
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
					i += Level.SQUARE_SIZE;
				}
				
				caseNbOnLine=0;
				list.add(caseLine);
				System.out.println(caseLine.size());
				i = 0;
				j += Level.SQUARE_SIZE;
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
				this.pacman = new Pac_Man(y*Level.SQUARE_SIZE, x*Level.SQUARE_SIZE);
				
				//Setting the default position
				this.pacDefaultPos = new int[2];
				this.pacDefaultPos[0] = y;
				this.pacDefaultPos[1] = x;
				
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
				BonusEntity newBonus = new BonusEntity(Bonus.SUPER_GOMME, this.index, y*Level.SQUARE_SIZE,x*Level.SQUARE_SIZE);
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
				
				//Adding the default position of this ghost to the list of default positions
				this.ghostDefaultPos.add(new int[] {x,y});
				
				//Keeping a reference to the new ghost
				Fantome newGhost = new Fantome(y*Level.SQUARE_SIZE,x*Level.SQUARE_SIZE);
				this.ghostList.add(newGhost);
				
				//Adding it to the board
				this.list.get(x).get(y).setFantome(newGhost);
			}
		}
				
		System.out.println(list.size());
	}
	
	/**
	 * Checks the ending scenarios with {@link #checkEndGame()}, calls {@link #endGame(int)} if 1 or 2.
	 * If 0, calls successively {@link #updateGhost()}, then {@link #updatePacMan()},
	 * then {@link #drawModifiedList()}, then itself again.
	 */
	public void computeNextFrame() {
		int endState = this.checkEndGame();
		
		if(endState == 0) {
			//Moving the player and the ghosts
			SwingUtilities.invokeLater(new Runnable() {
	            @Override	            
	            public void run() {
	            	//This allows for a reasonable speed for the ghost
	            	try {
	    				Thread.sleep(100);
	    			} catch (InterruptedException e) {
	    				System.out.println("Error while sleeping");
	    				e.printStackTrace();
	    			}
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
			
			//Drawing the list of modified object, which is smaller.
			SwingUtilities.invokeLater(new Runnable() {
	            @Override	            
	            public void run() {
	            	//This allows for a reasonable speed for the ghost
	            	try {
	    				Thread.sleep(11);
	    			} catch (InterruptedException e) {
	    				System.out.println("Error while sleeping");
	    				e.printStackTrace();
	    			}
	            	Level.this.drawModifiedList();
	            }
	        });
			
			
			//As long as everything is fine, keep computing new states
			computeNextFrame();
		}else {
			//The endGame functions handles the different end game cases
			this.endGame(endState);
		}
	}
	
	/**
	 *  Updates the ghost object according to a random number and checks for collision with walls.
     *  It also manages to update the location of ghosts
	 */
	public void updateGhost() {
		for (Fantome ghost : ghostList) {
			int deplacement = (int) (1 + Math.random() * ( 5 - 1 ));
			int x = ghost.getX()/Level.SQUARE_SIZE;
			int y = ghost.getY()/Level.SQUARE_SIZE;
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
			
			ghost.moveTo(x*Level.SQUARE_SIZE, y*Level.SQUARE_SIZE);
			this.modifiedObjectList.add(0, this.list.get(y).get(x));
			this.modifiedObjectList.add(ghost);
		}
	}
	
	/**
	 * Resets all the ghost to their starting positions according to what has been found in
	 * {@link #changeList()}
	 */
	public void resetGhost() {		
		if(ghostList.size() == ghostDefaultPos.size())
			System.out.println("More ghost then there are default positions.");
		
		//Used to access all the default positions in order
		int i = 0;
		for(Fantome ghost : ghostList) {
			//Retrieving the list of default positions
			int x = ghostDefaultPos.get(i)[0];
			int y = ghostDefaultPos.get(i)[1];
			
			//Moving the ghost and setting the squares accordingly
			this.list.get(ghost.getY()).get(ghost.getX()).setFantome(null);
			this.list.get(y).get(x).setFantome(ghost);
			ghost.moveTo(x, y);
			
			//Adding the squares and the ghost to the modified objects list so they can be redrawn
			this.modifiedObjectList.add(ghost);
			this.modifiedObjectList.add(0, this.list.get(y).get(x));
			this.modifiedObjectList.add(0, this.list.get(ghost.getY()).get(ghost.getX()));
		}
	}
	
	/**
	 * Updates the Pacman object according to the input and checks for collision with walls, ghost, and bonuses, and then react accordingly.
	 */
	public void updatePacMan() {
		//Retrieving the input.
		boolean gauche = Canvas.getCanvas().isLeftPressed();
		boolean droite = Canvas.getCanvas().isRightPressed();
		boolean haut = Canvas.getCanvas().isUpPressed();
		boolean bas = Canvas.getCanvas().isDownPressed();
		
		//New coordinates for Pac
		int x = pacman.getX()/Level.SQUARE_SIZE;
		int y = pacman.getY()/Level.SQUARE_SIZE;
		
		Case currSquare = this.list.get(y).get(x);
		
		for(Fantome ghost : ghostList) {
			if(ghost.checkCollision(pacman)) {
				//If the ghost is dead, we simply ignore it
				if(!ghost.getDead()) {
					//If we are in super mode, we set the ghost to dead and add 200 to the score
					if(ghost.getSpooked() || pacman.getSuperMode()) {
						pacman.addScore(200);
						ghost.setDead(true);
						
					}else {
						//Removing the pacman from the current square
						currSquare.setPacMan(null);
						
						//Moving it to the beginning position
						this.list.get(this.pacDefaultPos[0]).get(this.pacDefaultPos[1]).setPacMan(this.pacman);
						this.pacman.moveTo(this.pacDefaultPos[0] * Level.SQUARE_SIZE, this.pacDefaultPos[1] * Level.SQUARE_SIZE);
						
						//Adding all the modified objects to the list
						this.modifiedObjectList.add(0, this.list.get(pacman.getY()/10).get(pacman.getX()/10));
						this.modifiedObjectList.add(0, currSquare);
						this.modifiedObjectList.add(this.pacman);
						
						//Removing 1 life
						pacman.perdVie();
					}
				}
			}
		}
		
	 	x += gauche?-1:0;
	 	x += droite?1:0;
	 	y += haut?-1:0;
	 	y += bas?1:0;
	 	
	 	if(gauche || droite || haut || bas) {
		 	if(this.list.get(y).get(x).isWalkable()) {
				//Removing the Pacman from the current square
				currSquare.setPacMan(null);
				
				//Moving the Pac
				pacman.moveTo(x*Level.SQUARE_SIZE, y*Level.SQUARE_SIZE);
				
				Case nextSquare = this.list.get(y).get(x);
				nextSquare.setPacMan(this.pacman);
				//Bonus of the next board square
				BonusEntity bonus = nextSquare.getBonus();
				
				if(bonus != null){
					pacman.addScore(bonus.getScore());
					nextSquare.setBonus(null);
					//In case it was a super bonus, we set the ghost and pacman accordingly
					if(bonus.getBonusType() == Bonus.SUPER_GOMME) {
						for(Fantome ghost : this.ghostList) {
							ghost.setSpooked(true);
						}
						this.pacman.setSuperMode(true);
						
						//In 15 seconds, deactivates all the effects we just set.
						Timer bonusEndTimer = new Timer();
						bonusEndTimer.schedule(new TimerTask() {

							@Override
							public void run() {
								for(Fantome ghost : Level.this.ghostList) {
									ghost.setSpooked(false);
								}
								
								Level.this.pacman.setSuperMode(false);
								
								//This is in case the user hasn't moved since the timer ended
								Level.this.modifiedObjectList.add(Level.this.pacman);
							}
							
						}, 15000);
					}
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
	 * Check all the end game cases, which are: Pacman has no lives left, there are
	 * no bonuses left
	 * 
	 * @return 0 if everything's fine, 1 if Pacman has no more lives, 2 if there are no more
	 * bonuses
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
	 * Handles the different ending scenarios
	 * 
	 * @param endState The state that has been detected. Cf {@link #checkEndGame()}
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
