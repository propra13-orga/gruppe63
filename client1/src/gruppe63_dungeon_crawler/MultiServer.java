package gruppe63_dungeon_crawler;
import java.net.*;
import java.io.*;
 
public class MultiServer {
   // public static void server() throws IOException{
	
	 public static void main(String args[]) throws IOException{
        
    
    	ServerSocket ss1 = null;
    	ServerSocket ss2 = null;
    	ServerSocket ss3 = null;
    	ServerSocket ss4 = null;
    	boolean listening = true;
	  
	  try {
		  
		
        	ss1 = new ServerSocket(1231);
          //  System.out.println("Connected One");
        	
           
            ss2=new ServerSocket(1232);       
          //  System.out.println("Connected Two");
           
            ss3 = new ServerSocket(1241);
            ss4= new ServerSocket(1242); 
            
            
            
            //int a1 = is1.read();
            //  int b1 = is1.read();
                          
          
                        
           // os2.write(a1);
           // os2.write(b1);
           // os2.write(a2);
           // os2.write(b2);
            
                       
            
           
            //os1.write(a1);
           // os1.write(b1);
            
            //s2.close();
           // ss2.close();
          //  s1.close();
          //  ss1.close();
             
         
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
            System.exit(-1);
        }
	  
	  while (listening) {
		  new MultiServerThread(ss1.accept(),ss2.accept()).start();
		  
		  if (Player.problem){
		  new MultiServerThread(ss3.accept(),ss2.accept()).start();}
	  }
	  
 ss1.close();
 ss2.close();
       
  }

    }


