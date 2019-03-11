
public class Case extends Drawable{
	
	private boolean Walkable;
	private BonusEntity bonus = new BonusEntity();
	private Pac_Man pacman = new Pac_Man();
	private Fantome fantome = new Fantome();
	
	public Case() {
		
	}
	
	public boolean isWalkable() {
		return this.Walkable;
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
	
	

}
