package pl.edu.pw.fizyka.pojava.MigA;
import java.util.Random;

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
	//parameters
	//charge
	public double charge=((int)((new Random()).nextInt(100)))*0.016;
	//mass
	public double mass=1;
	public int eN;
	//g acceleration
	double g=10;
	double coeff;
	
	public Droplet() {
		//start position
		x=230;
		y=80;
		ay=g;
		//default 
		diam=5;
		dist=32;
		volts=5081;
	}
	public Droplet(AnimationPanel Panel) {
		//start position
		x=(int)Panel.getWidth()/2;
		y=0;
		vy=0;
		ay=g;
		diam=10;
		coeff=6*3.1416*diam*0.5*0.005;
		//default 
	
		dist=32;//for calculations (in [m]) realdist=dist/(2*1000); aby zachowa� skal� rysunku, nale�a�oby tysi�ckrotnie zwi�kszy� odleg�o�c mi�dzy ok�adkami kondensatora
		volts=5081;
	}
	/*public static void main(String[] args) {
		
	}*/
	public void nextPos(double dt,AnimationPanel Panel, JSlider Voltage){
		if(y>(Panel.getHeight()-Panel.gap-60))ay=g-(((double)Voltage.getValue())/Panel.gap*charge)-vy*coeff;
		else ay=g-vy*coeff;
		vy+=ay*dt;
		y+=vy*dt+ay*dt*dt/2;
	}
	public void setx(int X){
		x=X;
	}
	public void reset(){
		y=0;
		vy=0;
		ay=g;
		charge=((int)((new Random()).nextInt(100)))*0.016;
	}
	
}