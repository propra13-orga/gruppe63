package gruppe63_dungeon_crawler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Ring extends Elements {

	/**
	 * Ring, der gefunden werden muss. Geh�rt der Nixe und kommt erst dann ins Spiel, wenn
	 * man ihren Quest akzeptiert.
	 * 
	 * @paint Ring wird gezeichnet und aus spirit geladen
	 * @spirit spirit wird als Platzhalter f�r das einlesen von Image wird eingelesen und spaeter in paint geladen.
	 * @super in public Ring  angabe der groe�e vom Item.
	 */
	private static final long serialVersionUID = 1L;

	private int width;
	private int height;
	private Image sprite;

	public Ring(int x, int y, Room room) {
		super(x, y, 30, 40);
		sprite = Toolkit.getDefaultToolkit().getImage("res/schmuck.gif");
		width = 30;
	    height = 40;
	    
	}

	private int collision = 0;
	Game game;

    public void paintComponent(Graphics g) {
		
		g.drawImage(sprite, 0, 0, this);
    }

	public int getCollision() {
		return collision;

	}

	public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

}