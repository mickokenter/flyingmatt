public class test
{
     public static void main(String args[])
     {
         test t = new test();
         t.dd();
     }
     public void dd()
     {
         try
         {
             int i=0;
             if( i < 40)
                  throw new MyException();
         }
         catch(MyException ee1)
         {
             System.out.println("my ex"+" "+ee1);
         }
     }
}
/*---------- Run Java ----------
my ex MyException You have failed
Normal Termination
Output completed (0 sec consumed).*/