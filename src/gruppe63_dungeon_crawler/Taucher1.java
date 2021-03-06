package gruppe63_dungeon_crawler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Taucher1 extends Elements {

	/**
	 * Ist f�r den Taucherquest da/ ist den Items �hnlich 
	 * Taucherquest,  dieser und der erste m�ssen ber�hrt werden, um den Quest zu bestehen
	 * 
	 * @paint Taucher wird gezeichnet und aus spirit geladen
	 * @spirit spirit wird als Platzhalter f�r Image eingelesen und spaeter in paint durch Angabe der spiritvariable geladen.
	 * @super in public Taucher1  Angabe der Groe�e vom Item.
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private Room room;
	private int width;
	private int height;
	@SuppressWarnings("unused")
	private boolean alive=true;
	private Image sprite;

	public Taucher1(int x, int y, Room room) {
		super(x, y, 30, 30);
		this.room = room;
		sprite = Toolkit.getDefaultToolkit().getImage("res/taucher.gif");
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


