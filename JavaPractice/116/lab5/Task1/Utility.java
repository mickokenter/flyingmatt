package Client.Services.Enums.Help;

public class Utility{
	double electricity=0.;
	double gas=0.;
	double water=0.;
	static double utilcostsforallobjects;
	
	public Utility(){
		electricity=0;
		gas=0;
		water=0;
	}
	public Utility(double e, double g, double w){
		electricity=e;
		gas=g;
		water=w;
	}
	
	public void setElectricity(double e){
		electricity=e;
	}
	public void setGas(double g){
		gas=g;
	}
	public void setWater(double w){
		water=w;
	}
	public double getElectricity(){
		return electricity;
	}
	public double getGas(){
		return gas;
	}
	public double getWater(){
		return water;
	}
	public double getUtilCostsForAllObjects(){
		return utilcostsforallobjects;
	}
	
	public double totalUtilCosts(){
		double sum=electricity+gas+water;
		utilcostsforallobjects=utilcostsforallobjects+sum;
		System.out.println("util cost for this object="+sum+"\ntotal utilities of all objects="+utilcostsforallobjects);
		return sum;
	}
	
	public String toString(){
		String str="The electricity is: "+electricity+" The gas is: "+gas+" The water is: "+water+" All the Utility cost is: "+utilcostsforallobjects;
		return str;
	}
}