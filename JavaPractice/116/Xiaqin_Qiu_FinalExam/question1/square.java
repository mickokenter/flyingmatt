package inheritance;

public class square extends Figure{
	int side;
	public square(){
		super();
		side=0;
	}
	public square(int l, String f, double p, String n, int s){
		super(l,f,p,n);
		side=s;
	}
	public void setSide(int s){
		side=s;
	}
	public int getSide(){
		return side;
	}
	
	public String toString(){
		String str=super.toString()+" The side is: "+side;
		return str;
	}
	
	public double calculateArea(){
		double temp=0;
		try{
			temp=side*side;
			if(temp>100){
				throw new LargeAreaException();
			}
		}
		catch(LargeAreaException lae){
			lae.getMessage();
		}
		finally{
			if(temp>100){
				temp=100;
			}
		}
		return temp;
	}

}