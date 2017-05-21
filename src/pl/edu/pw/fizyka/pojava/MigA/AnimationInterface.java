package pl.edu.pw.fizyka.pojava.MigA;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

//import pl.edu.pw.fizyka.pojava.MigA.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.data.xy.XYSeries;

/**
 * Container for animation panel and interface. 
 * 
 * @author MK
 *
 */

public class AnimationInterface extends JPanel {
	private static final long serialVersionUID = 8614982757356423063L;
	public double tempo=0.1;
	public double tm=0;
	XYSeries velData = new XYSeries("Velocity");
	XYSeries accData = new XYSeries("Acceleration");
	XYSeries posData = new XYSeries("Position");
	
	
	DropCharge dC=new DropCharge();
	public AnimationInterface(){
		//visual initialization
		this.setLayout(new GridBagLayout());
		GridBagConstraints c =new GridBagConstraints();
		Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	    TitledBorder tB = BorderFactory.createTitledBorder(lowerEtched, "Place Holder");
	    c.fill = GridBagConstraints.BOTH;
	    
	    
	    //Animation Panel
		AnimationPanel animationPanel=new AnimationPanel();
		
		c.gridx=0;c.gridy=0;
		c.gridwidth=3;c.gridheight=7;
		c.weightx = 7;c.weighty=7;
		this.add(animationPanel,c);
		

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
		this.add(new JLabel("Distance [mm]"),c);
		c.gridy=5;
		this.add(new JLabel("Voltage [V]"),c);
		c.gridy=7;
		this.add(new JLabel("Simulation Tempo [%]"),c);
		
		//Sliders & text
		//to do variable slider length
		c.gridx=3;
		c.gridwidth=1;
		c.gridheight=1;
		c.weightx = 1;
		c.weighty=1;
		
		c.gridy=4;
		SliderTexted sDist =new SliderTexted(1,48,1,4);
		this.add(sDist,c);
		
		sDist.slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
            	animationPanel.setGap((int)sDist.getValue());
            }
        });
		
		c.gridy=6;
		SliderTexted sVol=new SliderTexted(1,1000,1,4);
		this.add(sVol,c);
		c.gridy=8;
		SliderTexted sTime=new SliderTexted(1,1000,10,4);
		this.add(sTime,c);
		sTime.slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
            	tempo=0.002*(sTime.getValue());
            }
        });
		
		//Chart Data
		
		velData.add(0,0);
		
		//Animation
		
		Timer timer = new Timer(10, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if(animationPanel.drop.y>-10&&animationPanel.drop.y<(animationPanel.getHeight()-60)){
	            	animationPanel.drop.nextPos(tempo, animationPanel, sVol.slider);
	            	animationPanel.repaint();
	            }else {
	            	animationPanel.drop.reset();
	            	tm=0;
	            	velData.clear();
	            }
	            tm+=10;
	            if(tm%100==0){
	            	velData.add(tm/1000,animationPanel.drop.vy);
	            	accData.add(tm/1000,animationPanel.drop.ay);
	            	posData.add(tm/1000,animationPanel.drop.y);
	            	dC.calC=10*animationPanel.gap/sVol.getValue();
	            }
	            
//for testing	
	           // System.out.println(tm/1000+"\n"+velData.getItemCount());
	           // System.out.println(animationPanel.drop.charge+"\n"+calC);
	           // System.out.println(sVol.getValue()+"\n");
	        

	        }
	    });
		
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
		    		timer.start();
		    	}
		    	else {
		    		timer.stop();
		    		startButton.setText("ON");
	//	    		System.out.println(dC.calC);
		    	}
		    }
		});
		
		
//TO DO
		/*Mouse Listener for information placeholder*/
		
		
		
		
		
		
		
		
		
		
		
		
	}

}


