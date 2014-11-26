import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;

public class Sound {
	// Create a new Sound object for each sound you want to play.
	
	private Clip c;
	private boolean playable = false;
	
	public Sound(File input) {
		try {
			c = AudioSystem.getClip();
			c.open(AudioSystem.getAudioInputStream(input));
			playable = true;
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void play() {
		if (!playable) {
			return;
		}
		c.setFramePosition(0);
		c.start();
	}
}
