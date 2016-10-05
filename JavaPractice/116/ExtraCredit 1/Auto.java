public class Auto{
	int yearManufactured=0;
	String model="";
	double ODO=0;
	int ID4EachCar;
	static int ID=0;
	public Auto(){
		yearManufactured=2010;
		model="Chrysler";
		ODO=10.5;
		ID++;
		ID4EachCar=ID;
	}
	public Auto(int year, String modelName, double ODOmeter){
		setYear(year);
		setModel(modelName);
		setODO(ODOmeter);
		ID++;
		ID4EachCar=ID;
	}
	public void setYear(int year){
		if(year>0){
			yearManufactured=year;
		}
		else yearManufactured=2010;
	}
	public void setModel(String modelName){
		if(!modelName.equals("")){
			model=modelName;
		}
		else model="Chrysler";
	}
	public void setODO(double ODOmeter){
		if(ODOmeter>0){
			ODO=ODOmeter;
		}
		else ODO=10.5;
	}
	public int getYearManufactured(){
		return yearManufactured;
	}
	public String getModel(){
		return model;
	}
	public double getODO(){
		return ODO;
	}
	public int getID(){
		return ID;
	}
	public String toString(){
		String output;
		output="The object id is: "+ID4EachCar+" The model is "+model+" The miles are: "+ODO+" The year manufactured is: "+yearManufactured+" The static ID is: "+ID;
		return output;
	}
	public boolean equals(Auto AutoSample){
		if((this.getModel().equals(AutoSample.getModel()))&&(this.getODO()==AutoSample.getODO())){
			return true;
		}
		else return false;
	}
}