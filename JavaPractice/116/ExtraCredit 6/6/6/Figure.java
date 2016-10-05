import java.util.*;
public abstract class Figure implements INT1, INT2
{
	String nameOfShape;
	
	public Figure()
	{
		nameOfShape="NULL";
	}
	public Figure(String name)
	{
		nameOfShape=name;
	}
	public String toString()
	{
		String output="The name of the shape is "+nameOfShape;
		return output;
	}
	public abstract ArrayList<String> costToDraw(String type);
	
}