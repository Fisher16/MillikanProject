package pl.edu.pw.fizyka.pojava.MigA;
import javax.swing.*;

public class Droplet {
	//position, diameter
	public int x,y;
	public double vy;
	public double ay;
	public int diam;
	//capacitor - distance and voltage of plates 
	public int dist;
	public int volts;
	
	public Droplet() {
		//start position
		x=230;
		y=80;
		//default 
		diam=5;
		dist=32;
		volts=5081;
	}
	public Droplet(AnimationPanel Panel) {
		//start position
		x=(int)Panel.getWidth()/2;
		y=5;
		vy=0;
		ay=2;
		//default 
		diam=2;
		dist=32;//for calculations (in [m]) realdist=dist/(2*1000); aby zachowaæ skalê rysunku, nale¿a³oby tysi¹ckrotnie zwiêkszyæ odleg³oœc miêdzy ok³adkami kondensatora
		volts=5081;
	}
	/*public static void main(String[] args) {
		
	}*/
	public void nextPos(double dt,AnimationPanel Panel, JSlider Voltage){
		if(y>(Panel.getHeight()-Panel.gap-60))ay=2-(double)Voltage.getValue()/1000;
		else ay=2;
		vy+=ay*dt;
		y+=vy*dt+ay*dt*dt/2;
	}
	public void setx(int X){
		x=X;
	}
	public void reset(){
		y=5;
		vy=0;
		ay=2;
	}
	
}