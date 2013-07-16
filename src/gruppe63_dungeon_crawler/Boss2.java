package gruppe63_dungeon_crawler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Rectangle;

/**
 * 
 * Zweiter Bossgegner.
 * Leben: 60
 * Schaden: 100
 * Angriffsgeschwindigkeit: 1/200
 * Bildbreite: 35
 * Bildhöhe: 35
 * 
 *
 */


public class Boss2 extends Elements {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private Room room;
	private int width;
	private int height;
	
	private int health = 60;
	private int damage = 100;
	//private boolean attack = false;
	private int hits = 0;
	private boolean alive=true;
	
	private Image sprite;

	public Boss2(int x, int y, Room room) {
		super(x, y, 66, 35);
		this.room = room;
		sprite = Toolkit.getDefaultToolkit().getImage("res/bossgegner.gif");
		width = 35;
	    height = 35;
	    
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

	//public boolean getAttack() {
	//	return this.attack;
	//}

	//public void setAttack(boolean b) {
	//	this.attack = b;
	//}

	
	/**
	 * 
	 * Überschneiden sich die Rechtecke des Bossgegners und des Spielers
	 * fügt der Bossgegner, wenn er noch nicht getötet wurde, dem Spieler Schaden zu.
	 * 
	 * Damit der Bossgegner nicht bei jedem Durchgang der Spielschleife
	 * Schaden verursacht, wird seine Angriffsgeschwindigkeit herabgesetzt,
	 * sodass er nur bei jedem 200ten Durchgang der Schleife tatsächlich Schaden verursacht.
	 *
	 */
	public void collision(Player player) {
		if (alive) {
		Rectangle rb = getBounds();
		Rectangle rh = player.getBounds();
		if (rb.intersects(rh)) {
			if (hits % 200 == 0) {
				System.out.println("schaden");
				player.setHealth(player.getHealth() - getDamage());
				System.out.println(player.getHealth());
			}

			hits++;
		}
		}
	}

	/**
	 * 
	 * Rechteck zur Kollisionsabfrage.
	 *
	 */
	public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
	
	public void setDead(){
		this.alive=false;
	}
}
