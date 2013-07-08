package gruppe63_dungeon_crawler;
import java.io.*;
import java.net.*;

public class client {
    
      	private int otherX;
    	private int otherY;
    	
    	public void setotherXY() {
 
        try {
           Socket ss = new Socket("Jan", 1220);
                      
           OutputStream os = ss.getOutputStream();
           InputStream is = ss.getInputStream();
           
       
          
       /* int x = player1.getX();
         System.out.println("Client1:" + x);
        int y = player1.getY();
         System.out.println("Client1:" + y);
         
         os.write(x);
         os.write(y);*/
         
        // int a1 = is.read(); //player1 x
        // int a2 = is.read(); //player1 y
         
         
         int a3 = is.read(); //player2 x
         int a4 = is.read(); //player2 y
         
        
      
         
        /* System.out.println("Client1:" + a1);
         System.out.println("Client1:" + a2);
         System.out.println("Client1:" + a3);
         System.out.println("Client1:" + a4);*/
         
         ss.close(); /*
        	  
        	  int a=2;//player.getX;
        	  int b=3;//player.getY;
        	  
           out.write( a );
           out.write( b );
           System.out.println("Client: " + a);
           System.out.println("Client: " + b);
           
           int fromservera = in.read();
           int fromserverb = in.read();
           int fromserverc = in.read();
           int fromserverd = in.read();
           
           System.out.println("Server: " + fromservera);
           System.out.println("Server: " + fromserverb);
           System.out.println("Server: " + fromserverc);
           System.out.println("Server: " + fromserverd);
          
           
        out.close();
        in.close();
        kkSocket.close();*/
                                 
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: taranis.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: taranis.");
            System.exit(1);
        }
 

        }
    	
    	public static int getotherX(){
    		getotherXY();
    		return otherX;
    	}
    	
    	public static int getotherY(){
    		return this.otherY;
    	}
    	
    	public void setotherX(int x){
    		this.otherX=x;
    	}
    	public void setotherY(int y){
    		this.otherY=y;
    	}
    
    
}

