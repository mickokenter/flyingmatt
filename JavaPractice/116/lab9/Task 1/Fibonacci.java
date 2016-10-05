public class Fibonacci{
	public static int Fibonacci(long number){
		int fb=0;
		if(number==0)
			fb=0;
		else if(number==1)
			fb=1;
		else if(number>1)
			fb=Fibonacci(number-1)+Fibonacci(number-2);
		return fb;
	}
	public static void main(String[] args){
		for(int i=0; i<11; i++){
			System.out.println("Fibonacci of "+i+" is: "+Fibonacci(i));
		}
	}
}