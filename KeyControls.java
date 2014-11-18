import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class KeyControls implements KeyListener {
	
	private Model model;
	
	public KeyControls(Model m) {
		model = m;
	}
	
	public void keyTyped(KeyEvent e) { }
	
	public void keyPressed(KeyEvent e) { 
		switch(e.getKeyCode()) {
			// do something
			default: break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			// invert results of keyPressed
			default: break;
		}
	}
}
