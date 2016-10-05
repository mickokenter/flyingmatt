package Client.Services.Enums.Help;

public class ExpenseImpl extends RecurringExpenses implements ExpenseInterface{
	public double getRecurringExpenses(){
		return totalRecurringExpenses();
	}
}