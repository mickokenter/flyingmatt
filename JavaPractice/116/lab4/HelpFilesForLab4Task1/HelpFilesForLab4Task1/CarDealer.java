//George Koutsogiannakis
package Client.Services;
import Client.Services.Vehicle;
import Client.Services.Enums.*;
public class CarDealer
{
	Dealers deal=null;
	Vehicle v=null;
	double sale=0.0;
	static int id=0;
	int currentID=0;


	public CarDealer()
	{
		sale=0.0;
		deal=null;
		v=null;
		id++;
		currentID=id;
	}

	public CarDealer(double s)
	{
		sale=s;
		deal=null;
		v=null;
		id++;
		currentID=id;
	}

	public Dealers getDealer()
	{
		return deal;
	}

	public double getSale()
	{
		return sale;
	}

	public Vehicle getVehicle()
	{

		return v;
	}

	public int getCurrentID()
	{
		return currentID;
	}

	public void setDealer(Dealers de)
	{
		deal=de;
	}

	public void setSale(double sa)
	{
		sale=sa;
	}

	public void setVehicle(Vehicle veh)
	{
		v=veh;
	}

	public void setCurrentID(int cid)
	{
		currentID=cid;
	}

	public String toString()
	{
		String out="The Dealer is: "+" "+deal+" "+"The sale price of the car is: "+" "+sale+" "+"The car is: "+" "+v+" "+"The dealer object id is: "+currentID;
		return out;
	}
}
