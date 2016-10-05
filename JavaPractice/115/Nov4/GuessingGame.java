import java.util.Scanner;

public class GuessingGame {

	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		System.out.println("What is the low limit and high limit for the secret number? ");
		int l=input.nextInt();
		int h=input.nextInt();	
		SecretNumber x = new SecretNumber(l,h);
		int g;
		do {
			System.out.println("Please try to guess my number. ");
			g=input.nextInt();
		} while (x.guess(g)!=0);
		
		System.out.println("Correct!  You got the answer in " + x.getCount() + " guesses");
	}
}