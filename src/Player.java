import java.awt.Color;
import java.awt.Graphics;

public class Player {
	private int x, y;
	private int dx,dy;
	
	public Player(){
		this(100,0);
	}
	
	public Player(int x,int y){
		this.x = x;
		this.y = y;
		
	}
	
	public void gameLoop(){
		getInputMovement();
		checkCollisions();
		applyMovement();
		
		
	}
	
	private void getInputMovement(){
		double sqrt2 = Math.sqrt(2);
		boolean verticalInput = KeyboardController.isUpHeld()	|| KeyboardController.isDownHeld();
		boolean horizontalInput = KeyboardController.isLeftHeld()	|| KeyboardController.isRightHeld();
			
		dx = 0;
		dy = 0;
		
		if(KeyboardController.isUpHeld()){
			if(!horizontalInput){
			dy=-6;
			}else{
			dy-= (int)(6 / sqrt2);	
			}
		}
		
		if(KeyboardController.isDownHeld()){
			if(!horizontalInput){
			dy+=6;
			}else{
			dy+= (int)(6 / sqrt2);	
			}
		}
		
		if(KeyboardController.isLeftHeld()){
			if(!verticalInput){
			dx-=6;
			}else{
			dx-= (int)(6 / sqrt2);	
			}
		}
		
		if(KeyboardController.isRightHeld()){
			if(!verticalInput){
			dx+=6;
			}else{
			dx+= (int)(6 / sqrt2);	
			}
		}
		
	}
	
	private void checkCollisions(){
		Solid[] solids = GamePanel.getInstance().getSolids();
	}
	
	private void applyMovement(){
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics g){
		
		g.setColor(Color.blue);
		g.fillRect(x,y,50,50);
	}
	
	
	public int getX(){
		return x;
	}

	public int getY() {
		return y;
	}
	
	
}
