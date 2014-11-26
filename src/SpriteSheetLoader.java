/** credit _Al3x */
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheetLoader {
	
   BufferedImage spriteSheet;   
   int width;
   int height;
   int rows;
   int columns;
   BufferedImage[] sprites;
   
   public SpriteSheetLoader(File sheet, int rows, int columns) throws IOException {
	  spriteSheet = ImageIO.read(sheet);
      this.rows = rows;
      this.columns = columns;
      this.width = spriteSheet.getWidth()/columns;
      this.height = spriteSheet.getHeight()/rows;
      sprites = new BufferedImage[rows * columns];
      
      for(int i = 0; i < columns; i++) {
         for(int j = 0; j < rows; j++) {
			 System.out.println(((i * columns) + j)+"\t"+i * width+"\t"+ j * height+"\t"+ width+"\t"+ height);
            sprites[(j * columns) + i] = spriteSheet.getSubimage(i * width, j * height, width, height);
         }
      }
   }
   
   public BufferedImage[] getSprites() {
	   return sprites;
   }
   
   /* for testing
   public class SSPanel extends JPanel {
	   BufferedImage[] s;
	   SSPanel(BufferedImage[] sprites) {
		   s = sprites;
			setFocusable(true);
			requestFocusInWindow();
			repaint();
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			for (int i=0; i<s.length; i++) {
				g.drawImage(s[i], i*60, 0, s[i].getWidth(), s[i].getHeight(), this);
			}
		}
   }
   
   
   public static void main(String[] args) {
	   try {
		   new SpriteSheetLoader(new File("dark-hero-sprites.png"), 4, 6);
		}catch (IOException e) {
			e.printStackTrace();
		}
   }*/
}
