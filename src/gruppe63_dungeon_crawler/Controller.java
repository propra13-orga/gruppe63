package gruppe63_dungeon_crawler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
	private Player player;
	
	/**
	 * Konstruktor der Klasse.
	 * @param player Der player, der gesteuert werden soll.
	 */
	public Controller(Player player) {
		this.player = player;
	}

	public Controller() {
		
	}

	/**
	 * Setzt die Variable player auf Parameter player.
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Reagiert auf Tasten der Tastatur, falls diese gedr�ckt werden (der Parameter e), wird etwas ausgef�hrt.
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) { // Wenn Pfeiltaste gedr�ckt
			player.setMovX(-1); // dann stelle �quivalente Bewegungsrichtung ein
		}

		if (key == KeyEvent.VK_RIGHT) {
			player.setMovX(1);
		}

		if (key == KeyEvent.VK_UP) {
			player.setMovY(1);
		}

		if (key == KeyEvent.VK_DOWN) {
			player.setMovY(-1);
		}
		if (key == KeyEvent.VK_1) { // Taste 1 fuer Nahkampf.
			player.setAttack(true);
		}
		if (key == KeyEvent.VK_2)
		{
			if (player.getHealth() != player.getMaxHealth())
			{
				if (player.getHealthpotions() > 0)
				{
					if (player.getHealth()+10 < player.getMaxHealth())
					{
						player.setHealth(player.getHealth()+10);
						player.setHealthpotions(player.getHealthpotions()-1);
					}
					else
					{
						player.setHealth(player.getMaxHealth());
						player.setHealthpotions(player.getHealthpotions()-1);
					}
				}
			}
		}
		if (key == KeyEvent.VK_3)
		{
			if (player.getMana() != player.getMaxMana())
			{
				if (player.getManapotions() > 0)
				{
					if (player.getMana()+10 < player.getMaxMana())
					{
						player.setMana(player.getMana()+10);
						player.setManapotions(player.getManapotions()-1);
					}
					else
					{
						player.setMana(player.getMaxMana());
						player.setManapotions(player.getManapotions()-1);
					}
				}
			}
		}
		
		if (key == KeyEvent.VK_ENTER) {
			if(player.getMana()>=5)
			{
		    	player.castMagic();
		    	player.setMana(player.getMana()-5);
			}
		}
		    
		if (key == KeyEvent.VK_SHIFT) {
		    	player.shootWeapon();
		 }
		if (key == KeyEvent.VK_ESCAPE) {
	    	player.setescape(true);
	 }
	}
	

	/**
	 * Reagiert auf Tasten der Tastatur, falls diese losgelassen werden (der Parameter e), wird etwas ausgef�hrt.
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) { // Wenn Taste losgelassen
			if (player.getMovX() == -1)
				player.setMovX(0); // dann setze Bewegungsvariablen zur�ck
		}

		if (key == KeyEvent.VK_RIGHT) {
			if (player.getMovX() == 1)
				player.setMovX(0);
		}

		if (key == KeyEvent.VK_UP) {
			if (player.getMovY() == 1)
				player.setMovY(0);
		}

		if (key == KeyEvent.VK_DOWN) {
			if (player.getMovY() == -1)
				player.setMovY(0);
		}
		if (key == KeyEvent.VK_1) { // Taste 1 fuer Nahkampf.
			player.setAttack(false);
		}
	}

}