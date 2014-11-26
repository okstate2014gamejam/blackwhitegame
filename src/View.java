import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

public class View extends JFrame {
	
	private Model model;
	GamePanel panel;
	
	public View(Model m) {
		model = m;
		
		// frame settings
		setTitle(model.gameTitle);
		setSize(model.size[0]+6, model.size[1]+26);
		//setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-getSize().width/2, dim.height/2-getSize().height/2); // centered on the screen
		
		// create a panel
		panel = new GamePanel();
		add(panel);
		setVisible(true);
	}
	
	class GamePanel extends JPanel {
		
		GamePanel() {
			addKeyListener(new KeyControls(model));
			setFocusable(true);
			requestFocusInWindow();
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			drawSprites(g);
		}
		
		private void drawSprites(Graphics g) {
			List<Sprite> toDraw = model.getSprites();
			for(Sprite i : toDraw) {
				g.drawImage(i.getImage(), (int)Math.round(i.x*model.size[0]), (int)Math.round(i.y*model.size[1]), 
							(int)Math.round(i.width), (int)Math.round(i.height), this);
			}
		}
	}
}
