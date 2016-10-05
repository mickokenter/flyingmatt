class DateClassNoThrow
{
	public String setDate(int d,int m, int y) 
	{
	  String mydate=" ";
	  try
	  {
		if(d<1||d>31)
		 throw new InvalidDateException();
		else if(m<1||m>12)
		  throw new InvalidDateException();
		else if(y<0)
		  throw new InvalidDateException();
		else
		{
		  mydate=Integer.toString(d)+"/"+Integer.toString(m)+"/"+Integer.toString(y);
		 
		}
	  }
	  catch(InvalidDateException ide)
	  {
	     System.out.println(ide);
	  }
	  return mydate;  
	}
//NOTICE THAT WE REMOVED THE throws InvalidDateException 
//FROM THE SIGNATURE OF THE METHOD. BY DOING THIS WE ARE NOT
//REQUIRING THE CLINR CLASS TO USE try/catch

}
