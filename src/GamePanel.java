
import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel  {
	
	private Player player;
	private Solid[] solids;
	
	private static GamePanel instance;
		
		public static GamePanel getInstance(){
			if(instance == null){
				instance = new GamePanel();
			}
			return instance;
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		JFrame frame = new JFrame("game");
		frame.setSize(600,500);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		GamePanel panel = new GamePanel();
		instance = panel;
		
		
		frame.add(panel);
		
		frame.setVisible(true);
	}
	
	private GamePanel(){
		player = new Player();
		
		solids = new Solid[1];
		solids[0] = new Solid(400,300,64,64);
		/*solids[1] = new Solid(50,60,80,90);
		solids[2] = new Solid(100,100);
		solids[3] = new Solid(200,250);
		solids[4] = new Solid(300,300); */
		
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
	protected void paintComponent(Graphics p) {
		p.setColor(Color.cyan);
		p.fillRect(0, 0, getWidth(), getHeight());
		
		player.draw(p);
		
		for(int i=0;i<solids.length;i++){
			solids[i].draw(p);
			
		}
		
	}

	public Solid[] getSolids() {
		return solids;
	}
	
}
