package game_eclipse;

public class Levelup {
	
	
		public static boolean levelup(int w, int d, int s, int a, Level L) {

		int[][] Z = L.returnLvl();

		if (Z[ FindSpawn.x(L)+s-w][ FindSpawn.y(L)+d-a] == 4) {
		System.out.println("Eine Stufe weiter hoch.");
		return true;
		}
		return false;
		}
		}


