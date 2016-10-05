
package folder1;
import folder1.folder2.Auto;
import folder1.folder2.model;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.*;
public class  AutoClient
{
	public static void main(String[] args) 
	{
		Auto[] autos=null;
		model md=null;
		try
		{
			File file=new File("auto.txt");
			Scanner scan=new Scanner(file);
			int count=0;
			while(scan.hasNextLine())
			{
				scan.nextLine();
				count++;
			}
			autos=new Auto[count];
			Scanner scan1=new Scanner(file);
			int index=0;
			String s2[]=new String[count];
			Auto car[]=new Auto[count];
			while(scan1.hasNextLine())
			{
				String line=scan1.nextLine();
				StringTokenizer strtok=new StringTokenizer(line);
				String s1=strtok.nextToken();
				int year=Integer.parseInt(s1);
				s2[index]=strtok.nextToken();
				String s3=strtok.nextToken();
				double mileage=Double.parseDouble(s3);
				md=model.valueOf(s2[index]);
				car[index]=new Auto(year,md,mileage);
				autos[index]=car[index];
				index++;
			}

		
		
		for(int a=0;a<count;a++){
		int aaaaa=0;
		if(s2[a].equals("BMW"))
	   {aaaaa=0;}
	    else if(s2[a].equals("FIAT"))
	   {aaaaa=1;}
	    else if(s2[a].equals("FORD"))
	   {aaaaa=2;}
	   else if(s2[a].equals("GM"))
	   {aaaaa=3;}
	   else if(s2[a].equals("MERCEDES"))
	   {aaaaa=4;}
	   else if(s2[a].equals("CHRYSLER"))
	   {aaaaa=5;}
	 System.out.println("trying again");
	 System.out.println("The position of the value in the enumerarion is: "+aaaaa);
	   }
		
		
		double max=autos[0].getMiles();
		int num=0;
		for(int i=0; i<=autos.length-1; i++)
		{
		   System.out.println(autos[i]);
		   if(autos[i].getMiles()>max)
			{
			   max=autos[i].getMiles();
			   num=i;
			}
		}
		System.out.println("The auto with the highest mileage is:"+"\n"+autos[num].toString());
		
		
		for(int a=0; a<count-1;a++)
		{
			for(int b=a+1; b<count;b++)
			{
			 if(model.valueOf(s2[a]).equals(md=model.valueOf(s2[b])))
			{System.out.println("The model of the Auto object with id:  "+(a+1)+" is the same model as  the Auto object with id: "+(b+1));    }		
			}
		}
		
		
		
		
		
		}//try
		catch (IOException ioe)
		{
			System.out.println(ioe.getMessage());
		}
	}
}
