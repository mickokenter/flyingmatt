import java.text.*;
import java.util.*;
class Test {
	public static void main(String[] args) 	{

		String s1="matt";
		String s2="Matt";
		System.out.println(s1.compareTo(s2));
		System.out.println(s2.compareTo(s1));
		System.out.println(s2.compareTo("Mbtt"));
		
		int count=1;	// initialize
		while (count<=100) {	// condition
			if(count%2==0) {
				System.out.println(count + " " + Math.pow(count,2));
			}
			count=count+1;	// increment			
		}

		Scanner in = new Scanner(System.in);
		System.out.println("Give me a positive integer:");
		int data = in.nextInt();
		while (data<=0) {
			System.out.println("Give me a positive integer:");
			data = in.nextInt();
		}		
/*		
		Scanner in = new Scanner(System.in);
		System.out.println("Give me a positive integer:");
		while (!in.hasNextInt()) {
			String garbage=in.next();
			System.out.println("Give me a positive integer:");
		}		
		int data=in.nextInt();
		System.out.println("Done " + data );
		*/
	}
	
}