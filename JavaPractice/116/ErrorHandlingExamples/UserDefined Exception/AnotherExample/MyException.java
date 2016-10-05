public class MyException extends Exception
    {
     String msg = "";
     int marks;
     public MyException()
     {
     }
     public MyException(String str)
     {
         super(str);
     }
     public String toString()
    {
         if(marks <= 40)
				msg = "You have failed";
         if(marks > 40)
				msg = "You have Passed";
        
         return msg;
        
     }
}
