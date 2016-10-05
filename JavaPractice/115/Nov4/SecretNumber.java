import java.util.Random;

public class SecretNumber {
	private int secret;
	private int count;
	
	public SecretNumber(int low, int high){
		if (high < low) {
			//swap high and low
			int temp=low;
			low=high;
			high=temp;
		}
		
		Random myRandObject = new Random();
		int range = high-low+1;
		secret=myRandObject.nextInt(range)+low;	
		count=0;
	}
	
	public int  guess(int myGuess) {
	//  return 0 if match, 1 if too high, -1 if too low
		int myReturn=0;	
		if (secret == myGuess) myReturn=0;
		else if (secret > myGuess) myReturn=-1;
		else myReturn=1;
		
		count++;  // count=count+1;
		return myReturn;
	}
	
	public int getCount(){
		return count;
	}

}