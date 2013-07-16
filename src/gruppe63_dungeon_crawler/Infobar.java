package gruppe63_dungeon_crawler; 

import java.awt.Graphics;



@SuppressWarnings({ "serial" })
public class Infobar extends Elements {
	@SuppressWarnings("unused")
	private Room room;
	private Player player;
	private Game game;
		
	/**
	 * Konstruktor der Infobar.
	 * @param x x-Startkoordinate
	 * @param y y-Startkoordinate
	 * @param room Raum, in dem die Informationsleiste angezeigt wird.
	 * @param player Wichtig, um die Werte des Spielers abzufragen;
	 * dieser Parameter wird der Variable player zugewiesen.
	 * @param game Ebenfalls wichtig, um die Werte des Games abzufragen.
	 */
	public Infobar(int x, int y, Room room, Player player, Game game){
		//Groesse des Spielers
		super(x,y,600,50);
		this.room = room;
		this.player = player;
		this.game = game;
	}
	
	/**
	 * Wird bei jedem repaint() in Game aufgerufen.
	 * Hier werden die verschiedenen Elemente der Informationsleiste "gemalt".
	 */
	@SuppressWarnings("static-access")
	public void paintComponent(Graphics g){
		g.drawString("Lebenspunkte: " + player.getHealth() + " / " + player.getMaxHealth(), 20, 15);
		g.drawString("Mana: " + player.getMana() + " / " + player.getMaxMana(), 20, 45);
		g.drawString("Geld: " + player.getMoney(), 190, 15);
		g.drawString("Healthpotions: " + player.getHealthpotions(), 190, 45);
		g.drawString("Leben: " + game.lifes, 320, 15);
		g.drawString("Manapotions: " + player.getManapotions(), 190, 30);
		g.drawString("Playerlevel: " + game.getPlayerlevel(), 320, 30);
		g.drawString("Erfahrung: " + game.getLevelXP() + " / 3", 320, 45);
		if (game.getClient()!=0){
			g.drawString("Winnerpoints: " + player.getWinnerpoints(), 420, 15);
			g.drawString("Winnerpoints2: " + player.getWinnerpoints2(), 420, 45);
		}
		

	}
	

}

