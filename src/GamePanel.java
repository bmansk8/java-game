
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel  {
	
	KeyboardController keyboard;
	
	int player_x=10;
	int player_y=10;
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame frame = new JFrame("game");
		frame.setSize(600,500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		GamePanel panel = new GamePanel();
		
		frame.add(panel);
		
		frame.setVisible(true);
	}
	
	private GamePanel(){
		
		keyboard = new KeyboardController();
		this.addKeyListener(keyboard);
		setFocusable(true);
		requestFocus();
		new Thread(){
			public void run(){
				try{
					while(true){
						gameLoop();
						repaint();
						Thread.sleep(33);
					}
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}.start();

	}

	private void gameLoop(){
		double sqrt2 = Math.sqrt(2);
		boolean verticalInput = keyboard.isUpHeld()	|| keyboard.isDownHeld();
		boolean horizontalInput = keyboard.isLeftHeld()	|| keyboard.isRightHeld();
			
		if(keyboard.isUpHeld()){
			if(!horizontalInput){
			player_y-=6;
			}else{
			player_y-= (int)(6 / sqrt2);	
			}
		}
		
		if(keyboard.isDownHeld()){
			if(!horizontalInput){
			player_y+=6;
			}else{
			player_y+= (int)(6 / sqrt2);	
			}
		}
		
		if(keyboard.isLeftHeld()){
			if(!verticalInput){
			player_x-=6;
			}else{
			player_x-= (int)(6 / sqrt2);	
			}
		}
		
		if(keyboard.isRightHeld()){
			if(!verticalInput){
			player_x+=6;
			}else{
			player_x+= (int)(6 / sqrt2);	
			}
		}
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.black);
		g.fillRect(player_x,player_y,50,50);
		
	}

	
	
}
