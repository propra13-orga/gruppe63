package gruppe63_dungeon_crawler;
import java.net.*;
import java.io.*;
 
/**
 * 
 * Server für die Kommunikation von zwei Clients.
 * 
 * Der Server empfängt und sendet Informationen über Port 1231.
 *
 */
public class MultiServer implements Runnable{
	
	public static int x1=25;
	public static int y1=25;
	public static int x2=25;
	public static int y2=25;
	public static int r1=1;
	public static int r2=1;
	public static int wp1=0;
	public static int wp2=0;
	public static int wpp2c2=0;
	public static int wpp2c1=0;
	public static int wpc1local=0;
	public static int wpc2local=0;
	public static int wpc1unlocal=0;
	public static int wpc2unlocal=0;
	public static int down1=0;
	public static int down2=0;
	
	
	/**
	 * 
	 * Der Server horcht in einer Schleife an dem Port. Die Spielmechanik und die Kommunikation
	 * des Servers werden durch unterschiedliche Threads bearbeitet.
	 *
	 */
	@Override	
	public void run() {
		
     	
	  
		  try {ServerSocket ss1 = new ServerSocket(1231);
		  
		  while (true)
			  new MultiServerThread(ss1.accept()).start();
		} catch (IOException e) {
			System.err.println("Could not listen on port: 1231.");
            System.exit(-1);
		}
		
	}
	 
	}
	 

  

    


