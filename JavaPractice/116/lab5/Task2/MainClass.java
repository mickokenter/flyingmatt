package Client;
import Client.Services.*;
import Client.Services.Enums.*;
import Client.Services.Enums.Help.*;
import java.util.*;

public class MainClass{
	public static void main(String[] args){
		System.out.println("FROM THE INVOCATIONS IN MAIN CLASS");
		AllExpenses AE=new AllExpenses();
		ArrayList<Worker> al=AE.listOfWorkers();
		AE.displayData(al);
		System.out.println("\nFROM THE ALLEXPENSES CLASS\n");
		AE.createExpenses();
		System.out.println("\nBACK TO MAIN CLASS\n");
		double tb=AE.totalBenefits(al);
		System.out.println("The sum of all salaries paid was:"+tb);
		System.out.println("The total operating expenses were:"+AE.getRecurringExpenses());
		double te=AE.getTotalExpenses();
		System.out.println("The total of all expenses were:"+te);
	}
}