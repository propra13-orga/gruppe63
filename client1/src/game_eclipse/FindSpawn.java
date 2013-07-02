package game_eclipse;

public class FindSpawn {

public static int x(Level L) {

int n = 0;

int[][] Z = L.returnLvl();
for (int i = 0; i < Z.length; i++) {
for (int j = 0; j < Z[i].length; j++) {
if(Z[i][j] == 4) {
n = i;
}
}
}
return n;
}
public static int y(Level L) {

int n = 0;

int[][] Z = L.returnLvl();
for (int i = 0; i < Z.length; i++) {
for (int j = 0; j < Z[i].length; j++) {
if(Z[i][j] == 4) {
n = j;
}
}
}
return n;
}

}