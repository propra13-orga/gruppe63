package gruppe63_dungeon_crawler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
	private Player player;
	/*private Magic magic;
	//private Game game;
	/*boolean quit = false;

	public void paintComponent(Graphics g){
		if(quit==true){
		g.drawString("Resume (R)",250,100);
		g.drawString("Main Menu (M)",250,150);
		g.drawString("Quit Game (Q)",250,200);
	}}*/
	
	public Controller(Player player) {
		this.player = player;
	}
	/*public Controller(Magic magic) {
		this.magic = magic;
	}*/

	public Controller() {
		
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	/*public void setMagic(Magic magic) {
		this.magic = magic;
	}*/

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) { // Wenn Pfeiltaste gedrückt
			player.setMovX(-1); // dann stelle äquivalente Bewegungsrichtung ein
		    /*magic.setMovX(-1);*/
		}

		if (key == KeyEvent.VK_RIGHT) {
			player.setMovX(1);
			//if (key == KeyEvent.VK_ENTER){magic.setMovX(1);}
		}

		if (key == KeyEvent.VK_UP) {
			player.setMovY(1);
			//if (key == KeyEvent.VK_ENTER){magic.setMovY(1);}
		}

		if (key == KeyEvent.VK_DOWN) {
			player.setMovY(-1);
			//if (key == KeyEvent.VK_ENTER){magic.setMovY(-1);}
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
			
			//if(key == KeyEvent.VK_LEFT) {magic.setMovX(-1);}
			
			 
			if(player.getMana()>=5)
			{
		    	player.castMagic();
		    	player.setMana(player.getMana()-5);
			}
		}
		    
		if (key == KeyEvent.VK_SHIFT) {
		    	player.shootWeapon();
		 }
		
		/*if (key == KeyEvent.VK_ESCAPE) {
			quit = true;
	 }*/
	}
	

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) { // Wenn Taste losgelassen
			if (player.getMovX() == -1)
				player.setMovX(0); // dann setze Bewegungsvariablen zurück
			//if (key == KeyEvent.VK_ENTER){ magic.setMovX(0);}
		}

		if (key == KeyEvent.VK_RIGHT) {
			if (player.getMovX() == 1)
				player.setMovX(0);
			//if (key == KeyEvent.VK_ENTER){ magic.setMovX(0);}
		}

		if (key == KeyEvent.VK_UP) {
			if (player.getMovY() == 1)
				player.setMovY(0);
			//if (key == KeyEvent.VK_ENTER){ magic.setMovX(0);}
		}

		if (key == KeyEvent.VK_DOWN) {
			if (player.getMovY() == -1)
				player.setMovY(0);
			//if (key == KeyEvent.VK_ENTER){ magic.setMovX(0);}
		}
		if (key == KeyEvent.VK_1) { // Taste 1 fuer Nahkampf.
			player.setAttack(false);
		}
		/*if (key == KeyEvent.VK_ESCAPE) {
	    	quit= false;
	 }*/
	}

}