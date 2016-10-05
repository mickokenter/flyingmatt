package inheritance;

public class rectangle extends Figure{
	int baser;
	double heightr;
	public rectangle(){
		super();
		baser=0;
		heightr=0;
	}
	public rectangle(int l, String f, double p, String n, int b, double h){
		super(l,f,p,n);
		baser=b;
		heightr=h;
	}
	public void setBaser(int b){
		baser=b;
	}
	public void setHeightr(double h){
		heightr=h;
	}
	public int getBaser(){
		return baser;
	}
	public double getHeightr(){
		return heightr;
	}
	
	public String toString(){
		String str=super.toString()+" The rectangle base is: "+baser+" The rectangle height is: "+heightr;
		return str;
	}
	
	public double calculateArea(){
		double temp=0;
		try{
			temp=baser*heightr;
			if(temp>20){
				throw new LargeAreaException();
			}
		}
		catch(LargeAreaException lae){
			lae.getMessage();
		}
		finally{
			if(temp>20){
				temp=20;
			}
		}
		return temp;
	}

}