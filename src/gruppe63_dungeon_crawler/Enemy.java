package gruppe63_dungeon_crawler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * 
 * Standardgegner.
 * Bildbreite: 48
 * Bildh�he: 48
 * Leben: 100
 * Schaden: 10
 *
 */

public class Enemy extends Elements {										

	private static final long serialVersionUID = 1L;
	private Room room;
	
	private int width;
	private int height;
	private Image sprite;
	
	private int damage = 10;
	private int health=100;

	public Enemy(int x, int y, Room room) {
		super(x,y,48,48);
		this.room = room;
		vx = getRandomMov();
		vy = getRandomMov();
		setVisible(true);
		sprite = Toolkit.getDefaultToolkit().getImage("res/qualle05.gif");
		width = 48;
	    height = 48;
	}
	
	
	/**
	 * 
	 * Bewegung des Standardgegners in eine zuf�llige Richtung.
	 *
	 */
	public void move() {
		x = this.getX();
		y = this.getY();

		if (room.Environment(x + vx, y, xDim, yDim) != 1 & room.Environment(x + vx, y, xDim, yDim) != 4 & room.Environment(x + vx, y, xDim, yDim) != 5) {
			x = x + vx;
			this.setLocation(x, y);
		}
		
		else {
			vx = -vx;
			x = x+vx;
		}

		if (room.Environment(x, y - vy, xDim, yDim) != 1 & room.Environment(x, y - vy, xDim, yDim) != 4 & room.Environment(x, y - vy, xDim, yDim) != 5) {
			y = y - vy;
			this.setLocation(x, y);
		}
		
		else {
			vy = -vy;
			y = y-vy;
		}
		
	
	}
	
	/**
	 * 
	 * Die Bewegungsrichtung wird zuf�llig bestimmt.
	 *
	 */
	public int getRandomMov() {
		int rand = (int)(Math.random()*2)+1;
		int r = 0;
		switch(rand) {
		case 1:
			r = -1; break;
		case 2:
			r = 0; break;
		case 3:
			r = 1; break;		
		}
		return r;
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
	
	public void paintComponent(Graphics g){
		g.drawImage(sprite, 0, 0, this);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
		}
	
	/**
	 * 
	 * Rechteck zur Kollisionsabfrage.
	 *
	 */
	public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

	public Image getSprite() {
		return sprite;
	}
	
	public int getHealth() {
		return this.health;
	}

	public void setHealth(int n) {
		this.health = n;
	}

}
