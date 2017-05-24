package pl.edu.pw.fizyka.pojava.MigA;
import java.util.Random;



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
	public double charge=((new Random()).nextInt(4)+1)*e;
	//mass
	double m;
	
	//g acceleration
	double g=10;
	//air resistance
	double coeff;
	//buoyancy force
	double bF;
	

	public Droplet(AnimationPanel Panel) {
		//start position
		x=Panel.getWidth()/2;
		y=0.001;
		vy=0;
		
		//Start parameters in meters
		diam=Math.pow(10, -6);
		double r=diam/2;
		double vol=4/3*3.1416*r*r*r;
		m=vol*920;
		bF=vol*1.2*g;
		ay=g;
		coeff=6*3.1416*r*17*Math.pow(10, -6);
	}
	

	public void nextPos(double dt,AnimationPanel Panel, SliderTexted Voltage){
		System.out.println(coeff);
		int n=100000;
		for(int i=0;i<n;++i){
			if((Panel.scl*y)>(Panel.getHeight()-Panel.gap-60))ay=g-(Voltage.getValue()/(Panel.gap/Panel.scl)*charge)/m-vy*coeff/m-bF/m;
			else ay=g-bF/m-vy*coeff/m;
		vy+=ay*dt/n;
		y+=vy*dt/n;
		}
	}
	public void setx(int X){
		x=X;
	}
	public void setCoeff(double viscosity){
		coeff=6*3.1416*diam*0.5*viscosity*Math.pow(10, -6);
	}
	public void reset(){
		y=0.001;
		vy=0;
		ay=g;
		charge=((new Random()).nextInt(4)+1)*e;
	}
	
}