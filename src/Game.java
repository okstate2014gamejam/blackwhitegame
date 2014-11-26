public class Game {
	public static void main(String[] args) {
		//Load the database
		SpriteFactory.loadDatabase();
		Model m = new Model();
		View v = new View(m);
		new Controller(m, v);
	}
}
