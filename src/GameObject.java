import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class GameObject {
    protected String imgName;
	protected Rectangle bounds;
	protected int dx, dy;
	protected int id;
	
	private static int next_id = 0;
	
	public GameObject(int x,int y){
		this(x,y,64,64);
	}
	
	public GameObject(int x,int y,int width,int height){
		this(x,y,width,height,"");
	}
	
	public GameObject(int x,int y, String imgName){
		this (x,y,64,64,imgName);
	}
	
	public GameObject(int x,int y,int width,int height, String imgName){
		this.imgName = imgName;
		bounds = new Rectangle(x,y,width,height);
		dx=0;
		dy=0;
		id = getNewID();
	}
	
	public void gameLoop(){
		
	}
	
	public void draw(Graphics g){
		g.drawImage(Resources.getInstance().getImage(imgName), bounds.x, bounds.y, null);
	}
	
	
	//old way 
	public int getX() {
		return bounds.x;
	}

	public int getY() {
		return bounds.y;
	}

	public int getWidth() {
		return bounds.width;
	}

	public int getHeight() {
		return bounds.height;
	}

	public Rectangle getBounds(){
		return bounds;
	}
	
	
	protected void checkCollisions(){
		HashMap<Integer,GameObject> objects = GamePanel.getInstance().getObjects();
		
		Rectangle newBounds = new Rectangle(bounds);
		newBounds.translate(dx,dy);
		
		for(GameObject object : objects.values()){
			Rectangle oBounds = object.getBounds();
			
			if(newBounds.intersects(oBounds)){
				collideWith(object);
			}
		}
			
	}
	
	
	protected void collideWith(GameObject object){
		
	}
	
	
	protected void applyVelocity(){
		bounds.translate(dx,dy);
	}
	
	protected void delete(){
		GamePanel.getInstance().removeObject(this);
	}
	
	private int getNewID(){
		return next_id++;
		
	}

	public int getId() {
		return id;
	}
	
	
	
}
