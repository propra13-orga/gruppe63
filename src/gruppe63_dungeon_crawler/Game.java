package gruppe63_dungeon_crawler;

import java.awt.Color;
import java.awt.Container;
import java.util.StringTokenizer;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Game extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Menu main;
	private Container container;
	private Player player;
	private Leben leben;
	private Armor armor;
	private Mana mana;
	private Geld money;
	private Enemy enemy;
	private Enemyquest qenemy;
	private Ring ring;
	private Bubous bubous;
	private Boss boss;
	private Boss2 boss2;
	private Boss3 boss3;
	private Fireball fireball;
	private Infobar infobar;
	boolean quit = false;
	private boolean load;
	
	private Player2 player2;
	private int client;
	
	private int saveHealth=100;
	private int saveMoney=100;
	private int saveHealthpotions=0;
	private int saveManapotions=0;
	private int saveMana=100;
	private int saveMaxHealth=100;
	private int saveMaxMana=100;
	private boolean saveHatRuestung;
	@SuppressWarnings("unused")
	private boolean saveHatRing;
	private int savewinpoints=0;
	private int savewinpoints2=0;
	private boolean saveFireres, saveUpMagicdmg;
	
	private int playerdamaged=0;
	private int moneyAmount=10;
	private int healAmount=10;
	private int manaAmount=10;
	private int player1X, player1Y;
	private int totalxp=0;
	private int levelxp=0;
	private int playerLevel=0;
	
	private ArrayList<Enemy> enemies;
	private ArrayList<Magic> fireballs;
	private ArrayList<Weapon> arrows;

	private Room room;
	private Timer timer;
	private boolean ball;
	private boolean down = true;
	
	private boolean isleben;
	private boolean ismana;
	private boolean isarmor;
	private boolean ismoney;
	private boolean isring;
	private boolean isbubous;
	private boolean quest=false;
	private boolean quest2 = false;
	private boolean ringtaken=false;
	private boolean buboustaken=false;
	
	private boolean coopquestfinish=false;
	
	FileWriter writer;
	File file;

	public static int lifes = 5;
	int[][] Z;

	public static int actualroom = 1;
	private int endroom = 10;

	/**
	 * Konstruktor der Game-Klasse.
	 * @param container
	 * @param menu
	 * @param n Gibt an, ob Multiplayer (!=0) oder Singleplayer (=0) gespielt wird.
	 * @param b Parameter, der angibt, ob Spielstand aus Datei geladen werden soll (true) oder ein neues Spiel gestartet werden soll (false).
	 */
	public Game(Container container, Menu menu, int n, boolean b) {

		load = b;
		this.container = container;
		this.main = menu;
		this.client=n;
	}
	
	int fireballtimer = 0;
	int fireballcounter = 0;

	/**
	 * Zyklus, der dauerhaft w�hrend des Spiels durchlaufen wird.
	 * Hier sind Bewegung von Spieler und Gegnern, Kollisionsabfragen sowie Abfragen, ob das Game zu ende ist implementiert.
	 */
	public void run() {

		int[] ladewerte = new int[13];
		if (load)
		{
			String string;
			
			try {
				File fil = new File("Savegame.cjr");
				FileInputStream fis = new FileInputStream(fil);
				InputStreamReader isr = new InputStreamReader(fis);
				@SuppressWarnings("resource")
				BufferedReader br = new BufferedReader(isr);

				string = br.readLine();
				StringTokenizer tokenisedstring = new StringTokenizer(string, ",");
				for (int i = 0; i < 13; i++)
				{
					ladewerte[i] = Integer.parseInt(tokenisedstring.nextToken());
				}
				
				// Anfangswerte setzen (player werte k�nnen noch nicht gesetzt werden, da dieser erst in startRoom erstellt wird)
				actualroom = ladewerte[0];
				Game.lifes = ladewerte[1];
				totalxp = ladewerte[10];
			}
			catch (java.io.FileNotFoundException e) {
				e.printStackTrace();
			}
			catch (java.io.IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			// neues Spiel
			Game.lifes=5;
			actualroom=1;
		}
		startRoom();
		
		// player werte setzen, falls spielstand geladen wurde
		if (load)
		{
			player.setHealth(ladewerte[2]);
			player.setMana(ladewerte[3]);
			player.setMoney(ladewerte[4]);
			player.setHealthpotions(ladewerte[5]);
			player.setManapotions(ladewerte[6]);
			if (ladewerte[7] == 1)
				player.setHatRuestung(true);
			else
				player.setHatRuestung(false);
			player.setMaxHealth(ladewerte[8]);
			player.setMaxMana(ladewerte[9]);
			if (ladewerte[11] == 1)
				player.setFireres(true);
			else
				player.setFireres(false);
			if (ladewerte[12] == 1)
				player.setUpMagicdmg(true);
			else
				player.setUpMagicdmg(false);
			
			// playerlevel und playerxp berechnen, damit diese richtig angezeigt werden in der infobar
			berechnePlayerlevel();
	    	berechneLevelXP();
		}
		TimerTask action = new TimerTask() {
			public void run() {
				
				if (player.getHealth() <= 0) 
				{
					saveHealth = player.getMaxHealth();
					saveMaxHealth = player.getMaxHealth();
					Game.lifes--;
					System.out.println(Game.lifes);
					if (Game.lifes == 0)
					{
						gameOver();
					}
					else
					{
						startRoom();
					}
				}
								
				// Gegner				
				for (int i = 0; i < enemies.size(); i++) {
				    Enemy e = (Enemy) enemies.get(i);
				    if (e.isVisible())
				        e.move();
				    else 
				    {
				    	enemies.remove(i);
				    	totalxp++;
				    	if(totalxp%3 == 0)
				    	{
				    		playerLevelUp(1);
				    	}
				    	berechnePlayerlevel();
				    	berechneLevelXP();
				    }
				}
				// Spieler
				saveLocation1();
				player.move(client);
				if (client==0) {room.status();}
				else {player2.move(player);
				room.status(player, player2);}
				infobar.repaint();
				
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
				if (actualroom == 3) {down=false; player.setdown(false);
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
						player.setdown(true);
					}

				}
				// Ende Bosskampf 1.
				// Angfang Bosskampf 2.

				if (actualroom == 6) {down=false;player.setdown(false);
					
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
						player.setdown(true);
					}

				}
				// Ende Bosskampf 2.
////////////////////////////////////////////////////////////////////////////////////////				
				//Anfang enemyquest
				if (actualroom == 5) { 				
				for (int k = 0; k<fireballs.size(); k++) {
		        	Magic m = (Magic) fireballs.get(k);
		        	Rectangle rm = m.getBounds();
		        	Rectangle rb = qenemy.getBounds();
		        	if(rm.intersects(rb)) {
		        		m.setVisible(false);
		        		qenemy.setHealth(qenemy.getHealth()-50);
		        		System.out.println(qenemy.getHealth());
		        		}
				}			
				qenemy.collision(player);
				player.collision(qenemy);
				
				if (qenemy.getHealth() <= 0) {
					qenemy.setVisible(false);
					room.remove(qenemy);
					qenemy.setDead();
					if(quest){ring.setVisible(true);}
				}

			}
				// Ende Enemyquest
//////////////////////////////////////////////////////////////////////////////////////////				
				//Anfang Bubous quest
               if (actualroom == 8) {
            	   
            	   if (coopquestfinish) {bubous.setVisible(true);}
            	
				}

			//}
				//Ende Bubous quest
				// Anfang Bosskampf 3.
				if (actualroom == 9) {down=false; player.setdown(false);
					
					
					

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
						player.setdown(true);
					}
				}
				// Ende Bosskmapf 3.
					
				if (actualroom==10 & player.getescape()) {
					main.win(true, player);
				}
			}
		};
		timer = new Timer();
		timer.schedule(action, 0, 5);
	}

	/**
	 * L�d den Raum und setzt alle Elemente und zeichnet sie. Ebenso werden alte Werte des Spielers wiederhergestellt.
	 */
	void startRoom() {
		
				container.setBackground(Color.CYAN);
		container.removeAll();
		room = new Room(50, 50, actualroom, this); // (Elementwidth,
													// Elementheight, Level,
													// Game)
		
		enemies = new ArrayList<Enemy>();
		fireballs = new ArrayList<Magic>();
		arrows = new ArrayList<Weapon>();
		
		isleben=false;
		isarmor=false;
		ismana=false;
		ismoney=false;
/////////////////////////////////////////////////////////////////		
		isring=false;
		isbubous=false;
/////////////////////////////////////////////////////////////////////		
		int[][] Z = Matrix.playedRoom(actualroom);

		for (int i = 0; i < Z.length; i++) {
			for (int j = 0; j < Z[i].length; j++) {
				if (Z[i][j] == 4) {

					player = new Player(j * Room.elementheight, i
							* Room.elementwidth, room);
					room.add(player);
					if(client!=0){player2 = new Player2(j * Room.elementheight, (i) * Room.elementwidth,room);
					room.add(player2);
					player.setdown(true);}
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
					
				}
				if (Z[i][j] == 13) {

	                   leben = new Leben(j * Room.elementheight, i
	                                   * Room.elementwidth, room);
	                                  room.add(leben);
	                                  isleben=true;
	                          }
				if (Z[i][j] == 10) {

	                   armor = new Armor(j * Room.elementheight, i
	                                   * Room.elementwidth, room);
	                                  room.add(armor);
	                                  isarmor=true;
	                          }
				if (Z[i][j] == 11) {

	                   mana = new Mana(j * Room.elementheight, i
	                                   * Room.elementwidth, room);
	                                  room.add(mana);
	                                  ismana=true;
	                          }
				if (Z[i][j] == 15) {

	                   money = new Geld(j * Room.elementheight, i
	                                   * Room.elementwidth, room);
	                                  room.add(money);
	                                  ismoney=true;
	                          }
////////////////////////////////////////////////////////////////////////////////////				
				if (Z[i][j] == 16) {

	                   qenemy = new Enemyquest(j * Room.elementheight, i
	                                   * Room.elementwidth, room);
	                                   room.add(qenemy);
	                                  }
////////////////////////////////////////////////////////////////////////////////////	                                  
			if (quest & (ringtaken==false))	{if (Z[i][j] == 17) {

	                   ring = new Ring(j * Room.elementheight, i
	                                   * Room.elementwidth, room);
	                                  room.add(ring);
	                                  ring.setVisible(false);
	                                  isring=true;}
	                          }
			if(quest2 & (buboustaken==false)){if (Z[i][j] == 18) {

	                   bubous = new Bubous(j * Room.elementheight, i
	                                   * Room.elementwidth, room);
	                                  room.add(bubous);
	                                  bubous.setVisible(false);
	                                  isbubous=true;
	                                
	                          
	                          }}
//////////////////////////////////////////////////////////////////////////////				
			}
		}
		player.setHealth(saveHealth);
		player.setMana(saveMana);
		player.setMoney(saveMoney);
		player.setHealthpotions(saveHealthpotions);
		player.setManapotions(saveManapotions);
		player.setHatRuestung(saveHatRuestung);
		player.setMaxHealth(saveMaxHealth);
		player.setMaxMana(saveMaxMana);
		player.setwplocal(savewinpoints);
		player.setwpunlocal(savewinpoints2);
		player.setUpMagicdmg(saveUpMagicdmg);
		player.setFireres(saveFireres);
		moneyAmount = 10;
		manaAmount = 10;
		healAmount = 10;
		infobar = new Infobar(20,600,room,player, this);
		room.add(infobar);
		main.controller.setPlayer(player);
		room.paintRoom();
		container.add(room);
		room.repaint(100);
		
		
	}
	
	/**
	 * Setzt das Spiel nach einer Unterbrechung (z.B. Shop, NPCs) fort. Funktioniert wie run().
	 */
	public void continueGame() {

		continueRoom();
		TimerTask action = new TimerTask() {
			public void run() {
				
				if (player.getHealth() <= 0)
				{
					saveHealth = player.getMaxHealth();
					saveMaxHealth = player.getMaxHealth();
					Game.lifes--;
					System.out.println(Game.lifes);
					if (Game.lifes == 0)
					{
						gameOver();
					}
					else
					{
						startRoom();
					}
				}
				
				// Gegner				
				for (int i = 0; i < enemies.size(); i++) {
				    Enemy e = (Enemy) enemies.get(i);
				    if (e.isVisible())
				        e.move();
				    else 
				    {
				    	enemies.remove(i);
				    	totalxp++;
				    	if(totalxp%3 == 0)
				    	{
				    		playerLevelUp(1);
				    	}
				    	berechnePlayerlevel();
				    	berechneLevelXP();
				    }
				}
				// Spieler
				saveLocation1();
				player.move(client);
				if (client==0) {room.status();}
				else {player2.move(player);
				room.status(player, player2);}
				
				infobar.repaint();
				
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
				if (actualroom == 3) {down=false; player.setdown(false);
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
						player.setdown(true);
					}

			}
				// Ende Bosskampf 1.
				//Anfang NixeQuest
				
				if (actualroom == 5) { 				
					for (int k = 0; k<fireballs.size(); k++) {
			        	Magic m = (Magic) fireballs.get(k);
			        	Rectangle rm = m.getBounds();
			        	Rectangle rb = qenemy.getBounds();
			        	if(rm.intersects(rb)) {
			        		m.setVisible(false);
			        		qenemy.setHealth(qenemy.getHealth()-50);
			        		System.out.println(qenemy.getHealth());
			        		}
					}			
					qenemy.collision(player);
					player.collision(qenemy);
					
					if (qenemy.getHealth() <= 0) {
						qenemy.setVisible(false);
						room.remove(qenemy);
						qenemy.setDead();
						if(quest){ring.setVisible(true);}
					}

				}
				
			//Ende NixeQuest
           //Anfang Bubous Quest
					if (actualroom == 8) {if (coopquestfinish) {bubous.setVisible(true);}}
	           
	//Ende Bubous quest
/////////////////////////////////////////////////////////////////////////
				// Angfang Bosskampf 2.

				if (actualroom == 6) {down=false; player.setdown(false);
					
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
						player.setdown(true);
					}

				}
				// Ende Bosskampf 2.
				// Anfang Bosskampf 3.
				if (actualroom == 9) {down=false; player.setdown(false);
					
					
					

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
						player.setdown(true);
					}
				}
				// Ende Bosskmapf 3.
				if (actualroom==10 & player.getescape()) {
					main.win(true, player);
				}
			}
		};
		timer = new Timer();
		timer.schedule(action, 0, 5);
	}
	
	/**
	 * Setzt den Raum fort.
	 */
	public void continueRoom() {

		container.setBackground(Color.CYAN);
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

					player.setLocation(player1X, player1Y);
					room.add(player);
					if(client!=0){player2.setLocation((j + 1) * Room.elementheight, i * Room.elementwidth);
					room.add(player2);
					player.setdown(true);}
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
					
				}
				if (Z[i][j] == 8) {

					boss2 = new Boss2(j * Room.elementheight, i
							* Room.elementwidth, room);
					
				}
				if (Z[i][j] == 9) {

					boss3 = new Boss3(j * Room.elementheight, i
							* Room.elementwidth, room);
					room.add(boss3);
					
				}
				if (Z[i][j] == 13) {

	                   leben = new Leben(j * Room.elementheight, i
	                                   * Room.elementwidth, room);
	                                  room.add(leben);
	                          }
				if (Z[i][j] == 10) {

	                   armor = new Armor(j * Room.elementheight, i
	                                   * Room.elementwidth, room);
	                                  room.add(armor);
	                          }
				if (Z[i][j] == 11) {

	                   mana = new Mana(j * Room.elementheight, i
	                                   * Room.elementwidth, room);
	                                  room.add(mana);
	                          }
				if (Z[i][j] == 15) {

	                   money = new Geld(j * Room.elementheight, i
	                                   * Room.elementwidth, room);
	                                  room.add(money);
	                          }
				if (Z[i][j] == 17) {

	                   ring = new Ring(j * Room.elementheight, i
	                                   * Room.elementwidth, room);
	                          ring.setVisible(false);     
	                          room.add(ring);
	                          }
				if (Z[i][j] == 16) {

	                   qenemy = new Enemyquest(j * Room.elementheight, i
	                                   * Room.elementwidth, room);
	                                  room.add(qenemy);
	                                 // qenemies.add(qenemy);
	                                  qenemy.setVisible(false);
	                          }
if (quest & (ringtaken==false))	{if (Z[i][j] == 17) {

ring = new Ring(j * Room.elementheight, i
* Room.elementwidth, room);
room.add(ring);
ring.setVisible(false);
isring=true;}
}
if(quest2 & (buboustaken==false)){	if (Z[i][j] == 18) {

bubous = new Bubous(j * Room.elementheight, i
* Room.elementwidth, room);
room.add(bubous);
bubous.setVisible(false);
isbubous=true;

}}
			}
		}
		infobar = new Infobar(20,600,room,player, this);
		room.add(infobar);
		main.controller.setPlayer(player);
		room.paintRoom();
		container.add(room);
		room.repaint(100);
		
		
	}
	
	/**
	 * Hier werden alle Kollisionen behandelt.
	 */
	public void collision() {
		Rectangle rp = player.getBounds();
		
		if (isleben) {
		Rectangle l = leben.getBounds();
		
if (rp.intersects(l)) {
	        
			if (player.getHealth() != player.getMaxHealth())
			{
				if (player.getHealth()+this.healAmount < player.getMaxHealth())
				{
					player.setHealth(player.getHealth()+this.healAmount);
				}
				else
				{
					player.setHealth(player.getMaxHealth());
				}
			}
			healAmount = 0;
        	leben.setVisible(false);
	        room.remove(leben);			            
        }
		}
		
		if (isarmor) {
			
			Rectangle a = armor.getBounds();
			
			 if (rp.intersects(a)) {
			        
		        	player.setHatRuestung(true);
		        	armor.setVisible(false);
			        room.remove(armor);			            
		        }
		}		
	
		if (ismana) {
			
			Rectangle ma = mana.getBounds();
			
			  if (rp.intersects(ma)) {
			        
		        	if (player.getMana() != 100)
		 			{
		 				if (player.getMana()+this.manaAmount < 100)
		 				{
		 					player.setMana(player.getMana()+this.manaAmount);
		 				}
		 				else
		 				{
		 					player.setMana(100);
		 				}
		 			}
		        	manaAmount = 0;
		        	mana.setVisible(false);
			        room.remove(mana);			            
		        }
		}
		
		
		if (ismoney){
			
			Rectangle g = money.getBounds();
			
			if (rp.intersects(g)) {
		        
	        	player.setMoney(player.getMoney()+this.moneyAmount);
	        	moneyAmount = 0;
	        	money.setVisible(false);
		        room.remove(money);			            
	        }
			
		}
		if (isring){
			
			if ((ringtaken==false) & ring.isVisible()) {
			
			Rectangle rg = ring.getBounds();
			
			if (rp.intersects(rg)) {
		        
	        	ring.setVisible(false);
		        room.remove(ring);
		        ringtaken=true;
		        playerLevelUp(2);
	        }
		}}	
			if (isbubous){

				if ((buboustaken==false) & bubous.isVisible()) {
				
				Rectangle rg = bubous.getBounds();
				
				if (rp.intersects(rg)) {
			        
		        	bubous.setVisible(false);
			        room.remove(bubous);
			        buboustaken=true;
			        playerLevelUp(2);
		        }}}
        
		for (int j = 0; j<enemies.size(); j++) {
	        Enemy e = (Enemy) enemies.get(j);
	        Rectangle re = e.getBounds();

	        if (rp.intersects(re)) {
	        	
	        	if (playerdamaged % 100 == 0) 
	        	{
	        		if (player.getHatRuestung())
	        		{
	        			player.setHealth(player.getHealth()-(e.getDamage()/2));
	        		}
	        		else
	        		{
	        			player.setHealth(player.getHealth()-e.getDamage());
	        		}
	        		System.out.println(player.getHealth());
	        	}
	        	
	        	playerdamaged++;
	        	player.collision(e);
	          		        	
	        	}
	        
	        for (int k = 0; k<fireballs.size(); k++) {
	        	Magic m = (Magic) fireballs.get(k);
	        	Rectangle rm = m.getBounds();
	        	
	        	if(rm.intersects(re)) {
	        		m.setVisible(false);
	        			        		
	        		if (player.getUpMagicdmg() == false)
	        		{
	        			e.setHealth(e.getHealth() - m.getDamage());
	        		}
	        		else
	        		{
	        			e.setHealth(e.getHealth() - m.getDamage() - 10);
	        		}
					System.out.println(e.getHealth());
	        	}
	        	
	        	
	        }
	        
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
	
	/**
	 * Beendet das Spiel nach einer Niederlage.
	 */
	public void gameOver() {
		timer.cancel();
		timer.purge();
		container.remove(room);
		room = null;
		main.win(false, player);
	}

	/**
	 * Startet den aktuellen Raum neu (nachdem man gestorben ist, jedoch noch Leben hat).
	 */
	public void thisRoom() {
		room.removeAll();
		container.remove(room);
		room = null;
		startRoom();
		}

	/**
	 * L�d den n�chsten Raum.
	 * Ebenfalls werden hier die Speicherst�nde erstellt.
	 */
	public void nextRoom() {
		actualroom++;
		// spielstand bei n�chstem level (nach bossgegner, also raum 4 oder raum 7) in datei speichern
				if (actualroom == 4 || actualroom == 7)
				{
					file = new File("Savegame.cjr");
					try
					{
						// new FileWriter(file ,true) - falls die Datei bereits existiert
						// werden die Bytes an das Ende der Datei geschrieben
					       
					    // new FileWriter(file) - falls die Datei bereits existiert
					    // wird diese �berschrieben
					    writer = new FileWriter(file);
					       
					    // Text wird in den Stream geschrieben
					    int n = 0;
					    int f = 0;
					    int m = 0;
					    if(player.getHatRuestung() == true)
					    {
					    	n = 1;
					    }
					    if(player.getFireres() == true)
					    {
					    	f = 1;
					    }
					    if(player.getUpMagicdmg() == true)
					    {
					    	m = 1;
					    }
					    writer.write("" + actualroom + "," + this.lifes +  "," + player.getHealth() + "," + player.getMana() + "," + player.getMoney() + "," + player.getHealthpotions() + "," + player.getManapotions() + "," + n + "," + player.getMaxHealth() + "," + player.getMaxMana() + "," + totalxp + "," + f + "," + m);
					       
					    // Schreibt den Stream in die Datei
					    // Sollte immer am Ende ausgef�hrt werden, sodass der Stream 
					    // leer ist und alles in der Datei steht.
					    writer.flush();
					       
					    // Schlie�t den Stream
					    writer.close();
					}
					catch (IOException e) 
					{
					   e.printStackTrace();
				    }
				}
		container.remove(room);
		room = null;
		if (actualroom > endroom) {
			timer.cancel();
			timer.purge();
			main.win(true, player);
		} else {
			setsaveMoney(player.getMoney());
			setsaveHealth(player.getHealth());
			setsaveMana(player.getMana());
			setsaveHealthpotions(player.getHealthpotions());
			setsaveManapotions(player.getManapotions());
			setsaveHatRuestung(player.getHatRuestung());
			setsaveMaxHealth(player.getMaxHealth());
			setsaveMaxMana(player.getMaxMana());
			setsaveWinnerpoints(player.getWinnerpoints());
			setsaveWinnerpoints2(player.getWinnerpoints2());
			setsaveFireres(player.getFireres());
			setsavegetUpMagicdmg(player.getUpMagicdmg());
			
			startRoom();
		}
	}

	/**
	 * Gibt zur�ck, ob der Bossgegner tot ist.
	 * @return
	 */
	public boolean getDown() {
		return this.down;
	}
	
	/**
	 * Setzt Variable saveMoney.
	 * @param n Wert, der saveMoney zugewiesen wird.
	 */
	public void setsaveMoney(int n){
	this.saveMoney=n;	
	}
		
	/**
	 * Setzt Variable saveHealth.
	 * @param n Wert, der saveHealth zugewiesen wird.
	 */
	public void setsaveHealth(int n){
	this.saveHealth=n;	
	}
	
	/**
	 * Setzt Variable saveHealthpotions.
	 * @param h Wert, der saveHealthpotions zugewiesen wird.
	 */
	public void setsaveHealthpotions(int h)
	{
		this.saveHealthpotions = h;
	}
	
	/**
	 * Setzt Variable saveManapotions.
	 * @param m Wert, der saveManapotions zugewiesen wird.
	 */
	public void setsaveManapotions(int m)
	{
		this.saveManapotions = m;
	}
	
	/**
	 * Setzt Variable saveMana.
	 * @param m Wert, der saveMana zugewiesen wird.
	 */
	public void setsaveMana(int m)
	{
		this.saveMana = m;
	}
	
	/**
	 * Setzt Variable saveHatRuestung.
	 * @param r Wert, der saveHatRuestung zugewiesen wird.
	 */
	public void setsaveHatRuestung(boolean r)
	{
		this.saveHatRuestung = r;
	}
	
	/**
	 * Setzt Variable saveHatRing.
	 * @param rg Wert, der saveHatRing zugewiesen wird.
	 */
	public void setsaveHatRing(boolean rg)
	{
		this.saveHatRing = rg;
	}
	
	/**
	 * Setzt Variable saveMaxHealth.
	 * @param mh Wert, der saveMaxHealth zugewiesen wird.
	 */
	public void setsaveMaxHealth(int mh)
	{
		saveMaxHealth = mh;
	}
	
	/**
	 * Setzt Variable saveMaxMana.
	 * @param mm Wert, der saveMaxMana zugewiesen wird.
	 */
	public void setsaveMaxMana(int mm)
	{
		saveMaxMana = mm;
	}
	
	/**
	 * Setzt Variable savewinpoints.
	 * @param n Wert, der savewinpoints zugewiesen wird.
	 */
	public void setsaveWinnerpoints(int n)
	{
		this.savewinpoints = n;
	}
	
	/**
	 * Setzt Variable savewinpoints2.
	 * @param n Wert, der savewinpoints2 zugewiesen wird.
	 */
	public void setsaveWinnerpoints2(int n)
	{
		this.savewinpoints2 = n;
	}
	
	/**
	 * Setzt Variable saveFireres.
	 * @param b Wert, der setFireres zugewiesen wird.
	 */
	public void setsaveFireres(boolean b)
	{
		this.saveFireres = b;
	}
	
	/**
	 * Setzt Variable saveUpMagicdmg.
	 * @param b Wert, der saveUpMagicdmg zugewiesen wird.
	 */
	public void setsavegetUpMagicdmg(boolean b)
	{
		this.saveUpMagicdmg = b;
	}
	
	/**
	 * Ruft das NPC Fenster auf, in dem die Steuerung und allgemeines erkl�rt wird.
	 */
	public void story()
	{
		timer.cancel();
		main.NPCstory();
	}

	/**
	 * Ruft das Nixequest Fenster auf.
	 */
	public void quest(){
		timer.cancel();
		main.NixeQuest();
	}
	
	/**
	 * Ruft das Froschquest Fenster auf.
	 */
	public void quest2() {
		timer.cancel();
		main.FroschQuest();
	}
	
	/**
	 * Ruft das Tauerfenster auf.
	 */
	public void taucher() {
		timer.cancel();
		main.Taucher();
	}

	/**
	 * �ffnet den Shop.
	 */
	public void shop()
	{
		timer.cancel();
		main.shop(player);
	}
	
	/**
	 * �ffnet das Levelupmenu.
	 * @param punkte Anzahl der Punkte, die vergeben werden k�nnen.
	 */
	public void playerLevelUp(int punkte)
	{
		timer.cancel();
		main.playerLevelUp(player, punkte);
	}	
	
	/**
	 * Gibt die Variable client zur�ck.
	 * @return
	 */
	public int getClient(){
		return this.client;
	}
	
	/**
	 * Setzt, ob Quest angenommen wurde.
	 * @param b Wert, der gesetzt wird.
	 */
	public void setQuest(boolean b){
		this.quest=b;
	}
	
	/**
	 * Setzt, ob Quest2 angenommen wurde.
	 * @param a Wert, der gesetzt wird.
	 */
	public void setQuest2 (boolean a){
		this.quest2=a;
	}
	
	/**
	 * Setzt, ob coop-Quest beendet wurde.
	 * @param b Wert, der gesetzt wird.
	 */
	public void setcoopquestfinish(boolean b){
		this.coopquestfinish=b;
	}
	
	/**
	 * Speichert die Position des Spielers in den Variablen player1X, player1Y.
	 */
	public void saveLocation1()
	{
		player1X = player.getPosX();
		player1Y = player.getPosY();
	}
	
	/**
	 * Gibt die Anzahl der insgesamt erhaltenen Erfahrung zur�ck.
	 * @return
	 */
	public int getTotalXP()
	{
		return totalxp;
	}
	
	/**
	 * Gibt die Erfahrung zur�ck, die seit dem letzten Levelup gesammelt wurde.
	 * @return
	 */
	public int getLevelXP()
	{
		return levelxp;
	}
	
	/**
	 * Gibt das Level des Spielers zur�ck.
	 * @return
	 */
	public int getPlayerlevel()
	{
		return this.playerLevel;
	}
	
	/**
	 * Berechnet das Level des Spielers.
	 */
	public void berechnePlayerlevel()
	{
		this.playerLevel = totalxp/3;
	}
	
	/**
	 * Berechnet die Erfahrung, die seit dem letzten Levelup gesammelt wurde.
	 */
	public void berechneLevelXP()
	{
		this.levelxp = totalxp%3;
	}
	
	/**
	 * Gibt quest2 zur�ck.
	 * @return
	 */
	public boolean getquest2() {
		return this.quest2;
	}
	
}
