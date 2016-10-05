public class TooLargeCostException extends Exception{
	public TooLargeCostException(){
		super();
	}
	public String getMessage(){
		return "The cost calculated exceeds the limits";
	}
	public String toString(){
		return "TooLargeCostException occurred";
	}
}
