package gruppe63_dungeon_crawler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.ArrayList;

@SuppressWarnings({ "serial" })
public class Player extends Elements {
	private Room room;
	private int health =100;
	private int damage = 50;
	private boolean attack = false;
	private int hits = 0;
	private float level;
	private float xp;
	Game game;
	
	private int money;
	int width;
	int height;
	private Image sprite;
	private ArrayList<Magic> magic;
	private ArrayList<Weapon> weapon;

	public Player(int x, int y, Room room) {
		// Groesse des Spielers
		super(x, y, 30, 30);
		this.room = room;
		level = 1;
		xp = 0;
		health = 100;
				
		magic = new ArrayList<Magic>();
		weapon = new ArrayList<Weapon>();
		sprite = Toolkit.getDefaultToolkit().getImage("ghost.gif");
		width = sprite.getWidth(null);
	    height = sprite.getHeight(null);

	}

	public int getLevel() { // wann das Spielerlevel erh�ht werden soll
		return (int) (xp / 50) + 1;
	}

	public void addXp(float amt) { // amt = amount ... xp wird erh�ht
		xp += amt;
	}

	public int getMaxHealth() {

		return getLevel() * 100;
	}

	public int getCurrentHealth() {
		int max = getMaxHealth();
		if (health > max)
			health = max;
		return health;
	}

	public float getStrengh() {
		return getLevel() * 4f;
	}

	/*public float getMagic() {
		return getLevel() * 4f;
	}*/

	public void update() { // Status wird in der Console angezeigt ....
							// kann/wird sp�ter modifiziert werden und im
							// Spielfeld angezeigt (werden)
		System.out.println("Status: LEVEL: " + getLevel() + "MaxHP: "
				+ getMaxHealth() + "HP: " + getCurrentHealth() + "Strengh: "
				+ getStrengh() + "Magic: " + getMagic());

	}

	public void addItem(Item item) {
		System.out.println("Du hast gerade ein Leben eingesammelt");
	}

	public void move() {
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
		room.status();
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

	public int getHealth() { // oben health erneuert
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

}
