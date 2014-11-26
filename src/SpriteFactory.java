import java.util.*;
import java.io.*;

import org.yaml.snakeyaml.*;

public class SpriteFactory {
	
	private static HashMap<String, HashMap<String, Object>> spriteData = new HashMap<String, HashMap<String, Object>>();
	
	private static final String[] imageSet = new String[] {"imagesIdle", "imagesLeft", "imagesRight", "imagesInAirLeft", "imagesInAirRight"};
	
	public static boolean loadDatabase() {
		
		Yaml yaml = new Yaml();
		
		try {
			File dir = new File("resources/sprite data/config");
			
			for(File file : dir.listFiles()) {
				InputStream input = new FileInputStream(file);
				
				if (file.getName().startsWith(".")) {
					continue;
				}
				
				HashMap<String, Object> tempMap = (HashMap<String, Object>) yaml.load(input);
				spriteData.put((String) tempMap.get("name"), tempMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public static Sprite createSprite(String name) {
		
		HashMap<String, Object> tempMap = SpriteFactory.spriteData.get(name.toLowerCase());
		Sprite.Type type = null;
		
		switch ((String) tempMap.get("type")) {
			case "PLAYER": type = Sprite.Type.PLAYER; break;
			case "ENEMY" : type = Sprite.Type.ENEMY; break;
			case "ITEM"  : type = Sprite.Type.ITEM; break;
			case "ZONE"  : type = Sprite.Type.ZONE; break;
			default : type = Sprite.Type.OBJECT;
		}
		
		Sprite toReturn = new Sprite((double) tempMap.get("xStart"), (double) tempMap.get("yStart"), type);
		
		for (int i = 0; i < SpriteFactory.imageSet.length; i++) {
			ArrayList<String> tempList = (ArrayList<String>) tempMap.get(SpriteFactory.imageSet[i]);
			System.out.println("TempList: " + tempList);
			for (String s : tempList) {
				Sprite.Movement movement = null;
				
				System.out.println("Filepath: " + s);
				
				if (i == 0) {
					movement = Sprite.Movement.NONE;
				} else if (i == 1 || i == 3) {
					movement = Sprite.Movement.LEFT;
				} else {
					movement = Sprite.Movement.RIGHT;
				}
				
				toReturn.addImage(new File(s), movement, (i > 2 ? true : false));
			}
		}
		
		return toReturn;
	}
}