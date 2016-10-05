import java.io.*;

public class Point extends Coordinate implements Formula, Serializable{
	String name;
	int ID;
	static int staticID;
	double ResultantField=0;
	
	public Point(){
		super();
		name="";
		staticID++;
		ID=staticID;
	}
	public Point(double xt, double yt, String nm){
		super(xt, yt);
		name=nm;
		staticID++;
		ID=staticID;
	}
	public void setName(String nm){
		name=nm;
	}
	public String getName(){
		return name;
	}
	public void setID(int idt){
		ID=idt;
	}
	public void setStaticID(int sid){
		staticID=sid;
	}
	public int getID(){
		return ID;
	}
	public int getStaticID(){
		return staticID;
	}
	public void setResultantField(double rf){
		ResultantField=rf;
	}
	public double getResultantField(){
		return ResultantField;
	}
	
	public double distance(Charge c){
		double d=Math.sqrt(Math.pow((c.getX()-x),2)+Math.pow((c.getY()-y),2));
		return d;
	}
	public double cos(Charge c){
		double cosans=0;
		try{
			if(distance(c)==0){
				throw new CoincideException();
			}
			cosans=(x-c.getX())/distance(c);
		}
		catch(CoincideException ce){
			System.out.println(ce.getMessage());
			System.out.println(ce.toString());
		}
		return cosans;
	}
	public double sin(Charge c){
		double sinans=0;
		try{
			if(distance(c)==0){
				throw new CoincideException();
			}
			sinans=(y-c.getY())/distance(c);
		}
		catch(CoincideException ce){
			System.out.println(ce.getMessage());
			System.out.println(ce.toString());
		}
		return sinans;
	}
	
	public String toString(){
		String str="The x coordinate is x="+this.getX()+" The y coordinate is y="+this.getY()+" The point is: "+name+"\nThe ResultantField is: "+ResultantField;
		return str;
	}
}