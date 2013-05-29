package game_eclipse;

public class ProgressLevel {


public static int[][] returnProgLvl(int w, int d, int s, int a, int[][] A, Level L, int r) {

int[][] Z = L.returnLvl();

//if (Z[ FindSpawn.x(L)+s-w][ FindSpawn.y(L)+d-a] == 4){	
//System.out.println("Du bist geflohen!");	
//}

Z[ FindSpawn.x(L)+s-w][ FindSpawn.y(L)+d-a] = 3;


if (r == 1) {

if (Z[ FindSpawn.x(L)+s-w][ FindSpawn.y(L)+d-1-a]!=4) {
Z[ FindSpawn.x(L)+s-w][ FindSpawn.y(L)+d-1-a] = 0;
}
}
if (r == 2) {

if (Z[ FindSpawn.x(L)+s-1-w][ FindSpawn.y(L)+d-a]!=4) {
Z[ FindSpawn.x(L)+s-1-w][ FindSpawn.y(L)+d-a] = 0;
}
}

if (r == 3) {

if (Z[ FindSpawn.x(L)+s-w][ FindSpawn.y(L)+d-a+1]!=4) {
Z[ FindSpawn.x(L)+s-w][ FindSpawn.y(L)+d-a+1] = 0;
}
}


if (r == 4) {

if (Z[ FindSpawn.x(L)+s+1-w][ FindSpawn.y(L)+d-a]!=4) {
Z[ FindSpawn.x(L)+s+1-w][ FindSpawn.y(L)+d-a] = 0;
}
}


return Z;
}


}
