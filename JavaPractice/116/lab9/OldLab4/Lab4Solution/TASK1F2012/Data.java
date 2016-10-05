//George Koutsogiannakis


package ClientClass;
import ClientClass.ServiceClasses.*;
import java.io.*;
import java.util.*;
import java.text.DecimalFormat;
public class Data
{

  
   public Data()
   {

   }

   public Vehicle[] arrayOfObjects()
   {
		String line=" ";
		int count=0;
		Vehicle [] veh=null;
		EngineSizes vehe=null;
		Dealers d=null;
		try
		{
			
			
			File file=new File("Vehicles.txt");
			
			Scanner scan=new Scanner(file);
			while(scan.hasNextLine())
			{
				scan.nextLine();
				count++;
			}
			veh=new Vehicle[count];
			Scanner scan1=new Scanner(file);
			int i=0;
			System.out.println("The file has"+" "+count+"lines");
			while(scan1.hasNextLine())
			{
				line=scan1.nextLine();
				StringTokenizer strtok=new StringTokenizer(line,",");
				String tok1=strtok.nextToken();
				double a=Double.parseDouble(tok1);
				String tok2=strtok.nextToken();
				int t=Integer.parseInt(tok2);
				String tok3=strtok.nextToken();
				String tok4=strtok.nextToken();
				if(tok4.equals("GlenEllynChevy"))
				{
					
					d=Dealers.GlenEllynChevy;
				}
				else if(tok4.equals("ChicagoFord"))
				{
					
					d=Dealers.ChicagoFord;
				}
				else if(tok4.equals("WilmetteChrysler"))
				{
				   d=Dealers.WilmetteChrysler;
				}
				else if(tok4.equals("SouthMercedes"))
				{
					d=Dealers.SouthMercedes;
				}
				else if(tok4.equals("WestBMW"))
					d=Dealers.WestBMW;

				String tok5=strtok.nextToken();
				if(tok5.equals("Litters_1Point5"))
					vehe=EngineSizes.Litters_1Point5;
				else if(tok5.equals("Litters_2Point4"))
					vehe=EngineSizes.Litters_2Point4;
				else if(tok5.equals("Litters_3Point0"))
					vehe=EngineSizes.Litters_3Point0;
				else if(tok5.equals("Litters_4Point0"))
					vehe=EngineSizes.Litters_4Point0;
				else if(tok5.equals("Litters_6Point0"))
					vehe=EngineSizes.Litters_6Point0;

				Vehicle v=new Vehicle(a,t,tok3,d,vehe);
		
					double vel=v.calculateVelocity(1.0);
				// re adjust the accelearation value before you ask for teh distance
					v.setAcceleration(a);
					double dist=v.calculateDistance(1.0,100.0);
					v.calculateValue();
				System.out.println("I am creating the non sorted array. Vehicle with id:"+" "+v.getCurrentID()+
					" "+"has a velocity"+" "+vel+" "+"The distance covered is:"+" "+dist+" "+"the value is"+" "+v.getValue());
				veh[i]=v;
				i++;
			}//end of while loop
		}//end of try
		catch(IOException ioe)
		{
			System.out.println("Something is wrong");
		}
		return veh;




   }

  public Vehicle [] selectionSort(Vehicle [] vh)
  {
		Vehicle [] veh=new Vehicle[vh.length];
		for(int f=0; f<=vh.length-1;f++)
			veh[f]=vh[f];
		DecimalFormat df=new DecimalFormat("#.##");
		EngineSizes ven;
		double accel=0.0;
		for(int i=0; i<=veh.length-1; i++)
		{
			ven=veh[i].getEngineSizes();
			if(ven.equals(EngineSizes.Litters_1Point5))
				veh[i].setAcceleration(Double.parseDouble(df.format(0.8*veh[i].getAcceleration())));
			else if(ven.equals(EngineSizes.Litters_2Point4))
				veh[i].setAcceleration(Double.parseDouble(df.format(1.2*veh[i].getAcceleration())));
			else if(ven.equals(EngineSizes.Litters_3Point0))
				veh[i].setAcceleration(Double.parseDouble(df.format(1.8*veh[i].getAcceleration())));
			else if(ven.equals(EngineSizes.Litters_4Point0))
				veh[i].setAcceleration(Double.parseDouble(df.format(2.0*veh[i].getAcceleration())));
			else if(ven.equals(EngineSizes.Litters_6Point0))
				veh[i].setAcceleration(Double.parseDouble(df.format(2.5*veh[i].getAcceleration())));
		}
	    Vehicle temp;
		int subarraylength=0;
		for (int j=0; j<=veh.length-1; j++ )
		{
			subarraylength=veh.length-j;
                  //find index of largest element in subarray
			int index=0;
			for (int k=1; k<subarraylength; k++)
			{
				if (veh[k].getAcceleration() > veh[index].getAcceleration())
				{
					index=k;					
				}
			}
			temp=veh[index];
			veh[index]=veh[veh.length-j-1];
			veh[veh.length-j-1]=temp;
		}
       // System.out.println("accel for 7th veh="+" "+veh[6].getAcceleration());
		return veh;
	  
	  
	}//end of method		
		
	public Vector vectorOfVehicles()
   {
		String line=" ";
		int count=0;
		Vehicle [] veh=null;
		EngineSizes vehe=null;
		Dealers d=null;
		Vector vect=new Vector();
		try
		{
			
			Vehicle v1=new Vehicle();
				v1.reSetVehicleID();
			File file=new File("Vehicles.txt");
			
			Scanner scan=new Scanner(file);
			Scanner scan1=new Scanner(file);
			
			while(scan1.hasNextLine())
			{
				line=scan1.nextLine();
				StringTokenizer strtok=new StringTokenizer(line,",");
				String tok1=strtok.nextToken();
				double a=Double.parseDouble(tok1);
				String tok2=strtok.nextToken();
				int t=Integer.parseInt(tok2);
				String tok3=strtok.nextToken();
				String tok4=strtok.nextToken();
				if(tok4.equals("GlenEllynChevy"))
				{
					
					d=Dealers.GlenEllynChevy;
				}
				else if(tok4.equals("ChicagoFord"))
				{
					
					d=Dealers.ChicagoFord;
				}
				else if(tok4.equals("WilmetteChrysler"))
				{
				   d=Dealers.WilmetteChrysler;
				}
				else if(tok4.equals("SouthMercedes"))
				{
					d=Dealers.SouthMercedes;
				}
				else if(tok4.equals("WestBMW"))
					d=Dealers.WestBMW;

				String tok5=strtok.nextToken();
				if(tok5.equals("Litters_1Point5"))
					vehe=EngineSizes.Litters_1Point5;
				else if(tok5.equals("Litters_2Point4"))
					vehe=EngineSizes.Litters_2Point4;
				else if(tok5.equals("Litters_3Point0"))
					vehe=EngineSizes.Litters_3Point0;
				else if(tok5.equals("Litters_4Point0"))
					vehe=EngineSizes.Litters_4Point0;
				else if(tok5.equals("Litters_6Point0"))
					vehe=EngineSizes.Litters_6Point0;
				
				Vehicle v=new Vehicle(a,t,tok3,d,vehe);
		       
					double vel=v.calculateVelocity(1.0);
				// re adjust the accelearation value before you ask for the distance
					v.setAcceleration(a);
					double dist=v.calculateDistance(1.0,1.0);
				System.out.println("I am creating the non sorted Vector. Vehicle with id:"+" "+v.getCurrentID()+
					" "+"has a velocity"+" "+vel+" "+"The distance covered is:"+" "+dist);
				vect.addElement(v);
				
			}//end of while loop
		}//end of try
		catch(IOException ioe)
		{
			System.out.println("Something is wrong");
		}
		return vect;
	}

	  public Vector sortVector(Vector vc)
   {
		
		Vector newvector=new Vector();
		//System.out.println(vc.size());
		for(int j=0; j<=vc.size()-1; j++)
			newvector.addElement(vc.get(j));
		//System.out.println(newvector.size());
		
		Vehicle temp;
		int subvectorlength=0;
		
		for (int j=0; j<=newvector.size()-1; j++ )
		{
			subvectorlength=newvector.size()-j;
                  //find index of largest element in subarray
			int index=0;
			for (int k=1; k<subvectorlength; k++)
			{   
				Object obj=newvector.get(k);
				Vehicle veh=(Vehicle)obj;
				Object obj1=newvector.get(index);
				Vehicle veh1=(Vehicle)obj1;
				
				if (veh.calculateVelocity(1.0) > veh1.calculateVelocity(1.0))
				{
					index=k;					
				}
			}
			//swap code for the vector
			Object ob=newvector.elementAt(index);
			temp=(Vehicle)ob;
			newvector.set(index,newvector.elementAt(newvector.size()-j-1));
			newvector.set(newvector.size()-j-1,temp);
			
		}
		return newvector;
   }


	  public int arrayBinarySearch(Vehicle [] sort, double key)
  {        
		  /*
		   for(int i=0; i<sort.length-1; i++)
			   System.out.println("a="+" "+sort[i].getAcceleration());*/
		   int start = 0, end = sort.length - 1;
		   while ( end >= start )
		   {
			  int middle = ( start + end ) / 2;  
			  if ( sort[middle].getAcceleration() == key )
				 return sort[middle].getCurrentID();   // key found 
			  else if ( sort[middle].getAcceleration() > key )
				 end = middle - 1; // search left  
			  else
				 start = middle + 1; // search right 
		   }
		   return -1;  // key not found
	}

	public int vectorBinarySearch(Vector sortvect, double key)
	{
			int i=0;
			
			 
			  int index=0; 
			  
			  boolean flag=true;
			  int start = 0, end=sortvect.size()-1;
			  int middle=0;
			  Vehicle veha1=null;
			  while(end>=start&&flag)
			  {
					//System.out.println("inside while loop");
					middle=(start+end)/2;  
					//System.out.println("middle="+middle);
					Object vo=sortvect.get(middle);
					Vehicle veha=(Vehicle)vo;
					if(veha.getVelocity()==key)
					{
					   index= middle;   // key found
					   flag=false;
					}
					else if(veha.getVelocity()>key )
						end=middle-1; // search left  
					else
						start=middle+1; // search right 
					
			  }
			  if(flag==false)
			  {
				Object vo1=sortvect.get(index);
				veha1=(Vehicle)vo1;
				i=veha1.getCurrentID();
				
			  }
			  else if(flag==true)
			    i=-1;
			  
		
			  
			//System.out.println(veha1.getVelocity());

		
			return i;


		}//end binarySearch method
}
