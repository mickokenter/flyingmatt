import java.util.Scanner;

public class Program{
	public static void main(String[] args){
		Scanner scan=new Scanner(System.in);
		int start = scan.nextInt();
		int end = scan.nextInt();
		int increment = scan.nextInt();
		int perLine = scan.nextInt();
		int length = (end-start)/increment+1;
		int[] number = new int[length];
		for (int i=0; i<length; i++){
			number[i]=start+increment*i;
			System.out.print(number[i]+" ");
			if ((i+1)%perLine==0){
				System.out.print("\n");
			}
		}
	}
}