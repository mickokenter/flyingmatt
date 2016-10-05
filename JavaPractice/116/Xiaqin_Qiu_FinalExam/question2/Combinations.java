package recursion;
import java.io.*;
import java.util.*;

public class Combinations{
	public static void main(String[] args){
		Scanner scan=new Scanner(System.in);
		boolean flag=true;
		while(true){
			System.out.println("Please enter the combination numbers");
			int a=scan.nextInt();
			int b=scan.nextInt();
			if(a==-1 || b==-1){
				break;
			}
			else if(a<0 || b<0){
				System.out.println("Please input positive numbers");
				flag=false;
			}
			else if(a<b){
				System.out.println("Please input n>=p");
				flag=false;
			}
			else{
				if(flag){
					Combinations cb=new Combinations();
					int outcome=cb.calculateCombinations(a,b);
					System.out.println(outcome);
				}
			}
			flag=true;
		}
	}
	public int calculateCombinations(int n, int p){
		int com=0;
		if(p==0){
			com=1;
		}
		else if(n==p){
			com=1;
		}
		else{
			com=calculateCombinations(n-1,p)+calculateCombinations(n-1,p-1);
		}
		return com;
	}
}