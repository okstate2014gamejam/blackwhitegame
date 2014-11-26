import java.util.List;
import java.util.ArrayList;

public interface Scene {
	/** Can be a platforming level, a title screen, a cutscene, etc. */
	
	/** Updates the game information.
	 */
	public void timerEvent();
	
	/** Returns a list of the available sprites.
	 */
	public List<Sprite> getSprites();
}
