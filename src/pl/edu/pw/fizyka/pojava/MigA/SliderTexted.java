package pl.edu.pw.fizyka.pojava.MigA;


import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;

/**
 * Container for slider and text. 
 * 
 * Maybe spinners.
 * 
 * @author MK
 *
 */
public class SliderTexted extends JPanel {
	private static final long serialVersionUID = 8614982757356423063L;
	int max,min;
	int textSize;
	int div;
	public SliderTexted(){
		this.setLayout(new FlowLayout());
		max=1000;
		min=1;
		div=100;
		textSize=3;
		final JTextField text=new JTextField(String.valueOf((double)max/2/div),textSize);
		final JSlider slider= new JSlider(min,max);
        slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                text.setText(String.valueOf((double)slider.getValue()/div));
            }
        });
        
        this.add(slider);
        this.add(text);
	}
	
	//Ask about camelCase here->
	//Ask about slider length
	public SliderTexted(int Min,int Max,int Div,int TextSize){
		//this.setLayout(new FlowLayout());
		min=Min;
		max=Max;
		div=Div;
		textSize=TextSize;
		final JTextField text=new JTextField(String.valueOf((double)max/2/div),textSize);
		final JSlider slider= new JSlider(min,max);
        slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                text.setText(String.valueOf((double)slider.getValue()/div));
            }
        });
        
        this.add(slider);
        this.add(text);
	}
	
}