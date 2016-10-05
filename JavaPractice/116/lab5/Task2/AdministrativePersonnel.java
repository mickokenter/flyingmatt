package Client.Services;
import Client.Services.Enums.Jobs;

public class AdministrativePersonnel extends Worker{
	private double rate=0.;
	private double hours=0.;

	public AdministrativePersonnel(){
		super();
		rate=10.0;
		hours=10.0;
	}
	public AdministrativePersonnel(String nm, int sS, int yE, Jobs Steve, double rt,double h){
		super(nm,sS,yE,Steve);
		rate=rt;
		hours=h;
	}

	public double benefitsCalculation(Jobs Steve){
		double benefits=0.0;
		if(Steve.equals(Jobs.ADMINISTRATIVE_SECRETARY)){
			benefits=rate*hours+getYearsExperience()*15;
		}
		else if(Steve.equals(Jobs.ADMINISTRATIVE_ASSISTANT)){
			benefits=rate*hours+getYearsExperience()*25;
		}
		else{
			benefits=0.0;
		}
		return benefits;
	}

	public double getRate(){
		return rate;
	}
	public double getHours(){
		return hours;
	}

	public void setRate(double rt){
		rate=rt;
	}
	public void setHours(double h){
		hours=h;
	}

	public String toString(){
		String str=super.toString()+" The rate is: "+rate+" The hours are: "+hours;
		return str;
	}
}
