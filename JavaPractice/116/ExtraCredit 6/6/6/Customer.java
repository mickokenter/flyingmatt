import java.util.*;
public class Customer
{
	String data;
	int score;
	
	public String getData()
	{
		return data;
	}
	public int getScore()
	{
		return score;
	}
	public void setData(String sd)
	{
		data=sd;
	}
	public void setScore(int ss)
	{
		score=ss;
	}
	public Vector customerData()
	{
		Vector data=new Vector();
		
		Customer c1=new Customer();
		c1.setData("Architect");
		c1.setScore(500);
		data.addElement(c1);

		Customer c2=new Customer();
		c2.setData("Other");
		c2.setScore(500);
		data.addElement(c2);
		
		Customer c3=new Customer();
		c3.setData("Architect");
		c3.setScore(800);
		data.addElement(c3);
		
		Customer c4=new Customer();
		c4.setData("Other");
		c4.setScore(700);
		data.addElement(c4);	
		
		return data;	
	}
}