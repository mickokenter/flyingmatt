
//George Koutsogiannakis
//Spring 2010
public class TestExceptionsNoTry
  
{
	public static void main(String[] args) 
	{
		int a=10;
		int b=0;
		int c=0;
		
		  c=a/b;
		
		System.out.println("c="+" "+c);
		
			b=2;
			c=a/b;
			
		System.out.println("c="+" "+c);
	}
}
/* OUTPUT:
---------- Run Java ----------
Exception in thread "main" java.lang.ArithmeticException: / by zero
	at TestExceptionsNoTry.main(TestExceptionsNoTry.java:13)
Normal Termination
Output completed (0 sec consumed).
*/
