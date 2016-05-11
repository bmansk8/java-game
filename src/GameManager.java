import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {
	private HashMap <Integer,GameObject> objects;
	private ArrayList<GameObject> objectsToRemove;
	private ArrayList<GameObject> objectsToAdd;
	
	private GamePanel panel;
	
	private  HashMap<String,Level> levels;
	
	
	//singleton
		private static GameManager instance;
			
		public static GameManager getInstance(){
			return instance;
		}
	////////
			
			
	public GameManager(GamePanel panel){
		this.panel=panel;
		
		instance = this;
		
		objectsToRemove = new ArrayList<GameObject>();
		objectsToAdd = new ArrayList<GameObject>();
		objects = new HashMap<Integer,GameObject>();
		
		initLevel();
		
		new Thread(){
			public void run(){
				try{
					while(true){
						gameLoop();
						panel.repaint();
						Thread.sleep(33);
					}
				}catch(InterruptedException e){
					e.printStackTrace();
				}
			}
		}.start();
		
	}
	
	
	private void initLevel(){
		levels = new HashMap<String, Level>();
		
		char[][] lvlData = {
				{'-','-','-','-','-','-','-','-','-','-','-','-','-',},
				{'-','P','-','-','-','-','-','-','-','-','-','-','-',},
				{'-','-','-','-','-','-','-','-','-','-','-','-','-',},
				{'-','S','-','-','H','-','S','-','-','-','H','-','S',},
				{'-','-','-','-','-','-','-','S','-','S','S','S','S',},
				{'-','V','-','C','C','-','V','S','-','S','C','O','S',},
				{'-','-','-','-','-','-','-','S','-','S','C','C','S',},
				{'-','-','-','-','-','-','-','-','-','-','-','-','S',},
				{'-','S','-','-','H','-','S','S','S','S','S','S','S',}
		};
		
		levels.put("level1",new Level(lvlData));
		levels.get("level1").load();
		
		
		char[][] lvlData2 = {
				{'-','-','-','-','-','-','-','-','-','-','-','-','-',},
				{'-','P','-','-','-','-','-','-','-','-','-','-','-',},
				{'-','-','-','-','-','-','-','-','-','-','-','-','-',},
				{'S','-','-','-','H','-','S','-','-','-','H','-','S',},
				{'-','S','-','-','-','-','-','S','-','S','S','S','S',},
				{'C','S','-','C','C','-','V','S','-','S','C','C','S',},
				{'-','S','S','-','-','-','-','S','-','S','C','C','S',},
				{'-','-','-','-','-','-','-','-','-','-','-','-','S',},
				{'S','-','-','-','H','-','S','S','S','S','S','S','S',}
		};
		
		levels.put("level2",new Level(lvlData2));
		
	
	}
	
	public HashMap<Integer,GameObject> getObjects(){
		return objects;
	}
	
	public void addObject(GameObject obj){
		objectsToAdd.add(obj);
	}
	
	public void removeObject(GameObject obj){
		objectsToRemove.add(obj);
	}
	
	public void clearRoom(){
		for(GameObject object :objects.values()){
			objectsToRemove.add(object);
		}
	}
	
	public void goToLevel(String lvl){
		levels.get(lvl).load();
	}
	
	private void gameLoop(){
			
			for(GameObject object: objects.values()){
				object.gameLoop();
			}
			
			for(GameObject object : objectsToRemove){
				objects.remove(object.getId());
			}
			
			objectsToRemove.clear();
			

			for(GameObject object : objectsToAdd){
				objects.put(object.getId(),object);
			}
			objectsToAdd.clear();
			
		}
	
	
	public void draw(Graphics q){
		q.setColor(Color.cyan);
		q.fillRect(0, 0, panel.getWidth(), panel.getHeight());
		
		
		for(GameObject object : objects.values()){
			object.draw(q);
		}	
	}
	
	
	
}
