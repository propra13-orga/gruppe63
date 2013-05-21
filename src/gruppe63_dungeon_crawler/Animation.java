package gruppe63_dungeon_crawler;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Animation extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private int posX;		// Position der Spielfigur
	private int posY;	
	private int movX;		// Bewegungsrichtung
	private int movY;
	
	private Image sprite;	// Spielfigursprite
	
	private Timer timer;	// Bildaktualisierungsrate

	// Bild einesen
    public Animation() {
        setBackground(Color.WHITE);
        ImageIcon i = new ImageIcon(this.getClass().getResource("circle2.png"));	// Lade Spielfigursprite
        sprite = i.getImage();    
    }
    
    // Bewegung um einen Schritt
    public void move() {
    	posX += movX;	// aktuelle Position + Bewegungsrichtung 
        posY += movY;
    }

    // Zeichenmethode
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(sprite, posX, posY, this);		// zeichnet den Sprite an der aktuellen Position

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }       
    
    // wird nach Ablauf der in Variable Timer eingestellten Zeit aufgerufen
    public void actionPerformed(ActionEvent e) {
        move();			// Errechnet Spielfigurbewegung
        repaint();  	// zeichne Bild neu, ohne Fenster zu aktualisieren
    }    

    // KeyEvent: Pfeiltasten gedrückt
    public void keyPressed(KeyEvent e) {
    	
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {		// Wenn Pfeiltaste gedrückt
            movX = -1;							// dann stelle äquivalente Bewegungsrichtung ein
        }

        if (key == KeyEvent.VK_RIGHT) {
            movX = 1;
        }

        if (key == KeyEvent.VK_UP) {
            movY = -1;
        }
        
        if (key == KeyEvent.VK_DOWN) {
            movY = 1;
        }
    }    
    
 // KeyEvent: Pfeiltasten loslassen
    public void keyReleased(KeyEvent e) {
    	int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {			// Wenn Taste losgelassen
            movX = 0;								// dann setze Bewegungsbariablen zurück
        }

        if (key == KeyEvent.VK_RIGHT) {
            movX = 0;
        }

        if (key == KeyEvent.VK_UP) {
            movY = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            movY = 0;
        }
    }
    
    
    // Funktionen zur Variablenverwaltung
    public int getPosX()
    {
    	return posX;
    }
    
    public int getPosY()
    {
    	return posY;
    }
    
    public int getMovX()
    {
    	return movX;
    }
    
    public int getMovY()
    {
    	return movY;
    }
    
    public void setPosX(int x)
    {
    	posX = x;
    }
    
    public void setPosY(int y)
    {
    	posY = y;
    }

	public Image getImage() {		
		return sprite;
	}
}