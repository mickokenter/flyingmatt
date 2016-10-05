import java.util.*;

public class Cube extends Figure implements INT3
{
	
	double length;
	public Cube()
	{
		super("cube");
		length=0.0;
	}
	public Cube(double len)
	{
		super("cube");
		length=len;
	}
	
	public double getLength()
	{
		return length;
	}
	public void setLength(double l)
	{
		length=l;
	}
	
	public double calculateArea()
	{
		double area=6*length*length;
		return area;
	}
	
	public double calculateVolume()
	{
		double volume=length*length*length;
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
		ArrayList<String> cubeCost=new ArrayList<String>();
		double areaCost;
		double volumeCost;
		
	
		Cube cb=new Cube();
		cb.setLength(length);
		
		Vector v=cb.secretData();
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
						areaCost=20.00*cb.calculateArea();
						boolean a=cubeCost.add("The area drawing cost for an Architect with score <600 is "+areaCost);
					}
					else
					{
						areaCost=10.00*cb.calculateArea();
						boolean b=cubeCost.add("The area drawing cost for an Architect with score >=600 is "+areaCost);
					}
				}
				else
				{
					if((cu.getScore())<600)
					{
						areaCost=30.00*cb.calculateArea();
						boolean c=cubeCost.add("The area drawing cost for an Other with score <600 is "+areaCost);

					}
					else
					{
						areaCost=20.00*cb.calculateArea();
						boolean d=cubeCost.add("The area drawing cost for an Other with score >=600 is "+areaCost);
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
						volumeCost=40.00*cb.calculateVolume();
						boolean e=cubeCost.add("The volume drawing cost for an Architect with score <600 is "+volumeCost);
					}
					else
					{
						volumeCost=20.00*cb.calculateVolume();
						boolean f=cubeCost.add("The volume drawing cost for an Architect with score >=600 is "+volumeCost);
					}
				}
				else
				{
					if((cu.getScore())<600)
					{
						volumeCost=60.00*cb.calculateVolume(); 
						boolean g=cubeCost.add("The volume drawing cost for an Other with score <600 is "+volumeCost);
					}
					else
					{
						volumeCost=50.00*cb.calculateVolume();
						boolean h=cubeCost.add("The volume drawing cost for an Other with score >=600 is "+volumeCost);
					}
				}
			}
		}
		
		
		return cubeCost;
	}
	public String toString(String costType)
	{
		Cube cub=new Cube();
		cub.setLength(length);
		String out=super.toString()+" The length is "+length+" And the parameter asked for is "+costType
		+"\n"+cub.costToDraw(costType);
		
		return out;
	}
}