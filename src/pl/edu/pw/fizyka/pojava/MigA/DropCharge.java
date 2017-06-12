package pl.edu.pw.fizyka.pojava.MigA;


/**
 * Class with meaningless existence made for passing double variable.
 * 
 * Used also for passing stop animation signal.
 * 
 * @author MK
 *
 */

public class DropCharge {
	public double calC;
	//flag for stopping animation
	public boolean saving=false;
	
	double e=1.60217*Math.pow(10,-19);
	
	public DropCharge(){
		calC=0;
	}
	public double ratio(){
		return calC/e;
	}
}
