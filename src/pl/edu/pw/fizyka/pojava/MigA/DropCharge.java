package pl.edu.pw.fizyka.pojava.MigA;


/**
 * Class with meaningless existence made for passing double variable.
 * 
 * @author MK
 *
 */

public class DropCharge {
	public double calC;
	double e=1.60217*Math.pow(10,-19);
	public DropCharge(){
		calC=0;
	}
	public double ratio(){
		return calC/e;
	}
}
