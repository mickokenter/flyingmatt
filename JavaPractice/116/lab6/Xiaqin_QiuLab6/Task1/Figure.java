import java.util.*;
public abstract class Figure implements INT1, INT2  
{
	public String name=" ";

	public Figure()
	{
		name=" ";
	}

	public Figure(String a)
	{
		name=a;
	}

	public String toString()
	{
		String out="The name of the shape is:"+" "+name;
		return out;
	}

	public abstract ArrayList<String> costToDraw();
}
