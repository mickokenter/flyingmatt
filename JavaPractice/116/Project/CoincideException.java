public class CoincideException extends Exception{
	public CoincideException(){
		super();
	}
	public String getMessage(){
		return "The point is on the charge position";
	}
	public String toString(){
		return "CoincideException occurred";
	}
}