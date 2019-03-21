package Pacman;

public enum Bonus {
	
	GOMME(0, Level.MAX_INDEX, 10),
	SUPER_GOMME(0, Level.MAX_INDEX, 100);
	
	private final int levelMin;
	private final int levelMax;
	private final int score;
	
	Bonus(int levelMin, int levelMax, int score){
		this.levelMax = levelMax;
		this.levelMin = levelMin;
		this.score = score;
	}
	
}
