//George Koutsogiannakis
//Spring 2010

public class AdministrativePersonnel extends Employee 
{
	private double rate=0.0;
	private double hours=0.0;
	

	public AdministrativePersonnel()
	{

	}
	public AdministrativePersonnel(String nm, int ssn, double rt, double hrs) 
	{

		super(nm, ssn);
		setRate(rt);
		setHours(hrs);
	}

	public double getRate()
	{
		return rate;
	}

	public double getHours()
	{
		return hours;
	}

	public void setRate(double r)
	{
		rate=r;
	}

	public void setHours(double h)
	{
		hours=h;
	}

	public double compensation()
	{
		double salary=rate*hours;
		try{
			if(salary>800)
				throw new HighCompensationException();
		}
		catch(HighCompensationException hce){
			System.out.println(hce);
		}
		finally{
			if(salary>800){
				salary=800;
				System.out.println("Your compensation exceeded the limit and now it is set to: "+salary);
			}
		}
		return salary;
	}
	 public String toString()
	 {
	   String output;
	   output=super.toString()+"The rate is:"+" "+rate+" "+"The hours are"+" "+hours;
	   return output;
	 }
}
