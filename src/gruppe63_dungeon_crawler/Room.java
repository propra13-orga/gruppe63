package gruppe63_dungeon_crawler;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JPanel;

public class Room extends JPanel{

// Das mit den Sprite- bzw. Elementgroe�en muss wahrscheinlich nochmal ueberarbeitet werden, das hab ich so nicht gut gemacht!
	
public static int elementwidth = 50; // Breite der Matrixelemente.
public static int elementheight = 50; // Hoehe der Matrixelemente.
public static int roomwidth=12; // Breite der Raeume als Anzahl von Matrixelementen.
public static int roomheight=12; // Hoehe der Raeume als Anzahl von Matrixelementen.

int[][] room = Matrix.getMat("Room1.txt"); 
int[][] room2 = Matrix.getMat("Room2.txt");
int[][] room3 = Matrix.getMat("Room3.txt");
int[][] room4 = Matrix.getMat("Room4.txt");
int[][] room5 = Matrix.getMat("Room5.txt");
int[][] room6 = Matrix.getMat("Room6.txt");
int[][] room7 = Matrix.getMat("Room7.txt");
int[][] room8 = Matrix.getMat("Room8.txt");
int[][] room9 = Matrix.getMat("Room9.txt");

private Player player;
private Game game;

public Room(int elementwidth, int elementheigth, int level, Game game){
super();
this.elementwidth= elementwidth;
this.elementheight= elementheigth;
this.setBounds(0, 0, 600, 600);
this.setBackground(Color.WHITE);
this.setLayout(null);
this.setVisible(true);

this.game=game;

switch(level){
case 1: break;
case 2: this.room=this.room2; break;
case 3: this.room=this.room3; break;
case 4: this.room=this.room4; break;
case 5: this.room=this.room5; break;
case 6: this.room=this.room6; break;
case 7: this.room=this.room7; break;
case 8: this.room=this.room8; break;
case 9: this.room=this.room9; break;
}
}

public Elements getElement(int x, int y){ // Nur fuer unbewegliche Objekte.

int number=room[y][x];
switch (number){

case 1: return new Elements (this.elementwidth, this.elementheight, number);
//case 3: return new Elements (this.elementwidth, this.elementheight, number);
case 4: return new Elements (this.elementwidth, this.elementheight, number);
case 5: return new Elements (this.elementwidth, this.elementheight, number);
case 2: return new Elements (this.elementwidth, this.elementheight, number);
case 7: return new Elements (this.elementwidth, this.elementheight, number);
//case 6: return new Elements (this.elementwidth, this.elementheight, number);
default: return null;
}
}

public void paintRoom(){

int x,y;
for(int i=0; i<roomwidth;i++){
x=i*this.elementwidth;
for(int j=0; j< roomheight; j++){
y=j*this.elementheight;
Elements E1 = this.getElement(i,j);

if(E1!=null){
E1.setLocation(x,y);
this.add(E1);

}
}
}
}


public int Environment(int x, int y, int sizeplx, int sizeply) { // Untersuche, was die vier Ecken des Spielfigurenbildes beruehren.

// Auch hier geht es nur um unbewegliche Objekte.		
	
int upright = room[(int) (y/this.elementheight)][(int) ((x+sizeplx)/this.elementwidth)]; // Ecke oben Rechts.
int downright = room[(int)((y+sizeply)/this.elementheight)][(int) ((x+sizeplx)/this.elementwidth)]; // Ecke unten Rechts.
int downleft = room[(int) ((y+sizeply)/this.elementheight)][(int) (x/this.elementwidth)]; // Ecke unten Links.
int upleft = room[(int) (y/this.elementheight)][(int) (x/this.elementwidth)]; // Ecke oben Links.

if (upright==2 || downright==2 || downleft==2 || upleft==2) return 2; // Es wird ausgegeben, was beruehrt wird.
else if (upright==1 || downright==1 || downleft==1 || upleft==1) return 1;
else if(upright==4 || downright==4 || downleft==4 || upleft==4) return 4;
else if (upright==5 || downright==5 || downleft==5 || upleft==5) return 5;
//else if (upright==6 || downright==6 || downleft==6 || upleft==6) return 6;
else if (upright==7 || downright==7 || downleft==7 || upleft==7) return 7;
else return 0;
}

public Component add(Player player){
Component component=super.add(player);
this.player=player;
return component;
}

public void status() {
int collision = Environment(player.x, player.y,player.xDim, player.yDim);
switch(collision){
case 2 : if (Menu.getDifficulty()==0) {
game.thisRoom();}
else {game.gameOver();}
break;
case 5:
if (game.getDown()) {game.nextRoom();}
break;
case 4 :
game.lastRoom();
break;
case 7:
//Menu.shop(); // Shopmenue.
break;
//case 6:
//break;
default:
break;
}

}

}
