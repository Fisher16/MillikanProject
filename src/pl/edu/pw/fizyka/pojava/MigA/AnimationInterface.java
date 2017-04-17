package pl.edu.pw.fizyka.pojava.MigA;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import pl.edu.pw.fizyka.pojava.MigA.SliderTexted;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Container for animation panel and interface. 
 * 
 * @author MK
 *
 */

public class AnimationInterface extends JPanel {
	private static final long serialVersionUID = 8614982757356423063L;
	public AnimationInterface(){
		//visual initialization
		this.setLayout(new GridBagLayout());
		GridBagConstraints c =new GridBagConstraints();
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	    TitledBorder tB = BorderFactory.createTitledBorder(lowerEtched, "Place Holder");
	    c.fill = GridBagConstraints.BOTH;
	    
		JPanel AnimationPanel=new JPanel();
		AnimationPanel.setBackground(Color.darkGray);
		
		c.gridx=0;c.gridy=0;
		c.gridwidth=3;c.gridheight=7;
		c.weightx = 7;c.weighty=7;
		this.add(AnimationPanel,c);
		

		//PH1
	    JPanel ph = new JPanel();
	    ph.setBorder(tB);
	    JFormattedTextField phField=new JFormattedTextField();
	    phField.setText("Place Holder");
	    phField.setEditable(false);
	    ph.add(phField);
	    
	    c.gridx=0;c.gridy=7;
		c.gridwidth=3;c.gridheight=3;
		c.weightx =7;c.weighty=3;
		this.add(ph,c);
		
		
		//PH2
	    JPanel ph2 = new JPanel();
	    ph2.setBorder(tB);
	    JFormattedTextField phField2=new JFormattedTextField();
	    phField2.setText("Place Holder");
	    phField2.setEditable(false);
	    ph2.add(phField2);
	    
	    c.gridx=3;c.gridy=0;
		c.gridwidth=1;c.gridheight=3;
		c.weightx = 1;c.weighty=3;
		this.add(ph2,c);
		
		
		//Labels		
		c.gridx=3;
		c.gridwidth=1;
		c.gridheight=1;
		c.weightx = 1;
		c.weighty=1;

		c.gridy=3;
		this.add(new JLabel("Distance [10^-3 m]"),c);
		c.gridy=5;
		this.add(new JLabel("Voltage [kV]"),c);
		c.gridy=7;
		this.add(new JLabel("Simulation Speed"),c);
		
		//Sliders & text
		//to do variable slider length
		c.gridx=3;
		c.gridwidth=1;
		c.gridheight=1;
		c.weightx = 1;
		c.weighty=1;
		
		c.gridy=4;
		this.add(new SliderTexted(1,2400,100,4),c);
		c.gridy=6;
		this.add(new SliderTexted(1,9000,1,4),c);
		c.gridy=8;
		this.add(new SliderTexted(1,1000,10,4),c);
		
		
		//On/Off button
		c.gridx=3;
		c.gridwidth=1;
		c.gridheight=1;
		c.weightx = 1;
		c.weighty=1;
		c.fill = GridBagConstraints.BOTH;
		
		c.gridy=9;
		JButton startButton = new JButton("ON");
		this.add(startButton,c);
		startButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	if(startButton.getText()=="ON"){
		    		startButton.setText("OFF");
		    		
		    	}
		    	else startButton.setText("ON");
		    }
		});
		
	}

}


