package inheritance;

public class LargeAreaException extends Exception{
	public LargeAreaException(){
		getMessage();
		toString();
	}
	public String getMessage(){
		return "The area exceeds the value allowed";
	}
	public String toString(){
		return "A LargeAreaException occurred";
	}
}