package pl.edu.pw.fizyka.pojava.MigA;

import java.awt.Color;
import java.awt.LayoutManager;


import java.awt.*;
import java.awt.geom.Line2D;

import javax.swing.*;

public class AnimationPanel extends JPanel {
	int width=this.getWidth();
	int height=this.getHeight();
	int dropx=width/2;
	int dropy=height/2;
	
	public AnimationPanel() {
		this.setBackground(Color.darkGray);
	}


	@Override
	protected void paintComponent(Graphics g) {

	    // Ustawiamy kolor kulek
	    setForeground(Color.BLUE);
	    Graphics2D g2 = (Graphics2D)g;

	    // Kasujemy obszar animacji
	    g2.clearRect(0, 0, getWidth(), getHeight());
	   
	    // Wyświetlamy kulki
	   this.print(g2);

	}

	private void print(Graphics2D g) {
	    g.fillOval((int) dropx, (int) dropy, 2,2);
	}
	
	/*public void iterate(double dt){

	    List<Ball> balls = ballContainer.getBalls();

	    for (int ii=0;ii<balls.size(); ii++){
	        Ball b1 = balls.get(ii);
	        checkCollisions(ballContainer, b1, ii);
	        b1.iteration(dt);
	    }
	}*/
	
	
	
	
	
	
	
	
}
