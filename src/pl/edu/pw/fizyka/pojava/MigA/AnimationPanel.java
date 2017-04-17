package pl.edu.pw.fizyka.pojava.MigA;

import java.awt.*;
//import java.awt.event.ActionListener;
//import java.awt.geom.Line2D;
import pl.edu.pw.fizyka.pojava.MigA.Droplet;
import javax.swing.*;

public class AnimationPanel extends JPanel  {
	private static final long serialVersionUID = 3960445104787308557L;
	int width=this.getWidth();
	int height=this.getHeight();
	int gap=0;
	//int dropx=width/2;
	//int dropy=height/2;
	
	//in ms
	int delay=1000;
	//center of capacitor
	int halfcap= height-2*Droplet.dist;
	public AnimationPanel() {
		this.setBackground(Color.darkGray);
	}
	

	@Override
	protected void paintComponent(Graphics g) {
	
		//droplet
        Graphics2D graph = (Graphics2D)g;
        graph.clearRect(0, 0, getWidth(), getHeight());            
        graph.setColor(Color.blue);
        
        graph.fillOval(Droplet.x, Droplet.y, Droplet.diam, Droplet.diam+10);     
        /*// capacitor
	
	    Line2D lin = new Line2D.Float(180, 400, 300, 400);
	    Line2D lin2 = new Line2D.Float(180, 400-Droplet.dist, 300, 400-Droplet.dist);
	    graph.draw(lin);
	    graph.draw(lin2);*/
        
	    //cap
	    g.setColor(Color.BLACK);
		int w=200;
        g.fillRect((this.getWidth()-w)/2,this.getHeight()-50, w,20);
        g.drawRect((this.getWidth()-w)/2,this.getHeight()-gap-70, w,20);
	   

	}
	
	
	public void setGap(int Gap){
		gap=Gap;
		this.repaint();
	}
	
	/* ActionListener taskPerformer = new ActionListener() {
	      public void actionPerformed(ActionEvent evt) {
	    	//fall stop in capacitor 
	        	if(y>=halfcap && Droplet.volts == 5081){
	        		stop();
		        
		        	}
		        	
	            //touching the ground
	        	else if(y + Droplet.diam+10 > getHeight() || y < 0){
	        		stop();
	        	}
	        	else{
	        		repaint();
	        	}
	      }
	  };
	  new Timer(delay, taskPerformer).start();

/*	private void print(Graphics2D g) {
		//drawing
	    g.fillOval((int) dropx, (int) dropy, 2,2);
	}*/
	
	/*public void iterate(double dt){
	    List<Ball> balls = ballContainer.getBalls();
	    for (int ii=0;ii<balls.size(); ii++){
	        Ball b1 = balls.get(ii);
	        checkCollisions(ballContainer, b1, ii);
	        b1.iteration(dt);
	    }
	}*/
	
	
	
	
	
	
	
	
}
