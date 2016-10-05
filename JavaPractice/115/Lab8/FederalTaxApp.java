import java.util.Scanner;

public class FederalTaxApp {
	public static void main(String[] args) {
		FederalTax Tax = new FederalTax();
		System.out.println(Tax);
		System.out.println("Enter your taxable income:");
		Scanner income = new Scanner(System.in);
		double incomeInput=income.nextDouble();
		Tax = new FederalTax(incomeInput);
		System.out.println(Tax);
		System.out.println("Enter another taxable income:");
		incomeInput=income.nextDouble();
		Tax = new FederalTax(incomeInput);
		System.out.println(Tax);
		System.out.println("Test a negative Income");
		Tax = new FederalTax(-1000);
		System.out.println(Tax);
	}
}