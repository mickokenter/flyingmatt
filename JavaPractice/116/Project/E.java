import java.util.*;
import java.text.*;

public class E{
	public final long k=8990000000L;
	public double ESingle(Charge c, Point p){
		DecimalFormat df=new DecimalFormat("0.00");
		double esTemp=0;
		esTemp=k*c.getQ()/(Math.pow(p.distance(c),2));
		String st=df.format(esTemp);
		double es=Double.parseDouble(st);
		return es;
	}
	public double Ex(Charge c, Point p){
		DecimalFormat df=new DecimalFormat("0.00");
		double Exc=0;
		Exc=p.cos(c)*ESingle(c,p);
		String st=df.format(Exc);
		double Excreturn=Double.parseDouble(st);
		return Excreturn;
	}
	public double Ey(Charge c, Point p){
		DecimalFormat df=new DecimalFormat("0.00");
		double Eyc=0;
		Eyc=p.sin(c)*ESingle(c,p);
		String st=df.format(Eyc);
		double Eycreturn=Double.parseDouble(st);
		return Eycreturn;
	}
	public String ExSign(Charge c, Point p){
		if(Ex(c,p)>0)
			return "Positive";
		else if(Ex(c,p)==0)
			return "No direction";
		else
			return "Negative";
	}
	public String EySign(Charge c, Point p){
		if(Ey(c,p)>=0)
			return "Positive";
		else if(Ey(c,p)==0)
			return "No direction";
		else
			return "Negative";
	}
	public double EResultant(ArrayList<Charge> alc, Point p){
		double er;
		double ExR=0, EyR=0;
		for(int i=0; i<alc.size(); i++){
			ExR=ExR+Ex(alc.get(i),p);
			EyR=EyR+Ey(alc.get(i),p);
		}
		er=Math.sqrt(Math.pow(ExR,2)+Math.pow(EyR,2));
		return er;
	}
}