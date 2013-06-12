package gruppe63_dungeon_crawler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Rectangle;

public class Boss2 extends Elements {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Room room;
	
	private int health=60;
	private int damage=100;
	private boolean attack=false;
	private int hits=0;
	
	public Boss2(int x, int y, Room room){
		super(x,y,30,30);
		this.room = room;
	}
	
	public void move(){
		x=this.getX();
		y=this.getY();
			
		if(room.Environment(x+vx,y,xDim,yDim)!=1){	
			x = x+vx;
			this.setLocation(x,y);
		}
		
		if(room.Environment(x,y-vy,xDim,yDim)!=1){	
			y = y-vy;
			this.setLocation(x,y);
		}
		room.status();
	}
	
	public void paintComponent(Graphics g){
		Image img = Toolkit.getDefaultToolkit().getImage("ghost.gif");
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
	Rectangle rb = new Rectangle(getXmiddle(),getYmiddle(), 30, 30);
	Rectangle rh = new Rectangle(player.getXmiddle(), player.getYmiddle(), 30, 30);
	if (rb.intersects(rh))	{if (hits % 1000==0)	{System.out.println("schaden");player.setHealth(player.getCurrentHealth()-getDamage());System.out.println(player.getCurrentHealth());}
	
	hits++;}
		
	}
	
	public int getXmiddle() {
		return getX()+15;
	}
	
	public int getYmiddle() {
		return getY()+15;
	}
	
}

