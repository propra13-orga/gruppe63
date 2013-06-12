package gruppe63_dungeon_crawler; 

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;



@SuppressWarnings({ "serial" })
public class Player extends Elements {
	private Room room;
	private int health/*=100*/; 
	private int damage/*=50*/;
	private boolean attack=false;
	private int hits=0;
	private float level;
	private float xp;
	

	public Player(int x, int y, Room room){
		//Groesse des Spielers
		super(x,y,30,30);
		this.room = room;
		level = 1;
		xp= 0;
		health = 100;
		
	}
	
	public int getLevel(){    //wann das Spielerlevel erhöht werden soll
		return (int)(xp/50)+1;
	}
	public void addXp(float amt){ //amt = amount ... xp wird erhöht
		xp += amt;
		}
	
	public int getMaxHealth(){
		
		return getLevel() * 100;
	}
	
	public int getCurrentHealth(){
		int max= getMaxHealth();
		if (health > max)
			health = max;
		return health;
	}
	
	public float getStrengh(){
		return getLevel()* 4f;
	}
	
	public float getMagic(){
		return getLevel() * 4f;
	}
	public void update(){      //Status wird in der Console angezeigt .... kann/wird später modifiziert werden und im Spielfeld angezeigt (werden)
		System.out.println("Status: LEVEL: "+ getLevel()+ "MaxHP: "+ getMaxHealth()+ "HP: "+ getCurrentHealth()+ "Strengh: "+ getStrengh()+ "Magic: "+ getMagic());
	}
	
	public void addItem(Item item){
		System.out.println("Du hast gerade ein Leben eingesammelt");
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
	//public int getHealth(){      //oben health erneuert
	//	return this.health;
	//}
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
	Rectangle rh = new Rectangle(getXmiddle(),getYmiddle(), 30, 30);
	Rectangle rb = new Rectangle(boss.getXmiddle(), boss.getYmiddle(), 30, 30);		
	if (attack) {
		
		
	if (rh.intersects(rb)) {
		
	if (hits % 20 ==0) {boss.setHealth(boss.getHealth()-getDamage());System.out.println(boss.getHealth());}
	hits++;}
	}	
	}
	
	public void collision2(Boss2 boss2) {
	Rectangle rh = new Rectangle(getXmiddle(),getYmiddle(), 30, 30);
	Rectangle rb = new Rectangle(boss2.getXmiddle(), boss2.getYmiddle(), 30, 30);
		
	if (attack) {	
	if (rh.intersects(rb)) {
		
	if (hits % 20 ==0) {boss2.setHealth(boss2.getHealth()-getDamage());System.out.println(boss2.getHealth());}
	hits++;}
	}	
	}
	
	public void collision3(Boss3 boss3) {
	Rectangle rh = new Rectangle(getXmiddle(),getYmiddle(), 30, 30);
	Rectangle rb = new Rectangle(boss3.getXmiddle(), boss3.getYmiddle(), 30, 30);
	
	if (attack) {	
	if (rh.intersects(rb)) {
		
	if (hits % 20 ==0) {boss3.setHealth(boss3.getHealth()-getDamage());System.out.println(boss3.getHealth());}
	hits++;}
	}	
	}
	
	public int getXmiddle() {
		return getX()+15;
	}
	
	public int getYmiddle() {
		return getY()+15;
	}
	
	
}

