package gruppe63_dungeon_crawler;

import java.net.*;
import java.io.*;
 
public class MultiServerThread extends Thread {
    private Socket s1 = null;
    private Socket s2 = null;
 
    public MultiServerThread(Socket s1, Socket s2) {
    super("KKMultiServerThread");
    this.s1 = s1;
    this.s2 = s2;
    }
 
    public void run() {
 
    try {
    	
        OutputStream os1=s1.getOutputStream();
        InputStream is1=s1.getInputStream();
        OutputStream os2=s2.getOutputStream();
        InputStream is2=s2.getInputStream();
       
        int a1 = is1.read();
        System.out.println("test" +a1); 
        int b1 = is1.read();
        System.out.println("test" +b1); 
       
        int a2 = is2.read();
        System.out.println("test" +a2);
        int b2 = is2.read();
        System.out.println("test" +b2); 
        
        
        
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