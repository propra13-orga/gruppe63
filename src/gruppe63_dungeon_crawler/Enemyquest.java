package gruppe63_dungeon_crawler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Rectangle;

public class Enemyquest extends Elements {

	/**
	 * Die besondere boese rosa Qualle die einen Ring versteckt haelt
	 * anderes als die anderen Quallen bewegt sie sich nicht, so ist bei  der Questaufgabe das ein Hinweiß,
	 * um herauszufinden, welche Qualle den Ring versteckt (alle anderen Quallen bewegen sich zufaellig).
	 * Die schwierigkeit besteht darin, dass die anderen Quallen sich willkuerlich bewegen aber auch stehen bleiben können,
	 * so ist es auch nicht immer direkt klar welche Qualle die Qualle mit dem Ring ist.
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private Room room;
	private int width;
	private int height;
	
	private int health = 40;
	private int damage = 10;
	private boolean attack = false;
	private int hits = 0;
	private boolean alive=true;
	
	private Image sprite;
/**
 * 
 * @param x
 * @param y siehe Bubous
 * @param room
 */
	public Enemyquest(int x, int y, Room room) {
		super(x, y, 40, 40);
		this.room = room;
		sprite = Toolkit.getDefaultToolkit().getImage("res/qualle05.gif");
		width = sprite.getWidth(null);
	    height = sprite.getHeight(null);
	    
	}

	public void paintComponent(Graphics g) {
		
		g.drawImage(sprite, 0, 0, this);
		
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

	public int getDamage() {
		return this.damage;
	}

	public boolean getAttack() {
		return this.attack;
	}

	public void setAttack(boolean b) {
		this.attack = b;
	}
/**
 * 
 * @param player Kollision mit dem Player wird überprüft und dann wird Leben gesenkt oder auch nicht.
 */
	public void collision(Player player) {
		if (alive) {
		Rectangle rb = getBounds();
		Rectangle rh = player.getBounds();
		if (rb.intersects(rh)) {
			if (hits % 1000 == 0) {
				System.out.println("schaden");
				player.setHealth(player.getHealth() - getDamage());
				System.out.println(player.getHealth());
			}

			hits++;
		}
		}
	}

	public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

	public void setDead(){
		this.alive=false;
	}
}

