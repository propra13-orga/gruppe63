package gruppe63_dungeon_crawler; 

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;



@SuppressWarnings({ "serial" })
public class Player extends Elements {
	private Room room;

	public Player(int x, int y, Room room){
		//Groesse des Spielers
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
	

}

