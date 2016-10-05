import java.util.*;

public class FigureCost
{
	public static void main(String [] args)
	{	
		Cube c=new Cube(10.0);
		Sphere s=new Sphere(10.0);

		System.out.println(c.toString("area"));
		System.out.println("\n");
		System.out.println(c.toString("volume"));
		System.out.println("\n");
		System.out.println(s.toString("area"));
		System.out.println("\n");
		System.out.println(s.toString("volume"));
		
	}
	
}