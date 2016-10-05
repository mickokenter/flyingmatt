package Client.Services.Enums.Help;

public class Rent{
	String location="";
	int yearOfLeasing=0;
	int buildingNumber=0;
	static double rentcostforallobjects;
	
	public Rent(){
		location="";
		yearOfLeasing=0;
		buildingNumber=0;
	}
	public Rent(String l, int y, int b){
		location=l;
		yearOfLeasing=y;
		buildingNumber=b;
	}
	
	public void setLocation(String l){
		location=l;
	}
	public void setYearOfLeasing(int y){
		yearOfLeasing=y;
	}
	public void setBuildingNumber(int b){
		buildingNumber=b;
	}
	public String getLocation(){
		return location;
	}
	public int getYearOfLeasing(){
		return yearOfLeasing;
	}
	public int getBuilidngNumber(){
		return buildingNumber;
	}
	public double getRentCostForAllObjects(){
		return rentcostforallobjects;
	}
	
	public double rent(){
		double locationDouble=Double.parseDouble(location);
		double buildingRent=locationDouble*yearOfLeasing*150/buildingNumber;
		rentcostforallobjects=rentcostforallobjects+buildingRent;
		System.out.println("Building rent="+buildingRent+"\nTotal rent for all objects is ="+rentcostforallobjects);
		return buildingRent;
	}
	
	public String toString(){
		String str="The location is: "+location+" The year of leasing is: "+yearOfLeasing+" The building number is: "+buildingNumber+" All the Rent cost is: "+rentcostforallobjects;
		return str;
	}
}