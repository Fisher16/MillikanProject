package pl.edu.pw.fizyka.pojava.MigA;

import java.awt.*;
import pl.edu.pw.fizyka.pojava.MigA.Droplet;
import javax.swing.*;

/**
 * Animation panel, graphic visualization functions. 
 * 
 * @author MK
 *
 */


public class AnimationPanel extends JPanel  {
	private static final long serialVersionUID = 3960445104787308557L;
	int gap=24;
	public Droplet drop=new Droplet(this);
	//true Y scale real always 2.5mm
	public double scl;


	public AnimationPanel() {
		this.setBackground(Color.darkGray);
	}
	

	@Override
	public void paintComponent(Graphics g) {
		scl=(double)this.getHeight()/0.0025;
	//	System.out.println(drop.y*scl);
		//droplet
        Graphics2D graph = (Graphics2D)g;
        graph.clearRect(0, 0, getWidth(), getHeight());            
        graph.setColor(Color.blue);
        drop.setx(this.getWidth()/2);
        graph.fillOval((int)(drop.x), (int)(drop.y*scl), 10,10);
        
	    //capacitor
	    g.setColor(Color.BLACK);
		int w=200;
        g.fillRect((this.getWidth()-w)/2,this.getHeight()-50, w,20);
        g.drawRect((this.getWidth()-w)/2,this.getHeight()-gap-70, w,20);
	   

	}
	
	
	public void setGap(int Gap){
		gap=Gap;
		this.repaint();
	}
	

	
}