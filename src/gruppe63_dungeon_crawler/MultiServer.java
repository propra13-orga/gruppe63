package gruppe63_dungeon_crawler;
import java.net.*;
import java.io.*;
 
public class MultiServer implements Runnable{
	
	public static int x1=100;
	public static int y1=100;
	public static int x2=100;
	public static int y2=100;
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
	 

  

    


