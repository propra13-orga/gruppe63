package gruppe63_dungeon_crawler;

import java.net.*;
import java.io.*;



 
public class MultiServerThread extends Thread {
	
    private Socket s1 = null;
    private int x1=MultiServer.x1;
    private int y1=MultiServer.y1;
    private int x2=MultiServer.x2;
    private int y2=MultiServer.y2;
    private int wplocal;
    private int wpunlocal;
    private int wpc1unlocal=MultiServer.wpc1unlocal;
    private int wpc2unlocal=MultiServer.wpc2unlocal;
    
    private int Kennung;
    private int x;
    private int y;
    private int r;
    private int r1=MultiServer.r1;
    private int r2=MultiServer.r2;


    
    public MultiServerThread(Socket s1) {
    super("KKMultiServerThread");
    this.s1 = s1;
    }
 
    public void run() {
 
    try {
    	
        OutputStream os1=s1.getOutputStream();
        InputStream is1=s1.getInputStream();
       
        Kennung = is1.read();
        x=is1.read();
        y=is1.read();
        r=is1.read();
        wplocal=is1.read();
        wpunlocal=is1.read();

        
        if (Kennung==1) {
        	

        	
        	MultiServer.x1=x;
        	MultiServer.y1=y;
        	MultiServer.r1=r;
        	MultiServer.wpc1local=wplocal;
        	MultiServer.wpc1unlocal=wpunlocal;
        	
        	
        	os1.write(x2);
            os1.write(y2);
            os1.write(r2);
            os1.write((MultiServer.wpc2local+wpc1unlocal)/2);
            os1.write((MultiServer.wpc1local+wpc2unlocal)/2);
        	
        }
        
        if (Kennung==2) {
        	

        	
        	MultiServer.x2=x;
        	MultiServer.y2=y;
        	MultiServer.r2=r;
        	MultiServer.wpc2local=wplocal;
        	MultiServer.wpc2unlocal=wpunlocal;
        	
        	os1.write(x1);
            os1.write(y1);
            os1.write(r1);
            os1.write((MultiServer.wpc1local+wpc2unlocal)/2);
            os1.write((MultiServer.wpc2local+wpc1unlocal)/2);
        }

        
        s1.close();
 
    } catch (IOException e) {
        e.printStackTrace();
    
	}
    }

    
}



