package gruppe63_dungeon_crawler;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Room extends JPanel {

	// Das mit den Sprite- bzw. Elementgroeﬂen muss wahrscheinlich nochmal
	// ueberarbeitet werden, das hab ich so nicht gut gemacht!

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static int elementwidth = 50; // Breite der Matrixelemente.
	public static int elementheight = 50; // Hoehe der Matrixelemente.
	public static int roomwidth = 12; // Breite der Raeume als Anzahl von
										// Matrixelementen.
	public static int roomheight = 12; // Hoehe der Raeume als Anzahl von
										// Matrixelementen.

	int[][] room = Matrix.getMat("Room1.txt");
	int[][] room2 = Matrix.getMat("Room2.txt");
	int[][] room3 = Matrix.getMat("Room3.txt");
	int[][] room4 = Matrix.getMat("Room4.txt");
	int[][] room5 = Matrix.getMat("Room5.txt");
	int[][] room6 = Matrix.getMat("Room6.txt");
	int[][] room7 = Matrix.getMat("Room7.txt");
	int[][] room8 = Matrix.getMat("Room8.txt");
	int[][] room9 = Matrix.getMat("Room9.txt");

	private Player player;
	private Game game;
	//private Leben leben;
	Image img;
	static BufferedImage background;
	boolean quit = false;
	//private Mana mana;
	//private Money money;
	//private Armor armor;
	//private Enemy enemy = new Enemy(1, 1, null);

	public Room(int elementwidth, int elementheigth, int level, Game game) {
		super();
		Room.elementwidth = elementwidth;
		Room.elementheight = elementheigth;
		this.setBounds(0, 0, 600, 650);
		this.setLayout(null);
		this.setVisible(true);

		this.game = game;

		switch (level) {
		case 1:
			break;
		case 2:
			this.room = this.room2;
			break;
		case 3:
			this.room = this.room3;
			break;
		case 4:
			this.room = this.room4;
			break;
		case 5:
			this.room = this.room5;
			break;
		case 6:
			this.room = this.room6;
			break;
		case 7:
			this.room = this.room7;
			break;
		case 8:
			this.room = this.room8;
			break;
		case 9:
			this.room = this.room9;
			break;
		}
	}

	public Elements getElement(int x, int y) { // Nur fuer unbewegliche Objekte.

		int number = room[y][x];
		switch (number) {

		case 1:
			return new Elements(Room.elementwidth, Room.elementheight, number);
			// case 3: return new Elements (this.elementwidth,
			// this.elementheight, number);
		case 4:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 5:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 2:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 7:
			return new Elements(Room.elementwidth, Room.elementheight, number);
			// case 6: return new Elements (this.elementwidth,
			// this.elementheight, number);
		case 8:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 9:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 13:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 12:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 10:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 11:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 14:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 15:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 16:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 17:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 18:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 20:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 21:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 22:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 23:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 24:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		case 25:
			return new Elements(Room.elementwidth, Room.elementheight, number);
		default:
			return null;
		}
	}

	public void paintRoom() {

		int x, y;	
	
		try {
	          img = getToolkit().getImage("res/hintergrund2.png");
	          MediaTracker mt = new MediaTracker( this );
	          mt.addImage( img,0);
	        }catch(Exception ex ) {
	          System.out.println(ex.toString());
	       }
		
		for (int i = 0; i < roomwidth; i++) {
			x = i * Room.elementwidth;
			for (int j = 0; j < roomheight; j++) {
				y = j * Room.elementheight;
				Elements E1 = this.getElement(i, j);

				if (E1 != null) {
					E1.setLocation(x, y);
					this.add(E1);

				}
			}
		}
		
	}


	public int Environment(int x, int y, int sizeplx, int sizeply) { // Untersuche,
																		// was
																		// die
																		// vier
																		// Ecken
																		// des
																		// Spielfigurenbildes
																		// beruehren.

		// Auch hier geht es nur um unbewegliche Objekte.

		int upright = room[(int) (y / Room.elementheight)][(int) ((x + sizeplx) / Room.elementwidth)]; // Ecke
																										// oben
																										// Rechts.
		int downright = room[(int) ((y + sizeply) / Room.elementheight)][(int) ((x + sizeplx) / Room.elementwidth)]; // Ecke
																														// unten
																														// Rechts.
		int downleft = room[(int) ((y + sizeply) / Room.elementheight)][(int) (x / Room.elementwidth)]; // Ecke
																										// unten
																										// Links.
		int upleft = room[(int) (y / Room.elementheight)][(int) (x / Room.elementwidth)]; // Ecke
																							// oben
																							// Links.

		if (upright == 2 || downright == 2 || downleft == 2 || upleft == 2)
			return 2; // Es wird ausgegeben, was beruehrt wird.
		else if (upright == 1 || downright == 1 || downleft == 1 || upleft == 1)
			return 1;
		else if (upright == 4 || downright == 4 || downleft == 4 || upleft == 4)
			return 4;
		else if (upright == 5 || downright == 5 || downleft == 5 || upleft == 5)
			return 5;
		// else if (upright==6 || downright==6 || downleft==6 || upleft==6)
		// return 6;
		else if (upright == 7 || downright == 7 || downleft == 7 || upleft == 7)
			return 7;
		else if (upright == 8 || downright == 8 || downleft == 8 || upleft == 8)
			return 8;
		else if (upright == 9 || downright == 9 || downleft == 9 || upleft == 9)
			return 9;
		else if (upright == 13 || downright == 13 || downleft == 13
				|| upleft == 13)
			return 13;
		else if (upright == 12 || downright == 12 || downleft == 12
				|| upleft == 12)
			return 12;
		else if (upright == 10 || downright == 10 || downleft == 10
				|| upleft == 10)
			return 10;
		else if (upright == 11 || downright == 11 || downleft == 11
				|| upleft == 11)
			return 11;
		else if (upright == 14 || downright == 14 || downleft == 14
				|| upleft == 14)
			return 14;
		else if (upright == 15 || downright == 15 || downleft == 15
				|| upleft == 15)
			return 15;
		else if (upright == 16 || downright == 16 || downleft == 16
				|| upleft == 16)
			return 16;
		else if (upright == 17 || downright == 17 || downleft == 17
				|| upleft == 17)
			return 17;
		else if (upright == 18 || downright == 18 || downleft == 18
				|| upleft == 18)
			return 18;
		else if (upright == 20 || downright == 20 || downleft == 20
				|| upleft == 20)
			return 20;
		else if (upright == 21 || downright == 21 || downleft == 21
				|| upleft == 21)
			return 21;
		else if (upright == 22 || downright == 22 || downleft == 22
				|| upleft == 22)
			return 22;
		else if (upright == 23 || downright == 23 || downleft == 23
				|| upleft == 23)
			return 23;
		else if (upright == 24 || downright == 24 || downleft == 24
				|| upleft == 24)
			return 24;
		else if (upright == 25 || downright == 25 || downleft == 25
				|| upleft == 25)
			return 25;
		else
			return 0;
	}

	public Component add(Player player) {
		Component component = super.add(player);
		this.player = player;
		return component;
	}
		
	public void paintComponent(Graphics g){
		g.drawImage(img,0,0,this);
		if(quit==true){
			g.drawString("Resume (R)",250,100);
			g.drawString("Main Menu (M)",250,150);
			g.drawString("Quit Game (Q)",250,200);
		}
	}
	
	public void status(Player player, Player2 player2) {
		int collision = Environment(player.x, player.y,player.xDim,
				player.yDim);
		
		if (Environment(player2.getX(), player2.getY(), player2.xDim,
				player2.yDim)==5) {player.incwpunlocal();}
		
		if (player.getRoom2()>Game.actualroom) {
				game.nextRoom();
			}
		
		switch (collision) {
		
		case 5:
						
			if (game.getDown() & Environment(player2.getX(), player2.getY(), player2.xDim,
					player2.yDim)==5) {
				game.nextRoom();
			}
			
			if (game.getDown()) {
			
			player.incwplocal();}
			
			
			
			break;
		case 4:
			//game.lastRoom(); // Jan: Dass man wieder nach oben gehen kann habe ich erstmal rausgenommen!
			break;
		case 7:
			game.shop(); // Shopmenue.
			break;
		case 8:
			// bossgegner
			break;
		case 9:
			// bossgegner
			break;
		case 14:
			// NPC
			game.story();
			
		case 20: //Nixe Quest verdienen von 20 xp
			game.quest();
			break;
		case 21: //Frosch Quest verdienen von Belohnung
			game.quest2();
			break;
		case 22: //Frosch Quest verdienen von Belohnung
			game.quest2();
			break;
		case 23: //Frosch Quest verdienen von Belohnung
			game.quest2();
			break;
		case 24: //Frosch Quest verdienen von Belohnung
			game.quest2();
			break;
		case 25: //Frosch Quest verdienen von Belohnung
			game.quest2();
			break;
		default:
			break;
		}

	}
	
	public void status() {
		int collision = Environment(player.x, player.y, player.xDim,
				player.yDim);
		switch (collision) {
		
		case 5:
			if (game.getDown()) {
				game.nextRoom();
			}
			break;
		case 4:
			//game.lastRoom(); // Jan: Dass man wieder nach oben gehen kann habe ich erstmal rausgenommen!
			break;
		case 7:
			game.shop(); // Shopmenue.
			break;
			
		case 20: //Nixe Quest verdienen von 10 xp
			game.quest();
			break;
			
		case 21: //Frosch Quest verdienen von Belohnung
			game.quest2();
			break;
		case 22: //Frosch Quest verdienen von Belohnung
			game.taucher();
			break;
		case 23: //Frosch Quest verdienen von Belohnung
			game.taucher();
			break;
		case 24: //Frosch Quest verdienen von Belohnung
			game.taucher();
			break;
		case 25: //Frosch Quest verdienen von Belohnung
			game.taucher();
			break;
		
		case 14:
			// NPC
			game.story();
		default:
			break;
		}

	}

}
