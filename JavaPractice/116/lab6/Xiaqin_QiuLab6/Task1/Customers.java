import java.util.Vector;
import java.util.*;
public class  Customers
{
	
	public String type=" ";
	public int score=0;
	static boolean flag=true;
	static Vector v=new Vector();

	public void setType(String a)
	{
		type=a;
	}

	public void setScore(int b)
    {
		score=b;
	}

	public String getType()
	{
		return type;
	}

	public int getScore()
	{
		return score;
	}
	
	public Vector customerData()
	{   
		if(flag){
			System.out.println("Please enter the 2 pieces of information for type and score for a customer object separated by space or type done");
			Scanner scan=new Scanner(System.in);
			String line=scan.nextLine();
			do{
				StringTokenizer strtok=new StringTokenizer(line);
				String str1=strtok.nextToken();
				String str2=strtok.nextToken();
				int scoreT=Integer.parseInt(str2);
				Customers cstm=new Customers();
				cstm.setType(str1);
				cstm.setScore(scoreT);
				v.add(cstm);
				System.out.println("Please enter the 2 pieces of information for type and score for a customer object separated by space or type done");
			    line=scan.nextLine();
			}while(!line.equals("done"));
			flag=false;
		}
		return v;
	}
}
