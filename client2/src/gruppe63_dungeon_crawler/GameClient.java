package gruppe63_dungeon_crawler;

import java.awt.Color;
import java.awt.Container;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.awt.Rectangle;

public class GameClient extends JPanel implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MenuClient mainclient;
	private Container container;
	private Player player;
	private Leben leben;
	private Armor armor;
	private Mana mana;
	private Geld money;
	private Enemy enemy;
	private Boss boss;
	private Boss2 boss2;
	private Boss3 boss3;
	private Fireball fireball;
	private Infobar infobar;
	
	private int saveHealth=100;
	private int saveMoney=100;
	private int saveHealthpotions=0;
	private int saveManapotions=0;
	private int saveMana=100;
	private boolean saveHatRuestung;
	
	private int playerdamaged=0;
	private int moneyAmount=10;
	private int healAmount=10;
	private int manaAmount=10;
	
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

	public static int lifes = 5;
	int[][] Z;

	public int actualroom = 1;
	private int endroom = 9;

	int[] fromuser= new int[2];
	
	public GameClient(Container container, MenuClient menu) {

		this.container = container;
		this.mainclient = menu;
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
				
				


				// Spieler
				player.move();
				
				fromuser[0]=player.getX();
				fromuser[1]=player.getY();
				
	

				

				
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
		

		
		isleben=false;
		isarmor=false;
		ismana=false;
		ismoney=false;
		
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

		
			}
		}
		player.setHealth(saveHealth);
		player.setMana(saveMana);
		player.setMoney(saveMoney);
		player.setHealthpotions(saveHealthpotions);
		player.setManapotions(saveManapotions);
		player.setHatRuestung(saveHatRuestung);
		moneyAmount = 10;
		manaAmount = 10;
		healAmount = 10;

		mainclient.controller.setPlayer(player);
		room.paintRoom();
		container.add(room);
		room.repaint(100);
		
		
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
		mainclient.win(false);
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
			mainclient.win(true);
		} else {
			setsaveMoney(player.getMoney());
			setsaveHealth(player.getHealth());
			setsaveMana(player.getMana());
			setsaveHealthpotions(player.getHealthpotions());
			setsaveManapotions(player.getManapotions());
			setsaveHatRuestung(player.getHatRuestung());
			startRoom();
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
	
	public void story()
	{
		timer.cancel();
		//container.remove(room);
		mainclient.NPCstory();
	}

	public void shop()
	{
		timer.cancel();
		//container.remove(room);
		mainclient.shop(player);
	}
	
	
}