package Pacman;

/**
 * 
 * Holds a definition of all the different bonuses.
 * They are implemented by {@link Pacman.BonusEntity}.
 * 
 * @author Nicolas FONNIER, Henri GLEVEAU
 *
 */
public enum Bonus {
	
	/**
	 * Simplest bonus. Found on nearly every walkable square.
	 * Adds 10 score.
	 */
	GOMME("simple", 0, Level.MAX_INDEX, 10),
	
	/**
	 * Super bonus. Allows the ghost to be spooked and Pacman to change to superMode
	 * Adds 100 score.
	 */
	SUPER_GOMME("super", 0, Level.MAX_INDEX, 100);
	
	/**
	 * Minimum level for the bonus to appear
	 */
	private final int levelMin;
	
	/**
	 * Type of the bonus
	 */
	private String type;
	
	/**
	 * Maximum level for the bonus to appear
	 */
	private final int levelMax;
	
	/**
	 * Score of the bonus
	 */
	private final int score;
	
	/**
	 * 
	 * @param type Type of the bonus 
	 * @param levelMin
	 * @param levelMax
	 * @param score Score of the bonus
	 */
	Bonus(String type, int levelMin, int levelMax, int score){
		this.type = type;
		this.levelMax = levelMax;
		this.levelMin = levelMin;
		this.score = score;
	}
	
	/**
	 * Getter of the bonus' score
	 * @return the score of the bonus
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * Getter of the bonus' type
	 * @return the type of the bonus
	 */
	public String getType() {
		return this.type;
	}
	
	/**
	 * Checks if the level is inside the bonus' range
	 * 
	 * @param level Current level from the Level object
	 * 
	 * @return true if the current level is inside the bonus' range, false if outside.
	 */
	public boolean checkLevel(int level) {
		return level >= this.levelMin && level <= this.levelMax;
	}
}
