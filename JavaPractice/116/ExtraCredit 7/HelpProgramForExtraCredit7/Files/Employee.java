
//George Koutsogiannakis
//Spring  2010

public abstract class Employee 
{
	private String name=" ";
	private int socialSecurity=0;
	private static int id=0;
	private int currentID=0;

	public Employee()
	{

		id++;
		currentID=id;
		name="AnyName";
		socialSecurity=12345;
		

	}

	public Employee(String nm, int ss)
	{
		

		id++;
		currentID=id;
		name=nm;
		socialSecurity=ss;
	}

	public String getName()
	{
	  return name;
	}

	public int getSS()
	{

		return socialSecurity;
	}

	public int getID()
	{

		return id;
	}

	public int getCurrentID()
	{

		return currentID;
	}

	public void setName(String ne)
	{
		name=ne;
	}
	public void setSS(int ssn)
	{
	  socialSecurity=ssn;
	 
	}

	public void setID(int idd)
	{
		id=idd;
	}

	public void setCurrentID(int cid)
	{
		currentID=cid;
	}

	public String toString()
	{
		String output;
		output="The name is:"+" "+name+" "+"The ss number is:"+" "+socialSecurity+" "+"The id is:"+" "+id+" "+"The currentId is:"+currentID;
		return output;
	}

	public abstract double compensation();
	
		

}

 






