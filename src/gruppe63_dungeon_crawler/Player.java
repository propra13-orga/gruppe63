package gruppe63_dungeon_crawler; 

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;



@SuppressWarnings({ "serial" })
public class Player extends Elements {
	private Room room;
	private int health=100;
	private int damage=50;
	private boolean attack=false;
	private int hits=0;
	

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
	
	public void collision(Boss boss) {
		x=this.getX();
		y=this.getY();
		
	if (attack) {	
	if (x-boss.getPosX()<100 & y-boss.getPosY()<100 & x-boss.getPosX()>0 & y-boss.getPosY()>0 |
			boss.getPosX()-x<100 & boss.getPosY()-y<100 & boss.getPosX()-x>0 & boss.getPosY()-y>0) {
		
	if (hits % 20 ==0) {boss.setHealth(boss.getHealth()-getDamage());System.out.println(boss.getHealth());}
	hits++;}
	}	
	}
	
}

