
public class Turret extends GameObject {

	int time = 60;
	int timer = 0;
	
	public Turret(int x ,int y){
		super(x,y,"turret");
	}
	
	public void gameLoop(){
		
		if(timerReady()){
			GameManager.getInstance().addObject(new Bullet((int)bounds.getCenterX(),
					(int)bounds.getCenterY()));
		}
	}
	
	private boolean timerReady(){
		
		boolean ready = false;
		timer++;
		if(timer >= time){
			ready = true;
			timer = 0;
		}
		return ready;
	}
	
}
