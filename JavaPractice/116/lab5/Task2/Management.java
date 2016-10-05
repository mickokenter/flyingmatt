package Client.Services;
import Client.Services.Enums.Jobs;

public class Management extends Worker{
	private double weeklyBenefits=0.;
	private double bonus=0.;

	public Management(){
		super();
		weeklyBenefits=10.0;
		bonus=10.0;
	}

	public Management(String nm, int sS, int yE, Jobs Steve, double wb, double bn){
		super(nm,sS,yE,Steve);
		weeklyBenefits=wb;
		bonus=bn;
	}

	public double benefitsCalculation(Jobs Steve){
		double benefits=0.0;
		if(Steve.equals(Jobs.ENGINEERING_MANAGER)){
			benefits=weeklyBenefits+bonus;
		}
		else if(Steve.equals(Jobs.ADMINISTRATIVE_MANAGER)){
			benefits=weeklyBenefits+0.5*bonus;
		}
		else{
			benefits=0.0;
		}
		return benefits;
	}

	public double getWeeklyBenefits(){
		return weeklyBenefits;
	}
	public double getBonus(){
		return bonus;
	}

	public void setWeeklyBenefits(double wb){
		weeklyBenefits=wb;
	}
	public void setBonus(double bn){
		bonus=bn;
	}

	public String toString(){
		String str=super.toString()+" The weeklyBenefits is: "+weeklyBenefits+" The bonus are: "+bonus;
		return str;
	}
}
