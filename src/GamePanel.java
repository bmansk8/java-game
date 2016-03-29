
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel  {
	
	private HashMap <Integer,GameObject> objects;
	private ArrayList<GameObject> objectsToRemove;
	
	
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
		frame.setSize(700,600);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		GamePanel panel = GamePanel.getInstance();
		instance = panel;
		
		
		frame.add(panel);
		
		frame.setVisible(true);
	}
	
	private GamePanel(){
		instance = this;
		
		objectsToRemove = new ArrayList<GameObject>();
		objects = new HashMap<Integer,GameObject>();
		
		addObject(new Player() );
		
		
		addObject ( new Solid(400,400,64,64));
		addObject ( new Solid(100,400,64,64));
		addObject ( new Solid(100,100,64,64));
		
		addObject ( new Enemy (200,400));
		addObject ( new Enemy (100,300,false));
		
		addObject( new Coin(200,400));
		addObject( new Coin(100,300));
		
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
		
		for(GameObject object: objects.values()){
			object.gameLoop();
		}
		
		for(GameObject object : objectsToRemove){
			objects.remove(object.getId());
		}
		
		objectsToRemove.clear();
	}
	
	@Override
	protected void paintComponent(Graphics p) {
		p.setColor(Color.cyan);
		p.fillRect(0, 0, getWidth(), getHeight());
		
		
		for(GameObject object : objects.values()){
			object.draw(p);
		}
		
	}
	
	public HashMap<Integer,GameObject> getObjects(){
		return objects;
	}
	
	private void addObject(GameObject obj){
		objects.put(obj.getId(),obj);
	}
	
	public void removeObject(GameObject obj){
		objectsToRemove.add(obj);
	}
	
}
