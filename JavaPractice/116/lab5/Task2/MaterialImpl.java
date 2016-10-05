package Client.Services.Enums.Help;

public class MaterialImpl extends Material implements MaterialCostsInterface{
	public double getMaterialExpenses(){
		return getMaterialCostForAllObjects();
	}
}