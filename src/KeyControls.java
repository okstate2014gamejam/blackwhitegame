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
			case KeyEvent.VK_RIGHT: model.right = true; break;
			case KeyEvent.VK_LEFT: model.left = true; break;
			case KeyEvent.VK_DOWN: model.down = true; break;
			case KeyEvent.VK_UP: model.up = true; break;
			case KeyEvent.VK_SHIFT: model.shift = true; break;
			default: break;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
			case KeyEvent.VK_RIGHT: model.right = false; break;
			case KeyEvent.VK_LEFT: model.left = false; break;
			case KeyEvent.VK_DOWN: model.down = false; break;
			case KeyEvent.VK_UP: model.up = false; break;
			case KeyEvent.VK_SHIFT: model.shift = false; break;
			default: break;
		}
	}
}
