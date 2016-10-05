package Client.Services;
import Client.Services.Enums.Jobs;

public abstract class Worker{
	public String name;
	public int socialSecurity;
	private int yearsExperience;
	public Jobs et;
	public static int id;
	public int currentID;
	public Worker(){
		name="AnyName";
		socialSecurity=12345;
		yearsExperience=0;
		et=Jobs.NONE;
		id++;
		currentID=id;
	}
	public Worker(String nm, int sS, int yE, Jobs Steve){
		name=nm;
		socialSecurity=sS;
		yearsExperience=yE;
		et=Steve;
		id++;
		currentID=id;
	}
	
	public String getName(){
		return name;
	}
	public int getSocialSecurity(){
		return socialSecurity;
	}
	public int getYearsExperience(){
		return yearsExperience;
	}
	public Jobs getJobs(){
		return et;
	}
	public int getID(){
		return id;
	}
	public int getCurrentID(){
		return currentID;
	}

	public void setName(String nm){
		name=nm;
	}
	public void setSocialSecurity(int sS){
		socialSecurity=sS;
	}
	public void setYearsExperience(int yE){
		yearsExperience=yE;
	}
	public void setJobs(Jobs Steve){
		et=Steve;
	}
	public void setID(int idn){
		id=idn;
	}
	public void setCurrentID(int cid){
		currentID=cid;
	}

	public String toString(){
		String str="The name is: "+name+" The SSN is: "+socialSecurity+" The working experience year is: "+yearsExperience+" The Job type is: "+et+" The id is: "+id;
		return str;
	}

	public abstract double benefitsCalculation(Jobs Steve);
}