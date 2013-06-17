package gruppe63_dungeon_crawler;

import java.awt.Color;
import java.awt.Container;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Rectangle;

public class Game extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Menu main;
	private Container container;
	private Player player;
	// private ArrayList<Elements> objects;
	// private ArrayList<Elements> remove;
	private Enemy enemy;
	private Boss boss;
	private Boss2 boss2;
	private Boss3 boss3;
	private Fireball fireball;
	
	private int saveHealth=100;
	private int saveMoney;
	
	private int playerdamaged=0;
	
	private ArrayList<Enemy> enemies;
	private ArrayList<Magic> fireballs;
	private ArrayList<Weapon> arrows;

	private Room room;
	private Timer timer;
	private boolean ball;
	private boolean down = true;

	public static int lifes = 5;
	int[][] Z;

	public int actualroom = 1;
	private int endroom = 9;

	public Game(Container container, Menu menu) {

		this.container = container;
		this.main = menu;

		// objects.add(new Leben(..?..));?
	}

	int fireballtimer = 0;
	int fireballcounter = 0;

	public void run() {

		startRoom();
		TimerTask action = new TimerTask() {
			public void run() {
				
				if (player.getHealth() <= 0) {
				Game.lifes--;
				System.out.println(Game.lifes);
				startRoom();
					
				}
				
				// Gegner				
				for (int i = 0; i < enemies.size(); i++) {
				    Enemy e = (Enemy) enemies.get(i);
				    if (e.isVisible())
				        e.move();
				    else enemies.remove(i);
				}
				// Spieler
				player.move();
				
				// Feuerbaelle Diana
				fireballs = player.getMagic();
				
				for(int k=0;k<fireballs.size();k++) {
					Magic m = (Magic) fireballs.get(k);
					room.add(m);		
				}

				for (int i = 0; i < fireballs.size(); i++) {
				    Magic m = (Magic) fireballs.get(i);
				    if (m.isVisible())
				        m.move();
				    else fireballs.remove(i);
				}
				
				// Pfeile
				arrows = player.getWeapon();
				
				for(int k=0;k<arrows.size();k++) {
					Weapon w = (Weapon) arrows.get(k);
					room.add(w);		
				}
				
				for (int i = 0; i < arrows.size(); i++) {
				    Weapon w = (Weapon) arrows.get(i);
				    if (w.isVisible())
				        w.move();
				    else arrows.remove(i);
				}
				
				collision();

				fireballtimer++;

				// Anfang Bosskmapf 1.
				if (actualroom == 3) {
					if (fireballtimer % 1000 == 0 & down == false) {
						fireball = new Fireball(boss.getPosX(), boss.getPosY(),
								room);
						room.add(fireball);
						ball = true;

						fireballcounter++;
					}

					if (ball) {

						if (fireballcounter % 4 == 1) {
							fireball.setMovX(-1);
						}
						if (fireballcounter % 4 == 2) {
							fireball.setMovY(-1);
						}
						if (fireballcounter % 4 == 3) {
							fireball.setMovX(1);
						}
						if (fireballcounter % 4 == 0) {
							fireball.setMovY(1);
						}

						fireball.move();
						fireball.setMovX(0);
						fireball.setMovY(0);

						if (fireball.getCollision() == 1) {
							room.remove(fireball);
							ball = false;
						}

						fireball.collision(player);
					}

					player.collision(boss);
					if (boss.getHealth() <= 0) {
						room.remove(boss);
						down = true;
					}

				}
				// Ende Bosskampf 1.
				// Angfang Bosskampf 2.

				if (actualroom == 6) {down=false;
					
					for (int k = 0; k<fireballs.size(); k++) {
			        	Magic m = (Magic) fireballs.get(k);
			        	Rectangle rm = m.getBounds();
			        	Rectangle rb = boss2.getBounds();
			        	if(rm.intersects(rb)) {
			        		m.setVisible(false);
			        		boss2.setHealth(boss2.getHealth()-50);
			        		System.out.println(boss2.getHealth());
			        		}
					}
					
					boss2.collision(player);
					player.collision(boss2);
					if (boss2.getHealth() <= 0) {
						boss2.setVisible(false);
						room.remove(boss2);
						boss2.setDead();
						down = true;
					}

				}
				// Ende Bosskampf 2.
				// Anfang Bosskampf 3.
				if (actualroom == 9) {
					
					
					

					if (fireballtimer % 1000 == 0 & down == false) {

						fireball = new Fireball(400, 50, room);
						room.add(fireball);

						ball = true;
					}

					if (fireballtimer % 1000 == 250) {

						fireball = new Fireball(400, 150, room);
						room.add(fireball);

						ball = true;
					}

					if (fireballtimer % 1000 == 500) {

						fireball = new Fireball(400, 250, room);
						room.add(fireball);

						ball = true;
					}

					if (fireballtimer % 1000 == 750) {

						fireball = new Fireball(400, 450, room);
						room.add(fireball);

						ball = true;
					}
					if (ball) {
						fireball.setMovX(-1);
						fireball.move();
						fireball.setMovX(0);

						if (fireball.getCollision() == 1) {
							room.remove(fireball);
							ball = false;
						}

						fireball.collision(player);
					}
					
					for (int k = 0; k<fireballs.size(); k++) {
			        	Magic m = (Magic) fireballs.get(k);
			        	Rectangle rm = m.getBounds();
			        	Rectangle rb = boss3.getBounds();
			        	if(rm.intersects(rb)) {
			        		m.setVisible(false);
			        		boss3.setHealth(boss3.getHealth()-50);
			        		System.out.println(boss3.getHealth());
			        		}
					}

					boss3.collision(player);
					player.collision(boss3);
					if (boss3.getHealth() <= 0) {
						boss3.setVisible(false);
						room.remove(boss3);
						boss3.setDead();
						down = true;
					}
				}
				// Ende Bosskmapf 3.

			}
		};
		timer = new Timer();
		timer.schedule(action, 0, 5);
	}

	private void startRoom() {

		container.setBackground(Color.WHITE);
		container.removeAll();
		room = new Room(50, 50, actualroom, this); // (Elementwidth,
													// Elementheight, Level,
													// Game)
		
		enemies = new ArrayList<Enemy>();
		fireballs = new ArrayList<Magic>();
		arrows = new ArrayList<Weapon>();
		
		int[][] Z = Matrix.playedRoom(actualroom);

		for (int i = 0; i < Z.length; i++) {
			for (int j = 0; j < Z[i].length; j++) {
				if (Z[i][j] == 4) {

					player = new Player((j + 1) * Room.elementheight, i
							* Room.elementwidth, room);
					room.add(player);
				}

				if (Z[i][j] == 2) {

					enemy = new Enemy(j * Room.elementheight, i
							* Room.elementwidth, room);
					enemies.add(enemy);
					room.add(enemy);
				}

				if (Z[i][j] == 6) {

					boss = new Boss(j * Room.elementheight, i
							* Room.elementwidth, room);
					room.add(boss);
					down = false;
				}
				if (Z[i][j] == 8) {

					boss2 = new Boss2(j * Room.elementheight, i
							* Room.elementwidth, room);
					room.add(boss2);
				}
				if (Z[i][j] == 9) {

					boss3 = new Boss3(j * Room.elementheight, i
							* Room.elementwidth, room);
					room.add(boss3);
					down = false;
				}
			}
		}
		main.controller.setPlayer(player);
		room.paintRoom();
		container.add(room);
		room.repaint(100);
		player.setHealth(saveHealth);
		player.setMoney(saveMoney);
		
		
	}

	private void startRoomRev() { // Muss noch überarbeitet werden.

		container.setBackground(Color.WHITE);
		container.removeAll();
		room = new Room(50, 50, actualroom, this); // (Elementwidth,
													// Elementheight, Level,
													// Game)
		player = new Player(Matrix.Downy(actualroom) * Room.elementheight,
				Matrix.Downx(actualroom) * Room.elementwidth, room);
		room.add(player);
		
		enemyInit(Z);
		
		main.controller.setPlayer(player);
		room.paintRoom();
		container.add(room);
		room.repaint(100);
	}
	
	public void collision() {
		Rectangle rp = player.getBounds();

	    for (int j = 0; j<enemies.size(); j++) {
	        Enemy e = (Enemy) enemies.get(j);
	        Rectangle re = e.getBounds();

	        if (rp.intersects(re)) {
	        	
	        	if (playerdamaged % 100 == 0) {player.setHealth(player.getHealth()-e.getDamage());System.out.println(player.getHealth());}
   	
	
	        	playerdamaged++;
	        	player.collision(e);
	          		        	
	        	}
	        	
	        	
	        
	        
	        for (int k = 0; k<fireballs.size(); k++) {
	        	Magic m = (Magic) fireballs.get(k);
	        	Rectangle rm = m.getBounds();
	        	
	        	if(rm.intersects(re)) {
	        		m.setVisible(false);
	        			        		
	        		e.setHealth(e.getHealth() - m.getDamage());
					System.out.println(e.getHealth());
	        	}
	        	
	        	
	        }
	        
	      /*  for (int k = 0; k<fireballs.size(); k++) {
	        	Magic m = (Magic) fireballs.get(k);
	        	Rectangle rm = m.getBounds();
	        	Rectangle b2 = boss2.getBounds();
	        
	       if(rm.intersects(b2)) {System.out.println("test");room.remove(boss2);}
	        }*/
	        
	        for (int k = 0; k<arrows.size(); k++) {
	        	Weapon w = (Weapon) arrows.get(k);
	        	Rectangle rw = w.getBounds();
	        	
	        	if(rw.intersects(re)) {
	        		w.setVisible(false);
	        		e.setHealth(e.getHealth() - w.getDamage());
					System.out.println(e.getHealth());
	        	}
	        }
	        
	        if(e.getHealth()<=0)
            {	System.out.println("getoetet");
        		e.setVisible(false);}
	    }
	    
	}
	
	private void enemyInit(int[][] Z) {
		enemies = new ArrayList<Enemy>();
		
		//int[][] room2 = room.getRoom(actualroom);
		
		for(int i=0;i<Z.length;i++) {
			for(int j=0;j<Z[0].length;j++) {
				if(Z[i][j]==2) {
					enemies.add(new Enemy((int)(j*Room.elementheight),(int)(i*Room.elementwidth),room));
				}
			}
		}
		
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = (Enemy) enemies.get(i);
			room.add(e);
		}
	}
	
	
	

	public void gameOver() {
		timer.cancel();
		timer.purge();
		container.remove(room);
		room = null;
		main.win(false);
	}

	public void thisRoom() {
		room.removeAll();
		container.remove(room);
		room = null;
		startRoom();
	}

	public void nextRoom() {
		actualroom++;
		//room.removeAll();
		container.remove(room);
		room = null;
		if (actualroom > endroom) {
			timer.cancel();
			timer.purge();
			main.win(true);
		} else {
			setsaveMoney(player.getMoney());
			setsaveHealth(player.getHealth());
			startRoom();
		}
	}

	public void lastRoom() {
		if (actualroom > 1) {
			actualroom--;
			room.removeAll();
			container.remove(room);
			room = null;
			startRoomRev();
		}

	}

	public boolean getDown() {
		return this.down;
	}
	
	public void setsaveMoney(int n){
	this.saveMoney=n;	
	}
		
	public void setsaveHealth(int n){
	this.saveHealth=n;	
	}
	
	
}
