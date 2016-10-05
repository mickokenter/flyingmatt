//George Koutsogiannakis
package ClientClass;
import java.util.*;
import java.io.*;
import ClientClass.ServiceClasses.*;


public class NewVehicleClient  
{
	public static void main(String[] args) 
	{
		//System.out.println("I am ready to get the array");
		
		Data aov=new Data();
		Vehicle [] varray=aov.arrayOfObjects();
		//System.out.println("I got the array");
		System.out.println("NON SORTED ARRAY OUTPUT-ACCELERATIONS AS LISTED IN TEXT FILE");
		for(int i=0; i<=varray.length-1; i++)
			System.out.println(varray[i].toString());
       //  System.out.println("CALL FOR selectionSort FOR ARRAY");
		Vehicle [] sorted=aov.selectionSort(varray);
		
		System.out.println("OUTPUT OF SORTED ACCELERATION VALUES-ADJUSTED ACCELERATIONS");
		System.out.println("-------------------------------");
		for (int k=0; k<=sorted.length-1; k++)
		{
			System.out.println(sorted[k].toString());
		}

/*
		//CODE FOR RE ADJUSTING THE ACCELERATION TO ORIGINAL VALUES BEFORE THE VELOCITY IS CALCULATED.
		System.out.println("NEXT THE VELOCITIES AND DISTANCES WILL BE DISPLAYED");
		System.out.println("-----------------------------------------------");
		//we need to restore acceleration to its pre sorting values

		try
		{
			File file=new File("Vehicles.txt");
			Scanner scan=new Scanner(file);
			int index=0;
			while(scan.hasNextLine())
			{
				String line=scan.nextLine();
				StringTokenizer strtok=new StringTokenizer(line,",");
				String ac=strtok.nextToken();
				double acd=Double.parseDouble(ac);
				varray[index].setAcceleration(acd);
				//System.out.println(varray[index].toString());
				index++;
			}
			double [] vel=new double[sorted.length];
			for(int i=0; i<=sorted.length-1; i++)
			{
				
				//System.out.println(sorted[i].toString());
				vel[i]=sorted[i].calculateV(2);
			}
			int index1=0;
			while(scan.hasNextLine())
			{
				String line=scan.nextLine();
				StringTokenizer strtok=new StringTokenizer(line,",");
				String ac=strtok.nextToken();
				double acd=Double.parseDouble(ac);
				varray[index1].setAcceleration(acd);
				
				index1++;
			}
			double [] dist=new double[sorted.length];
			for(int i=0; i<=sorted.length-1; i++)
				dist[i]=sorted[i].calculateD(2,2);
			for(int j=0; j<=sorted.length-1; j++)
				System.out.println("Vehicle with id="+" "+sorted[j].getCurrentID()+
				" "+"has velocity"+" "+vel[j]+" "+"distance traveled is"+" "+dist[j]);

		}
		catch (IOException ioe)
		{
				System.out.println("Something is wrong");
		}
*/
		System.out.println("FIND IF A VEHICLE HAS ADJUSTED ACCEL.=6.36");
		System.out.println("---------------------------------------");
		
		
		int x=aov.arrayBinarySearch(sorted,6.36);
		System.out.println(x);
		if(x>0)
			System.out.println("The vehicle has id ="+" "+x);
		else
			System.out.println("The vehicle was not found");
		
		System.out.println("NON SORTED VECTOR VALUES");
		System.out.println("---------------------------------------");
		
		Vector nonsortedvec=aov.vectorOfVehicles();
		for(int i=0; i<=nonsortedvec.size()-1; i++)
		{
			Vehicle obj=(Vehicle)nonsortedvec.get(i);
			obj.calculateValue();
			System.out.println(nonsortedvec.get(i).toString());
		}

		System.out.println("SORTED VECTOR VALUES");
		System.out.println("---------------------------------------");
		Vector sortedvect=aov.sortVector(nonsortedvec);
		for(int f=0; f<=sortedvect.size()-1; f++)
		{
			Object o=sortedvect.get(f);
			Vehicle v=(Vehicle)o;
			System.out.println(v.toString());
		}
		System.out.println("FIND VEHICLEs WHOSE VELOCITY=7.0 and 41.5");
		System.out.println("---------------------------------------");
		int i=aov.vectorBinarySearch(sortedvect, 7.0);
		
		
		//System.out.println(sortedvect.get(8));
		if(i!=-1)
			System.out.println("The vehicle with velocity=7.0 was found and its id="+" "+i);
		else
			System.out.println("The vehicle with velocity=7.0 was NOT found because id="+" "+i);
		i=aov.vectorBinarySearch(sortedvect, 41.5);
		if(i!=-1)
			System.out.println("The vehicle velocity=41.5 was found and its id="+" "+i);
		else
			System.out.println("The vehicle velocity=41.5 was NOT found because id="+" "+i);
	}
}
