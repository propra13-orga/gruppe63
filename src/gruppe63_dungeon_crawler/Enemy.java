package gruppe63_dungeon_crawler;

public class Enemy extends Elements { // Einfache Kopie von der Klasse Player
										// als Platzhalter.

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Room room;
	private int damage = 1;

	public Enemy(int x, int y, Room room) {
		super(x, y, 30, 30);
		this.room = room;
	}

	public void move() {
		x = this.getX();
		y = this.getY();

		if (room.Environment(x + vx, y, xDim, yDim) != 1) {
			x = x + vx;
			this.setLocation(x, y);
		}

		if (room.Environment(x, y - vy, xDim, yDim) != 1) {
			y = y - vy;
			this.setLocation(x, y);
		}
		room.status();
	}

	public void setMovX(int vx) {
		this.vx = vx;
	}

	public void setMovY(int vy) {
		this.vy = vy;
	}

	public int getMovX() {
		return vx;
	}

	public int getMovY() {
		return vy;
	}

	public int getDamage() {
		return damage;

	}

}
