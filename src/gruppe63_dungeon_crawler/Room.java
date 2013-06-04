package gruppe63_dungeon_crawler;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JPanel;

public class Room extends JPanel{

public static int elementwidth = 50; // Breite der Matrixelemente. Jan: Alle vier auf static geaendert!
public static int elementheight = 50; // Hoehe der Matrixelemente.
public static int roomwidth=12; // Breite der Raeume als Anzahl von Matrixelementen.
public static int roomheight=12; // Hoehe der Raeume als Anzahl von Matrixelementen.

int[][] room = Matrix.getMat("Room1.txt"); 

int[][] room2 = Matrix.getMat("Room2.txt");

int[][] room3 = Matrix.getMat("Room3.txt");

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
}
}

public Elements getElement(int x, int y){

int number=room[y][x];
switch (number){

case 1: return new Elements (this.elementwidth, this.elementheight, number);
case 3: return new Elements (this.elementwidth, this.elementheight, number);
case 4: return new Elements (this.elementwidth, this.elementheight, number);
case 5: return new Elements (this.elementwidth, this.elementheight, number);
case 2: return new Elements (this.elementwidth, this.elementheight, number);
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

int upright = room[(int) (y/this.elementheight)][(int) ((x+sizeplx)/this.elementwidth)]; // Ecke oben Rechts.
int downright = room[(int)((y+sizeply)/this.elementheight)][(int) ((x+sizeplx)/this.elementwidth)]; // Ecke unten Rechts.
int downleft = room[(int) ((y+sizeply)/this.elementheight)][(int) (x/this.elementwidth)]; // Ecke unten Links.
int upleft = room[(int) (y/this.elementheight)][(int) (x/this.elementwidth)]; // Ecke oben Links.

if (upright==2 || downright==2 || downleft==2 || upleft==2) return 2; // Es wird ausgegeben, was beruehrt wird.
else if (upright==1 || downright==1 || downleft==1 || upleft==1) return 1;
else if(upright==4 || downright==4 || downleft==4 || upleft==4) return 4;
else if (upright==5 || downright==5 || downleft==5 || upleft==5) return 5;
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
case 2 : if (Menu.difficulty==0) {
game.thisRoom();}
else {game.gameOver();}
break;
case 5:
game.nextRoom();
break;
case 4 :
game.lastRoom();
break;
default:
break;
}

}

}

