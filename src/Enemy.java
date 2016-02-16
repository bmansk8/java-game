import java.awt.Color;
import java.awt.Graphics;

public class Enemy {
	
	private int x,y;
	private int width,height;
	private int dx,dy;
	
	public Enemy(int x,int y){
		this.x = x;
		this.y = y;
		width = 48;
		height = 48;
		dx = 4;
		dy = 0;
	}
	
    public Enemy(int x,int y, boolean horizontal){
    	
    	dx =(horizontal)? 4 : 0;
    	dy =(horizontal)? 0 : 4;
    	
    	this.x = x;
    	this.y = y;
    	
    	width =  48;
    	height = 48;
    }
    
    
    public void gameLoop(){
    	checkCollisions();
    	move();
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
					dx = -dx;
				}else{
					if(myTop < sTop){//collsion with top of solid
						y = sTop - height;
					}else{//collsion w/ bot of solid
						y = sBot;
					}
					dy = -dy;
				}
			}
		}
    }
    
    private void move(){
    	x+= dx;
    	y+= dy;
    }
    
    public void draw(Graphics g){
    	g.setColor(Color.BLUE);
    	g.fillRect(x,y,width,height);
    }

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
    
    
    
}
