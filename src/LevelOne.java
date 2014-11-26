import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.File;

public class LevelOne implements Scene {
	/** Test level.
	 * Has defined boundaries and objects, enemies, etc. */
	
	private BGM bgm = new BGM();
	private Model model;
	
	// 'sprites' are the sprites that are somewhere in this level, even if not visible or on the screen right now
	private List<Sprite> sprites = new ArrayList<Sprite>(),
		visibleSprites = new LinkedList<Sprite>();
	
	private int playerStartPosX = 0, playerStartPosY = 0, 
		boundaryX = 0, boundaryY = 0, 		// map boundaries
		cameraStartX = 0, cameraStartY = 0; // change if the camera doesn't start in the top-left corner of the map
	
	public LevelOne(Model m) {
		model = m;
		sprites.add(SpriteFactory.createSprite("player"));
		sprites.add(new Sprite(300, 500, Sprite.Type.OBJECT, new File("resources/sprite data/images/standsprite.png")));
		visibleSprites.addAll(sprites);
	}
	
	/** Returns a list of the visible sprites.
	 */
	public List<Sprite> getSprites() {
		return visibleSprites;
	}
	
	/** Updates the game information. Checks collisions and moves sprites. 
	 */
	public void timerEvent() {
		getDirections();
		checkCollisions();
		moveSprites();
	}
	
	private void getDirections() {
		if (model.right) {
			sprites.get(0).xAccel = 1;
		} else if (model.left) {
			sprites.get(0).xAccel = -1;
		} else {
			sprites.get(0).xAccel = 0;
		}
		if (model.up) {
			sprites.get(0).yAccel = -1;
		} else if (model.down) {
			sprites.get(0).yAccel = 1;
		} else {
			sprites.get(0).yAccel = 0;
		}
		// gravity
		sprites.get(0).yAccel += 0.5;
		// terminal velocity horizontal
		if (Math.abs(sprites.get(0).xSpeed)>5) {
			sprites.get(0).xSpeed = 5*Math.abs(sprites.get(0).xSpeed)/sprites.get(0).xSpeed;
		}
		// terminal velocity vertical
		if (Math.abs(sprites.get(0).ySpeed)>5) {
			sprites.get(0).ySpeed = 5*Math.abs(sprites.get(0).ySpeed)/sprites.get(0).ySpeed;
		}
		// stop at walls
		if (sprites.get(0).x<=0 && sprites.get(0).xSpeed<0) {
			sprites.get(0).xSpeed = 0;
		}
		if (sprites.get(0).x>700 && sprites.get(0).xSpeed>0) {
			sprites.get(0).xSpeed = 0;
		}
		// stop at floor
		if (sprites.get(0).y>550 && sprites.get(0).ySpeed>0) {
			sprites.get(0).ySpeed = 0;
		}
	}
	
	/** Moves all sprites in 'sprites' by calling their move method.
	 */
	private void moveSprites() {
		for (Sprite i : sprites) {
			i.move();
		}
	}
	
	/** Checks if sprites collide. For those that do, calls the collided method.
	 */
	private void checkCollisions() {
		int i, j, tot;
		Sprite a, b;
		for (i=0, tot = visibleSprites.size(); i<tot; i++) {
			a = new Sprite(visibleSprites.get(i));
			a.move();
			for (j=i+1; j<tot; j++) {
				b = new Sprite(visibleSprites.get(j));
				b.move();
				if (a.collidesWith(b)) {
					collided(visibleSprites.get(i), visibleSprites.get(j));
				}
			}
		}
	}
	
	private void collided(Sprite a, Sprite b) {
		System.out.println("collided");
		a.xSpeed = 0;
		a.ySpeed = 0;
		b.xSpeed = 0;
		b.ySpeed = 0;
	}
}
