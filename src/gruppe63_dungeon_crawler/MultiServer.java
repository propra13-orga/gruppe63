package gruppe63_dungeon_crawler;
import java.net.*;
import java.io.*;
 
public class MultiServer {
	
	public static int x1=100;
	public static int y1=100;
	public static int x2=100;
	public static int y2=100;
	public static int r1=1;
	public static int r2=1;
	public static int wp1=0;
	public static int wp2=0;
	
    public static void server() throws IOException{
	
    	
       	ServerSocket ss1 = null;

    	boolean listening = true;
	  
	  try {
		  
		
        	ss1 = new ServerSocket(1231);
          
             
         
        } catch (IOException e) {
            System.err.println("Could not listen on port: 1231.");
            System.exit(-1);
        }
	  
	  while (listening) {
		  new MultiServerThread(ss1.accept()).start();}
		  
		
	  
 ss1.close();
 
	 }
	 }

       
  

    


