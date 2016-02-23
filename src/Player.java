import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Player extends GameObject {
	private int startx,starty;
	
	public Player(){
		this(100,0);
	}
	
	public Player(int x,int y){
		super(x,y,50,50);
		startx = x;
		starty = y;
		
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
		
		ArrayList<GameObject> objects = GamePanel.getInstance().getObjects();
		
		int myLeft = x + dx;
		int myRight = myLeft + width;
		int myTop = y + dy;
		int myBot = myTop + height;
		
		for(GameObject object : objects){
			int sLeft = object.getX();
			int sRight = sLeft + object.getWidth();
			int sTop = object.getY();
			int sBot = sTop + object.getHeight();
			
			boolean xOverlaps = myLeft < sRight && sLeft < myRight;
			boolean yOverlaps = myTop < sBot && sTop < myBot;
			if(xOverlaps && yOverlaps){
				if(object instanceof Solid){
					boolean yDidOverlap = myTop-dy < sBot && sTop < myBot-dy;
					boolean horCollission = yDidOverlap;
					if(horCollission){
						if(myLeft < sLeft){//colision wth left of solid
							x = sLeft - width;
						}else{//colliosion wth right of solid
							x  = sRight;
						}
						dx=0;
					}else{
						if(myTop < sTop){//collsion with top of solid
							y = sTop - height;
						}else{//collsion w/ bot of solid
							y = sBot;
						}
						dy=0;
					}
				}
				if(object instanceof Enemy){
					x = startx;
					y = starty;
				}
			}
		}
	}
	
	private void applyMovement(){
		x += dx;
		y += dy;
	}
	
	public void draw(Graphics g){
		
		g.setColor(Color.red);
		g.fillRect(x,y,width,height);
	}
	
	
}
