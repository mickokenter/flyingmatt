import java.util.*;
import java.text.*;

public class Sphere extends Figure implements INT3
{

	double radius;
	public Sphere()
	{
		super("Sphere");
		radius=0.0;
	}
	public Sphere(double ra)
	{
		super("Sphere");
		radius=ra;
	}
	
	public double getRadius()
	{
		return radius;
	}
	public void setRadius(double r)
	{
		radius=r;
	}
	
	public double calculateArea()
	{
		double area=4*Math.PI*radius*radius;
		return area;
	}
	
	public double calculateVolume()
	{
		double volume=(4/3)*Math.PI*radius*radius*radius;
		return volume;
	}
	
	public Vector secretData()
	{
		Impl d=new Impl();
		Vector customers=d.customerData(); //point to data in Customer class
		return customers;
	}
	
	public ArrayList<String> costToDraw(String costType)
	{
		ArrayList<String> sphereCost=new ArrayList<String>();
		double areaCost;
		double volumeCost;
		DecimalFormat df=new DecimalFormat("0.00");
		
		Sphere sp=new Sphere();
		sp.setRadius(radius);
		
		Vector v=sp.secretData();
		if (costType.equals("area"))
		{
			for(int i=0; i<v.size();i++)
			{
				Object obj=v.elementAt(i);
				Customer cu=(Customer)obj;
				
				if(cu.getData().equals("Architect"))
				{
					if((cu.getScore())<600)
					{
						areaCost=40.00*sp.calculateArea();
						
						boolean a=sphereCost.add("The area drawing cost for an Architect with score <600 is "+df.format(areaCost));
					}
					else
					{
						areaCost=20.00*sp.calculateArea();
						boolean b=sphereCost.add("The area drawing cost for an Architect with score >=600 is "+df.format(areaCost));
					}
				}
				else
				{
					if((cu.getScore())<600)
					{
						areaCost=60.00*sp.calculateArea();
						boolean c=sphereCost.add("The area drawing cost for an Other with score <600 is "+df.format(areaCost));

					}
					else
					{
						areaCost=40.00*sp.calculateArea();
						boolean d=sphereCost.add("The area drawing cost for an Other with score >=600 is "+df.format(areaCost));
					}
				}
			}
		}
		else //for volume
		{
			for(int i=0; i<v.size();i++)
			{
				Object obj=v.elementAt(i);
				Customer cu=(Customer)obj;
				if(cu.getData().equals("Architect"))
				{
					if((cu.getScore())<600)
					{
						volumeCost=80.00*sp.calculateVolume();
						boolean e=sphereCost.add("The volume drawing cost for an Architect with score <600 is "+df.format(volumeCost));
					}
					else
					{
						volumeCost=40.00*sp.calculateVolume();
						boolean f=sphereCost.add("The volume drawing cost for an Architect with score >=600 is "+df.format(volumeCost));
					}
				}
				else
				{
					if((cu.getScore())<600)
					{
						volumeCost=120.00*sp.calculateVolume(); 
						boolean g=sphereCost.add("The volume drawing cost for an Other with score <600 is "+df.format(volumeCost));
					}
					else
					{
						volumeCost=100.00*sp.calculateVolume();
						boolean h=sphereCost.add("The volume drawing cost for an Other with score >=600 is "+df.format(volumeCost));
					}
				}
			}
		}
		
		
		return sphereCost;
	}
	public String toString(String costType)
	{
		Sphere sph=new Sphere(radius);
		String out=super.toString()+" The radius is "+radius+" And the parameter asked for is "+costType
		+"\n"+sph.costToDraw(costType);
		
		return out;
	}

}