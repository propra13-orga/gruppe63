package gruppe63_dungeon_crawler;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Controller extends KeyAdapter {
	private Player player;

	public Controller(Player player) {
		this.player = player;
	}

	public Controller() {
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) { // Wenn Pfeiltaste gedrückt
			player.setMovX(-1); // dann stelle äquivalente Bewegungsrichtung ein
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
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) { // Wenn Taste losgelassen
			if (player.getMovX() == -1)
				player.setMovX(0); // dann setze Bewegungsvariablen zurück
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