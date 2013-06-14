package gruppe63_dungeon_crawler;

public class Item extends Elements {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static String name;

	public Item(int x, int y, int xDim, int yDim, String name) {
		super(x, y, xDim, yDim);

		this.x = x;
		this.y = y;
		this.type = 1;
		// fehlt noch was
		this.name = name;
	}

	public void pickUp() {
	}

	/*
	 * protected void init (int x, int y, String name){
	 * 
	 * this.x = x; this.y= y; this.type= 1; //fehlt noch was this.name = name; }
	 */

}
