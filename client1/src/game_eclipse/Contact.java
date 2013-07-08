package game_eclipse;

public class Contact {

public static boolean contact(int w, int d, int s, int a, Level L) {

int[][] Z = L.returnLvl();

if (Z[ FindSpawn.x(L)+s-w][ FindSpawn.y(L)+d-a] == 2) {
System.out.println("Kampf");
return true;
}
return false;
}
}