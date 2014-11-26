import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;

public class BGM {
	// Create a CGM object once, then change it (with playNew) for each BGM.
	
	private Clip c;
	private boolean playable = false;

	public BGM() {
		try {
			c = AudioSystem.getClip();
			playable = true;
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} 
	}
	
	/** Starts playing a new background music file.
	 * 
	 * @return true if the BGM was started successfully
	 */
	public boolean playNew(File input) {
		if (!playable) {
			return false;
		}
		try {
			// in case a BGM is already playing
			c.stop();
            c.close();
		} catch (Exception e) { }
		try {
			c.open(AudioSystem.getAudioInputStream(input));
			c.setLoopPoints(0, c.getFrameLength()-1);
			c.loop(Clip.LOOP_CONTINUOUSLY); // starts playing the music
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		}		
		return false;
	}
}
