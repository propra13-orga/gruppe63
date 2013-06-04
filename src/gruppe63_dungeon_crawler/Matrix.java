package gruppe63_dungeon_crawler;

import java.lang.String;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.util.StringTokenizer;
import java.lang.Integer.*;

public class Matrix {
	
	
	
public static int[][] playedRoom(int roomnumber) {
	
if (roomnumber==1) {return getMat("Room1.txt");}
if (roomnumber==2) {return getMat("Room2.txt");}
if (roomnumber==3) {return getMat("Room3.txt");}
else {return null;}
}



public static int[][] getMat(String file){ 
		
int [][] matrix;

String string;

matrix = new int [Room.roomheight][Room.roomwidth];

try {

File fil = new File(file);
FileInputStream fis = new FileInputStream(fil);
InputStreamReader isr = new InputStreamReader(fis);
BufferedReader br = new BufferedReader(isr); 

for (int i=0; i<12; i++) {

string = br.readLine();
StringTokenizer tokenisedstring = new StringTokenizer(string,","); 

for (int j =0; j<12; j++) { 

matrix[i][j] = Integer.parseInt(tokenisedstring.nextToken());
	
} 
} 
}  

catch (java.io.FileNotFoundException e) { 
e.printStackTrace(); 
} 

catch (java.io.IOException e) { 
e.printStackTrace();
} 

return matrix;
}	
	


public static int Spawnx(int roomnumber) {

int n = 0;

int[][] Z = playedRoom(roomnumber);

for (int i = 0; i < Z.length; i++) {
for (int j = 0; j < Z[i].length; j++) {
if(Z[i][j] == 4) {
n = i;
}
}
}
return n;
}



public static int Spawny(int roomnumber) {

int n = 0;

int[][] Z = playedRoom(roomnumber);

for (int i = 0; i < Z.length; i++) {
for (int j = 0; j < Z[i].length; j++) {
if(Z[i][j] == 4) {
n = j;
}
}
}
return n+1;
}



public static int Downx(int roomnumber) {

int n = 0;

int[][] Z = playedRoom(roomnumber);

for (int i = 0; i < Z.length; i++) {
for (int j = 0; j < Z[i].length; j++) {
if(Z[i][j] == 5) {
n = i;
}
}
}
return n;
}



public static int Downy(int roomnumber) {

int n = 0;

int[][] Z = playedRoom(roomnumber);

for (int i = 0; i < Z.length; i++) {
for (int j = 0; j < Z[i].length; j++) {
if(Z[i][j] == 5) {
n = j;
}
}
}
return n+1;
}	
	
}
