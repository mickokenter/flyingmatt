public class HighCompensationException extends Exception{
	public HighCompensationException(){}
	public HighCompensationException(String msg){
		super(msg);
	}
	public String getMessage(){
		return "The compensation exceeds the limit";
	}
	public String toString(){
		String str="The compensation exceeds the limit\nHigh CompensationException triggered";
		return str;
	}
}