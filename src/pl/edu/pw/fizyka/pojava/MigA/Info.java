package pl.edu.pw.fizyka.pojava.MigA;

import java.awt.*;
import javax.swing.*;

/**
 * Panel with educational informations. 
 * 
 * @author AR
 *
 */


public class Info extends JPanel {

	private static final long serialVersionUID = 1L;

	public Info() {
		setLayout(new BorderLayout());
				
		JPanel ph = new JPanel();
		JPanel ph2 = new JPanel();
		
		ph.setMinimumSize(new Dimension(450,500));

	    
	    JTextArea phField=new JTextArea();
	    phField.setLineWrap(true);
	    phField.setWrapStyleWord(true);
	    phField.setBackground(null);
	    phField.setEditable(false);
	    
	    phField.setText("Between 1910 and 1911 Robert Millikan conducted an experiment to show that charge is quantized. "
	    	    +"He then determined a value for the fundamental quantum of charge – known as unit charge. Instead of measuring a "
	    	    +"single quantum of charge-that was impossible - he measured the charge on a number of oil drops and deduced that "
	    	    +"they could all be divided by a single factor – which must be the basic unit of charge. An oil drop will fall through "
	    	    +"air under its own weight. If the drop is given a charge, it can be suspended using an electric field. At this point "
	    	    +"the electrostatic force balances the weight of the drop. The size of the electrostatic force depends on the charge on "
	    	    +"the drop. So Millikan could work out the charge as long as he knew the weight.  \nIn order to find the weight of the"
	    	    +" drop, Millikan allowed the drop to fall through air. It quickly reaches its terminal velocity. At this point, the weight"
	    	    +" is being balanced by the viscous drag of the air. The drag can be calculated from Stokes' Law which allowed Millikan to "
	    	    +"determine the weight. Millikan repeated the experiment for over 150 oil drops. He selected 58 of his results and found the "
	    	    +"highest common factor."
	    	    +"That is, the single unit of charge which could be multiplied up to give the charge he measured on all his oil drops. \n"
	     
	    		+"The calculations \nWhen the oil drop is in the electric field, there is an electric force, F, acting upwards. This is given by:\n"
	    		+"F = Eq where q is the charge on the oil drop and E is the field strength.\nF= Vq/d where V is the voltage on the plates and d is their separation.\n"
	    		 
	    		+"The drop is being pulled down by its weight, mg. When the drop is suspended and stationary, the net force is zero. So:\n"
	    		+"Vq/d – mg = 0\nVq/d = mg  \nso q = mgd/V \n"
	    		+"To find the weight of the drop, Millikan let it fall through the air and measured its terminal velocity. At this point,"
	    		+"the net force is zero – i.e. weight is balanced by the viscous drag. The viscous drag, D, is given by:  \n"
	    		+"D = 6πηvr where η is the viscosity of air, r is the radius of a spherical drop and v is its speed. \n"
	    		+"This allowed him to work out the radius of the drop and therefore its weight.\n"
	    		+"Having measured the charge on a number of oil drops, q1, q2, q3, etc, Millikan reasoned that each charge must be a whole\n" 
	    		+"number multiple of the fundamental charge, e. So: \n"
	    		+"q1,= n1e, q2,= n2e, q3,= n3e and so on, where n is a whole number in each case.So he found e by finding the highest common"
	    		+" factor of all the values of charge that he measured on the oil drops.");
	    ph.setLayout(new GridLayout(1,1,30,30));
	    ph.add(phField);
	    
	    JScrollPane scroll = new JScrollPane(phField);
	    scroll.setBorder(null);
	    ph.add(scroll);
	    ImageIcon scheme = new ImageIcon(getClass().getClassLoader().getResource("scheme.jpg"));
		JLabel sLabel = new JLabel();
		sLabel.setIcon(scheme);
		sLabel.setIconTextGap(15);
	    ph2.add(sLabel);
		
	    
		this.add(ph2,BorderLayout.WEST);
	    this.add(ph,BorderLayout.CENTER);

	}
	
	

}
