package gruppe63_dungeon_crawler;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

Game game;
ServerSocket serverSocket;
private Client client;
private Player player;

public Server() {
game = new Game();
gameLogic.addHero(new Hero(0, 0, gameLogic));
gameLogic.switchLevel("levels/TestLevel.xml");
try {
serverSocket = new ServerSocket(6116);
Socket clientSocket = serverSocket.accept();
client = new Client(clientSocket);
Thread t = new Thread(client);
t.setDaemon(true);
t.start();

} catch (IOException e) {
e.printStackTrace();
}
}


public static void main(String[] args) {
Thread t = new Thread(new Server());
t.start();
}

@Override
public void run() {
long last = System.nanoTime();
while(true){
try {
System.out.println((long)1e9/(System.nanoTime() - last));
//last = System.nanoTime();
player = client.getPlayer();
if(player != null){
game.tick(0, player);
}
client.send(new ServerResponse(game.getActors(),game.getHero()));
Thread.sleep(10);
} catch (InterruptedException e) {
e.printStackTrace();
}
}
}

}
