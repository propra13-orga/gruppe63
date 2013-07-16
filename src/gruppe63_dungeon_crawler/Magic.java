package gruppe63_dungeon_crawler;   

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Magic extends Elements{
	/**
	 * Magie/Feuerbaelle die von der Qualle benutzt wird, erhaelt bewegung im controller
	 */
	private static final long serialVersionUID = 1L;
	private Room room;
	private int width;
	private int height;
	private Image sprite;
	private int damage=50;
	
	public Magic(int x, int y, Room room){
		//Groesse des Spielers
		super(x,y,20,20);
		this.room = room;
		vx = 2;
		//vy = getRandomMov();
		setVisible(true);
		sprite = Toolkit.getDefaultToolkit().getImage("res/Fireball.gif");
		width = sprite.getWidth(null);
	    height = sprite.getHeight(null);
	 }
	/**
	 * hier wird die Bewegung der Feuerbaelle festgelegt 
	 **/
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
		
		if(room.Environment(x,y-vy,width,height)!=1) {	
			y = y+vy;
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
	 * Kollisionsüberprüfung
	 */
	public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

	public Image getSprite() {
		return sprite;
	}
/**
 * @param movDirX  gibt die Bewegungsrichtung wieder
 * @param movDirY gibt die Bewegungsrichtung wieder
 * @parem getDamage gibt den Schaden wieder, der zugefügt wird (ist oben deklariert)
 */
	public void setDirX(int movDirX) {
		this.vx = movDirX;
	}
	
	public void setDirY(int movDirY) {
		this.vy = movDirY;
	}
	public int getDamage(){
	return this.damage;		
	}}