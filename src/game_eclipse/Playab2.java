package game_eclipse;

public class Playab2 {
	
	public static void playab2() {
		Level L = new Level2();
		int b = Game1.play(L);
		if (b==3) {System.out.println("Du gewinnst! Das Spiel wird neu gestartet!"); Playab0.playab0();}
		if (b==1) {Playab1.playab1();}
		if (b==-1) {System.exit(0);}
		if (b==2) {Playab0.playab0();}
	}

}
