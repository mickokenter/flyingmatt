package Client.Services;
import Client.Services.Enums.Jobs;

public class Engineer extends Worker{
	private double weeklyBenefits=0.;
	
	public Engineer(){
		super();
		weeklyBenefits=400.00;
	}
	public Engineer(String nm, int sS, int yE, Jobs Steve, double wb){
		super(nm,sS,yE,Steve);
		weeklyBenefits=wb;
	}

	public double benefitsCalculation(Jobs Steve){
		double benefits=0.;
		if(Steve.equals(Jobs.ELECTRICAL_ENGINEER)){
			benefits=weeklyBenefits+getYearsExperience()*80.00;
		}
		else if(Steve.equals(Jobs.MECHANICAL_ENGINEER)){
			benefits=weeklyBenefits/2+getYearsExperience()*120.00;	
		}
		else{
			benefits=0.;
		}
		return benefits;
	}
	public void setWeeklyBenefits(double wb){
		weeklyBenefits=wb;
	}
	public double getWeeklyBenefits(){
		return weeklyBenefits;
	}

	public String toString(){
		String str=super.toString()+" The weeklyBenefits is: "+weeklyBenefits;
		return str;
	}
}