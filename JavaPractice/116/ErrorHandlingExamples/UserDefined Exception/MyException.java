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
        
		msg = super.toString()+" "+"You have failed";
       
        
         return msg;
        
     }
}
//Notice that no throw statement is used here. We are forcing the exception to occur
// when in th etest class a violation of the rule occurs. The appropriate message in the toString
// of this exception class will be printed out.