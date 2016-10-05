public class TooLargeVolumeException extends Exception{
	public TooLargeVolumeException(){
		super();
	}
	public String getMessage(){
		return "The volume calculated exceeds the limits";
	}
	public String toString(){
		return "TooLargeVolumeException occurred";
	}
}
