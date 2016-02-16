import java.awt.Color;
import java.awt.Graphics;

public class Player {
	private int x, y;
	private int dx,dy;
	private int width,height;
	
	private int startx,starty;
	
	public Player(){
		this(100,0);
	}
	
	public Player(int x,int y){
		this.x = x;
		this.y = y;
		width = 50;
		height = 50;
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
		Solid[] solids = GamePanel.getInstance().getSolids();
		int myLeft = x + dx;
		int myRight = myLeft + width;
		int myTop = y + dy;
		int myBot = myTop + height;
		
		for(Solid solid : solids){
			int sLeft = solid.getX();
			int sRight = sLeft + solid.getWidth();
			int sTop = solid.getY();
			int sBot = sTop + solid.getHeight();
			
			boolean xOverlaps = myLeft < sRight && sLeft < myRight;
			boolean yOverlaps = myTop < sBot && sTop < myBot;
			if(xOverlaps && yOverlaps){
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
		}
		Enemy[] enemies =GamePanel.getInstance().getEnemies();
		for(Enemy enemy : enemies){
			int sLeft = enemy.getX();
			int sRight = sLeft + enemy.getWidth();
			int sTop = enemy.getY();
			int sBot = sTop + enemy.getHeight();
			
			boolean xOverlaps = myLeft < sRight && sLeft < myRight;
			boolean yOverlaps = myTop < sBot && sTop < myBot;
			if(xOverlaps && yOverlaps){
				x = startx;
				y = starty;
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
	
	
	public int getX(){
		return x;
	}

	public int getY() {
		return y;
	}
	
	
}
