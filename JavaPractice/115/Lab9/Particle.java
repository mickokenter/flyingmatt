import java.text.*;
public class Particle {
	//instance variables
	private double a, b, c, d;
	private static final DecimalFormat THREE_FORMAT = new DecimalFormat("##0.000");
	
	//non-default constructor
	public Particle(double newA, double newB, double newC, double newD) {
		setA(newA);
		setB(newB);
		setC(newC);
		setD(newD);
	}
	
	//accessor methods
	public double getA() {
		return a;
	}

	public double getB() {
		return b;
	}

	public double getC() {
		return c;
	}

	public double getD() {
		return d;
	}

	//mutator methods
	public void setA(double newA) {
		a=newA;
	}

	public void setB(double newB) {
		b=newB;
	}

	public void setC(double newC) {
		c=newC;
	}

	public void setD(double newD) {
		d=newD;
	}
	
	public void table(double start, double end, double increment) {
	// add your code here
		double t = start;		
		int length = (int)((end-start)/increment+1);
		double[] location = new double[length];
		double[] velocity = new double[length];
		double[] change= new double[length];
		System.out.println("Time"+"\t"+"Location"+"\t"+"Velocity");
		for(int i=0; i<length; i++){
			location[i]=a*Math.pow(t,4)+b*Math.pow(t,3)+c*Math.pow(t,2)+d*t;
			velocity[i]=4*a*Math.pow(t,3)+3*b*Math.pow(t,2)+2*c*t+d;
			System.out.println(t+"\t"+THREE_FORMAT.format(location[i])+"\t"+"\t"+THREE_FORMAT.format(velocity[i]));
			if(i>0){
				if(Math.abs(location[i]-location[i-1])>1){
					System.out.println("More than 1 unit movement");
				}
			}
			t=t+increment;
		}
	}
	public String toString() {
		return "location(t) = " + a + "t^4+" + b + "t^3+" + c + "t^2+" + d + "t";
	}
}