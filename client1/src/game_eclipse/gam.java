package game_eclipse;

import java.util.Scanner;


public class gam {

public static void main(String[] args) {

System.out.println("Willkommen bei Dungeoncrawlers");
System.out.println("1: Starten 2: Ende 3: Hilfe ; Bitte alle Eingaben durch Dr�cken der Eingabe-Taste best�tigen.");


while (true) {
	
Scanner scanner = new Scanner(System.in);
String t = scanner.nextLine();

if (t.equals("2")) {break;}

if (t.equals("1")) {
	
	System.out.println("w: hoch s: runter d: rechts a: links exit: Spiel komplett beenden ; Bitte alle Eingaben durch Dr�cken der Eingabe-Taste best�tigen.");
	Playab0.playab0();}

if (t.equals("3")) {
System.out.println("Hilfe");
System.out.println("Der Held (gef�llter Kreis) versucht das Ende des Dungeons (gef�lltes Rechteck) von seinem Startpunk (ungef�lltes Rechteck) zu erreichen.");
System.out.println("Dabei sollte er den Gegnern (ungef�llte Kreise) m�glichst ausweichen. Diese t�ten den Helden sehr wahrscheinlich, wenn sie ihn erwischen.");
System.out.println("Der Tot f�hrt zum Neustart des Spiels!");
System.out.println();
System.out.println("Innerhalb des Spiels: w: hoch s: runter d: rechts a: links exit: Spiel komplett beenden ; Bitte alle Eingaben durch Dr�cken der Eingabe-Taste best�tigen.");
System.out.println("Innerhalb des Men�s: 1: Starten 2: Ende 3: Hilfe ; Bitte alle Eingaben durch Dr�cken der Eingabe-Taste best�tigen.");
System.out.println("w�hle nun");
System.out.println();
System.out.println("1: Starten 2: Ende 3: Hilfe ; Bitte alle Eingaben durch Dr�cken der Eingabe-Taste best�tigen.");}



}
}
}


