
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel  {
	
	Player player;
	
	
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
		player = new Player();
		
		this.addKeyListener(KeyboardController.getInstance());
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
		player.gameLoop();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		player.draw(g);
		
	}

	
	
}
