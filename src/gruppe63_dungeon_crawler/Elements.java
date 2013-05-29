package gruppe63_dungeon_crawler;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JComponent;

public class Elements extends JComponent {

int x,y;

int vx,vy;

int xDim,yDim ;

int number;

public Elements(int x, int y, int xDim, int yDim){
this.xDim = xDim;
this.yDim = yDim;
this.x=x;
this.y=y;
this.setBounds( x, y, xDim, yDim);
}

public Elements(int xDim, int yDim, int number){
this.number=number;
this.xDim=xDim;
this.yDim=yDim;
this.setBounds(0,0,xDim,yDim);
}


public void paintComponent(Graphics g){
switch (this.number){
case 1:
g.setColor(Color.black);
g.fillRect(0, 0, xDim, yDim);
break;
case 2:
g.setColor(Color.gray);
g.fillRect(0, 0, xDim, yDim);
break;
case 4:
g.setColor(Color.green);
g.fillRect(0, 0, xDim, yDim);
break;
case 3:
Image img1 = Toolkit.getDefaultToolkit().getImage("ghost.gif");
g.drawImage(img1, 0, 0, this);
g.finalize();
break;
case 5: 
g.setColor(Color.blue);
g.fillRect(0, 0, xDim, yDim);
break;
}

}





}