class InvalidDateException extends Exception 
{
	public InvalidDateException()
	{
		super();
		System.out.println("A wrong date format was used");
	}
}
