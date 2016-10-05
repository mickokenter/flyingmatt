public class DateUser
{
	public static void main(String[] args) 
	{
		try
		{
			DateClass dc=new DateClass();
			String date=dc.setDate(20, 13, 2011);
			System.out.println(date);
		}
		catch(InvalidDateException ide)
		{
		  System.out.println(ide);
		}
	}
}
/*
---------- Run Java ----------
A wrong date format was used
InvalidDateException
Normal Termination
Output completed (0 sec consumed).*/