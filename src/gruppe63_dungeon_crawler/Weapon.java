package gruppe63_dungeon_crawler;   

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

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
		//vy = getRandomMov();
		setVisible(true);
		sprite = Toolkit.getDefaultToolkit().getImage("res/Arrow.png");
		width = sprite.getWidth(null);
	    height = sprite.getHeight(null);
	 }
	
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
			y = y-vy;
			this.setLocation(x,y);
		}
		else {
			vy = -vy;
			y = y-vy;
		}
		//room.status();
	}
	
	/*public int getRandomMov() {
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
	}*/
	
	public void paintComponent(Graphics g){
	//Image img = Toolkit.getDefaultToolkit().getImage("ghost.gif");
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


