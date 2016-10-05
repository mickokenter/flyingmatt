import java.util.*;
import java.text.*;
public class Sphere extends Figure  implements INT3
{
	public double radius=0.0;
	String s=" ";
	public Sphere()
	{
		super();
		radius=2.0;
	}
	public Sphere(String n, double r, String a)
	{
		super(n);
		radius=r;
		s=a;
		
	}

	public double calculateArea()
	{
		double area=4*Math.PI*Math.pow(radius,2.0);
		boolean flag=false;
		try{
			if(area>1000){
				throw new TooLargeAreaException();
			}
		}
		catch(TooLargeAreaException te){
			System.out.println(te.getMessage());
			System.out.println(te.toString());
			flag=true;
		}
		finally{
			if(flag){
				area=0;
			}
		}
		
		return area;
	}

	public double calculateVolume()
	{
		double volume=(4/3)*Math.PI*Math.pow(radius,3);
		boolean flag=false;
		try{
			if(volume>10000){
				throw new TooLargeVolumeException();
			}
		}
		catch(TooLargeVolumeException te){
			System.out.println(te.getMessage());
			System.out.println(te.toString());
			flag=true;
		}
		finally{
			if(flag){
				volume=0;
			}
		}
		return volume;
	}

	
	public Vector secretData()
	{
		 Impl i=new Impl();
		 Vector v3=i.secretData();
		 return v3;
	}

	public ArrayList<String> costToDraw()
	{
		double cost=0.0;
		String str=" ";
		ArrayList<String> strcost=new ArrayList<String>();
		Vector v4=secretData();
		for(int i=0; i<v4.size(); i++)
		{
			Object o=v4.get(i);
			Customers c=(Customers)o;
			System.out.println("The Customer is: type= "+c.getType()+" "+"score= "+c.getScore());
			if(c.getType().equals("Architect")&&c.getScore()<600&&s.equals("area"))
			{
				 cost=40*calculateArea();
				 str="The area drawing cost for an Architect with score <600 is"+" "+(new DecimalFormat("#.##")).format(cost);
				 
			}
			else if(c.getType().equals("Architect")&&c.getScore()<600&&s.equals("volume"))
			{
				cost=80*calculateVolume();
				str="The volume drawing cost for an Architect with score<600 is"+" "+(new DecimalFormat("#.##")).format(cost);
				
			}
			else if(c.getType().equals("Architect")&&c.getScore()>=600&&s.equals("area"))
		    {  
				cost=20*calculateArea();
				str="The area drawing cost for an Architect with score >=600 is"+" "+(new DecimalFormat("#.##")).format(cost);
				 
			}
			else if(c.getType().equals("Architect")&&c.getScore()>=600&&s.equals("volume"))
		    {  
				cost=40*calculateVolume();
				str="The volume drawing cost for an Architect with score >=600 is"+" "+(new DecimalFormat("#.##")).format(cost);
				 
			}
			else if(c.getType().equals("Other")&&c.getScore()<600&&s.equals("area"))
		    {  
				cost=60*calculateArea();
				str="The area drawing cost for an Other with score <600 is"+" "+(new DecimalFormat("#.##")).format(cost);
				 
			}
			else if(c.getType().equals("Other")&&c.getScore()<600&&s.equals("volume"))
		    {  
				cost=120*calculateVolume();
				str="The volume drawing cost for an Other with score <600 is"+" "+(new DecimalFormat("#.##")).format(cost);
				 
			}
			else if(c.getType().equals("Other")&&c.getScore()>=600&&s.equals("area"))
		    {  
				cost=40*calculateArea();
				str="The area  drawing cost for an Other with score >=600 is"+" "+(new DecimalFormat("#.##")).format(cost);
				 
			}
			else if(c.getType().equals("Other")&&c.getScore()>=600&&s.equals("volume"))
		    {  
				cost=100*calculateVolume();
				str="The volume drawing cost for an Other with score>=600 is"+" "+(new DecimalFormat("#.##")).format(cost);
				 
			}
			    try{
					if(cost>400000){
						throw new TooLargeCostException();
					}
					else{
						strcost.add(str);
					}
				}
				catch(TooLargeCostException te){
					String strT=te.getMessage();
					strcost.add(strT);
				}
        }
		return strcost;
	}

	public String toString()
	{
		 String out=super.toString()+"The radius is"+" "+radius+" "+"And the parameter s for is:"+" "+s;
		 return out;
	}
}
