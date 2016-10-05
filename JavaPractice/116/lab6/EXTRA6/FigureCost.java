import java.util.*;
public class FigureCost
{
	public static void main(String[] args) 
	{
		Cube cube=new Cube("Cube", 10.0, "area");

		Cube cube1=new Cube("Cube", 10.0, "volume");
		
		 Sphere sphere=new Sphere("Sphere", 10.0, "area");

		 Sphere sphere1=new Sphere("Sphere", 10.0, "volume");
		
		ArrayList<String> cost1=cube.costToDraw();
		
		System.out.println(cube.toString());
        
		for(int j=0; j<cost1.size(); j++)
			System.out.println(cost1.get(j));
		
		
		
		ArrayList<String> cost2=cube1.costToDraw();
		
		System.out.println(cube1.toString());

		for(int k=0; k<cost1.size(); k++)
			System.out.println(cost2.get(k));

		
		ArrayList<String> cost3=sphere.costToDraw();
		
		System.out.println(sphere.toString());
		for(int s=0; s<cost1.size(); s++)
			System.out.println(cost3.get(s));

		ArrayList<String> cost4=sphere1.costToDraw();
		
		System.out.println(sphere1.toString());
		for(int f=0; f<cost1.size(); f++)
		  System.out.println(cost4.get(f));

	}
}
