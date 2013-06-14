package gruppe63_dungeon_crawler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Fireball extends Elements {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Room room;

	private int collision = 0;
	private int hits = 0;

	private int damage = 50;
	Game game;

	public Fireball(int x, int y, Room room) {
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

		if (room.Environment(x + vx, y, xDim, yDim) == 1) {
			collision = 1;
		}

		if (room.Environment(x, y - vy, xDim, yDim) != 1) {
			y = y - vy;
			this.setLocation(x, y);
		}

		if (room.Environment(x, y - vy, xDim, yDim) == 1) {
			collision = 1;
		}

		room.status();
	}

	public void collision(Player player) {
		Rectangle rf = new Rectangle(getXmiddle(), getYmiddle(), 30, 30);
		Rectangle rh = new Rectangle(player.getXmiddle(), player.getYmiddle(),
				30, 30);
		if (rf.intersects(rh)) {

			if (hits % 150 == 0) {
				player.setHealth(player.getCurrentHealth() - getDamage());
				System.out.println(player.getCurrentHealth());
				if (player.getCurrentHealth() <= 0) {
					Game.lifes--;
					System.out.println(Game.lifes);
					/*
					 * if (Game.lifes==0) { Game.lifes=5; game.gameOver();} else
					 * {game.thisRoom(); }}}
					 */}
			}

			hits++;
		}

	}

	public void paintComponent(Graphics g) {
		Image img = Toolkit.getDefaultToolkit().getImage("feuerball.png");
		g.drawImage(img, 0, 0, this);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
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

	public int getCollision() {
		return collision;

	}

	public int getDamage() {
		return this.damage;
	}

	public int getXmiddle() {
		return getX() + 15;
	}

	public int getYmiddle() {
		return getY() + 15;
	}
}
