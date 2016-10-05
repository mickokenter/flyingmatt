public class DateUserCorrected
{
	public static void main(String[] args) 
	{
		try
		{
			DateClass dc=new DateClass();
			String date=dc.setDate(20, 12, 2011);
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
20/12/2011
Normal Termination
Output completed (0 sec consumed).*/