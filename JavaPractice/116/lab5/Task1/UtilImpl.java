package Client.Services.Enums.Help;

public class UtilImpl extends Utility implements UtilityCostsInterface{
	public double getUtilityExpenses(){
		return getUtilCostsForAllObjects();
	}
}