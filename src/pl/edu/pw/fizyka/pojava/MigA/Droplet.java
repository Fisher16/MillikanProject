package pl.edu.pw.fizyka.pojava.MigA;
import java.util.Random;

/**
 * Data object.
 * 
 * @author MK
 *
 */

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
	double g=9.8;
	//air resistance
	double coeff;
	//buoyancy force
	double bF;
	//for predictor corrector
	double vpre;

	public Droplet(AnimationPanel Panel) {
		//start position
		x=Panel.getWidth()/2;
		y=0.001;
		vy=0;
		vpre=vy;
		//Start parameters in meters
		diam=Math.pow(10, -6);
		double r=diam/2;
		double vol=4/3*3.1416*r*r*r;
		m=vol*920;
		bF=vol*1.2*g;
		ay=g;
		coeff=6*3.1416*r*17*Math.pow(10, -6);
	}
	
	//simple Euler calculation
	//old not used
	public void nextPos(double dt,AnimationPanel Panel, SliderTexted Voltage){

		int n=100000;
		System.out.println(dt/n+"\n"+vy);
		for(int i=0;i<n;++i){
			if((Panel.scl*y)>(Panel.getHeight()-Panel.gap-60))
				ay=g-(Voltage.getValue()/(Panel.gap/Panel.scl)*charge)/m-vy*coeff/m-bF/m;
			else ay=g-bF/m-vy*coeff/m;
		vy+=ay*dt/n;
		y+=vy*dt/n;
		}

	}
	//old not used

	//return acceleration given velocity and field strength
	public double acc(double vv, double field){
		return g-field*charge/m-(vv*coeff/m)-bF/m;
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
	
	//Predictor corrector (order=1)
	public void nextPosPre(double dt,AnimationPanel Panel, SliderTexted Voltage){
		int n=100000;
		double field=Voltage.getValue()/(Panel.gap/Panel.scl);
		dt/=n;
		
		for(int i=0;i<n;++i){
			if((Panel.scl*y)>(Panel.getHeight()-Panel.gap-60)){
				vpre=vy+acc(vy,field)*dt;
				ay=0.5*(acc(vy,field)+acc(vpre,field));
			}
			else{
				vpre=vy+acc(vy,0)*dt;
				ay=0.5*(acc(vy,0)+acc(vpre,0));
			}			
			vy+=ay*dt;
			y+=vy*dt+ay*dt*dt/2;
		}
	}
	
}