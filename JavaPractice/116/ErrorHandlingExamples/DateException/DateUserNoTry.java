public class DateUserNoTry
{
	public static void main(String[] args) 
	{
		
		
			DateClassNoThrow dc=new DateClassNoThrow();
			String date=dc.setDate(20, 13, 2011);
			System.out.println(date);
		
	}
}
//---------- Run Java ----------
/*A wrong date format was used
InvalidDateException
 
Normal Termination
Output completed (0 sec consumed).*/

/*NOTICE THAT WE DID NOT HAVE TO USE THE try/catch HERE
SINCE IT WAS USED IN THE CLASS THAT TROWS THE EXCEPTION