package pl.edu.pw.fizyka.pojava.MigA;

import java.awt.Dimension;

import javax.swing.*;

/**
 * Main class, container for rest. 
 * 
 * @author AR MK
 *
 */

public class MillikanExperiment extends JFrame {
	private static final long serialVersionUID = 8614982757356423063L;
	
	public MillikanExperiment(){
		setTitle("Millikan Exp");
		setSize(700, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(700,500));
		JTabbedPane tPane = new JTabbedPane();
		AnimationInterface exp=new AnimationInterface();
		
		tPane.addTab("Experiment",exp);
		
		tPane.addTab("Charts", new Chart(exp.velData,exp.accData,exp.posData,exp.dC));

		tPane.addTab("Info",new Info());
        this.add(tPane);
        
		
	}
	
	public static void main(String[] args) {
		MillikanExperiment window=new MillikanExperiment();
		window.setVisible(true);
		
	}

}
