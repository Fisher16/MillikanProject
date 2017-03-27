package pl.edu.pw.fizyka.pojava.MigA;


import java.awt.*;
import java.awt.geom.Line2D;

import javax.swing.*;

/**
 * JPanel with animation
 * 
 * Method startAnim
 * 
 * @author AR
 *
 */

public class Animation extends JPanel
{
	private static final long serialVersionUID = 8614982757356423063L;
        //droplet diameter Z PLIKU 2RAD
		int diam=20;
		//start position
		int x=230, y=80;
		//velocity Z PLIKU
		int dy=17;
		//refreshing delay [ms]
		int delay=1000;
		//plate dist Z PLIKU
		int dist=32;
		//plate volts Z PLIKU
		int volts=5081;
		
		public void paintComponent(Graphics g){
	        super.paintComponent(g);
	        //droplet
	        Graphics2D graph = (Graphics2D)g;
	                    
	        graph.setColor(Color.blue);
	        //graph.drawOval(x, y, diam, diam+10);
	        graph.fillOval(x, y, diam, diam+10);
	        
	        //drawing capacitor
		
		    Line2D lin = new Line2D.Float(180, 400, 300, 400);
		    Line2D lin2 = new Line2D.Float(180, 400-dist, 300, 400-dist);
		    graph.draw(lin);
		    graph.draw(lin2);
	    }
		 public void startAnim(){
			 	int halfcap= 400-2*dist;
			    boolean condition=true;
		        while(condition){  
		        	//fall stop in capacitor 
		        	if(y>=halfcap && volts == 5081){
		        		condition=false;
			        
			        	}
			        	
		            //touching the ground
		        	if(y + diam+10 > getHeight() || y < 0){
		        		condition=false;
		        	}
		        	
		            // fall
		           
		            y += dy;
		        	
		            repaint(); 

		            try{
		                Thread.sleep(delay);
		            }
		            catch(InterruptedException e){
		            }
		        } }
		        public Dimension getPreferredSize(){
		            return new Dimension(480, 480);
		        }
		        
		
		        public static void main (String[] args) {

		            JFrame frame = new JFrame("Animation");
		            Animation anim  = new Animation();
		            frame.getContentPane().add(anim);
		            frame.setLocation(300, 20);
		            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		            frame.pack();
		            frame.setVisible(true);
		                // rozpoczecie animacji
		            anim.startAnim();
		        }

		    }
		        

