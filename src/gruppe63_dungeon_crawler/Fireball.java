package gruppe63_dungeon_crawler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Fireball extends Elements {

	private Room room;
	
	private int collision=0; 
	private int hits=0;
	
	private int damage=50;
	
	
	public Fireball(int x, int y, Room room){
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
		
		if (room.Environment(x+vx,y,xDim,yDim)==1) {collision=1;}
		
		if(room.Environment(x,y-vy,xDim,yDim)!=1){	
			y = y-vy;
			this.setLocation(x,y);
		}
		
		if (room.Environment(x,y-vy,xDim,yDim)==1) {collision=1;}
	
		room.status();	
	}
	
	
			
	public void collision(Player player) {
		x=this.getX();
		y=this.getY();
	if (x-player.getPosX()<50 & y-player.getPosY()<50 & x-player.getPosX()>0 & y-player.getPosY()>0 |
			player.getPosX()-x<50 & player.getPosY()-y<50 & player.getPosX()-x>0 & player.getPosY()-y>0) {
		
	if (hits % 40==0)	{player.setHealth(player.getHealth()-getDamage());System.out.println(player.getHealth());}
	
	hits++;}
		
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
	public int getCollision() {
		return collision;
		
	}
	public int getDamage(){
		return this.damage;
	}
}


