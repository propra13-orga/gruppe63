package gruppe63_dungeon_crawler;

import java.awt.Color;
import java.awt.Container;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JPanel;

public class Game extends JPanel implements Runnable{

private Menu main;
private Container container;
private Player player;
private Enemy enemy;
private Boss boss;
private Room room;
private Timer timer;


public int actualroom=1;
private int endroom=3;


public Game(Container container, Menu menu) {

this.container=container;
this.main=menu;
}

public void run(){

startRoom();
TimerTask action = new TimerTask() {
public void run() {
player.move();
//enemy.move(); // Bewegung der Gegner.
//boss.move(); // Bewegung des Bossgegners.
}
};
timer = new Timer();
timer.schedule(action, 0, 5);
}

private void startRoom(){

container.setBackground(Color.WHITE);
container.removeAll();
room = new Room(50,50, actualroom, this); // (Elementwidth, Elementheight, Level, Game)

int[][] Z = Matrix.playedRoom(actualroom);

for (int i = 0; i < Z.length; i++) {
for (int j = 0; j < Z[i].length; j++) {
if(Z[i][j] == 4) {

player = new Player((j+1)*Room.elementheight,i*Room.elementwidth,room);
room.add(player);}

if (Z[i][j] == 2) {

enemy = new Enemy(j*Room.elementheight, i*Room.elementwidth, room);
room.add(enemy);}

if (Z[i][j] == 6) {
	
boss = new Boss(j*Room.elementheight, i*Room.elementwidth,room);
room.add(boss);}
}
}
main.controller.setPlayer(player);
room.paintRoom();
container.add(room);
room.repaint(100);
}

private void startRoomRev(){ // Muss noch überarbeitet werden.

container.setBackground(Color.WHITE);
container.removeAll();
room = new Room(50,50, actualroom, this); // (Elementwidth, Elementheight, Level, Game)
player = new Player(Matrix.Downy(actualroom)*Room.elementheight,Matrix.Downx(actualroom)*Room.elementwidth,room);
room.add(player);
main.controller.setPlayer(player);
room.paintRoom();
container.add(room);
room.repaint(100);
}


public void gameOver() {
timer.cancel();
timer.purge();
container.remove(room);
room=null;
main.win(false);
}

public void thisRoom() {
room.removeAll();
container.remove(room);
room=null;
startRoom();
}

public void nextRoom() {
actualroom++;
room.removeAll();
container.remove(room);
room=null;
if(actualroom>endroom){
timer.cancel();
timer.purge();
main.win(true);
}else {
startRoom();
}
}

public void lastRoom() {
if (actualroom>1) {
actualroom--;
room.removeAll();
container.remove(room);
room=null;
startRoomRev();
}

}


}


