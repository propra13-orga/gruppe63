package game_eclipse;

import java.util.*;
public class Game1 {

public static int play(Level L) {

int[][] A = L.returnLvl();

Matrix.printMatrix(A);


int w = 0;
int s = 0;
int a = 0;
int d = 0;
int[][] T = A;

int b = L.Lvlnr();

while (true) {
	
	Scanner scanner = new Scanner(System.in);
	String t = scanner.nextLine();

//String t = IO.readln("w, d, s, a, exit");

if (t.equals("d")) {
if (LegalMove.legalMove(w,d+1,s,a,L)== true) {
	if (NextLevel.nextLvl(w,d+1,s,a,L)== false)	{
		if (Contact.contact(w,d+1,s,a,L)== false) {
			if (Levelup.levelup(w,d+1,s,a,L)==false){
			
d++;
Matrix.printMatrix(ProgressLevel.returnProgLvl(w,d,s,a,T,L,1));
}
			else {b--; break;}
}		
		else {if (Fight.fight()==false) break; else {d++;
		Matrix.printMatrix(ProgressLevel.returnProgLvl(w,d,s,a,T,L,1));}}
}
	else {b++; break;}
}
}


if (t.equals("w")) {
if (LegalMove.legalMove(w,d,s+1,a,L)== true) {
	if (NextLevel.nextLvl(w,d,s+1,a,L)== false)	{
		if (Contact.contact(w,d,s+1,a,L)== false) {
			if (Levelup.levelup(w,d,s+1,a,L)== false){

s++;
Matrix.printMatrix(ProgressLevel.returnProgLvl(w,d,s,a,T,L,2));
}
			else {b--; break;}
}
		else {if (Fight.fight()==false) break; else {w++;
		Matrix.printMatrix(ProgressLevel.returnProgLvl(w,d,s,a,T,L,2));}}
}
	else {b++; break; }
}
}


if (t.equals("a")) {
if (LegalMove.legalMove(w,d,s,a+1,L)== true) {
	if (NextLevel.nextLvl(w,d,s,a+1,L)== false)	{
		if (Contact.contact(w,d,s,a+1,L)== false) {
			if (Levelup.levelup(w,d,s,a+1,L)== false){
a++;
Matrix.printMatrix(ProgressLevel.returnProgLvl(w,d,s,a,T,L,3));
}
			else {b--;break;}
}		
		else {if (Fight.fight()==false) break; else {a++;
		Matrix.printMatrix(ProgressLevel.returnProgLvl(w,d,s,a,T,L,3));}}
}
	else {b++; break;}
}
}


if (t.equals("s")) {
if (LegalMove.legalMove(w+1,d,s,a,L)== true) {
	if (NextLevel.nextLvl(w+1,d,s,a,L)== false)	{
		if (Contact.contact(w+1,d,s,a,L)== false) {
			if (Levelup.levelup(w+1,d,s,a,L)== false){
w++;
Matrix.printMatrix(ProgressLevel.returnProgLvl(w,d,s,a,T,L,4));
}
			else{b--; break;}
}		
		else {if (Fight.fight()==false) break; else {s++;
		Matrix.printMatrix(ProgressLevel.returnProgLvl(w,d,s,a,T,L,4));}}
}
	else {b++; break;}
}
}


if (t.equals("exit")) {
b=-1;
break;
}

}



	
//System.out.println(b);
return b;

}
}



