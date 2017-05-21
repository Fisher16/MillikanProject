package pl.edu.pw.fizyka.pojava.MigA;

import java.awt.*;


import javax.swing.JPanel;
/**
 * Container made for testing 
 * 
 * @author MK
 *
 */
public class PanelCap extends JPanel {
	private static final long serialVersionUID = 8614982757356423063L;
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
