class Test 
{
	public static void main(String[] args) 
	{
		SomeClass sc=new SomeClass();
		int y=sc.add(30);
		System.out.println("y="+" "+y);
	}
}

/*
---------- Compiler ----------
Test.java:6: unreported exception MyException; must be caught or declared to be thrown
		int y=sc.add(30);
		            ^
1 error
Normal Termination
Output completed (0 sec consumed).
*/