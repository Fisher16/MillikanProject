package pl.edu.pw.fizyka.pojava.MigA;

public class Droplet {
	//position, diameter
	static int x,y;
	static int diam;
	//capacitor - distance and voltage of plates 
	static int dist;
	static int volts;
	
	public Droplet() {
		//start position
		x=230;
		y=80;
		//default 
		diam=20;
		dist=32;
		volts=5081;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Droplet drop =new Droplet();
	}

}
