import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class Enemy extends GameObject {
	
	public Enemy(int x,int y){
		super(x,y,48,48);
		dx = 4;
		dy = 0;
	}
	
    public Enemy(int x,int y, boolean horizontal){
    	super(x,y,48,48);
    	dx =(horizontal)? 4 : 0;
    	dy =(horizontal)? 0 : 4;

    }
    
    
    public void gameLoop(){
    	checkCollisions();
    	applyVelocity();
    }
	
    protected void collideWith(GameObject object){
		/* if(object instanceof Solid){
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
		} */
	}
    
    
    public void draw(Graphics g){
    	g.setColor(Color.BLUE);
    	g.fillRect(bounds.x,bounds.y,bounds.width,bounds.height);
    }
    
    
    
}
