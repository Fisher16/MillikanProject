package pl.edu.pw.fizyka.pojava.MigA;

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
		setSize(640, 480);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		JTabbedPane tPane = new JTabbedPane();
		
		tPane.addTab("Eksperyment",new AnimationInterface());
		tPane.addTab("Wykres", new Chart());
        this.add(tPane);
        
		
	}
	
	public static void main(String[] args) {
		MillikanExperiment window=new MillikanExperiment();
		window.setVisible(true);
		
	}

}
