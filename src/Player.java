import java.awt.Color;
import java.awt.Graphics;

public class Player {
	private int x, y;
	
	public Player(){
		this(10,10);
	}
	
	public Player(int x,int y){
		this.x = x;
		this.y = y;
		
	}
	
	public void gameLoop(){
		
		double sqrt2 = Math.sqrt(2);
		boolean verticalInput = KeyboardController.isUpHeld()	|| KeyboardController.isDownHeld();
		boolean horizontalInput = KeyboardController.isLeftHeld()	|| KeyboardController.isRightHeld();
			
		if(KeyboardController.isUpHeld()){
			if(!horizontalInput){
			y-=6;
			}else{
			y-= (int)(6 / sqrt2);	
			}
		}
		
		if(KeyboardController.isDownHeld()){
			if(!horizontalInput){
			y+=6;
			}else{
			y+= (int)(6 / sqrt2);	
			}
		}
		
		if(KeyboardController.isLeftHeld()){
			if(!verticalInput){
			x-=6;
			}else{
			x-= (int)(6 / sqrt2);	
			}
		}
		
		if(KeyboardController.isRightHeld()){
			if(!verticalInput){
			x+=6;
			}else{
			x+= (int)(6 / sqrt2);	
			}
		}
	}
	
	
	public void draw(Graphics g){
		
		g.setColor(Color.black);
		g.fillRect(x,y,50,50);
	}
	
	
	public int getX(){
		return x;
	}

	public int getY() {
		return y;
	}
	
	
}
