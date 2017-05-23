package pl.edu.pw.fizyka.pojava.MigA;
import java.util.Random;

import javax.swing.*;


public class Droplet {
	//position, diameter
	//everything in meters
	public double x,y;
	public double vy;
	public double ay;
	public double diam;
	
	//parameters
	//charge
	double e=1.60217*Math.pow(10,-19);
	public double charge=((int)((new Random()).nextInt(1000)))*e;
	//mass
	double m;
	
	//g acceleration
	double g=10;
	double coeff;
	//buoyancy force
	double bF;
	
	/*
	public Droplet() {


	}
	*/

	public Droplet(AnimationPanel Panel) {
		//start position
		x=Panel.getWidth()/2;
		y=0;
		vy=0;
		
		//Start parameters in meters
		diam=Math.pow(10, -6);
		double r=diam/2;
		double vol=4/3*3.1416*r*r*r;
		m=vol*920;
		bF=vol*1.2*g;
		ay=g-bF/m;
		coeff=6*3.1416*r*17*Math.pow(10, -6);
	}
	
	public void print(){
		System.out.println(m+ay+vy+y);
	}


	public void nextPos(double dt,AnimationPanel Panel, JSlider Voltage){
		if((Panel.scl*y)>(Panel.getHeight()-Panel.gap-60))ay=g-(((double)Voltage.getValue())/(Panel.gap/Panel.scl)*charge)/m-vy*coeff/m-bF/m;
		else ay=g-bF/m-vy*coeff/m;
		vy+=ay*dt;
		y+=vy*dt+ay*dt*dt/2;
		this.print();
	}
	public void setx(int X){
		x=X;
	}
	public void reset(){
		y=0;
		vy=0;
		ay=g;
		charge=((int)((new Random()).nextInt(1000)))*e;
	}
	
}