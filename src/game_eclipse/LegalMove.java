package game_eclipse;

public class LegalMove {

public static boolean legalMove(int w, int d, int s, int a, Level L) {

int[][] Z = L.returnLvl();

if (Z[ FindSpawn.x(L)+s-w][ FindSpawn.y(L)+d-a] == 1) {
System.out.println("Da ist ein Hindernis!");
return false;
}
return true;
}
}


