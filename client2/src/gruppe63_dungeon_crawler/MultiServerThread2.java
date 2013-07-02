package gruppe63_dungeon_crawler;

import java.net.*;
import java.io.*;
 
public class MultiServerThread2 extends Thread {
    private Socket s1 = null;
    private Socket s2 = null;
 
    public MultiServerThread2(Socket s1, Socket s2) {
    super("KKMultiServerThread");
    this.s1 = s1;
    this.s2 = s2;
    }
 
    public void run() {
 
    try {
    	
        OutputStream os1=s1.getOutputStream();
        OutputStream os2=s2.getOutputStream();
      
        os1.write(a2);
        os1.write(b2);
                
        os2.write(a1);
        os2.write(b1);
        
        System.out.println("test");
        
        s1.close();
        s2.close();
 
    } catch (IOException e) {
        e.printStackTrace();
    }
    }
}
