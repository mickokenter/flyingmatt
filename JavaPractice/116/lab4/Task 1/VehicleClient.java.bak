//George Koutsogiannakis
package Client;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.io.File;
import java.io.IOException;
import java.util.Vector;
import java.util.ArrayList;
import Client.Services.*;
import Client.Services.Enums.*;
public class VehicleClient
{
	public static void main(String[] args) 
	{
		VehicleClient vc=new VehicleClient();
		
		System.out.println("-------------FIRST OUTPUT ----------------");
		Vehicle [] vobjs=vc.carData();
		
		ArrayList<Vehicle> arlcd=new ArrayList<Vehicle>();
		for(int i=0; i<vobjs.length; i++)
		{
			
			arlcd.add(vobjs[i]);
		}
		//Get the Deler information so that the CarDealer instance variable reference of Vehicle can be set to a specific CarDealer object 

		CarDealer [] dealers=dealerData();

		for(int j=0; j<dealers.length; j++)
		{
			arlcd.get(j).setCarDealer(dealers[j]);
		}
		for(Vehicle cade: arlcd)
			System.out.println("The price of car with id= "+cade.getCurrentID()+" "+"is : "+cade.getCarDealer().getSale()+" "+"The sale status is: "+cade.getSold());
		
		System.out.println("-------------SECOND OUTPUT----------------");
	
		double[][][] s=vc.tripleArray(arlcd);
		System.out.println("\n");
		System.out.println("\t"+"\t"+"MON"+"\t"+"\t"+"TUE"+"\t"+"\t"+"WED"+"\t"+"\t"+"THU"+"\t"+"\t"+"FRI"+"\t"+"\t"+"SAT");
		for(int i=0; i<=s.length-1; i++)
		{   if(i==0)
			{
			  System.out.println("\n");
			  System.out.print("ID1");
			  System.out.println("\n");
			}
			else if(i==1)
			{
				System.out.println("\n");
				System.out.print("ID2"+"\t");
				System.out.println("\n");
			}
			else if(i==2)
			{
				System.out.println("\n");
				System.out.print("ID3"+"\t");	
				System.out.println("\n");
			}
			else if(i==3)
			{
				System.out.println("\n");
			    System.out.print("ID4"+"\t");
				System.out.println("\n");
			}
			else if(i==4)
			{
				System.out.println("\n");
			    System.out.print("ID5"+"\t");
				System.out.println("\n");

			}
			for(int j=0; j<=s[i].length-1; j++)
			{	
				if(j==0)
				{
					System.out.print("week 1:"+"\t");
					
				}
				else if(j==1)
				{
					System.out.print("\n");
					System.out.print("week 2:"+"\t");
				}
				for(int k=0; k<=s[i][j].length-1; k++)
				{
					if(k==1||k==3||k==5)
					  System.out.print("\t"+"\t"+s[i][j][k]+"\t");
					else
					  System.out.print("\t"+s[i][j][k]);
				}
			}
		}
		System.out.println("\n");
		System.out.println("-------------THIRD OUTPUT----------------");
	    for(Vehicle cade: arlcd)
			System.out.println("The price of car with id= "+cade.getCurrentID()+" "+"The sale status is: "+cade.getSold());
	}
	

	///////////////END OF MAIN METHOD //////////////////////////////////////////

	public Vehicle[] carData()
	{
		
		Vehicle [] veharray=null;
		//double[] varray=null;
		//double[] darray=null;
		//double[] farray=null;
		int count=0;
		try
		{
			File file =new File("Vehicles.txt");
			Scanner scan=new Scanner(file);
			VehicleEngine vehe=null;
			while(scan.hasNextLine())
			{
				scan.nextLine();
				count++;
			}

			veharray=new Vehicle[count];
			//varray=new double[count];
			//darray=new double[count];
			//farray=new double[count];
			Scanner scan1=new Scanner(file);
			int index=0;
			while(scan1.hasNextLine())
			{
				String line=scan1.nextLine();
				StringTokenizer strtok=new StringTokenizer(line,",");
				String tok1=strtok.nextToken();
				
				double a=Double.parseDouble(tok1);
				String tok2=strtok.nextToken();

				int t=Integer.parseInt(tok2);
				String tok3=strtok.nextToken();

				String tok4=strtok.nextToken();

				if(tok4.equals("Type1"))
				{
					
					vehe=VehicleEngine.Type1;
				}
				else if(tok4.equals("Type2"))
				{
					
					vehe=VehicleEngine.Type2;
				}
				else if(tok4.equals("Type3"))
				{
				   vehe=VehicleEngine.Type3;
				}
				else if(tok4.equals("Type4"))
				{
					vehe=VehicleEngine.Type4;
				}
				Vehicle v=new Vehicle(a,t,tok3,vehe);
				veharray[index]=v;
				
				index++;
			}
		}//end of try
		catch(IOException ioe)
		{
		   System.out.println("Something is wrong");
		}
		return veharray;
	}

	public static CarDealer[] dealerData()
	{
		CarDealer[] dealarray=null;
		Dealers d=null;
		int count=0;
		try
		{
			File file =new File("Vehicles.txt");
			Scanner scan=new Scanner(file);
			VehicleEngine vehe=null;
			while(scan.hasNextLine())
			{
				scan.nextLine();
				count++;
			}

			dealarray=new CarDealer[count];
			
			Scanner scan1=new Scanner(file);
			int index=0;
			while(scan1.hasNextLine())
			{
				String line=scan1.nextLine();
				StringTokenizer strtok=new StringTokenizer(line,",");
				strtok.nextToken();
				strtok.nextToken();
				strtok.nextToken();
				strtok.nextToken();
				String tok1=strtok.nextToken();
				
				String tok2=strtok.nextToken();
				double sal=Double.parseDouble(tok2);
				
				if(tok1.equals("ID1"))
				{
					
					d=Dealers.ID1;
				}
				else if(tok1.equals("ID2"))
				{
					
					d=Dealers.ID2;
				}
				else if(tok1.equals("ID3"))
				{
				   d=Dealers.ID3;
				}
				else if(tok1.equals("ID4"))
				{
					d=Dealers.ID4;
				}
				else if(tok1.equals("ID5"))
				{
					d=Dealers.ID5;
				}

				CarDealer cd=new CarDealer(sal);
				cd.setDealer(d);
				dealarray[index]=cd;
		
				index++;
			}
		}
		catch(IOException ioe)
		{
		   System.out.println("Something is wrong");
		}
		
		
		return dealarray;
	}

	public Vehicle[] sortCars(Vehicle[] vh)
	{

		
		Vehicle [] veh=new Vehicle[vh.length];
		for(int f=0; f<=vh.length-1;f++)
			veh[f]=vh[f];
		
		
		
		
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

		return veh;

	}

	public Vector vectorOfVehicles()
    {
		
		Vector vect=new Vector();
		try
		{
			VehicleEngine vehe=null;
			Vehicle v1=new Vehicle();
				v1.setVehicleID(0);
			File file=new File("Vehicles.txt");
			
			Scanner scan=new Scanner(file);
			Scanner scan1=new Scanner(file);
			
			while(scan1.hasNextLine())
			{
				String line=scan1.nextLine();
				StringTokenizer strtok=new StringTokenizer(line,",");
				String tok1=strtok.nextToken();
				double a=Double.parseDouble(tok1);
				String tok2=strtok.nextToken();
				int t=Integer.parseInt(tok2);
				String tok3=strtok.nextToken();
				String tok4=strtok.nextToken();
				if(tok4.equals("Type1"))
				{
					
					vehe=VehicleEngine.Type1;
				}
				else if(tok4.equals("Type2"))
				{
					
					vehe=VehicleEngine.Type2;
				}
				else if(tok4.equals("Type3"))
				{
				   vehe=VehicleEngine.Type3;
				}
				else if(tok4.equals("Type4"))
				{
					vehe=VehicleEngine.Type4;
				}
				Vehicle v=new Vehicle(a,t,tok3,vehe);
					
				vect.addElement(v);
				
			}//end of while loop
		}//end of try
		catch(IOException ioe)
		{
			System.out.println("Something is wrong");
		}
		return vect;

    }

	public  Vector sortVector(Vector v)
	{

		
		Vector newvector=new Vector();
		//System.out.println(vc.size());
		for(int j=0; j<=v.size()-1; j++)
			newvector.addElement(v.get(j));
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
				CarDealer cd=veh.getCarDealer();
				double sale=cd.getSale();
				CarDealer cd1=veh1.getCarDealer();
				double sale1=cd1.getSale();
				if (sale> sale1)
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

	public ArrayList<CarDealer> sortDealerList(ArrayList<CarDealer> ald)
	{
		ArrayList<CarDealer> list=new ArrayList<CarDealer>();
		for(int i=0; i<ald.size(); i++)
			list.add(ald.get(i));

        CarDealer temp;
		int sublistlength=0;
		
		for (int j=0; j<=list.size()-1; j++ )
		{
			sublistlength=list.size()-j;
                  //find index of largest element in subarray
			int index=0;
			for (int k=1; k<sublistlength; k++)
			{   
				CarDealer obj=list.get(k);
				
				CarDealer obj1=list.get(index);
				
				
				double sale=obj.getSale();
				
				double sale1=obj1.getSale();
				if (sale> sale1)
				{
					index=k;					
				}
			}
			//swap code for the vector
			temp=list.get(index);
			
			list.set(index,list.get(list.size()-j-1));
			list.set(list.size()-j-1,temp);
			
		}

	return list;

	}

	public static int vectorBinarySearch(Vector sortvect, double   key)
	{

		
			int i=0;
			
			 
			  int index=0; 
			  
			  boolean flag=true;
			  int start = 0;
			  int end=sortvect.size()-1;
			  int middle=0;
			  Vehicle veha1=null;
			  while(end>=start&&flag)
			  {
					
					middle=(start+end)/2;  
					
					Object vo=sortvect.get(middle);
					Vehicle veha=(Vehicle)vo;
					
					if(veha.getCarDealer().getSale()==key)
					{
					   index= middle;   // key found
					  
					   flag=false;
					}
					else if(veha.getCarDealer().getSale()>key )
						end=middle-1; // search left  
					else
						start=middle+1; // search right 
					
			  }
			  if(flag==false)
			  {
				
				
				return index;
				
			  }
			  else if(flag==true)
			    i=-1;
			  
		
			  
			
		
			return i;



	}


	public double[][][] tripleArray(ArrayList<Vehicle> al)
	{
			double[][][] sales=new double[5][2][6];
			double total=0.0;
			int countw1=0;
			int countw2=0;
			//i is dealers , j is weeks, h is days of week
			
			for(int i=0; i<=sales.length-1; i++)
			{
				for(int j=0; j<=sales[i].length-1; j++)
				{
					for(int h=0; h<=sales[i][j].length-1; h++)
					{
						//if oneDealer and day is M,W,or F
						if((i==0)&&(h==0||h==2||h==4))
						{
							
							if(j==0)
							{
								sales[i][j][h]=sales[i][j][h]+al.get(countw1).getCarDealer().getSale();
								al.get(countw1).setSold(true);
								//System.out.println(al.get(countw1).getCurrentID());
								countw1++;
								countw2=countw1;
							}
							else
							{
									
								
								sales[i][j][h]=sales[i][j][h]+al.get(countw2+12).getCarDealer().getSale();
								al.get(countw2+12).setSold(true);
								//System.out.println(al.get(countw2+12).getCurrentID());
								countw2++;
							}
						}
						//if twoDealer and day is T, TH or S
						else if((i==1)&&(h==1||h==3||h==5))
						{   
							//if week=1
							if(j==0)
							{
								
								sales[i][j][h]=sales[i][j][h]+al.get(countw1).getCarDealer().getSale();
								al.get(countw1).setSold(true);
								//System.out.println(al.get(countw1).getCurrentID());
								countw1++;
								countw2=countw1;
							}
							//else if week =2
							else
							{
								
								sales[i][j][h]=sales[i][j][h]+al.get(countw2+12).getCarDealer().getSale();
								al.get(countw2+12).setSold(true);
								//System.out.println(al.get(countw2+12).getCurrentID());
								countw2++;
							}
						}

						///if threeDealer and day =M,W or F
						else if((i==2)&&(h==0||h==2||h==4))
						{   
							//if week=1
							if(j==0)
							{
								sales[i][j][h]=sales[i][j][h]+al.get(countw1).getCarDealer().getSale();
								al.get(countw1).setSold(true);
								//System.out.println(al.get(countw1).getCurrentID());
								countw1++;
								countw2=countw1;
							}
							//else if week =2
							else
							{
								
								sales[i][j][h]=sales[i][j][h]+al.get(countw2+12).getCarDealer().getSale();
								al.get(countw2+12).setSold(true);
								//System.out.println(al.get(countw2+12).getCurrentID());
								countw2++;
							}
						}
						//if fourDealer and day=T,Th,S
						else if((i==3)&&(h==1||h==3||h==5))
						{   
							//if week=1
							if(j==0)
							{
								sales[i][j][h]=sales[i][j][h]+al.get(countw1).getCarDealer().getSale();
								al.get(countw1).setSold(true);
								//System.out.println(al.get(countw1).getCurrentID());
								countw1++;
								countw2=countw1;
							}
							//else if week =2
							else
							{
								
								sales[i][j][h]=sales[i][j][h]+al.get(countw2+12).getCarDealer().getSale();
								al.get(countw2+12).setSold(true);
								//System.out.println(al.get(countw2+12).getCurrentID());
								countw2++;
							}
						}
						//if five Dealer and Day=M,W,F
						else if((i==4)&&(h==0||h==2||h==4))
						{   
							//if week=1
							if(j==0)
							{
								sales[i][j][h]=sales[i][j][h]+al.get(countw1).getCarDealer().getSale();
								al.get(countw1).setSold(true);
								//System.out.println(al.get(countw1).getCurrentID());
								countw1++;
								countw2=countw1;
							}
							//else if week =2
							else
							{
								
								sales[i][j][h]=sales[i][j][h]+al.get(countw2+12).getCarDealer().getSale();
								al.get(countw2+12).setSold(true);
								//System.out.println(al.get(countw2+12).getCurrentID());
								countw2++;
							}
						}
						
						
					}
				}
			}
			return sales;
	}
}
