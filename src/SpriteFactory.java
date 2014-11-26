import java.util.*;
import java.io.*;
import org.yaml.snakeyaml.*;

public class SpriteFactory {
	
	private static HashMap<String, HashMap<String, Object>> spriteData = new HashMap<String, HashMap<String, Object>>();
	
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

		return new Sprite((double) tempMap.get("xStart"), (double) tempMap.get("yStart"), type, (List<String>) tempMap.get("images"));
	}
}