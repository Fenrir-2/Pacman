package Pacman;

public class Fantome extends MoveableEntity{
	
	private boolean spooked;
	private boolean dead;
	
	public Fantome() {
		this.spooked = false;
		this.dead = false;
	}
	
	public void setSpooked(boolean state) {
		this.spooked = state;
	}
	
	public void setDead(boolean state){
		this.dead = state;
	}
	
	public boolean getSpooked() {
		return this.spooked;
	}
	
	public boolean getDead() {
		return this.dead;
	}
}
