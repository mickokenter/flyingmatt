public class test1
{
     public static void main(String args[])
     {
         test1 t = new test1();
         t.dd();
     }
     public void dd()
     {
         //try
         //{
             int i=50;
             if( i < 40)
                  throw new MyException();
         //}
        // catch(MyException ee1)
        // {
         //    System.out.println("my ex"+ee1);
       //  }
     }
}

/*
---------- Compiler ----------
test1.java:14: unreported exception MyException; must be caught or declared to be thrown
                  throw new MyException();
                  ^
1 error
Normal Termination
Output completed (0 sec consumed). */

//NOTICE THAT THE COMPILER GAVE US AN ERROOR MESSAGE BECAUSE 
//THIS IS A CHECKED EXCEPTION AND WE DID NOT USE THE try/catch