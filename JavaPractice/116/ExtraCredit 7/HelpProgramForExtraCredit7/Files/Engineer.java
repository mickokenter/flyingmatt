//George Koutsogiannakis
//Spring 2010

public class Engineer extends Employee 
{
	private double weeklySalary=0.0;
	private int yearsExperience=0;

	public Engineer()
	{

	}

	public Engineer(String nm, int ssn, double ws, int ye)
	{

		super(nm, ssn);
		setWeeklySalary(ws);
		setYearsExperience(ye);
	}

	public double getWeeklySalary()
	{
		return weeklySalary;
	}

	public double getYearsExperience()
	{
		return yearsExperience;
	}

	public void setWeeklySalary(double wsa)
	{
		weeklySalary=wsa;
	}

	public void setYearsExperience(int yex)
	{
		yearsExperience=yex;
	}

	public double compensation()
	{
		double salary=weeklySalary+yearsExperience*100.00;
		try{
			if(salary>2000)
				throw new HighCompensationException();
		}
		catch(HighCompensationException hce){
			System.out.println(hce);
		}
		finally{
			if(salary>2000){
				salary=2000;
				System.out.println("Your compensation exceeded the limit and now it is set to: "+salary);
			}
		}
		return salary;
	}
	 public String toString()
	 {
	   String output;
	   output=super.toString()+"The weekly salary is:"+" "+weeklySalary+" "+"The years of exper. is:"+" "+yearsExperience;
	   return output;
	 }
}
