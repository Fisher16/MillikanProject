package pl.edu.pw.fizyka.pojava.MigA;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.jfree.data.xy.XYSeries;

/**
 * Container for animation panel and interface. 
 * 
 * @author MK AR
 *
 */

public class AnimationInterface extends JPanel {
	private static final long serialVersionUID = 8614982757356423063L;
	public double tempo=0.1/2;
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
	    TitledBorder tBdescr = BorderFactory.createTitledBorder(lowerEtched, "Description");
	    TitledBorder tB = BorderFactory.createTitledBorder(lowerEtched, "Results ");
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
	    JTextArea phField=new JTextArea();
	    phField.setLineWrap(true);
	    phField.setWrapStyleWord(true);
	    ph.setLayout(new BorderLayout());
	    phField.setBackground(null);
	    phField.setText("Set the properties of animation using sliders. To calculate the charge and see charts go to 'Charts' section. There results can be saved by clicking 'Save' button - charts to .jpg, charge to results.txt files. ");
	    phField.setEditable(false);
	    ph.add(phField);
	    
	    
	    c.gridx=0;c.gridy=7;
		c.gridwidth=3;c.gridheight=3;
		c.weightx =7;c.weighty=3;
		this.add(ph,c);
		
		
		//PH2 
		
	    JPanel ph2 = new JPanel();
	    ph2.setBorder(tBdescr);
	    ph2.setMinimumSize(new Dimension(248,88));
	    JTextArea phField2=new JTextArea(4,16);
	    phField2.setLineWrap(true);
	    phField2.setWrapStyleWord(true);
	    phField2.setEditable(false);
	    phField2.setBackground(null);
	    ph2.setLayout(new BorderLayout());
	    JScrollPane scroll = new JScrollPane(phField2);
	    scroll.setBorder(null);
	    ph2.add(scroll,BorderLayout.CENTER); 
	    
	    c.gridx=3;c.gridy=0;
		c.gridwidth=2;c.gridheight=1;
		c.weightx = 2;c.weighty=3;
		this.add(ph2,c);
		
		
		//Labels		
		c.gridx=3;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx = 2;
		c.weighty=1;
		c.gridy=1;
		this.add(new JLabel("Viscosity [Pa·s]"),c);
		c.gridy=3;
		this.add(new JLabel("Distance [10^-4m]"),c);
		c.gridy=5;
		this.add(new JLabel("Voltage [V]"),c);
		c.gridy=7;
		this.add(new JLabel("Simulation Tempo [%]"),c);
		
		//Sliders & text
		//to do variable slider length
		c.gridx=3;
		c.gridwidth=2;
		c.gridheight=1;
		c.weightx = 2;
		c.weighty=1;
		
		//Viscosity
		c.gridy=2;
		SliderTexted sVis =new SliderTexted(0,2000,100,4);
		sVis.slider.setValue(1700);
		this.add(sVis,c);
		
		sVis.slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
            	animationPanel.drop.setCoeff((double)sVis.getValue());
            }
        });
		
		//Capacitor gap
		c.gridy=4;
		SliderTexted sDist =new SliderTexted(1,100,1,4);
		this.add(sDist,c);
		
		sDist.slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
            	animationPanel.setGap((int)sDist.getValue());
            }
        });
		
		//Voltage
		c.gridy=6;
		SliderTexted sVol=new SliderTexted(1,1000,100,4);
		this.add(sVol,c);
		
		//Timer setup
		c.gridy=8;
		SliderTexted sTime=new SliderTexted(1,1000,10,4);
		this.add(sTime,c);
		sTime.slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
            	tempo=0.001*(sTime.getValue());
            }
        });
		
		
	//Animation
		
		Timer timer = new Timer(10, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	animationPanel.repaint();
	            if(animationPanel.drop.y>-10&&(animationPanel.drop.y*animationPanel.scl)<(animationPanel.getHeight()-60)){
	            	animationPanel.drop.nextPosPre(tempo, animationPanel, sVol);
	            	animationPanel.repaint();
	            }else {
	            	animationPanel.drop.reset();
	            	tm=0;
	            	velData.clear();
	            	accData.clear();
	            	posData.clear();
	            }
	            
	            tm+=10;
	            if(tm%50==0){
	            	velData.add(tm/1000,animationPanel.drop.vy);
	            	accData.add(tm/1000,animationPanel.drop.ay);
	            	posData.add(tm/1000,animationPanel.drop.y);
	            	dC.calC=(animationPanel.drop.m*animationPanel.drop.g-animationPanel.drop.bF)*
	            			(animationPanel.gap/animationPanel.scl)/sVol.getValue();
	            }
	            if(sVis.getValue()==0){sTime.slider.setValue(1);sTime.slider.setEnabled(false);}
	            else sTime.slider.setEnabled(true);

	        }
	    });
		
		//Buttons
		c.gridx=3;
		c.gridwidth=1;
		c.gridheight=1;
		c.weightx = 1;
		c.weighty=1;
		c.fill = GridBagConstraints.BOTH;
		
		c.gridy=9;
		JPanel buttonPanel=new JPanel();
		buttonPanel.setLayout(new BorderLayout());
		
		//On/Off button
		JButton startButton = new JButton("ON");
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
		    	}
		    }
		});
		buttonPanel.add(startButton,BorderLayout.CENTER);
		
		//Reset button
		JButton resetButton = new JButton("RESET");
		resetButton.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	animationPanel.drop.reset();
		    	tm=0;
            	velData.clear();
            	accData.clear();
            	posData.clear();
		    }
		});
		buttonPanel.add(resetButton,BorderLayout.WEST);
		
		this.add(buttonPanel,c);
		
		
		
		
//TO DO
		/*Mouse Listener for information placeholder*/
		//add mouse listener to capacitor&drop separately
		
		  this.addMouseListener(new MouseListener() {

          @Override
          public void mouseReleased(MouseEvent e) { }

          @Override
          public void mousePressed(MouseEvent e) {}

          @Override
          public void mouseExited(MouseEvent e) { 

        	   phField2.setText("The Millikan oil drop experiment investigates the charge on a single droplet of oil.");
        	    
          }

          @Override
          public void mouseEntered(MouseEvent e) {
          	
          	
          }

          @Override
          public void mouseClicked(MouseEvent e) {}
      });
		
		
	this.addMouseMotionListener(new MouseMotionListener(){
	@Override
	public void mouseDragged(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {
		if(e.getY()>=225){
       //mouse is inside the capacitor

      	phField2.setText("Ionized droplet falls into the capacitor - due to electric field, there occurs additional force. Adjust the voltage, so that the droplet will remain steady. ");
      	
  	    
      }else if ( e.getY()>=100 && e.getY()<225 ) {
      	//mouse in radiation area

  	    phField2.setText("Ionising radiation provides the droplet with a negative charge. ");
  	  
			
      }else if ( e.getY()<=100 ) {
      	//mouse inside the drop

  	    phField2.setText("Three forces act upon a falling droplet - gravitation, a buoyant force (Archimedes' principle), and a drag force (Stoke's law). Measuring the terminal velocity in the air allows to calculate the radius and the mass of droplet. ");
  	  
			
      }
	}
	
	});
	}
	
	


}