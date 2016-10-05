import java.util.Scanner;

public class GuessingGame {

	public static void main(String [] args){
		Scanner input = new Scanner(System.in);
		System.out.println("What is the low limit and high limit for the secret number? ");
		int l=input.nextInt();
		int h=input.nextInt();	
		SecretNumber x = new SecretNumber(l,h);
		int g;
		int guessValue;
		do {
			System.out.println("Please try to guess my number. ");
			g=input.nextInt();
			guessValue=x.guess(g);
			if (guessValue==1)
				System.out.println("Your guess (" + g + ") was too high.");
			else if (guessValue==-1)
				System.out.println("Your guess (" + g + ") was too low.");
			
			/* don't call the method twice
			if (x.guess(g)==1)
				//Your guess (95) was too high.
			else if (x.guess(g)==-1)
				//Your guess (50) was too low.
			*/
		} while (guessValue!=0);
		
		System.out.println("Correct!  You got the answer in " + x.getCount() + " guesses");
	}
}