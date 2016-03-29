import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Coin extends GameObject {

	private BufferedImage img;
	
	public Coin(int x, int y){
		super(x,y);
		
		try {
			img = ImageIO.read(new File("img/coin.png"));
		}catch(IOException e){
			e.printStackTrace();
		}
		
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
	
	public void draw(Graphics h){
		h.drawImage(img, bounds.x, bounds.y, null);
	}
}
