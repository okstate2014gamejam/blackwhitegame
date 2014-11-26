import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Controller implements ActionListener {
	
	private int fps;
	private int delay;
	private Model model;
	private View view;
		
	public Controller(Model m, View v) {
		model = m;
		view = v;
		this.fps = m.fps;
		delay = 1000/fps;
		Timer myTimer = new Timer(delay, this);
		myTimer.start();
	}
	
	public void actionPerformed(ActionEvent e) {
		model.timerFired();
		view.panel.repaint();
	}
}
