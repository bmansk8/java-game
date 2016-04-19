
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GamePanel extends JPanel  {
	
	char[][] level = {
			{'-','-','-','-','-','-','-','-','-','-','-','-','-',},
			{'-','P','-','-','-','-','-','-','-','-','-','-','-',},
			{'-','-','-','-','-','-','-','-','-','-','-','-','-',},
			{'-','S','-','-','H','-','S','-','-','-','H','-','S',},
			{'-','-','-','-','-','-','-','S','-','S','S','S','S',},
			{'-','V','-','C','C','-','V','S','-','S','C','C','S',},
			{'-','-','-','-','-','-','-','S','-','S','C','C','S',},
			{'-','-','-','-','-','-','-','S','-','-','-','-','S',},
			{'-','S','-','-','H','-','S','S','S','S','S','S','S',}
	};
	
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
		frame.setSize(840,600);
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
		
		initLevel();
		
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
	
	private void initLevel(){
		for(int i =0; i< level.length;i++){
			for(int j=0;j<level[i].length; j++){
				switch(level[i][j]){
				case 'P':  addObject(new Player(j*64,i*64));break;
				case 'C':  addObject(new Coin(j*64,i*64)); break;
				case 'V':  addObject(new Enemy(j*64,i*64,false)); break;
				case 'H':  addObject(new Enemy(j*64,i*64)); break;
				case 'S':  addObject(new Solid(j*64,i*64)); break;
				}
			}
		}
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
