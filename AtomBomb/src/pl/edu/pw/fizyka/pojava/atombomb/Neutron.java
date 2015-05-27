package pl.edu.pw.fizyka.pojava.atombomb;

public class Neutron {
	double x,y,z;
	double vx,vy,vz;
	public Neutron(double a, double b, double c, double v) {
		x=a;
		y=b;
		z=c;
		double tetta=Math.random()*Math.PI;
		double fi=Math.random()*Math.PI*2;
		vx=v*Math.cos(tetta)*Math.cos(fi);
		vy=v*Math.cos(tetta)*Math.sin(fi);
		vz=v*Math.sin(tetta);
	}
	
	public boolean isOut(double a){
		if(x<0 || x>a || y<0 || y>a || z<0 || z>a)
			return true;
		else
			return false;
	}
	
	public void move(double time){
		x+=vx*time;
		y+=vy*time;
		z+=vz*time;
	}
	
	public double distance(Atom a){
		double d=Math.sqrt(Math.pow((a.x-x),2) +Math.pow((a.y-y),2) +Math.pow((a.z-z),2));
		return d;
	}
}
