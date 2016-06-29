import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Bullet extends GameObject  {
	public Bullet(int x,int y){
		super(x,y,16,16,"bullet");
		
		HashMap<Integer,GameObject> objects = 
				GameManager.getInstance().getObjects();
		Iterator it = objects.entrySet().iterator();
		GameObject player = new Player(0,0);
		while(it.hasNext()){
			Map.Entry <Integer,GameObject> pair =
					(Map.Entry <Integer,GameObject>)it.next();
			GameObject obj = (GameObject)pair.getValue();
			if(obj instanceof Player){
				player = obj;
			}
			
		}
		

		dx = player.getX() - x;
		dy = player.getY() - y;
		int speed = 10;
		double mag = Math.sqrt(dx * dx + dy * dy);
		double f =speed / mag;
				
		dx *= f;
		dy *= f;
	}
	
	
	public void gameLoop(){
		applyVelocity();
		outOfRoom();
	}
	
	public void outOfRoom(){
		int roomWidth = GameManager.getInstance().getRoomWidth(); 
		int roomHeight = GameManager.getInstance().getRoomHeight();
		
		if(getX() > roomWidth||getY() > roomHeight || getX() < 0 || getY() < 0 ){
			delete();
		}
	}
	
	
}
