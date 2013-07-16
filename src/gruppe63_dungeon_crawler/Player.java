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



/**
 * 
 * Die Spielfigur.
 * 
 * Bildbreite: 30
 * Bildhöhe: 30
 * 
 * Besitzt je nach Fortschritt im Spiel unterschiedliche Eigenschaften und Gegenstände.
 *  
 * 
 * Eigenschaften: 
 * Leben, maximales Leben, Mana, maximale Mana, Schaden
 * Gegenstände:
 * Rüstung, Geld, Manatränke, Heiltränke
 * 
 * Über die Spielfigur wird auch die Verarbeitung der empfangenen Daten im Multiplayer geregelt.
 * 
 * Da die Spielschleifen auf unterschiedlichen Rechnern unterschiedlich schnell ausgeführt werden
 * wird der Mittelwert der Winnerpoints bestimmt. Dieser wird beiden Spielern angezeigt.
 *
 */
@SuppressWarnings({ "serial" })
public class Player extends Elements {
	private Room room;
	private int health =100;
	private int mana = 100;
	private int damage = 50;
	private boolean attack = false;
	//private boolean speak = false;
	private int hits = 0;
	private int maxHealth =100;
	private int maxMana = 100;
	
	private int winnerpoints=0;
	private int winnerpoints2=0;
	private int posX2;
	private int posY2;
	private int r2;
	private boolean escape;
	private boolean player2down;
	private boolean playerdown;
	
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
	private boolean hatRuestung, fireres, UpMagicdmg;
	
	private int wplocal=0;
	private int wpunlocal=0;

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
		width = 30;
	    height = 30;

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
	
	public int getMaxMana()
	{
		return this.maxMana;
	}
	
	public void setMaxMana(int mm)
	{
		maxMana = mm;
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

	/**
	 * 
	 * Bewegung der Spielfigur, sowie Austausch der Daten im Multiplayer.
	 *
	 */
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
    
	/**
	 * 
	 * Überschneiden sich das Rechteck der Spielfigur und eines Elements fügt der Spieler dem Element Schaden zu.
	 * 
	 * Die Angriffsgeschwindigkeit ist auf 1/20 herabgesetzt.
	 *
	 */
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
		
	/**
	 * 
	 * Rechteck zur Kollisionsabfrage.
	 *
	 */
	public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

	/**
	 * 
	 * Der Spieler kann Feuerbälle verschießen.
	 *
	 */
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
	
	/**
	 * 
	 * Der Spieler kann Pfeile verschießen.
	 *
	 */
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
	
	public void setFireres(boolean b)
	{
		fireres = b;
	}
	
	public boolean getFireres()
	{
		return fireres;
	}
	
	public void setUpMagicdmg(boolean b)
	{
		UpMagicdmg = b;
	}
	
	public boolean getUpMagicdmg()
	{
		return UpMagicdmg;
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
	
	
	/**
	 * 
	 * Verarbeitung des Datenversands im Multiplayer.
	 *
	 */
	public void send(int n) {

        try {
           Socket ss = new Socket("localhost", 1231);
                           
           OutputStream os = ss.getOutputStream();
           InputStream is = ss.getInputStream();    

          
                  	   
           os.write(n);
           os.write(x/4);
           os.write(y/4);
           os.write(Game.actualroom);
           os.write(this.wplocal/100);
           os.write(this.wpunlocal/100);
           if (playerdown) {os.write(1);}
           else {os.write(0);}
           
          
           
           int a1=is.read()*4;
           int b1=is.read()*4;
           int c = is.read();
           int d = is.read()*100;
           int e = is.read()*100;
           int f = is.read();
                      

           
           this.posX2=a1;
           this.posY2=b1;
           this.r2=c;
           this.winnerpoints2=d;
           this.winnerpoints=e;
           
           if (f==1) {this.player2down=true;} else {this.player2down=false;}

           
         
           
           ss.close();
           
         
           
                                 
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: taranis.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: taranis.");
            System.exit(1);
            
            
        }}
	
	/**
	 * 
	 * Der Raum in dem sich der ander Spieler zur Zeit befindet.
	 *
	 */
        public int getRoom2() {
        	
        	return r2;
        }
        	
        /**
         * 
         * Y Position des anderen Spielers.
         *
         */
           	public int getposY2(){
        		return posY2;
        	}
        	
        	 /**
             * 
             * X Position des anderen Spielers.
             *
             */	
        	public int getposX2(){
        		return posX2;
        	}
        	
        	 /**
             * 
             * Mittelwert der Winnerpoints für den Spieler dieses Clients.
             *
             */	
        	public int getWinnerpoints(){
        		return this.winnerpoints;
        	}
        	
        	 /**
             * 
             * Mittelwert der Winnerpoints für den Spieler des anderen Clients.
             *
             */	
        	public int getWinnerpoints2(){
        		return this.winnerpoints2;
        	}
        
        	 /**
             * 
             * Winnerpoints für den Spieler des anderen Clients,
             * wie sie aufgrund der Rechengeschwindigkeit dieses Rechners gezählt werden.
             *
             */	
        	public int getwpunlocal() {
        		return this.wpunlocal;
        	}
        	
           	public void incwpunlocal(){
      		this.wpunlocal++;
        	}
			public void incwplocal() {
				this.wplocal++;
			}
			
			/**
             * 
             * Winnerpoints für den Spieler dieses Clients,
             * wie sie aufgrund der Rechengeschwindigkeit dieses Rechners gezählt werden.
             *
             */	
			public int getwplocal(){
				return this.wplocal;
			}
			public void setwplocal(int n){
        		this.wplocal=n;
        	}
			public void setwpunlocal(int n){
        		this.wpunlocal=n;
        	}
			public void setescape(boolean b) {
				this.escape=b;
			}
			public boolean getescape(){
				return this.escape;
			}
			public boolean getplayer2down() {
				return this.player2down;
			}
			public void setdown(boolean b) {
				this.playerdown=b;
			}

    }


