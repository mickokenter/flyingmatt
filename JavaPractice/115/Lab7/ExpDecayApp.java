import java.util.*;
public class ExpDecayApp {
	public static void main(String[] args) {
		//declare Scanner class and interest, principal variables for prompting user for input 
		Scanner input = new Scanner(System.in);		
		double decayConstant, initialQuantity;
		
		//instantiate a default object of the InvestCalc class
		ExpDecay value1 = new ExpDecay();
		System.out.println("Default ExpDecay Object");
		System.out.println(value1.toString()+ "\n");
		
		//query for decay constant and initial quantity
		System.out.print("Enter a decay constant: ");
		decayConstant  = input.nextDouble();
		System.out.print("Enter the initial quantity: ");	
		initialQuantity = input.nextDouble();

		//change object and output
		value1.setLambda(decayConstant);
		value1.setN0(initialQuantity);
		System.out.println("Updated ExpDecay Object");
		System.out.println(value1.toString()+ "\n");
		
		//test the futureValue and displayTable methods 
// UNCOMMENT THE NEXT TWO STATEMENTS WHEN YOU ARE READY TO TEST
		System.out.println("Amount at time=1: " + value1.futureQuantity(1) + "\n");	
		value1.displayTable();	
		
		//query for another decay constant and initial quantity
		System.out.print("Enter a decay constant: ");
		decayConstant  = input.nextDouble();
		System.out.print("Enter the initial quantity: ");	
		initialQuantity = input.nextDouble();
		
		//instantiate an object of the InvestCalc class
		ExpDecay value2 = new ExpDecay(decayConstant, initialQuantity);
		System.out.println("Non-Default ExpDecay Object");
		System.out.println(value2.toString()+ "\n");

// UNCOMMENT THE NEXT STATEMENT WHEN YOU ARE READY TO TEST
		value2.displayTable();
	}
}