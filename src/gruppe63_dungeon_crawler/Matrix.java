package gruppe63_dungeon_crawler;

import java.lang.String;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.util.StringTokenizer;


public class Matrix {

	public static int[][] playedRoom(int roomnumber) {

		if (roomnumber == 1) {
			return getMat("Room1.txt");
		}
		if (roomnumber == 2) {
			return getMat("Room2.txt");
		}
		if (roomnumber == 3) {
			return getMat("Room3.txt");
		}
		if (roomnumber == 4) {
			return getMat("Room4.txt");
		}
		if (roomnumber == 10) {
			return getMat("Room4_2.txt");
		}
		if (roomnumber == 5) {
			return getMat("Room5.txt");
		}
		if (roomnumber == 6) {
			return getMat("Room6.txt");
		}
		if (roomnumber == 7) {
			return getMat("Room7.txt");
		}
		if (roomnumber == 11) {
			return getMat("Room7_2.txt");
		}
		if (roomnumber == 8) {
			return getMat("Room8.txt");
		}
		if (roomnumber == 9) {
			return getMat("Room9.txt");
		} else {
			return null;
		}
	}

	public static int[][] getMat(String file) {

		int[][] matrix;

		String string;

		matrix = new int[Room.roomheight][Room.roomwidth];

		try {

			File fil = new File(file);
			FileInputStream fis = new FileInputStream(fil);
			InputStreamReader isr = new InputStreamReader(fis);
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(isr);

			for (int i = 0; i < 12; i++) {

				string = br.readLine();
				StringTokenizer tokenisedstring = new StringTokenizer(string,
						",");

				for (int j = 0; j < 12; j++) {

					matrix[i][j] = Integer
							.parseInt(tokenisedstring.nextToken());

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

	/*
	 * public static int Spawnx(int roomnumber) {
	 * 
	 * int n = 0;
	 * 
	 * int[][] Z = playedRoom(roomnumber);
	 * 
	 * for (int i = 0; i < Z.length; i++) { for (int j = 0; j < Z[i].length;
	 * j++) { if(Z[i][j] == 4) { n = i; } } } return n; }
	 * 
	 * 
	 * 
	 * public static int Spawny(int roomnumber) {
	 * 
	 * int n = 0;
	 * 
	 * int[][] Z = playedRoom(roomnumber);
	 * 
	 * for (int i = 0; i < Z.length; i++) { for (int j = 0; j < Z[i].length;
	 * j++) { if(Z[i][j] == 4) { n = j; } } } return n+1; }
	 */

	public static int Downx(int roomnumber) { // Kommt auch irgendwann weg.

		int n = 0;

		int[][] Z = playedRoom(roomnumber);

		for (int i = 0; i < Z.length; i++) {
			for (int j = 0; j < Z[i].length; j++) {
				if (Z[i][j] == 5) {
					n = i;
				}
			}
		}
		return n;
	}

	public static int Downy(int roomnumber) { // Kommt auch irgendwann weg.

		int n = 0;

		int[][] Z = playedRoom(roomnumber);

		for (int i = 0; i < Z.length; i++) {
			for (int j = 0; j < Z[i].length; j++) {
				if (Z[i][j] == 5) {
					n = j;
				}
			}
		}
		return n + 1;
	}

}
