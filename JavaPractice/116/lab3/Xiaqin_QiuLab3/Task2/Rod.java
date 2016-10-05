package XIAQIN.QIU;
import java.text.*;

public class Rod{
	MaterialCategories mc=null;
	String name="";
	double CSA=0.;
	double length=0.;
	int temperature=0;
	int idNum=0;
	
	static int id=0;
	
	public Rod(){
		setMaterial(null);
		setName("any");
		setArea(-0.1);
		setLength(-0.1);
		setTemperature(0);
		id++;
		idNum=id;
	}
	public Rod(int t, String nm, double a, double l){
		setMaterial(null);
		setName(nm);
		setArea(a);
		setLength(l);
		setTemperature(t);
		id++;
		idNum=id;
	}
	
	public void setMaterial(MaterialCategories m){
		mc=m;
	}
	public void setName(String nm){
		name=nm;
	}
	public void setArea(double a){
		CSA=a;
	}
	public void setLength(double l){
		length=l;
	}
	public void setTemperature(int temp){
		temperature=temp;
	}
	public MaterialCategories getMaterial(){
		return mc;
	}
	public String getName(){
		return name;
	}
	public double getCSA(){
		return CSA;
	}
	public double getLength(){
		return length;
	}
	public int getTemperature(){
		return temperature;
	}
	public int getID(){
		return idNum;
	}
	
	public double calculateExpansion(double a){
		DecimalFormat myFormater4 = new DecimalFormat("0.0000");
		double dl=a*length*temperature;
		String temp=myFormater4.format(dl);
		dl=Double.parseDouble(temp);
		return dl;
	}
	public double calculateForce(double y, double dl){
		DecimalFormat myFormater2 = new DecimalFormat("0.0");
		double f=0.;
		if(length>0){
			f=y*CSA*dl/length;
		}
		else f=-0.1;
		String temp=myFormater2.format(f);
		f=Double.parseDouble(temp);
		return f;
	}
	
	public String toString(){
		String str="The category is: "+mc+" The name of the material is "+name+" The length is "+length+" The cross area is "+CSA+" The temperature is: "+temperature+" the object id is "+idNum;
		return str;
	}
	public boolean equals(Rod obj){
		if(this.getName().equals(obj.getName()) && this.getMaterial().equals(obj.getMaterial())){
			return true;
		}
		else return false;
	}
}
