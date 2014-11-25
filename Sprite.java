import java.io.File;
import java.io.Serializable;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.LinkedList;
import java.util.List;

public class Sprite implements Serializable {
	/** Represents anything visible that isn't a background image, 
	 * 	e.g. characters, walls, objects. 
	 * 
	 * 	Note: Using BufferedImage ties sizes to pixels and the view to something that can handle buffered images. It would be better MVC architecture to avoid that, but this is more convenient for a platformer game.
	 */
	 
	enum Movement {
		NONE, LEFT, RIGHT
	}
	
	public static final long serialVersionUID = 1L;
	public double x = 0, y = 0, xSpeed = 0, ySpeed = 0, xAccel = 0, yAccel = 0, height = 0, width = 0;
	// having height/width different from imageHeight/imageWidth is useful if the sprite's image size doesn't match its actual dimensions.
	public int currentFrame = 0;
	
	private LinkedList<BufferedImage> currentFrames,
			idleFrames = new LinkedList<BufferedImage>(), 
			leftFrames = new LinkedList<BufferedImage>(), 
			rightFrames = new LinkedList<BufferedImage>();
	
	/** Creates a sprite to be initialized later. 
	 */
	public Sprite() { 
		currentFrames = idleFrames;
	}
	
	/** Creates a sprite with a given images, whose position will be initialized later.
	 */
	public Sprite(File... imagePath) {
		currentFrames = idleFrames;
		for (File i : imagePath) {
			addImage(i);
		}
		height = getImageHeight();
		width = getImageWidth();
	}
	
	/** Creates a sprite with the given images and x/y coordinates.
	 */
	public Sprite(double x, double y, File... imagePath) {
		currentFrames = idleFrames;
		for (File i : imagePath) {
			addImage(i);
		}
		height = getImageHeight();
		width = getImageWidth();
		this.x = x;
		this.y = y;
	}
	
	/** Adds the given file to the sprite's idle animation.
	 *
	 * @return true if the sprite's image was set
	 */
	public boolean addImage(File imagePath) {
		try {
			idleFrames.add(ImageIO.read(imagePath));
			return true;
		} catch (IOException e) {
			return false;
		} catch (IllegalArgumentException e) {
			return false;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			return false;
		}
	}
	
	/** Sets the sprite's image to the given file.
	 *
	 * @return true if the sprite's image was set
	 */
	public boolean addImage(File imagePath, Movement m) {
		try {
			switch (m) {
			case LEFT: leftFrames = leftFrames; break;
			case RIGHT: rightFrames.add(ImageIO.read(imagePath)); break;
			default: idleFrames.add(ImageIO.read(imagePath));
			}
			return true;
		} catch (IOException e) {
			return false;
		} catch (IllegalArgumentException e) {
			return false;
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			return false;
		}
	}
	
	/** Gets the sprite's image.
	 * 
	 * @return BufferedImage
	 */
	public BufferedImage getImage() {
		return currentFrames.get(currentFrame);
	}
	
	private int getImageWidth() {
		return getImage().getWidth();
	}
	
	private int getImageHeight() {
		return getImage().getHeight();
	}
	
	private List<BufferedImage> getCurrentFrames() {
		return currentFrames;
	}
	
	private void setCurrentFrames(Movement m) {
		switch (m) {
		case LEFT: if (leftFrames.size() > 0) currentFrames = leftFrames; break;
		case RIGHT: if (rightFrames.size() > 0) currentFrames = rightFrames; break;
		default: currentFrames = idleFrames;
		}
	}
	
	/** Moves the sprite by its speed. Adds the sprite's speed to its coordinates, then adds acceleration to speed.
	 */
	public void move() {
		x += xSpeed;
		y += ySpeed;
		xSpeed += xAccel;
		ySpeed += yAccel;
		if (Math.round(xSpeed) > 0) {
			setCurrentFrames(Movement.RIGHT);
		} else if (Math.round(xSpeed) < 0) {
			setCurrentFrames(Movement.LEFT);
		} else {
			setCurrentFrames(Movement.NONE);
		}
		currentFrame = (currentFrame + 1) % getCurrentFrames().size();
		height = getImageHeight();
		width = getImageWidth();
	}
	
	/** Gets the vertical position of the top edge of this sprite.
	 *
	 * @return the double value of the top edge's vertical position
	 */
	public double getTopEdge() {
		return y;
	}
	
	/** Gets the horizontal position of the left edge of this sprite.
	 *
	 * @return the double value of the left edge's horizontal position
	 */
	public double getLeftEdge() {
		return x;
	}
	
	/** Gets the horizontal position of the right edge of this sprite.
	 *
	 * @return the double value of the right edge's horizontal position
	 */
	public double getRightEdge() {
		return x+width;
	}
	
	/** Gets the vertical position of the bottom edge of this sprite.
	 *
	 * @return the double value of the bottom edge's vertical position
	 */
	public double getBottomEdge() {
		return y+height;
	}
	
	/** Checks if this sprite's image overlaps the image of sprite b.
	 *
	 * @return true if the images overlap
	 */
	public boolean collidesWith(Sprite b) {
		boolean xcollide = false;
		
		// check horizontal collision
		if (getLeftEdge() >= b.getLeftEdge() &&
			getLeftEdge() <= b.getRightEdge()) {
			// collision on my left
			xcollide = true;
		} else if (getRightEdge() >= b.getLeftEdge() &&
				   getRightEdge() <= b.getRightEdge()) {
			// collision on my right
			xcollide = true;
		}
		
		if (!xcollide) {
			return false;
		}
		
		// check vertical collision
		if (getTopEdge() >= b.getTopEdge() &&
			getTopEdge() <= b.getBottomEdge()) {
			// collision on top
			return true;
		} else if (getBottomEdge() >= b.getTopEdge() &&
				   getBottomEdge() <= b.getBottomEdge()) {
			// collision on bottom
			return true;
		}
		
		// no collision found
		return false;
	}
}
