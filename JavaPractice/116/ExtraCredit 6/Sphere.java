public class Sphere extends Figure implements INT3{
	double radius;
	public double calculateArea(){
		double area=4*Math.PI*radius*radius;
		return area;
	}
	public double calculateVolume(){
		double volume=4/3*Math.PI*radius*radius*radius;
		return volume;
	}
}