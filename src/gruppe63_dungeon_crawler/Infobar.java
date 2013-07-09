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
		g.drawString("Lebenspunkte: " + player.getHealth(), 20, 20);
		g.drawString("Mana: " + player.getMana(), 20, 40);
		g.drawString("Geld: " + player.getMoney(), 200, 20);
		g.drawString("Healthpotions: " + player.getHealthpotions(), 200, 40);
		g.drawString("Leben: " + game.lifes, 350, 20);
		g.drawString("Manapotions: " + player.getManapotions(), 350, 40);
		g.drawString("MaxHealth: " + player.getMaxHealth(), 20, 30);
		if (game.getClient()!=0){
			g.drawString("Winnerpoints: " + player.getWinnerpoints(), 20, 50);
			g.drawString("Winnerpoints2: " + player.getWinnerpoints2(), 200, 50);
		}
		
	//g.dispose();
	}
	

}

