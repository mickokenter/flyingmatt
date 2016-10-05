//George Koutsogiannakis
//Spring 2010

public class Management extends Employee 
{
	private double weeklySalary=0.0;
	private double bonus=0.0;
	

	public Management()
	{
	}
	public Management(String nm, int ssn, double ws, double bn) 
	{

		super(nm, ssn);
		setWeeklySalary(ws);
		setBonus(bn);
	}

	public double getWeeklySalary()
	{
		return weeklySalary;
	}

	public double getBonus()
	{
		return bonus;
	}

	public void setWeeklySalary(double wsa)
	{
		weeklySalary=wsa;
	}

	public void setBonus(double bo)
	{
		bonus=bo;
	}

	public double compensation()
	{

		double salary=weeklySalary+bonus;
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
	   output=super.toString()+"The weekly salary is:"+" "+weeklySalary+" "+"The bonus is"+" "+bonus;
	   return output;
	 }
}
