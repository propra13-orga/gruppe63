package gruppe63_dungeon_crawler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import javax.swing.JComponent;

/**
 * 
 * Statischen Elemente.
 *
 */

public class Elements extends JComponent {


	private static final long serialVersionUID = 1L;

	private int width;
	private int heigth;
	private int health;
	private int damage;
	
	int x, y;

	int vx, vy;

	int xDim, yDim;

	int number;

	protected int type;
	protected static boolean remove = false;

	public Elements(int x, int y, int xDim, int yDim) {
		this.xDim = xDim;
		this.yDim = yDim;
		this.x = x;
		this.y = y;

		this.setBounds(x, y, xDim, yDim);
	}

	public Elements(int xDim, int yDim, int number) {
		this.number = number;
		this.xDim = xDim;
		this.yDim = yDim;
		this.setBounds(0, 0, xDim, yDim);
	}

	public boolean getRemove() {
		return remove;
	}

	public void paintComponent(Graphics g) {
	
		switch (this.number) {
		case 1:
			Image img3 = Toolkit.getDefaultToolkit().getImage("res/oralle1.png");
		    g.drawImage(img3, 0, 0, this);
			break;
		case 4:
			Image img7 = Toolkit.getDefaultToolkit().getImage("res/eingang.gif");
		    g.drawImage(img7, 0, 0, this);
			break;
		//case 3:
		//	Image img1 = Toolkit.getDefaultToolkit().getImage("res/ghost.gif");
			//g.drawImage(img1, 0, 0, this);
			//g.finalize();
			//break;
		case 5:
			Image img8 = Toolkit.getDefaultToolkit().getImage("res/Ausgang.png");
		    g.drawImage(img8, 0, 0, this);
			break;
		case 7:
			Image img2 = Toolkit.getDefaultToolkit().getImage("res/shop.png");
			g.drawImage(img2, 0, 0, this);
			break;
		case 14:
			Image img4 = Toolkit.getDefaultToolkit().getImage("res/info.gif");
			g.drawImage(img4, 0, 0, this);
			break;
		case 20: //Quest 1  Nixe und Ring      finde meinen Schatz und du erh�lst 10xp
			Image img5 = Toolkit.getDefaultToolkit().getImage("res/nixe.gif");
			g.drawImage(img5, 0, 0, this);
			break;
		case 21: //Quest 2  Frosch und Freund      finde meinen Freund und du erh�lst eine Belohnung
			Image img6 = Toolkit.getDefaultToolkit().getImage("res/Frosch.gif");
			g.drawImage(img6, 0, 0, this);
			break;
		case 22: //Quest 2  Frosch und Freund      finde meinen Freund und du erh�lst eine Belohnung
			Image img9 = Toolkit.getDefaultToolkit().getImage("res/taucher.gif");
			g.drawImage(img9, 0, 0, this);
			break;
		case 23: //Quest 2  Frosch und Freund      finde meinen Freund und du erh�lst eine Belohnung
			Image img10 = Toolkit.getDefaultToolkit().getImage("res/taucher.gif");
			g.drawImage(img10, 0, 0, this);
			break;
		case 24: //Quest 2  Frosch und Freund      finde meinen Freund und du erh�lst eine Belohnung
			Image img11 = Toolkit.getDefaultToolkit().getImage("res/taucher.gif");
			g.drawImage(img11, 0, 0, this);
			break;
		case 25: //Quest 2  Frosch und Freund      finde meinen Freund und du erh�lst eine Belohnung
			Image img12 = Toolkit.getDefaultToolkit().getImage("res/taucher.gif");
			g.drawImage(img12, 0, 0, this);
			break;
		}

	}
	
	/**
	 * 
	 * Rechteck zur Kollisionsabfrage.
	 *
	 */
	public Rectangle getBounds() {
        return new Rectangle(x, y, width, heigth);
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
}
