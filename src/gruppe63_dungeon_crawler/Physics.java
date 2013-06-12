package gruppe63_dungeon_crawler;

import java.awt.Rectangle;

public class Physics {
	
	public static boolean collision (Elements go1, Elements go2){
		
		Rectangle l1 = new Rectangle ((int)go1.getX(), (int)go1.getY(),(int)go1.getWidth(),(int)go1.getHeight() );
        Rectangle r2 = new Rectangle ((int)go1.getX(), (int)go1.getY(),(int)go1.getWidth(),(int)go1.getHeight());
        
        
        return r2.intersects(l1);
        
	}
	
	

}
