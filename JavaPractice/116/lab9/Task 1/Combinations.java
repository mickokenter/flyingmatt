import java.util.*;

public class Combinations{
	public static int Combinations(int n, int p){
		int ans=0;
		if(p==0)
			ans=1;
		else if(n==p)
			ans=1;
		else
			ans=Combinations(n-1, p-1)+Combinations(n-1, p);
		return ans;
	}
	public static void main(String[] args){
		int n=0, p=0;
		do{
			System.out.println("Please enter the combination numbers (enter -1 to terminate)");
			Scanner input=new Scanner(System.in);
			n=input.nextInt();
			p=input.nextInt();
			if(n<0 || p<0 || n<p)
				break;
			System.out.println(Combinations(n,p));
		}while(n>=0 || p>=0);
	}
}