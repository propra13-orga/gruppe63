package gruppe63_dungeon_crawler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Rectangle;

public class Boss3 extends Elements {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private Room room;
	private int width;
	private int height;
	private int health=60;
	private int damage=100;
	private boolean attack=false;
	private int hits=0;
	private Image sprite;
	private boolean alive=true;
	
	public Boss3(int x, int y, Room room){
		super(x, y, 66, 35);
		this.room = room;
		sprite = Toolkit.getDefaultToolkit().getImage("res/bossgegner.gif");
		width = 35;
	    height =35;
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
	public int getHealth(){
		return this.health;
	}
	public void setHealth(int n){
		this.health=n;
	}
	public int getDamage(){
		return this.damage;
	}
	public boolean getAttack() {
		return this.attack;
	}
	
	public void setAttack(boolean b) {
		this.attack=b;
	}
	
	public void collision(Player player) {
		if (alive) {
	Rectangle r = getBounds();
	Rectangle rh = player.getBounds();
	if (r.intersects(rh))	{if (hits % 1000==0)	{System.out.println("schaden");player.setHealth(player.getHealth()-getDamage());System.out.println(player.getHealth());}
	hits++;
	}
		
	}}
	
	public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
	
	public void setDead(){
		this.alive=false;
	}
	
}

