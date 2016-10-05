import java.util.Scanner;
class TestCorrected
{
	public static void main(String[] args) 
	{
		try
		{
			SomeClass sc=new SomeClass();
			Scanner scan=new Scanner(System.in);
			System.out.println("Please enter an int");
			int i=scan.nextInt();
			int y=sc.add(i);
			System.out.println("y="+" "+y);
		}
		catch(MyException me)
		{  
		   System.out.println("An exception occured");
		   System.out.println(me);
		 }
	}
}


/*
C:\CS116\Lectures\Lecture10revised\UserDefined Exception\AnotherExample>java TestCorrected
Please enter an int
10
An exception occured
You have failed

C:\CS116\Lectures\Lecture10revised\UserDefined Exception\AnotherExample>java TestCorrected
Please enter an int
45
y= 65

C:\CS116\Lectures\Lecture10revised\UserDefined Exception\AnotherExample>
*/