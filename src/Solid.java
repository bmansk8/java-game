import java.awt.Color;
import java.awt.Graphics;

public class Solid extends GameObject {
	
	public Solid(int x,int y){
		super(x,y,64,64);
	
	}
	
	public Solid(int x,int y,int width,int height){
	super(x,y,width,height,"solid");
		
	}
	
}
