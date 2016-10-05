public abstract class Coordinate{
	public double x=0, y=0;
	public Coordinate(){
		x=0;
		y=0;
	}
	public Coordinate(double xt, double yt){
		x=xt;
		y=yt;
	}
	public void setX(double xt){
		x=xt;
	}
	public void setY(double yt){
		y=yt;
	}
	public double getX(){
		return x;
	}
	public double getY(){
		return y;
	}
	
	public String toString(){
		String str="X is: "+x+" Y is: "+y;
		return str;
	}
}