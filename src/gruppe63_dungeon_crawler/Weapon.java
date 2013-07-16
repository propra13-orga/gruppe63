package gruppe63_dungeon_crawler;   

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;


/**
 * 
 * Die Qualle verschießt Pfeile.
 * Bildbreite: 35
 * Bildhöhe: 10
 * Schaden: 20
 * 
 *
 */	
public class Weapon extends Elements {
	private static final long serialVersionUID = 1L;
	private Room room;
	private int width;
	private int height;
	private Image sprite;
	private int damage=20;
	
	public Weapon(int x, int y, Room room){
		//Groesse des Spielers
		super(x,y,35,10);
		this.room = room;
		vx = 2;
		setVisible(true);
		sprite = Toolkit.getDefaultToolkit().getImage("res/Arrow.png");
		width = sprite.getWidth(null);
	    height = sprite.getHeight(null);
	 }
	
	/**
     * 
     * Bewegung des Pfeils.
     * 
     *
     */	
	public void move(){
		x=this.getX();
		y=this.getY();
		
		if(room.Environment(x+vx,y,width,height)!=1) {	
			x = x+vx;
			this.setLocation(x,y);
		}
		else {
			setVisible(false);
		}
		

	}
	

	
	public void paintComponent(Graphics g){
	g.drawImage(sprite, 0, 0, this);
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
	
	/**
     * 
     * Rechteck zur Kollisionsabfrage.
     * 
     *
     */	
	public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

	public Image getSprite() {
		return sprite;
	}
	public int getDamage(){
		return this.damage;
	}
	

}


