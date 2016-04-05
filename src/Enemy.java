import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Enemy extends GameObject {
	
	public Enemy(int x,int y){
		this(x,y,true);
	}
	
    public Enemy(int x,int y, boolean horizontal){
    	super(x,y,64,64,"enemy");
    	dx =(horizontal)? 4 : 0;
    	dy =(horizontal)? 0 : 4;

    }
    
    
    public void gameLoop(){
    	checkCollisions();
    	applyVelocity();
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
				dx = -dx;
			}else{
				if(bounds.getMinY() < obounds.getMinY()){//collsion with top of solid
					bounds.y = (int)obounds.getMinY() - bounds.height;
				}else{//collsion w/ bot of solid
					bounds.y = (int)obounds.getMaxY();
				}
				dy = -dy;
			}
		} 
	}    
    
}
