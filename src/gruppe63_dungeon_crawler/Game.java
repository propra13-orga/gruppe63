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
private Fireball fireball;
private Room room;
private Timer timer;
private boolean ball;
private boolean down=true;


public int actualroom=1;
private int endroom=9;


public Game(Container container, Menu menu) {

this.container=container;
this.main=menu;
}
int fireballtimer=0;
int fireballcounter=0;
public void run(){
	
startRoom();
TimerTask action = new TimerTask() {
public void run() {
if (player.getHealth()<=0) {gameOver();}

fireballtimer++;

player.move();
//enemy.move(); // Bewegung der Gegner.
if (actualroom==3 | actualroom==6 | actualroom==9) {down=false; if (fireballtimer % 1000==0) {fireball = new Fireball(boss.getPosX(),boss.getPosY(), room); room.add(fireball);ball=true;

/*switch ((int) (Math.random()*4)) {

case 0: fireball.setMovX(1); break;
case 1: fireball.setMovX(-1); break;
case 2: fireball.setMovY(1); break;
case 3: fireball.setMovY(-1); break;

}*/
fireballcounter++;}

if (ball) {
	
if (fireballcounter % 4==1) {fireball.setMovX(-1);}
if (fireballcounter % 4==2) {fireball.setMovY(-1);}
if (fireballcounter % 4==3) {fireball.setMovX(1);}
if (fireballcounter % 4==0) {fireball.setMovY(1);}

fireball.move();fireball.setMovX(0);fireball.setMovY(0);
 
 
/*System.out.println(fireball.getCollision());*/  if (fireball.getCollision()==1) {room.remove(fireball);ball=false;}

fireball.collision(player);}


// Bewegung des Bossgegners.
player.collision(boss);
if (boss.getHealth()<=0) {room.remove(boss); down=true;}

}
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
room.add(boss);
}
if (Z[i][j] == 8) {
	
boss = new Boss(j*Room.elementheight, i*Room.elementwidth,room);
room.add(boss);
}
if (Z[i][j] == 9) {
	
boss = new Boss(j*Room.elementheight, i*Room.elementwidth,room);
room.add(boss);
}
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

public boolean getDown() {
	return this.down;
}
}


