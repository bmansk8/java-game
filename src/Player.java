import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject {
	
	private int startx,starty;
	private int points=0;
	
	private float frame=0;
	private int numFrames =10;
	private float frameSpeed = .25f;
	
	public Player(){
		this(100,0);
	}
	
	public Player(int x,int y){
		super(x,y,64,64,"player_anim");
		startx = x;
		starty = y;
	}
	
	public void gameLoop(){
		getInputMovement();
		checkCollisions();
		applyVelocity();
		updateFrame();
		
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
		
		if(object instanceof Bullet){
			bounds.setLocation(startx, starty);
			object.delete();
			
		}
	}
	
	private void updateFrame(){
		if(dx<0 || (dx ==0 && dy!=0)){
			frame += frameSpeed;
			if(frame >= numFrames){
				frame -= numFrames;
			}
		}
		if(dx>0){
			frame -= frameSpeed;
			if(frame <0){
				frame += numFrames;
			}
		}
	}	

	public void gainPoint(){
		points++;
	}
	
	public void draw(Graphics g){
		//super.draw(g);
		g.drawImage(Resources.getInstance().getImage(imgName),
				(int)bounds.getMinX(),(int)bounds.getMinY(),
				(int)bounds.getMaxX(),(int)bounds.getMaxY(),
				(int)frame * bounds.width,0,
				(int)(frame+1)* bounds.width, bounds.height,
				null);
		
		g.setColor(Color.WHITE);
		Font font = new Font(null,Font.BOLD,18);
		g.setFont(font);
		g.drawString(points + "",10,15 );
	}
	
	
	
	
	
}
