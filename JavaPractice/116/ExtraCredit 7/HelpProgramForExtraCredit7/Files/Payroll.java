//George Koutsogiannakis
//Spring 2010
import java.util.*;
import java.io.*;
public class Payroll  
{
	public ArrayList<Employee> listOfEmployees()
	{

		ArrayList<Employee> al=new ArrayList<Employee>();
		try
		{
		   File file=new File("employee.txt");
		   Scanner scan=new Scanner(file);
		   while(scan.hasNextLine())
		   {
				String line=scan.nextLine();
				StringTokenizer strtok=new StringTokenizer(line, ",");
				String emp=strtok.nextToken();
				if(emp.equals("Engineer"))
				{
					String name=strtok.nextToken();
					String ssstr=strtok.nextToken();
					int ss=Integer.parseInt(ssstr);
					String wsstr=strtok.nextToken();
					double ws=Double.parseDouble(wsstr);
					String yestr=strtok.nextToken();
					int ye=Integer.parseInt(yestr);
					Engineer eng=new Engineer(name,ss,ws,ye);
					al.add(eng);
				}
				else if(emp.equals("Manager"))
				{

					String name=strtok.nextToken();
					String ssstr=strtok.nextToken();
					int ss=Integer.parseInt(ssstr);
					String wsstr=strtok.nextToken();
					double ws=Double.parseDouble(wsstr);
					String bonusstr=strtok.nextToken();
					double bonus=Double.parseDouble(bonusstr);
					Management man=new Management(name,ss,ws,bonus);
					al.add(man);

				}
				else if(emp.equals("AdministrativePersonnel"))
				{
					String name=strtok.nextToken();
					String ssstr=strtok.nextToken();
					int ss=Integer.parseInt(ssstr);
					String ratestr=strtok.nextToken();
					double rate=Double.parseDouble(ratestr);
					String hoursstr=strtok.nextToken();
					double hours=Double.parseDouble(hoursstr);
					AdministrativePersonnel adminp=new AdministrativePersonnel(name,ss,rate,hours);
					al.add(adminp);

				}
			  }//end of while loop
			}
			catch(IOException ioe)
			{
				System.out.println("Something is wrong");
			}
		
		return al;

	}

	public void outputEmployeeData(ArrayList<Employee> ale)
	{
		
		for(Employee e: ale)
		{
			
			double sal=e.compensation();
			System.out.println("The salary is"+" "+sal);
		}
		for(int i=0; i<ale.size(); i++)
		     System.out.println(ale.get(i).toString());
	}

	public double totalSalaries(ArrayList<Employee> ale)
	{
		
		double totalsalaries=0;
		
		for(Employee e: ale)
		{
			
			double sal=e.compensation();
			totalsalaries+=sal;
		}

		return totalsalaries;

	}

	public static void main(String[] args)
	{
		Payroll pay=new Payroll();
		ArrayList<Employee> arl=pay.listOfEmployees();
		pay.outputEmployeeData(arl);
		double sal=pay.totalSalaries(arl);
		System.out.println("The total amount of salaries paid is:"+" "+sal);
	}
		

}
