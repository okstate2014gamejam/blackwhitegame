import java.util.List;
import java.util.ArrayList;

public class Model {
	
	// basic info
	public int[] size = {800, 600};
	public int fps = 60;
	public String gameTitle = "Game";
	public boolean right = false, left = false, up = false, down = false, shift = false;
	
	// current display info
	private Scene currentScene = new LevelTwo(this);
	
	/** Called by the controller when a timer event is fired. 
	 */
	public void timerFired() {
		currentScene.timerEvent();
	}
	
	/** Returns the visible sprites for the current view.
	 * 	Called by the view when it draws the screen.
	 * 
	 * @return the list of sprites to draw
	 */
	public List<Sprite> getSprites() {
		return currentScene.getSprites();
	}
}
