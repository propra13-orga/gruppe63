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
private Room room;
private Timer timer;


private int actualroom=1;
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
}
};
timer = new Timer();
timer.schedule(action, 0, 5);
}

private void startRoom(){

container.setBackground(Color.WHITE);
container.removeAll();
room = new Room(50,50, actualroom, this);
player = new Player(150,150,room);
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
startRoom();
}
}

}

