
public class Portal extends GameObject {

	String level;
	
	public Portal (String lvl,int x,int y){
		super(x,y,"portal");
		this.level=lvl;
		
	}
	
	public void gameLoop(){
		checkCollisions();
	}
	
	protected void collideWith(GameObject object){
		if(object instanceof Player){
			GameManager.getInstance().goToLevel(level);
		}
	}
	
	
	
}
