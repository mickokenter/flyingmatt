public class Cube extends Figure implements INT3{
	double length;
	public double calculateArea(){
		double area=6*length*length;
		return area;
	}
	public double calculateVolume(){
		double volume=length*length*length;
		return volume;
	}
}