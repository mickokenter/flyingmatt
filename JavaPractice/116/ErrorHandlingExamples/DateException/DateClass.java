class DateClass
{
	public String setDate(int d,int m, int y) throws InvalidDateException
	{
		if(d<1||d>31)
		 throw new InvalidDateException();
		else if(m<1||m>12)
		  throw new InvalidDateException();
		else if(y<0)
		  throw new InvalidDateException();
		else
		{
		  String mydate=Integer.toString(d)+"/"+Integer.toString(m)+"/"+Integer.toString(y);
		  return mydate;
		}
		  
	}


}
