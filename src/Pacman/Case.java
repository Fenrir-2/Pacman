package Pacman;

public class Case extends Drawable{
	
	protected boolean walkable;
	protected BonusEntity bonus;
	protected Pac_Man pacman;
	protected Fantome fantome;
	
	public Case(boolean walkable, BonusEntity bonus, Pac_Man pacman, Fantome fantome) {
		this.walkable = walkable;
		this.bonus = bonus;
		this.pacman = pacman;
		this.fantome = fantome;
	}
	
	public boolean isWalkable() {
		return this.walkable;
	}
	
	public BonusEntity getBonus() {
		return this.bonus;
	}
	
	public void setBonus(BonusEntity bonus) {
		this.bonus = bonus;
	}
	
	public Pac_Man getPacMan() {
		return this.pacman;
	}
	
	public void setPacMan(Pac_Man pacman) {
		this.pacman = pacman;
	}
	
	public Fantome getFantome() {
		return this.fantome;
	}
	
	public void setFantome(Fantome fantome) {
		this.fantome = fantome;
	}
	
	public void draw() {
		if(this.pacman != null){
			this.pacman.draw();			
		}
		
		if(this.fantome != null) {
			this.fantome.draw();
		}
		
		if(this.bonus != null) {
			this.bonus.draw();
		}
	}
}
