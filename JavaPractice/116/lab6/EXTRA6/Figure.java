import java.util.*;
public abstract class Figure implements INT1, INT2  
{
	public String nameOfShape=" ";

	public Figure()
	{
		nameOfShape="NONE";
	}

	public Figure(String f)
	{
		nameOfShape=f;
	}

	public String toString()
	{
		String out="The name of the shape is:"+" "+nameOfShape;
		return out;
	}

	public abstract ArrayList<String> costToDraw();
}
