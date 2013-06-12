/**package gruppe63_dungeon_crawler;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

public class Healthbar extends Elements {

	 /**
	 * 
	 */
/**	private static final long serialVersionUID = 1L;
	public Healthbar(int x, int y){
		 super(x,y, 20);
	 }
	 
	 public void update(){
		 
	 }
	 public void draw(Graphics2D g2d){
				Image img = Toolkit.getDefaultToolkit().getImage("leben.png");
				g2d.drawImage(img, 0, 0, this);
				Toolkit.getDefaultToolkit().sync();
				g2d.dispose();
				}
	 }
**/
