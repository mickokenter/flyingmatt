import java.util.*;

public class CombinationsFormula{
	public static int combinations(int n, int p){
		int ans=0;
		if(p==0)
			ans=1;
		else if(n==p)
			ans=1;
		else
			ans=factorial(n)/(factorial(p)*factorial(n-p));
		return ans;
	}
	public static int factorial(int f){
		int ans=0;
		if(f==0)
			ans=1;
		else if(f>0)
			ans=f*factorial(f-1);
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
			System.out.println(combinations(n,p));
		}while(n>=0 || p>=0);
	}
}