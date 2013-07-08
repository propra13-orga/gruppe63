package game_eclipse;

public class NextLevel {

public static boolean nextLvl(int w, int d, int s, int a, Level L) {

int[][] Z = L.returnLvl();

if (Z[ FindSpawn.x(L)+s-w][ FindSpawn.y(L)+d-a] == 5) {
System.out.println("Eine Stufe weiter runter.");
return true;
}
return false;
}
}