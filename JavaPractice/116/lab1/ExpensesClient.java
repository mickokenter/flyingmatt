package ServiceClasses.ClientClass;
import ServiceClasses.Expenses;
import java.util.*;
import java.io.*;
import java.text.NumberFormat;

public class ExpensesClient{
	static String month="";
	static String type="";
	static double[] amount=null;
	static int days=0;
	static String tempAmount="";
	static int numOfExpenses=0;
	static Expenses[] expensesOftheMonth = null;
	
	public static void main(String[] args){
		try{
			File txt = new File("Expenses.txt");
			Scanner fileScanner = new Scanner(txt);
			Scanner expensesInstanceCount = new Scanner(txt);
			numOfExpenses=0;
			while(expensesInstanceCount.hasNextLine()){
				String line4Count=expensesInstanceCount.nextLine();
				StringTokenizer strtkColonForCount = new StringTokenizer(line4Count,":");
				String temp4Count=strtkColonForCount.nextToken();
				if(temp4Count.equals("Expense")){
					numOfExpenses++;
				}
			}
			expensesOftheMonth = new Expenses[numOfExpenses];
			int i=0;
			while(fileScanner.hasNextLine()){
				String line=fileScanner.nextLine();
				StringTokenizer strtkColon = new StringTokenizer(line,":");
				String temp=strtkColon.nextToken();
				if(temp.equals("Month")){
					month=strtkColon.nextToken();
				}
				else if(temp.equals("Expense")){
					type=strtkColon.nextToken();
				}
				else {
					StringTokenizer strtkComma = new StringTokenizer(temp,",");
					days=strtkComma.countTokens();
					amount = new double[days];
					for(int j=0; j<days; j++){
						tempAmount=strtkComma.nextToken();
						amount[j]=Double.parseDouble(tempAmount);
					}
					expensesOftheMonth[i]=new Expenses(month, type, amount, days);
					i++;
				}
			}
		}
		catch(IOException ioe){
			System.out.println("Something went wrong with the opening of the file!");
		}
		System.out.println("\t\t\tOUTPUT # 1");
		for(int k=0; k<expensesOftheMonth.length; k++){
			System.out.print(expensesOftheMonth[k]);
		}
		System.out.println("\t\t\tOUTPUT # 2");
		ExpensesClient sample = new ExpensesClient();
		for(int k=0; k<sample.totalExpensesPerMonth(expensesOftheMonth).length; k++){
			System.out.print(sample.totalExpensesPerMonth(expensesOftheMonth)[k]);
		}
	}
	public String[] totalExpensesPerMonth(Expenses[] exp){
		NumberFormat currencyFormater = NumberFormat.getCurrencyInstance();
		double[] sum4Expenses = new double[exp.length];
		double[] amountTemp=null;
		String monthTemp="";
		String monthTemp4Count="";
		int k=0;
		int t=0;
		for(int i=0; i<exp.length; i++){
			if(!exp[i].getMonth().equals(monthTemp4Count)){
				t++;
			}
			monthTemp4Count=exp[i].getMonth();
		}
		double[] sum4EachMonth = new double[t];
		String[] forReturn = new String[t];
		for(int i=0; i<exp.length; i++){
			amountTemp=new double[exp[i].getAmount().length];
			amountTemp=exp[i].getAmount();
			for(int j=0;j<amountTemp.length;j++){
				sum4Expenses[i]=sum4Expenses[i]+amountTemp[j];
			}
			if(exp[i].getMonth().equals(monthTemp)){
				sum4EachMonth[k]=sum4EachMonth[k]+sum4Expenses[i];
			}
			else {
				if(i>0){
					forReturn[k]="The total of all the expenses for the month of "+exp[i-1].getMonth()+" is: "+currencyFormater.format(sum4EachMonth[k])+"\n";
					k++;
				}
				sum4EachMonth[k]=sum4EachMonth[k]+sum4Expenses[i];
			}
			monthTemp=exp[i].getMonth();
		}
		forReturn[k]="The total of all the expenses for the month of "+exp[exp.length-1].getMonth()+" is: "+currencyFormater.format(sum4EachMonth[k])+"\n";
		return forReturn;
	}
}