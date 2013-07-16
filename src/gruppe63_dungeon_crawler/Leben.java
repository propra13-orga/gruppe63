package gruppe63_dungeon_crawler;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

public class Leben extends Elements {

	/**
	 * Lebensitem. Wird ebenfalls in der Gameklasse implementiert sowie im Room und im Elements
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private Room room;
	private int width;
	private int height;
	@SuppressWarnings("unused")
	private boolean alive=true;
	
	private Image sprite;

	public Leben(int x, int y, Room room) {
		super(x, y, 30, 30);
		this.room = room;
		sprite = Toolkit.getDefaultToolkit().getImage("res/leben.png");
		width = 30;
	    height = 30;
	    
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
