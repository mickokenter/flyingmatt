public class Charge extends Coordinate{
	double Q=0;
	int ID;
	static int staticID;
	
	public Charge(){
		super();
		Q=0;
		staticID++;
		ID=staticID;
	}
	public Charge(double xt, double yt, double qt){
		super(xt, yt);
		Q=qt;
		staticID++;
		ID=staticID;
	}
	
	public void setQ(double qt){
		Q=qt;
	}
	public void setID(int idt){
		ID=idt;
	}
	public void setStaticID(int sid){
		staticID=sid;
	}
	public double getQ(){
		return Q;
	}
	public int getID(){
		return ID;
	}
	public int getStaticID(){
		return staticID;
	}
	
	public String toString(){
		String str="The charge is: "+Q+"\nThe charge coordinates are: "+x+" "+y;
		return str;
	}
}