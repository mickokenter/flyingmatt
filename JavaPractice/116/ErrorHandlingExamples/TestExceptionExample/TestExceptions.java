//George Koutsogiannakis


public class TestExceptions  
{
	public static void main(String[] args) 
	{
		
		int a=10;
		int b=0;
		int c=0;
		try
		{
		  c=a/b;
		}
		catch(ArithmeticException ae)
		{
			System.out.println(ae.toString());
			System.out.println("you are trying to divide by zero");
		}
		finally
		{
			b=2;
			c=a/b;
		}	
		System.out.println("c="+" "+c);
	}
}
/*
---------- Run Java ----------
java.lang.ArithmeticException: / by zero
you are trying to divide by zero
c= 5
Normal Termination
Output completed (0 sec consumed).
*/