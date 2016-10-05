package ServiceClasses;

public class Expenses{
	String month="";
	String type="";
	double[] amount=null;
	int days=0;
	int id=0;
	static int totalID=0;
	
	public Expenses(){
		setMonth("any month");
		setType("any expense");
		setAmount(null);
		setDays(0);
		totalID++;
		id=totalID;
	}
	public Expenses(String monthInput, String typeInput, double[] amountInput, int daysInput){
		setMonth(monthInput);
		setType(typeInput);
		setAmount(amountInput);
		setDays(daysInput);
		totalID++;
		id=totalID;
	}
	public void setMonth(String monthInput){
		if(!monthInput.equals("")){
			month=monthInput;
		}
		else {
			month="any month";
		}
	}
	public void setType(String typeInput){
		if(!typeInput.equals("")){
			type=typeInput;
		}
		else {
			type="any expense";
		}
	}
	public void setAmount(double[] amountInput){
		if(amountInput!=null){
			amount = new double[amountInput.length];
			for(int i=0; i<amountInput.length; i++){
				amount[i]=amountInput[i];
			}
		}
		else{
			amount=null;
		}		
	}
	public void setDays(int daysInput){
		if(daysInput!=0){
			days=daysInput;
		}
		else {
			days=0;
		}
	}
	public String getMonth(){
		return month;
	}
	public String getType(){
		return type;
	}
	public double[] getAmount(){
		return amount;
	}
	public int getDays(){
		return days;
	}
	public String toString(){
		String amountOutput="";
		for(int i=0; i<amount.length; i++){
			amountOutput=amountOutput+amount[i]+" ";
		}
		String output="The month is: "+month+" The type of expenses is "+type+" The amounts are "+amountOutput+"The number of days for data is "+days+" The expense ID is "+id+" And the static id value is "+totalID+"\n";
		return output;
	}
	public boolean equals(Expenses sample){
		if((this.getType().equals(sample.getType()))&&(this.getDays()==sample.getDays())){
			return true;
		}
		else return false;
	}
}