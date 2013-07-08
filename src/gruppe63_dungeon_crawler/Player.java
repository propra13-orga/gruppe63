package gruppe63_dungeon_crawler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

@SuppressWarnings({ "serial" })
public class Player extends Elements {
	private Room room;
	private int health =100;
	private int mana = 100;
	private int damage = 50;
	private boolean attack = false;
	private int hits = 0;
	private int maxHealth =100;
	
	private int winnerpoints=0;
	private int winnerpoints2=0;
	private int posX2;
	private int posY2;
	private int r2;
	
	//private float level;
	//private float xp;
	Game game;
	//Leben leben;
	//Armor armor;
	
	private int money;
	private int healthpotions=0;
	private int manapotions=0;
	int width;
	int height;
	private Image sprite;
	private ArrayList<Magic> magic;
	private ArrayList<Weapon> weapon;
	private boolean hatRuestung;

	public Player(int x, int y, Room room) {
		// Groesse des Spielers
		super(x, y, 30, 30);
		this.room = room;
		//level = 1;
		//xp = 0;
		health = 100;
				
		magic = new ArrayList<Magic>();
		weapon = new ArrayList<Weapon>();
		sprite = Toolkit.getDefaultToolkit().getImage("res/ghost.gif");
		width = sprite.getWidth(null);
	    height = sprite.getHeight(null);

	}

	/*public int getLevel() { // wann das Spielerlevel erhöht werden soll
		return (int) (xp / 50) + 1;
	}

	public void addXp(float amt) { // amt = amount ... xp wird erhöht
		xp += amt;
	}*/

	public int getMaxHealth() {

		return this.maxHealth;
	}
	
	public void setMaxHealth(int mh)
	{
		maxHealth = mh;
	}

	public int getCurrentHealth() {
		int max = getMaxHealth();
		if (health > max)
			health = max;
		return health;
	}

	/*public float getStrengh() {
		return getLevel() * 4f;
	}

	/*public float getMagic() {
		return getLevel() * 4f;
	}*/


	public void move(int n) {
		x = this.getX();
		y = this.getY();

		if (room.Environment(x + vx, y, xDim, yDim) != 1) {
			x = x + vx;
			this.setLocation(x, y);
		}

		if (room.Environment(x, y - vy, xDim, yDim) != 1) {
			y = y - vy;
			this.setLocation(x, y);
		}
		if (n!=0) {
			send(n);}
	}

	public void paintComponent(Graphics g) {
		g.drawImage(sprite, 0, 0, this);
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

	public int getHealth() { 
		return this.health;
	}

	public void setHealth(int n) {
		this.health = n;
	}

	public int getDamage() {
		return this.damage;
	}

	public boolean getAttack() {
		return this.attack;
	}

	public void setAttack(boolean b) {
		this.attack = b;
	}

	public void collision(Elements element) {
		Rectangle rh = getBounds();
		Rectangle rb = element.getBounds();
		if (attack) {

			if (rh.intersects(rb)) {

				if (hits % 20 == 0) {
					element.setHealth(element.getHealth() - getDamage());
					System.out.println(element.getHealth());
					
				}hits++;
			}
		}

	}
		

	public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

	public void castMagic() {
		Magic fireball = new Magic(x,y,room);
		 magic.add(fireball);
	
	}
	
	public ArrayList<Magic> getMagic() {
		return this.magic;
	}

	public ArrayList<Weapon> getWeapon() {
		return this.weapon;
	}

	public void shootWeapon() {
		Weapon arrow = new Weapon(x,y,room);
		weapon.add(arrow);
	}

	public int getMoney() {
	return money;
	}
		
	public void setMoney(int n) {
	this.money=n;		
	} 
	
	public int getHealthpotions()
	{
		return healthpotions;
	}
	
	public void setHealthpotions(int h)
	{
		healthpotions = h;
	}
	
	public void setHatRuestung(boolean b)
	{
		hatRuestung = b;
	}
	
	public boolean getHatRuestung()
	{
		return hatRuestung;
	}
	
	public void setMana(int m)
	{
		this.mana = m;
	}
	
	public int getMana()
	{
		return mana;
	}
	
	public int getManapotions()
	{
		return manapotions;
	}
	
	public void setManapotions(int m)
	{
		manapotions = m;
	}
	
	public void send(int n) {

        try {
           Socket ss = new Socket("Jan", 1231);
                           
           OutputStream os = ss.getOutputStream();
           InputStream is = ss.getInputStream();    

           int x=this.getX();
           int y=this.getY();
           
           System.out.println("Client x:" + x);
           System.out.println("Client y:" + y); 
           
                  	   
           os.write(n);
           os.write(x/4);
           os.write(y/4);
           os.write(Game.actualroom);
           os.write(this.winnerpoints/100);
          
           
           int a1=is.read()*4;
           int b1=is.read()*4;
           int c = is.read();
           int d = is.read()*100;
           
           
           System.out.println("Client x2:" + a1);
           System.out.println("Client y2:" + b1);
           
           this.posX2=a1;
           this.posY2=b1;
           this.r2=c;
           this.winnerpoints2=d;
           

           
         
           
           ss.close();
           
         
           
                                 
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: taranis.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: taranis.");
            System.exit(1);
            
            
        }}
	
        public int getRoom2() {
        	
        	return r2;
        }
        	
        	
        	public int getposY2(){
        		return posY2;
        	}
        	public int getposX2(){
        		return posX2;
        	}
        	
        	public int getWinnerpoints(){
        		return this.winnerpoints;
        	}
        	public int getWinnerpoints2(){
        		return this.winnerpoints2;
        	}
        	public void incWinnerpoints(){
        		this.winnerpoints++;
        	}
        	public void setWinpoints(int n){
        		this.winnerpoints=n;
        	}
 

    }


