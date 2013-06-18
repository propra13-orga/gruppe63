package gruppe63_dungeon_crawler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Armor extends Elements {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Room room;
	///*****////
	
	private int width;
	private int height;
	
	//private int health = 60;
	//private int damage = 100;
	//private boolean attack = false;
	//private int hits = 0;
	private boolean alive=true;
	
	private Image sprite;

	public Armor(int x, int y, Room room) {
		super(x, y, 30, 30);
		this.room = room;
		sprite = Toolkit.getDefaultToolkit().getImage("ruestung.gif");
		width = sprite.getWidth(null);
	    height = sprite.getHeight(null);
	    
	}

	private int collision = 0;
	//private int hits = 0;

	//private int damage = 100;
	Game game;



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

    public void paintComponent(Graphics g) {
		
		g.drawImage(sprite, 0, 0, this);
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

	//public int getDamage() {
	//	return this.damage;
	//}
	
	public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}







/*package gruppe63_dungeon_crawler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Armor extends Elements { // Einfache Kopie von der Klasse Player
										// als Platzhalter.

	/**
	 * 
	 */
	/*private static final long serialVersionUID = 1L;
	private Room room;
	private int width;
	private int height;
	private Image sprite;


	public Armor(int x, int y, Room room) {
		super(x,y,30,30);
		this.room = room;
		vx = getRandomMov();
		vy = getRandomMov();
		setVisible(true);
		sprite = Toolkit.getDefaultToolkit().getImage("ruestung.gif");
		width = sprite.getWidth(null);
	    height = sprite.getHeight(null);
	}

	public void move() {
		x = this.getX();
		y = this.getY();

		if (room.Environment(x + vx, y, xDim, yDim) != 1) {
			x = x + vx;
			this.setLocation(x, y);
		}
		
		else {
			vx = -vx;
			x = x+vx;
		}

		if (room.Environment(x, y - vy, xDim, yDim) != 1) {
			y = y - vy;
			this.setLocation(x, y);
		}
		
		else {
			vy = -vy;
			y = y-vy;
		}
		
		//room.status();
	}
	
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
	
	public void paintComponent(Graphics g){
		g.drawImage(sprite, 0, 0, this);
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
		}
	
	public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

	public Image getSprite() {
		return sprite;
	}

}*/
