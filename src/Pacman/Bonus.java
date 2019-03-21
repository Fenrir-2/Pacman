package Pacman;

public enum Bonus {
	
	GOMME("simple", 0, Level.MAX_INDEX, 10),
	SUPER_GOMME("super", 0, Level.MAX_INDEX, 100);
	
	/**
	 * 
	 */
	private final int levelMin;
	
	/**
	 * 
	 */
	private String type;
	
	/**
	 * 
	 */
	private final int levelMax;
	
	/**
	 * 
	 */
	private final int score;
	
	/**
	 * 
	 * @param type
	 * @param levelMin
	 * @param levelMax
	 * @param score
	 */
	Bonus(String type, int levelMin, int levelMax, int score){
		this.type = type;
		this.levelMax = levelMax;
		this.levelMin = levelMin;
		this.score = score;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getScore() {
		return this.score;
	}
	
	/**
	 * 
	 * @return
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
