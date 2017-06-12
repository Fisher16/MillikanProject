package pl.edu.pw.fizyka.pojava.MigA;

import java.awt.*;
import java.awt.geom.*;
import java.awt.geom.RoundRectangle2D;

import pl.edu.pw.fizyka.pojava.MigA.Droplet;
import javax.swing.*;

/**
 * Animation panel, graphic visualization functions. 
 * 
 * @author MK AR
 *
 */


public class AnimationPanel extends JPanel  {
//MK
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
//MK
//AR	   
        //radiation source
        Graphics2D gh = (Graphics2D)g;
        g.setColor(Color.GRAY);
        gh.fill(new RoundRectangle2D.Double(10,this.getHeight()-gap-150,30, 15, 5, 5));
       
        CubicCurve2D c = new CubicCurve2D.Double();
        CubicCurve2D d = new CubicCurve2D.Double();
        CubicCurve2D e = new CubicCurve2D.Double();
  
	    c.setCurve(40, this.getHeight()-gap-145, 45,
	    		 this.getHeight()-gap-155, 50, this.getHeight()-gap-135, 60, this.getHeight()-gap-145);
	    d.setCurve(40, this.getHeight()-gap-140, 45,
	    		 this.getHeight()-gap-150, 50, this.getHeight()-gap-130, 60, this.getHeight()-gap-140);
	    e.setCurve(40, this.getHeight()-gap-135, 45,
	    		 this.getHeight()-gap-145, 50, this.getHeight()-gap-125, 60, this.getHeight()-gap-135);
	    g.setColor(Color.black);
	    gh.draw(c);
	    gh.draw(d);
	    gh.draw(e);
	}
	
	
	public void setGap(int Gap){
		gap=Gap;
		this.repaint();
	}
	

	
}