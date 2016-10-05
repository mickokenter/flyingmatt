public class TooLargeAreaException extends Exception{
	public TooLargeAreaException(){
		super();
	}
	public String getMessage(){
		return "The area calculated exceeds the limits";
	}
	public String toString(){
		return "TooLargeAreaException occurred";
	}
}