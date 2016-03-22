import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {

	private int startx,starty;
	private int points=0;
	
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
		applyVelocity();
		
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
		
			
	
	protected void collideWith(GameObject object){
		if(object instanceof Solid){
			Rectangle obounds = object.getBounds();
			boolean yDidOverlap = bounds.getMinY() < obounds.getMaxY() 
					&& obounds.getMinY() < bounds.getMaxY();
			boolean horCollission = yDidOverlap;
			if(horCollission){
				if(bounds.getMinX() < obounds.getMinX()){//colision wth left of solid
					bounds.x = (int)obounds.getMinX() - bounds.width;
				}else{//colliosion wth right of solid
				bounds.x  = (int)obounds.getMaxX();
				}
				dx = 0;
			}else{
				if(bounds.getMinY() < obounds.getMinY()){//collsion with top of solid
					bounds.y = (int)obounds.getMinY() - bounds.height;
				}else{//collsion w/ bot of solid
					bounds.y = (int)obounds.getMaxY();
				}
				dy = 0;
			}
		} 
		if(object instanceof Enemy){
			
			bounds.setLocation(startx,starty);
		}  
	}

	public void gainPoint(){
		points++;
	}
	
	public void draw(Graphics g){
		
		g.setColor(Color.red);
		g.fillRect(bounds.x,bounds.y,bounds.width,bounds.height);
		
		g.setColor(Color.WHITE);
		Font font = new Font(null,Font.BOLD,18);
		g.setFont(font);
		g.drawString(points + "",(int)bounds.getCenterX(), (int)bounds.getCenterY() );
	}
	
	
}
