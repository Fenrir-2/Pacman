
public class Fantome extends MoveableEntity{
	
	private boolean Spooked;
	private boolean Dead;
	
	public Fantome() {
		
	}
	
	public void setSpooked(boolean state) {
		this.Spooked = state;
	}
	
	public void setDead(boolean state){
		this.Dead = state;
	}
	
}
