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
	public JTextField text;
	public JSlider slider;
	public SliderTexted(){
		this.setLayout(new FlowLayout());
		max=1000;
		min=1;
		div=100;
		textSize=3;
		text=new JTextField(String.valueOf((double)max/2/div),textSize);
		slider= new JSlider(min,max);
        slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                text.setText(String.valueOf((double)slider.getValue()/div));
            }
        });
        
        this.add(slider);
        this.add(text);
	}
	

	public SliderTexted(int Min,int Max,int Div,int TextSize){
		//this.setLayout(new FlowLayout());
		min=Min;
		max=Max;
		div=Div;
		textSize=TextSize;
		text=new JTextField(String.valueOf((double)max/2/div),textSize);
		slider= new JSlider(min,max);
        slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                text.setText(String.valueOf((double)slider.getValue()/div));
            }
        });
        
        this.add(slider);
        this.add(text);
	}
	public SliderTexted(int Min,int Max,String s){
		min=Min;
		max=Max;
		text=new JTextField(String.valueOf((max-min)/2),String.valueOf(max).length());
		slider= new JSlider(min,max);
        slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                text.setText(String.valueOf(slider.getValue()));
            }
        });
        this.add(new JLabel(s));
        this.add(slider);
        this.add(text);
	}
	public double getValue(){
		return (double)slider.getValue()/div;
	}
}