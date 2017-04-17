package pl.edu.pw.fizyka.pojava.MigA;

import java.awt.*;


import javax.swing.JPanel;

public class PanelCap extends JPanel {

	public PanelCap() {
		this.setBackground(Color.GRAY);
		this.setVisible(true);
	}
	
	@Override
    protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		 g.setColor(Color.BLACK);
		 int w=200;
         g.fillRect((this.getWidth()-w)/2,this.getHeight()-50, w,20);
         g.drawRect((this.getWidth()-w)/2,this.getHeight()-100, w,20);
	}

	

}
