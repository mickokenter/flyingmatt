package Client.Services.Enums.Help;

public class RecurringExpenses extends Rent implements UtilityCostsInterface, MaterialCostsInterface{
	public double totalRecurringExpenses(){
		double tre=getRentCostForAllObjects()+getUtilityExpenses()+getMaterialExpenses();
		System.out.println("the total recurring expenses is: "+tre);
		return tre;
	}
	public void createExpenses(){
		Utility U1=new Utility(100.00,100.00,100.00);
		Utility U2=new Utility(150.00,150.00,150.00);
		Rent R1=new Rent("2",3,4);
		Rent R2=new Rent("4",1,1);
		Material M1=new Material("Paper",3.0,1000);
		Material M2=new Material("Ink",2.0,200);
		double uc1=U1.totalUtilCosts();
		System.out.println("The first utility object has: "+uc1);
		double uc2=U2.totalUtilCosts();
		System.out.println("The second utility object has: "+uc2);
		double rt1=R1.rent();
		System.out.println("The first rent object has: "+rt1);
		double rt2=R2.rent();
		System.out.println("The second rent object has: "+rt2);
		double mp1=M1.materialPrice();
		System.out.println("The first material object has: "+mp1);
		double mp2=M2.materialPrice();
		System.out.println("The second material object has: "+mp2);
	}
	public double getUtilityExpenses(){
		UtilImpl UI=new UtilImpl();
		double UE=UI.getUtilityExpenses();
		return UE;
	}
	public double getMaterialExpenses(){
		MaterialImpl MI=new MaterialImpl();
		double ME=MI.getMaterialExpenses();
		return ME;
	}
	public String toString(){
		String str="The total recurring expenses is: "+totalRecurringExpenses();
		return str;
	}
}