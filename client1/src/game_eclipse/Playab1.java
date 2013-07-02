package game_eclipse;

public class Playab1 {
	
	public static void playab1() {
		Level L = new Level1();
		int b = Game1.play(L);
		if (b==2) {Playab2.playab2();}
		if (b==0) {Playab0.playab0();}
		if (b==1) {Playab0.playab0();}
		if (b==-1) {System.exit(0);}
		}

}
