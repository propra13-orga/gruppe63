package gruppe63_dungeon_crawler;

import java.awt.Color;
import java.awt.Component;
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

import javax.swing.JFrame;

public class Panel extends JFrame {

	private static final long serialVersionUID = 1L;
				
	public Panel() {
		
		add(new Level(1));							// erstes Level wird im Konstruktor aufgerufen, 1 ist Konstruktorparameter für Klasse Level
		setTitle("Dungeon Crawler");				
        setDefaultCloseOperation(EXIT_ON_CLOSE);	
        setSize(500, 500);							// Spielfeld 500 x 500 px
        setLocationRelativeTo(null);				// Bild erscheint in der Mittel des Bildschrims
        setVisible(true);							// Fenster sichtbar machen
        setResizable(false);						// Fenster nicht veränderbar
    }
	
    public static void main(String[] args) {
       Panel panel = new Panel();      				// erzeugt neues Panel und wird durch Konstruktor initialisiert
    }
    
    public static void loadLevel(int level, Panel panel)
    {
    	panel.getContentPane().removeAll();				// leere Panel, bevor neues Level geladen wird
        panel.getContentPane().add(new Level(level)); 	// neues Level einfügen  
    }
}