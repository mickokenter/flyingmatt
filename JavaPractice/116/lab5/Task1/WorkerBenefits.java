package Client;
import Client.Services.Enums.Jobs;
import Client.Services.*;
import java.util.*;
import java.io.*;

public class WorkerBenefits{
/**	public static void main(String[] args){
		WorkerBenefits workerBnft=new WorkerBenefits();
		ArrayList<Worker> aList=workerBnft.listOfWorkers();
		workerBnft.displayData(aList);
	}**/
	public ArrayList<Worker> listOfWorkers(){
		ArrayList<Worker> list=new ArrayList<Worker>();
		try{
			File myfile=new File("worker.txt");
			Scanner scan=new Scanner(myfile);
			while(scan.hasNextLine()){
				String line=scan.nextLine();
				StringTokenizer strtk=new StringTokenizer(line,",");
				String jobsT=strtk.nextToken();
				String nmT=strtk.nextToken();
				String sST=strtk.nextToken();
				String yeT=strtk.nextToken();
				String wbT=strtk.nextToken();
				Jobs Steve=Jobs.valueOf(jobsT);
				int ssn=Integer.parseInt(sST);
				int years=Integer.parseInt(yeT);
				double wbs=Double.parseDouble(wbT);
				if(jobsT.equals("ELECTRICAL_ENGINEER") || jobsT.equals("MECHANICAL_ENGINEER")){
					Engineer egnr=new Engineer(nmT,ssn,years,Steve,wbs);
					list.add(egnr);
				}
				else{
					String special=strtk.nextToken();
					double dTemp=Double.parseDouble(special);
					if(jobsT.equals("ADMINISTRATIVE_ASSISTANT") || jobsT.equals("ADMINISTRATIVE_SECRETARY")){
						AdministrativePersonnel admin=new AdministrativePersonnel(nmT,ssn,years,Steve,wbs,dTemp);
						list.add(admin);
					}
					else{
						Management mgmt=new Management(nmT,ssn,years,Steve,wbs,dTemp);
						list.add(mgmt);
					}
				}
			} 
		}
		catch(IOException ioe){
			System.out.println("Something went wrong.");
		}
		catch(IllegalArgumentException ia){
			System.out.println("Something went wrong.");
		}
		catch(NullPointerException np){
			System.out.println("Something went wrong.");
		}
		return list;
	}
	
	public void displayData(ArrayList<Worker> lst){
		for(int i=0; i<lst.size(); i++){
			System.out.println("The benefit is "+lst.get(i).benefitsCalculation(lst.get(i).getJobs())+" The name is: "+lst.get(i).getName()+" The Job type type is "+lst.get(i).getJobs()+" The id is: "+lst.get(i).getCurrentID());
		}
	}
	
	public double totalBenefits(ArrayList<Worker> lst){
		double tb=0.;
		for(int i=0; i<lst.size(); i++){
			tb=tb+lst.get(i).benefitsCalculation(lst.get(i).getJobs());
		}
		return tb;
	}
}