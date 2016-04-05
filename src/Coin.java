import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Coin extends GameObject {
	
	public Coin(int x, int y){
		super(x,y,32,32,"coin");
	}
	
	public void gameLoop(){
		checkCollisions();
	}
	
	protected void collideWith(GameObject object){
		if(object instanceof Player){
			Player p = (Player)object;
			p.gainPoint();
			delete();
		}
	}
	
}
