package gruppe63_dungeon_crawler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * 
 * Feuerbälle in den Räumen der Bossgegner.
 * Bildbreite: 30
 * Bildhöhe: 30
 * Schaden: 50
 *
 */

public class Fireball extends Elements {

	private static final long serialVersionUID = 1L;

	private Room room;
	private int width;
	private int heigth;

	private int collision = 0;
	private int hits = 0;

	private int damage = 50;
	Game game;

	public Fireball(int x, int y, Room room) {
		super(x, y, 30, 30);
		this.room = room;
		width=30;
		heigth=30;
	}

	/**
	 * 
	 * Bewegung des Feuerballs.
	 *
	 */
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

	/**
	 * 
	 * Überschneiden sich die Rechtecke des Feuerballs und des Spielers
	 * fügt der Feuerball dem Spieler Schaden zu.
	 * 
	 * Damit der Feuerball nur einmal bei Kontakt mit dem Spieler Schaden verursacht,
	 * wird seine Angriffsgeschwindigkeit herabgesetzt,
	 * sodass er nur bei jedem 150ten Durchgang der Schleife tatsächlich Schaden verursacht.
	 * Dies führt im Normalfall dazu, dass der Feuerball nur einmal Schaden verursacht.
	 *  
	 */	
	public void collision(Player player) {
		Rectangle rf = getBounds();
		Rectangle rh = player.getBounds();
		if (rf.intersects(rh)) {

			if (hits % 150 == 0) {
				if (player.getFireres() == true)
				{
					player.setHealth(player.getHealth() - getDamage() + 10);
				}
				else
				{
					player.setHealth(player.getHealth() - getDamage());
				}
				System.out.println(player.getHealth());
				
			}

			hits++;
		}

	}

	public void paintComponent(Graphics g) {
		Image img = Toolkit.getDefaultToolkit().getImage("res/feuerball.png");
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
	
	/**
	 * 
	 * Rechteck zur Kollisionsabfrage.
	 *
	 */
	public Rectangle getBounds() {
        return new Rectangle(x, y, width, heigth);
    }

}
