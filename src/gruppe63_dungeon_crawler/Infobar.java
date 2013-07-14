package gruppe63_dungeon_crawler; 

import java.awt.Graphics;



@SuppressWarnings({ "serial" })
public class Infobar extends Elements {
	@SuppressWarnings("unused")
	private Room room;
	private Player player;
	private Game game;
		
	public Infobar(int x, int y, Room room, Player player, Game game){
		//Groesse des Spielers
		super(x,y,600,50);
		this.room = room;
		this.player = player;
		this.game = game;
	}
	
	@SuppressWarnings("static-access")
	public void paintComponent(Graphics g){
		g.drawString("Lebenspunkte: " + player.getHealth(), 20, 15);
		g.drawString("Mana: " + player.getMana(), 20, 45);
		g.drawString("Geld: " + player.getMoney(), 150, 15);
		g.drawString("Healthpotions: " + player.getHealthpotions(), 150, 45);
		g.drawString("Leben: " + game.lifes, 280, 15);
		g.drawString("Manapotions: " + player.getManapotions(), 150, 30);
		g.drawString("MaxHealth: " + player.getMaxHealth(), 20, 30);
		g.drawString("Playerlevel: " + game.getPlayerlevel() + " (Levelup bei 3xp)", 280, 30);
		g.drawString("Erfahrung: " + game.getLevelXP(), 280, 45);
		if (game.getClient()!=0){
			g.drawString("Winnerpoints: " + player.getWinnerpoints(), 400, 15);
			g.drawString("Winnerpoints2: " + player.getWinnerpoints2(), 400, 45);
		}
		
	//g.dispose();
	}
	

}

