package gruppe63_dungeon_crawler;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JPanel;

public class Room extends JPanel{

private int elementwidth;
private int elementheight;
private int roomwidth=12;
private int roomheight=12;

int[][] room = {
		{1,1,1,1,1,1,1,1,1,1,1,1},
		{1,0,0,0,0,0,0,0,0,0,0,1},
		{1,0,4,0,0,0,2,0,0,0,0,1},
		{1,0,0,0,0,0,0,0,0,0,0,1},
		{1,0,0,0,0,0,0,0,0,0,0,1},
		{1,0,1,1,1,0,1,0,0,0,0,1},
		{1,0,0,0,0,0,0,0,0,0,0,1},
		{1,0,0,0,5,0,0,0,0,0,0,1},
		{1,0,0,0,0,0,2,0,0,0,0,1},
		{1,0,0,0,0,0,0,0,0,0,0,1},
		{1,0,0,0,0,0,0,0,0,0,0,1},
		{1,1,1,1,1,1,1,1,1,1,1,1}
		};
		
int[][] room2 = {
		{1,1,1,1,1,1,1,1,1,1,1,1},
		{1,1,0,1,0,0,0,0,0,0,0,1},
		{1,0,0,0,0,0,0,0,0,0,0,1},
		{1,0,0,0,0,2,0,0,0,0,0,1},
		{1,4,0,0,0,1,0,0,0,0,0,1},
		{1,0,1,0,0,1,1,0,0,1,0,1},
		{1,0,1,0,0,0,0,0,0,1,0,1},
		{1,0,1,0,0,0,0,0,1,0,0,1},
		{1,0,0,1,0,0,0,1,0,5,0,1},
		{1,0,0,0,1,0,1,0,0,0,0,1},
		{1,0,0,2,0,1,0,0,0,0,0,1},
		{1,1,1,1,1,1,1,1,1,1,1,1}
		};

int[][] room3 = {
		{1,1,1,1,1,1,1,1,1,1,1,1},
		{1,0,0,0,0,0,0,1,0,0,0,1},
		{1,0,0,0,0,0,0,1,0,0,0,1},
		{1,0,0,0,0,0,0,0,0,0,0,1},
		{1,4,0,0,0,0,0,0,0,0,0,1},
		{1,0,1,0,0,0,0,0,0,0,0,1},
		{1,0,1,0,0,0,0,0,0,0,0,1},
		{1,0,1,0,0,0,0,0,1,0,0,1},
		{1,0,0,1,0,0,0,0,0,5,0,1},
		{1,0,0,0,1,0,0,0,0,0,0,1},
		{1,0,0,0,0,1,0,0,0,0,0,1},
		{1,1,1,1,1,1,1,1,1,1,1,1}
		};

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


public int Environment(int x, int y, int sizeplx, int sizeply) {

int up = room[(int) (y/this.elementheight)][(int) (x/this.elementwidth)];
int right = room[(int)(y/this.elementheight)][(int) ((x+sizeplx)/this.elementwidth)];
int down = room[(int) ((y+sizeply)/this.elementheight)][(int) (x/this.elementwidth)];
int left = room[(int) (y/this.elementheight)][(int) (x/this.elementwidth)];

if (up==2 || right==2 || down==2 || left==2) return 2;
else if (up==1 || right==1 || down==1 || left==1) return 1;
else if(up==4 || right==4 || down==4 || left==4) return 4;
else if (up==5 || right==5 || down==5 || left==5) return 5;
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
case 2 :
game.gameOver();
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

