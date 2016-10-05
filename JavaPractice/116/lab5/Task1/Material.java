package Client.Services.Enums.Help;

public class Material{
	String materialName;
	double unitPrice;
	int quantity;
	static double materialcostforallobjects;
	
	public Material(){
		materialName="";
		unitPrice=0;
		quantity=0;
	}
	public Material(String m, double u, int q){
		materialName=m;
		unitPrice=u;
		quantity=q;
	}
	
	public void setMaterialName(String m){
		materialName=m;
	}
	public void setUnitPrice(double u){
		unitPrice=u;
	}
	public void setQuantity(int q){
		quantity=q;
	}
	public String getMaterialName(){
		return materialName;
	}
	public double getUnitPrice(){
		return unitPrice;
	}
	public int getQuantity(){
		return quantity;
	}
	public double getMaterialCostForAllObjects(){
		return materialcostforallobjects;
	}
	
	public double materialPrice(){
		double price;
		if(quantity>=100){
			price=unitPrice*quantity*0.8;
		}
		else{
			price=unitPrice*quantity*0.9;
		}
		materialcostforallobjects=materialcostforallobjects+price;
		System.out.println("Material price="+price+"\nTotal material cost="+materialcostforallobjects);
		return price;
	}
	
	public String toString(){
		String str="The material name is: "+materialName+" The unit price is: "+unitPrice+" The quantity is: "+quantity+" All the Material cost is: "+materialcostforallobjects;
		return str;
	}
}