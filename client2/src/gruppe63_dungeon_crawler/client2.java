package gruppe63_dungeon_crawler;
import java.io.*;
import java.net.*;

public class client2 {
	
	
	   public static void send() {

			          try {
			             Socket ss = new Socket("Jan", 1222);
			             
			            // for (int i=0; i<200; i++) {
			             
			             InputStream is = ss.getInputStream();
			             OutputStream os = ss.getOutputStream();
			                 

			             
			          	 int x=50;
			             int y=150;
			             System.out.println("Client2:" + x);
			             System.out.println("Client2:" + y);   
			          	   
			             os.write(x);
			             os.write(y);
			             
			            /* int a1 = is.read();
			             int a2 = is.read();
			             int a3 = is.read();
			             int a4 = is.read();
			             
			             System.out.println("Client1:" + a1);
			             System.out.println("Client1:" + a2);
			             System.out.println("Client1:" + a3);
			             System.out.println("Client1:" + a4);*/
			             
			             //}
			             
			             ss.close();
			             
			             /*
			          	  
			          	  int a=4;//player.getX;
			          	  int b=5;//player.getY;
			          	  
			             out.write( a );
			             out.write( b );
			             System.out.println("Client2: " + a);
			             System.out.println("Client2: " + b);
			             
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
	 	
	
    public static void main(String[] args) throws IOException {

    	
while (true) {send();}
    	
    	
}
}