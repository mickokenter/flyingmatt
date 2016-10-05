package inheritance;

public class triangle extends Figure{
	int base;
	double height;
	public triangle(){
		super();
		base=0;
		height=0;
	}
	public triangle(int l, String f, double p, String n, int b, double h){
		super(l,f,p,n);
		base=b;
		height=h;
	}
	public void setBase(int b){
		base=b;
	}
	public void setHeight(double h){
		height=h;
	}
	public int getBase(){
		return base;
	}
	public double getHeight(){
		return height;
	}
	
	public String toString(){
		String str=super.toString()+" The base is: "+base+" The height is: "+height;
		return str;
	}
	
	public double calculateArea(){
		double temp=0;
		try{
			temp=(base*height)/2;
			if(temp>10){
				throw new LargeAreaException();
			}
		}
		catch(LargeAreaException lae){
			lae.getMessage();
		}
		finally{
			if(temp>10){
				temp=10;
			}
		}
		return temp;
	}
}