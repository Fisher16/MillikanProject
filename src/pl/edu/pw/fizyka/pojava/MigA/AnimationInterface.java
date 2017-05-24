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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.jfree.data.xy.XYSeries;

/**
 * Container for animation panel and interface. 
 * 
 * @author MK
 *
 */

public class AnimationInterface extends JPanel implements MouseListener, MouseMotionListener {
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
	    phField.setText("Calculated charge:");
	    phField.setEditable(false);
	    ph.add(phField);
	    
	    
	    c.gridx=0;c.gridy=7;
		c.gridwidth=3;c.gridheight=3;
		c.weightx =7;c.weighty=3;
		this.add(ph,c);
		
		
		//PH2 
		
	    JPanel ph2 = new JPanel();
	    ph2.setBorder(tBdescr);
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
            	//capacitor description
            	phField2.setText("Ionized droplet falls into the capacitor - due to electric field, there occurs additional force. Adjust the voltage, so that the droplet will remain steady. ");
            	
        	    //droplet description 
        	    //phField2.setText("Three forces act upon a falling droplet - gravitation, a buoyant force (Archimedes' principle), and a drag force (Stoke's law). Measuring the terminal velocity in the air allows to calculate the radius and the mass of droplet. ");
        	   
        	    //source of radiation
        	    //phField2.setText("Ionising radiation provides droplet with charge.");
            	
            }

            @Override
            public void mouseClicked(MouseEvent e) {}
        });
		
		
	}
	@Override
	public void mouseDragged(MouseEvent e) {}
	@Override
	public void mouseMoved(MouseEvent e) {
	/*	if (animationPanel.g.contains(e.getX(), e.getY()) ) {
            //mouse is inside the capacitor

        	phField2.setText("Ionized droplet falls into the capacitor - due to electric field, there occurs additional force. Adjust the voltage, so that the droplet will remain steady. ");
        	
			
        } else if(animationPanel.graph.contains(e.getX(), e.getY())){
        	//mouse inside the drop

    	    phField2.setText("Three forces act upon a falling droplet - gravitation, a buoyant force (Archimedes' principle), and a drag force (Stoke's law). Measuring the terminal velocity in the air allows to calculate the radius and the mass of droplet. ");
    	   
        } else {
            //mouse is outside
        	phField2.setText("The Millikan oil drop experiment investigates the charge on a single droplet of oil.");
     	    
        }*/
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	


}


