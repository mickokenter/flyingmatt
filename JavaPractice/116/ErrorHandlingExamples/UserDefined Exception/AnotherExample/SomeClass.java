public class SomeClass
{
    
     public int add(int i) throws MyException
     {
        
            int x=0;
             if( i < 40)
                  throw new MyException();
		     else
			    x=i+20;
			 return x;
        
     }
}
