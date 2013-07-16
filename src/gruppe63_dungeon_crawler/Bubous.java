package gruppe63_dungeon_crawler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Bubous extends Elements {

	/**
	 * ist der Freund des Frosches. Soll als questaufgabe gefunden werden. 
	 * Die Taucher verstecken Ihn. Es muss die richtige Kombination an Taucherkollision gefunden werde,
	 * um  Bubous zu finden. 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private Room room;
	private int width;
	private int height;
	@SuppressWarnings("unused")
	private boolean alive=true;
	private Image sprite;
/**
 * 
 * @param x  hier wird die x Variable aus der Klasse Room verwendet 
 * @param y  hier wird die y Variable aus der Klasse Room verwendet 
 * @param room  Es wird bekannt gegeben, dass aus dem Room  geladen werden soll.
 */
	public Bubous(int x, int y, Room room) {
		super(x, y, 30, 30);
		this.room = room;
		sprite = Toolkit.getDefaultToolkit().getImage("res/bubous.gif");
		width = 30;
	    height = 30;
	    
	}

    public void paintComponent(Graphics g) {
		
		g.drawImage(sprite, 0, 0, this);
    }
	
	public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}

