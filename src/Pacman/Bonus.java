package Pacman;

public enum Bonus {
	
	GOMME("simple", 0, Level.MAX_INDEX, 10),
	SUPER_GOMME("super", 0, Level.MAX_INDEX, 100);
	
	/**
	 * 
	 */
	private final int levelMin;
	
	/**
	 * Type of the bonus
	 */
	private String type;
	
	/**
	 * 
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
	 * 
	 * @param level
	 * @return
	 */
	public boolean checkLevel(int level) {
		return level >= this.levelMin && level <= this.levelMax;
	}
}
