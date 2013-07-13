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
	private Leben leben;
	private Armor armor;
	private Mana mana;
	private Geld money;
	private Enemy enemy;
	private Enemyquest qenemy;
	private Ring ring;
	private Bubous bubous;
	private Taucher1 taucher1;
	private Taucher2 taucher2;
	private Boss boss;
	private Boss2 boss2;
	private Boss3 boss3;
	private Fireball fireball;
	private Infobar infobar;
	boolean quit = false;
	
	private Player2 player2;
	private int client;
	
	private int saveHealth=100;
	private int saveMoney=100;
	private int saveHealthpotions=0;
	private int saveManapotions=0;
	private int saveMana=100;
	private int saveMaxHealth=100;
	private boolean saveHatRuestung;
	@SuppressWarnings("unused")
	private boolean saveHatRing;
	private int savewinpoints=0;
	private int savewinpoints2=0;
	
	private int playerdamaged=0;
	private int moneyAmount=10;
	private int healAmount=10;
	private int manaAmount=10;
	
	private ArrayList<Enemy> enemies;
	private ArrayList<Enemyquest> qenemies;
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
	private boolean quest=false;
	private boolean quest2 = false;

	public static int lifes = 5;
	int[][] Z;

	public static int actualroom = 1;
	private int endroom = 9;

	public Game(Container container, Menu menu, int n) {

		this.container = container;
		this.main = menu;
		this.client=n;
	}
	
	int fireballtimer = 0;
	int fireballcounter = 0;

	public void run() {

		startRoom();
		Game.lifes=5;
		TimerTask action = new TimerTask() {
			public void run() {
				
				if (player.getHealth() <= 0) {
				saveHealth = 100;
				Game.lifes--;
				System.out.println(Game.lifes);
				startRoom();
					
				}
								
				// Gegner				
				for (int i = 0; i < enemies.size(); i++) {
				    Enemy e = (Enemy) enemies.get(i);
				    if (e.isVisible())
				        e.move();
				    else 
				    {
				    	enemies.remove(i);
				    	playerLevelUp();
				    }
				}
				//quenemy
				for (int i = 0; i < qenemies.size(); i++) {
				    Enemyquest eq = (Enemyquest) qenemies.get(i);
				    if (eq.isVisible())
				        ring.setVisible(false);//eq.move();
				    else 
				    {
				    	qenemies.remove(i);
				    	ring.setVisible(true);
				    }
				}
				// Spieler
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
               if (actualroom == 8) { bubous.setVisible(false);
				
				player.collision(taucher1);
				player.collision(taucher2);
				//if (player.collision1(taucher1)&& player.collision1(taucher2)) {
					/*taucher1.setVisible(false);
					taucher2.setVisible(false);
					room.remove(taucher1);
					room.remove(taucher2);*/
					bubous.setVisible(true);
				}

			//}
				//Ende Bubous quest
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

	void startRoom() {
		
				container.setBackground(Color.CYAN);
		container.removeAll();
		room = new Room(50, 50, actualroom, this); // (Elementwidth,
													// Elementheight, Level,
													// Game)
		
		enemies = new ArrayList<Enemy>();
		qenemies = new ArrayList<Enemyquest>();
		fireballs = new ArrayList<Magic>();
		arrows = new ArrayList<Weapon>();
		
		isleben=false;
		isarmor=false;
		ismana=false;
		ismoney=false;
/////////////////////////////////////////////////////////////////		
		isring=false;
/////////////////////////////////////////////////////////////////////		
		int[][] Z = Matrix.playedRoom(actualroom);

		for (int i = 0; i < Z.length; i++) {
			for (int j = 0; j < Z[i].length; j++) {
				if (Z[i][j] == 4) {

					player = new Player(j * Room.elementheight, i
							* Room.elementwidth, room);
					room.add(player);
					if(client!=0){player2 = new Player2(j * Room.elementheight, (i) * Room.elementwidth,room);
					room.add(player2);}
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
	                                   qenemies.add(qenemy);
	                                   room.add(qenemy);
	                                  }
				if (Z[i][j] == 24) {

	                   taucher1 = new Taucher1(j * Room.elementheight, i
	                                   * Room.elementwidth, room);
	                                  room.add(taucher1);
	                                  }
				if (Z[i][j] == 23) {

	                   taucher2 = new Taucher2(j * Room.elementheight, i
	                                   * Room.elementwidth, room);
	                                  room.add(taucher2);
	                                  }
////////////////////////////////////////////////////////////////////////////////////	                                  
			if (quest)	{if (Z[i][j] == 17) {

	                   ring = new Ring(j * Room.elementheight, i
	                                   * Room.elementwidth, room);
	                                  room.add(ring);
	                                  ring.setVisible(false);
	                                  isring=true;}
	                          }
			if(quest2){	if (Z[i][j] == 18) {

	                   bubous = new Bubous(j * Room.elementheight, i
	                                   * Room.elementwidth, room);
	                                  room.add(bubous);
	                                  bubous.setVisible(false);
	                          
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
		player.setwplocal(savewinpoints);
		player.setwpunlocal(savewinpoints2);
		moneyAmount = 10;
		manaAmount = 10;
		healAmount = 10;
		infobar = new Infobar(50,600,room,player, this);
		room.add(infobar);
		main.controller.setPlayer(player);
		room.paintRoom();
		container.add(room);
		room.repaint(100);
		
		
	}
	
	
	public void continueGame() {

		continueRoom();
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
				    else 
				    {
				    	enemies.remove(i);
				    	playerLevelUp();
				    }
				}
				// Spieler
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
				//Anfang NixeQuest
				if(quest){	if (actualroom == 5){
					player.collision(qenemy);
					qenemy.collision(player);
					if (qenemy.getHealth() <= 0) {
						room.remove(qenemy);
						qenemy.setDead();
						ring.setVisible(true);
					}	
					} //ring.setVisible(false);}
					
					for (int k = 0; k<fireballs.size(); k++) {
			        	Magic m = (Magic) fireballs.get(k);
			        	Rectangle rm = m.getBounds();
			        	Rectangle rb = qenemy.getBounds();
			        	if(rm.intersects(rb)) {
			        		m.setVisible(false);
			        		qenemy.setHealth(qenemy.getHealth()-50);
			        		System.out.println(qenemy.getHealth());
			        		
					}					
				}}
				
				if (actualroom == 5){
					player.collision(qenemy);
					qenemy.collision(player);
					if (qenemy.getHealth() <= 0) {
						room.remove(qenemy);
						qenemy.setDead();
						}	
					} 
					
					for (int k = 0; k<fireballs.size(); k++) {
			        	Magic m = (Magic) fireballs.get(k);
			        	Rectangle rm = m.getBounds();
			        	Rectangle rb = qenemy.getBounds();
			        	if(rm.intersects(rb)) {
			        		m.setVisible(false);
			        		qenemy.setHealth(qenemy.getHealth()-50);
			        		System.out.println(qenemy.getHealth());
			        		
					}}
			//Ende NixeQuest
           //Anfang Bubous Quest
           if (quest2){if (actualroom == 8) { bubous.setVisible(false);
	
	           player.collision(taucher1);
	           player.collision(taucher2);
	           
	           /*if (game.taucher()(player2.getX(), player2.getY(), player2.xDim,
						player2.yDim)==5) {
					bubous.setVisible(true);
				}
	           //if (player.collision1(taucher1)&& player.collision1(taucher2)) {
		/*taucher1.setVisible(false);
		taucher2.setVisible(false);
		room.remove(taucher1);
		room.remove(taucher2);*/
           }// bubous.setVisible(true);}
	           }
	//Ende Bubous quest
/////////////////////////////////////////////////////////////////////////
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
	
	public void continueRoom() {

		container.setBackground(Color.CYAN);
		container.removeAll();
		room = new Room(50, 50, actualroom, this); // (Elementwidth,
													// Elementheight, Level,
													// Game)
		
		enemies = new ArrayList<Enemy>();
		qenemies = new ArrayList<Enemyquest>();
		fireballs = new ArrayList<Magic>();
		arrows = new ArrayList<Weapon>();
		
		int[][] Z = Matrix.playedRoom(actualroom);

		for (int i = 0; i < Z.length; i++) {
			for (int j = 0; j < Z[i].length; j++) {
				if (Z[i][j] == 4) {

					player.setLocation(j * Room.elementheight, i * Room.elementwidth);
					room.add(player);
					if(client!=0){player2.setLocation((j + 1) * Room.elementheight, i * Room.elementwidth);
					room.add(player2);}
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
	                                  qenemies.add(qenemy);
	                                  qenemy.setVisible(false);
	                          }
			}
		}
		infobar = new Infobar(50,600,room,player, this);
		room.add(infobar);
		main.controller.setPlayer(player);
		room.paintRoom();
		container.add(room);
		room.repaint(100);
		//player.setHealth(saveHealth);
		//player.setMoney(saveMoney);
		
		
	}

	private void startRoomRev() { // Muss noch überarbeitet werden.

		container.setBackground(Color.CYAN);
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
			
			Rectangle rg = ring.getBounds();
			
			if (rp.intersects(rg)) {
		        
	        	//player.setMoney(player.getMoney()+this.moneyAmount);
	        	//moneyAmount = 0;
	        	ring.setVisible(false);
		        room.remove(ring);	
		        playerLevelUp();
	        }
        }
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
	        /*for (int y = 0; y<enemies.size(); y++) {
    	        Enemyquest eq = (Enemyquest) qenemies.get(y);
    	        Rectangle req = eq.getBounds();

    	        if (rp.intersects(req)) {
    	        	
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
    	        	}}
	

        	playerdamaged++;
        	player.collision(eq);
          		        	
        	}*/
	        	
	        	
	        
	        
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
		main.win(false, player);
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
			main.win(true, player);
		} else {
			setsaveMoney(player.getMoney());
			setsaveHealth(player.getHealth());
			setsaveMana(player.getMana());
			setsaveHealthpotions(player.getHealthpotions());
			setsaveManapotions(player.getManapotions());
			setsaveHatRuestung(player.getHatRuestung());
			setsaveMaxHealth(player.getMaxHealth());
			setsaveWinnerpoints(player.getWinnerpoints());
			setsaveWinnerpoints2(player.getWinnerpoints2());
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
	
	public void setsaveHealthpotions(int h)
	{
		this.saveHealthpotions = h;
	}
	
	public void setsaveManapotions(int m)
	{
		this.saveManapotions = m;
	}
	
	public void setsaveMana(int m)
	{
		this.saveMana = m;
	}
	
	public void setsaveHatRuestung(boolean r)
	{
		this.saveHatRuestung = r;
	}
	public void setsaveHatRing(boolean rg)
	{
		this.saveHatRing = rg;
	}
	
	public void setsaveMaxHealth(int mh)
	{
		saveMaxHealth = mh;
	}
	
	public void setsaveWinnerpoints(int n)
	{
		this.savewinpoints = n;
	}
	public void setsaveWinnerpoints2(int n)
	{
		this.savewinpoints2 = n;
	}
	
	public void story()
	{
		timer.cancel();
		//container.remove(room);
		main.NPCstory();
	}

	public void quest(){
		timer.cancel();
		main.NixeQuest();
	}
	public void quest2() {
		timer.cancel();
		main.FroschQuest();
	}
	public void taucher() {
		timer.cancel();
		main.Taucher();
	}
	/*public void gameStop1() {
		timer.cancel();
		main.gameStop();
	}*/
	

	public void shop()
	{
		timer.cancel();
		//container.remove(room);
		main.shop(player);
	}
	
	public void playerLevelUp()
	{
		timer.cancel();
		main.playerLevelUp(player);
	}	
	
	public int getClient(){
		return this.client;
	}
	public void setQuest(boolean b){
		this.quest=b;
	}
	public void setQuest2 (boolean a){
		this.quest2=a;
	}
	
}
