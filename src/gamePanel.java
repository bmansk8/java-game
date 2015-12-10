
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
	
	boolean right=true;
	boolean down=false;
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
		
		if(right == true){
			player_x +=5;
		}else{
			player_x -=5;
		}
		
		if(down == true){
			player_y +=5;
		}else{
			player_y -=5;
		}
		
		if(player_x+50 > getWidth()){
			right = false;
		}
		
		if(player_x < 0){
			right = true;
		}
		
		if(player_y < 0){
			down = true;
		}
		
		if(player_y+50 > getHeight()){
			down = false;
		}
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		//g.setColor(Color.cyan);
		//g.fillRect(0, 0, getWidth(), getHeight());
		
		g.setColor(Color.black);
		g.fillRect(player_x,player_y,50,50);
		
	}
	
	
}
