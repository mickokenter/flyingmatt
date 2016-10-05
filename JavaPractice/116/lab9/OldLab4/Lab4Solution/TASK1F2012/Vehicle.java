//George Koutsogiannakis

package ClientClass.ServiceClasses;
import java.text.DecimalFormat;
public class Vehicle 
{
	private String owners_name;
	private double acceleration;
	private int time;
	private Dealers deal;
	private EngineSizes ve;
	private static int vehicleID=100;
	private int currentID=0;
	private double value;
	private double velocity=0.0;
	
	public Vehicle()
	{
		owners_name=" ";

		acceleration=0.0;
		time=0;
		deal=null;
		ve=null;
		vehicleID++;
		currentID=vehicleID;
		value=0.0;
		velocity=0.0;
	}

	public Vehicle(double a, int t, String on, Dealers dl, EngineSizes veeg)
	{
		vehicleID++;
		currentID=vehicleID;
		
		acceleration=a;
		time=t;
		owners_name=on;
		deal=dl;
		ve=veeg;
		value=0.0;
		velocity=0.0;
	}

	public String getOwnersName()
	{
		return owners_name;
	}

	public double getAcceleration()
	{

		return acceleration;
	}


	public int getTime()
	{
		return time;
	}

	public int getVehicleID()
	{
		return vehicleID;
	}

	public int getCurrentID()
	{

		return currentID;
	}

	public Dealers getDealers()
	{
		return deal;
	}

	public EngineSizes getEngineSizes()
	{
		return ve;
	}

	public double getValue()
	{
		return value;
	}

	public double getVelocity()
	{
		return velocity;
	}

	public void setOwnersName(String name)
	{
		owners_name=name;
	}

	public void setTime(int t)
	{
		time=t;
	}

	public void setAcceleration(double ac)
	{

		acceleration=ac;

	}

	public void setDealers(Dealers d)
	{
		deal=d;
	}

    public void setEngineSizes(EngineSizes vehen)
	{
		ve=vehen;
	}

	public void setValue(double val)
	{
		value=val;
	}

   public void reSetVehicleID()
	{
	   vehicleID=100;
	}

	public void setVelocity(double veloc)
	{
		velocity=veloc;
	}


	public String toString()
	{

		String str="The vehicle ID is:"+" "+currentID+" "+"The name of the owner is:"+" "+owners_name+" "+"The acceleration is:"+" "+acceleration+
		"m/sec"+"" +"The velocity is"+" "+velocity+" "+"The dealership is:"+" "+deal+" "+"The engine Size is:"+" "+ve+" "+"The traveling time is;"+" "+time+" "+"The value is:"+" "+value;
		return str;
	}

	public double calculateVelocity(double i)
	{

		DecimalFormat nf=new DecimalFormat("0.00");
		
		double accel=0.00;
		if(ve.equals(EngineSizes.Litters_1Point5))
			accel=0.8*acceleration;
		else if(ve.equals(EngineSizes.Litters_2Point4))
			accel=1.2*acceleration;
		else if(ve.equals(EngineSizes.Litters_3Point0))
			accel=1.8*acceleration;
		else if(ve.equals(EngineSizes.Litters_4Point0))
			accel=2.0*acceleration;
		else if(ve.equals(EngineSizes.Litters_6Point0))
			accel=2.5*acceleration;
		double velo=accel*time+i;
		String strvel=nf.format(velo);
		double vel=Double.parseDouble(strvel);
		velocity=vel;
		return vel;

	}

	public double calculateDistance(double i, double d)
	{
		DecimalFormat nf=new DecimalFormat("0.00");
		double accel=0.0;
		if(ve.equals(EngineSizes.Litters_1Point5))
			accel=0.8*acceleration;
		else if(ve.equals(EngineSizes.Litters_2Point4))
			accel=1.2*acceleration;
		else if(ve.equals(EngineSizes.Litters_3Point0))
			accel=1.8*acceleration;
		else if(ve.equals(EngineSizes.Litters_4Point0))
			accel=2.0*acceleration;
		else if(ve.equals(EngineSizes.Litters_6Point0))
			accel=2.5*acceleration;

		double timed=(double)time;
		double t=Math.pow(timed, 2.0);
		double distance=0.5*accel*t+i*time+d;
		String dist=nf.format(distance);
		double di=Double.parseDouble(dist);
		return di;
	}

	public boolean equals(Vehicle v)
	{
		
		if((this.getValue()==v.getValue())&&(this.getEngineSizes().equals(v.getEngineSizes())))
		   return true;
		else
		    return false;
	}
	
	public void calculateValue()
	{
		
		double accel=0.0;
		if(ve.equals(EngineSizes.Litters_1Point5))
			accel=0.8*acceleration;
		else if(ve.equals(EngineSizes.Litters_2Point4))
			accel=1.2*acceleration;
		else if(ve.equals(EngineSizes.Litters_3Point0))
			accel=1.8*acceleration;
		else if(ve.equals(EngineSizes.Litters_4Point0))
			accel=2.0*acceleration;
		else if(ve.equals(EngineSizes.Litters_6Point0))
			accel=2.5*acceleration;

		if(accel>=0.10&&accel<=2.99)
			value=25000.00;
		else if(accel>=3.00&&accel<=4.49)
			value=38000.00;
		else if(accel>=4.50&&accel<=7.99)
			value=48000.00;
		else if(accel<=8.00&&accel<=9.99)
			value=59000.00;
		else if(accel>=10.00)
			value=75000.00;
		

	}
	
}
