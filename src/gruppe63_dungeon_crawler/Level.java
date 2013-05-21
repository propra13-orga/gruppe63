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

public class Level extends JPanel implements ActionListener {


    // Instanzvariablen
    private Image enemyImg;		// Gegnerbild
    private Image exitImg;		// Bild des Ziels
        
    private Timer timer;		// Bildaktualisierungsrate
    
    Animation player;			// Spielfigur
    
    int[] entrancePos = new int[2];		// Startposition
    int[] exitPos = new int[2];			// Zielposition
    int[][] enemyPos = new int[3][2];	// Gegnerposition
    
    //private int[][] matrix = new int[10][10];		//0 nichts 1 hindernis 2 feind 3 hero 4 türe runter(portal) 5 türe hoch(hero spawn) Startpunkt
    
          
    // Konstruktor
    public Level(int l) {					// übergibt dem Konstruktor die aktuelle Levelzahl
    	addKeyListener(new TAdapter());		// KeyListener hört die Tasten ab
        setFocusable(true);
        setBackground(Color.WHITE);			// Hintergrundfarbe weiß
        setDoubleBuffered(true);			// Graphikbuffer

        ImageIcon i = new ImageIcon(this.getClass().getResource("enemy.png"));	// Gegnerbild einlesen
        enemyImg = i.getImage();
        
        ImageIcon j = new ImageIcon(this.getClass().getResource("door.png"));	// Zielbild einlesen
        exitImg = j.getImage();
        
        player = new Animation();			// Spieler einrichten
        
        timer = new Timer(5, this);			// Bild nach 5 ms aktualisieren
        timer.start();						// Timer starten
              
        setBackground(Color.WHITE);		// Hintergrundfarbe weiß
        
        if(l==1)	// Konstruktorparameter 1, richte Level 1 ein
        {
        	entrancePos[0] = 20; 	entrancePos[1] = 30;	// Startposition für Spieler
        	exitPos[0] = 400;		exitPos[1] = 400;		// Zielposition
        	enemyPos[0][0] = 200;	enemyPos[0][1] = 400;	// Gegnerposition
        	enemyPos[1][0] = 30;	enemyPos[1][1] = 220;
        	enemyPos[2][0] = 300;	enemyPos[2][1] = 100;
        }
        else if(l==2)	// richtet Level 2 ein
        {
        	entrancePos[0] = 400; 	entrancePos[1] = 400;
        	exitPos[0] = 300;		exitPos[1] = 200;
        	enemyPos[0][0] = 100;	enemyPos[0][1] = 146;
        	enemyPos[1][0] = 431;	enemyPos[1][1] = 453;
        	enemyPos[2][0] = 124;	enemyPos[2][1] = 122;
        }
        else if(l==3)	// richtet Level 3 ein
        {
        	entrancePos[0] = 300; 	entrancePos[1] = 200;
        	exitPos[0] = 100;		exitPos[1] = 100;
        	enemyPos[0][0] = 153;	enemyPos[0][1] = 421;
        	enemyPos[1][0] = 222;	enemyPos[1][1] = 153;
        	enemyPos[2][0] = 112;	enemyPos[2][1] = 341;
        }
        
        player.setPosX(entrancePos[0]);	player.setPosY(entrancePos[1]);	// Initialisiert Spielfigur mit der Startposition des jeweiligen Levels
    }        
    
    // Zeichenmethode
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        
        for(int i=0;i<enemyPos.length;i++)
        	g2d.drawImage(enemyImg, enemyPos[i][0], enemyPos[i][1], this);	// zeichnet die Gegner
        
        g2d.drawImage(exitImg, exitPos[0], exitPos[1], this);				// zeichnet das Ziel
        
        g2d.drawImage(player.getImage(), player.getPosX(), player.getPosY(), this);	// zeichnet die Spielfigur
        
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }    
    
    // wird nach Ablauf des Timers aufgerufen
    public void actionPerformed(ActionEvent e) {
        player.move();	// bewegt Spielfigur
        repaint();  	// zeichnet Speilfeld anschließend neu
    }
    
    // überprüft, ob Pfeiltasten gedrückt und losgelassen werden
    private class TAdapter extends KeyAdapter {

    	public void keyPressed(KeyEvent e) {	// bei gedrückten Pfeiltasten Figur in die jeweilige Richtung bewegen
        	player.keyPressed(e);
        }
        public void keyReleased(KeyEvent e) {	// bei Loslassen der Tasten Bewegungsvariablen zurücksetzen
        	player.keyReleased(e);
        }
    }
}