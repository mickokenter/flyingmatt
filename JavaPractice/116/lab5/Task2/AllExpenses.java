package Client.Services.Enums.Help;
import Client.*;

public class AllExpenses extends WorkerBenefits implements ExpenseInterface{
	ExpenseImpl eximpl=new ExpenseImpl();
	
	public AllExpenses(){};
	public double getRecurringExpenses(){
		return eximpl.getRecurringExpenses();
	}
	public double getTotalExpenses(){
		double TE=totalBenefits(listOfWorkers())+getRecurringExpenses();
		return TE;
	}
	public void createExpenses(){
		RecurringExpenses RE=new RecurringExpenses();
		RE.createExpenses();
	}
}