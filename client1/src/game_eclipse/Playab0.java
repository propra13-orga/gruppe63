package game_eclipse;

public class Playab0 {
	
	public static void playab0() {
		Level L = new Level0();	
		int b = Game1.play(L);
		if (b==1) {Playab1.playab1();}
		if (b==-1) {System.exit(0);}
		if (b==0) {Playab0.playab0();}
	}

}
