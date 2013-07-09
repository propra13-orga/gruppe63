package gruppe63_dungeon_crawler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Boss extends Elements {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Room room;
	private int health = 30;
	private int width=50;
	private int height=50;

	public Boss(int x, int y, Room room) {
		super(x, y, 66, 35);
		this.room = room;
	}

	public void paintComponent(Graphics g) {
		Image img = Toolkit.getDefaultToolkit().getImage("res/bossgegner.gif");
		g.drawImage(img, 0, 0, this);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public int getPosX() {
		return this.getX();
	}

	public int getPosY() {
		return this.getY();
	}

	public int getHealth() {
		return this.health;
	}

	public void setHealth(int n) {
		this.health = n;
	}

	public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
